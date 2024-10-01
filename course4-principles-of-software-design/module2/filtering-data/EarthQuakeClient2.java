import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
    
        
        /*Filter f1 = new MagnitudeFilter(4.0, 5.0); 
        ArrayList<QuakeEntry> list1  = filter(list, f1);
        Filter f2 = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> list2 = filter(list1, f2);*/
        
        Location toyko = new Location(35.42, 139.43);
        Filter f1 = new DistanceFilter(toyko, 10000.0 * 1000.0, "distFilter");
        ArrayList<QuakeEntry> list1 = filter(list, f1);
        Filter f2 = new PhraseFilter("end", "Japan", "phraseFilter");
        ArrayList<QuakeEntry> list2 = filter(list1, f2);
        
        
        for (QuakeEntry qe: list2) { 
            System.out.println(qe);
        } 
    }

    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println(list.size() + " no of read data");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0, "MagFilter"));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0, "DepthFilter"));
        maf.addFilter(new PhraseFilter("any", "a", "PhraseFilter"));
        ArrayList<QuakeEntry> filteredList = filter(list , maf);
        
        String names = maf.getName();
        
        for(QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        
        System.out.println(names);
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println(list.size() + " no of read data");
        
        Location oklahoma = new Location(36.1314, -95.9372);
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0, "MagFilter"));
        maf.addFilter(new DistanceFilter(oklahoma, 10000.0 * 1000.0, "DistFilter"));
        maf.addFilter(new PhraseFilter("any", "Ca", "PhraseFilter"));
        ArrayList<QuakeEntry> filteredList = filter(list , maf);
        
        for(QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
    }
    
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
