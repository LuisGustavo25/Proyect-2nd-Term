/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloto;

import java.util.StringTokenizer;

/**
 *
 * @author RAFAEL RAMIREZ
 */
public class boleto {
    int [] numeros = new int[5];
    StringTokenizer tok;
    int superbalota;
    
    boleto(String line){
        tok = new StringTokenizer(line);
        numeros[0] = Integer.parseInt(tok.nextToken("-"));
        numeros[1] = Integer.parseInt(tok.nextToken("-"));
        numeros[2] = Integer.parseInt(tok.nextToken("-"));
        numeros[3] = Integer.parseInt(tok.nextToken("-"));
        numeros[4] = Integer.parseInt(tok.nextToken("-"));
        superbalota = Integer.parseInt(tok.nextToken("-"));
    }
    
}
