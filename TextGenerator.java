/** Implementation of Text Generator.
 *  @author: Rafayel Mkrtchyan
 *  @mm/dd/yyyy: 03/11/2015
**/

import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

public class TextGenerator extends Generator {

	TextGenerator(String path) {
		this._path = path;
	}

	@Override
	public TreeMap<String, ArrayList<WordTuple>> saveToMap()
		throws IOException {
		File file = new File(this._path);
		TreeMap<String, ArrayList<WordTuple>> data = 
			new TreeMap<String, ArrayList<WordTuple>>();
		Scanner scannedFile = new Scanner(new FileReader(file));
		BufferedReader br = new BufferedReader(new FileReader(this._path));
		if (br.readLine() == null) {
			System.out.printf("EROROR 001: The given file is empty.\n");
			return null;
		}
		String predecessor;
		predecessor = scannedFile.next();
		if (!scannedFile.hasNext()) {
			ArrayList<WordTuple> uniqueArray = new ArrayList<WordTuple>();
			WordTuple onlyTuple = new WordTuple(null, 1);
			uniqueArray.add(onlyTuple);
			data.put(predecessor, uniqueArray);
			return data;
		}
		while(scannedFile.hasNext()) {
			String successor;
			successor = scannedFile.next();
			_lastWord = successor;
			WordTuple currentTuple = new WordTuple(successor, 1);
			if (!data.containsKey(predecessor)) {
				ArrayList<WordTuple> currentArray = new ArrayList<WordTuple>();
				currentArray.add(currentTuple);
				data.put(predecessor, currentArray);
			} else {
				boolean checker = true;
				ArrayList<WordTuple> currentList = data.get(predecessor);
				for (WordTuple tup : currentList) {
					if (tup.getword().equals(successor)) {
						tup.setNumber(tup.getNumber() + 1);
						checker = false;
						break;
					}
				}
				if (checker) {
					data.get(predecessor).add(currentTuple);
				}
			}
			predecessor = successor;
		}
		return data;
	}

	@Override
	public ArrayList<Triple> saveToList() throws IOException {
		ArrayList<Triple> sortedData= new ArrayList<Triple>();
		TreeMap<String, ArrayList<WordTuple>> currentMap = this.saveToMap();
		if (currentMap == null) {
			System.out.printf("EROROR 002: The given TreeMap is empty.\n");
			return null;
		}
		for (String item : currentMap.keySet()) {
			ArrayList<WordTuple> currentList = currentMap.get(item);
			Double overallCount = 0.0;
			for (WordTuple tup : currentList) {
				Double num = tup.getNumber() / 1.0;
				overallCount += num;
			}
			for (WordTuple tup : currentList) {
				String successor = tup.getword();
				Double num = tup.getNumber() / 1.0;
				Double percentage;
				percentage = this.round((num * 100.0) / overallCount, 2);
				Triple currentTriple = new Triple(item, successor, percentage);
				sortedData.add(currentTriple);
			}
		}
		return sortedData;
	}

	/** Rounds number NUMBER to POSITIONS decimal points. **/
	private static double round(double number, int positions) {
		if (positions < 0) {
			throw new IllegalArgumentException();
		} else {
			BigDecimal num = new BigDecimal(number);
			num = num.setScale(positions, RoundingMode.HALF_UP);
			return num.doubleValue();
		}
	}

	/** Random number generator that generates number between lower_bound
	 *  and upper_bound inclusivelu. **/
	private int randomNum(int lower_bound, int upper_bound) {
		Random number = new Random();
		int result = number.nextInt((upper_bound - lower_bound) + 1) + lower_bound;
		return result;
	}

	@Override
	public TreeMap<String, Stats> textToData() throws IOException {
		TreeMap<String, Stats> data = new TreeMap<String, Stats>();
		if (this.saveToMap() == null) {
			return null;
		}
		File file = new File(this._path);
		Scanner scannedFile = new Scanner(new FileReader(file));
		if (scannedFile.hasNext()) {
			String firstWord = scannedFile.next();
			WordTuple currentTuple = new WordTuple(firstWord, 0);
			ArrayList<WordTuple> current = new ArrayList<WordTuple>();
			current.add(currentTuple);
			Stats curreStats = new Stats(current, 1);
			data.put("$", curreStats);
		}
		TreeMap<String, ArrayList<WordTuple>> map = this.saveToMap();
		int maxValue = 0;
		int minValue = 10000;
		for (String item : map.keySet()) {
			String key = item;
			ArrayList<WordTuple> myList = map.get(item);
			int sigma = 0;
			int value = 0;
			ArrayList<WordTuple> newList = new ArrayList<WordTuple>();
			for (WordTuple tup : myList) {
				sigma += tup.getNumber();
				String predecessor = tup.getword();
				int counter = tup.getNumber();
				if (counter > maxValue) {
					_mostCommonWord = predecessor;
					maxValue = counter;
				}
				if (counter < minValue) {
					_leastCommonWord = predecessor;
					minValue = counter;
				}
				while (counter > 0) {
					WordTuple update = new WordTuple(predecessor, value);
					newList.add(update);
					value += 1;
					counter -= 1;
				}
			}
			Stats currentStats = new Stats(newList, sigma);
			data.put(key, currentStats);
		}
		ArrayList<WordTuple> finalList = new ArrayList<WordTuple>();
		finalList.add(new WordTuple(".", 1));
		Stats newStats = new Stats(finalList, 1);
		data.put(_lastWord,newStats);
		return data;
	}

	@Override
	public void generateText(long length) throws IOException {
		clearContent();
		TreeMap<String, Stats> data = this.textToData();
		File file = new File(_path);
		Scanner scanned = new Scanner(new FileReader(file));
		String currentWord;
		if (length < 0) {
			System.out.println("Error 004: length cannot be less than 0");
		} else {
			long lengthCounter = 1;
			try {
				String res = _mostCommonWord.substring(0, 1).toUpperCase() +
				_mostCommonWord.substring(1);
				writeToFile(res, OUTFILE);
			} catch (IOException exp) { }
			while (scanned.hasNext()) {
				if (lengthCounter == length) {
					break;
				}
				currentWord = scanned.next();
				Stats currentStats = data.get(currentWord);
				int minimum_bound = 0;
				int sigma = currentStats.getSigma();
				int random = randomNum(0, sigma - 1);
				ArrayList<WordTuple> currentArray = currentStats.getArray();
				String finalString = "";
				for (WordTuple tup : currentArray) {
					long number = tup.getNumber();
					if (number == random) {
						finalString = tup.getword();
					}
				}
				String outputtedWord = " " + finalString;
				try {
					writeToFile(outputtedWord, OUTFILE);
				} catch (IOException exp) { }
				lengthCounter += 1;
			}
			if (lengthCounter < length) { 
				try {
					writeToFile(_leastCommonWord, OUTFILE);
				} catch (IOException exp) { }
			}
		}
	}

	/** Clears the content of the output file for the next call. **/
	private void clearContent() {
		try {
			PrintWriter writer = new PrintWriter(OUTFILE);
			writer.print("");
			writer.close();
		} catch (java.io.FileNotFoundException exp) {

		}
	}

	/** Writes the given string TEXT into a given file-path FILENAME. **/
	private void writeToFile(String text, String filename) throws IOException {
		BufferedWriter output = null;
		try {
			FileWriter stream = new FileWriter(OUTFILE, true);
			output = new BufferedWriter(stream);
			output.write(text);
		} catch (IOException exp) {
			System.err.printf("Error 003: Output file does not exist.");
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

	/** Contains information about the input file.**/
	private String _path;

	/** Contains the name of the output file. **/
	private final String OUTFILE = "output.txt";

	/** Saves the last word of the given text. **/
	private String _lastWord;

	/** Saves the most commond word in the text. **/
	private String _mostCommonWord;

	/** Saves the least common word in the text. **/
	private String _leastCommonWord;
}