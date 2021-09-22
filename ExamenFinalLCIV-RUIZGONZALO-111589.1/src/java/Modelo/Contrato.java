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
public class Contrato {
    private int idContrato;
    private String domicilio;
    private String inquilino;
    private double importe;

    public Contrato(int idContrato, String domicilio, String inquilino, double importe) {
        this.idContrato = idContrato;
        this.domicilio = domicilio;
        this.inquilino = inquilino;
        this.importe = importe;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getInquilino() {
        return inquilino;
    }

    public void setInquilino(String inquilino) {
        this.inquilino = inquilino;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    public String getDatos()
    {
        return inquilino + " - $" +importe;
    }
    
    
}
