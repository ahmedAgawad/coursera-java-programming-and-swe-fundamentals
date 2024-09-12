import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of csvCountries here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class csvCountries {

    public String countryInfo(CSVParser parser , String country) {
        String info = "NOT FOUND";
        
        for(CSVRecord record : parser) {
            if(record.get("Country").contains(country)) {
              info = record.get("Country") + " : " + record.get("Exports") + " : " + record.get("Value (dollars)");
            }
        }
        return info;
    }
    
    
    public void listExportersTwoProducts(CSVParser parser,String exportItem1, String exportItem2) {
        for(CSVRecord record : parser) {
          if(record.get("Exports").contains(exportItem1) == true && record.get("Exports").contains(exportItem2) == true) {
              System.out.println(record.get("Country"));  
          }
        }
    }
    
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
      int counter = 0;
        
      for(CSVRecord record : parser) {
            if(record.get("Exports").contains(exportItem)) {
                counter++;
            }
      }
     
      return counter;
    }
    
    
    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord record : parser) {
          if(record.get("Value (dollars)").length() > amount.length()) {
            System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
          }
        }
    }
    
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999");
    }
}
