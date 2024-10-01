
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double min;
    private double max;
    private String filterName;
    
    public DepthFilter(double minDepth, double maxDepth, String name) {
            min = minDepth;
            max = maxDepth;
            filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() >= min && qe.getDepth() <= max); 
     }
     
    public String getName() {
        return filterName;
    }
}
