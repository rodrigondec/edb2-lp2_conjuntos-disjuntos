/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.de.edbii;

/**
 *
 * @author gustavo
 */
class Celula{
    
    protected int linha;
    protected int coluna;
    protected int valor;
    
    public Celula(int i, int j, int val){//irá receber a localização na matriz de custos se referindo as casa que se ligam
        
        linha = i;
        coluna = j;
        valor = val;
    }
}

