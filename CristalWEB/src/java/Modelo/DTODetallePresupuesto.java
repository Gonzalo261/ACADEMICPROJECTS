
package Modelo;

public class DTODetallePresupuesto {
    
    private int idArticulo;
    private String descripcion;
    private double precio;
    private int cantidad;

    public DTODetallePresupuesto(int idArticulo,String descripcion, double precio,int cantidad) {
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
   
    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    public String getDatosCompletos()
    {
        return descripcion + " - $ " + precio;
    }
    
}
