/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Alvaro
 */
public class MazoSorpresas {
    private ArrayList<Sorpresa> sorpresa;
    private boolean barajada;
    private int usadas;
    private boolean debug;
    Diario diario = Diario.getInstance();
    
    private void init(){
        barajada = false;
        usadas = 0;
        sorpresa = new ArrayList<>();
    }
    
    MazoSorpresas(boolean d){
        debug = d;
        init();
        if(debug == true){
            diario.ocurreEvento("Debug Mode: True");
        }
    }
    
    MazoSorpresas(){
        init();
        debug = false;
    }
    
    void alMazo(Sorpresa s){
        if(barajada == false){
            sorpresa.add(s);
        }
    }
    
    Sorpresa siguiente(){
        if(barajada == false || usadas == sorpresa.size()){
            if (debug == false){
                Collections.shuffle(sorpresa);
            }
            usadas = 0;
            barajada = true;
        }
        Sorpresa nueva = sorpresa.remove(usadas);
        sorpresa.add(nueva);
        usadas++;
        
        return nueva;
    }
}
