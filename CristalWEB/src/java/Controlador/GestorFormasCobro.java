/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.FormasCobro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gonza
 */
public class GestorFormasCobro {
    
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
    
    
    public ArrayList<FormasCobro> obtenerFormasCobro()
    {
        ArrayList<FormasCobro> lista = new ArrayList<FormasCobro>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT idFormaCobro, descripcion FROM FormaCobro ORDER BY 2");
            while(rs.next())
            {
                int idFormaCobro = rs.getInt("idFormaCobro");
                String descripcion = rs.getString("descripcion");
                
                FormasCobro formasCobro = new FormasCobro(idFormaCobro,descripcion);
                lista.add(formasCobro);
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

