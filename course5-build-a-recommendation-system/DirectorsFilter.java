
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class DirectorsFilter implements Filter {
    private String [] directorsList; 
    public DirectorsFilter(String directors) {
       directorsList = directors.split(",");
       
    } 
    
    @Override
    public boolean satisfies(String id) {
        String movieDirectors = MovieDatabase.getDirector(id);
        boolean found = false;
        for(int i = 0; i < directorsList.length; i++) {
            if(movieDirectors.contains(directorsList[i])) {
                found = true;
                break;
            }
        }
        return found;
    }
}
