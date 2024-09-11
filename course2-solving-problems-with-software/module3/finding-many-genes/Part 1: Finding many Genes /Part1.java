import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currentIndex = dna.indexOf(stopCodon, startIndex+3);
        while(currentIndex != -1) {
          if((currentIndex - startIndex ) % 3 == 0) {
           return currentIndex;  
          } else {
            currentIndex = dna.indexOf(stopCodon, currentIndex + 1);  
          }
        }
        
        return dna.length();
    }
    
    
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        
        if(startIndex == -1) {
          return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG"); 
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int stopIndex = 0;
        
        stopIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(stopIndex == dna.length()) {
          return "";
        }
        
        return dna.substring(startIndex, stopIndex+3);
    }
    
    
    public void printAllGenes(String dna) {
     
    
    }
    
    public void testFindStop() {
      int idx = findStopCodon("ATGABCDEFTAA", 0, "TAA");
      System.out.println(idx);
      
      idx =  findStopCodon("ATGABCDETAA", 0, "TAA");
      System.out.println(idx);
      
      idx =  findStopCodon("ATGABCDETAG", 0, "TAG");
      System.out.println(idx);
    }
    
    
    public void testFindGene() {
      String dna = "ATGABCDEFTAATGA";
      System.out.println(findGene(dna));
    
      dna = "ABATGDEFGJKJ";
      System.out.println(findGene(dna));
      
      dna = "ATGABCDTAAEFTGA";
      System.out.println(findGene(dna));
    }
}
