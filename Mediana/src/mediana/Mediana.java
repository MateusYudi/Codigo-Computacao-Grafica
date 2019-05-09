package mediana;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mediana {

    public static int cinzar(int r, int g, int b) {
        return (r + g + b) / 3;
    }
    
    public static int calcularMediana(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                }
            }
        }
        if (array.length % 2 == 0) {
            int valor = (array[array.length / 2] + array[array.length / 2 + 1]) / 2;
            return valor;
        }
        return array[((int) (array.length / 2)) + 1];
}
    
    public static void grayScale(BufferedImage img,int[][] matrizAux) {
        for (int i = 0; i < img.getWidth() - 1; i++) {
            for (int j = 0; j < img.getHeight() - 1; j++) {
                Color pixel = new Color(img.getRGB(i, j));
                int valor = cinzar(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
                matrizAux[i][j] = valor;
            }
        }
    }

    
    public static void executarMediana(BufferedImage img) throws IOException{
        
        int[][] mAux;
        int[] array3X3;
        int[] array5X5;
        int aux5X5[];
        int aux7X7[];
        int aux[] = new int[6];
        array3X3  = new int[9];
        aux5X5    = new int[16];
        array5X5  = new int[25];
        aux7X7    = new int[20];
        
        BufferedImage finalImg;
        finalImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        mAux = new int[img.getWidth()][img.getHeight()];
        
        grayScale(img, mAux);
        
        for (int i = 0; i < img.getWidth() - 1; i++) {
            for (int j = 0; j < img.getHeight() - 1; j++) {
                try {
                    array5X5[0] = mAux[i][j];
                    array5X5[1] = mAux[i][j - 1];
                    array5X5[2] = mAux[i][j - 2];
                    array5X5[3] = mAux[i][j + 1];
                    array5X5[4] = mAux[i][j + 2];
                    array5X5[5] = mAux[i + 1][j - 1];
                    array5X5[6] = mAux[i + 1][j - 2];
                    array5X5[7] = mAux[i + 1][j];
                    array5X5[8] = mAux[i + 1][j + 1];
                    array5X5[9] = mAux[i + 1][j + 2];
                    array5X5[10] = mAux[i - 1][j - 2];
                    array5X5[11] = mAux[i - 1][j - 1];
                    array5X5[12] = mAux[i - 1][j];
                    array5X5[13] = mAux[i - 1][j + 1];
                    array5X5[14] = mAux[i - 1][j + 2];
                    array5X5[15] = mAux[i - 2][j - 2];
                    array5X5[16] = mAux[i - 2][j - 1];
                    array5X5[17] = mAux[i - 2][j];
                    array5X5[18] = mAux[i - 2][j + 1];
                    array5X5[19] = mAux[i - 2][j + 2];
                    array5X5[20] = mAux[i + 2][j - 2];
                    array5X5[21] = mAux[i + 2][j - 1];
                    array5X5[22] = mAux[i + 2][j];
                    array5X5[23] = mAux[i + 2][j + 1];
                    array5X5[24] = mAux[i + 2][j + 2];

                    Color c = new Color(calcularMediana(array5X5), calcularMediana(array5X5), calcularMediana(array5X5));
                    finalImg.setRGB(i, j, c.getRGB());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    if (j == 0) {
                        if (i == 0) {
                            array3X3[0] = mAux[i + 1][j];
                            array3X3[1] = mAux[i][j];
                            array3X3[2] = mAux[i + 2][j];
                            array3X3[3] = mAux[i + 1][j + 1];
                            array3X3[4] = mAux[i][j + 1];
                            array3X3[5] = mAux[i + 2][j + 1];
                            array3X3[6] = mAux[i + 1][j + 2];
                            array3X3[7] = mAux[i][j + 2];
                            array3X3[8] = mAux[i + 2][j + 2];
                            Color c = new Color(calcularMediana(array3X3), calcularMediana(array3X3), calcularMediana(array3X3));
                            finalImg.setRGB(i, j, c.getRGB());
                        }
                        if (i == img.getWidth() - 1) {
                            array3X3[0] = mAux[i + 1][j];
                            array3X3[1] = mAux[i][j];
                            array3X3[2] = mAux[i + 2][j];
                            array3X3[3] = mAux[i + 1][j - 1];
                            array3X3[4] = mAux[i][j - 1];
                            array3X3[5] = mAux[i + 2][j - 1];
                            array3X3[6] = mAux[i + 1][j - 2];
                            array3X3[7] = mAux[i][j - 2];
                            array3X3[8] = mAux[i + 2][j - 2];
                            Color c = new Color(calcularMediana(array3X3), calcularMediana(array3X3), calcularMediana(array3X3));
                            finalImg.setRGB(i, j, c.getRGB());
                        }
                        if (i >= 2 && i < img.getWidth() - 3) {
                            aux5X5[0] = mAux[i][j];
                            aux5X5[1] = mAux[i - 1][j];
                            aux5X5[2] = mAux[i - 2][j];
                            aux5X5[3] = mAux[i + 1][j];
                            aux5X5[4] = mAux[i + 2][j];
                            aux5X5[5] = mAux[i + 2][j + 1];
                            aux5X5[6] = mAux[i + 1][j + 1];
                            aux5X5[7] = mAux[i][j + 1];
                            aux5X5[8] = mAux[i - 1][j + 1];
                            aux5X5[9] = mAux[i][j + 2];
                            aux5X5[10] = mAux[i + 2][j + 1];
                            aux5X5[11] = mAux[i - 2][j + 2];
                            aux5X5[12] = mAux[i - 1][j + 2];
                            aux5X5[13] = mAux[i + 1][j + 2];
                            aux5X5[14] = mAux[i + 2][j + 2];
                            Color c = new Color(calcularMediana(aux5X5), calcularMediana(aux5X5), calcularMediana(aux5X5));
                            finalImg.setRGB(i, j, c.getRGB());
                        }
                    }
                    if (j == img.getHeight() - 1) {
                        if (i == 0) {
                            array3X3[0] = mAux[i + 1][j];
                            array3X3[1] = mAux[i][j];
                            array3X3[2] = mAux[i + 2][j];
                            array3X3[3] = mAux[i + 1][j - 1];
                            array3X3[4] = mAux[i][j - 1];
                            array3X3[5] = mAux[i + 2][j - 1];
                            array3X3[6] = mAux[i + 1][j - 2];
                            array3X3[7] = mAux[i][j - 2];
                            array3X3[8] = mAux[i + 2][j - 2];
                            Color c = new Color(calcularMediana(array3X3), calcularMediana(array3X3), calcularMediana(array3X3));
                            finalImg.setRGB(i, j, c.getRGB());
                        }
                        if (i == img.getWidth() - 1) {
                            array3X3[0] = mAux[i - 1][j];
                            array3X3[1] = mAux[i][j];
                            array3X3[2] = mAux[i - 2][j];
                            array3X3[3] = mAux[i - 1][j - 1];
                            array3X3[4] = mAux[i][j - 1];
                            array3X3[5] = mAux[i - 2][j - 1];
                            array3X3[6] = mAux[i - 1][j - 2];
                            array3X3[7] = mAux[i][j - 2];
                            array3X3[8] = mAux[i - 2][j - 2];
                            Color c = new Color(calcularMediana(array3X3), calcularMediana(array3X3), calcularMediana(array3X3));
                            finalImg.setRGB(i, j, c.getRGB());
                        }
                        if (j < img.getHeight() - 3 && i < img.getWidth() - 3) {
                            aux5X5[0] = mAux[i][j];
                            aux5X5[1] = mAux[i - 1][j];
                            aux5X5[2] = mAux[i - 2][j];
                            aux5X5[3] = mAux[i + 1][j];
                            aux5X5[4] = mAux[i + 2][j];
                            aux5X5[5] = mAux[i + 2][j - 1];
                            aux5X5[6] = mAux[i + 1][j - 1];
                            aux5X5[7] = mAux[i][j - 1];
                            aux5X5[8] = mAux[i - 1][j - 1];
                            aux5X5[9] = mAux[i][j - 2];
                            aux5X5[10] = mAux[i + 2][j - 1];
                            aux5X5[11] = mAux[i - 2][j - 2];
                            aux5X5[12] = mAux[i - 1][j - 2];
                            aux5X5[13] = mAux[i + 1][j - 2];
                            aux5X5[14] = mAux[i + 2][j - 2];
                            Color c = new Color(calcularMediana(aux5X5), calcularMediana(aux5X5), calcularMediana(aux5X5));
                            finalImg.setRGB(i, j, c.getRGB());
                        }
                    }
                }
            }
        }
        String outPut = "C:\\Users\\Mateus\\Desktop\\Imagens\\mediana.jpg";
        File out = new File(outPut);
        ImageIO.write(finalImg,"JPG",out);          
    }
  
    public static void main(String[] args) {
        try{
            File f = new File("C:\\Users\\Mateus\\Desktop\\Imagens\\teste.jpg");
            BufferedImage img = ImageIO.read(f);
            executarMediana(img);
              
        }catch (Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
}
