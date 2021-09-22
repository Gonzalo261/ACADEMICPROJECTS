/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Articulo;
import Modelo.DTODetallePresupuesto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gonza
 */
public class GestorArticulo {
    
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
    
    public void agregarArticulo(Articulo articulo)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Articulo (nombre,descripcion, precio, imagen, stock, idCategoria ) VALUES (?,?,?,?,?,?)");
            
            ps.setString(1, articulo.getNombre());
            ps.setString(2, articulo.getDescripcion());
            ps.setDouble(3, articulo.getPrecio());
            ps.setString(4, articulo.getImagen());
            ps.setInt(5, articulo.getStock());
            ps.setInt(6, articulo.getIdCategoria());
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
    
    public void actualizarArticulo(Articulo articulo)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Articulo SET nombre=?, descripcion= ?,precio = ?, imagen = ?, stock = ?, idCategoria = ? WHERE idArticulo = ?");
            
            ps.setString(1, articulo.getNombre());
            ps.setString(2, articulo.getDescripcion());
            ps.setDouble(3, articulo.getPrecio());
            ps.setString(4, articulo.getImagen());
            ps.setInt(5, articulo.getStock());
            ps.setInt(6, articulo.getIdCategoria());
            ps.setInt(7, articulo.getId());
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
    
    public void eliminarArticulo(int idArticulo)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("DELETE Articulo WHERE idArticulo = ?"); // BAJA FISICA
            //PreparedStatement ps = con.prepareStatement("UPDATE Ventas SET fechaBaja = GETDATE() WHERE id = ?"); //BAJA LOGICA
            //UPDATE Ventas SET estado = 0 WHERE id = ?
            ps.setInt(1, idArticulo);
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
    
    public ArrayList<Articulo> obtenerArticulos()
    {
        ArrayList<Articulo> lista = new ArrayList<Articulo>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT idArticulo,nombre, descripcion, precio, imagen,stock,idCategoria FROM Articulo ORDER BY 2");
            while(rs.next())
            {
                int id = rs.getInt("idArticulo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String imagen = rs.getString("imagen");
                int stock = rs.getInt("stock");
                int idCategoria = rs.getInt("idCategoria");
                
                Articulo articulo = new Articulo(id,nombre,descripcion, precio,imagen, stock, idCategoria);
                lista.add(articulo);
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
    
    public ArrayList<Articulo> obtenerArticulosPorFiltro(String filtro)
    {
        String f = "%"+filtro+"%";
        ArrayList<Articulo> lista = new ArrayList<Articulo>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT idArticulo,nombre, descripcion, precio, imagen,stock,idCategoria FROM Articulo where descripcion like ? ORDER BY 2");
            st.setString(1, f);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("idArticulo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String imagen = rs.getString("imagen");
                int stock = rs.getInt("stock");
                int idCategoria = rs.getInt("idCategoria");
                
                Articulo articulo = new Articulo(id,nombre,descripcion, precio,imagen, stock, idCategoria);
                lista.add(articulo);
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
    public Articulo obtenerArticuloPorId(int idArticulo)
    {
        Articulo a = null;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT * FROM Articulo WHERE idArticulo = ?");
            st.setInt(1, idArticulo);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Double precio = rs.getDouble("precio");
                String imagen = rs.getString("imagen");
                int stock = rs.getInt("stock");
                int idCategoria = rs.getInt("idCategoria");
                
                a = new Articulo(idArticulo,nombre,descripcion, precio,imagen, stock, idCategoria);
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
        
        return a;
    }
    
    public ArrayList<DTODetallePresupuesto> obtenerArticulosMasAlquilados(String fechaA,String fechaB)
    {
        ArrayList<DTODetallePresupuesto> lista = new ArrayList<DTODetallePresupuesto>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("select a.idArticulo, "+
                                            " a.descripcion , "+
                                            " a.precio , "+
                                            " sum(dp.cantidad) as cantidad" +
                                            " from Articulo a" +
                                            " join DetallePresupuesto dp on a.idArticulo = dp.idArticulo" +
                                            " join Presupuesto p on dp.idPresupuesto = p.idPresupuesto" +
                                            " join Contrato c on c.idPresupuesto = p.idPresupuesto " +
                                            " where c.fechaEmision between ? and ? " +
                                            " group by a.idArticulo, a.descripcion, a.precio" +
                                            " order by cantidad desc");
            st.setString(1, fechaA);
            st.setString(2, fechaB);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idArticulo = rs.getInt("idArticulo");
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");
                
                DTODetallePresupuesto dp = new DTODetallePresupuesto(idArticulo,descripcion,precio, cantidad);
                lista.add(dp);
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
    
    public ArrayList<Articulo> obtenerArticuloPorCategoria(int idCategoria)
    {
        ArrayList<Articulo> lista = new ArrayList<Articulo>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT idArticulo,nombre, descripcion, precio, imagen,stock,idCategoria "+
                                           " FROM Articulo " +
                                           " WHERE idCategoria = ? "
                                         + " ORDER BY 2");
            st.setInt(1, idCategoria);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("idArticulo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String imagen = rs.getString("imagen");
                int stock = rs.getInt("stock");
                
                Articulo articulo = new Articulo(id,nombre,descripcion, precio,imagen, stock, idCategoria);
                lista.add(articulo);
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

