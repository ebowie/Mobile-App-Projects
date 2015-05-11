
/**
 * LinkedListDictionary class that is implemented from the Dictionary.java interface.
 * 
 * @author Eric Bowers
 * @Version Feb 4 2014
 * @Purpose:  produce two Java implementations of a Dictionary interface: LinkedListDictionary and HashDictionary, 
 * which map a String key to an Object value
 */
package dict;



public  class LinkedListDictionary implements Dictionary
{	
	public LinkedListDictionary next;
	public String key;
	public Object value;
	public String display = " ";
	public LinkedListDictionary mainObject;
	
	/**
	 * no-arg Constructor
	 */
	
	public LinkedListDictionary()//This is no arg contructor
	{
		mainObject=null;
		next = null;
		key = null;
		value = null;
	}
	/**
	 * Constructor
	 * 
	 * @param key
	 * @param value
	 */
	public LinkedListDictionary (String key, Object value)// this contructor with paramenters arguments
	{
		this.key = key; //store key to main field varable 
		this.value = value; //store value to main field varable
		
	}
	/********************************************************************************************
	 * This Setters are use to stored parameters that are being use and store them into
	 * the main field variables.
	 * @param k
	 * 
	 *********************************************************************************************/
	
	//setters
	public void setKey(String k)//setter for key to be stored 
	{
		key = k;
	}
	/**
	 *  setter for value to be stored 
	 * @param v
	 */
	public void setValue(Object v)	// setter for value to be stored 	
	{
		value = v;
	}
	/**
	 *  setter for pointer to be stored
	 * @param n
	 */
	public void setNext(LinkedListDictionary n)// setter for pointer to be stored
	{
		next = n;
	}
	/*************************************************************************************************
	 * The Getters take the stored main variables and return them.
	 * @return key
	 * @return value
	 * @return  next
	 * 
	 **************************************************************************************************/
	
	//getters
	public String getKey()// getter to return String from key
	{
		return key;
	}
	public Object getValue() //getter to return Object from value
	{
		return value;
	}
	public LinkedListDictionary getNext() // getter return next that is point to another object
	{
		return next;
	}
	/*************************************************************************************************
	 * The Put Method is use to take both the "key" and "value" parameter and use them to put into the
	 * Linked List Object to be inserted in at the end of the list. Every linked list object will be added
	 * at the end of the list.
	 * @param key
	 * @param value 
	 **************************************************************************************************/
 // using the put method 
	public void put(String key, Object value)
	{	
		
		
		LinkedListDictionary referenceMainObj = mainObject; // setting the first object to reference object
		LinkedListDictionary newObject = new LinkedListDictionary(key,value);//putting key and value in a new Object
		
		if(mainObject == null)// if the main object has nothing in it then new object will be pushed into main object
		{
			mainObject = newObject;
		}
		else
		{
			while(referenceMainObj.next != null)// while loop will point to next object unless it a null then it break;
			{
				referenceMainObj = referenceMainObj.next; // while loop will keep going until get to the last pointer object and store new object in there.
			}
			
			referenceMainObj.next = newObject;// storing the new object
		}
	}
	
	/*************************************************************************************************
	 * The remove method is to take parameter "key" and iterator through a loop to check to see there is
	 * a match of the key. if there is not match then null will be displayed. if there is a match from 
	 * the main object in front of the linked list then it will insert main object in the currentObj and 
	 * then take the next object and insert it into the main object. if there is a  match some were in 
	 * the middle of the list then previousObj will keep track of currentObj so when it remove a object
	 * it shift link list to fill in that object that is removed.
	 * @param key
	 * @return currentObj
	 **************************************************************************************************/
	// remove method
	public Object remove(String key)
	{
		
		LinkedListDictionary currentObj = mainObject;// stores the first object on the list
		LinkedListDictionary previousObj = mainObject;// stores the first object on the list
		
		while(currentObj.key != key)// check through all the key to find a match
		{
			if(currentObj.next == null)// if equal null it will return null
			{
				return null;			
			}
			else
			{
				previousObj = currentObj; // take current object that went through while loop stores in in a different object.
				currentObj = currentObj.next;//take next object being pointed to and put it in the current object.
			}
		}
		if(currentObj == mainObject)//this if statement checks to if first object equal to current object that was enter by the users
		{
			mainObject = mainObject.next; // its take main object and pushes the next object to the front of the list to become main object
		}
		else
		{
			previousObj.next = currentObj.next;// if does equal the first object on list then, the next previous and current will get stored in to previous next object
		}
		return currentObj + " deleted";
	}
/*************************************************************************************************
 * The Get Method will take in a parameter  of "key" to find a match of that key and if there not
 * a match of that key it will return object null. However, if there is match it will find the value
 * of that matched key and return value.
 * @param key
 * @return getObject.value 
 **************************************************************************************************/

	public Object get(String key)// to find specific key and return the value 
	{
		
		LinkedListDictionary getObject = mainObject;// storing first object in to get object
		
		while(getObject.key != key)// checking to see which key will match up 
		{
			if(getObject.next == null)// if there no match it will return no 
			{
				return null;
			}
			else
			{
				getObject = getObject.next; // else it will put the match in the get object and then return it.
			}
			
			
			
		}
		
		return getObject.value + "Found!!";
	}
	
	/*************************************************************************************************
	 * The toString Method will make a LinkedListDictionary Object and store the main object
	 * inside displayObj then if the displayobj is empty it will return null for display String.
	 * However, if there key/values link list objects then it will insert those objects in the 
	 * display String until it reaches null then it will return display.
	 * 
	 * @return display
	 **************************************************************************************************/
	
	public String toString()// return all the keys/values 
	{
		LinkedListDictionary displayObj = mainObject; // stores the first object in the list
		if(displayObj != null)// if list is empty then it will return null to the display String 
		{
		while(displayObj != null)// this will find all the objects in the list and push them through display String 
		{
			display += displayObj.key+":"+displayObj.value+", ";// pushing each object in the displaying string
			displayObj = displayObj.next;// going to the next object that is being pointed too.
			
		}
		   return "["+display+"]"; // returning all keys/values
			
		}
		else
		{
			return display = null; // returns null when there  is no keys 
		}
	}
}
	


