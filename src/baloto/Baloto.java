/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloto;

import java.io.IOException;

/**
 *
 * @author RAFAEL RAMIREZ
 */
public class Baloto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Maquina a = new Maquina();
        int opcion;
        
        do{
            opcion = a.menu();
            switch(opcion){
                    case 1:
                        a.compra();
                        break;
                    case 2:
                        a.sorteo();
                        break;
            }
        }while(opcion!=3);
        
    }
    
}
