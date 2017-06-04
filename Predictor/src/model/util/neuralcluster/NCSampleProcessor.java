package model.util.neuralcluster;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.datastore.PixelSample;
import model.util.ImageUtils;
import model.util.mask.BaseMaskGenerator;
import model.util.mask.SimpleMaskGenerator;
import org.opencv.core.Mat;
import weka.core.Instances;

public class NCSampleProcessor {
    private final ImageUtils imgObj;
    private final BaseMaskGenerator simpleMaskGenerator;
    private Polygon mask;
    
    private ArrayList<String> photos;

    public NCSampleProcessor() {
        this.imgObj = new ImageUtils();
        this.photos = new ArrayList<>();
        this.simpleMaskGenerator = new SimpleMaskGenerator();
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
    
    public Instances generateSample(){
        this.mask = this.simpleMaskGenerator.getMask(this.photos);
        PixelSample sample = new PixelSample(getPixelValues());
        
        return sample.getInstances();
    }
    
    private ArrayList<ArrayList<Integer>> getPixelValues(){
        ArrayList<ArrayList<Integer>> listOfSlideValues = new ArrayList<>();
        
        for(String photo : this.photos){
            this.imgObj.setFilePath(photo);
            ArrayList<Integer> pixelValues = getPixelValuesByPolygon(this.imgObj.loadMat());
            listOfSlideValues.add(pixelValues);
        }
        
        return listOfSlideValues;
    }
    
    private ArrayList<Integer> getPixelValuesByPolygon(Mat grayMat){
        ArrayList<Integer> pixelValues = new ArrayList<>();
        Rectangle boundaryBox = this.mask.getBounds();
        
        double minX = boundaryBox.getMinX();
        double maxX = boundaryBox.getMaxX();
        double minY = boundaryBox.getMinY();
        double maxY = boundaryBox.getMaxY();
        
        for(int x = (int) minX; x <= maxX; x++){
            for(int y = (int) minY; y <= maxY; y++){
                if(this.mask.contains(x, y)){
                    pixelValues.add((int)grayMat.get(y, x)[0]);
                }
            }
        }
        
        return pixelValues;
    }
    
    public ArrayList<Point> getCoordinateByPolygon(){
        ArrayList<Point> coordinates = new ArrayList<>();
        Rectangle boundaryBox = this.mask.getBounds();
        
        double minX = boundaryBox.getMinX();
        double maxX = boundaryBox.getMaxX();
        double minY = boundaryBox.getMinY();
        double maxY = boundaryBox.getMaxY();
        
        for(int x = (int) minX; x <= maxX; x++){
            for(int y = (int) minY; y <= maxY; y++){
                if(this.mask.contains(x, y)){
                    coordinates.add(new Point(x, y));
                }
            }
        }
        
        return coordinates;
    }
    
//    public Instances getNormalInstances(Instances allInstances){
//        Instances normalInstances = new Instances(allInstances);
//        
//        for(Instance instance : normalInstances){
//            String classLabel = instance.stringValue(instance.attribute(instance.numAttributes() - 1));
//            
//            if(classLabel.equalsIgnoreCase(Constant.ABNORMAL_CLASS)){
//                normalInstances.remove(instance);
//            }
//        }
//        
//        return normalInstances;
//    }
//    
//    public Instances getAbnormalInstances(Instances allInstances){
//        Instances abnormalInstances = new Instances(allInstances);
//        
//        for(Instance instance : abnormalInstances){
//            String classLabel = instance.stringValue(instance.attribute(instance.numAttributes() - 1));
//            
//            if(classLabel.equalsIgnoreCase(Constant.NORMAL_CLASS)){
//                abnormalInstances.remove(instance);
//            }
//        }
//        
//        return abnormalInstances;
//    }
    
//    private ArrayList<ArrayList<Integer>> nomailizePixelValues(ArrayList<ArrayList<Integer>> listOfSlideValues){
//        ArrayList<Integer> numberOfPixelsPerSlide = new ArrayList<>();
//        
//        for(ArrayList<Integer> slideValues : listOfSlideValues){
//            numberOfPixelsPerSlide.add(slideValues.size());
//        }
//        
//        int minNumberOfPixelsPerSlide = Collections.min(numberOfPixelsPerSlide);
//        
//        for(ArrayList<Integer> slideValues : listOfSlideValues){
//            for(int i = minNumberOfPixelsPerSlide; i <= slideValues.size() - 1; i++){
//                slideValues.remove(i);
//            }
//        }
//        return listOfSlideValues;
//    }
}
