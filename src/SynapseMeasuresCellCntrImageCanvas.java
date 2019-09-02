/*
 * CellCntrImageCanvas.java
 *
 * Created on November 22, 2005, 5:58 PM
 *
 */
/*
 *
 * @author Kurt De Vos � 2005
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation (http://www.gnu.org/licenses/gpl.txt )
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. 
 *
 */

import ij.IJ;
import ij.ImagePlus;
import ij.gui.ImageCanvas;
import ij.gui.Overlay;
import ij.gui.Roi;
import ij.gui.Toolbar;
import ij.process.ImageProcessor;
import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import java.util.Vector;
import java.io.*;

/**
 *
 * @author Kurt De Vos
 */
public class SynapseMeasuresCellCntrImageCanvas extends ImageCanvas{
    private Vector typeVector;
    private SynapseMeasuresCellCntrMarkerVector currentMarkerVector;
    private SynapseMeasures cc;
    private ImagePlus img;
    private boolean delmode = false;
    private boolean showNumbers = false;
    private boolean showAll = false;
    private Font font = new Font("SansSerif", Font.PLAIN, 10);
    private int radius=10;
    
    /** Creates a new instance of CellCntrImageCanvas */
    public SynapseMeasuresCellCntrImageCanvas(ImagePlus img, Vector typeVector, SynapseMeasures cc, Overlay overlay) {
        super(img);
        this.img=img;
        this.typeVector = typeVector;
        this.cc = cc;
        if (overlay!=null)
            this.setOverlay(overlay);
    }
    
    public void largerRadius() {
    	radius++;
    }
    
    public void smallerRadius() {
    	if (radius>2) radius--;
    }
    
    public void mousePressed(MouseEvent e) {
        if (IJ.spaceBarDown() || Toolbar.getToolId()==Toolbar.MAGNIFIER || Toolbar.getToolId()==Toolbar.HAND) {
            super.mousePressed(e);
            return;
        }
        
        if (currentMarkerVector==null){
            IJ.error("Select a counter type first!");
            return;
        }
        
        int x = super.offScreenX(e.getX());
        int y = super.offScreenY(e.getY());
        if (!delmode){
            SynapseMeasuresCellCntrMarker m = new SynapseMeasuresCellCntrMarker(x, y, img.getCurrentSlice());
            currentMarkerVector.addMarker(m);
        }else{
            SynapseMeasuresCellCntrMarker m = currentMarkerVector.getMarkerFromPosition(new Point(x,y) ,img.getCurrentSlice());
            currentMarkerVector.remove(m);
        }
        repaint();
        cc.populateTxtFields();
    }
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (!IJ.spaceBarDown() | Toolbar.getToolId()!=Toolbar.MAGNIFIER | Toolbar.getToolId()!=Toolbar.HAND)
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }
    
    private Point point;
    private Rectangle srcRect = new Rectangle(0, 0, 0, 0);
    public void paint(Graphics g){
        super.paint(g);
        srcRect = getSrcRect();
        Roi roi = img.getRoi();
        double xM=0;
        double yM=0;
        
        /*
        double magnification = super.getMagnification();
        
        try {
            if (imageUpdated) {
                imageUpdated = false;
                img.updateImage();
            }
            Image image = img.getImage();
            if (image!=null)
                g.drawImage(image, 0, 0, (int)(srcRect.width*magnification),
                        (int)(srcRect.height*magnification),
                        srcRect.x, srcRect.y, srcRect.x+srcRect.width,
                        srcRect.y+srcRect.height, null);
            if (roi != null)
                roi.draw(g);
        } catch(OutOfMemoryError e) {
            IJ.outOfMemory("Paint "+e.getMessage());
        }
        */
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(1f));
        g2.setFont(font);
        
        ListIterator it = typeVector.listIterator();
        while(it.hasNext()){
            SynapseMeasuresCellCntrMarkerVector mv = (SynapseMeasuresCellCntrMarkerVector)it.next();
            int typeID = mv.getType();
            g2.setColor(mv.getColor());
            ListIterator mit = mv.listIterator();
            while(mit.hasNext()){
                SynapseMeasuresCellCntrMarker m = (SynapseMeasuresCellCntrMarker)mit.next();
                boolean sameSlice = m.getZ()==img.getCurrentSlice();
                if (sameSlice || showAll){
                    xM = ((m.getX()-srcRect.x)*magnification);
                    yM = ((m.getY()-srcRect.y)*magnification);
                   	g2.drawOval((int)(xM-radius*magnification),
                   			    (int)(yM-radius*magnification),
                   			    (int)(2*radius*magnification),
                   			    (int)(2*radius*magnification));
                    if (showNumbers)
                        g2.drawString(Integer.toString(typeID), (int)xM+3, (int)yM-3);
                }
            }
        }
    }
    
    public void removeLastMarker(){
        currentMarkerVector.removeLastMarker();
        repaint();
        cc.populateTxtFields();
    }
    public void measure(){
    	try {
    		// Produce raw data
	        IJ.setColumnHeadings("Type\tMean\tStddev");
	        ImageProcessor ip = img.getProcessor();
	        int W=ip.getWidth();
	        int H=ip.getHeight();
	        int radius2=radius*radius;
	    
    		FileOutputStream rawData=new FileOutputStream(
    				"SynapseMeasures.txt",
    				(new File("SynapseMeasures.txt")).exists());
	    	PrintStream pRawData=new PrintStream(rawData);
	    	ListIterator it = typeVector.listIterator();
	        double[] muobs=new double[4];
	        double[] sigma2obs=new double[4];
              double A=0;
	        while(it.hasNext()){
	            SynapseMeasuresCellCntrMarkerVector mv = (SynapseMeasuresCellCntrMarkerVector)it.next();
	            int typeID = mv.getType();
	            String typeLabel="";
	            switch (typeID) {
	            case 1: typeLabel="Bg"; break;
	            case 2: typeLabel="B"; break;
	            case 3: typeLabel="Tsyn"; break;
	            case 4: typeLabel="Tnosyn"; break;
	            }
	            ListIterator mit = mv.listIterator();
	            double muSum=0, sigma2Sum=0;
	            double muN=0;
	            while(mit.hasNext()){
	                SynapseMeasuresCellCntrMarker m = (SynapseMeasuresCellCntrMarker)mit.next();
	                int xM = m.getX();
	                int yM = m.getY();
	                double value=0;
	                A=0;
	                for (int sx=-radius; sx<=radius; sx++)
	                {
	                	int sx2=sx*sx;
	                    for (int sy=-radius; sy<=radius; sy++)
	                    {
	                    	int sy2=sy*sy;
	                    	if (sx2+sy2<radius2 &&
	                    	    xM+sx>=0 && xM+sx<W &&
	                    	    yM+sy>=0 && yM+sy<H)
	                    	{
	                            value += ip.getPixelValue(xM+sx,yM+sy);
	                            A++;
	                    	}
	                    }
	                }
	                if (A>0) value/=A;
	                m.setValue(value);
	                muSum+=value;
	                sigma2Sum+=value*value;
	                muN++;
	    			IJ.write(typeLabel+"\t"+value);
	                pRawData.println(typeLabel+"\t"+value);
	            }
	            muobs[typeID-1]=(muSum/muN);
	            sigma2obs[typeID-1]=(sigma2Sum/muN)-
	            	muobs[typeID-1]*muobs[typeID-1];
	        }
	        pRawData.println(" ");
	        rawData.close();
	        
    		double[] mu=new double[4];
	        mu[0]=muobs[0];
	        mu[1]=2*(muobs[1]-muobs[0]);
	        mu[2]=2*(muobs[2]-muobs[1]);
	        mu[3]=2*(muobs[3]-muobs[0]);

    		double[] sigma2=new double[4];
    		sigma2[0]=sigma2obs[0];
    		sigma2[1]=4*(sigma2obs[0]+sigma2obs[1]);
    		sigma2[2]=4*(sigma2obs[0]+sigma2obs[2]);
    		sigma2[3]=4*(sigma2obs[0]+sigma2obs[3]);
	        
    		double z=mu[2]/mu[3];
    		double t0=1.96;
    		double a=t0*t0*sigma2[3]-mu[3]*mu[3];
    		double b=2*mu[2]*mu[3];
    		double c=t0*t0*sigma2[2]-mu[2]*mu[2];
    		double disc=Math.sqrt(b*b-4*a*c);

    		IJ.write(" ");
            IJ.write("CircleRadius\t"+radius);
            IJ.write("CircleArea\t"+A);
	    	IJ.write("Bg\t"+mu[0]+"\t"+Math.sqrt(sigma2[0]));
	    	IJ.write("B\t"+mu[1]+"\t"+Math.sqrt(sigma2[1]));
	    	IJ.write("Tsyn\t"+mu[2]+"\t"+Math.sqrt(sigma2[2]));
	    	IJ.write("Tnosyn\t"+mu[3]+"\t"+Math.sqrt(sigma2[3]));
	        IJ.write(" ");
	        IJ.write("Ratio\t"+(mu[2]/mu[3]));
	        IJ.write("Confidence\t"+((-b+disc)/(2*a))+"\t"+((-b-disc)/(2*a)));

    		FileOutputStream ratioData=new FileOutputStream(
    				"SynapseRatios.txt",
    				(new File("SynapseRatios.txt")).exists());
	    	PrintStream pRatioData=new PrintStream(ratioData);
	        pRatioData.println(mu[2]/mu[3]);
	        ratioData.close();
    	} catch (FileNotFoundException e) {
    		IJ.write("Cannot open raw data file for output");
    		return;    		
    	} catch (IOException e) {
    		// Do nothing
    	}
    }
    
    public Vector getTypeVector() {
        return typeVector;
    }
    
    public void setTypeVector(Vector typeVector) {
        this.typeVector = typeVector;
    }
    
    public SynapseMeasuresCellCntrMarkerVector getCurrentMarkerVector() {
        return currentMarkerVector;
    }
    
    public void setCurrentMarkerVector(SynapseMeasuresCellCntrMarkerVector currentMarkerVector) {
        this.currentMarkerVector = currentMarkerVector;
    }
    
    public boolean isDelmode() {
        return delmode;
    }
    
    public void setDelmode(boolean delmode) {
        this.delmode = delmode;
    }
    
    public boolean isShowNumbers() {
        return showNumbers;
    }

    public void setShowNumbers(boolean showNumbers) {
        this.showNumbers = showNumbers;
    }
    
    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

}
