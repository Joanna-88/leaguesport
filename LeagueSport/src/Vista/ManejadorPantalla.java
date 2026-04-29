/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.CEquipo;
import Controlador.CGestionarEquipo;
import Controlador.CGestionarJugador;
import Controlador.CJugador;
import Controlador.CMenu;
import Modelo.EquipoDAO;
import Modelo.JugadorDAO;

/**
 *
 * @author Acer
 */
public class ManejadorPantalla {
    private static final JugadorDAO jugadorDAO = new JugadorDAO();
    private static final EquipoDAO equipoDAO = new EquipoDAO();
    
    public static void abrirjugador() {
        VJugador vista =new VJugador();
        CJugador controlador = new CJugador(vista, jugadorDAO);
        
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setTitle("gestion academica");
    }
    public static void cerrarjugador(CJugador controlador) {
        if (controlador!=null) {
            controlador.finalizar();
            System.out.println("pantalla cerrada y objetos liberados");
             abrirmenu();
        }
        
    }
    public static void abrirequipo() {
        VEquipo vista =new VEquipo();
        CEquipo controlador = new CEquipo(vista, equipoDAO, jugadorDAO);
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setTitle("gestion academica");
    }
    public static void cerrarequipo(CEquipo controlador) {
        if (controlador!=null) {
            controlador.finalizar();
            controlador=null;
            System.out.println("pantalla cerrada y objetos liberados");
            abrirmenu();
        }
        
    }
    public static void abrirgestionarequipo() {
        VGestionarEquipo vista = new VGestionarEquipo();
        CGestionarEquipo controlador =
            new CGestionarEquipo(vista, equipoDAO, jugadorDAO);

    vista.setVisible(true);
    vista.setLocationRelativeTo(null);
    vista.setTitle("gestionar equipo");
    }
    public static void cerrargestionarequipo(CGestionarEquipo controlador) {
        if (controlador!=null) {
            controlador.finalizar();
            controlador=null;
            System.out.println("pantalla cerrada y objetos liberados");
            abrirmenu();
        }
        
    }
    public static void abrirmenu() {
        VMenu vista =new VMenu();
        CMenu controlador= new  CMenu(vista);
        
       
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setTitle("gestion academica");
    }
    public static void cerrarmenu(VMenu controlador) {
        if (controlador!=null) {
            controlador.finalizar();
            controlador=null;
            System.out.println("pantalla cerrada y objetos liberados");
            
        }
        
    }
   public static void abrirgestionarjugador() {
    VGestionarJugador vista = new VGestionarJugador();
   
    CGestionarJugador controlador =
            new CGestionarJugador(vista, equipoDAO, jugadorDAO);

    vista.setVisible(true);
    vista.setLocationRelativeTo(null);
    vista.setTitle("gestion academica");
}
   public static void cerragestionarjugador(CGestionarJugador controlador) {
        if (controlador!=null) {
            controlador.finalizar();
            controlador=null;
            System.out.println("pantalla cerrada y objetos liberados");
            
        }
}
}
