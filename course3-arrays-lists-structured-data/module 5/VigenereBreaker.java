import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i < klength; i++ ) {
            String currentCipher = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(currentCipher);
        }
        return key;
    }

    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for(String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    
    
    public int countWords(String message, HashSet<String> dictionary) {
        String [] words = message.split("\\W+");
        int counter = 0;
        
        for(int i =0; i < words.length; i++) {
            String currentWord = words[i].toLowerCase();
            
            if(dictionary.contains(currentWord)) {
                counter++;
            }
        }
        
        
        return counter;
    }
    
    
    public String breakForLanguage(String message, HashSet<String> dictionary) {
        int max = -99999;
        String correctDecrypted = "";
        for(int i = 1; i <= 100; i++) {
            int [] keys = tryKeyLength(message, i, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(message);
            String [] words = decrypted.split("\\W+");
            int correctWords = 0;
            for(int j = 0; j < words.length; j++) {
                if(dictionary.contains(words[j].toLowerCase())) {
                    correctWords++;
                }
            }
            
            if(correctWords > max) {
                max = correctWords;
                correctDecrypted = decrypted;
            }
        }
        
        return correctDecrypted;
    }
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        FileResource dicFile = new FileResource("dictionaries/English");
        HashSet<String> dic = readDictionary(dicFile);
        
        String decrypted = breakForLanguage(message, dic);
        System.out.println(decrypted);
    }
    
}
