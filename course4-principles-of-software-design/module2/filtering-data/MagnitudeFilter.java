
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double min;
    private double max;
    
    public MagnitudeFilter(double minMag, double maxMag) {
        min = minMag;
        max = maxMag;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getMagnitude() >= min && qe.getMagnitude() <= max);
    }
    
}
