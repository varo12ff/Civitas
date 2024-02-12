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
public class CasillaCalle extends Casilla{
    private Jugador propietario;
    
    public CasillaCalle(String unNombre, float unPrecioCompra, float unPrecioEdificar, float unPrecioAlquilerBase) {
        super(unNombre, unPrecioCompra, unPrecioEdificar, unPrecioAlquilerBase);
    }
    
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        informe(actual, todos);

        Jugador jugador = todos.get(actual);

        if(!tienePropietario()){
            jugador.puedeComprarCasilla();
        }
        else
            tramitarAlquiler(jugador);
    }
    
    @Override
    public String toString(){
            if(!tienePropietario())
                return(getNombre() + ". Precio: Compra: " + getPrecioCompra() + ", Edificar: " + getPrecioEdificar() + 
                 ", Alquiler base: " + getPrecioAlquilerCompleto() + ", Casas: " + getNumCasas() + ", Hoteles: " + getNumHoteles());
            else
                return(getNombre() + ". Precio: Compra: " + getPrecioCompra() + ", Edificar: " + getPrecioEdificar() + 
                 ", Alquiler base: " + getPrecioAlquilerCompleto() + ", Casas: " + getNumCasas() + ", Hoteles: " + getNumHoteles() +
                        " y su propietario es: " + getPropietario());
        }  
    
    public void actualizaPropietarioPorConversion(JugadorEspeculador jug){
        propietario = (JugadorEspeculador)jug;
    }
        
    
}
