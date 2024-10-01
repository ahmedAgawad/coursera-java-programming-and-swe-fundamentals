
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location from;
    private double distMax;
    
    public DistanceFilter(Location loc, double max) {
        from = loc;
        distMax = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(from) < distMax);
    }
}
