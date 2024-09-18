import edu.duke.*;
import java.util.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
private ArrayList<String> names;
private ArrayList<Integer> counts;

public CharactersInPlay() {
    names = new ArrayList<String>();
    counts = new ArrayList<Integer>();
}

public void update(String person) {
    int index = names.indexOf(person);
    if(index == -1) {
        names.add(person);
        counts.add(1);
    } else {
        int value = counts.get(index);
        counts.set(index , value + 1);
    }
}

public void findAllCharacters() {
    FileResource fr = new FileResource();
    names.clear();
    counts.clear();
    
    
    for(String line : fr.lines()) { 
        int indexOfPoint = line.indexOf(".");
        if(indexOfPoint != -1) {
            update(line.substring(0, indexOfPoint));
        }
    }
    
}

public void charactersWithNumParts(int num1, int num2) {
    for(int i = 0; i < counts.size(); i++) {
        int currentCount = counts.get(i);
        
        if(currentCount >= num1 && currentCount <= num2) {
            System.out.println(names.get(i));
        }
    }
}

public int indexOfMaxChar() {
    int maxIndex = 0;
    int maxCount = counts.get(0);
    
    for(int i = 1; i < counts.size(); i++) {
        if(counts.get(i) > maxCount) {
            maxIndex = i;
            maxCount = counts.get(i);
        }
    }
    
    return maxIndex;
}

public void tester() {
    findAllCharacters();



   charactersWithNumParts(10,15);
}
}
