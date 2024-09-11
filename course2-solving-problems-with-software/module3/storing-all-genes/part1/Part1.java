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
    
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        
        if(startIndex == -1) {
          return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG"); 
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = 0;
        
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex) ) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
          minIndex = tagIndex;
        }
        
        if(minIndex == -1) {
          return "";
        }
        
        return dna.substring(startIndex, minIndex+3);
    }
    
    
    public void printAllGenes(String dna) {
         int startIndex = 0;
         while(true) {
              String currentGene = findGene(dna, startIndex);  
              if(currentGene.isEmpty()) {
                  break;
              }
              System.out.println(currentGene);
              startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
         }
         
    }
    
    public StorageResource getAllGenes(String dna) {
         StorageResource sr = new StorageResource();
         int startIndex = 0;
         while(true) {
              String currentGene = findGene(dna, startIndex);  
              if(currentGene.isEmpty()) {
                  break;
              }
              sr.add(currentGene);
              startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
         }
         
         return sr;
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
      System.out.println(findGene(dna, 0));
    
      dna = "ABATGDEFGJKJ";
      System.out.println(findGene(dna, 0));
      
      dna = "ATGABCDTAAEFTGA";
      System.out.println(findGene(dna,0));
    }
    
    public void testPrintAllGenes() {
       printAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    }
    
    public void testGetAllGenes() {
      StorageResource sr = getAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
      for(String s : sr.data()) {
        System.out.println(s);
      }
    }
}
