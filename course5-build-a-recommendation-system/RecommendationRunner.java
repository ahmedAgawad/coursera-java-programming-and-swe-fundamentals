
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> list = new ArrayList<String>();
        int noAdded = 0;
        for(String movieId : MovieDatabase.filterBy(new YearAfterFilter(2000))) {
            if(noAdded < 20) {
                list.add(movieId);
                noAdded++;
            } else {
                break;
            }
        }
        
        return list;
    }
    
    public void printRecommendationsFor(String webRaterID) {
            FourthRatings fr = new FourthRatings();
            ArrayList<Rating> movies = fr.getSimilarRatings(webRaterID, 20, 3);
            
            if(movies.size() == 0) {
                 System.out.println("<h2>Sorry, there are no movie recommend for you based on your rating!</h2>");
            } else {
                int displayed = 1;
                
                System.out.println("<table>");
                System.out.println("<tr>");
                System.out.println("<th>Rank</th>");
                System.out.println("<th>Poster</th>");
                System.out.println("<th>Title & Rating</th>");
                System.out.println("<th>Genre</th>");
                System.out.println("<th>Country</th>");
                System.out.println("</tr>");
                
                for(Rating i : movies) {
                    if(displayed < 21) {
                         System.out.println("<tr><td>" + (displayed) + "</td>" +
                        
                        "<td><img src = \"" + MovieDatabase.getPoster(i.getItem()) + "\" width=\"50\" height=\"70\"></td> " +
                        "<td>" + MovieDatabase.getYear(i.getItem()) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
                        i.getItem() + "\">" + MovieDatabase.getTitle(i.getItem()) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;"
                        + String.format("%.1f", i.getValue()) +
                        "<td>" + MovieDatabase.getGenres(i.getItem()) + "</td>" +
                        "<td>" + MovieDatabase.getCountry(i.getItem()) + "</td>" +
                        "</tr> ");
                         displayed++;
                    } else {
                        break;
                    }
                }   
                System.out.println("</table>");
            }
    }
}
