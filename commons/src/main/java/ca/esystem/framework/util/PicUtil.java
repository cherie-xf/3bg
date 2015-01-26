package ca.esystem.framework.util;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PicUtil {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private static int maxPicWidth=800;
	
	public static int getMaxPicWidth() {
		return maxPicWidth;
	}

	public static int getImageWidth(String src){
		File origFile = new File(src);
		ImageIcon icon = new ImageIcon(origFile.getPath());
		return icon.getIconWidth();
	}
	
	public static int getImageHeight(String src){
		File origFile = new File(src);
		ImageIcon icon = new ImageIcon(origFile.getPath());
		return icon.getIconHeight();
	}
	
	public static void resizePicture(String src,String dest,int width,int height){
		String extname = FilenameUtils.getExtension(src);
		File origFile = new File(src);
		ImageIcon icon = new ImageIcon(origFile.getPath());
		
        int thumbWidth = width;// Specify image width in px  
        int thumbHeight = height; // Specify image height in px  
          
        int imageWidth = icon.getIconWidth();  // get image Width  
        int imageHeight = icon.getIconHeight();  // get image Height  
        
        double thumbRatio = (double) thumbWidth / (double) thumbHeight;  
        double imageRatio = (double) imageWidth / (double) imageHeight;  
 
        // This calculation is used to convert the image size according to the pixels mentioned above  
        if (thumbRatio < imageRatio) {  
           thumbHeight = (int) (thumbWidth / imageRatio);  
        } else {  
           thumbWidth = (int) (thumbHeight * imageRatio);  
        }  
		
        // create BufferedImage object with thumbnail's width and height
        BufferedImage bufferedImage = new BufferedImage(thumbWidth,thumbHeight,BufferedImage.TYPE_INT_RGB);
        
        // create graphics object and add original image to it
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(icon.getImage(), 0, 0, thumbWidth, thumbHeight, null);
        graphics.dispose();
        
       
        //Write the new picture into destination
        if(!new File(dest).exists()){
			   new File(dest).mkdirs();
		}
        File newFile = new File(dest);		   
        try {
              ImageIO.write(bufferedImage, extname , newFile);
        } catch (IOException e) {
              e.printStackTrace();
        }
	}
	
	public static void printWatermark(String src,String dest,String watermark){
		String extname = FilenameUtils.getExtension(src);
		File origFile = new File(src);
		ImageIcon icon = new ImageIcon(origFile.getPath());
		
        // create BufferedImage object of same width and height as of original image
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),
                    icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        
        // create graphics object and add original image to it
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(icon.getImage(), 0, 0, null);
        
        // 50% transparent
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        graphics.setColor(Color.white);
        
        // set font for the watermark text
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
       
        // add the watermark text
        if(icon.getIconWidth()>200){
           graphics.drawString(watermark, icon.getIconWidth()-120, icon.getIconHeight()-10);
        }
        graphics.dispose();
        
        //Write the new picture into destination
        if(!new File(dest).exists()){
			   new File(dest).mkdirs();
		}
        
        File newFile = new File(dest);		   
        try {
              ImageIO.write(bufferedImage, extname , newFile);
        } catch (IOException e) {
              e.printStackTrace();
        }
        
        origFile.delete();
	}
}
