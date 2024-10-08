
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
        for(int i = from + 1; i < quakeData.size(); i++) {
            if(quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()) {
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for(int i =0; i < 70; i++) {
            int maxIdx = getLargestDepth(in , i);
            QuakeEntry qc = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qc);
        }    
    }
    
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        int lastElementIndex = quakeData.size() - 1;
        for(int i = 0; i < quakeData.size() - numSorted; i++) {
            if(i != lastElementIndex) {
                if(quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()) {
                    QuakeEntry quakeCurr = quakeData.get(i);
                    QuakeEntry quakeNext = quakeData.get(i+1);
                    quakeData.set(i, quakeNext);
                    quakeData.set(i+1, quakeCurr);
                }
            }
        }
        
    }
    
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for(int i =0; i < in.size() - 1 ; i++ ) {
            onePassBubbleSort(in, i);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for(int i =1; i < quakes.size(); i++) {
            if(quakes.get(i).getMagnitude() < quakes.get(i-1).getMagnitude()) {
                return false;
            }
        }
        
        return true;
    }
    
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int howManyPasses = 0;
        for(int i = 0; i < in.size()  - 1; i++) {
            if(checkInSortedOrder(in)) {
                break;
            }
            
            onePassBubbleSort(in, i);
            howManyPasses++;
        }
        
        System.out.println(howManyPasses + " passes needed to sort by magnitude with bubble");
    }
    
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int howManyPasses = 0;
        
        for (int i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in)) {
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            howManyPasses++;
        }
        System.out.println(howManyPasses + " needed by selection sort");
    }
    
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByLargestDepth(list);
        sortByMagnitudeWithCheck(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        
        // 4 2 5 9 8 1 / 2 4 5 8 1 9 / 2 4 5 1 8 9
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
