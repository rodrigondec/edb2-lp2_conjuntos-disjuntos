/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.de.edbii;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author gustavo
 */
public class TrabalhoDeEDBII{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        double start = System.currentTimeMillis();  
        Wiriter escrever = new Wiriter();
        
        Casa c1 = new Casa(); 
        String Total;
        Total = "";
        c1.copiaCustos();
        
        Total += c1.combinacao();
        double elapsed = ((System.currentTimeMillis() - start)/1000); 
        Total +="\n"+elapsed;
        escrever.escrever("out", Total);
        
        
    }
}
