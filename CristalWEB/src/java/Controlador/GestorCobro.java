/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cobro;
import Modelo.DTOCobro;
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
public class GestorCobro {
    
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
    
    public void agregarCobro(Cobro cobro)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Cobro (idContrato, idUsuarioCajero,fechaCobrado,idFormaCobro, montoCobrado,numeroCheque,fechaCheque,vigente) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, cobro.getIdContrato());
            ps.setInt(2, cobro.getIdUsuarioCobro());
            ps.setString(3, cobro.getFechaCobrado());
            ps.setInt(4, cobro.getIdFormaCobro());
            ps.setDouble(5, cobro.getMontoCobrado());
            ps.setInt(6, cobro.getNumeroCheque());
            ps.setString(7, cobro.getFechaCobrado());
            ps.setBoolean(8, cobro.isVigente());
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
    
    public void eliminarCobro(int idCobro)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Cobro set vigente = 'false' where idCobro= ?"); 
            ps.setInt(1, idCobro);
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
    public ArrayList<DTOCobro> obtenerCobros()
    { 
        ArrayList<DTOCobro> lista = new ArrayList<DTOCobro>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("  select idCobro, " +
                                                "		cob.idContrato," +
                                                "		per.nombre as 'cajero'," +
                                                "		cob.fechaCobrado," +
                                                "		fc.descripcion," +
                                                "		cob.montoCobrado," +
                                                "		cob.numeroCheque," +
                                                "		cob.fechaCheque" +
                                                "  from Cobro cob " +
                                                "  join Contrato con on con.idContrato = cob.idContrato" +
                                                "  join Usuario u on u.idUsuario = cob.idUsuarioCajero" +
                                                "  join Persona per on u.idPersona = per.idPersona" +
                                                "  join FormaCobro fc on fc.idFormaCobro = cob.idFormaCobro" +
                                                "  where cob.vigente = 'true' " +
                                                "  order by 4 desc");
            while(rs.next())
            {
                int idCobro = rs.getInt("idCobro");
                int idContrato = rs.getInt("idContrato");
                String cajero = rs.getString("cajero");
                String fechaCobrado = rs.getString("fechaCobrado");
                String formaCobro = rs.getString("descripcion");
                double montoCobrado = rs.getDouble("montoCobrado");
                int numeroCheque = rs.getInt("numeroCheque");
                String fechaCheque = rs.getString("fechaCheque");

                DTOCobro dtoCobro = new DTOCobro(idCobro,idContrato,cajero,fechaCobrado,formaCobro,montoCobrado,numeroCheque,fechaCheque, true);
                lista.add(dtoCobro);
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
    
    public ArrayList<DTOCobro> obtenerCobrosPorId(int idUsuarioCliente)
    { 
        ArrayList<DTOCobro> lista = new ArrayList<DTOCobro>();
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("  select idCobro, " +
                                                "		cob.idContrato," +
                                                "		per.nombre as 'cajero'," +
                                                "		cob.fechaCobrado," +
                                                "		fc.descripcion," +
                                                "		cob.montoCobrado," +
                                                "		cob.numeroCheque," +
                                                "		cob.fechaCheque" +
                                                "  from Cobro cob " +
                                                "  join Contrato con on con.idContrato = cob.idContrato" +
                                                "  join Presupuesto pre on pre.idPresupuesto = con.idPresupuesto" +
                                                "  join Usuario u on u.idUsuario = cob.idUsuarioCajero" +
                                                "  JOIN Persona per on per.idPersona = u.idPersona " +
                                                "  join FormaCobro fc on fc.idFormaCobro = cob.idFormaCobro" +
                                                "  where pre.idUsuarioCliente = ? and cob.vigente = 'true' " +
                                                "  order by 4 desc");
            ps.setInt(1, idUsuarioCliente);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int idCobro = rs.getInt("idCobro");
                int idContrato = rs.getInt("idContrato");
                String cajero = rs.getString("cajero");
                String fechaCobrado = rs.getString("fechaCobrado");
                String formaCobro = rs.getString("descripcion");
                double montoCobrado = rs.getDouble("montoCobrado");
                int numeroCheque = rs.getInt("numeroCheque");
                String fechaCheque = rs.getString("fechaCheque");

                DTOCobro dtoCobro = new DTOCobro(idCobro,idContrato,cajero,fechaCobrado,formaCobro,montoCobrado,numeroCheque,fechaCheque, true);
                lista.add(dtoCobro);
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
    
    public DTOCobro obtenerCobroPorId(int idCobro)
    {
        DTOCobro dtoCobro = null;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("select idCobro, " +
                                                "		cob.idContrato," +
                                                "		per.nombre as 'cajero'," +
                                                "		fechaCobrado," +
                                                "		fc.descripcion," +
                                                "		montoCobrado," +
                                                "		numeroCheque," +
                                                "		fechaCheque" +
                                                "  from Cobro cob " +
                                                "  join Contrato con on con.idContrato = cob.idContrato" +
                                                "  join Presupuesto pre on pre.idPresupuesto = con.idPresupuesto" +
                                                "  join Usuario u on u.idUsuario = cob.idUsuarioCajero" +
                                                "  JOIN Persona per on per.idPersona = u.idPersona " +
                                                "  join FormaCobro fc on fc.idFormaCobro = cob.idFormaCobro" +
                                                "  where cob.idCobro = ? and cob.vigente = 'true' " +
                                                "  order by 3 desc");
            st.setInt(1, idCobro);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                int idContrato = rs.getInt("idContrato");
                String cajero = rs.getString("cajero");
                String fechaCobrado = rs.getString("fechaCobrado");
                String formaCobro = rs.getString("descripcion");
                double montoCobrado = rs.getDouble("montoCobrado");
                int numeroCheque = rs.getInt("numeroCheque");
                String fechaCheque = rs.getString("fechaCheque");

                dtoCobro = new DTOCobro(idCobro,idContrato,cajero,fechaCobrado,formaCobro,montoCobrado,numeroCheque,fechaCheque,true);
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
        
        return dtoCobro;
    }
    
 }

