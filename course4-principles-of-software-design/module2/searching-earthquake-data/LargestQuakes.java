
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LargestQuakes {
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int indexLargest = 0;
        Double magLargest = -9999.9;
        for(int i = 1; i < data.size(); i++) {
            if(data.get(i).getMagnitude() > magLargest) {
                indexLargest = i;
                magLargest = data.get(i).getMagnitude();
            }
        } 
        
        return indexLargest;
    }
    
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> top = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        for(int i = 0; i < howMany; i++) {
            int largestIndex = indexOfLargest(copy);
            
            top.add(copy.get(largestIndex));
            copy.remove(largestIndex);
        }
    
        return top;
    }
    
    
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);
        
        /*for(QuakeEntry qe : list) {
            System.out.println(qe);
        }*/
        
        System.out.println("Number of EarthQuakes : " + list.size());
        
        /*int largestInd = indexOfLargest(list);
        System.out.println(list.get(largestInd));
        System.out.println(largestInd);*/
        
        ArrayList<QuakeEntry> top = getLargest(list, 5);
        for(QuakeEntry qe : top) {
        System.out.println(qe);
        }
    }
    
    
    
    
}
