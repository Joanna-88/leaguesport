/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.ManejadorPantalla;
import Vista.VMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Acer
 */
public class CMenu implements ActionListener{
     
    private VMenu vista;

    public CMenu(VMenu vista) {
        this.vista = vista;

        vista.getBtnJugadores().addActionListener(this);
        vista.getBtnEquipos().addActionListener(this);
        vista.getBtnGestionEuipos().addActionListener(this);
        vista.getBtnGestionarJugador().addActionListener(this);
        this.vista.addWindowListener(new java.awt.event.WindowAdapter() {
           
            @Override
            public void windowClosing( java.awt.event.WindowEvent e) {
                //ManejadorPantallas.cerrarmenu(CMenu.this);
                ManejadorPantalla.cerrarmenu(vista);
            }
            
            
             
         });
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getBtnJugadores()) {
            ManejadorPantalla.abrirjugador();
        }

        if (e.getSource() == vista.getBtnEquipos()) {
            ManejadorPantalla.abrirequipo();
        }

        if (e.getSource() == vista.getBtnGestionEuipos()) {
            ManejadorPantalla.abrirgestionarequipo();
        }
        if (e.getSource()==vista.getBtnGestionarJugador()) {
            ManejadorPantalla.abrirgestionarjugador();
            
        }
    }

    public void finalizar() {
        vista.dispose();
        vista = null;

        System.out.println("Controlador del menú finalizado.");
    }
}
