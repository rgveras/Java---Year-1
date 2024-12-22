/**
 * 
 * This program WordLL.java stores a mystery word.
 * It also stores a history of past word guesses in a linked list.
 * @author ricardoveras
 *
 */
public class WordLL {
	private Word mysteryWord;
	private LinearNode<Word> history;
	
	public WordLL(Word mystery) {
		history = new LinearNode<Word>(null);
		mysteryWord = mystery;
	}
	
	
	// sets labels to the guessed word and adds it to the front of the history list. Returns true if the guess was correct
	public boolean tryWord(Word guess) {
		guess.labelWord(mysteryWord);
		
		if (history.getNext() != null) {
			LinearNode<Word> guess2 = new LinearNode<Word>(guess);
			guess2.setNext(history.getNext());
			history.setNext(guess2);
		} else {
			LinearNode<Word> guessNode = new LinearNode<Word>(guess);
			history.setNext(guessNode);
		}
		
		if (guess.labelWord(mysteryWord)) {
			return true;
		} else
			return false;
	}
	
	
	public String toString() {
		LinearNode<Word> temp = history.getNext();
		String guesses = temp.getElement().toString() + "\n";
		
		while (temp.getNext() != null) {
			temp = temp.getNext();
			guesses += temp.getElement().toString() + "\n";
		}
		
		return guesses;
		}
		
	}

