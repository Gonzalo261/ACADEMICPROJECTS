/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTODetallePresupuesto;
import Modelo.DetallePresupuesto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author gonza
 */
public class GestorDetallePresupuesto {
    
    private Connection con;
    
    private void abrirConexion()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-CQUI9AH:1433;databaseName=CristalWEB", "sa", "sa");
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    private void cerrarConexion()
    {
        try
        {
            if(con != null && !con.isClosed())
                con.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    public void agregarDetallePresupuesto(DetallePresupuesto dp)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO DetallePresupuesto (idArticulo,idPresupuesto,precio,cantidad)VALUES(?,?,?,?)");
            
            ps.setInt(1, dp.getIdArticulo());
            ps.setInt(2, dp.getIdPresupuesto());
            ps.setDouble(3, dp.getPrecio());
            ps.setInt(4, dp.getCantidad());
            ps.executeUpdate();
            
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
    }
    
    public double obtenerImporteTotalPorIdPresupuesto(int idPresupuesto)
    {
        double importe =0;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT sum(precio*cantidad) as 'importe' "
                                                        + "FROM DetallePresupuesto "
                                                         + "WHERE idPresupuesto = ? " );
            st.setInt(1, idPresupuesto);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                importe = rs.getInt("importe");
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return importe;
    }
    
    public ArrayList<DTODetallePresupuesto> obtenerDetallePresupuestoPorId(int idPre)
    {
        ArrayList<DTODetallePresupuesto> lista = new ArrayList<DTODetallePresupuesto>();
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("select a.idArticulo, "+
                                                        "       a.descripcion , " +
                                                        "       dp.cantidad, " +
                                                        "       dp.precio " +
                                                        "from Presupuesto p join " +
                                                        "DetallePresupuesto dp on p.idPresupuesto = dp.idPresupuesto " +
                                                        "join Articulo a on dp.idArticulo = a.idArticulo " +
                                                        "where p.idPresupuesto = ? " +
                                                        "order by a.descripcion");
            ps.setInt(1, idPre);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int idArticulo = rs.getInt("idArticulo");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                
                DTODetallePresupuesto dtoDetallePresupuesto = new DTODetallePresupuesto(idArticulo,descripcion,precio,cantidad);
                lista.add(dtoDetallePresupuesto);
            }
            rs.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            cerrarConexion();
        }
        
        return lista;
    }
    
}
