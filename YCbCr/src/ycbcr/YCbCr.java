package ycbcr;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class YCbCr{

    public static void executarYCbCr(BufferedImage img) throws IOException{
        
        BufferedImage redImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        BufferedImage greenImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        BufferedImage blueImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        int cont =0;
        
        
         for (int x = 0; x < img.getWidth(); x++) {
                for (int z = 0; z < img.getHeight(); z++) {
                    
                    Color c = new Color(img.getRGB(x, z));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                    
                    double y = 16 + (65.738 * r / 256) + 
                                    (129.057 * g / 256)+ 
                                    (25.064 * b / 256);
                    
                    double cb = 128 - (37.945 * r / 256) -
                                      (74.494 * g / 256) +
                                      (112.439 * b / 256);
                    
                    double cr = 128 + (112.439 * r / 256) -
                                      (94.154 * b / 256) -
                                      (18.258 * b / 256);
                    
                    double nY = (255 / 218) * (y - 16);
                    double nCb = (255 / 224) * (y - 16);
                    double nCr = (255 / 224) * (y - 16);

                    redImage.setRGB(x, z, new Color((int) y, (int) y, (int) y).getRGB());
                    greenImage.setRGB(x, z, new Color((int) cb, (int) cb, (int) cb).getRGB());
                    blueImage.setRGB(x, z, new Color((int) cr, (int) cr, (int) cr).getRGB());
                    cont++;
                }
            }
         
         File out = new File("C:\\Users\\Mateus\\Desktop\\Imagens\\Y.jpg");
         ImageIO.write(redImage, "JPG", out);
              out = new File("C:\\Users\\Mateus\\Desktop\\Imagens\\Cb.jpg");
         ImageIO.write(greenImage, "JPG", out);
              out = new File("C:\\Users\\Mateus\\Desktop\\Imagens\\Cr.jpg");
         ImageIO.write(blueImage, "JPG", out);
    }
    
    
    public static void main(String[] args) {
        try{
            File f = new File("C:\\Users\\Mateus\\Desktop\\Imagens\\teste.jpg");
            BufferedImage img = ImageIO.read(f);
            
            executarYCbCr(img); 
                
        }catch (Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
}
