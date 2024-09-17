
/**
 * Write a description of CaeserBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaeserBreaker {
// methods implementation
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


public String decrypt(String encrypted) {
    CaesarCipher cc = new CaesarCipher();
    int [] freqs = countLetters(encrypted);
    int maxDex = maxIndex(freqs);
    int dkey = maxDex - 4;
    if(maxDex < 4) {
        dkey = 26 - (4 - maxDex);
    }
    
    return cc.encrypt(encrypted, 26 - dkey);
}


public String halfOfString(String message, int start) {
    StringBuilder sb = new StringBuilder();
    for(int i = start; i < message.length(); i+=2) {
        sb.append(message.charAt(i));
    }
    return sb.toString();
}

public int getKey(String s) {
    int [] count = countLetters(s);
    int maxInd = maxIndex(count);
    
    int dkey = maxInd - 4;
    if(maxInd < 4) {
        dkey = 26 - (4 - maxInd);
    }
    return dkey;
}


public String decryptTwoKeys(String encrypted) {
    String halfOfString0 = halfOfString(encrypted, 0);
    String halfOfString1 = halfOfString(encrypted, 1);
    int keyOfHalf0 = getKey(halfOfString0);
    int keyOfHalf1 = getKey(halfOfString1);
    System.out.println(keyOfHalf0 + "  " + keyOfHalf1);
    CaesarCipher cc = new CaesarCipher();
    String decryptedStr = cc.encryptTwoKeys(encrypted, 26 - keyOfHalf0, 26 - keyOfHalf1);
    return decryptedStr;
    
    
} 

// methods testing

public void testCountLetters() {
    int [] counts = countLetters("aaabbbcccdddeee");
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    for(int i = 0; i < counts.length; i++) {
          System.out.println(alpha.charAt(i) + " reptead " + counts[i] + " times" );
    }
}


public void testMaxIndex() {
    int [] counts = countLetters("aaaaaabbggggggggcde");
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    int mostFreq = maxIndex(counts);
    System.out.println(alpha.charAt(mostFreq) + " repeated " + counts[mostFreq] + " times (most freq)");
}


public void testDecrypt() {
   String message = "COBB ZXHB FK QEB ZLKCBOBKZB OLLJ!";
   System.out.println("Decrypted message = " + decrypt(message));
    
}


public void testHalfOfString() {
    System.out.println(halfOfString("Qbkm Zgis", 0));
    System.out.println(halfOfString("Qbkm Zgis", 1));
}

public void testDecryptTwoKeys() {
     String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
     System.out.println("Decrypted message = " + decryptTwoKeys(message)); 
        
     message = "Io iwjv jz dv bcm kjvammmikz mwju edbc twpz pvb wi awm v ncmxmqnm xvzog. TMZMZMZMZMZMZMGT TJCY!";
     System.out.println("Decrypted message = " + decryptTwoKeys(message));
        
     message = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
     System.out.println("Decrypted message = " + decryptTwoKeys(message));
}

}
