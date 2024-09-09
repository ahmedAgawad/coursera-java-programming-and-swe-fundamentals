import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numOfPoints = 0;
        for(Point ptr : s.getPoints()) {
            numOfPoints = numOfPoints + 1;
        }
        return numOfPoints;
    }

    public double getAverageLength(Shape s) {
        int numberOfSides = getNumPoints(s);
        double shapePerim = getPerimeter(s);
        double averageLen = shapePerim / (double) numberOfSides;
        return averageLen;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            
            double currDist = prevPt.distance(currPt);
           
            if(currDist > largestSide) {
                largestSide = currDist;
            }
           
            // Update prevPt to be currPt
            prevPt = currPt;
        }        
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestPoint = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point p : s.getPoints()) {
            if(p.getX() > largestPoint) {
              largestPoint = p.getX();
            }
        }
        return largestPoint;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestShapePerim = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape sh = new Shape(fr);
            double currentShapePerim = getPerimeter(sh);
            if(currentShapePerim > largestShapePerim) {
                largestShapePerim = currentShapePerim;
            }
        }
        return largestShapePerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null; 
        DirectoryResource dr = new DirectoryResource();
        double largestShapePerim = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape sh = new Shape(fr);
            double currentShapePerim = getPerimeter(sh);
            if(currentShapePerim > largestShapePerim) {
                largestShapePerim = currentShapePerim;
                temp = f;
            }
        }
        
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        getLargestPerimeterMultipleFiles();
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("largest perim from selected files is " + largest);
    }

    public void testFileWithLargestPerimeter() {
        String largest = getFileWithLargestPerimeter();
        System.out.println("largest file name is " + largest);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        FileResource fr = new FileResource();
        Shape sh = new Shape(fr);
     pr.testFileWithLargestPerimeter();
    }
}
