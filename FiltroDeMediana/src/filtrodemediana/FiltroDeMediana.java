/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtrodemediana;

import java.io.IOException;

/**
 *
 * @author willi
 */
public class FiltroDeMediana {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Mediana m = new Mediana();
        m.getValores5X5();
        System.out.println("Image processed");

    }

}
