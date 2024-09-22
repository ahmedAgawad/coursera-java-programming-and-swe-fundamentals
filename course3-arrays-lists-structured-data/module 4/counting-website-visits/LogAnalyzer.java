
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
     
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> visitCounts = new HashMap<String, Integer>();
         
         for(LogEntry le : records) {
             String currentIpAdd = le.getIpAddress();
             if(!(visitCounts.containsKey(currentIpAdd))) {
                visitCounts.put(currentIpAdd, 1);
             } else {
                 visitCounts.put(currentIpAdd, visitCounts.get(currentIpAdd) + 1);   
             }
         }
         
         return visitCounts;
     }
     
     public int mostNumberVisitsByIp(HashMap<String, Integer> visitCounts) {
         int mostNum = 0;
         for(String keyName : visitCounts.keySet()) {
            if(visitCounts.get(keyName) > mostNum) {
                mostNum = visitCounts.get(keyName);
            }
         }
         
         return mostNum;
     }
     
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visitCounts) {
        ArrayList<String> mostVisitedIps = new ArrayList<String>();
        int mostNumVisited = mostNumberVisitsByIp(visitCounts);
        for(String keyName : visitCounts.keySet()) {
            if(visitCounts.get(keyName) == mostNumVisited) {
                mostVisitedIps.add(keyName);
            }
        }
        return mostVisitedIps;
     }
     
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> daysToIps = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records) {
            String dateStr = le.getAccessTime().toString().substring(4,10);
            String currIpAdd = le.getIpAddress();
            if(!daysToIps.containsKey(dateStr)) {
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(currIpAdd);
                daysToIps.put(dateStr, ips);
            } else {
                ArrayList<String> existedIps = daysToIps.get(dateStr);
                existedIps.add(currIpAdd);
                daysToIps.put(dateStr, existedIps);
            }
        }
        
        return daysToIps;
     }
     
     
     public String dayWithMostIpVisits(HashMap<String, ArrayList<String>> daysToIps) {
        int maxNum = 0;
        String dayWithMaxNum = "";
        
        for(String keyName : daysToIps.keySet()) {
            if(daysToIps.get(keyName).size() > maxNum) {
                maxNum = daysToIps.get(keyName).size();
                dayWithMaxNum = keyName;
            }
        }
        
        return dayWithMaxNum;
     }
     
     
      public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsPerDay, String day) {
        ArrayList<String> ipList = ipsPerDay.get(day);
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        
        for (String ip : ipList) {
             if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }
        
        return iPsMostVisits(ipCounts);
     }
     
}
