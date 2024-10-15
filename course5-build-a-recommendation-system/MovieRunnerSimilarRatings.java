
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {

    public void printSimilarRatings() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> similarRatings = sr.getSimilarRatings("71", 20, 5);
        for(Rating rt : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rt.getItem()) + " " + rt.getValue());
        }
    }
    
    
    public void printSimilarRatingsByGenre() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> similarRatings = sr.getSimilarRatingsByFiler("964", 20, 5, new GenreFilter("Mystery"));
        for(Rating rt : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rt.getItem()) + " " + rt.getValue());
            System.out.println(MovieDatabase.getGenres(rt.getItem()));
        }
    }
    
    public void printSImilarRatingsByDirector() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> similarRatings = sr.getSimilarRatingsByFiler("120", 10, 2, 
        new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for(Rating rt : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rt.getItem()) + " " + rt.getValue());
            System.out.println(MovieDatabase.getDirector(rt.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Drama"));
        allFilters.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> similarRatings = sr.getSimilarRatingsByFiler("168", 10, 3, 
        allFilters);
        for(Rating rt : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rt.getItem()) + " " + rt.getValue() + " " + MovieDatabase.getMinutes(rt.getItem()));
            System.out.println(MovieDatabase.getGenres(rt.getItem()));
        }
    } 
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1975));
        allFilters.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> similarRatings = sr.getSimilarRatingsByFiler("314", 10, 5, 
        allFilters);
        for(Rating rt : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rt.getItem()) + " " + rt.getValue() + " " + MovieDatabase.getMinutes(rt.getItem()) + " " + MovieDatabase.getYear(rt.getItem()));
            
        }
    } 
    
}
