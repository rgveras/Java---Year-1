/**
 * 
 * This program Letter.java creates Letter objects to later be stored in a linked list to represent a word.
 * It also sets a label (an integer) to each letter to represent it as used, unused, correct or unset when comparing it to letters in a mystery word.
 * @author ricardoveras
 *
 */
public class Letter {
	private char letter;
	private int label;
	private int UNSET = -1;
	private int UNUSED = -2;
	private int USED = -3;
	private int CORRECT = -4;
	
	public Letter(char c) {
		label = UNSET;
		letter = c;
	}
	
	// If the other object is of type Letter, it is compared to this letter and returns true if they are the same character
	public boolean equals(Object otherObject) {
		if (otherObject instanceof Letter) {
			if (this.letter == ((Letter)otherObject).letter) {
				return true;
			} else 
				return false;
			}
		return false;
			}
	
	
	public String decorator() {
		String used = "+";
		String unused = "-";
		String correct = "!";
		String unset = " ";
		
		if (label == USED) {
			return used;
			}
		if (label == UNUSED) {
			return unused;
		}
		if (label == CORRECT) {
			return correct;
		} else 
			return unset;
	}
	
	
	public String toString() {
		return decorator() + letter + decorator();
	}
	
	public void setUnused() {
		label = UNUSED;
	}
	
	public void setUsed() {
		label = USED;
	}
	
	public void setCorrect() {
		label = CORRECT;
	}
	
	public boolean isUnused() {
		if (label == UNUSED) {
			return true;
		}
		return false;
	}
	
	
	// Produces an array of Letter objects given a string as a parameter
	public static Letter[] fromString(String s) {
		Letter[] letterArray = new Letter[s.length()];

		for (int i=0; i<s.length(); i++) {
			letterArray[i] = new Letter(s.charAt(i));
		}
		
		return letterArray;
	}

}
