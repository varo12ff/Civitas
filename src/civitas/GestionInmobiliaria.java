/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author Alvaro
 */
public class GestionInmobiliaria {
    private OperacionInmobiliaria operacion;
    private int id;
    
    
    public GestionInmobiliaria(OperacionInmobiliaria op, int ip){
        operacion = op;
        id = ip;
    }
    
    public OperacionInmobiliaria getOperacion(){
        return operacion;
    }
    
    public int getPropiedad(){
        return id;
    }
}
