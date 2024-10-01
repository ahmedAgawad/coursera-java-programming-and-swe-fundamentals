
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String type;
    private String phrase;
    private String filterName;
    
    public PhraseFilter(String typeInput, String phraseInput, String name) {
        type = typeInput;
        phrase = phraseInput;
        filterName = name;
    }
    
    
    
    
    public boolean satisfies(QuakeEntry qe) {
        if((type.equals("start") && qe.getInfo().startsWith(phrase)) || (type.equals("end") && qe.getInfo().endsWith(phrase)) || (type.equals("any") && (qe.getInfo().indexOf(phrase) != -1))) {
            return true;
        
        } else {
          return false;        
        }
        
    }
    
    
    public String getName() {
        return filterName;
    }
}
