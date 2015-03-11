/** Manual tests for Text Generator.
 *  @author: Rafayel Mkrtchyan
**/

import java.util.TreeMap;
import java.io.IOException;
import java.util.ArrayList;

public class testing {

	public static void main(String[] args) {
		checkForEmpty();
		System.out.println();
		checkForOne();
		System.out.println();
		testToMap();
		System.out.println();
		testingSaveToList();
		System.out.println();
		noElemSaveToList();
		System.out.println();
		oneElemSaveToList();
		System.out.println();
		testTextToData();
		System.out.println();
		testingGenerator();
		System.out.println();
		secondTestGenerator();
	}

	/** Checks saveToMap() for empty file. **/
	private static void checkForEmpty() {
		TextGenerator current = new TextGenerator("input.txt");
		try {
			TreeMap<String, ArrayList<WordTuple>> data = current.saveToMap();
			if (current.saveToMap() == null) {
				System.out.println("Checking Error 001");
			}
		} catch (IOException ex) {
    	}
	}

	/** Checks saveToMap() for file that contains just one word. **/
	private static void checkForOne() {
		TextGenerator current = new TextGenerator("input1.txt");
		try {
			TreeMap<String, ArrayList<WordTuple>> data = current.saveToMap();
			for (String item : data.keySet()) {
				ArrayList<WordTuple> currentList = data.get(item);
				for (WordTuple tup : currentList) {
					System.out.println(item + " " + tup.getword() + " "
						+ tup.getNumber());
				}
			}

		} catch (IOException ex) {
		}
	}

	/** Checks saveToMap() function. **/
	private static void testToMap() {
		TextGenerator current = new TextGenerator("input2.txt");
		try {
			TreeMap<String, ArrayList<WordTuple>> data = current.saveToMap();
			for (String item : data.keySet()) {
				ArrayList<WordTuple> currentList = data.get(item);
				for (WordTuple tup : currentList) {
					System.out.println(item + " " + tup.getword() + " "
						+ tup.getNumber());
				}
			}
		} catch (IOException ex){
    	}
	}

	/** Checks saveToList() function. **/
	private static void testingSaveToList() {
		TextGenerator current = new TextGenerator("input2.txt");
		try {
			ArrayList<Triple> result = current.saveToList();
			for (Triple item : result) {
				System.out.println("Pred is: " + item.getPredecessor() + ", " +
					"Succ is: " + item.getSuccessor() + ", " + 
					"# of Occurances: " + item.getNumber());
			}
		} catch(IOException exp) {

		}	
	}

	/** Checks saveToList() for an input file that doesn't 
	 *  have any data in it.
	**/
	private static void noElemSaveToList() {
		TextGenerator current = new TextGenerator("input.txt");
		try {
			ArrayList<Triple> result = current.saveToList();
			if (result == null) {
				System.out.println("Checking Error 002");
			}
		} catch (IOException ex) {
    	}
	}

	/** Checks saveToList() function for an input file that 
	 *  contains only 1 word in it. **/
	private static void oneElemSaveToList() {
		TextGenerator current = new TextGenerator("input1.txt");
		try {
			ArrayList<Triple> result = current.saveToList();
			for (Triple item : result) {
				System.out.println("Pred is: " + item.getPredecessor() + ", " +
					"Succ is: " + item.getSuccessor() + ", " + 
					"% of Occurances: " + item.getNumber());
			}
		} catch(IOException exp) {

		}	
	}

	/** Checks textToData() function. **/
	private static void testTextToData() {
		TextGenerator current = new TextGenerator("input2.txt");
		try {
			TreeMap<String, Stats> answer = current.textToData();
			for (String item : answer.keySet()) {
				Stats currentStats = answer.get(item);
				ArrayList<WordTuple> massive = currentStats.getArray();
				for (WordTuple tup : massive) {
					System.out.println("Word is: " + item + ", Succ is: " 
						+ tup.getword() + ", Successor's # is: "+ tup.getNumber() + 
						", sigma is: " + currentStats.getSigma());
				}
			}
		} catch (IOException exp) { }
	}

	/** Tests generateText() function for Martin Luther King's 
	 *  "I Have a Dream Speech" text. 
	**/
	private static void testingGenerator() {
		TextGenerator current = new TextGenerator("input2.txt");
		try {
			current.generateText(2000);
		} catch( IOException exp) {
		}
	}

	/** Tests generateText() function for  Shakespeare's "Hamlet" 
	 *  text. 
	**/
	private static void secondTestGenerator() {
		TextGenerator current = new TextGenerator("input3.txt");
		try {
			current.generateText(40000);
		} catch( IOException exp) {

		}
	}
}