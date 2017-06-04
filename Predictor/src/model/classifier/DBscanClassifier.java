package model.classifier;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.clusterers.MakeDensityBasedClusterer;
import weka.core.Instances;

public class DBscanClassifier {
    private Instances dataSample;
    private MakeDensityBasedClusterer clusterer;
    
    public DBscanClassifier(Instances instances){
        try {
            this.dataSample = instances;
            
            clusterer = new MakeDensityBasedClusterer();
            clusterer.buildClusterer(instances);
            
        } catch (Exception ex) {
            Logger.getLogger(DBscanClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Instances  classify(){
        Instances predicteddata = new Instances(this.dataSample);
        predicteddata.setClassIndex(predicteddata.numAttributes() - 1);
        
        for (int i = 0; i < this.dataSample.numInstances(); i++) {
            try {
                double clsLabel = this.clusterer.clusterInstance(this.dataSample.get(i));
                predicteddata.instance(i).setClassValue(clsLabel);
            } catch (Exception ex) {
                Logger.getLogger(NeuralNetworkClassifier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return predicteddata;
    }
}
