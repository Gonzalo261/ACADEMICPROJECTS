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
public class Retiro {
    private int id;
    private int idContrato;
    private String fechaYHoraRetiro;
    private int idUsuarioCadete;
    private String observaciones;

    public Retiro(int id, int idContrato , String fechaYHoraRetiro, int idUsuarioCadete,String observaciones) {
        this.id = id;
        this.idContrato = idContrato;
        this.fechaYHoraRetiro = fechaYHoraRetiro;
        this.idUsuarioCadete = idUsuarioCadete;
        this.observaciones=observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
    
    

    public String getFechaYHoraRetiro() {
        return fechaYHoraRetiro;
    }

    public void setFechaYHoraRetiro(String fechaYHoraRetiro) {
        this.fechaYHoraRetiro = fechaYHoraRetiro;
    }

    public int getIdUsuarioCadete() {
        return idUsuarioCadete;
    }

    public void setIdUsuarioCadete(int idUsuarioCadete) {
        this.idUsuarioCadete = idUsuarioCadete;
    }
    
    
    
}
