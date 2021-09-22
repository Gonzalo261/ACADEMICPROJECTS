
package Modelo;

public class DTORetiro {
    
    private int id;
    private DTOContrato dtoContrato;
    private String fechaYHoraRetiro;
    private int idUsuarioCadete;
    private String observaciones ;

    public DTORetiro(int id, DTOContrato dtoContrato, String fechaYHoraRetiro, int idUsuarioCadete, String observaciones) {
        this.id = id;
        this.dtoContrato = dtoContrato;
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

    public DTOContrato getDTOContrato() {
        return dtoContrato;
    }

    public void setDTOContrato(DTOContrato dtoContrato) {
        this.dtoContrato = dtoContrato;
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
