import edu.duke.*;
import java.util.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
// Fields 
private ArrayList<String> myWords;
private ArrayList<Integer> myFreqs;

// Methods Implementation
public WordFrequencies() {
    myWords = new ArrayList<String>();
    myFreqs = new ArrayList<Integer>();
}


public void findUnique() {
    myWords.clear();
    myFreqs.clear();
    
    
    FileResource fr = new FileResource();
    
    for(String word : fr.words()) {
        word = word.toLowerCase();
        if(!myWords.contains(word)) {
            myWords.add(word);
            myFreqs.add(1);
        } else {
            int wordIndex = myWords.indexOf(word);
            int value = myFreqs.get(wordIndex);
            myFreqs.set(wordIndex, value + 1);
        }
    }
}

public int findIndexOfMax() {
    int maxIndex = 0;
    int maxRepeat = myFreqs.get(0);
    
    for(int i = 1; i < myFreqs.size(); i++) {
        if(myFreqs.get(i) > maxRepeat) {
            maxRepeat = myFreqs.get(i);
            maxIndex = i;
        }
    }
    
    return maxIndex;
}

// Methods Testing
public void tester() {
    findUnique();
    for(int i = 0; i < myWords.size(); i++) {
        System.out.println(myWords.get(i) + "\t" + myFreqs.get(i) );
    }
    
    int maxIndex = findIndexOfMax();
    
    System.out.println("The word that occurs most often and its count are: " + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
}
}
