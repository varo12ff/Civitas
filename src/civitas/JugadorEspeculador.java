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
public class JugadorEspeculador extends Jugador {
    static int factorEspeculador = 2;
    protected static int CasasMax = 4 * factorEspeculador;
    protected static int HotelesMax = 4 * factorEspeculador;
    ArrayList<CasillaCalle> propiedades = new ArrayList<CasillaCalle>();
    
    protected JugadorEspeculador(Jugador otro) {
        super(otro);
        actualizaPropiedadesPorConversion();
    } 
    
    boolean paga(float cantidad){
        return modificarSaldo((cantidad/factorEspeculador) * -1);
    }
    
    private void actualizaPropiedadesPorConversion(){
        for(int i = 0; i < propiedades.size(); i++)
            propiedades.get(i).actualizaPropietarioPorConversion(this);
    }
    
    public int getCasasMax(){
        return CasasMax;
    }
    
    public int getHotelesMax(){
        return HotelesMax;
    }
    
    @Override
    public String toString(){
        return("El jugador especulador: " + getNombre() + " se encuentra en la casilla: " + getCasillaActual() + 
                " con el saldo disponible de: " + getSaldo() + ".");
    }
}
