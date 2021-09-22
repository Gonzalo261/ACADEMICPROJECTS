/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author gonza
 */
public class GestorPersona {
    
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
    
    public void agregarPersona(Persona persona)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Persona (nombre, apellido,celular,dni ) VALUES (?,?,?,?)");
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setInt(3, persona.getCelular());
            ps.setInt(4, persona.getDni());
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
    public void actualizarPersona(Persona persona)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Persona SET nombre= ?, apellido= ?, celular= ?, dni = ? WHERE idPersona = ?");
            
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setInt(3, persona.getCelular());
            ps.setInt(4, persona.getDni());
            ps.setInt(5, persona.getId());
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
    
    
    public int obtenerIdUltimaPersonaCreada()
    {
        int id = 1;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT TOP 1 idPersona FROM Persona ORDER BY 1 DESC");
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                id = rs.getInt("idPersona");
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

    public Persona obtenerPersonaPorId(int idPersona)
    {
        Persona persona = null;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT * FROM Persona WHERE idPersona = ? ");
            st.setInt(1, idPersona);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int celular = rs.getInt("celular");
                int dni = rs.getInt("dni");
                
                persona = new Persona(idPersona,nombre,apellido, celular,dni);
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
        
        return persona;
    }
    
 }

