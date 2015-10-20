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
import java.util.ArrayList;
import static java.util.Date.parse;
import static java.util.logging.Level.parse;

/**
 *
 * @author gustavo
 */
public class Casa {
    
      
    private Disjoint casa;
    private int grau[];
    private int qtdCasas;
    private Celula cel[];
    private int conexoes;
    private Combinacao totalComb;
    private int matrizCustos[][];
    
    public Casa() throws IOException{
        
        
        setMatriz();
        int str[] = new int[qtdCasas];//vetor com os valores das casas
        casa = new Disjoint(); 
        casa.MakeSet(qtdCasas);
        grau = new int[qtdCasas];
        cel = new Celula[qtdCasas*(qtdCasas-1)/2];
       
        for(int i = 0;i < qtdCasas;i++){
            
            str[i] = i;
            grau[i] = 0;
        }
        
   
    }
    //Ler os valore do arquivo e pega os valores das casas, ligações e custos
    public void setMatriz() throws FileNotFoundException, IOException{
        
        FileInputStream stream = new FileInputStream("io");  
        InputStreamReader streamReader = new InputStreamReader(stream);  
        BufferedReader reader  = new BufferedReader(streamReader);  
        String linha[] = reader.readLine().split(" ");
        
        qtdCasas = Integer.parseInt(linha[0]);
        conexoes = Integer.parseInt(linha[1]);
        
        matrizCustos = new int[qtdCasas][qtdCasas];
        int cont = 0;
        
        for(int i = 0; i < qtdCasas-1; i++){
           linha = reader.readLine().split(" ");
            
           int k = 0;

            for(int j = 0; j < qtdCasas; j++){
            	cont ++;
                if(i == j){
                    matrizCustos[i][j] = 0;
                    k = 0;
                }    
                else if(j > i){
                    matrizCustos[i][j] = Integer.parseInt(linha[k]);
                    matrizCustos[j][i] = matrizCustos[i][j];
                    
                    k++;
                }
            }
        }
        
    }
    
    //Classse para gerar todas as combinações e fazer as ligações das casas e verificar os custos
    public String combinacao() throws IOException{
        
      
        int [] saida;//recebe a combinação
        int somaTotal = 100000000;//custo final
        int somaCustos = 0;//recebe o custo de cada combinação/
        int aux;
        String escreverArquivo ="";//string retornada pelo metodo com os resultados
        int auxComb = 0;
        int verificaValidez;
        int combinaçoesValidas  = 0;
        int outOf = (qtdCasas*(qtdCasas-1))/2;
        String cadaCombinaçao = "";
        String aux2 ="";
        int[][] combinations = Combinacao.getCombinations(qtdCasas-1, outOf);
             
            //faz as combinações possíveis levando em conta as repetições
             for (int i = 0; i < combinations.length; i++){
                 
                 Disjoint novo = new Disjoint();//auxiliar para verificar se as casas já estão ligadas
                 novo.MakeSet(qtdCasas);
                 
                 //zera o vetor de grau para verificar a próxima combinação
                 for(int a = 0;a < qtdCasas;a++){
                   
                    grau[a] = 0;  
                }
       
                verificaValidez = 0;
                for (int j = 0; j < combinations[i].length; j++){

                            //recebe os elemento de uma combinação
                            aux = combinations[i][j];
                            
                            cadaCombinaçao +="("+ cel[aux].linha+", "+cel[aux].coluna+")";
                            //atualizar o vetor grau
                            grau[novo.Find(cel[aux].linha)] ++; 
                            grau[novo.Find(cel[aux].coluna)] ++; 
                            
                            //verificar se realmente é necessário
                           if(grau[novo.Find(cel[aux].linha)] > conexoes || grau[novo.Find(cel[aux].coluna)] > conexoes){
                                
                                grau[novo.Find(cel[aux].linha)] --; 
                                grau[novo.Find(cel[aux].coluna)] --;
                                
                            }
 
                            //verifica se a ligação pode ser feita em relação as conexões
                            if(grau[novo.Find(cel[aux].linha)] < conexoes || grau[novo.Find(cel[aux].coluna)] < conexoes){
                                
                                
                                //verifica se o find são diferentes pra poder eliminar os ciclos
                                if(novo.Find(cel[aux].linha) != novo.Find(cel[aux].coluna)){

                                    auxComb ++;
                                    novo.UnionbyRank(cel[aux].linha, cel[aux].coluna);
                                    somaCustos = somaCustos + matrizCustos[cel[aux].linha][cel[aux].coluna];
                                    verificaValidez ++;//contador para as combinações válidas
                                    
                                }
                               
                            }
                  
            }
               
            if(verificaValidez == qtdCasas-1){
                    
                combinaçoesValidas++;
                    
            }
            //serve para eliminar as combinações com ciclos 
            else if(verificaValidez < qtdCasas-1){
                    
                somaCustos = 0;
            }
            //quando gerar a quantidade de combinação por fez, zera pra começar outra
               
            if(auxComb == qtdCasas-1){
                    
                   
                auxComb = 0;
            }
          
            if(somaTotal > somaCustos && somaCustos != 0){
                    
                aux2 = cadaCombinaçao;//armazena a combinação com o menor custo até o momento
                somaTotal = somaCustos;//armazena o melhor custo
                somaCustos = 0;
            }
                somaCustos = 0;
                cadaCombinaçao = "";
        }
   
        escreverArquivo += somaTotal+"\n"+aux2+"\n"+combinaçoesValidas;//falta adicionar o custo do tempo
        return escreverArquivo;
        
        
    }
    //Método que cópia a matriz de custos para um vetor de céluas que guardam cada posição da matriz
    public void copiaCustos(){
        
        int x = 0;
        
        for(int i = 0; i < qtdCasas-1;i++){
            for(int j = i+1; j < qtdCasas;j++){
             
                cel[x] = new Celula(i,j,matrizCustos[i][j]);
                x++;
            }
            
        }
       
    }
    
}
