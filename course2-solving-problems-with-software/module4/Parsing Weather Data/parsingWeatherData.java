import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of parsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class parsingWeatherData {
public CSVRecord coldestHourInFile(CSVParser parser) {
    CSVRecord coldestSoFar = null;
    
    for(CSVRecord record : parser) {
        double currentRow = Double.parseDouble(record.get("TemperatureF"));
        if(coldestSoFar == null || ((currentRow < Double.parseDouble(coldestSoFar.get("TemperatureF"))) && (currentRow != -9999))) {
           coldestSoFar = record;
        } 
        
    }

    
    return coldestSoFar;
}


public void testColdestHourInFile() {
  FileResource fr = new FileResource("./2014/weather-2014-01-30.csv");
  CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
  System.out.println(lowest.get("TemperatureF") + " occured at time " + lowest.get("DateUTC"));
  

}


public String fileWithColdestTemperature() {
    DirectoryResource dr = new DirectoryResource();
    File fileWithLowestTemp = null;
    CSVRecord lowestRecord = null;
    for(File f : dr.selectedFiles())  {
        FileResource fr = new FileResource(f);
        
        if(fileWithLowestTemp == null) {
          fileWithLowestTemp = f;
          lowestRecord = coldestHourInFile(fr.getCSVParser());
        } else {
           double currentFileLowest = Double.parseDouble(coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
           double lowestRecordValue = Double.parseDouble(lowestRecord.get("TemperatureF"));
           
           
           if(currentFileLowest < lowestRecordValue) {
                fileWithLowestTemp = f;
                lowestRecord = coldestHourInFile(fr.getCSVParser());
            
           }
        }
    }
    
    return fileWithLowestTemp.getName();
}

public void testFileWithColdestTemperature() {
    System.out.println(fileWithColdestTemperature());
}

public CSVRecord lowestHumidityInFile(CSVParser parser) {
    CSVRecord lowestHumidity = null;
    
    for(CSVRecord record : parser) {
        double currentRow = 0;
        try {
             currentRow = Double.parseDouble(record.get("Humidity"));
        } catch(NumberFormatException ex) {
            currentRow = 9999999;
        
        }
        
        
        
        if(lowestHumidity == null) {
           lowestHumidity = record;
        } else {
            if(currentRow < Double.parseDouble(lowestHumidity.get("Humidity"))) {
              lowestHumidity = record;
            }
        } 
        
    }
    
    return lowestHumidity;
} 



public void testLowestHumidityInFile() {

FileResource fr = new FileResource();
CSVParser parser = fr.getCSVParser();
CSVRecord csv = lowestHumidityInFile(parser);
System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
}


public CSVRecord lowestHumidityInManyFiles(){

    DirectoryResource dr = new DirectoryResource();
    CSVRecord lowestRecord = null;
    for(File f : dr.selectedFiles())  {
        FileResource fr = new FileResource(f);
        
        if(lowestRecord == null) {
          lowestRecord = lowestHumidityInFile(fr.getCSVParser());
        } else {
           double currentFileLowest = Double.parseDouble(lowestHumidityInFile(fr.getCSVParser()).get("Humidity"));
           double lowestRecordValue = Double.parseDouble(lowestRecord.get("Humidity"));
           
           
           if(currentFileLowest < lowestRecordValue) {
                lowestRecord = lowestHumidityInFile(fr.getCSVParser());
           }
        }
    }
    
    return lowestRecord;
}


public void testLowestHumidityInManyFiles() {
  CSVRecord rec = lowestHumidityInManyFiles();
  System.out.println(rec.get("Humidity") + " at " + rec.get("DateUTC"));
}


public double averageTempInFile(CSVParser parser) {
    double sum = 0;
    double count = 0;
    
    for(CSVRecord rec : parser) {
        double tempRec = Double.parseDouble(rec.get("TemperatureF"));
        if(tempRec != -9999) {
            count++;
            sum += tempRec;
        }
    
    
    }
    
    return sum / count;
} 


public void testAverageTemperatureInFile() {
FileResource fr = new FileResource();
CSVParser parser = fr.getCSVParser();
double avg = averageTempInFile(parser);
System.out.println(avg);

}


public Double averageTempWithHighHumidInFile(CSVParser parser, int value) {
    double sum = 0;
    double count = 0;
    
    for(CSVRecord rec : parser) {
        double tempRec = Double.parseDouble(rec.get("TemperatureF"));
        double humidRec = 0;
       
        try {
            humidRec = Double.parseDouble(rec.get("Humidity"));
        } catch(NumberFormatException ex) {
            humidRec = -9999999;
        }
        
        if(tempRec != -9999 && humidRec >= value) {
            count++;
            sum += tempRec;
        }
    
    
    }
    
    if(count == 0) {
        return 0.0;
    }
    
    return sum / count;
}


public void testAverageTempWithHighHumidInFile() {
    FileResource fr = new FileResource();
    CSVParser csv = fr.getCSVParser();
    double avgWithHighHumid = averageTempWithHighHumidInFile(csv, 80);
    System.out.println(avgWithHighHumid);
  
}

}