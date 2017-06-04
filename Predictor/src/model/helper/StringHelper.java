package model.helper;

import java.io.File;

public class StringHelper { 
    
    public static String getExtension(String fileName){
        String[] fileNameComponents = fileName.split("\\.");
        int extensionIndex = fileNameComponents.length - 1;
        return fileNameComponents[extensionIndex];
    }
    
    public static String getAbsolutePath(String directoryPath, String fileName){
        return directoryPath + "/" + fileName;
    }
    
    public static String getDirectoryPath(File directory){
        return directory.getAbsolutePath().replace("\\", "/");
    }
}
