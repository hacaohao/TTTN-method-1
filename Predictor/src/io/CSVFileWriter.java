/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THEDE
 */
public class CSVFileWriter {
    private static final String FILE_NAME = "coordinate";
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = System.lineSeparator();
    
    public static void writeToFile(ArrayList<Point> coordinateValues, String category, int index){
        String savedDirectoryPath = "D:\\hao\\workspace\\java\\Predictor\\tmp\\";
        FileWriter fileWriter;
        
        try {
            File savedFile = new File(savedDirectoryPath + FILE_NAME + index + ".csv");
            if(!savedFile.isFile()) savedFile.createNewFile();
            
            fileWriter = new FileWriter(savedDirectoryPath + FILE_NAME + index + ".csv", true);
            for(Point pointCoordinate : coordinateValues){
                fileWriter.append(String.valueOf(pointCoordinate.x));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(pointCoordinate.y));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(category); //Abnormal or Normal
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            
            fileWriter.flush();
            fileWriter.close();
            
            //coordinateValues.clear();
        } catch (IOException ex) {
            Logger.getLogger(CSVFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

