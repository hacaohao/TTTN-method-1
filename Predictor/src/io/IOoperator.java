package io;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class IOoperator {
    private PrintWriter writer;
    
    public void setPATH(String path) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        writer = new PrintWriter(new FileWriter(path, true));
    }
    
    public  void WriteFile(String content){
        writer.print(content );
    }
    public void close(){
        writer.close();
    }
    
}
