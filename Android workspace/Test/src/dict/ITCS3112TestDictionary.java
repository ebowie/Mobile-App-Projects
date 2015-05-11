package dict;

public class ITCS3112TestDictionary {
	
	public static void main(String [] args)
	{
		
		Object getValue;
		
		
	
	LinkedListDictionary d = new LinkedListDictionary();
	
	d.put("cool",1);
	d.put("awesome", 2);
	d.put("fantastic", 3);
	d.put("great",1);
	
		d.remove("great");
   
	
	getValue = d.toString();

	
	
	System.out.println(getValue);
   System.out.println(d.toString());
		
		HashDictionary c = new HashDictionary();
		
		c.put("abd", "hello");
		c.put("asd", "how");
		c.put("aert", "are");
		c.put("erd", "you");
		c.put("abc", "doing");
		c.put("ejd", "today");
		
		
		
		c.remove("aert");
		
		System.out.println(c.get("ejd"));
	
	
		
		System.out.println(c.toString());
	
	

	
	
	}
}	

	

	
