/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Contrato;
import Modelo.Pago;
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
public class GestorBD {
    
    private Connection con;
    private void abrirConexion()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-CQUI9AH:1433;databaseName=ExaFinalLCIV-RUIZGONZALO", "sa", "sa");
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
    
    public void agregarPago(Pago p)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Pagos (IdContrato , Mes, Año, Demora, Importe) VALUES (?, ?,?,?,?)");
            ps.setInt(1, p.getIdContrato());
            ps.setInt(2, p.getMes());
            ps.setInt(3, p.getAño());
            ps.setInt(4, p.getDemora());
            ps.setDouble(5, p.getImporte());
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
    public ArrayList<Contrato> obtenerContratos()
    {
        ArrayList<Contrato> lista = new ArrayList<Contrato>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Contratos");
            while(rs.next())
            {
                int id = rs.getInt("IdContrato");
                String domicilio = rs.getString("Domicilio");
                String nombre = rs.getString("Inquilino");
                double importe = rs.getInt("Importe");
                
                Contrato con = new Contrato(id,domicilio, nombre, importe);
                lista.add(con);
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
    
    
    public ArrayList<Pago> pagosSinDemora()
    {
        ArrayList<Pago> lista = new ArrayList<Pago>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Pagos WHERE Demora = 0");
            while(rs.next())
            {
                Pago p = new Pago();
                p.setIdContrato(rs.getInt("IdContrato"));
                p.setMes(rs.getInt("Mes"));
                p.setAño(rs.getInt("Año"));
                p.setDemora(rs.getInt("Demora"));
                p.setImporte(rs.getInt("Importe"));
                lista.add(p);
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
