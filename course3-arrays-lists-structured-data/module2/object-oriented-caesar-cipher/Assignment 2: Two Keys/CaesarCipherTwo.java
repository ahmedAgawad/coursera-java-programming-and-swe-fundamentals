import edu.duke.*;
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
private String alpha;
private String shiftedAlpha1;
private String shiftedAlpha2;
private int mainKey1;
private int mainKey2;
public CaesarCipherTwo(int key1, int key2) {
    alpha = "abcdefghijklmnopqrstuvwxyz";
    shiftedAlpha1 = alpha.substring(key1) + alpha.substring(0,key1);
    shiftedAlpha2 = alpha.substring(key2) + alpha.substring(0,key2);
    mainKey1 = key1;
    mainKey2 = key2;
}

public String encrypt(String input) {
     StringBuilder encrypted = new StringBuilder(input);
     
      for(int i = 0; i < encrypted.length(); i++) {
        char currChar = encrypted.charAt(i);
        int idx = alpha.indexOf(Character.toLowerCase(currChar));
        
        
        if(idx != -1) {
            char newChar = ' ';
            if(i % 2 == 0) {
                newChar = shiftedAlpha1.charAt(idx);
            } else {
                newChar = shiftedAlpha2.charAt(idx);
            }
            
            
            if(Character.isUpperCase(currChar)) {
                encrypted.setCharAt(i, Character.toUpperCase(newChar));
            } else {
                encrypted.setCharAt(i, newChar);
            }
            
        } 
        
    }
    
    return encrypted.toString();
}

public String decrypt(String input) {
    CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
    return cc.encrypt(input);
}
}
