
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings_short.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double sumRatings = 0.0;
        double noOfRatings = 0.0;
        
        for(Rater rt : myRaters) {
            if(rt.hasRating(id)) {
                sumRatings += rt.getRating(id);
                noOfRatings++;
            }
        }
        
        if(noOfRatings >= minimalRaters) {
            return sumRatings / noOfRatings;
        }
        
        return 0.0;
    }
    
    
    public ArrayList<Rating> getAverageRatings(int minimalRatings) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieId : movies) {
           
            double currMovieAvgRating = getAverageByID(movieId, minimalRatings);
            if(currMovieAvgRating > 0.0) {
                Rating newAvgRating = new Rating(movieId, currMovieAvgRating);
                ratings.add(newAvgRating);
            }
        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRatings, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> moviesId = MovieDatabase.filterBy(filterCriteria);
        for(String movieId : moviesId) {
            double currMovieAvgRating = getAverageByID(movieId, minimalRatings);
            if(currMovieAvgRating != 0.0) {
                Rating currRating = new Rating(movieId, currMovieAvgRating);
                ratings.add(currRating);
            }
        }
        
        return ratings;
    }
}
