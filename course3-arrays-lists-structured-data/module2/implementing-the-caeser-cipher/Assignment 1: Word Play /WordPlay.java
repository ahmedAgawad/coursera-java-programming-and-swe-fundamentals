import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
// methods implementation
    
public boolean isVowel(char ch) {
    ch = Character.toLowerCase(ch);
    if(ch == 'o' || ch == 'a' || ch == 'i' || ch == 'e' || ch == 'u') {
        return true;
    }
    return false;
}

public String replaceVowels(String phase, char ch) {
    StringBuilder sb = new StringBuilder(phase);
    for(int i = 0; i < sb.length(); i++) {
        if(isVowel(sb.charAt(i))) {
            sb.setCharAt(i, ch);
        }
    }
    
    return sb.toString();
}


public String emphasize(String phase, char ch) {
    String newStr = "";
    for(int i = 0; i < phase.length(); i++) {
        if(Character.toLowerCase(phase.charAt(i)) == ch) {
            if(i % 2 == 0) {
                newStr += '*';
            } else {
                newStr += '+';
            }
        } else {
            newStr += phase.charAt(i);
        }
    
    }
    
    return newStr;
}


// methods testing

public void testIsVowel() {
    System.out.println(isVowel('F'));
    System.out.println(isVowel('f'));
    System.out.println(isVowel('A'));
} 


public void testReplaceVowels() {
    System.out.println(replaceVowels("Hello World" , '*'));
    System.out.println(replaceVowels("abcdefghijklmnopqrstuvwxyz" , '*'));
}

public void testEmphasize() {
    System.out.println(emphasize("dna ctgaaactga", 'a'));
    System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
}

}
