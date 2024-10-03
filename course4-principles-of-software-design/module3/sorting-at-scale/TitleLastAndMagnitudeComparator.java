
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        //int indLastWord = qe.getInfo().lastIndexOf(" ") + 1;
         //   String lastWord = qe.getInfo().substring(indLastWord);
        int q1IndLast = q1.getInfo().lastIndexOf(" ") + 1;
        int q2IndLast = q2.getInfo().lastIndexOf(" ") + 1;
        String q1lastWord = q1.getInfo().substring(q1IndLast);
        String q2lastWord = q2.getInfo().substring(q2IndLast);
        
        if(q1lastWord.compareTo(q2lastWord) < 0) {
            return -1;
        } 
        if(q1lastWord.compareTo(q2lastWord) > 0) {
            return 1;
        }
        if(q1lastWord.compareTo(q2lastWord) == 0) {
            if(q1.getMagnitude() < q2.getMagnitude()) {
                return -1;
            } else if(q1.getMagnitude() > q2.getMagnitude()) {
                return 1;
            }
        }
        
        return 0;
    } 
}
