/*
 * Assignment Number: In Class Assignment 1
 * File Name: IClass_HW1
 * Student: David Farynyk
 * Group H
 * 
 * Class: ITCS 5180 - Mobile Application Development, Summer Session 1
 * Java Class: Words.java
 * Date: 5/22/14
 */


public class Words {
	private String word;
	private int count;
	
	Words( String input ) {
		count = 1;
		word = input;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount( int input ) {
		count = input;
	}
	
	public String getWord() {
		return word;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Words other = (Words) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
	/*
	public boolean equals( String inputString ) {
		if( word.equals( inputString ) )
			return true;
		
		// If the strings are not equal
		return false;
	}
	*/
	
}
