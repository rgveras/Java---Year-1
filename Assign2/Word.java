/**
 * 
 * This program Word.java creates a linked list of letter objects to represent a word
 * @author ricardoveras
 *
 */
public class Word {
	LinearNode<Letter> firstLetter;
	
	public Word(Letter[] letters) {
		firstLetter = new LinearNode<Letter>(letters[0]);
		LinearNode<Letter> curr;
		LinearNode<Letter> next = null;
		
		for (int i=letters.length-1; i>0; i--) {
			curr = new LinearNode<Letter>(letters[i]);
			firstLetter.setNext(curr);
			curr.setNext(next);
			next = curr;
		}
	}
	
	
	public String toString() {
		String word = "Word: ";
		LinearNode<Letter> next = firstLetter;
		
		while (next != null) {
			word += next.getElement().toString() + " ";
			next = next.getNext();
		}
		
		return word;
	}
	
	
	// returns the length of the mystery word
	private int mysteryLength(Word mystery) {
		int count = 0;
		LinearNode<Letter> temp = mystery.firstLetter;
		
		while (temp != null) {
			count++;
			temp = temp.getNext();
			}	
		return count;
		}
	
	
	// returns true if all letters in the word is set to correct, false otherwise
	private boolean isCorrect(Word mystery) {
		LinearNode<Letter> letterNode = firstLetter;
		LinearNode<Letter> mysNode = mystery.firstLetter;
		
		while (letterNode != null && true) {
			if (letterNode.getElement().equals(mysNode.getElement())) {
				letterNode = letterNode.getNext();
				mysNode = mysNode.getNext();
			} else {
				return false;
			}
		}
			return true;
			}
			
	
	// Labels used letters and returns true if mystery word is identical to this.Word
	public boolean labelWord(Word mystery) {
		Letter temp = firstLetter.getElement();
		LinearNode<Letter> thisNode = firstLetter;
		Letter mysTemp = mystery.firstLetter.getElement();
		LinearNode<Letter> mysNode = mystery.firstLetter;
		int check = 0;
		int length = 0;
		
		// Iterates through the letters in this word and sets each letter as unused to start
		while (thisNode != null) {
			temp = thisNode.getElement();
			temp.setUnused();
			mysTemp = mystery.firstLetter.getElement();
			mysNode = mystery.firstLetter;
			// check monitors the location of the letter in the mystery word for checking if it is correct or just used
			check = 0;
			
			// Iterates through mystery word. Sets this word's letter to correct or used if applicable
			while (mysNode != null) {
				mysTemp = mysNode.getElement();
				if (temp.equals(mysTemp)) {
					if (check == length) {
						check++;
						temp.setCorrect();
						mysNode = mysNode.getNext();
					}
					else {
						check++;
						temp.setUsed();
						mysNode = mysNode.getNext();
					}
				} else {
				check++;
				mysNode = mysNode.getNext();
				}
				}
			length++;
			thisNode = thisNode.getNext();
			}
		
		// If this word is the same length as the mystery word and all letters are correct (they are identical), returns true
		if (length == mysteryLength(mystery) && isCorrect(mystery)) {
			return true;
		} else 
			return false;
		}
	
	}