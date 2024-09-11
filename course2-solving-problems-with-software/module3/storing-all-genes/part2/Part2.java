import edu.duke.*;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
  public double cgRatio(String dna) {
      int count = 0;
      for(char ch : dna.toCharArray()){
          if(ch == 'G' || ch == 'C')  {
            count++;  
          }
      }
      
      return (count / (double)dna.length());
  }
   
  public int countCTG(String dna) {
      int currentIndex;
      currentIndex = dna.indexOf("CTG");
      if(currentIndex == -1) {
        return 0;  
      }
      int count = 1;
      while(true) {
          currentIndex = dna.indexOf("CTG", currentIndex + 1);
          if(currentIndex != -1) {
            count++;
          } else {
            break;  
          }
      }
      
      return count;
  }
  
  public void testCgRatio() {
    System.out.println(cgRatio("ATGCCATAG")); // should print 0.4444
    System.out.println(cgRatio("AACCGGAAAA")); // should print 0.4
    System.out.println(cgRatio("ACACGAAAAA")); // should print 0.3333
  } 
  
  public void testCountCTG() {
    System.out.println(countCTG("CTGCTGCTG")); // should print 3
    System.out.println(countCTG("CTJCTGjkjkjCTGjkjkCTG")); // should print 3
    System.out.println(countCTG("ABCDEFG")); // should print 0 
  }
}
