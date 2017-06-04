package component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePane extends JPanel {
    private BufferedImage backgroundImage;
    private Polygon polygon;
    private boolean isGetSample;

    public ImagePane(){
        this.isGetSample = false;
        this.polygon = new Polygon();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isGetSample){
                    Point point = e.getPoint();
                    polygon.addPoint(point.x, point.y);
                    repaint();
                }
            }
        });
    }
    
    public void clearPolygon(){
        this.polygon.reset();
        repaint();
    }
    
    public Polygon getPolygon(){
        return this.polygon;
    }

    public boolean isGetSample() {
        return this.isGetSample;
    }

    public void setIsGetSample(boolean isGetSample) {
        this.isGetSample = isGetSample;
    }

    public BufferedImage getBackgroundImage() {
        return this.backgroundImage;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return this.backgroundImage == null ? new Dimension(512, 512) : new Dimension(this.backgroundImage.getWidth(), this.backgroundImage.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int x = (getWidth() - this.backgroundImage.getWidth()) / 2;
            int y = (getHeight()- this.backgroundImage.getHeight()) / 2;
            g2d.drawImage(this.backgroundImage, x, y, this);
            
            g2d.dispose();
        }
        
        if(this.polygon.npoints != 0){
            g.setColor(Color.red);
            g.drawPolygon(this.polygon);
        }
    }
}
