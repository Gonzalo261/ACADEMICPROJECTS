/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTORetiro;
import Modelo.Retiro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author gonza
 */
public class GestorRetiro {
    
    private Connection con;
    
    GestorContrato gestorCON = new GestorContrato();
    GestorPresupuesto gestorP = new GestorPresupuesto ();
    
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
    
    public void agregarRetiro(Retiro retiro)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Retiro (idContrato,fechaYHoraRetiro,idUsuarioCadete,observaciones)VALUES(?,?,?,?)");
            
            ps.setInt(1, retiro.getIdContrato());
            ps.setString(2, retiro.getFechaYHoraRetiro());
            ps.setInt(3, retiro.getIdUsuarioCadete());
            ps.setString(2, retiro.getObservaciones());
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
    
    public ArrayList<DTORetiro> obtenerRetiros(String fechaA, String fechaB)
    {
        ArrayList<DTORetiro> lista = new ArrayList<DTORetiro>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT * FROM Retiro WHERE fechaYHoraRetiro between ? and ? order by fechaYHoraRetiro desc");
            st.setString(1, fechaA);
            st.setString(2, fechaB);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idEntrega = rs.getInt("idEntrega");
                int idContrato = rs.getInt("idContrato");
                String fechaYHoraRetiro = rs.getString("fechaYHoraRetiro");
                int idUsuarioCadete = rs.getInt("idUsuarioCadete");
                String observaciones = rs.getString("observaciones");
                
                DTORetiro dtoRetiro = new DTORetiro(idEntrega,gestorCON.obtenerDTOContratoPorId(idContrato),fechaYHoraRetiro,idUsuarioCadete,observaciones);
                lista.add(dtoRetiro);
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
    
    
    public ArrayList<DTORetiro> obtenerPedidosRetiradosPorCliente(int idCliente,String fechaA, String fechaB)
    {
        ArrayList<DTORetiro> lista = new ArrayList<DTORetiro>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT r.idRetiro ," +
                                                        "		r.idContrato," +
                                                        "		r.fechaYHoraRetiro," +
                                                        "		r.idUsuarioCadete," +
                                                        "		r.observaciones" +
                                                        "FROM Retiro r " +
                                                        "join Contrato con on r.idContrato = con.idContrato " +
                                                        "join Presupuesto pre on pre.idPresupuesto = con.idPresupuesto " +
                                                        "WHERE pre.idUsuarioCliente = ? and e.fechaYHoraEntrega between ? and ? "+
                                                        "WHERE pre.idUsuarioCliente = ?");
            st.setInt(1, idCliente);
            st.setString(2, fechaA);
            st.setString(3, fechaB);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idRetiro = rs.getInt("idRetiro");
                int idContrato = rs.getInt("idContrato");
                String fechaYHoraRetiro = rs.getString("fechaYHoraRetiro");
                int idUsuarioCadete = rs.getInt("idUsuarioCadete");
                String observaciones = rs.getString("observaciones");
                
                DTORetiro dtoRetiro = new DTORetiro(idRetiro,gestorCON.obtenerDTOContratoPorId(idContrato),fechaYHoraRetiro,idUsuarioCadete,observaciones);
                lista.add(dtoRetiro);
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
    
    public ArrayList<DTORetiro> obtenerPedidosRetiradosPorCadete(int idCadete)
    {
        ArrayList<DTORetiro> lista = new ArrayList<DTORetiro>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT r.idEntrega ," +
                                                        "		r.idContrato," +
                                                        "		r.fechaYHoraEntrega," +
                                                        "		r.idUsuarioCadete," +
                                                        "		r.observaciones" +
                                                        "FROM Retiro r " +
                                                        "WHERE pre.idUsuarioCadete = ?");
            st.setInt(1, idCadete);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idRetiro = rs.getInt("idRetiro");
                int idContrato = rs.getInt("idContrato");
                String fechaYHoraRetiro = rs.getString("fechaYHoraRetiro");
                int idUsuarioCadete = rs.getInt("idUsuarioCadete");
                String observaciones = rs.getString("observaciones");
                
                DTORetiro dtoRetiro = new DTORetiro(idRetiro,gestorCON.obtenerDTOContratoPorId(idContrato),fechaYHoraRetiro,idUsuarioCadete,observaciones);
                lista.add(dtoRetiro);
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
