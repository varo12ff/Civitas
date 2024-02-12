
package civitas;
import java.util.ArrayList;
import java.util.Collections;
 
public class CivitasJuego {
    private int indiceJugadorActual;
    private Tablero tablero;
    private GestorEstados gestor;
    private EstadoJuego estado;
    private MazoSorpresas mazo;
    private ArrayList<Jugador> jugadores;
    
    public CivitasJuego(ArrayList<String> nombres){
        jugadores = new ArrayList<Jugador>();
        
        for(int i=0; i<nombres.size(); i++){
            Jugador aux = new Jugador(nombres.get(i));
            jugadores.add(aux);
        }
        
        gestor = new GestorEstados();
        estado = gestor.estadoInicial();

        Dado.getInstance().setDebug(false);
        indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
        
        mazo = new MazoSorpresas(false);
        inicializaMazoSorpresas();

        tablero = new Tablero();
        inicializarTablero(mazo);
    }
    private void avanzaJugador(){
        Jugador jugadorActual = getJugadorActual();

        int posicionActual = jugadorActual.getCasillaActual();

        int tirada = Dado.getInstance().tirar();

        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);

        Casilla casilla = tablero.getCasilla(posicionNueva);

        ContabilizarPasosPorSalida(jugadorActual);

        jugadorActual.moverACasilla(posicionNueva);

        casilla.recibeJugador(indiceJugadorActual, jugadores);
    }
    
    public boolean comprar(){
        int numCasillaActual = jugadores.get(indiceJugadorActual).getCasillaActual();
        CasillaCalle casilla = (CasillaCalle) tablero.getCasilla(numCasillaActual);
        boolean res = jugadores.get(indiceJugadorActual).comprar(casilla);
        
        return res;
    }
    
    public Jugador getJugadorActual(){
        return jugadores.get(indiceJugadorActual);
    }
    
    public int getIndiceJugadorActual(){
        return indiceJugadorActual;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    private void ContabilizarPasosPorSalida(Jugador jugadorActual){
        while(tablero.computarPasoPorSalida()){
            jugadorActual.pasaPorSalida();
        }
    }
    
    private void pasarTurno(){
        if (indiceJugadorActual!=jugadores.size()-1)
            indiceJugadorActual++;
        else
            indiceJugadorActual=0;
    }
    
    public void siguientePasoCompletado(OperacionJuego operacion){
        Jugador actual = getJugadorActual();
        
        estado = gestor.siguienteEstado(actual, estado, operacion);
    }
    
    public OperacionJuego siguientePaso(){
        Jugador jugadorActual = getJugadorActual();

        OperacionJuego operacion = gestor.siguienteOperacion(jugadorActual, estado);

        if(operacion == OperacionJuego.PASAR_TURNO){
            pasarTurno();

            siguientePasoCompletado(operacion);
        }
        else if(operacion == OperacionJuego.AVANZAR){
            avanzaJugador();
            siguientePasoCompletado(operacion);
        }

        return operacion;
    }

    
        
    public boolean construirCasa(int ip){
        return getJugadorActual().construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
        return getJugadorActual().construirHotel(ip);
    }
    
    public boolean finalDelJuego(){
        boolean bancarrota = false;
        
        for(int i=0; i< jugadores.size() && !bancarrota; i++){
            if(jugadores.get(i).enBancarrota()){
                bancarrota = true;
            }
        }
        
        return bancarrota;
    }
    
    public ArrayList<Jugador> ranking(){
        int x;
        ArrayList<Jugador> ranking = jugadores;
        
        for(int i=0; i<jugadores.size(); i++){
            x = jugadores.get(i).compareTo(jugadores.get(i+1));
            if(x < 0){
                Collections.swap(ranking, i, i+1);
            }
        }
        
        return ranking;
    }
    
    
    
    private void inicializaMazoSorpresas(){
        Sorpresa s1 = new SorpresaPAGARCOBRAR("Te encuentras un billete de 100$.\nRECIBES 100", 100);
        mazo.alMazo(s1);

        Sorpresa s2 = new SorpresaPAGARCOBRAR("Visitas el auditorio Manuel de Falla.\nPPAGAS150", -150);
        mazo.alMazo(s2);

        Sorpresa s3 = new SorpresaPAGARCOBRAR("Sales un sabado a Mae West.\nPAGAS 50", -50);
        mazo.alMazo(s3);

        Sorpresa s4 = new SorpresaPAGARCOBRAR("Trabajas de RRPP un mes entero.\nRECIBES 200", 200);
        mazo.alMazo(s4);

        Sorpresa s5 = new SorpresaPAGARCOBRAR("Visitas la Capilla Real.\nPagas 50", -50);
        mazo.alMazo(s5);

        Sorpresa s6 = new SorpresaPAGARCOBRAR("Ganas un premio en el bingo.\nRECIBES 100", 100);
        mazo.alMazo(s6);

        Sorpresa s7 = new SorpresaPORCASAHOTEL("Tu casero te adelanta el alquiler.\nPAGAS 40 POR CADA EDIFICIO", -40);
        mazo.alMazo(s7);

        Sorpresa s8 = new SorpresaPORCASAHOTEL("Limpias las escaleras de los edificios.\nRECIBES 20 por cada casa y hotel", 20);
        mazo.alMazo(s8);

        Sorpresa s9 = new SorpresaPORCASAHOTEL("El casero te devuelve la fianza del piso.\nRECIBES 40 por casa y hotel", 40);
        mazo.alMazo(s9);

        Sorpresa s10 = new SorpresaPORCASAHOTEL("Invitas a cenar a tus vecinos de arriba y abajo.\nPagas 10 por cada casa y hotel", -10);
        mazo.alMazo(s10);
        
        Sorpresa s11 = new SorpresaConvertirme("Te has convertido en especulador");
        mazo.alMazo(s11);
        

    }
    
    
    private void inicializarTablero(MazoSorpresas mazo){
        Casilla c1 = new CasillaCalle("Paseo de la Bomba", 15f, 45f, 60f);
        tablero.añadeCasilla(c1);
        Casilla c2 = new CasillaCalle("Carrera de la Virgen", 30f, 60f, 120f);
        tablero.añadeCasilla(c2);
        Casilla s1 = new CasillaSorpresa("Biblioteca del Salon", mazo);
        tablero.añadeCasilla(s1);
        
        Casilla c3 = new CasillaCalle("Cuesta de Gomérez", 50f, 100f, 180f);
        tablero.añadeCasilla(c3);
        Casilla c4 = new CasillaCalle("Calle oficios", 50f, 100f, 180f);
        tablero.añadeCasilla(c4);
        Casilla c5 = new CasillaCalle("Calle Cv. Encina", 70f, 140f, 240f);
        tablero.añadeCasilla(c5);
        Casilla s2 = new CasillaSorpresa("Centro Comercial Nevada", mazo);
        tablero.añadeCasilla(s2);

        Casilla c6 = new CasillaCalle("Calle Recogidas", 110f, 220f, 300f);
        tablero.añadeCasilla(c6);
        Casilla c7 = new CasillaCalle("Calle San Antón", 140f, 300f, 420f);
        tablero.añadeCasilla(c7);
        Casilla parking = new Casilla("Parking");
        tablero.añadeCasilla(parking);

        Casilla c8 = new CasillaCalle("Calle Pavaneras", 140f, 260f, 400f);
        tablero.añadeCasilla(c8);
        Casilla c9 = new CasillaCalle("Calle Reyes Catolicos", 170f, 340f, 500f);
        tablero.añadeCasilla(c9);
        Casilla s3 = new CasillaSorpresa("Plaza Isabel La Católica", mazo);
        tablero.añadeCasilla(s3);

        Casilla c10 = new CasillaCalle("Camino del Sacromonte", 250f, 500f, 750f);
        tablero.añadeCasilla(c10);
        Casilla c11 = new CasillaCalle("Acera del Darro", 260f, 520f, 780f);
        tablero.añadeCasilla(c11);
        Casilla c12 = new CasillaCalle("Calle de las Teterias", 320f, 600f, 950f);
        tablero.añadeCasilla(c12);
        Casilla s4 = new CasillaSorpresa("Plaza Nueva", mazo);
        tablero.añadeCasilla(s4);
        Casilla c13 = new CasillaCalle("Paseo de los Tristes", 500f, 900f, 1400f);
        tablero.añadeCasilla(c13);
        Casilla c14 = new CasillaCalle("Gran Via de Colón", 600f, 1250f, 1700f);
        tablero.añadeCasilla(c14);
    }
    
}
