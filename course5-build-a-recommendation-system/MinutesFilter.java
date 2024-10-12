
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MinutesFilter implements Filter {
    private int filterMinMinutes;
    private int filterMaxMinutes;
    public MinutesFilter(int minMinutes, int maxMinutes) {
        filterMinMinutes = minMinutes;
        filterMaxMinutes = maxMinutes;
    }
    
    @Override 
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= filterMinMinutes && MovieDatabase.getMinutes(id) <= filterMaxMinutes;
    }
}
