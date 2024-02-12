/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorCivitas;

import GUI.CivitasView;
import civitas.CivitasJuego;
import civitas.GestionInmobiliaria;
import civitas.OperacionInmobiliaria;
import civitas.OperacionJuego;
import vistaTextualCivitas.VistaTextual;
import static civitas.OperacionInmobiliaria.CONSTRUIR_CASA;
import static civitas.OperacionInmobiliaria.CONSTRUIR_HOTEL;
import static civitas.OperacionInmobiliaria.TERMINAR;
import static civitas.OperacionJuego.COMPRAR;
import static civitas.OperacionJuego.GESTIONAR;
import static civitas.OperacionJuego.PASAR_TURNO;
import static controladorCivitas.Respuesta.SI;
import java.util.ArrayList;

/**
 *
 * @author Alvaro
 */
public class Controlador {
    private CivitasJuego juegoModel;
    private VistaTextual vista;
    private CivitasView vista2;
    
    public Controlador(CivitasJuego jue, VistaTextual vist){
        juegoModel = jue;
        vista = vist;
    }
    
    public Controlador(CivitasJuego jue, CivitasView vist){
        juegoModel = jue;
        vista2 = vist;
    }
    
    public void juego(){
        boolean juegoFinalizado;
        juegoFinalizado = juegoModel.finalDelJuego();
        
        while(!juegoFinalizado){
            vista2.actualiza();
            vista2.pausa();
            OperacionJuego operacion = juegoModel.siguientePaso();
            vista2.mostrarSiguienteOperacion(operacion);
            
            if(operacion != OperacionJuego.PASAR_TURNO)
                vista2.mostrarEventos();
            
            juegoFinalizado = juegoModel.finalDelJuego();
            
            //System.out.println(operacion);
            
            if(!juegoFinalizado){
                if(operacion == COMPRAR){
                    if(vista2.comprar() == SI)
                        juegoModel.comprar();
                    juegoModel.siguientePasoCompletado(operacion);
                }
                else if(operacion == GESTIONAR){
                    OperacionInmobiliaria opInm = vista2.elegirOperacion();
                    int propiedad;
                    if(opInm != TERMINAR){
                        propiedad = vista2.elegirPropiedad();
                        GestionInmobiliaria gestion = new GestionInmobiliaria(opInm, propiedad);
                        
                        if(gestion.getOperacion() == CONSTRUIR_CASA){
                            boolean result = juegoModel.construirCasa(propiedad);
                            if(result){
                                System.out.println("Casa construida con exito.");
                                juegoModel.siguientePasoCompletado(operacion);
                            }
                            else{
                                System.out.println("No se pudo construir la casa.");
                                juegoModel.siguientePasoCompletado(operacion);
                            }
                        }
                        else if(gestion.getOperacion() == CONSTRUIR_HOTEL){
                            boolean result = juegoModel.construirHotel(propiedad);
                            if(result){
                                System.out.println("Hotel construido con exito.");
                                juegoModel.siguientePasoCompletado(operacion);
                            }
                            else{
                                System.out.println("No se pudo construir el Hotel.");
                                juegoModel.siguientePasoCompletado(operacion);
                            }
                        }
                    }
                    else
                        juegoModel.siguientePasoCompletado(operacion);
                }
            }
            else
                vista2.actualiza();
        }
        
    }
}
