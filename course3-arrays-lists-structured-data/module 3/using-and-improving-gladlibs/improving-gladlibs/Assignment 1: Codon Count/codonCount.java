import edu.duke.*;
import java.util.*;
/**
 * Write a description of codonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class codonCount {
private HashMap<String, Integer> dnaMap;
    
public codonCount() {
    dnaMap = new HashMap<String, Integer>();
}

public void bulidCodonMap(int start, String dna) {
    dnaMap.clear();
    int currentIndex = start;
    String currentCodon = "";
    while(currentIndex < dna.length() - 3) {
        currentCodon = dna.substring(currentIndex, currentIndex+3);
        if(!dnaMap.containsKey(currentCodon)) {
            dnaMap.put(currentCodon, 1);
        } else {
            dnaMap.put(currentCodon, dnaMap.get(currentCodon) + 1);
        }
        
        currentIndex = currentIndex + 3;
    }
}

public String getMostCommonCodon() {
    int maxRepeat = 0;
    String maxString = "";
    
    for(String codonKey : dnaMap.keySet()) {
        if(dnaMap.get(codonKey) > maxRepeat) {
            maxRepeat = dnaMap.get(codonKey);
            maxString = codonKey;
        }
    }
    
    return maxString;
}


public void printCodonCounts(int start, int end) {
    int value = 0;
    for(String codonKey : dnaMap.keySet()) {
        value = dnaMap.get(codonKey);
        if(value >= start && value <= end) {
            System.out.println(codonKey + "\t" + value);
        }
    }
} 


public void tester() {
    FileResource fr = new FileResource();
    String dna = fr.asString();
    
    for(int i = 0; i < 3; i++) {
        bulidCodonMap(i,dna);
        System.out.println("Reading frame starting with " + i + "results in " + dnaMap.size() + "unique codons");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + dnaMap.get(getMostCommonCodon()));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1,5);
     
    }
}

}
