/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.Random;

/**
 *
 * @author Alvaro
 */
public class Dado {
    private Random random;
    private static int ultimoResultado;
    private static boolean debug;
    
    private final static Dado instance = new Dado();
    
    private Dado(){
        random = new Random();
        ultimoResultado = 0;
        debug = false;
    }
    
    static Dado getInstance(){
        return instance;
    }
    
    int tirar(){
        if (debug == false){
           ultimoResultado = (int)(random.nextDouble() * 6 + 1);
        }
        else{
            ultimoResultado = 1;
        }
        
        return ultimoResultado;
    }
    
    int quienEmpieza(int n){
        
        int v[];
        v = new int[n];
        
        int jugador=0;
        
        for(int i=0; i<n; i++){
            jugador = (int)(random.nextDouble() * n + 1);
        }
        
        return jugador;
    }
    
    void setDebug(boolean d){
        debug = d;
        Diario.getInstance().ocurreEvento("Debug Mode: " + d);
    }

    int getUltimoResultado(){
        return ultimoResultado;
    }
}
