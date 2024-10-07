
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i = 0; i < length(); i++) {
            ret += (wordAt(i) + " ");
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(length() != other.length()) {
            return false;
        }
        
        for(int i = 0; i < length(); i++) {
            if(!(wordAt(i).equals(other.wordAt(i)))) {
                return false;
            }
        }
        
        return true;
    }

    public WordGram shiftAdd(String word) {    
        WordGram out = new WordGram(myWords, 0, myWords.length);
        
        // Shift the words in the array to the left by one position
        for(int i = 0; i < out.myWords.length - 1; i++) {
            out.myWords[i] = out.myWords[i + 1];
        }
    
        // Add the new word at the last position of the array
        out.myWords[out.myWords.length - 1] = word;
        out.toString();
        return out;
    
    }

}