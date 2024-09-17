import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    public int[] countLetters(String message) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alpha.indexOf(ch);
            if(dex != -1) {
                counts[dex]++;
            }
        }
    
        return counts;
    }
    
    public int maxIndex(int [] vals) {
        int maxDex = 0;
        for(int i = 1; i < vals.length; i++) {
            if(vals[i] > vals[maxDex]) {
                maxDex = i;
            }
        }
    
        return maxDex;
    }
    
     public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
		
        String encrypted = cc.encrypt(message);
        System.out.println("Encryption");
        System.out.println("==========");
        System.out.println(message + " -> " + encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decryption");
        System.out.println("==========");
        System.out.println(encrypted + " -> " + decrypted);

        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Break Caesar Cipher");
        System.out.println("===================");
        System.out.println(encrypted + " -> " + decrypted);
    }
    
    
    public String breakCaesarCipher(String input) {
         int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        CaesarCipher cc = new CaesarCipher(dkey);
		
        return cc.decrypt(input);
    }
}
