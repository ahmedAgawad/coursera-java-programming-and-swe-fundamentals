
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private ArrayList<String> uniqueIPs;
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
         uniqueIPs = new ArrayList<String>();
     }
        
     public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         for(LogEntry le : records) {
                String currentIPaddr = le.getIpAddress();
                if(!uniqueIPs.contains(currentIPaddr)) {
                    uniqueIPs.add(currentIPaddr);
                }
         }           
         
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
        for(LogEntry le : records) {
            int currentStatusCode = le.getStatusCode();
            
            if(currentStatusCode > num) {
                System.out.println(le);
            }
        }    
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPday = new ArrayList<String>();
        for(LogEntry le : records) {
            String dateStr = le.getAccessTime().toString();
            if(dateStr.contains(someday) && !(uniqueIPday.contains(le.getIpAddress()))) {
                uniqueIPday.add(le.getIpAddress());
            }
        }
        
        
        return uniqueIPday;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> ips = new ArrayList<String>();
         for(LogEntry le : records) {
             int currentStatusCode = le.getStatusCode();
             if(currentStatusCode >= low && currentStatusCode <= high && !(ips.contains(le.getIpAddress()))) {
                 ips.add(le.getIpAddress());
                }
         }
         
         return ips.size();
     }
}
