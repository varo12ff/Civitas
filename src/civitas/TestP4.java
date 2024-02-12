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
public class TestP4 {
    public static void main(String[] args) {
        Jugador jug = new Jugador("Álvaro");
        Jugador otro;
        otro = jug.convertir();
        
        System.out.println(((JugadorEspeculador)otro).getCasasMax());
    }
}
