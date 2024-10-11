
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) {
            ArrayList<Movie> movies = new ArrayList<Movie>();
            FileResource fr = new FileResource(filename);
            
            for(CSVRecord record : fr.getCSVParser()) {
                Movie currMovie = new Movie(record.get("id"), record.get("title"), record.get("year"), 
                                      record.get("genre"), record.get("director"), record.get("country"), record.get("poster") 
                                       , Integer.parseInt(record.get("minutes")));
                                       
                movies.add(currMovie);
            }
            
            return movies;
    }
    
    
    public ArrayList<EfficientRater> loadRaters(String fileName) {
        FileResource fr = new FileResource(fileName);
        ArrayList<EfficientRater> raters = new ArrayList<EfficientRater>();
        
        
        for(CSVRecord record : fr.getCSVParser()) {
            String currLineRaterId = record.get("rater_id");
            int indexOfCurrRater = indexOfRater(raters, currLineRaterId);
            
            if(indexOfCurrRater == -1) {
                EfficientRater newRater = new EfficientRater(currLineRaterId);
                newRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")) );
                raters.add(newRater);
            } else {
                EfficientRater oldRater = raters.get(indexOfCurrRater);
                oldRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")) );
                raters.set(indexOfCurrRater, oldRater);
            }
        
        }
        
        
    
        return raters;
    }
    
    public int indexOfRater(ArrayList<EfficientRater> raters,String idToSearchFor) {
        int index = -1;
        for(int i = 0; i < raters.size(); i++) {
            String currId = raters.get(i).getID();
            if(currId.equals(idToSearchFor)) {
                index = i;
            }
        }
        
        return index;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println(movies.size());
    
        int comedyCount = 0;
        int greaterThan150 = 0;
        for(Movie mv : movies) {
            if(mv.getGenres().contains("Comedy")) {
                comedyCount++;
            }
            
            if(mv.getMinutes() > 150) {
                greaterThan150++;
            }
        }
        
        System.out.println("number of movies including comedy in genre " + comedyCount);
        System.out.println("number of movies with time greater than 150 minutes " + greaterThan150);
        
        HashMap<String, ArrayList<String>> directorMovies = new HashMap<String, ArrayList<String>>();
        for(Movie mv : movies) {
            
            String [] currMovieDirectors = mv.getDirector().split(",");
            
            for(int i = 0; i < currMovieDirectors.length; i++) {
                if( directorMovies.containsKey(currMovieDirectors[i].trim()) ) {
                    ArrayList<String> currMovies = directorMovies.get(currMovieDirectors[i].trim());
                    currMovies.add(mv.getTitle());
                    directorMovies.put(currMovieDirectors[i].trim(), currMovies);
                } else {
                    ArrayList<String> currMovies = new ArrayList<String>();
                    currMovies.add(mv.getTitle());
                    directorMovies.put(currMovieDirectors[i].trim(), currMovies);
                }
            }
            
        }
        
        int maxNumber = -1;
        String maxNumDirector = "";
        
        for(String directorName : directorMovies.keySet()) {
            if(directorMovies.get(directorName).size() > maxNumber) {
                maxNumber = directorMovies.get(directorName).size();
                maxNumDirector = directorName;
            }
        }
        
        
        System.out.println("Director with max num of movies is " + maxNumDirector + " directed " + maxNumber + " movies" );
        
        
    }
    
    
    public void testLoadRaters() {
        ArrayList<EfficientRater> raters  = loadRaters("data/ratings.csv");
        
        for(Rater rt : raters) {
            System.out.println("Rater ID : " + rt.getID() + " No of ratings given " + rt.numRatings());
            for(String movieId : rt.getItemsRated() ) {
                System.out.println("Movie Id is " + movieId + " rating is " + rt.getRating(movieId));
            }
        }
        
        System.out.println(raters.size() + " no of raters");
        
        
        String rater_id = "2";
        for(Rater rt : raters) {
            if(rt.getID().equals(rater_id)) {
                System.out.println("user with id " + rater_id +  " has given " + rt.numRatings() + " ratings");
            }
        }
        
        int maxRatingsNo = 0;
        for(Rater rt : raters) {
            if(rt.numRatings() > maxRatingsNo) {
                maxRatingsNo = rt.numRatings();
            }
        }   
        int maxNoRaters = 0;
        for(Rater rt : raters) {
            if(rt.numRatings() == maxRatingsNo) {
                maxNoRaters++;
                System.out.println(rt.getID() + " rater with this Id has max No of ratings");
            }
        }
        System.out.println(maxNoRaters + " no of users has max no of ratings : " + maxRatingsNo );
        
        String movieId = "1798709";
        int movieIdNoOfRaters = 0;
        for(Rater rt : raters) {
            Boolean currentRaterBool = rt.hasRating(movieId);
            if(currentRaterBool) {
                movieIdNoOfRaters++;
            }
        }
        System.out.println("Movie with ID " + movieId + " has " + movieIdNoOfRaters  + " ratings");
        
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for(Rater rt : raters) {
            for(int i = 0; i < rt.getItemsRated().size(); i++) {
                    if(!(uniqueMovies.contains(rt.getItemsRated().get(i))) ) {
                        uniqueMovies.add(rt.getItemsRated().get(i));
                    }
            }
        }
        System.out.println(uniqueMovies.size() + " unique titles reviewed");
        
        
        System.out.println(raters.get(193).getItemsRated().size());
    }
}
