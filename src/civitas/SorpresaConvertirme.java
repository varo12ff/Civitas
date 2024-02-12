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
public class SorpresaConvertirme extends Sorpresa{
    public SorpresaConvertirme(String texto){
        super(texto);
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador>todos){
        todos.get(actual).convertir();
        informe(actual, todos);
    }
}
