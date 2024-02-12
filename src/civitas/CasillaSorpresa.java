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
public class CasillaSorpresa extends Casilla{
    
    public CasillaSorpresa(String nom, MazoSorpresas maz) {
        super(nom, maz);
    }
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        sorpresa = mazo.siguiente();
        informe(actual, todos);
        sorpresa.aplicarAJugador(actual, todos);
    }
    
    @Override
    public String toString(){
        return("Has caido en una casilla sorpresa");
    }
    
}
