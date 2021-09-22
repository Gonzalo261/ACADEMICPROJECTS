
package Modelo;

public class Contrato {
    
    private int idContrato;
    private String fechaEmision;
    private int idUsuarioVendedor;
    private int idPresupuesto;
    private double saldo;
    private int idEstado;
    private boolean vigente;
    private String observaciones;

    public Contrato(int idContrato, String fechaEmision,int idUsuarioVendedor, int idPresupuesto,  double saldo, int idEstado,boolean vigente,String observaciones) {
        this.idContrato = idContrato;
        this.fechaEmision = fechaEmision;
        this.idUsuarioVendedor = idUsuarioVendedor;
        this.idPresupuesto = idPresupuesto;
        this.saldo = saldo;
        this.idEstado = idEstado;
        this.vigente= vigente;
        this.observaciones = observaciones;
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
    
    
    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public int getIdUsuarioVendedor() {
        return idUsuarioVendedor;
    }

    public void setIdUsuarioVendedor(int idUsuarioVendedor) {
        this.idUsuarioVendedor = idUsuarioVendedor;
    }

    
    public int getIdContrato() {
        return idContrato;
    }

    public void setId(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }


    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    
    

}

    