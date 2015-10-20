/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.de.edbii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Wiriter{
	
	BufferedWriter buffWrite;
	
	public void escrever(String url, String resultado) throws IOException{
		
		buffWrite = new BufferedWriter(new FileWriter(url));  
		buffWrite.append(resultado + "\n"); 
		buffWrite.close(); 
	}
	

	

}