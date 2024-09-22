import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
private HashMap<String, ArrayList<String>> wordsToFiles;

public WordsInFiles() {
     wordsToFiles = new HashMap<String, ArrayList<String>>();
}

private void addWordsFromFile(File f) {
    FileResource fr = new FileResource(f);
    for(String word : fr.words()) {
        if(!wordsToFiles.containsKey(word)) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(f.getName());            
            wordsToFiles.put(word, list);
        } else {
            ArrayList<String> existedList = wordsToFiles.get(word);
            if(!existedList.contains(f.getName())) {
                existedList.add(f.getName());
            }
            wordsToFiles.put(word, existedList);
        }
    }
}

public void buildWordFileMap() {
    wordsToFiles.clear();
    DirectoryResource dr = new DirectoryResource();
    for(File eachFile : dr.selectedFiles()) {
        addWordsFromFile(eachFile);
    }
}

public int maxNumber() {
    int maxNumber = 0;
    
    for(String keyName : wordsToFiles.keySet()) {
        int currentWordNumber = wordsToFiles.get(keyName).size();
        if(currentWordNumber > maxNumber) {
            maxNumber = currentWordNumber;
        }
    }
    
    return maxNumber;
}



public ArrayList wordsInNumFiles(int number) {
    ArrayList<String> wordsList = new ArrayList<String>();
    
    for(String keyName : wordsToFiles.keySet()) {
         int currentWordNumber = wordsToFiles.get(keyName).size();
         if(currentWordNumber == number) {
             wordsList.add(keyName);       
         }
    }
    
    return wordsList;
}


public void printFilesIn(String word) {
    for(String keyName : wordsToFiles.keySet()) {
        if(keyName.equals(word)) {
            ArrayList<String> fileNames = wordsToFiles.get(keyName);
            
            for(int i = 0; i < fileNames.size() ;i++) {
                System.out.println(fileNames.get(i));
            }
        }
    }
}

public void tester() {
    buildWordFileMap();
    printFilesIn("red");
    

}
}