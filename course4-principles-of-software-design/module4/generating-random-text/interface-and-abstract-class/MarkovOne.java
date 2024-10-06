import java.util.*;
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovOne implements IMarkovModel{
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> followList = new ArrayList<String>();
        
        int index = myText.indexOf(key);
        while(index != -1 && index + key.length() < myText.length()) {
            followList.add(myText.substring(index + key.length(), index + key.length() + 1));
            index = myText.indexOf(key, index+1);
        }
        
        return followList;
    }
    
    public String getRandomText(int numChars) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        sb.append(key);
        
        for(int i = 0; i < numChars - 1; i++) {
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        
        return sb.toString();
        
    }
    
}

