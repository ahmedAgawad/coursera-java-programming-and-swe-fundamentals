import edu.duke.*;
import java.util.*;

/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource();
        String text = fr.asString();
        String encryptedTxt = cc.encrypt(text);
        String decryptedTxt = cc.decrypt(encryptedTxt);
        System.out.println(encryptedTxt);
        System.out.println(decryptedTxt);
    }
    
    public void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        String encryptedTxt = fr.asString();
        System.out.println(cc.decrypt(encryptedTxt));
        
        fr = new FileResource();
        cc = new CaesarCracker('a');
        encryptedTxt = fr.asString();
        System.out.println(cc.decrypt(encryptedTxt));
    }
    
    public void testVigenereCipher() {
        int [] keys = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(keys);
        FileResource fr = new FileResource();
        String txt = fr.asString();
        String encryptedTxt = vc.encrypt(txt);
        System.out.println(encryptedTxt);
        String decryptedTxt = vc.decrypt(encryptedTxt);
        System.out.println(decryptedTxt);
        
    }
    
    public void testVBsliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
    }
    
    public void testVBtryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String txt = fr.asString();
        int [] test = vb.tryKeyLength(txt, 5, 'e');
        for(int i =0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
    
    public void testVB() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
