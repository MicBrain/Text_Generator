/** The implementation of Word Tuple data structure.
 *  @author: Rafayel Mkrtchyan
**/

public class WordTuple {

	/** The constructor of Word. **/
	public WordTuple(String word, Integer number) {
		_word = word;
		_number = number;
	}

	/** Returns the value of word. **/
	public String getword() {
		return this._word;
	}

	/** Sets the value of the word to STR. **/
	public void setword(String str) {
		this._word = str;
	}

	/** Returns the value of number. **/
	public Integer getNumber() {
		return this._number;
	}

	/** Sets the value of the number to NUM. **/
	public void setNumber(Integer num) {
		this._number = num;
	}

	public boolean isEqualTo(WordTuple tup) {
		if (tup == null) {
			return false;
		}
		if (this._word.equals(tup.getword()) &&
			this._number == tup.getNumber()) {
			return true;
		}
		return false;
	}

	public void printTuple() {
		System.out.println("Content of my tuple is: " + this._word + " " + this._number);
	}

	/** Contains the first element of the Word Tuple. **/
	private String _word;

	/** Contains the second element of the Word Tuple. **/
	private Integer _number;
}