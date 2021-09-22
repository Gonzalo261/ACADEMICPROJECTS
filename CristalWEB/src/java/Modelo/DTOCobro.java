
package Modelo;

public class DTOCobro {
    
    private int idCobro;
    private int idContrato;
    private String cajero;
    private String fechaCobrado;
    private String formaCobro;
    private double montoCobrado;
    private int numeroCheque;
    private String fechaCheque;
    private boolean vigente;

    public DTOCobro(int idCobro, int idContrato,String cajero, String fechaCobrado, String formaCobro, double montoCobrado, int numeroCheque, String fechaCheque,boolean vigente) {
        this.idCobro = idCobro;
        this.idContrato = idContrato;
        this.cajero = cajero;
        this.fechaCobrado = fechaCobrado;
        this.formaCobro = formaCobro;
        this.montoCobrado = montoCobrado;
        this.numeroCheque = numeroCheque;
        this.fechaCheque = fechaCheque;
        this.vigente = vigente;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
    

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }
    

    public int getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(int numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getFechaCheque() {
        return fechaCheque;
    }

    public void setFechaCheque(String fechaCheque) {
        this.fechaCheque = fechaCheque;
    }

    
    
    public int getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(int idCobro) {
        this.idCobro = idCobro;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getFechaCobrado() {
        return fechaCobrado;
    }

    public void setFechaCobrado(String fechaCobrado) {
        this.fechaCobrado = fechaCobrado;
    }

    public String getFormaCobro() {
        return formaCobro;
    }

    public void setFormaCobro(String formaCobro) {
        this.formaCobro = formaCobro;
    }

    public double getMontoCobrado() {
        return montoCobrado;
    }

    public void setMontoCobrado(double montoCobrado) {
        this.montoCobrado = montoCobrado;
    }
}
