 /*In every file submitted you MUST place the following comments:
a.Assignment 1
b. PartOne.java
c. Eric Bowers and Evodie Kayembe.

*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
public class PartOne {

    public static void main(String[] args) throws IOException {


        Object line;
        String records ="";
        User getList = new User();

        File file = new File("userList1.txt");//geting file
        Scanner list = new Scanner(file);
        ArrayList<String> temp = new ArrayList<String>();
        HashSet<String> RepeatedRecords = new HashSet<String>();
        //check the file from each line
        while(list.hasNext())
        {
           line = list.nextLine();//geting next line


          temp.add(getList.parseMethod(line));// displaying the repeat records

        }


        list.close();

     Collections.sort(temp);

     System.out.println(temp);

     System.out.println("Please scroll right to see the repeated records list ------>");



    }

}
