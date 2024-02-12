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
public class Jugador implements Comparable<Jugador> {
    protected static int CasasMax = 4;
    protected static int CasasPorHotel = 4;
    protected static int HotelesMax = 4;
    protected static float pasoPorSalida = 1000.0f;
    private int casillaActual;
    private String nombre;
    private boolean puedeComprar;
    private float saldo;
    private static float saldoInicial = 7500.0f;
    ArrayList<CasillaCalle> propiedades = new ArrayList<CasillaCalle>();
    private boolean especulador = false;
    
    public Jugador(String nom){
        nombre = nom;
        saldo = saldoInicial;
        puedeComprar = false;
        casillaActual = 0;
    }
    
    protected Jugador(Jugador otro){
        casillaActual = otro.getCasillaActual();
        nombre = otro.getNombre();
        puedeComprar = otro.getPuedeComprar();
        saldo = otro.getSaldo();
    }
    
    boolean construirHotel(int ip){
        boolean result = false;
        
        if(existeLaPropiedad(ip)){
            Casilla propiedad = propiedades.get(ip);
            boolean puedoEdificarHotel = puedeEdificarHotel(propiedad);
            puedoEdificarHotel = false;
            float precio = propiedad.getPrecioEdificar();
            
            if(puedoGastar(precio))
                if(propiedad.getNumHoteles() < getHotelesMax() && propiedad.getNumCasas() >= getCasasPorHoteles())
                    puedoEdificarHotel = true;
            
            if(puedoEdificarHotel){
                result = propiedad.construirHotel(this);
                propiedad.derruirCasa(CasasPorHotel, this);
                Diario.getInstance().ocurreEvento("El jugador " + " construye hotel en la propiedad " + ip);
            }
        }
        
        return result;
    }
    
    boolean construirCasa(int ip){
        boolean result = false;
        boolean existe = existeLaPropiedad(ip);
        if(existe){
            CasillaCalle propiedad = propiedades.get(ip);
            boolean puedeEdificar = puedeEdificarCasa(propiedad);
            float precioEdificar = propiedad.getPrecioEdificar();
            
            if(puedoGastar(precioEdificar) && propiedad.getNumCasas() < getCasasMax())
                puedeEdificar = true;
            
            if(puedeEdificar){
                result = propiedad.construirCasa(this);
                Diario.getInstance().ocurreEvento("El jugador " + " construye una casa en la propiedad " + ip);
            }
                
        }
        
        return result;
    }
    
    int cantidadCasaHoteles(){
        return propiedades.get(this.casillaActual).cantidadCasasHoteles();
    }
    
    private int getCasasMax(){
        return CasasMax;
    }
    
    private int getHotelesMax(){
        return HotelesMax;
    }
    
    int getCasasPorHoteles(){
        return CasasPorHotel;
    }
    
    public int getCasillaActual(){
        return casillaActual;
    }
    
    boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public ArrayList<CasillaCalle> getPropiedades(){
        return propiedades;
    }
    
    public float getSaldo(){
        return saldo;
    }
    
    public boolean getEspeculador(){
        return especulador;
    }
    
    boolean enBancarrota(){
        return (saldo < 0);
    }
    
    boolean existeLaPropiedad(int ip){
        return propiedades.size() > ip;
    }
    
    float getPremioPasoPorSalida(){
        return pasoPorSalida;
    }
    
    boolean modificarSaldo(float cantidad){
        saldo += cantidad;
        Diario.getInstance().ocurreEvento("Se ha modificado el saldo en" + cantidad + " ahora el saldo es: " + saldo);
        
        return true;
    }
    
    boolean moverACasilla(int numCasilla){
        casillaActual = numCasilla;
        puedeComprar = false;
        Diario.getInstance().ocurreEvento("Se ha desplazado al jugador a la casilla: " + numCasilla);
        
        return true;
    }
    
    boolean paga(float cantidad){
        return modificarSaldo(cantidad * -1);
    }
    
    boolean pagaAlquiler(float cantidad){
        return paga(cantidad);
    }
    
    boolean pasaPorSalida(){
        recibe(pasoPorSalida);
        Diario.getInstance().ocurreEvento("El jugador ha pasado por salida y recibido el premio por ello de: " + pasoPorSalida);
        
        return true;
    }
    
    boolean puedeComprarCasilla(){
        boolean puede = puedeComprar;
        
        puedeComprar = true;
        
        return puede;
    }
    
    boolean recibe(float cantidad){
        return modificarSaldo(cantidad);
    }
    
    boolean puedeEdificarCasa(Casilla propiedad){
        return (propiedad.getPrecioEdificar() <= saldo);
    }
    
    boolean puedeEdificarHotel(Casilla propiedad){
        return (propiedad.getPrecioEdificar() <= saldo && propiedad.getNumCasas() == CasasMax);
    }
    
    boolean puedoGastar(float precio){
        return saldo >= precio;
    }
    
    boolean tieneAlgoQueGestionar(){
        return propiedades.size() > 0;
    }
    
    boolean comprar(CasillaCalle titulo){
        boolean result = false;

        if(puedeComprar){
            float precio = titulo.getPrecioCompra();

            if(puedoGastar(precio)){
                result = titulo.comprar(this);

                propiedades.add(titulo);

                Diario.getInstance().ocurreEvento("El jugador "+ nombre + " compra la propiedad" + titulo.getNombre());
                puedeComprar = true;

            }else{
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " no tiene saldo para comprar la propiedad " + titulo.getNombre());

            }
        }

        return result;
    }
    
    protected Jugador convertir(){
        JugadorEspeculador jugadorEspeculador = new JugadorEspeculador(this);
        especulador = true;
        
        return jugadorEspeculador;
    }
    
    @Override
    public int compareTo(Jugador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        return("El jugador: " + nombre + " se encuentra en la casilla: " + casillaActual + 
                " con el saldo disponible de: " + saldo + ".");
    }
    
}
