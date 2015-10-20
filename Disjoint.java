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
public class Disjoint {
    
    private int parent[];
    private int rank[];
    private int tam;
    
    
    void MakeSet( int n ){

    parent = new int[n];
    rank = new int[n];
    tam = n;
        
    for( int i = 0 ; i < n ; ++i ){
        
        parent[ i ] = i;      //Inicialmente el padre de cada vértice es el mismo vértice
        rank[i] = 0;
    }
    }
    
    public int Find( int x ){
    if( x == parent[ x ] ){          //Si estoy en la raiz
        return x;                   //Retorno la raiz
    }
    else return Find( parent[ x ] ); //De otro modo busco el padre del vértice actual, hasta llegar a la raiz.
    }

    public void UnionbyRank( int x , int y ){
        
    int xRoot = Find( x );    //Obtengo la raiz de la componente del vértice X
    int yRoot = Find( y );    //Obtengo la raiz de la componente del vértice Y
    if( rank[ xRoot ] > rank[ yRoot ] ){ //en este caso la altura de la componente del vértice X es
                                           //mayor que la altura de la componente del vértice Y.
        parent[ yRoot ] = xRoot;            //el padre de ambas componentes será el de mayor altura
    }
    else{                    //en este caso la altura de la componente del vértice Y es mayor o igual que la de X
        parent[ xRoot ] = yRoot;            //el padre de ambas componentes será el de mayor altura
        if( rank[ xRoot ] == rank[ yRoot ] ){ //si poseen la misma altura
            rank[ yRoot ]++;              //incremento el rango de la nueva raíz
        }
    }
    }
    
    void Union( int x , int y ){
        int xRoot = Find( x );    //Obtengo la raiz de la componente del vértice X
        int yRoot = Find( y );    //Obtengo la raiz de la componente del vértice Y
        parent[ xRoot ] = yRoot;   //Mezclo ambos arboles o conjuntos, actualizando su padre de alguno de ellos como la raiz de otro
    }
    
    public void imprime(){
        
        for(int x = 0;x < tam;x++){
            
            System.out.print(parent[x]);
        }
        System.out.print("\n");
    }
}
