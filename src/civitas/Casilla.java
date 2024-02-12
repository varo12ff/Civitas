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
public class Casilla {
    private String Nombre;
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    private static float FACTORALQUILERCALLE = 1.0f;
    private static float FACTORALQUILERCASA = 1.0f;
    private static float FACTORALQUILERHOTEL = 4.0f;
    MazoSorpresas mazo;
    private Jugador propietario;
    Sorpresa sorpresa;
    
    Casilla(String unNombre, float unPrecioCompra, float unPrecioEdificar, float unPrecioAlquilerBase){
        init();
        Nombre = unNombre;
        precioCompra = unPrecioCompra;
        precioEdificar = unPrecioEdificar;
        precioBaseAlquiler = unPrecioAlquilerBase;
        numCasas = 0;
        numHoteles = 0;
    }
    
    Casilla(String nom){
        init();
        Nombre = nom;
    }
    
    Casilla(String nom, MazoSorpresas maz){
        init();
        Nombre = nom;
        mazo = maz;
    }
    
    public int cantidadCasasHoteles(){
        return numCasas + numHoteles;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public float getPrecioCompra(){
        return precioCompra;
    }
    float getPrecioEdificar(){
        return precioEdificar;
    }
    
    public int getNumCasas(){
        return numCasas;
    }
    
    public int getNumHoteles(){
        return numHoteles;
    }
    
    String getPropietario(){
        return propietario.getNombre();
    }
    
    public float getPrecioAlquilerCompleto(){
        float alquilerFinal;
        
        alquilerFinal = precioBaseAlquiler * (FACTORALQUILERCASA + numCasas + numHoteles * FACTORALQUILERHOTEL);
        
        return alquilerFinal;
    }
    
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        informe(actual, todos);
    }
    
    Boolean comprar(Jugador jugador){
        propietario = jugador;

        return propietario.paga(getPrecioCompra());
    }
    
    boolean construirCasa( Jugador jugador){
        jugador.paga(precioEdificar);
        numCasas++;
        
        return true;
    }
    
    boolean construirHotel( Jugador jugador){
        propietario.paga(precioEdificar);
        numHoteles++;
        
        return true;
    }
    
    void init(){
        Nombre = "";
        numCasas = 0;
        numHoteles = 0;
        precioBaseAlquiler = 0;
        precioCompra = 0;
        precioEdificar = 0;
    }

    void informe(int actual, ArrayList<Jugador>todos){
        Diario.getInstance().ocurreEvento("El jugador: " + todos.get(actual).getNombre() + " ha caido en la casilla " + toString());
    }
    
    public boolean esEsteElPropietario(Jugador jugador){
        return jugador == propietario;
    }
    
    boolean derruirCasa(int num, Jugador jugador){
        if(esEsteElPropietario(jugador) && numCasas >= num){
            numCasas -= num;
            return true;
        }
        else
            return false;
    }
    
    public boolean tienePropietario(){
        return propietario != null;
    }
    
    public void tramitarAlquiler(Jugador jugador){
        if(tienePropietario() && !esEsteElPropietario(jugador)){
            jugador.pagaAlquiler(getPrecioAlquilerCompleto());
            propietario.recibe(getPrecioAlquilerCompleto());
        }
        
    }
    
    @Override
    public String toString(){
        return ("Casilla de descanso");
    }
}

