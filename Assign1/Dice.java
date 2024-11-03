/**
 * 
 *The Dice program initializes a dice value to -1.
 *It also implements methods to set the dice to a value or have a random value set.
 *It also contains a public method that returns the value of the die.
 *@author ricardoveras
 *
 */
public class Dice {

	private int value;
	
	
	public Dice() {
		value = -1 ;
	}
	
	public Dice(int diceValue) {
		value = diceValue ; 
	}
	
	public void roll() {
		this.value = RandomNumber.getRandomNumber(1, 6) ;
	}
	
	public int getValue() {
		return value;
	}
	
	}

