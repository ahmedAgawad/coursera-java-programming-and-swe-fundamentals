
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String type;
    private String phrase;
    
    public PhraseFilter(String typeInput, String phraseInput) {
        type = typeInput;
        phrase = phraseInput;
    }
    
    
    public boolean satisfies(QuakeEntry qe) {
        if((type.equals("start") && qe.getInfo().startsWith(phrase)) || (type.equals("end") && qe.getInfo().endsWith(phrase)) || (type.equals("any") && (qe.getInfo().indexOf(phrase) != -1))) {
            return true;
        
        } else {
          return false;        
        }
        
    }
}
