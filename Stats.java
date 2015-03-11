/** The implementation of Stats data structure.
 *  @author: Rafayel Mkrtchyan
**/

import java.util.ArrayList;

public class Stats {

	/** The constructor of Stats. **/
	public Stats(ArrayList<WordTuple> arr, Integer sigma) {
		_arr = arr;
		_sigma = sigma;
	}

	/** Returns the arr from the Stats. **/
	public ArrayList<WordTuple> getArray() {
		return this._arr;
	}

	/** Changes the value of arr to newArr. **/
	public void setArray(ArrayList<WordTuple> newArr) {
		for (int index = 0; index < this._arr.size(); index++) {
			WordTuple current = this._arr.get(index);
			current.setword(newArr.get(index).getword());
			current.setNumber(newArr.get(index).getNumber());
		}
	}

	/** Returns the value of Sigma**/
	public Integer getSigma() {
		return this._sigma;
	}

	/** Changes the value of Sigma to num. **/
	public void setSigme(int num) {
		this._sigma = num;
	}

	/** Saves the Arraylist of WordTuples. **/
	private ArrayList<WordTuple> _arr;

	/** Saves Sigma value. **/
	private Integer _sigma;
}
