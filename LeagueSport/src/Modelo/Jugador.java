/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Jugador implements Serializable{
 private String Nombre;
 private String Rol;
 private int Habilidad;
 private int Salario;

    public Jugador(String Nombre, String Rol, int Habilidad, int Salario) {
        this.Nombre = Nombre;
        this.Rol = Rol;
        this.Habilidad = Habilidad;
        this.Salario = Salario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public int getHabilidad() {
        return Habilidad;
    }

    public void setHabilidad(int Habilidad) {
        this.Habilidad = Habilidad;
    }

    public int getSalario() {
        return Salario;
    }

    public void setSalario(int Salario) {
        this.Salario = Salario;
    }

    @Override
    public String toString() {
        return "Jugador{" + "Nombre=" + Nombre + ", Rol=" + Rol + ", Habilidad=" + Habilidad + ", Salario=" + Salario + '}';
    }
 
}
