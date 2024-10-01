
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location from;
    private double distMax;
    private String filterName;
    
    public DistanceFilter(Location loc, double max, String name) {
        from = loc;
        distMax = max;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(from) < distMax);
    }
    
    public String getName() {
        return filterName;
    }
}
