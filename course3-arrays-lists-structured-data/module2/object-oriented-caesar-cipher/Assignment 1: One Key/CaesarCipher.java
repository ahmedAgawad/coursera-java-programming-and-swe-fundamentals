import edu.duke.*;
import java.io.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class CaesarCipher { 
// Class Fields 
private String alpha = "abcdefghijklmnopqrstuvwxyz";
private String shiftedAlpha;
private int mainKey;



// Class Methods Implementation

public CaesarCipher(int key) {
    shiftedAlpha = alpha.substring(key) + alpha.substring(0,key);
    mainKey = key;
}

public String encrypt(String input) {
    StringBuilder encrypted = new StringBuilder(input);
    for(int i = 0; i < encrypted.length(); i++) {
        char currChar = encrypted.charAt(i);
        int idx = alpha.indexOf(Character.toLowerCase(currChar));
        if(idx != -1) {
            char newChar = shiftedAlpha.charAt(idx);
            
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
    CaesarCipher cc = new CaesarCipher(26-mainKey);
    String decrypted = cc.encrypt(input);
    return decrypted;
}


public void printFields() {
    System.out.println(alpha);
    System.out.println(shiftedAlpha);
}

}
