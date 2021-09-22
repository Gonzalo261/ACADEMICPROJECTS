/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTOEntrega;
import Modelo.Entrega;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author gonza
 */
public class GestorEntrega {
    
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
    
    public void agregarEntrega(Entrega entrega)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Entrega (idContrato,fechaYHoraEntrega,idUsuarioCadete,observaciones)VALUES(?,?,?,?)");
            ps.setInt(1, entrega.getIdContrato());
            ps.setString(2, entrega.getFechaYHoraEntrega());
            ps.setInt(3, entrega.getIdUsuarioCadete());
            ps.setString(4, entrega.getObservaciones());
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
    public ArrayList<DTOEntrega> obtenerEntregas(String fechaA, String fechaB)
    {
        ArrayList<DTOEntrega> lista = new ArrayList<DTOEntrega>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT * FROM Entrega WHERE fechaYHoraEntrega between ? and ? order by fechaYHoraEntrega desc");
            st.setString(1, fechaA);
            st.setString(2, fechaB);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idEntrega = rs.getInt("idEntrega");
                int idContrato = rs.getInt("idContrato");
                String fechaYHoraEntrega = rs.getString("fechaYHoraEntrega");
                int idUsuarioCadete = rs.getInt("idUsuarioCadete");
                String observaciones = rs.getString("observaciones");
                
                DTOEntrega dtoEntrega = new DTOEntrega(idEntrega,gestorCON.obtenerDTOContratoPorId(idContrato),fechaYHoraEntrega,idUsuarioCadete,observaciones);
                lista.add(dtoEntrega);
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
    
    
    
    public ArrayList<DTOEntrega> obtenerPedidosEntregadosPorCliente(int idCliente,String fechaA, String fechaB)
    {
        ArrayList<DTOEntrega> lista = new ArrayList<DTOEntrega>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT e.idEntrega ," +
                                                        "		e.idContrato," +
                                                        "		e.fechaYHoraEntrega," +
                                                        "		e.idUsuarioCadete," +
                                                        "		e.observaciones" +
                                                        "FROM Entrega e " +
                                                        "join Contrato con on e.idContrato = con.idContrato " +
                                                        "join Presupuesto pre on pre.idPresupuesto = con.idPresupuesto " +
                                                        "WHERE pre.idUsuarioCliente = ? and e.fechaYHoraEntrega between ? and ? "+
                                                        " order by fechaYHoraEntrega desc");
            st.setInt(1, idCliente);
            st.setString(2, fechaA);
            st.setString(3, fechaB);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idEntrega = rs.getInt("idEntrega");
                int idContrato = rs.getInt("idContrato");
                String fechaYHoraEntrega = rs.getString("fechaYHoraEntrega");
                int idUsuarioCadete = rs.getInt("idUsuarioCadete");
                String observaciones = rs.getString("observaciones");
                
                DTOEntrega dtoEntrega = new DTOEntrega(idEntrega,gestorCON.obtenerDTOContratoPorId(idContrato),fechaYHoraEntrega,idUsuarioCadete,observaciones);
                lista.add(dtoEntrega);
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
    
    public ArrayList<DTOEntrega> obtenerPedidosEntregadosPorCadete(int idCadete )
    {
        ArrayList<DTOEntrega> lista = new ArrayList<DTOEntrega>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT e.idEntrega ," +
                                                        "		e.idContrato," +
                                                        "		e.fechaYHoraEntrega," +
                                                        "		e.idUsuarioCadete," +
                                                        "		e.observaciones" +
                                                        "FROM Entrega e " +
                                                        "WHERE pre.idUsuarioCadete = ?");
            st.setInt(1, idCadete);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idEntrega = rs.getInt("idEntrega");
                int idContrato = rs.getInt("idContrato");
                String fechaYHoraEntrega = rs.getString("fechaYHoraEntrega");
                int idUsuarioCadete = rs.getInt("idUsuarioCadete");
                String observaciones = rs.getString("observaciones");
                
                DTOEntrega dtoEntrega = new DTOEntrega(idEntrega,gestorCON.obtenerDTOContratoPorId(idContrato),fechaYHoraEntrega,idUsuarioCadete,observaciones);
                lista.add(dtoEntrega);
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
