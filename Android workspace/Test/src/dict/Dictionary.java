package dict;

public interface Dictionary {
  
  /** Add an object to the dictionary.  Do not allow null keys or values.
      Do nothing upon invalid key or value.
      put() may be called with the same key multiple times; you do not
      have to collate these values in a list and associate with a single
      key.  Just keep adding key/value pairs to the appropriate buckets.
      Add objects to any linked lists at the end of the list to preserve
      order of addition.
   */
  public void put(String key, Object value);

  /** Delete the first object you find associated with key from
   *  dictionary and return it.  Return null if not found.
   */
  public Object remove(String key);

  /** Return the first value object you find associated with key from
   *  dictionary; return null if not found.
   */
   public Object get(String key);

  /** Return a string of the key/value pairs in the dictionary.
      The string should look EXACTLY like this for both implementations:

        [key1:value1, ..., keyN:valueN]

      If there are multiple values with the same key, list the key
      multiple times.
      The element separator is ", " (comma space) and there NO newline
      character as I will print that myself during testing.  
   */
   public String toString();
}
