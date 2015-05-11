/**
 * HashDictionary class that is implemented from the Dictionary.java interface.
 * 
 * @author Eric Bowers
 * @Version Feb 4 2014
 * @Purpose:  produce two Java implementations of a Dictionary interface: LinkedListDictionary and HashDictionary, 
 * which map a String key to an Object value
 */

package dict;

public class HashDictionary implements Dictionary {

	LinkedListDictionary [] hashList = new LinkedListDictionary[6];
	LinkedListDictionary insert = new LinkedListDictionary();
	 public String key;
	 public Object value;
	 public HashDictionary next;
	 public String display ="";
	 public int converter = 0;
	 public int hashPosition;
	 
	 /**
	  * no-arg Constructor 
	  */
	 
	 public HashDictionary() // every thing is put to null in this constructor
	 {
		 key = null;
		 value = null;
		 next = null;
	 }
	 
	 /**
	  * Constructor
	  * 
	  * @param key
	  * @param value
	  */
	 
	 public HashDictionary(String key,  Object value)
	 {
		 this.key = key;
		 this.value =value;
	 }
	 
	 /**
	  * The Put Method take the parameters of "key" and "value". The first thing that method does
	  * is that is does the hash function and take a string and convert it in character to be added
	  * together into a integer variable then it will get mod by fixed size array length. After
	  * that is done the fixed size array will have the position and in array call the put method 
	  * from the linked list insert the object from there.
	  * 
	  * @param key
	  * @param value
	  * 
	  */
	 
	 
	 public void put(String key, Object value)
	 {
		 for(int x = 0; x<key.length();x++)// use for loop to go through all the string letter in the String 
		 {
			 converter += key.charAt(x); // adding up characters
		 }
		 
		 hashPosition = converter%hashList.length;// moding the added character with array length
		 
		 hashList[hashPosition] = insert;
		 hashList[hashPosition].put(key, value);// calling the puts method from LinkedListDictionary
		}
	 
	 /**
	  * The Remove Method take in parameter of "key". The next thing it does is that it has
	  * the hash function that converts the String in to character that are being added up
	  * to be put inside a integer variable then mod by fixed array length. after that 
	  * the fixed array size called the remove method from the linked list and removes 
	  * object from the one of the linked lists.
	  * 
	  * @param key
	  * 
	  */
	
	 
	 public Object remove(String key)
	 {
		 Object hashRemove;
		 
		 for(int x = 0; x<key.length();x++)// use for loop to go through all the string letter in the String 
		 {
			 converter += key.charAt(x); // adding up characters
		 }
		 
		 hashPosition = converter%hashList.length;
		 hashList[hashPosition] = insert;
		hashRemove = hashList[hashPosition].remove(key);// calling the puts method from LinkedListDictionary
		 
		 return hashRemove; // return deleted object
	 }
	 
	 /**
	  * The Get Method take in parameter of "key". after that it will do the hash function 
	  * to take string convert it in to characters to be added up to a integer variable then 
	  * mod by the fixed array list length. after that use the fixed array to call the get method 
	  * from the linked list dictionary to get value back from one of those linked lists.
	  * 
	  * @param key
	  */
	 
	 public Object get(String key)
	 {
		 Object hashValue;
		 
		 for(int x = 0; x<key.length();x++)// use for loop to go through all the string letter in the String 
		 {
			 converter += key.charAt(x); // adding up characters
		 }
		 
		 hashPosition = converter%hashList.length;// moding the added character with array length
		 hashList[hashPosition] = insert;
		 hashValue=hashList[hashPosition].get(key);
		 
		 return hashValue;
	 }
	 
	 /**
	  * The toString Method take fixed array and loops through each linked list 
	  * and puts it on the display String to be return
	  */

	
	 public String toString()
	 {  
		int i = 0; 
		
		while(i<hashList.length)// going through each bucket through the array
		{
			hashList[i] = insert;
			if(i == 1)
			{
				
			display += hashList[i].toString();// adding the objects in to display String
			
			}
			
			i++;
			
		}
		
		  
		 return display;
	 }
	 
	 
	 
	 
	 

}
