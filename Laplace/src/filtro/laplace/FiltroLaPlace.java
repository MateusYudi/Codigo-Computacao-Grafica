package filtro.laplace;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FiltroLaPlace {
    
    int[][] matriz3X3;
    BufferedImage img;
    BufferedImage finalImage;
    int[][] matAux;
    int altura;
    int largura;

    public void valores3X3() {
        matriz3X3[0][0] = 1;
        matriz3X3[0][1] = 1;
        matriz3X3[0][2] = 1;
        matriz3X3[1][0] = 1;
        matriz3X3[1][1] = -8;
        matriz3X3[1][2] = 1;
        matriz3X3[2][0] = 1;
        matriz3X3[2][1] = 1;
        matriz3X3[2][2] = 1;

    }

    public void metodoLaplace(BufferedImage img) throws IOException {

        matriz3X3 = new int[3][3];
        finalImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        matAux = new int[img.getWidth()][img.getHeight()];
        
        this.valores3X3();
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color c = new Color(img.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int gray = (r + g + b) / 3;
                matAux[i][j] = gray;

            }
        }
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                try {

                    int valorTotal = matAux[i][j] * matriz3X3[1][1];

                    valorTotal += matAux[i + 1][j] * matriz3X3[1][2];
                    valorTotal += matAux[i][j + 1] * matriz3X3[2][1];
                    valorTotal += matAux[i - 1][j] * matriz3X3[1][0];
                    valorTotal += matAux[i][j - 1] * matriz3X3[0][1];
                    valorTotal += matAux[i - 1][j - 1] * matriz3X3[0][0];
                    valorTotal += matAux[i + 1][j + 1] * matriz3X3[2][2];
                    valorTotal += matAux[i - 1][j + 1] * matriz3X3[2][0];
                    valorTotal += matAux[i + 1][j - 1] * matriz3X3[0][2];
                    if (valorTotal < 0) {
                        valorTotal = valorTotal * -1;
                    }
                    if (valorTotal > 255) {
                        valorTotal = 255;
                    }
                    Color c = new Color(valorTotal, valorTotal, valorTotal);
                    finalImage.setRGB(i, j, c.getRGB());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    if (j == 0) {
                        if (i == 0) {
                            int valorTotal = matAux[i][j] * matriz3X3[1][1];
                            valorTotal += matAux[i + 1][j] * matriz3X3[1][2];
                            valorTotal += matAux[i][j + 1] * matriz3X3[2][1];
                            valorTotal += matAux[i + 1][j] * matriz3X3[2][2];
                            if (valorTotal < 0) {
                                valorTotal = valorTotal * -1;
                            }
                            valorTotal = valorTotal / 3;
                            if (valorTotal > 255) {
                                valorTotal = 255;
                            }
                            Color c = new Color(valorTotal, valorTotal, valorTotal);
                            finalImage.setRGB(i, j, c.getRGB());

                        }
                        if (i == img.getWidth() - 1) {
                            int valorTotal = matAux[i][j] * matriz3X3[1][1];
                            valorTotal += matAux[i - 1][j] * matriz3X3[1][0];
                            valorTotal += matAux[i][j + 1] * matriz3X3[2][0];
                            valorTotal += matAux[i - 1][j + 1] * matriz3X3[2][1];
                            if (valorTotal < 0) {
                                valorTotal = valorTotal * -1;
                            }
                            valorTotal = valorTotal / 3;
                            if (valorTotal > 255) {
                                valorTotal = 255;
                            }
                            Color c = new Color(valorTotal, valorTotal, valorTotal);
                            finalImage.setRGB(i, j, c.getRGB());

                        }
                        if (i != 0 && i != img.getWidth() - 1) {
                            int valorTotal = matAux[i][j] * matriz3X3[1][1];
                            valorTotal += matAux[i - 1][j] * matriz3X3[1][0];
                            valorTotal += matAux[i - 1][j + 1] * matriz3X3[2][0];
                            valorTotal += matAux[i][j + 1] * matriz3X3[2][1];
                            valorTotal += matAux[i + 1][j + 1] * matriz3X3[2][2];
                            valorTotal += matAux[i + 1][j] * matriz3X3[1][2];
                            if (valorTotal < 0) {
                                valorTotal = valorTotal * -1;
                            }
                            valorTotal = valorTotal / 4;
                            if (valorTotal > 255) {
                                valorTotal = 255;
                            }
                            Color c = new Color(valorTotal, valorTotal, valorTotal);
                            finalImage.setRGB(i, j, c.getRGB());

                        }

                    }
                    if (j == img.getHeight() - 1) {
                        if (i == 0) {
                            int valorTotal = matAux[i][j] * matriz3X3[1][1];
                            valorTotal += matAux[i + 1][j] * matriz3X3[1][2];
                            valorTotal += matAux[i][j - 1] * matriz3X3[0][1];
                            valorTotal += matAux[i + 1][j - 1] * matriz3X3[0][2];
                            if (valorTotal < 0) {
                                valorTotal = valorTotal * -1;
                            }
                            valorTotal = valorTotal / 3;
                            if (valorTotal > 255) {
                                valorTotal = 255;
                            }
                            Color c = new Color(valorTotal, valorTotal, valorTotal);
                            finalImage.setRGB(i, j, c.getRGB());

                        }
                        if (i == img.getWidth() - 1) {
                            int valorTotal = matAux[i][j] * matriz3X3[1][1];
                            valorTotal += matAux[i - 1][j] * matriz3X3[1][0];
                            valorTotal += matAux[i][j - 1] * matriz3X3[2][0];
                            valorTotal += matAux[i - 1][j - 1] * matriz3X3[2][1];
                            if (valorTotal < 0) {
                                valorTotal = valorTotal * -1;
                            }
                            valorTotal = valorTotal / 3;

                            if (valorTotal > 255) {
                                valorTotal = 255;
                            }
                            Color c = new Color(valorTotal, valorTotal, valorTotal);
                            finalImage.setRGB(i, j, c.getRGB());

                        }
                        if (i != 0 && i != img.getWidth() - 1) {
                            int valorTotal = matAux[i][j] * matriz3X3[1][1];
                            valorTotal += matAux[i - 1][j] * matriz3X3[1][0];
                            valorTotal += matAux[i - 1][j - 1] * matriz3X3[0][0];
                            valorTotal += matAux[i][j - 1] * matriz3X3[0][1];
                            valorTotal += matAux[i + 1][j - 1] * matriz3X3[0][2];
                            valorTotal += matAux[i + 1][j] * matriz3X3[1][2];
                            if (valorTotal < 0) {
                                valorTotal = valorTotal * -1;
                            }
                            valorTotal = valorTotal / 4;

                            if (valorTotal > 255) {
                                valorTotal = 255;
                            }
                            Color c = new Color(valorTotal, valorTotal, valorTotal);
                            finalImage.setRGB(i, j, c.getRGB());

                        }

                    }

                }

            }
        }
        String outPut = "C:\\Users\\Mateus\\Desktop\\Imagens\\laplace.jpg";
	File out = new File(outPut);
        ImageIO.write(finalImage,"JPG",out); 
    }

    
    public static void main(String[] args) {
         try{
            File f = new File("C:\\Users\\Mateus\\Desktop\\Imagens\\teste.jpg");
            BufferedImage img = ImageIO.read(f);
            
            FiltroLaPlace eu = new FiltroLaPlace();
            eu.metodoLaplace(img);
                
        }catch (Exception ex){
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
}
