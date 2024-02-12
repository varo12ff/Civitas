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
abstract class Sorpresa {
    private String texto;
    int valor;
    private MazoSorpresas mazo;
    
    Sorpresa(String text){
        texto = text;
    }
    
    Sorpresa(String text, int val){
        texto = text;
        valor = val;
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador>todos){
        informe(actual, todos);
    }
    void informe(int actual, ArrayList<Jugador>todos){
        Diario.getInstance().ocurreEvento("Se ha aplicado una carta sorpresa a: " + todos.get(actual).getNombre());
    }
    
    @Override
    public String toString(){
        return texto;
    }
    
}
