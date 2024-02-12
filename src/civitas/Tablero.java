/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author Alvaro
 */
public class Tablero {
    private ArrayList<Casilla> casillas;
    private boolean porSalida;
    
    Tablero(){
        casillas = new ArrayList<Casilla>();
        casillas.add(new Casilla("Salida", 0.0f, 0.0f, 0.0f));
        porSalida = false;
    }
    
    private boolean correcto(int numCasilla){
        if(numCasilla >= 0 && numCasilla < casillas.size())
            return true;
        else
            return false;
    }
    
    boolean computarPasoPorSalida(){
        boolean paso = porSalida;
        porSalida = false;
        
        return paso;
    }
    
    void añadeCasilla(Casilla casilla){
        casillas.add(casilla);
    }
    
    public Casilla getCasilla(int numCasilla){
        if(correcto(numCasilla))
            return casillas.get(numCasilla);
        else
            return null;
    }
    
    int nuevaPosicion(int actual, int tirada){
        int nuevaCasilla;
        
        nuevaCasilla = (actual + tirada) % casillas.size();
        
        if(nuevaCasilla != actual + tirada)
            porSalida = true;
        
        return nuevaCasilla;
    }
}
