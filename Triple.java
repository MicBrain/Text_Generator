/** The implementation of Triple data structure.
 *  @author: Rafayel Mkrtchyan
**/

public class Triple {

	/** The constructor of Triple. **/
	public Triple(String predecessor, String successor, Double number) {
		_predecessor = predecessor;
		_successor = successor;
		_number = number;
	}

	/** Returns the value of the predecessor. **/
	public String getPredecessor() {
		return this._predecessor;
	}

	/** Sets the value of the predecessor to STR. **/
	public void setPredecessor(String str) {
		this._predecessor = str;
	}

	/** Returns the value of the successor. **/
	public String getSuccessor() {
		return this._successor;
	}

	/** Sets the value of the successor to STR. **/
	public void setSuccessor(String str) {
		this._successor = str;
	}

	/** Returns the value of number. **/
	public Double getNumber() {
		return this._number;
	}

	/** Sets the value of the number to NUM. **/
	public void setNumber(Double num) {
		this._number = num;
	}

	/** Contains the predecessor word when iterating through the data. **/
	private String _predecessor;

	/** Contains the successor word when iterating through the data. **/
	private String _successor;

	/** Contains the number of occurances of predecessor and successor
	 *  together. **/
	private Double _number;
}