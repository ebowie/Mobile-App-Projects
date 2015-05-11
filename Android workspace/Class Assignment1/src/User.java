/*. In every file submitted you MUST place the following comments:
a.Assignment 1.
b. User.java
c. Eric Bowers and Evodie Kayembe.

 */



import java.util.HashSet;

public class User {

  Object repeatRecords = "";// object for repeatRecords
  Integer Size;
  String line1;
  String line2 ="";
  String Temp;


  HashSet<Object> records = new HashSet<Object>();
  String[] a = new String[5];

    String parseMethod(Object line){
        Size = records.size();//get size before

        records.add(line);

        if(Size == records.size())// check size now for comparson
        {
            repeatRecords = line;// insert repeated records
        }

        line1 = repeatRecords.toString();
        String[] split = line1.split(",");


        if(split.length == 6)
        {
            line2 = "";
            Temp = split[0];
            split[0] = split[2];
            split[2] = Temp;

        for(int i = 0; i<split.length; i++)
        {
           line2 += split[i];
           if(i != 5)
           {
               line2 += ",";
           }



        }
        }






       return line2;// return repeated records
    }

}
