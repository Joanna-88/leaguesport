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
import Vista.VEquipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class CEquipo implements ActionListener{
    private VEquipo vista;
    private EquipoDAO dao;
    private JugadorDAO jugadorDAO;

    public CEquipo(VEquipo vista, EquipoDAO dao, JugadorDAO jugadorDAO) {
        this.vista = vista;
        this.dao = dao;
        this.jugadorDAO = jugadorDAO;

        vista.getBtnRegistrar().addActionListener(this);
        vista.getBtnBuscar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getBtnAgregarJugador().addActionListener(this);

        vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              ManejadorPantalla.cerrarequipo(CEquipo.this);
               
            }
        });

        llenarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getBtnRegistrar()) {
            registrar();

        } else if (e.getSource() == vista.getBtnBuscar()) {
            buscar();

        } else if (e.getSource() == vista.getBtnEliminar()) {
            eliminar();

        } else if (e.getSource() == vista.getBtnAgregarJugador()) {
            agregarJugador();
        }
    }

    private void registrar() {
        try {
            String id = vista.getTxtId().getText();
            String nombre = vista.getTxtNombre().getText();
            double presupuesto = Double.parseDouble(
                    vista.getTxtPresupuesto().getText());

            if (dao.crearEquipo(id, nombre, presupuesto)) {
                JOptionPane.showMessageDialog(vista,
                        "Equipo registrado correctamente.");
                limpiarCampos();
                llenarTabla();
            } else {
                JOptionPane.showMessageDialog(vista,
                        "Ya existe un equipo con ese ID.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,
                    "Error en los datos ingresados."+ex.getMessage());
        }
    }

    private void buscar() {
        String id = vista.getTxtId().getText();

        Equipo equipo = dao.buscarPorId(id);

        if (equipo != null) {
            vista.getTxtNombre().setText(equipo.getNombre());
            vista.getTxtPresupuesto().setText(
                    String.valueOf(equipo.getPresupuesto()));
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Equipo no encontrado.");
        }
    }

    private void eliminar() {
        String id = vista.getTxtId().getText();

        if (dao.eliminar(id)) {
            JOptionPane.showMessageDialog(vista,
                    "Equipo eliminado correctamente.");
            limpiarCampos();
            llenarTabla();
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Equipo no encontrado.");
        }
    }

    private void agregarJugador() {
        String idEquipo = vista.getTxtId().getText();
        String nombreJugador = JOptionPane.showInputDialog(
                vista,"Ingrese el nombre del jugador:"
        );

        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            return;
        }

        Jugador jugador = jugadorDAO.Actualizar(nombreJugador);

        if (jugador == null) {
            JOptionPane.showMessageDialog(vista,"Jugador no encontrado.");
            return;
        }

        if (dao.agregarJugador(idEquipo, jugador)) {
            JOptionPane.showMessageDialog(vista,"Jugador agregado al equipo.");
            llenarTabla();
        } else {
            JOptionPane.showMessageDialog(vista,"Equipo no encontrado.");
        }
    }

    private void llenarTabla() {
        DefaultTableModel modelo =(DefaultTableModel) vista.getTblEquipo().getModel();

        modelo.setRowCount(0);

        List<Equipo> lista = dao.consultarTodos();
     Object[] fila = new Object[4]; 
        for (Equipo equipo : lista) {
             
               fila[0]= equipo.getId();
               fila[1] =equipo.getNombre();
               fila[2]= equipo.getJugadoes().size();
               fila[3] =equipo.getPresupuesto();
           
            modelo.addRow(fila);
        }
    }

    private void limpiarCampos() {
        vista.getTxtId().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtPresupuesto().setText("");
        vista.getTxtId().requestFocus();
    }

    public void finalizar() {
        vista.dispose();
        vista = null;
        dao = null;
        jugadorDAO = null;
        System.out.println("Controlador de equipos finalizado.");
    }
}
