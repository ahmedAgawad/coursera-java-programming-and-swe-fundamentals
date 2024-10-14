
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatings(35);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getTitle(rt.getItem()));
        }
    }
    
    
    public void printAverageRatingsByYear() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(20, new YearAfterFilter(2000));
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getYear(rt.getItem()) + " " + MovieDatabase.getTitle(rt.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() +  " " + MovieDatabase.getTitle(rt.getItem()));
            System.out.println(MovieDatabase.getGenres(rt.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " Time : " + MovieDatabase.getMinutes(rt.getItem()) +  " " +MovieDatabase.getTitle(rt.getItem()));
            
        }
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " +MovieDatabase.getTitle(rt.getItem()));
            System.out.println(MovieDatabase.getDirector(rt.getItem()));
            
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
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
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        System.out.println("No of raters " + sr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("No of movies in the database " + MovieDatabase.size());
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new MinutesFilter(90,180));
        allFilters.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(3, allFilters);
        System.out.println("found " + ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rt : ratings) {
            System.out.println(rt.getValue() + " " + MovieDatabase.getMinutes(rt.getItem()) + " " +MovieDatabase.getTitle(rt.getItem()));
            System.out.println(MovieDatabase.getDirector(rt.getItem()));
            
        }
    }
}
