/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Jugador;
import Modelo.JugadorDAO;
import Vista.ManejadorPantalla;
import Vista.VJugador;
import Vista.VMenu;
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
public class CJugador implements ActionListener {

    private VJugador vista;
    private JugadorDAO dao;

    public CJugador(VJugador vista, JugadorDAO dao) {
        this.vista = vista;
        this.dao = dao;

        vista.getBtnRegistrar().addActionListener(this);
        vista.getBtnBuscar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getBtnSalario().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
        vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              ManejadorPantalla.cerrarjugador(CJugador.this);
               
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

        }  else if (e.getSource() == vista.getBtnSalario()) {
            consultarSalario();
        }else if(e.getSource()==vista.getBtnActualizar()){
            actualizar();
    }
        
    }

    private void registrar() {
        try {
            String nombre = vista.getTxtNombre().getText();
            String rol = vista.getTxtRol().getText();
            int habilidad = Integer.parseInt(vista.getTxtHabilidad().getText());
            int salario = Integer.parseInt(vista.getTxtSalario().getText());

            if (dao.crearjugador(nombre, rol, habilidad, salario)) {
                JOptionPane.showMessageDialog(vista,
                        "Jugador registrado correctamente.");
                limpiarCampos();
                llenarTabla();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Error en los datos ingresados.");
        }
    }

    private void buscar() {
        String nombre = vista.getTxtNombre().getText();

        Jugador jugador = dao.Actualizar(nombre);

        if (jugador != null) {
            vista.getTxtNombre().setText(jugador.getNombre());
            vista.getTxtRol().setText(jugador.getRol());
            vista.getTxtHabilidad().setText(
                    String.valueOf(jugador.getHabilidad()));
            vista.getTxtSalario().setText(
                    String.valueOf(jugador.getSalario()));
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Jugador no encontrado.");
        }
    }

    private void eliminar() {
        String nombre = vista.getTxtNombre().getText();

        if (dao.elimiminar(nombre)) {
            JOptionPane.showMessageDialog(vista,
                    "Jugador eliminado correctamente.");
            limpiarCampos();
            llenarTabla();
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Jugador no encontrado.");
        }
    }

    
    private void consultarSalario() {
        String nombre = vista.getTxtNombre().getText();

        double salario = dao.salario(nombre);

        if (salario != -1) {
            JOptionPane.showMessageDialog(vista,
                    "El salario del jugador es: $" + salario);
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Jugador no encontrado.");
        }
    }
    private void actualizar() {
         try {
        String nombre = vista.getTxtNombre().getText();
        String rol = vista.getTxtRol().getText();
        int habilidad = Integer.parseInt(vista.getTxtHabilidad().getText());
        int salario = Integer.parseInt(vista.getTxtSalario().getText());

        Jugador actualizado = new Jugador(nombre, rol, habilidad, salario);

        if (dao.actualizarJugador(actualizado)) {
            JOptionPane.showMessageDialog(vista,"Jugador actualizado correctamente");

            llenarTabla(); 
            limpiarCampos();

        } else {
            JOptionPane.showMessageDialog(vista,"Jugador no encontrado");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(vista,"Error en los datos ingresados");
    }
    }

    public void llenarTabla() {
        DefaultTableModel modelotabla=(DefaultTableModel) vista.getTblJugador().getModel();

        modelotabla.setRowCount(0);

        List<Jugador> lista = dao.consultartodos();

        for (Jugador jugador : lista) {
            Object[] fila = {
                jugador.getNombre(),
                jugador.getRol(),
                jugador.getHabilidad(),
                jugador.getSalario()
            };
            modelotabla.addRow(fila);
        }
    }

    private void limpiarCampos() {
        vista.getTxtNombre().setText("");
        vista.getTxtRol().setText("");
        vista.getTxtHabilidad().setText("");
        vista.getTxtSalario().setText("");
        vista.getTxtNombre().requestFocus();
    }
    public void finalizar() {
      
   this.vista.dispose();
    
    
    this.vista = null;
    this.dao = null; 
    
    System.out.println("Controlador y referencias liberadas.");

}
}