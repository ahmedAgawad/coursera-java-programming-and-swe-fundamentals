
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatings(1);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        }
    }
    
    
    public void printAverageRatingsByYear() {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1, new YearAfterFilter(2000));
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getYear(rt.getItem()) + " " + MovieDatabase.getTitle(rt.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1, new GenreFilter("Crime"));
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() +  " " + MovieDatabase.getTitle(rt.getItem()));
            System.out.println(MovieDatabase.getGenres(rt.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1, new MinutesFilter(110, 170));
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " Time : " + MovieDatabase.getMinutes(rt.getItem()) +  " " +MovieDatabase.getTitle(rt.getItem()));
            
        }
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1, new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " +MovieDatabase.getTitle(rt.getItem()));
            System.out.println(MovieDatabase.getDirector(rt.getItem()));
            
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1980));
        allFilters.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1, allFilters);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getYear(rt.getItem()) + " " +MovieDatabase.getTitle(rt.getItem()));
            System.out.println(MovieDatabase.getGenres(rt.getItem()));
            
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new MinutesFilter(30,170));
        allFilters.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(1, allFilters);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getMinutes(rt.getItem()) + " " +MovieDatabase.getTitle(rt.getItem()));
            System.out.println(MovieDatabase.getDirector(rt.getItem()));
            
        }
    }
}
