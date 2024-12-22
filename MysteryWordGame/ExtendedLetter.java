/**
 * 
 * This program ExtendedLetter.java extends the Letter class.
 * It introduces the concept of being related to other ExtendedLetter objects.
 * @author ricardoveras
 *
 */
public class ExtendedLetter extends Letter {
	private String content;
	private int family;
	private boolean related;
	private int SINGLETON = -1;

	public ExtendedLetter(String s) {
		super('c');
		content = s;
		related = false;
		family = SINGLETON;
	}
	
	public ExtendedLetter(String s, int fam) {
		super('c');
		content = s;
		related = false;
		family = fam;
	}
	
	
	/* 
	 * If other is an ExtendedLetter object it is compared to this family.
	 * If they are in the same family, this.related is set to true.
	 * If their content is the same, true is returned.
	*/ 
	public boolean equals(Object other) {
		if (other instanceof ExtendedLetter) {
			int otherFam = ((ExtendedLetter)other).family;
			if (otherFam == this.family) {
				this.related = true;
			} 
			String otherContent = ((ExtendedLetter)other).content;
			if (content.equals(otherContent)) {
				return true;
			} else
				return false;
		}
	return false;
	}
	
	
	public String toString() {
		String C = this.content;
		
		if (this.isUnused() && related == true) {
				return "." + C + ".";
			} else
				return this.decorator() + C + this.decorator();
	}
	
	
	/*
	 *  Creates an array of Letter objects. If codes is null, ExtendedLetter(content[i]) is used to create 
	 *  an ExtendedLetter object at position i in the letters array, containing the position i value of the content array.
	 *  If codes is not null, ExtendedLetter(content[i], codes[i]) is used to do the same.
	 *  The array is then returned.
	 */
	public static Letter[] fromStrings(String[] content, int[] codes) {
		Letter[] letters = new Letter[content.length];
		
		for (int i=0; i<letters.length; i++) {
			if (codes == null) {
				ExtendedLetter temp = new ExtendedLetter(content[i]);
				letters[i] = temp;
				} else {
					ExtendedLetter temp = new ExtendedLetter(content[i], codes[i]);
					letters[i] = temp;
		}
			}
		
		return letters;
	}

}
