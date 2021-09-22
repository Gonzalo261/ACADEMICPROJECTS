/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Contrato;
import Modelo.DTOContrato;
import Modelo.VMDeuda;
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
public class GestorContrato {
    
    private Connection con;
    GestorPresupuesto gestorP = new GestorPresupuesto();
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
    
    public void agregarContrato(Contrato c)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Contrato (fechaEmision, idUsuarioVendedor, idPresupuesto,  saldo, idEstado, vigente,observaciones) VALUES (?,?,?,?,?,?,?)");
            
            ps.setString(1, c.getFechaEmision());
            ps.setInt(2, c.getIdUsuarioVendedor());
            ps.setInt(3, c.getIdPresupuesto());
            ps.setDouble(4, c.getSaldo());
            ps.setInt(5, c.getIdEstado());
            ps.setBoolean(6, c.isVigente());
            ps.setString(7, c.getObservaciones());
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
    
    public ArrayList<DTOContrato> obtenerPedidosParaEntregar(String fechaA, String fechaB)
    {
        ArrayList<DTOContrato> lista = new ArrayList<DTOContrato>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT c.idContrato, " +
                                                        "       c.idPresupuesto, "+
                                                        "       p.nombre as'idUsuarioVendedor', "+
                                                        "       es.descripcion," +
                                                        "       c.observaciones " +
                                                        "FROM Contrato c " +
                                                        "join EstadoContrato es on c.idEstado = es.idEstadoPedido " +
                                                        "join Usuario u on u.idUsuario = c.idUsuarioVendedor " +
                                                        "join Persona p on p.idPersona = u.idPersona " +
                                                        "join Presupuesto pre on c.idPresupuesto = pre.idPresupuesto "+
                                                        "WHERE c.idEstado = 1 AND pre.fechaEntrega BETWEEN ? AND ? "+
                                                        "ORDER BY pre.fechaEntrega desc");
            st.setString(1, fechaA);
            st.setString(2, fechaB);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idContrato = rs.getInt("idContrato");
                int idPresupuesto = rs.getInt("idPresupuesto");
                String idUsuarioVendedor = rs.getString("idUsuarioVendedor");
                String descripcion = rs.getString("descripcion");
                String observaciones = rs.getString("observaciones");
                
                DTOContrato dtoContrato = new DTOContrato(idContrato,gestorP.obtenerDTOPresupuestoPorId(idPresupuesto),idUsuarioVendedor,descripcion,observaciones);
                lista.add(dtoContrato);
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
    public ArrayList<DTOContrato> obtenerPedidosParaRetirar(String fechaA, String fechaB)
    {
        ArrayList<DTOContrato> lista = new ArrayList<DTOContrato>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT c.idContrato, " +
                                                        "       c.idPresupuesto, "+
                                                        "       p.nombre as'idUsuarioVendedor', "+
                                                        "       es.descripcion," +
                                                        "       c.observaciones " +
                                                        "FROM Contrato c " +
                                                        "join EstadoContrato es on c.idEstado = es.idEstadoPedido " +
                                                        "join Usuario u on u.idUsuario = c.idUsuarioVendedor " +
                                                        "join Persona p on p.idPersona = u.idPersona " +
                                                        "join Presupuesto pre on c.idPresupuesto = pre.idPresupuesto "+
                                                        "WHERE c.idEstado = 3 AND pre.fechaRetiro BETWEEN ? AND ? "+
                                                        "ORDER BY pre.fechaRetiro desc");
            st.setString(1, fechaA);
            st.setString(2, fechaB);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idContrato = rs.getInt("idContrato");
                int idPresupuesto = rs.getInt("idPresupuesto");
                String idUsuarioVendedor = rs.getString("idUsuarioVendedor");
                String descripcion = rs.getString("descripcion");
                String observaciones = rs.getString("observaciones");
                
                DTOContrato dtoContrato = new DTOContrato(idContrato,gestorP.obtenerDTOPresupuestoPorId(idPresupuesto),idUsuarioVendedor,descripcion,observaciones);
                lista.add(dtoContrato);
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
    public ArrayList<DTOContrato> obtenerContratos()
    {
        ArrayList<DTOContrato> lista = new ArrayList<DTOContrato>();
        
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("	SELECT con.idContrato as 'idContrato', " +
                                                "	con.fechaEmision as 'fechaEmision', " +
                                                "	per.nombre as 'vendedor', " +
                                                "	con.idPresupuesto as 'idPresupuesto', " +
                                                "	con.saldo as 'saldo', " +
                                                "	es.descripcion as 'estado', " +
                                                "	con.observaciones as 'observaciones' " +
                                        "	FROM Contrato con  " +
                                        "	join Presupuesto p on p.idPresupuesto = con.idPresupuesto" +
                                        "	join EstadoContrato es on es.idEstadoPedido = con.idEstado " +
                                        "	join Usuario us on con.idUsuarioVendedor = us.idUsuario " +
                                        "	join Persona per on per.idPersona = us.idPersona" +
                                        "	WHERE es.idEstadoPedido <> 5 " +
                                        "	and con.vigente = 'true' and con.saldo > 0 " +
                                        "	ORDER BY con.idContrato DESC");
            while(rs.next())
            {
                int idContrato = rs.getInt("idContrato");
                int idPresupuesto = rs.getInt("idPresupuesto");
                String fechaEmision = rs.getString("fechaEmision");
                String vendedor = rs.getString("vendedor");
                double saldo = rs.getDouble("saldo");
                String estado = rs.getString("estado");
                String observaciones = rs.getString("observaciones");
                
                DTOContrato contrato = new DTOContrato(idContrato,gestorP.obtenerDTOPresupuestoPorId(idPresupuesto),fechaEmision, vendedor, saldo, estado, true,observaciones);
                lista.add(contrato);
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
    
    public ArrayList<DTOContrato> obtenerContratosPorCliente(int idCliente)
    {
        ArrayList<DTOContrato> lista = new ArrayList<DTOContrato>();
        
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("  SELECT con.idContrato as 'idContrato', " +
                                                        "  con.fechaEmision as 'fechaEmision', " +
                                                        "  con.idUsuarioVendedor as 'idVendedor', " +
                                                        "  pers.nombre as 'vendedor', "+
                                                        "  con.idPresupuesto as 'idPresupuesto', " +
                                                        "  con.saldo as 'saldo', " +
                                                        "  es.descripcion as 'estado', " +
                                                        "  con.observaciones as 'observaciones' " +
                                                        "  FROM Contrato con  " +
                                                        "  join Presupuesto p on p.idPresupuesto = con.idPresupuesto " +
                                                        "  join Usuario u on p.idUsuarioCliente = u.idUsuario " +
                                                        "  join Persona per on per.idPersona = u.idPersona " +
                                                        "  join EstadoContrato es on es.idEstadoPedido = con.idEstado " +
                                                        "  join Usuario us on con.idUsuarioVendedor = us.idUsuario " +
                                                        "  join Persona pers on pers.idPersona = us.idPersona " +
                                                        "  WHERE u.idUsuario = ? " +
                                                        "  and es.idEstadoPedido <> 5 " +
                                                        "  and con.vigente = 'true' and con.saldo > 0 " +
                                                        "  ORDER BY con.idContrato DESC");
            st.setInt(1, idCliente);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idContrato = rs.getInt("idContrato");
                int idPresupuesto = rs.getInt("idPresupuesto");
                String fechaEmision = rs.getString("fechaEmision");
                String vendedor = rs.getString("vendedor");
                double saldo = rs.getDouble("saldo");
                String estado = rs.getString("estado");
                String observaciones = rs.getString("observaciones");
                
                DTOContrato contrato = new DTOContrato(idContrato,gestorP.obtenerDTOPresupuestoPorId(idPresupuesto),fechaEmision, vendedor, saldo, estado, true,observaciones);
                lista.add(contrato);
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
    public DTOContrato obtenerDTOContratoPorId(int idContrato)
    {
        DTOContrato dtoContrato = null;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("  SELECT con.fechaEmision as 'fechaEmision', " +
                                                        "  pers.nombre as 'vendedor', " +
                                                        "  con.idPresupuesto as 'idPresupuesto', " +
                                                        "  con.saldo as 'saldo', " +
                                                        "  es.descripcion as 'estado' ," +
                                                        "  con.observaciones as 'observaciones' " +
                                                        "  FROM Contrato con  " +
                                                        "  join Presupuesto p on p.idPresupuesto = con.idPresupuesto " +
                                                        "  join Usuario u on p.idUsuarioCliente = u.idUsuario " +
                                                        "  join Persona per on per.idPersona = u.idPersona " +
                                                        "  join EstadoContrato es on es.idEstadoPedido = con.idEstado " +
                                                        "  join Usuario us on con.idUsuarioVendedor = us.idUsuario " +
                                                        "	join Persona pers on pers.idPersona = us.idPersona" +
                                                        "  WHERE con.idContrato = ? and con.saldo >= 0 ");
            st.setInt(1, idContrato);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                int idPresupuesto = rs.getInt("idPresupuesto");
                String fechaEmision = rs.getString("fechaEmision");
                String vendedor = rs.getString("vendedor");
                double saldo = rs.getDouble("saldo");
                String estado = rs.getString("estado");
                String observaciones = rs.getString("observaciones");
                
                dtoContrato = new DTOContrato(idContrato,gestorP.obtenerDTOPresupuestoPorId(idPresupuesto),fechaEmision, vendedor, saldo, estado, true,observaciones);
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
        
        return dtoContrato;
    }
    
    public void condicionContrato(int idContrato, boolean vigente, int idEstado)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Contrato "
                                                            + " SET vigente = ? ,"
                                                            + "     idEstado = ?"
                                                            + " WHERE idContrato = ?");
            
            ps.setBoolean(1, vigente);
            ps.setInt(2, idEstado);
            ps.setInt(3, idContrato);
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
   
    public void actualizarSaldoContrato(double cobro,int idContrato)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Contrato "
                                                            + " SET saldo = saldo - ? "
                                                            + " WHERE idContrato = ?");
            
            ps.setDouble(1, cobro);
            ps.setInt(2, idContrato);
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
    public ArrayList<VMDeuda> obtenerDeuda(int idCliente)
    {
        ArrayList<VMDeuda> lista = new ArrayList<VMDeuda>();
        
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("  SELECT  c.idContrato, es.descripcion, c.saldo " +
                                                            "FROM Contrato c " +
                                                            "join EstadoContrato es on es.idEstadoPedido = c.idEstado " +
                                                            "JOIN Presupuesto pre on pre.idPresupuesto = c.idPresupuesto " +
                                                            "join Usuario u on pre.idUsuarioCliente = u.idUsuario " +
                                                            "join Persona p on p.idPersona = u.idPersona " +
                                                            "where u.idUsuario = ? and c.saldo > 0 ");
            st.setInt(1, idCliente);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idContrato = rs.getInt("idContrato");
                String descripcion = rs.getString("descripcion");
                double saldo = rs.getDouble("saldo");
                
                VMDeuda deuda = new VMDeuda(idContrato,descripcion, saldo);
                lista.add(deuda);
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
    
    public ArrayList<DTOContrato> obtenerHistorialAlquileresCliente(int idCliente)
    {
        ArrayList<DTOContrato> lista = new ArrayList<DTOContrato>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("  SELECT con.idContrato as 'idContrato', " +
                                                        "  con.fechaEmision as 'fechaEmision', " +
                                                        "  con.idUsuarioVendedor as 'idVendedor', " +
                                                        "  pers.nombre as 'vendedor', "+
                                                        "  con.idPresupuesto as 'idPresupuesto', " +
                                                        "  con.saldo as 'saldo', " +
                                                        "  es.descripcion as 'estado', " +
                                                        "  con.observaciones as 'observaciones' " +
                                                        "  FROM Contrato con  " +
                                                        "  join Presupuesto p on p.idPresupuesto = con.idPresupuesto " +
                                                        "  join Usuario u on p.idUsuarioCliente = u.idUsuario " +
                                                        "  join Persona per on per.idPersona = u.idPersona " +
                                                        "  join EstadoContrato es on es.idEstadoPedido = con.idEstado " +
                                                        "  join Usuario us on con.idUsuarioVendedor = us.idUsuario " +
                                                        "  join Persona pers on pers.idPersona = us.idPersona " +
                                                        "  WHERE u.idUsuario = ? " +
                                                        "  and es.idEstadoPedido <> 5 " +
                                                        "  and con.vigente = 'false' and con.saldo > 0 " +
                                                        "  ORDER BY con.idContrato DESC");
            st.setInt(1, idCliente);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idContrato = rs.getInt("idContrato");
                int idPresupuesto = rs.getInt("idPresupuesto");
                String fechaEmision = rs.getString("fechaEmision");
                String vendedor = rs.getString("vendedor");
                double saldo = rs.getDouble("saldo");
                String estado = rs.getString("estado");
                String observaciones = rs.getString("observaciones");
                
                DTOContrato contrato = new DTOContrato(idContrato,gestorP.obtenerDTOPresupuestoPorId(idPresupuesto),fechaEmision, vendedor, saldo, estado, true,observaciones);
                lista.add(contrato);
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

