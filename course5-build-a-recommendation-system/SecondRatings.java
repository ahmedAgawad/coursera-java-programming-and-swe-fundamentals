
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmovies_short.csv", "data/ratings_short.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    
    public int getMovieSize() {
        return myMovies.size();
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
        for(Movie mv : myMovies) {
            String currMovieId = mv.getID();
            double currMovieAvgRating = getAverageByID(currMovieId, minimalRatings);
            if(currMovieAvgRating > 0.0) {
                Rating newAvgRating = new Rating(currMovieId, currMovieAvgRating);
                ratings.add(newAvgRating);
            }
        }
        return ratings;
    }
    
    
    public String getTitle(String id) {
        String movieTitle = "N/A";
        for(Movie mv : myMovies) {
            if(mv.getID().equals(id)) {
                movieTitle = mv.getTitle();
            }
        }
        return movieTitle;
    }
    
    public String getID(String title) {
        String id = "NO SUCH TITLE";
        for(Movie mv : myMovies) {
            if(mv.getTitle().equals(title)) {
                id = mv.getID();
            }
        }
        
        return id;
    }
}