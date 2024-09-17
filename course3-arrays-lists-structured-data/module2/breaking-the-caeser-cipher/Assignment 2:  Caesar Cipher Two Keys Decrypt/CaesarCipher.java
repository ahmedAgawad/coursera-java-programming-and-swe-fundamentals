import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
// methods implementaion
public String encrypt(String input, int key) {
    StringBuilder encrypted = new StringBuilder(input);
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    String shiftedAlpha = alpha.substring(key) + alpha.substring(0,key);
    
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



public String encryptTwoKeys(String input, int key1, int key2) {
     StringBuilder encrypted = new StringBuilder(input);
     String alpha = "abcdefghijklmnopqrstuvwxyz";
     String shiftedAlpha1 = alpha.substring(key1) + alpha.substring(0, key1);
     String shiftedAlpha2 = alpha.substring(key2) + alpha.substring(0, key2);
     
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

// methods testing
public void testEncrypt() {
    System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 17));
}

public void testEncryptTwoKeys() {

System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
}


}
