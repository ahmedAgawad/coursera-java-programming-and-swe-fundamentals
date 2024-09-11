import edu.duke.*;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
 public int howMany(String stringa, String stringb) {
   int count = 0;
   
   int currentIndex = stringb.indexOf(stringa);
   if(currentIndex != -1) {
     count++; 
   }
   
   while(currentIndex != -1) {
     currentIndex = stringb.indexOf(stringa, (currentIndex + stringa.length()));
     if(currentIndex != -1) {
       count++;   
     }
   }
   
   return count;
 }
 
 public void testHowMany() {
   System.out.println(howMany("AA", "ATAAAA"));
  
   System.out.println(howMany("A", "ATAAAA"));
   
   System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
   
   System.out.println(howMany("g", "ATAAAA"));
 }
}
