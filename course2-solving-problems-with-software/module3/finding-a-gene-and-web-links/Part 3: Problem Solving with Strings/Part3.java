
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
public boolean twoOccurrences(String stringa, String stringb) {
    int firstIndex = stringb.indexOf(stringa);
    int secondIndex = stringb.indexOf(stringa, firstIndex + stringa.length());
    if(secondIndex != -1) {
        return true;
    }
    return false;
} 


public String lastPart(String stringa, String stringb) {
    int startIndex = stringb.indexOf(stringa);
    if(startIndex == -1) {
     return stringb;
    }

    String lastPartStr = stringb.substring(startIndex + stringa.length());
    return lastPartStr;
}

public void testTwoOccurrences() {
 System.out.println(twoOccurrences("by", "A story by Abby Long"));
 
 System.out.println(twoOccurrences("a", "banana"));
 
 System.out.println(twoOccurrences("atg", "ctgtatgta"));

}

public void testLastPart() {
  System.out.println(lastPart("an", "banana"));
 
  System.out.println(lastPart("zoo", "forest"));
}
}
