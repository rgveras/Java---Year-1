/**
 * The program Yahtzee initializes an array of 5 Dice objects.
 * The values of the dice are then set.
 * Methods are then carried out to calculate and store all the possible scores in an array.
 * The maximum score and its index in the array of possible scores is stored in a separate array.
 * An equals method is available to check if two Yahtzee objects' values are identical.
 * A toString method is implemented to print the values of the dice.
 * @author ricardoveras
 *
 */
public class Yahtzee {
	
	private Dice[] dice;
	
	
	public Yahtzee() {
		dice = new Dice[5];
		
		for (int i=0; i<5; i++) {
			dice[i] = new Dice();
			dice[i].roll(); 
			}
		}
		
	//sets dice array values to userRoll (userRoll provides dice values)
	public Yahtzee(Dice[] userRoll) {
		dice = userRoll; 
		}
	
	//counts, stores in array, returns the amount of each die value rolled
	public int[] getValueCount() {
		int[] numCount = new int[6];
		numCount[0] = 0;
		
		for (int i=0; i<5; i++) {
		
			for (int k=1; k<7; k++) {
				
				if (dice[i].getValue() == k) {
					numCount[k-1]++; 
					}
			}
		}
		return numCount;
	}
	
	//sums the values of all dice
	private int sumDice() {
		int sum = 0;
		
		for (int i=0; i<6; i++) {
			sum += getValueCount()[i] * (i+1) ; 
			}
		return sum;
		}
	
	//calculates, stores in an array, returns the scores for Yahtzee using the game's algorithm's 
	public int[] getScoreOptions() {
		int[] scores = new int[13];
		int j = 0;
		
		//scores of index 0-5 (set to sum of corresponding die value)
		for (int i=0; i<6; i++) {
			scores[i] = getValueCount()[i] * (i+1);
		}

		//scores of index 6,7 and 11 (if 3 or 4 dice have same value, score[6] or score[7] is sum of dice. If all 5, score[11] is set to 50)
		for (int i=0; i<6; i++)  {
			if (getValueCount()[i] >= 3) 
					scores[6] = sumDice(); 
			if (getValueCount()[i] >= 4) 
					scores[7] = sumDice();
			if (getValueCount()[i] == 5) 
					scores[11] = 50;
				}
			
		//scores[8] set to 25 if full house
		for (int i=0; i<6; i++) {
			if (getValueCount()[i] == 2) {
				for (int k=0; k<6; k++) {
					if (getValueCount()[k] == 3) {
						scores[8] = 25;
					}
					}
				}
			}

		//scores[9], scores[10] set to 30, 40 if 4, 5 consecutive die values, respectively
		for (int i=0; i<5; i++) {
			
			if (getValueCount()[i] != 0 && getValueCount()[i+1] != 0) {
				j++;
			}
			if (j >= 3) {
				scores[9] = 30;
			}
			if (j == 4) {
				scores[10] = 40;
			}
		}			

		//scores[12] is set to the sum of the dice (Chance/No pattern)
		for (int i=0; i<5; i++) {
			scores[12] = sumDice();
			}
		return scores;
		}
					
	//returns array with maximum score value and its corresponding index from the scores array
	public int[] score() {
		int[] score = new int[2];
		int max = 0;
		
		for (int i=0; i<13; i++) {
			if (getScoreOptions()[i] > max) {
				max = getScoreOptions()[i]; 
			}
		}
		score[0] = max;
		
		for (int j=0; j<13; j++) {
			if (getScoreOptions()[j] == max) {
				score[1] = j;
				return score;
			}
		}
			return score;
			}
			
	//compares two Yahtzee objects' dice values and returns true if identical, false otherwise
	public boolean equals(Yahtzee other) {
		for (int i=0; i<6; i++) {
			if (this.getValueCount()[i] != other.getValueCount()[i]) 
			return false;
			} 
			return true;
		}

	//returns a string containing the dice values
	public String toString() {
		String diceStr = "";
		for (int i = 0; i<4; i++) {
			diceStr += dice[i].getValue() + ", ";
		}

		diceStr += dice[4].getValue();
		
		return "Dice: {" + diceStr + "}";
	}
		
	
}
