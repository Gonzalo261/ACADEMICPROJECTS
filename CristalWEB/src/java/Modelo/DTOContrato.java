
package Modelo;

public class DTOContrato {
    
    private int idContrato;
    private DTOPresupuesto dtoPresupuesto;
    private String fechaEmision;
    private String nombreVendedor;
    private double saldo;
    private String estado;
    private boolean vigente;
    private String observaciones;

    public DTOContrato(int idContrato,DTOPresupuesto dtoPresupuesto, String fechaEmision, String nombreVendedor,  double saldo, String estado,boolean vigente,String observaciones) {
        this.idContrato = idContrato;
        this.dtoPresupuesto = dtoPresupuesto;
        this.fechaEmision = fechaEmision;
        this.nombreVendedor = nombreVendedor;
        this.saldo = saldo;
        this.estado = estado;
        this.vigente= vigente;
        this.observaciones = observaciones;
    }
    public DTOContrato(int idContrato,DTOPresupuesto dtoPresupuesto,String nombreVendedor, String estado,String observaciones) {
        this.idContrato = idContrato;
        this.dtoPresupuesto = dtoPresupuesto;
        this.fechaEmision = fechaEmision;
        this.nombreVendedor = nombreVendedor;
        this.saldo = saldo;
        this.estado = estado;
        this.vigente= vigente;
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    

    public DTOPresupuesto getDTOPresupuesto() {
        return dtoPresupuesto;
    }

    public void setDTOPresupuesto(DTOPresupuesto dtoPresupuesto) {
        this.dtoPresupuesto = dtoPresupuesto;
    }
    
    

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
    

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

    