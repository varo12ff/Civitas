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
public class SorpresaPORCASAHOTEL extends Sorpresa{
    
    public SorpresaPORCASAHOTEL(String text, int val) {
        super(text, val);
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador>todos){
        todos.get(actual).modificarSaldo(valor * todos.get(actual).getCasasPorHoteles());
        informe(actual, todos);
    }
    
}
