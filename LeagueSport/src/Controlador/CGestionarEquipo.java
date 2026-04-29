/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Equipo;
import Modelo.EquipoDAO;
import Modelo.Jugador;
import Modelo.JugadorDAO;
import Vista.ManejadorPantalla;
import Vista.VGestionarEquipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


/**
 *
 * @author Acer
 */
public class CGestionarEquipo implements  ActionListener{
    private VGestionarEquipo vista;
    private EquipoDAO equipoDAO;
    private JugadorDAO jugadorDAO;

    public CGestionarEquipo(VGestionarEquipo vista,EquipoDAO equipoDAO, JugadorDAO jugadorDAO) {

        this.vista = vista;
        this.equipoDAO = equipoDAO;
        this.jugadorDAO = jugadorDAO;

 this.vista.getBtnAsignar().addActionListener(this);
vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              ManejadorPantalla.cerrargestionarequipo(CGestionarEquipo.this);
               
            }
        });
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnAsignar()) {
            asignarJugador();
        }
    }

    private void asignarJugador() {
        String idEquipo=vista.getTxtEquipos().getText();
        String nombreJugador= vista.getTxtJugadores().getText();
        if (idEquipo.isEmpty() || nombreJugador.isEmpty()) {
        JOptionPane.showMessageDialog(vista,
                "Debe ingresar el ID del equipo y el nombre del jugador.");
        return;
    }

        Jugador jugador = jugadorDAO.buscarPorNombre(nombreJugador);
        
        if (jugador==null) {
            JOptionPane.showMessageDialog(vista, "jugador no encontrado");
            return;
        }
     boolean agregado= equipoDAO.agregarJugador(idEquipo, jugador);
        if (!agregado) {
            JOptionPane.showMessageDialog(vista, "no se puede asignar el jugador del equipo");
            return;
        }
    Equipo equipo =equipoDAO.buscarPorId(idEquipo);
        actualizarListas(equipo);
        JOptionPane.showMessageDialog(vista, "jugador asignado correctamente");
}

   

    private void actualizarListas(Equipo equipo) {

        DefaultListModel<String> modeloTitulares =new DefaultListModel<>();
        DefaultListModel<String> modeloBanco =new DefaultListModel<>();

        for (Jugador j : equipo.getTitulares()) {
            modeloTitulares.addElement(j.getNombre());
        }
        for (Jugador j : equipo.getBanco()) {
            modeloBanco.addElement(j.getNombre());
            
            
            
        }

        vista.getLstTitulares().setModel(modeloTitulares);
        vista.getListBanco().setModel(modeloBanco);

       

        
    }
     public void finalizar() {
        vista.dispose();
        vista = null;
       
       
        System.out.println("Controlador de equipos finalizado.");
}
}
