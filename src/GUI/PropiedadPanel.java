/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.CasillaCalle;

/**
 *
 * @author Alvaro
 */
public class PropiedadPanel extends javax.swing.JPanel {
    
    CasillaCalle tituloPropiedad;

    /**
     * Creates new form PropiedadPanel
     */
    public PropiedadPanel() {
        initComponents();
    }
    
    public void setPropiedad(CasillaCalle titulo){
        tituloPropiedad = titulo;
        
        this.titulo.setText(tituloPropiedad.getNombre());
        this.casas.setText(Integer.toString(tituloPropiedad.getNumCasas()));
        this.hoteles.setText(Integer.toString(tituloPropiedad.getNumHoteles()));
        
        repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        titulo = new javax.swing.JTextField();
        casas = new javax.swing.JTextField();
        hoteles = new javax.swing.JTextField();

        jLabel1.setText("Título:");
        add(jLabel1);

        jLabel2.setText("Nº Casas: ");
        add(jLabel2);

        jLabel3.setText("Nº Hoteles");
        add(jLabel3);

        titulo.setEditable(false);
        titulo.setText("jTextField1");
        add(titulo);

        casas.setEditable(false);
        casas.setText("jTextField2");
        add(casas);

        hoteles.setEditable(false);
        hoteles.setText("jTextField3");
        add(hoteles);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField casas;
    private javax.swing.JTextField hoteles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField titulo;
    // End of variables declaration//GEN-END:variables
}
