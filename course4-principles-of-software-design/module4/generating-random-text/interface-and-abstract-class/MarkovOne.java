import java.util.*;
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovOne extends AbstractMarkovModel{    
    public MarkovOne() {
        order = 1;
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

