
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    private double dotProduct(Rater me, Rater r) {
        double dotProd = 0.0;
        ArrayList<String> myMovies = me.getItemsRated();
        for(String movieId : myMovies) {
            if(r.hasRating(movieId)) {
                dotProd += ((me.getRating(movieId) - 5.0) * (r.getRating(movieId) - 5.0 )) ;
            }
        }
        
        return dotProd;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarityRating = new ArrayList<Rating>();
        for(Rater rt : RaterDatabase.getRaters()) {
            if(!(rt.getID().equals(id))) {
                double dotProd = dotProduct(RaterDatabase.getRater(id), rt);
                Rating dotProdRating = new Rating(rt.getID(), dotProd);
                if(dotProdRating.getValue() > 0.0) {
                    similarityRating.add(dotProdRating);
                }
            }
        }
        
        Collections.sort(similarityRating, Collections.reverseOrder());
        return similarityRating;
    }
    
    public ArrayList<Rating> getSimilarRatings(String raterId, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> similarRaters = getSimilarities(raterId);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        
        for(String movieId : MovieDatabase.filterBy(new TrueFilter())) {
            int numOfRatersTop = 0;
            double currMovieRating = 0.0;
            for(int i = 0; i < numSimilarRaters; i++) {
                Rating r = similarRaters.get(i); // id of rater to the the dotProd to raterId
                String currRaterId = r.getItem();
                Double currRaterDot = r.getValue();
                if(RaterDatabase.getRater(currRaterId).hasRating(movieId)) {
                    numOfRatersTop++;
                    currMovieRating += (RaterDatabase.getRater(currRaterId).getRating(movieId) * currRaterDot);
                }
            }
            
            if(numOfRatersTop >= minimalRaters) {
                currMovieRating = currMovieRating / numOfRatersTop;
                ret.add(new Rating(movieId, currMovieRating));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
    
    
    public ArrayList<Rating> getSimilarRatingsByFiler(String raterId, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarRaters = getSimilarities(raterId);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        
        for(String movieId : MovieDatabase.filterBy(filterCriteria)) {
            int numOfRatersTop = 0;
            double currMovieRating = 0.0;
            for(int i = 0; i < numSimilarRaters; i++) {
                Rating r = similarRaters.get(i); // id of rater to the the dotProd to raterId
                String currRaterId = r.getItem();
                Double currRaterDot = r.getValue();
                if(RaterDatabase.getRater(currRaterId).hasRating(movieId)) {
                    numOfRatersTop++;
                    currMovieRating += (RaterDatabase.getRater(currRaterId).getRating(movieId) * currRaterDot);
                }
            }
            
            if(numOfRatersTop >= minimalRaters) {
                currMovieRating = currMovieRating / numOfRatersTop;
                ret.add(new Rating(movieId, currMovieRating));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    } 
    
    public double getAverageByID(String id, int minimalRaters) {
        double sumRatings = 0.0;
        double noOfRatings = 0.0;
        
        for(Rater rt : RaterDatabase.getRaters()) {
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
