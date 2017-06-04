package model.util.mask;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class SimpleMaskGenerator extends BaseMaskGenerator{
    @Override
    protected Mat createMask(){
        Mat mask = super.imgObj.loadMat();
        
        Imgproc.medianBlur(mask, mask, 19);
        Imgproc.threshold(mask, mask, 145, 10, Imgproc.THRESH_TOZERO_INV);
        Imgproc.medianBlur(mask, mask, 47);
        Imgproc.threshold(mask, mask, 10, 145, Imgproc.THRESH_BINARY);
        
        return mask;
    }
}
