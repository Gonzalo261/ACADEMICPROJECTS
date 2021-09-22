/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Ciudad;
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
public class GestorCiudad {
    
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

    public ArrayList<Ciudad> obtenerCiudades()
    {
        ArrayList<Ciudad> lista = new ArrayList<Ciudad>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT idCiudad, descripcion, kmDesdeLaEmpresa  FROM Ciudad ORDER BY 2");
            while(rs.next())
            {
                int id = rs.getInt("idCiudad");
                String descripcion = rs.getString("descripcion");
                int km = rs.getInt("kmDesdeLaEmpresa");
                
                Ciudad ciudad = new Ciudad(id,descripcion, km);
                lista.add(ciudad);
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
    public int obtenerKmPorIdCiudad(int idCiudad)
    {
        int id = 0;
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("select kmDesdeLaEmpresa " +
                                                        "from Ciudad " +
                                                        "where idCiudad = ? ");
            ps.setInt(1, idCiudad);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                id = rs.getInt("kmDesdeLaEmpresa");
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
        
        return id;
    }
    
 }

