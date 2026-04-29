/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class JugadorDAO {
    private  List<Jugador>listajugadores= new ArrayList<>();
    
    public boolean crearjugador(String Nombre,String Rol,int Habilidad,int Salario) {
        if (Habilidad<0||Habilidad>100) {
            JOptionPane.showMessageDialog(null,"la habilidad debe estar entre 0 y 100");
            return false;
        }
        Jugador jug=new Jugador(Nombre, Rol, Habilidad, Salario);
        return listajugadores.add(jug);
    }
    public  List<Jugador> consultartodos(){
        return listajugadores;
    }
    public Jugador buscarPorNombre(String nombre) {
        for (Jugador jugadores : listajugadores) {
            if (jugadores.getNombre().equals(nombre)) {
              return jugadores;
                
            }
            
        }
       return null;
    }
    public boolean actualizarJugador(Jugador actualizado) {

    for (int i = 0; i < listajugadores.size(); i++) {

        if (listajugadores.get(i).getNombre()
                .equalsIgnoreCase(actualizado.getNombre())) {

            listajugadores.set(i, actualizado);
            return true;
        }
    }

    return false;
}
    public boolean elimiminar(String nombre) {
        for (int i = 0; i < listajugadores.size(); i++) {
            if (listajugadores.get(i).getNombre().equals(nombre)) {
                listajugadores.remove(i);
                return true;
                
            }
            
        }
        return false;
    }
    
    public double salario(String nombre) {
        for (Jugador jugadore : listajugadores) {
            if (jugadore.getNombre().equals(nombre)) {
               return jugadore.getSalario();
                
            }
           
        }
        return -1;
    }

    public Jugador Actualizar(String nombreJugador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
