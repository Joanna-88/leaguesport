/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class EquipoDAO {
    private List<Equipo> listaEquipos = new ArrayList<>();
    private List<Jugador> jugadoes=new  ArrayList<>();
    public boolean crearEquipo(String id, String nombre, double presupuesto) {

        for (Equipo equipo : listaEquipos) {
            if (equipo.getId().equalsIgnoreCase(id)) {
                return false; 
            }
        }

        List<Jugador>titulares=new  ArrayList<>();
        List<Jugador>banco=new ArrayList<>();
        Equipo nuevo=new Equipo(id, nombre, jugadoes, titulares, banco, presupuesto);
        return listaEquipos.add(nuevo);
    }
    public List<Equipo> consultarTodos() {
        return listaEquipos;
    }

    public Equipo buscarPorId(String id) {
        for (Equipo equipo : listaEquipos) {
            if (equipo.getId().equalsIgnoreCase(id)) {
                return equipo;
            }
        }
        return null;
    }
    
    
    

    public boolean eliminar(String id) {
        for (int i = 0; i < listaEquipos.size(); i++) {
            if (listaEquipos.get(i).getId().equalsIgnoreCase(id)) {
                listaEquipos.remove(i);
                return true;
            }
        }
        return false;
    }

    
public boolean agregarJugador(String idEquipo, Jugador jugador) {

    Equipo equipo = buscarPorId(idEquipo);

    if (equipo == null) {
        return false;
    }

    int total = equipo.getTitulares().size() + equipo.getBanco().size();

    if (total >= 20) {
        return false;
    }

    if (equipo.getTitulares().size() < 11) {
        equipo.getTitulares().add(jugador);
    } else {
        equipo.getBanco().add(jugador);
    }

    return true;
}

    
}
