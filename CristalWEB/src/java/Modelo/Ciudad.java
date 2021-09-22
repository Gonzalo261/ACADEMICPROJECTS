
package Modelo;

public class Ciudad {
    private int idCiudad;
    private String descripcion;
    private int kmAlDeposito;

    public Ciudad(int idCiudad, String descripcion, int kmAlDeposito) {
        this.idCiudad = idCiudad;
        this.descripcion = descripcion;
        this.kmAlDeposito = kmAlDeposito;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getKmAlDeposito() {
        return kmAlDeposito;
    }

    public void setKmAlDeposito(int kmAlDeposito) {
        this.kmAlDeposito = kmAlDeposito;
    }
    
    
    
}
