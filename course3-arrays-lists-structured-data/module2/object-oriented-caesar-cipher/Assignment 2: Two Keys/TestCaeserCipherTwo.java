import edu.duke.*;
/**
 * Write a description of TestCaeserCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaeserCipherTwo {
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


public String halfOfString(String message, int start) {
    StringBuilder sb = new StringBuilder();
    for(int i = start; i < message.length(); i+=2) {
        sb.append(message.charAt(i));
    }
    return sb.toString();
}
 

 private int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        return dkey;
    }
    
    private String breakCaesarCipher(String input) {
        String oddString = halfOfString(input, 0);
        String evenString = halfOfString(input, 1);
        
        int key1 = getKey(oddString);
        int key2 = getKey(evenString);
        
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        
        return cc.decrypt(input);
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(14, 24);
        message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
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

}
