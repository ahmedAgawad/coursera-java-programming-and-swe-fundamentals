import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    // Methods Implementation
    public void countWordLengths(FileResource resource, int[] counts) {
        for(String word : resource.words()) {
            if(Character.isLetter(word.charAt(0)) && Character.isLetter(word.charAt(word.length() - 1))) {
                int size = word.length();
                counts[size]++;
            } else if((Character.isLetter(word.charAt(0)) && (Character.isLetter(word.charAt(word.length() - 1)) == false)) || 
              ((Character.isLetter(word.charAt(0)) == false) && Character.isLetter(word.charAt(word.length() - 1)) ) ) {
                int size = word.length() - 1;
                counts[size]++;
            } else {
                int size = word.length() - 2;
                counts[size]++;
            }
        }
    }
    
    public int indexOfMax(int [] values) {
        int maxIndex = 0;
        int maxFreq = values[0];
        for(int i = 1 ; i < values.length; i++) {
            if(values[i] > maxFreq) {
                maxIndex = i;
                maxFreq = values[i];
            }
        }
        
        return maxIndex;
    } 
    
    // Methods testing
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int [] count = new int[31];
        countWordLengths(fr, count);
        //for(int i = 0; i < count.length; i++) {
          //  System.out.println("word of size " + i + " is repeated " + count[i]);
        //}
        System.out.println(indexOfMax(count));
    }
}
