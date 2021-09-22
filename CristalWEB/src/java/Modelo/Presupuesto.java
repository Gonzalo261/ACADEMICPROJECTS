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
public class Presupuesto {
    private int id;
    private String fechaEmision;
    private int idUsuarioCliente;
    private double importeTotal;
    private String fechaInicioEvento;
    private String fechaFinEvento;
    private String fechaEntrega;
    private String fechaRetiro;
    private int lugarEntrega;
    private int lugarRetiro;
    private double costoFlete;
    private boolean vigente;
    private String observaciones;

    public Presupuesto(int id,String fechaEmision, int idUsuarioCliente,  double importeTotal,String fechaInicioEvento, String fechaFinEvento, String fechaEntrega, String fechaRetiro, int lugarEntrega, int lugarRetiro, double costoFlete,boolean vigente,String observaciones) {
        this.id = id;
        this.fechaEmision=fechaEmision;
        this.idUsuarioCliente = idUsuarioCliente;
        this.importeTotal = importeTotal;
        this.fechaFinEvento = fechaFinEvento;
        this.fechaInicioEvento = fechaInicioEvento;
        this.fechaEntrega = fechaEntrega;
        this.fechaRetiro = fechaRetiro;
        this.lugarEntrega = lugarEntrega;
        this.lugarRetiro = lugarRetiro;
        this.costoFlete = costoFlete;
        this.vigente = vigente;
        this.observaciones = observaciones;
    }
    public String getFechaFinEvento() {
        return fechaFinEvento;
    }

    public void setFechaFinEvento(String fechaFinEvento) {
        this.fechaFinEvento = fechaFinEvento;
    }

    public String getFechaInicioEvento() {
        return fechaInicioEvento;
    }

    public void setFechaInicioEvento(String fechaInicioEvento) {
        this.fechaInicioEvento = fechaInicioEvento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    

    public boolean getVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuarioCliente() {
        return idUsuarioCliente;
    }

    public void setIdCliente(int idUsuarioCliente) {
        this.idUsuarioCliente = idUsuarioCliente;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public int getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(int lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public int getLugarRetiro() {
        return lugarRetiro;
    }

    public void setLugarRetiro(int lugarRetiro) {
        this.lugarRetiro = lugarRetiro;
    }

    public double getCostoFlete() {
        return costoFlete;
    }

    public void setCostoFlete(double costoFlete) {
        this.costoFlete = costoFlete;
    }
    
}
