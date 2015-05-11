/*
 * Assignment Number: In Class Assignment 1
 * File Name: IClass_HW1
 * Student: David Farynyk
 * Group H
 * 
 * Class: ITCS 5180 - Mobile Application Development, Summer Session 1
 * Java Class: PartTwo.java
 * Date: 5/22/14
 * Purpose: This assignment demonstrates basic interfaces, such as the Map, List, and Set interfaces.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class PartTwo {
	public static void main( String[] args ) {
		// Include implementation for Part 2, and create all the required classes.
		
		 // Step 1: Locate source text file
		 File sourceFile = new File( "words.txt" );
		 if ( !( sourceFile.isFile() ) ) {
			 Scanner kb = new Scanner( System.in );
			 System.out.print( "The file words.txt could not be found on your computer.\nPlease enter the correct filename (without .txt): " );
			 
			 String userInput = kb.nextLine();
			 while( !( new File( userInput + ".txt" ).isFile() ) ) {
				 System.out.print( "\nThe file " + userInput + " .txt could not be found on your computer.\nPlease enter the correct filename (without .txt): " );
				 userInput = kb.nextLine();
			 }
			 sourceFile = new File( userInput + ".txt" );
			 kb.close();
		 }
		 
		 // Step 2: Store source data in HashSet to eliminate duplicates and count the number of each entry
		 HashSet<Words> allWords = new HashSet<Words>();		// Used to limit instances of 1 per element
		 ArrayList<Words> counters = new ArrayList<Words>();	// Used to access objects and increment counters
		 try {
			 Scanner inputFile = new Scanner( sourceFile );
			 while( inputFile.hasNext() ) {
				 Words curr = new Words( inputFile.nextLine() );
				 
				 // If the element is already in the HashSet then increment that element's counter
				 if( allWords.contains( curr ) ) {
					 Iterator<Words> itr = counters.iterator();
					 while( itr.hasNext() ) {
						 Words elem = itr.next();
						 if( elem.equals( curr ) )
							 elem.setCount( elem.getCount() + 1 );
					 }
				 }
				 // If the element is not yet in the HashSet then add that element and create a Map to its counter
				 else {
					 allWords.add( curr );
					 counters.add( curr );
				 }
			 }
			 inputFile.close();
		 } catch ( FileNotFoundException e ) {
			 System.out.println( "The source file could not be found. This error should not happen since file validation was performed beforehand." );
		 }
		
		 // Step 3: Sort the list of elements by frequency
		 Collections.sort( counters, new Comparator() {
			
		        public int compare(Object arg0, Object arg1) {
		            // return (int)pers0.lastName.charAt(0) - (int)pers1.lastName.charAt(0);
		        	
		            Words word0 = (Words)arg0;
		            Words word1 = (Words)arg1;
		            
		            return word1.getCount() - word0.getCount();
		        }
			 
		 });
		 
		 // Step 4: Display the 10 words that appear most frequently
		 Iterator<Words> temp = counters.iterator();
		 int i=0;
		 System.out.println( "Most frequently appearing words:\n" );
		 while( temp.hasNext() && i < 10 ) {
			 Words curr = temp.next();
			 System.out.println( "  - " + curr.getWord() + " (" + curr.getCount() + " times)"  );
			 i++;
		 }
		 System.out.println( "\nProcessed all entries and displayed top 10 results." );
	}
}
