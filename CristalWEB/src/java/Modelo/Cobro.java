
package Modelo;

public class Cobro {
    
    private int idCobro;
    private int idContrato;
    private int idUsuarioCobro;
    private String fechaCobrado;
    private int idFormaCobro;
    private double montoCobrado;
    private int numeroCheque;
    private String fechaCheque;
    private boolean vigente;

    public Cobro(int idCobro, int idContrato,int idUsuarioCobro, String fechaCobrado, int idFormaCobro, double montoCobrado, int numeroCheque, String fechaCheque,boolean vigente) {
        this.idCobro = idCobro;
        this.idContrato = idContrato;
        this.idUsuarioCobro = idUsuarioCobro;
        this.fechaCobrado = fechaCobrado;
        this.idFormaCobro = idFormaCobro;
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
    

    public int getIdUsuarioCobro() {
        return idUsuarioCobro;
    }

    public void setIdUsuarioCobro(int idUsuarioCobro) {
        this.idUsuarioCobro = idUsuarioCobro;
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

    public int getIdFormaCobro() {
        return idFormaCobro;
    }

    public void setIdFormaCobro(int idFormaCobro) {
        this.idFormaCobro = idFormaCobro;
    }

    public double getMontoCobrado() {
        return montoCobrado;
    }

    public void setMontoCobrado(double montoCobrado) {
        this.montoCobrado = montoCobrado;
    }
}
