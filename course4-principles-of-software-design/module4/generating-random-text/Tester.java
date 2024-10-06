import java.util.*;
import edu.duke.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testGetFollows() {
        MarkovOne mo = new MarkovOne();
        String test = "this is a test yes this is a test";
        mo.setTraining(test);
        ArrayList<String> follows = mo.getFollows("es");
        System.out.println(follows);
        
    }
    
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String test = fr.asString();
        MarkovOne mao = new MarkovOne();
        mao.setTraining(test);
        ArrayList<String> follows = mao.getFollows("t");
        System.out.println(follows.size());
    }
}
