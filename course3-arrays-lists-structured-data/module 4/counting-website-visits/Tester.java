
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int uniqueIPsNum = la.countUniqueIPs();
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int num = la.uniqueIPVisitsOnDay("Mar 17").size();
        System.out.println(num);
    }
    
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println(la.countUniqueIPsInRange(200, 299));
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        int maxNum = la.mostNumberVisitsByIp(counts);
        System.out.println("MaxNum : " + maxNum);
        ArrayList<String> list = la.iPsMostVisits(counts);
        System.out.println("MostVisted : " + list);
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> dateToIps = la.iPsForDays();
        System.out.println(dateToIps);
        System.out.println("day with most : " + la.dayWithMostIpVisits(dateToIps));
        ArrayList<String> testList = la.iPsWithMostVisitsOnDay(dateToIps, "Mar 17");
        System.out.println("test list : " + testList);
    }
    
    public void testForQuiz() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> visits = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(visits, "Sep 29"));
    }
}
