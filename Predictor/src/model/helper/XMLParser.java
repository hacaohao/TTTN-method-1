package model.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLParser {
    private static final int DEFAUTL_START_INDEX = 9;
    private static final int DEFAUTL_SLIDES = 4;
    private static final int DEFAUTL_POSITIVE_RESULT = 2;
    private static final float DEFAUTL_RATE_THRESHOLD = (float) 0.012;
    private static final float DEFAUTL_CLUSTER_UP_THRESHOLD = (float) 80;
    private static final float DEFAUTL_CLUSTER_BOTTOM_THRESHOLD = (float) 10;
    private static final String SLIDES_TAG = "slides";
    private static final String START_INDEX_TAG = "start-index";
    private static final String RATE_THRESHOLD_TAG = "rate-threshold";
    private static final String CLUSTER_UP_THRESHOLD_TAG = "cluster-up-threshold";
    private static final String CLUSTER_BOTTOM_THRESHOLD_TAG = "cluster-bottom-threshold";
    private static final String POSITIVE_RESULT_THRESHOLD_TAG = "positive-result-threshold";
    private static final String XML_FILE_PATH = "\\resources\\neuronnetwork\\config.xml";
    
    private static Document readXMLDocument(){
        try {
            File temp = Paths.get(".").toAbsolutePath().normalize().toFile();
            File xmlFile = new File(temp + XML_FILE_PATH);
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            
            Document doc = dbBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static int getStartIndex(){
        Document doc = readXMLDocument();
        Node node = doc.getElementsByTagName(START_INDEX_TAG).item(0);
        
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            return Integer.parseInt(element.getTextContent());
        }
        
        return DEFAUTL_START_INDEX;
    }
    
    public static int getSlides(){
        Document doc = readXMLDocument();
        Node node = doc.getElementsByTagName(SLIDES_TAG).item(0);
        
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            return Integer.parseInt(element.getTextContent());
        }
        
        return DEFAUTL_SLIDES;
    }
    
    public static float getRateThreshold(){
        Document doc = readXMLDocument();
        Node node = doc.getElementsByTagName(RATE_THRESHOLD_TAG).item(0);
        
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            return Float.parseFloat(element.getTextContent());
        }
        
        return DEFAUTL_RATE_THRESHOLD;
    }
    
    public static float getClusterUpThreshold(){
        Document doc = readXMLDocument();
        Node node = doc.getElementsByTagName(CLUSTER_UP_THRESHOLD_TAG).item(0);
        
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            return Float.parseFloat(element.getTextContent());
        }
        
        return DEFAUTL_CLUSTER_UP_THRESHOLD;
    }
    
    public static float getClusterBottomThreshold(){
        Document doc = readXMLDocument();
        Node node = doc.getElementsByTagName(CLUSTER_BOTTOM_THRESHOLD_TAG).item(0);
        
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            return Float.parseFloat(element.getTextContent());
        }
        
        return DEFAUTL_CLUSTER_BOTTOM_THRESHOLD;
    }
    
    public static int getPositiveResultThreshold(){
        Document doc = readXMLDocument();
        Node node = doc.getElementsByTagName(POSITIVE_RESULT_THRESHOLD_TAG).item(0);
        
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            return Integer.parseInt(element.getTextContent());
        }
        
        return DEFAUTL_POSITIVE_RESULT;
    }
}
