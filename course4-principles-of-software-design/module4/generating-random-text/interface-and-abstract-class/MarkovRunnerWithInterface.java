
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 600;
        int seed = 25;
        
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        runModel(emm, st, size, seed);
        
    }
    
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        MarkovModel mm = new MarkovModel(2);
        runModel(mm, st, size, seed);
        
        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        runModel(emm, st, size, seed);
    }
    
    public void testHashMap() {
        int seed = 42;
        int size = 50;
        String text = "yes-this-is-a-thin-pretty-pink-thistle";
        
        EfficientMarkovModel markov = new EfficientMarkovModel(2);
        markov.setTraining(text);
        markov.setRandom(seed);
        
        String st= markov.getRandomText(size);
        printOut(st);
    }
    

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
