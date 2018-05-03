/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtrodemediana;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author willi
 */
public class Mediana {

    BufferedImage imagem;
    BufferedImage imagemFinal;
    int largura = 0, altura = 0;
    int[][] matrizAux;
    int[] array3X3;
    int[] array5X5;
    int[] array7X7;
    int aux3X3[] = new int[4];
    int aux5X5[];
    int aux7X7[];
    int[] array16;
    int[] array28;
    int aux[] = new int[6];

    public Mediana() throws IOException {

        imagem = ImageIO.read(new File("image.png"));
        largura = imagem.getWidth();
        altura = imagem.getHeight();
        imagemFinal = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        array3X3 = new int[9];
        aux5X5 = new int[16];
        array5X5 = new int[25];
        array7X7 = new int[49];
        array16 = new int[16];
        array28 = new int[28];
        aux7X7 = new int[20];
        matrizAux = new int[imagem.getWidth()][imagem.getHeight()];

    }

    public int gray(int r, int g, int b) {
        return (r + g + b) / 3;
    }

    public void getGrayScale() {

        for (int i = 0; i < largura - 1; i++) {
            for (int j = 0; j < altura - 1; j++) {
                Color c = new Color(imagem.getRGB(i, j));
                int valor = gray(c.getRed(), c.getGreen(), c.getBlue());
                matrizAux[i][j] = valor;

            }
        }

    }

    public void getValores3X3() throws IOException {
        getGrayScale();
        for (int i = 0; i < largura - 1; i++) {

            for (int j = 0; j < altura - 1; j++) {
                try {
                    array3X3[0] = matrizAux[i][j];
                    array3X3[1] = matrizAux[i + 1][j];
                    array3X3[2] = matrizAux[i - 1][j];
                    array3X3[3] = matrizAux[i][j + 1];
                    array3X3[4] = matrizAux[i + 1][j + 1];
                    array3X3[5] = matrizAux[i - 1][j + 1];
                    array3X3[6] = matrizAux[i + 1][j - 1];
                    array3X3[7] = matrizAux[i - 1][j - 1];
                    array3X3[8] = matrizAux[i][j - 1];
                    Color c = new Color(mediana(array3X3), mediana(array3X3), mediana(array3X3));
                    imagemFinal.setRGB(i, j, c.getRGB());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    if (j == 0) {
                        if (i == 0) {
                            aux3X3[0] = matrizAux[i][j];
                            aux3X3[1] = matrizAux[i][j + 1];
                            aux3X3[2] = matrizAux[i + 1][j + 1];
                            aux3X3[3] = matrizAux[i + 1][j];
                            Color c = new Color(mediana(aux3X3), mediana(aux3X3), mediana(aux3X3));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                        if (i == largura - 1) {
                            aux3X3[0] = matrizAux[i][j];
                            aux3X3[1] = matrizAux[i][j + 1];
                            aux3X3[2] = matrizAux[i - 1][j + 1];
                            aux3X3[3] = matrizAux[i - 1][j];
                            Color c = new Color(mediana(aux3X3), mediana(aux3X3), mediana(aux3X3));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                        if (i > 0 && j < largura - 1) {
                            aux[0] = matrizAux[i][j];
                            aux[1] = matrizAux[i - 1][j + 1];
                            aux[2] = matrizAux[i + 1][j + 1];
                            aux[3] = matrizAux[i + 1][j];
                            aux[4] = matrizAux[i - 1][j + 1];
                            aux[5] = matrizAux[i - 1][j];
                            Color c = new Color(mediana(aux), mediana(aux), mediana(aux));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                    }
                    if (j == altura - 1) {
                        if (i == 0) {
                            aux3X3[0] = matrizAux[i][j];
                            aux3X3[1] = matrizAux[i][j - 1];
                            aux3X3[2] = matrizAux[i + 1][j - 1];
                            aux3X3[3] = matrizAux[i + 1][j];
                            Color c = new Color(mediana(aux3X3), mediana(aux3X3), mediana(aux3X3));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                        if (i == largura - 1) {
                            aux3X3[0] = matrizAux[i][j];
                            aux3X3[1] = matrizAux[i][j - 1];
                            aux3X3[2] = matrizAux[i - 1][j - 1];
                            aux3X3[3] = matrizAux[i - 1][j];
                            Color c = new Color(mediana(aux3X3), mediana(aux3X3), mediana(aux3X3));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                        if (i > altura - 1 && j < largura - 1) {
                            aux[0] = matrizAux[i][j];
                            aux[1] = matrizAux[i - 1][j - 1];
                            aux[2] = matrizAux[i + 1][j - 1];
                            aux[3] = matrizAux[i + 1][j];
                            aux[4] = matrizAux[i - 1][j - 1];
                            aux[5] = matrizAux[i - 1][j];
                            Color c = new Color(mediana(aux), mediana(aux), mediana(aux));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }

                    }

                }

            }

        }
        ImageIO.write(imagemFinal, "jpg", new File("Filter 3X3.jpg"));

    }

    public void getValores5X5() throws IOException {
        getGrayScale();
        for (int i = 0; i < largura - 1; i++) {

            for (int j = 0; j < altura - 1; j++) {
                try {
                    array5X5[0] = matrizAux[i][j];
                    array5X5[1] = matrizAux[i][j - 1];
                    array5X5[2] = matrizAux[i][j - 2];
                    array5X5[3] = matrizAux[i][j + 1];
                    array5X5[4] = matrizAux[i][j + 2];
                    array5X5[5] = matrizAux[i + 1][j - 1];
                    array5X5[6] = matrizAux[i + 1][j - 2];
                    array5X5[7] = matrizAux[i + 1][j];
                    array5X5[8] = matrizAux[i + 1][j + 1];
                    array5X5[9] = matrizAux[i + 1][j + 2];
                    array5X5[10] = matrizAux[i - 1][j - 2];
                    array5X5[11] = matrizAux[i - 1][j - 1];
                    array5X5[12] = matrizAux[i - 1][j];
                    array5X5[13] = matrizAux[i - 1][j + 1];
                    array5X5[14] = matrizAux[i - 1][j + 2];
                    array5X5[15] = matrizAux[i - 2][j - 2];
                    array5X5[16] = matrizAux[i - 2][j - 1];
                    array5X5[17] = matrizAux[i - 2][j];
                    array5X5[18] = matrizAux[i - 2][j + 1];
                    array5X5[19] = matrizAux[i - 2][j + 2];
                    array5X5[20] = matrizAux[i + 2][j - 2];
                    array5X5[21] = matrizAux[i + 2][j - 1];
                    array5X5[22] = matrizAux[i + 2][j];
                    array5X5[23] = matrizAux[i + 2][j + 1];
                    array5X5[24] = matrizAux[i + 2][j + 2];

                    Color c = new Color(mediana(array5X5), mediana(array5X5), mediana(array5X5));
                    imagemFinal.setRGB(i, j, c.getRGB());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    if (j == 0) {
                        if (i == 0) {
                            array3X3[0] = matrizAux[i + 1][j];
                            array3X3[1] = matrizAux[i][j];
                            array3X3[2] = matrizAux[i + 2][j];
                            array3X3[3] = matrizAux[i + 1][j + 1];
                            array3X3[4] = matrizAux[i][j + 1];
                            array3X3[5] = matrizAux[i + 2][j + 1];
                            array3X3[6] = matrizAux[i + 1][j + 2];
                            array3X3[7] = matrizAux[i][j + 2];
                            array3X3[8] = matrizAux[i + 2][j + 2];
                            Color c = new Color(mediana(array3X3), mediana(array3X3), mediana(array3X3));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                        if (i == largura - 1) {
                            array3X3[0] = matrizAux[i + 1][j];
                            array3X3[1] = matrizAux[i][j];
                            array3X3[2] = matrizAux[i + 2][j];
                            array3X3[3] = matrizAux[i + 1][j - 1];
                            array3X3[4] = matrizAux[i][j - 1];
                            array3X3[5] = matrizAux[i + 2][j - 1];
                            array3X3[6] = matrizAux[i + 1][j - 2];
                            array3X3[7] = matrizAux[i][j - 2];
                            array3X3[8] = matrizAux[i + 2][j - 2];
                            Color c = new Color(mediana(array3X3), mediana(array3X3), mediana(array3X3));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                        if (i >= 2 && i < largura - 3) {
                            aux5X5[0] = matrizAux[i][j];
                            aux5X5[1] = matrizAux[i - 1][j];
                            aux5X5[2] = matrizAux[i - 2][j];
                            aux5X5[3] = matrizAux[i + 1][j];
                            aux5X5[4] = matrizAux[i + 2][j];
                            aux5X5[5] = matrizAux[i + 2][j + 1];
                            aux5X5[6] = matrizAux[i + 1][j + 1];
                            aux5X5[7] = matrizAux[i][j + 1];
                            aux5X5[8] = matrizAux[i - 1][j + 1];
                            aux5X5[9] = matrizAux[i][j + 2];
                            aux5X5[10] = matrizAux[i + 2][j + 1];
                            aux5X5[11] = matrizAux[i - 2][j + 2];
                            aux5X5[12] = matrizAux[i - 1][j + 2];
                            aux5X5[13] = matrizAux[i + 1][j + 2];
                            aux5X5[14] = matrizAux[i + 2][j + 2];
                            Color c = new Color(mediana(aux5X5), mediana(aux5X5), mediana(aux5X5));
                            imagemFinal.setRGB(i, j, c.getRGB());

                        }

                    }
                    if (j == altura - 1) {
                        if (i == 0) {
                            array3X3[0] = matrizAux[i + 1][j];
                            array3X3[1] = matrizAux[i][j];
                            array3X3[2] = matrizAux[i + 2][j];
                            array3X3[3] = matrizAux[i + 1][j - 1];
                            array3X3[4] = matrizAux[i][j - 1];
                            array3X3[5] = matrizAux[i + 2][j - 1];
                            array3X3[6] = matrizAux[i + 1][j - 2];
                            array3X3[7] = matrizAux[i][j - 2];
                            array3X3[8] = matrizAux[i + 2][j - 2];
                            Color c = new Color(mediana(array3X3), mediana(array3X3), mediana(array3X3));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                        if (i == largura - 1) {
                            array3X3[0] = matrizAux[i - 1][j];
                            array3X3[1] = matrizAux[i][j];
                            array3X3[2] = matrizAux[i - 2][j];
                            array3X3[3] = matrizAux[i - 1][j - 1];
                            array3X3[4] = matrizAux[i][j - 1];
                            array3X3[5] = matrizAux[i - 2][j - 1];
                            array3X3[6] = matrizAux[i - 1][j - 2];
                            array3X3[7] = matrizAux[i][j - 2];
                            array3X3[8] = matrizAux[i - 2][j - 2];
                            Color c = new Color(mediana(array3X3), mediana(array3X3), mediana(array3X3));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }
                        if (j < altura - 3 && i < largura - 3) {
                            aux5X5[0] = matrizAux[i][j];
                            aux5X5[1] = matrizAux[i - 1][j];
                            aux5X5[2] = matrizAux[i - 2][j];
                            aux5X5[3] = matrizAux[i + 1][j];
                            aux5X5[4] = matrizAux[i + 2][j];
                            aux5X5[5] = matrizAux[i + 2][j - 1];
                            aux5X5[6] = matrizAux[i + 1][j - 1];
                            aux5X5[7] = matrizAux[i][j - 1];
                            aux5X5[8] = matrizAux[i - 1][j - 1];
                            aux5X5[9] = matrizAux[i][j - 2];
                            aux5X5[10] = matrizAux[i + 2][j - 1];
                            aux5X5[11] = matrizAux[i - 2][j - 2];
                            aux5X5[12] = matrizAux[i - 1][j - 2];
                            aux5X5[13] = matrizAux[i + 1][j - 2];
                            aux5X5[14] = matrizAux[i + 2][j - 2];
                            Color c = new Color(mediana(aux5X5), mediana(aux5X5), mediana(aux5X5));
                            imagemFinal.setRGB(i, j, c.getRGB());
                        }

                    }

                }

            }

        }
        ImageIO.write(imagemFinal, "jpg", new File("Filter 5X5.jpg"));

    }

    

    public int mediana(int[] array) {
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

}
