/*
 * Assignment Number: In Class Assignment 1
 * File Name: IClass_HW1
 * Student: David Farynyk
 * Group H
 * 
 * Class: ITCS 5180 - Mobile Application Development, Summer Session 1
 * Java Class: PartOne.java
 * Date: 5/22/14
 * Purpose: This assignment demonstrates basic interfaces and methods to override the interfaces in Java.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;


public class PartOne {
	 public static void main(String[] args) {
		 
		 // Step 1: Read source text file
		 File sourceFile = new File( "userList1.txt" );
		 if ( !( sourceFile.isFile() ) ) {
			 Scanner kb = new Scanner( System.in );
			 System.out.print( "The file userList1.txt could not be found on your computer.\nPlease enter the correct filename (without .txt): " );
			 
			 String userInput = kb.nextLine();
			 while( !( new File( userInput + ".txt" ).isFile() ) ) {
				 System.out.print( "\nThe file " + userInput + " .txt could not be found on your computer.\nPlease enter the correct filename (without .txt): " );
				 userInput = kb.nextLine();
			 }
			 sourceFile = new File( userInput + ".txt" );
			 kb.close();
		 }
		 
		 // Step 2: Create HashSet to store data
		 HashSet<User> users = new HashSet<User>();
		 ArrayList<User> duplicates = new ArrayList<User>();
		 
		 // Step 3: Add users to HashSet, checking for duplicates in the data before adding
		 int numAnalyzed=0, numDuplicates=0;
		 try {
			 Scanner inputFile = new Scanner( sourceFile );
			 
			 while( inputFile.hasNext() ) {
				 User nextUser = new User( inputFile.nextLine() );
				 
				 // Check for duplicate User object. If duplicate exists, print message to screen. If duplicate does not exist, add User object to HashSet.
				 if( users.contains( nextUser ) ) {
					 duplicates.add( nextUser );
					 numDuplicates++;
				 }
				 else {
					 users.add( nextUser );
				 }
				 numAnalyzed++;
			 }
			 
		 } catch ( FileNotFoundException e ) {
			 System.out.println( "The input file was not found. This error should never occur since there is file validation in place." );
		 }
		 
		 // Step 4: Print the list of duplicate user entries
		 System.out.println( "Duplicate user entries:\n" );
		 
		 Collections.sort( duplicates, new Comparator() {

		        public int compare(Object arg0, Object arg1) {
		            if (!(arg0 instanceof User)) {
		                return -1;
		            }
		            if (!(arg1 instanceof User)) {
		                return -1;
		            }

		            User pers0 = (User)arg0;
		            User pers1 = (User)arg1;

		            // return (int)pers0.lastName.charAt(0) - (int)pers1.lastName.charAt(0);
		            return pers0.lastName.compareTo( pers1.lastName );
		        }
		    });
		 
		 
		 // Step 5: Print the output
		 Iterator<User> itr = duplicates.iterator();
		 while( itr.hasNext() ) {
			 User curr = itr.next();
			 System.out.println( "  - " + curr.lastName + ", " + curr.firstName + " " + curr.middleInitial + "." );
		 }
		 System.out.println( "\nAnalyzed " + numAnalyzed + " users and found " + numDuplicates + " duplicate entries." );
		 System.out.println( "The entire source file has been traversed." );
		 
		 
	 }
}