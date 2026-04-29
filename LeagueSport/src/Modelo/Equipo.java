/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;

/**
 *
 * @author Acer
 */
public class Equipo {
    private String id;
    private String Nombre;
    private List<Jugador>jugadoes;
    private List<Jugador> titulares;
    private List<Jugador> banco;
    private double presupuesto;

    public Equipo(String id, String Nombre, List<Jugador> jugadoes, List<Jugador> titulares, List<Jugador> banco, double presupuesto) {
        this.id = id;
        this.Nombre = Nombre;
        this.jugadoes = jugadoes;
        this.titulares = titulares;
        this.banco = banco;
        this.presupuesto = presupuesto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public List<Jugador> getJugadoes() {
        return jugadoes;
    }

    public void setJugadoes(List<Jugador> jugadoes) {
        this.jugadoes = jugadoes;
    }

    public List<Jugador> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Jugador> titulares) {
        this.titulares = titulares;
    }

    public List<Jugador> getBanco() {
        return banco;
    }

    public void setBanco(List<Jugador> banco) {
        this.banco = banco;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
    public void agregarJugador(Jugador jugador) {
        
        this.jugadoes.add(jugador);
        this.titulares.add(jugador);
    }

    @Override
    public String toString() {
        return "Equipo{" + "id=" + id + ", Nombre=" + Nombre + ", jugadoes=" + jugadoes + ", titulares=" + titulares + ", banco=" + banco + ", presupuesto=" + presupuesto + '}';
    }

    
    
    
}
