package model.detecter;

import java.awt.Point;
import java.util.ArrayList;

public class NoiseDetecter {
    private ArrayList<Point> groupOfPoints;

    public NoiseDetecter() {
    }

    public void setGroupOfPoints(ArrayList<Point> groupOfPoints) {
        this.groupOfPoints = groupOfPoints;
    }

    public double getMeanDistance(){
        Point meanPoint = getMeanPoint();
        double mean = 0;
        
        for(Point point : groupOfPoints){
            mean += getEuclidianDistance(point, meanPoint);
        }
        
        return mean / groupOfPoints.size();
    }
    
    private double getEuclidianDistance(Point point1, Point point2){
        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
    }
    
    private Point getMeanPoint(){
        return new Point(getMeanX(), getMeanY());
    }

    private int getMeanX(){
        int sum = 0;
        
        for(Point point : groupOfPoints){
            sum += point.x;
        }
        
        return sum / groupOfPoints.size();
    }
    
    private int getMeanY(){
        int sum = 0;
        
        for(Point point : groupOfPoints){
            sum += point.y;
        }
        
        return sum / groupOfPoints.size();
    }
}
