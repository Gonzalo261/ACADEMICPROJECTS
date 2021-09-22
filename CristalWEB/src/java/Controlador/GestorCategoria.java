/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Categoria;
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
public class GestorCategoria {
    
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

    public ArrayList<Categoria> obtenerCategoria()
    {
        ArrayList<Categoria> lista = new ArrayList<Categoria>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT idCategoria, descripcion  FROM Categoria ORDER BY 2");
            while(rs.next())
            {
                int idCategoria = rs.getInt("idCategoria");
                String descripcion = rs.getString("descripcion");
                
                Categoria categoria = new Categoria(idCategoria,descripcion);
                lista.add(categoria);
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
    public String obtenerDescripcionCategoria(int idCategoria)
    {
        String descripcion = "";
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT descripcion" +
                                                        "  FROM Categoria " +
                                                        "  WHERE idCategoria = ?");
            st.setInt(1, idCategoria);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                descripcion = rs.getString("descripcion");
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
        
        return descripcion;
    }
    
 }

