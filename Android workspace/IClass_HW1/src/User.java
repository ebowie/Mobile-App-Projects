/*
 * Assignment Number: In Class Assignment 1
 * File Name: IClass_HW1
 * Student: David Farynyk
 * Group H
 * 
 * Class: ITCS 5180 - Mobile Application Development, Summer Session 1
 * Java Class: User.java
 * Date: 5/22/14
 */

import java.util.Scanner;

public class User {
	public String firstName, middleInitial, lastName;
	private int age;
	private String city, state;
 
	public User(String line){
		parseUser(line);
	}
 
	private void parseUser(String line){
		
		String[] input = line.split( "," );
		firstName = input[0];
		middleInitial = input[1];
		lastName = input[2];
		try {
			age = Integer.parseInt( input[3] );
		} catch ( NumberFormatException e ) {
			age = -1; // If the age is not formatted as an integer (part of error correcting), I can assign an "error" age and still compare records with other users.
		}
		city = input[4];
		state = input[5];
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleInitial == null) ? 0 : middleInitial.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleInitial == null) {
			if (other.middleInitial != null)
				return false;
		} else if (!middleInitial.equals(other.middleInitial))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	/*
	public boolean equals( User otherUser ) {
		
		// Begin with assumption that two users are equal. Check each value and set as false if any values are different.
		boolean theSame = true;
		if( !( otherUser.firstName.equals( this.firstName ) ) )
			theSame = false;
		else if( !( otherUser.middleInitial.equals( this.middleInitial ) ) )
			theSame = false;
		else if( !( otherUser.lastName.equals( this.lastName ) ) )
			theSame = false;
		else if( otherUser.age != this.age )
			theSame = false;
		else if( !( otherUser.city.equals( this.city ) ) )
			theSame = false;
		else if( !( otherUser.state.equals( this.state ) ) )
			theSame = false;
		
		// Return whether or not all fields match for these elements
		return theSame;
	}
	*/
	
	
}
