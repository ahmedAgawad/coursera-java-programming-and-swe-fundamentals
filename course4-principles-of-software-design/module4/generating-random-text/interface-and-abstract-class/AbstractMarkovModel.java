
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int order;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    
 
    abstract public String getRandomText(int numChars);

    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> followList = new ArrayList<String>();
        
        int index = myText.indexOf(key);
        while(index != -1 && index + key.length() < myText.length()) {
            followList.add(myText.substring(index + key.length(), index + key.length() + 1));
            index = myText.indexOf(key, index+1);
        }
        
        return followList;
    }
    
    
    public  String toString() {
        return "MarkovModel of order " + Integer.toString(order);
   }
}
