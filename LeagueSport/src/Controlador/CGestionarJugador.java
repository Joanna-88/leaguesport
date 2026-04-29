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
import Vista.VGestionarJugador;
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
public class CGestionarJugador implements ActionListener{
    private VGestionarJugador vista;
    private EquipoDAO equipoDAO;
    private JugadorDAO jugadorDAO;

    public CGestionarJugador(VGestionarJugador vista,EquipoDAO equipoDAO,JugadorDAO jugadorDAO) {
        this.vista = vista;
        this.equipoDAO = equipoDAO;
        this.jugadorDAO = jugadorDAO;

        vista.getBtnAsignar().addActionListener(this);
        vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              ManejadorPantalla.cerragestionarjugador(CGestionarJugador.this);
               
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
        String idEquipo = vista.getTxtId().getText().trim();
        String nombreJugador = vista.getTxtNombreJugador().getText().trim();

        Equipo equipo = equipoDAO.buscarPorId(idEquipo);
        Jugador jugador = jugadorDAO.buscarPorNombre(nombreJugador);

        if (equipo == null) {
            JOptionPane.showMessageDialog(vista,
                    "Equipo no encontrado.");
            return;
        }

        if (jugador == null) {
            JOptionPane.showMessageDialog(vista,
                    "Jugador no encontrado.");
            return;
        }

        equipo.agregarJugador(jugador);
        actualizarListas(equipo);

        JOptionPane.showMessageDialog(vista,
                "Jugador asignado correctamente.");
    }

    private void actualizarListas(Equipo equipo) {
        DefaultListModel<String> modeloTitulares =
                new DefaultListModel<>();

        DefaultListModel<String> modeloBanco =
                new DefaultListModel<>();

        for (Jugador j : equipo.getTitulares()) {
            modeloTitulares.addElement(j.getNombre());
        }

        for (Jugador j : equipo.getBanco()) {
            modeloBanco.addElement(j.getNombre());
        }

        vista.getLstTitulares().setModel(modeloTitulares);
        vista.getLstBanco().setModel(modeloBanco);
    }

    public void finalizar() {
        vista.dispose();
        vista = null;
        System.out.println("Controlador de gestión de jugadores finalizado.");
    }
    
}
