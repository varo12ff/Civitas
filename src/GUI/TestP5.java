/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.CivitasJuego;
import controladorCivitas.Controlador;
import java.awt.Frame;
import java.util.ArrayList;

/**
 *
 * @author Alvaro
 */
public class TestP5 {

    private static Frame vista;
    public static void main(String[] args) {
        CivitasView v = new CivitasView();
        VistaDado.createInstance(v);
        CapturaNombres captura = new CapturaNombres(v, true);
        ArrayList<String> nombres = new ArrayList<>();
        
        nombres = captura.getNombres();
        
        CivitasJuego juego = new CivitasJuego(nombres);
        
        Controlador controlador = new Controlador(juego, v);
        
        v.setCivitasJuego(juego);
        
        controlador.juego();
    }
}
