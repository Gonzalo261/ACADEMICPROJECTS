
package Modelo;

public class DTOPresupuesto {
    private int id;
    private String fechaEmision;
    private String nombreCliente;
    private double importeTotal;
    private String fechaFinEvento;
    private String fechaInicioEvento;
    private String fechaEntrega;
    private String fechaRetiro;
    private String lugarEntrega;
    private double costoFlete;
    private boolean vigente;
    private String observaciones;

    public DTOPresupuesto(int id, String fechaEmision, String nombreCliente, double importeTotal, String fechaInicioEvento, String fechaFinEvento, String fechaEntrega, String fechaRetiro, String lugarEntrega, double costoFlete,boolean vigente, String observaciones) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.nombreCliente = nombreCliente;
        this.importeTotal = importeTotal;
        this.fechaFinEvento = fechaFinEvento;
        this.fechaInicioEvento = fechaInicioEvento;
        this.fechaEntrega = fechaEntrega;
        this.fechaRetiro = fechaRetiro;
        this.lugarEntrega = lugarEntrega;
        this.costoFlete = costoFlete;
        this.vigente = vigente;
        this.observaciones=observaciones;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
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
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public double getCostoFlete() {
        return costoFlete;
    }

    public void setCostoFlete(double costoFlete) {
        this.costoFlete = costoFlete;
    }
    
    
    
}
