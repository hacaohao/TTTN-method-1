package model.util;

import java.io.File;
import java.util.ArrayList;
import model.helper.StringHelper;

public abstract class Predictor {
    public boolean isSick(File directory){
        System.out.println(directory);
        
        ArrayList<String> photos = getPhotos(directory);
        return isSick(photos);
    }
    
    private ArrayList<String> getPhotos(File directory){
        if(directory != null){
            String directoryPath = StringHelper.getDirectoryPath(directory);
            File[] filesInDirectory = directory.listFiles();
            ArrayList<String> photos = new ArrayList<>();
            
            for(File file : filesInDirectory){
                photos.add(StringHelper.getAbsolutePath(directoryPath, file.getName()));
            }
            
            return photos;
        }
        
        return null;
    }
    
    abstract protected boolean isSick(ArrayList<String> photos);
}
