
/*In every file submitted you MUST place the following comments:
    a. Assignment # 1.
    b. TartTwo.
    c. Evodie Kayembe, Eric Bowers.
*/
//package inClassAssignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class PartTwo {
    private final LinkedList<String> words;
    private final HashMap<String, Integer> repeatedWords;

    public PartTwo(){
        words = new LinkedList<String>();
        repeatedWords = new HashMap<String, Integer>();

    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
          if (hm.get(o).equals(value)) {
            return o;
          }
        }
        return null;
      }

    public HashMap<String, Integer> getRepeatedWords(){
        return repeatedWords;
    }

    public void readFileAtPath(String filename) {
        // Lets make sure the file path is not empty or null
        if (filename == null || filename.isEmpty()) {
            System.out.println("Invalid File Path");
            return;
        }

        String filePath = System.getProperty("user.dir") + "/" + filename;
        BufferedReader inputStream = null;
        // We need a try catch block so we can handle any potential IO errors
        try {
            try {
                inputStream = new BufferedReader(new FileReader(filePath));
                String lineContent = null;
                // Loop will iterate over each line within the file.
                // It will stop when no new lines are found.
                while ((lineContent = inputStream.readLine()) != null) {
                    words.add(lineContent);
                    if (words.contains(lineContent)){
                        if(repeatedWords.containsKey(lineContent)){
                            repeatedWords.put(lineContent, repeatedWords.get(lineContent) + 1);
                        }
                        else{
                            repeatedWords.put(lineContent,1);
                        }
                    }
                    //System.out.println("Found the line: " + lineContent);
                }
            }
            // Make sure we close the buffered reader.
            finally {
                if (inputStream != null)
                    inputStream.close();
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
        }// end of method
    public static void main(String[] args) {
        // Include implementation for Part 2, and create all the required classes.
        PartTwo execute = new PartTwo();
        execute.readFileAtPath("words.txt");
        HashMap<String, Integer> map = execute.getRepeatedWords();

        Collection<Integer> repeats = execute.getRepeatedWords().values();
        Integer[] arr = repeats.toArray(new Integer[0]);
        List<Integer> list = Arrays.asList(arr);
        Collections.sort(list, Collections.reverseOrder());

        Integer values[] = new Integer[10];
        for (int i=0; i< 10; i++){
            values[i] = list.get(i);
        }

        for(int k=0; k< 10;k++){
            System.out.println("Word: "+ getKeyFromValue(map,values[k])+ "     # times detected: "+values[k]);
        }


    }
}
