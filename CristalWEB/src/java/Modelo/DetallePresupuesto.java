
package Modelo;

public class DetallePresupuesto {
    
    private int id;
    private int idArticulo;
    private int idPresupuesto;
    private double precio;
    private int cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetallePresupuesto(int id, int idArticulo, int idPresupuesto, double precio, int cantidad) {
        this.id = id;
        this.idArticulo = idArticulo;
        this.idPresupuesto = idPresupuesto;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
}
