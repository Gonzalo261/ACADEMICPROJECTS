
package Modelo;

public class DTOEntrega {
    
    private int id;
    private DTOContrato dtoContrato;
    private String fechaYHoraEntrega;
    private int idUsuarioCadete;
    private String observaciones ;

    public DTOEntrega(int id, DTOContrato dtoContrato, String fechaYHoraEntrega, int idUsuarioCadete, String observaciones) {
        this.id = id;
        this.dtoContrato = dtoContrato;
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

    public DTOContrato getDTOContrato() {
        return dtoContrato;
    }

    public void setDTOContrato(DTOContrato dtoContrato) {
        this.dtoContrato = dtoContrato;
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
