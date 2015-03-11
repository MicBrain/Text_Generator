/** 	This is a public API of a system that randomly generates sensible
 *  output by generalizing patterns found in an input text. This system 
 *  is able to produce unique sentences based on a model that you design 
 *  using the input text. Such a system could be useful for randomly 
 *  generating poetry, song lyrics, or cryptic academic papers.
 *
 *  	The goal of this program is to:
 *	- Analyze the source text to build a model for your randomly generated 
 *  sentences.
 *  - Randomly generate unique sentences of user-defined length.
 *
 *  @author: Rafayel Mkrtchyan
 *  @mm/dd/yyyy: 03/11/2015
**/

import java.util.TreeMap;
import java.util.ArrayList;
import java.io.IOException;

public abstract class Generator {

	/** Returns a TreeMap data structure that stores all "words" of
	 *  the given text file as keys, and an ArrayLists of tuples that
	 *  contain the succeding word and the number of occurances of the 
	 *  word and its sucessor together in the text, as values. 
	**/
	public abstract TreeMap<String, ArrayList<WordTuple>> saveToMap() 
		throws IOException;

	/** Returns an ArrayList data structure that contains sorted Triples,
	 *  which contain the word from the text, its successor and the per-
	 *  centange of the occurance of the successor after that word in the
	 *  whole text. Sort is best on the alphabetical order of the words. 
	**/
	public abstract ArrayList<Triple> saveToList() throws IOException;

	/** Returns a TreeMap data structure that stores all the "words" of
	 *  the given text file as keys, and a Stats data strecuture as a
	 *  value.
	 *  Stats data structure contains an ArrayList of WordTuples, where we
	 *  save the successors of the word and their corresponding values and 
	 *  and variable called Sigam that stores the number of successor the 
	 *  given word has.
	 *  Example: Let's say the current word is A, and we have successors B,
	 *  C and D, such that (A, B) combination occurs 2 times, (A, C) combi-
	 *  nation occurs 3 times and (A, D) combination occurs only once in the
	 *  text. Then we can have the following table:
	 *				A -> B (2 times)
	 *				A -> C (3 times)
	 *				A -> D (1 time)
	 *	Here 		Sigma = 2 + 3 + 1 = 6
	 *
	 *  Now we can assign numbers from 0 to 5 to the all successors of A.
	 *  For simplicity we chose this WordTuples:
	 *	(A, 0), (A, 1), (B, 2), (B, 3), (B, 4), (C, 5)
	 *  Our Stats data structure contains an Arraylist that stores above
	 *  mentioned tuples and the value of Sigma.
	 **/
	public abstract TreeMap<String, Stats> textToData() throws IOException;

	/** This function analyzes the source text to build a model for 
	 *  randomly generated sentences and generats an arbitrary output
	 *  of given LENGTH by generalizing patterns found in an input text 
	**/
	public abstract void generateText(long length) throws IOException;
}
