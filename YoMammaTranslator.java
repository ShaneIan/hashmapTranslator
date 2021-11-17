package hashmapTranslator;

import java.util.*;
import java.io.*;
public class hashmapTranslator {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, String> key = new HashMap<>();
        
        // Takes .txt file and brings data into useable form
        File inputFile = new File("acronyms.txt");
        Scanner in = new Scanner(inputFile);
        
        while(in.hasNextLine()){
            String raw = in.nextLine();
            raw = raw.toLowerCase();
            String[] rawSplit = raw.split("\\s",2);
            
            key.put(rawSplit[0], rawSplit[1]);
        }
        
        in.close();
        
        // Takes user input and splits into single words for translation
        Scanner user = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Enter a sentence: ");
        String userInput = user.next();
        
        String[] split = userInput.split("\\s+");
        
        /*
         Takes raw data and checks individual words for their presence in 
         the map, and if present translates and adds to array
         if not adds the simple data from the input ie non-translated
        */
        
        ArrayList<String> translatedSentence = new ArrayList<String>();
        
        for (int i=0; i < split.length; i++){
            if (key.containsKey(split[i].toLowerCase())){
                String data = key.get(split[i].toLowerCase());
                translatedSentence.add(data);
            } else {
                translatedSentence.add(split[i]);
            }   
        }
        
        // Takes raw info from array and prints in specified format
        System.out.print("Translated Sentence: ");
        
        for (int j=0; j < translatedSentence.size(); j++){
            String removePunct = translatedSentence.get(j);
            translatedSentence.set(j, removePunct.replaceAll("[!?.,]", ""));
            if (j == 0){
                String first = translatedSentence.get(j);
                String caseCorrected = first.substring(0,1).toUpperCase() + first.substring(1);
                System.out.print(caseCorrected + " ");
            } else {
                if (j == translatedSentence.size() - 1){
                    System.out.println(translatedSentence.get(j));
                } else {
                    System.out.print(translatedSentence.get(j)+ " ");
                }
            }
        }
    }
}
