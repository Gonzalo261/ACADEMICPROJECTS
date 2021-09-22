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
public class Pago {
    private int idPago;
    private int idContrato;
    private int mes;
    private int año;
    private int demora;
    private double importe;

    public Pago(int idPago, int idContrato, int mes, int año, int demora, double importe) {
        this.idPago = idPago;
        this.idContrato = idContrato;
        this.mes = mes;
        this.año = año;
        this.demora = demora;
        this.importe = importe;
    }

    public Pago() {
        idPago = 0;
        idContrato = 0;
        mes = 0;
        año = 0;
        demora = 0;
        importe = 0;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getDemora() {
        return demora;
    }

    public void setDemora(int demora) {
        this.demora = demora;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double calcularImporte(double impContrato) {
        
        double saldo=0;
        if(demora>0)
        {
            saldo = impContrato + (impContrato*demora/100);
        }
        return saldo;
    }
    
      
    
}
