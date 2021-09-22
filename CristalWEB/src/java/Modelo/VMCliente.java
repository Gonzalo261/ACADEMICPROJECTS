/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author gonza
 */
public class VMCliente {
    
    private int idUsuario;
    private String nombre;
    private String apellido;
    private int dni;
    private double totalAlquilado;

    public VMCliente(int idUsuario, String nombre, String apellido, int dni, double totalAlquilado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni=dni;
        this.totalAlquilado = totalAlquilado;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getTotalAlquilado() {
        return totalAlquilado;
    }

    public void setTotalAlquilado(double totalAlquilado) {
        this.totalAlquilado = totalAlquilado;
    }
    
    
    
}
