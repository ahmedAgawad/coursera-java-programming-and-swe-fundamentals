import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
public void webLinks() {
  URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
  for(String line : ur.lines()) {
    String originalStr = line;
    String loweredStr = line.toLowerCase();
    if(loweredStr.indexOf("youtube.com") != -1) {
       int startIndex = originalStr.indexOf("\"");
       int endIndex = originalStr.lastIndexOf("\"");
       String linkOnly = originalStr.substring(startIndex, endIndex + 1);
       System.out.println(linkOnly);
    } 
  }
}


public void testWebLinks() {
  webLinks();
}
}
