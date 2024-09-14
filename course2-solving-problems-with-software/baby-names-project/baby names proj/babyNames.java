import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of babyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class babyNames {
public void totalBirth(FileResource fr) {
    int totalBirths = 0;
    int totalBoys = 0;
    int totalGirls = 0;
    for(CSVRecord rec : fr.getCSVParser(false)) {
        int numBorn = Integer.parseInt(rec.get(2));
        totalBirths += numBorn;
        if(rec.get(1).equals("M")) {
            totalBoys += numBorn;
        } else {
            totalGirls += numBorn;
        }
    }
    System.out.println("Total births = " + totalBirths);
    System.out.println("Total no of boys born " + totalBoys);
    System.out.println("Total no of girls born " + totalGirls);
} 

public void testTotalBirth() {
    FileResource fr = new FileResource();
    totalBirth(fr);
}


public int getRank(int year, String name, String gender) {
    int countGirls = 0;
    int countBoys = 0;
    int rank = -1;
    DirectoryResource dr = new DirectoryResource();
    
    for(File f : dr.selectedFiles()) {
   
        if(f.getName().contains(String.valueOf(year))) {
            FileResource fr = new FileResource(f);
        
            for(CSVRecord rec : fr.getCSVParser(false)) {
            
                if(rec.get(1).equals("M")) {
                    countBoys++;
                } else {
                    countGirls++;
                }
        
                if(rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                         if(gender == "M") {
                         rank = countBoys;
                         break;
                    } else {
                         rank = countGirls;
                         break;
                    }
                }
            }
        
        }
        
        
        
    }
    
    

    return rank;
}


public void testGetRank() {
    System.out.println(getRank(2012, "Isabella", "F"));
}

public String getName(int year, int rank, String gender) {
    String foundName = "NO NAME";
    DirectoryResource dr = new DirectoryResource();
    for(File f : dr.selectedFiles()) {
        if(f.getName().contains(String.valueOf(year))) {
            FileResource fr = new FileResource(f);
            
            int boysCount = 0;
            int girlsCount = 0;
            
            for(CSVRecord rec : fr.getCSVParser(false)) {
                if(rec.get(1).equals("M")) {
                    boysCount++;
                } else {
                    girlsCount++;
                }
                
                if((boysCount == rank || girlsCount == rank) && rec.get(1).equals(gender)) {
                    foundName = rec.get(0);
                }
                
            }
        
        }
    
    }
    return foundName;
}


public void testGetName() {
    System.out.println(getName(2013, 5, "F"));
}

public void whatIsNameInYear(String name, int year, int newYear, String gender) {
    int oldNameRank = getRank(year, name, gender);
    
    String newYearName = getName(newYear, oldNameRank, gender);
    
    System.out.println(name + " born in " + year + " would be " + newYearName + " if born in " + newYear);
    System.out.println(oldNameRank);
}


public void testWhatIsNameInYear() {
whatIsNameInYear("Isabella", 2012, 2014, "F");
}


public int singleYearNameRank(FileResource fr, String name, String gender) {
    int boysCount = 0;
    int girlsCount = 0;
    for(CSVRecord rec : fr.getCSVParser(false)) {
        
        if(rec.get(1).equals("M")) {
            boysCount++;
        } else {
            girlsCount++;
        }
        
        if(rec.get(0).equals(name) && rec.get(1).equals(gender)) {
            if(rec.get(1).equals("M")) {
                return boysCount;
            } else {
                return girlsCount;
            }
        }    
    }
    return -1;

}


public int yearOfHighestRank(String name, String gender) {
  int highestRank = 99999;
  File highestRankFile = null;
  DirectoryResource dr = new DirectoryResource();
  
  for(File f: dr.selectedFiles()) {
    FileResource fr = new FileResource(f);
    int currentYearRank = singleYearNameRank(fr, name, gender);
    if(currentYearRank < highestRank) {
        highestRank = currentYearRank;
        highestRankFile = f;
    } 
  }

  String yearAsString = (highestRankFile.getName()).substring(3,7);
  return Integer.parseInt(yearAsString);
} 


public void testYearOfHigestRank() {
    System.out.println(yearOfHighestRank("Mason", "M"));
}



}
