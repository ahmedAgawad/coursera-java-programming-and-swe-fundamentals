
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
public String findSimpleGene(String dna, String startCodon, String stopCodon) {
    String gene = "";
    
    int startIndex = dna.indexOf(startCodon);
    if(startIndex == -1) {
        return "";
    }   
    int endIndex = dna.indexOf(stopCodon, startIndex + 3);
    if(endIndex == -1) {
      return "";
    }
        
    gene = dna.substring(startIndex, endIndex + 3);
    
    if(gene.length() % 3 == 0) {
        return gene;
    }
    
    return "";
  }
  
public void testSimpleGene() {
    String dna = "ATGABCDEFTAA";
    System.out.println(findSimpleGene(dna, "ABC", "TAA"));
    
}
}
