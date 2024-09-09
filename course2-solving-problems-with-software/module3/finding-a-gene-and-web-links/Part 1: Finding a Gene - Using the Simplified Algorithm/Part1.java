
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
public String findSimpleGene(String dna) {
    String gene = "";
    
    int startIndex = dna.indexOf("ATG");
    if(startIndex == -1) {
        return "";
    }   
    int endIndex = dna.indexOf("TAA", startIndex + 3);
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
    System.out.println(findSimpleGene(dna));
    
    dna = "ATGABCDETAA";
    System.out.println(findSimpleGene(dna));
    
    dna = "jkdjfkfjdTAA";
    System.out.println(findSimpleGene(dna));
    
    dna = "ATGjkjkjfd";
     System.out.println(findSimpleGene(dna));
}
}