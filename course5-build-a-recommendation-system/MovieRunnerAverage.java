import java.util.*;

/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        ArrayList<Rating> ratings = sr.getAverageRatings(3);
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + sr.getTitle(rt.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        ArrayList<Rating> ratings = sr.getAverageRatings(1);
        String movieName = "The Godfather";
        String movieId = sr.getID(movieName);
        double movieRating = 0.0;
        for(Rating rt : ratings) {
            if(rt.getItem().equals(movieId)) {
                movieRating = rt.getValue();
            }
        }
        System.out.println("The rating of " + movieName + " " + movieRating);
    }
}
