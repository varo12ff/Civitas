/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaTextualCivitas;
//package vistaTextualCivitas;

import GUI.Vista;
import civitas.Casilla;
import civitas.CasillaCalle;
import civitas.CivitasJuego;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import controladorCivitas.Respuesta;
import civitas.OperacionInmobiliaria;
import civitas.Jugador;
import civitas.OperacionInmobiliaria;
import civitas.OperacionJuego;
import controladorCivitas.Respuesta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class VistaTextual implements Vista {
  
    
  private static String separador = "=====================";
  
  private Scanner in;
  
  CivitasJuego juegoModel;
  
  public VistaTextual (CivitasJuego juegoModel) {
    in = new Scanner (System.in);
    this.juegoModel=juegoModel;
  }
  
  
           
 public  void pausa() {
    System.out.print ("\nPulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

    /**
     *
     */
    @Override
public void actualiza(){
               
        Jugador jugadorActual = juegoModel.getJugadorActual();
        
        System.out.print(jugadorActual);
        
        if(juegoModel.finalDelJuego()){
            System.out.print("Esta es la lista de jugadores segun su saldo de mas a menos " + juegoModel.ranking());
        }
    }

    @Override
    public Respuesta comprar(){
        
        ArrayList<String> respuesta = new ArrayList<>();
        respuesta.add(Respuesta.values()[0].name());
        respuesta.add(Respuesta.values()[1].name());
        
        int opcion;
        
        opcion = menu("¿Desea comprar?", respuesta);
        
        if(opcion == 0){
            return Respuesta.values()[0];
        }
        else
            return Respuesta.values()[1];
    }
    @Override
    public OperacionInmobiliaria elegirOperacion(){
        
        ArrayList<String> respuesta = new ArrayList<>();
        respuesta.add(OperacionInmobiliaria.values()[0].name());
        respuesta.add(OperacionInmobiliaria.values()[1].name());
        respuesta.add(OperacionInmobiliaria.values()[2].name());
        
        int opcion;
        
        opcion = menu("¿Cual es la gestion inmobiliaría?", respuesta);
        
        if(opcion == 0){
            return OperacionInmobiliaria.values()[0];
        }
        else if (opcion == 1)
            return OperacionInmobiliaria.values()[1];
        else
            return OperacionInmobiliaria.values()[2];
    }

  @Override
    public int elegirPropiedad(){
        
        ArrayList<CasillaCalle> casillas = juegoModel.getJugadorActual().getPropiedades();
        ArrayList<String> respuesta = new ArrayList<>();
        
        for (int i=0; i < casillas.size(); i++)
            respuesta.add(casillas.get(i).getNombre());
        
        int opcion = -1;
        
        while(opcion < 0 || opcion > casillas.size())
            opcion = menu("Elige propiedad", respuesta);
        
        return opcion;
    }

  @Override
    public void mostrarSiguienteOperacion(OperacionJuego operacion){
        System.out.println(operacion);
        System.out.println(separador);
    }

  @Override
    public void mostrarEventos(){
        while(Diario.getInstance().eventosPendientes())
            System.out.println(Diario.getInstance().leerEvento());

    }
  
  
}


