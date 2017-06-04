package model.util.neuralcluster;

import java.awt.Point;
import java.util.ArrayList;
import model.classifier.DBscanClassifier;
import model.datastore.CoordinateSample;
import model.detecter.NoiseDetecter;
import model.helper.XMLParser;
import weka.core.Instance;
import weka.core.Instances;

public class NCSampleNoiseRemover {
    private final ArrayList<Point> abnormalCoordinates;
    private ArrayList<Point> removedCoordinates;
    private final float clusterUpThreshold;
    private final float clusterBottomThreshold;
    private final NoiseDetecter detecter;
    private static final int NUM_OF_ABNORMAL_THRESHOLD = 5;

    public NCSampleNoiseRemover(ArrayList<Point> abnormalCoordinates) {
        this.abnormalCoordinates = abnormalCoordinates;
        this.removedCoordinates = null;
        this.clusterUpThreshold = XMLParser.getClusterUpThreshold();
        this.clusterBottomThreshold = XMLParser.getClusterBottomThreshold();
        this.detecter = new NoiseDetecter();
    }

    public ArrayList<Point> getAbnormalCoordinates() {
        return abnormalCoordinates;
    }

    public ArrayList<Point> getRemovedCoordinates() {
        return removedCoordinates;
    }

  
    
    public ArrayList<Point> removeNoise(){
        if(this.abnormalCoordinates.size() >= NUM_OF_ABNORMAL_THRESHOLD ){
            CoordinateSample coordinateSample = new CoordinateSample(this.abnormalCoordinates);
            Instances coordinateInstances = coordinateSample.getInstances();

            DBscanClassifier classifier = new DBscanClassifier(coordinateInstances);
            Instances predictedInstances = classifier.classify();
            double numOfGroup = predictedInstances.numDistinctValues(predictedInstances.numAttributes() - 1);

            for(double i = 0; i < numOfGroup; i++){
                ArrayList<Point> groupOfPoint = getPointsByGroup(predictedInstances, i);
                removedCoordinates = new ArrayList<>(groupOfPoint);
                this.detecter.setGroupOfPoints(groupOfPoint);
                double meanDistance = this.detecter.getMeanDistance();

                if(meanDistance >= this.clusterUpThreshold || meanDistance <= this.clusterBottomThreshold){
                    predictedInstances = removeNoiseGroup(predictedInstances, i);
                    this.abnormalCoordinates.removeAll(groupOfPoint);
                }
            }
        }else{
            this.abnormalCoordinates.clear();
        }
     
        return this.abnormalCoordinates;
    }
    
    private ArrayList<Point> getPointsByGroup(Instances predictedInstances, double group){
        ArrayList<Point> groupOfPoint = new ArrayList<>();
        int index = 0;
     
        for(Instance instance : predictedInstances){
            if(instance.classValue() == group){
                groupOfPoint.add(this.abnormalCoordinates.get(index));
            }
            
            index++;
        }
        
        return groupOfPoint;
    }
    
    private Instances removeNoiseGroup(Instances predictedInstances, double group){
        ArrayList<Instance> removedInstances = new ArrayList<>();
        
        for(Instance instance : predictedInstances){
            if(instance.classValue() == group){
                removedInstances.add(instance);
            }
        }
        
        predictedInstances.removeAll(removedInstances);
        
        return predictedInstances;
    }
}
