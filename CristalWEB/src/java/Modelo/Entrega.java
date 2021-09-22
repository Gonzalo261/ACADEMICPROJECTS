
package Modelo;

public class Entrega {
    
    private int id;
    private int idContrato;
    private String fechaYHoraEntrega;
    private int idUsuarioCadete;
    private String observaciones ;

    public Entrega(int id, int idContrato, String fechaYHoraEntrega, int idUsuarioCadete, String observaciones) {
        this.id = id;
        this.idContrato = idContrato;
        this.fechaYHoraEntrega = fechaYHoraEntrega;
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

    public String getFechaYHoraEntrega() {
        return fechaYHoraEntrega;
    }

    public void setFechaYHoraEntrega(String fechaYHoraEntrega) {
        this.fechaYHoraEntrega = fechaYHoraEntrega;
    }

    public int getIdUsuarioCadete() {
        return idUsuarioCadete;
    }

    public void setIdUsuarioCadete(int idUsuarioCadete) {
        this.idUsuarioCadete = idUsuarioCadete;
    }
    
    
}
