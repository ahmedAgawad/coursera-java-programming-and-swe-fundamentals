
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatings(35);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1990));
        allFilters.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(8, allFilters);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getYear(rt.getItem()) + " " +MovieDatabase.getTitle(rt.getItem()));
            System.out.println(MovieDatabase.getGenres(rt.getItem()));
            
        }
    }
    
    public void printSimilarRatings() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> similarRatings = sr.getSimilarRatings("65", 20, 5);
        for(Rating rt : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rt.getItem()) + " " + rt.getValue());
        }
    }
    
    
    public void printSimilarRatingsByGenre() {
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> similarRatings = sr.getSimilarRatingsByFiler("65", 20, 5, new GenreFilter("Action"));
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
        ArrayList<Rating> similarRatings = sr.getSimilarRatingsByFiler("1034", 10, 3, 
        new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));
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
        allFilters.addFilter(new GenreFilter("Adventure"));
        allFilters.addFilter(new MinutesFilter(100, 200));
        ArrayList<Rating> similarRatings = sr.getSimilarRatingsByFiler("65", 10, 5, 
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
        allFilters.addFilter(new YearAfterFilter(2000));
        allFilters.addFilter(new MinutesFilter(80, 100));
        ArrayList<Rating> similarRatings = sr.getSimilarRatingsByFiler("65", 10, 5, 
        allFilters);
        for(Rating rt : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rt.getItem()) + " " + rt.getValue() + " " + MovieDatabase.getMinutes(rt.getItem()) + " " + MovieDatabase.getYear(rt.getItem()));
            
        }
    } 
    
}
