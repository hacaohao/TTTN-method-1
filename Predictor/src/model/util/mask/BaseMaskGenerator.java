package model.util.mask;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.util.ImageUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

abstract public class BaseMaskGenerator {
    protected ImageUtils imgObj = new ImageUtils();
    
    public Polygon getMask(ArrayList<String> photos){
        int minMaskArea = Integer.MAX_VALUE;
        Polygon actualMask = new Polygon();
        
        for(String photo : photos){
            Polygon mask = createPolygonByMask(photo);
            Rectangle boundingBoxOfMask = mask.getBounds();
            //Tinh dien tich
            int maskArea = (int) boundingBoxOfMask.getHeight() * (int) boundingBoxOfMask.getWidth();
            
            if(maskArea < minMaskArea){
                minMaskArea = maskArea;
                actualMask = mask;
            }
        }
        
        return actualMask;
    }
    
    private Polygon createPolygonByMask(String photo){
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Polygon polygon = new Polygon();
        
        this.imgObj.setFilePath(photo);
        Mat mask = createMask();
        Mat contoursFrame = mask.clone();
        
        Imgproc.findContours(contoursFrame, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
          
        for(MatOfPoint matOfPoint : contours){
            for(Point point : matOfPoint.toList()){
                polygon.addPoint((int)point.x, (int)point.y);
            }
        }
        
        return polygon;
    }
    
    abstract protected Mat createMask();
}
