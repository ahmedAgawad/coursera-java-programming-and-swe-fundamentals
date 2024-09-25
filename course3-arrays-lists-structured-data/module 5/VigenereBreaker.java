import java.util.*;
import edu.duke.*;
import java.io.*;
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
        int keyLength = 0;
        String correctDecrypted = "";
        char mostCommonInDic = mostCommonCharIn(dictionary);
        for(int i = 1; i <= 100; i++) {
            int [] keys = tryKeyLength(message, i, mostCommonInDic);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(message);
            
            int correctWords = countWords(decrypted, dictionary);
        
            if(correctWords > max) {
                max = correctWords;
                correctDecrypted = decrypted;
                keyLength = i;
            }
        }

        

        return correctDecrypted;
    }
    
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int[26];
    
        for(String word : dictionary) {
            for(int i = 0; i < word.length(); i++) {
                int currentCharIdx = chars.indexOf(word.charAt(i));
                if(currentCharIdx != -1) {
                     counts[currentCharIdx]++; 
                }
            }            
        }
     
        
        int maxIdx = 0;
        
        for(int i = 1; i < counts.length; i++) {
            if(counts[i] > counts[maxIdx]) {
                maxIdx = i;
            }
        }
        
        return chars.charAt(maxIdx);
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> dics) {
        String langForDecryption = "";
        int maxNoCorrect = 0;
        String decrypted = "";
        for(String lang : dics.keySet()) {
            HashSet<String> currentDic = dics.get(lang);
            String currentDicDecrypted = breakForLanguage(encrypted, currentDic);
            int currentDecryptedCount = countWords(currentDicDecrypted, currentDic);
            if(currentDecryptedCount > maxNoCorrect) {
                decrypted = currentDicDecrypted;
                maxNoCorrect = currentDecryptedCount;
                langForDecryption = lang;
            }
        }
        
        System.out.println(decrypted);
        System.out.println(langForDecryption);
        return decrypted;
    }
    
  
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashMap<String, HashSet<String>> langToDics = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource currentDicFile = new FileResource(f);
            HashSet<String> currentLangDic = readDictionary(currentDicFile);
            langToDics.put(f.getName() , currentLangDic);
        }
        breakForAllLangs(message, langToDics);
        
    }
    
}