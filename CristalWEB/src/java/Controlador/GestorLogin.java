/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTOUsuario;
import Modelo.Dni;
import Modelo.TipoUsuario;
import Modelo.Usuario;
import Modelo.VMCliente;
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
public class GestorLogin {
    
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

    public void agregarUsuario(Usuario usuario)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Usuario (email, pass,idPersona,idTipoUsuario, vigente ) VALUES (?,?,?,?,?)");
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPass());
            ps.setInt(3, usuario.getIdPersona());
            ps.setInt(4, usuario.getIdTipoUsuario());
            ps.setBoolean(5, usuario.isVigente());
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
    
    public ArrayList<DTOUsuario> obtenerUsuarios()
    {
        GestorPersona gestoPer = new GestorPersona();
        ArrayList<DTOUsuario> lista = new ArrayList<DTOUsuario>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT idUsuario, email, pass, idPersona, idTipoUsuario FROM Usuario WHERE vigente = 'true'");
            while(rs.next())
            {
                int id = rs.getInt("idUsuario");
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                int idPersona = rs.getInt("idPersona");
                int idTipoUsuario = rs.getInt("idTipoUsuario");
                
                DTOUsuario dtoUsuario = new DTOUsuario(id,email,pass, gestoPer.obtenerPersonaPorId(idPersona),idTipoUsuario);
                lista.add(dtoUsuario);
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
    public ArrayList<DTOUsuario> obtenerUsuariosPorTipo(String descripcion)
    {
        GestorPersona gestoPer = new GestorPersona();
        ArrayList<DTOUsuario> lista = new ArrayList<DTOUsuario>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT u.idUsuario, u.email, u.pass, u.idPersona, u.idTipoUsuario FROM Usuario u JOIN TipoUsuario t ON u.idTipoUsuario=t.idTipoUsuario WHERE t.descripcion = ? ");
            st.setString(1, descripcion);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("idUsuario");
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                int idPersona = rs.getInt("idPersona");
                int idTipoUsuario = rs.getInt("idTipoUsuario");
                
                DTOUsuario dtoUsuario = new DTOUsuario(id,email,pass, gestoPer.obtenerPersonaPorId(idPersona),idTipoUsuario);
                lista.add(dtoUsuario);
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
    
    public DTOUsuario obtenerUsuarioPorId(int idUsuario)
    {
        GestorPersona gestoPer = new GestorPersona();
        DTOUsuario dtoUsuario = null;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT email, pass, idPersona, idTipoUsuario FROM Usuario WHERE idUsuario = ? ");
            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                int idPersona = rs.getInt("idPersona");
                int idTipoUsuario = rs.getInt("idTipoUsuario");
                
                dtoUsuario = new DTOUsuario(idUsuario,email,pass, gestoPer.obtenerPersonaPorId(idPersona),idTipoUsuario);

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
        
        return dtoUsuario;
    }
    
    public DTOUsuario obtenerUsuarioClientePorDni(int dni)
    {
        GestorPersona gestoPer = new GestorPersona();
        DTOUsuario dtoUsuario = null;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT u.idUsuario, u.email, u.pass, u.idPersona, u.idTipoUsuario " +
                                                        "  FROM Usuario u " +
                                                        " JOIN TipoUsuario t ON u.idTipoUsuario=t.idTipoUsuario " +
                                                        "  JOIN Persona p on u.idPersona = p.idPersona " +
                                                        "  WHERE p.dni = ? and t.descripcion = 'CLIENTE'");
            st.setInt(1, dni);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                int idUsuario = rs.getInt("idUsuario");
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                int idPersona = rs.getInt("idPersona");
                int idTipoUsuario = rs.getInt("idTipoUsuario");
                
                dtoUsuario = new DTOUsuario(idUsuario,email,pass, gestoPer.obtenerPersonaPorId(idPersona),idTipoUsuario);

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
        
        return dtoUsuario;
    }
    
    public ArrayList<TipoUsuario> obtenerTipoUsuarios()
    {
        ArrayList<TipoUsuario> lista = new ArrayList<TipoUsuario>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT idTipoUsuario, descripcion FROM TipoUsuario ORDER BY 2");
            while(rs.next())
            {
                int idTipoUsuario = rs.getInt("idTipoUsuario");
                String descripcion = rs.getString("descripcion");
                
                TipoUsuario tipoUsuario = new TipoUsuario(idTipoUsuario,descripcion);
                lista.add(tipoUsuario);
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
    
    public ArrayList<Dni> obtenerDnis()
    {
        ArrayList<Dni> lista = new ArrayList<Dni>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("  SELECT p.dni " +
                                            "  FROM Usuario u " +
                                            "  JOIN Persona p on u.idPersona = p.idPersona");
            while(rs.next())
            {
                int dni = rs.getInt("dni");
                Dni d = new Dni(dni);
                lista.add(d);
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
    
    public String obtenerDescripcionUsuarioPorId(int idUsuario)
    {
        String nombreTipo = "";
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT tu.descripcion" +
                                                        "  FROM Usuario u " +
                                                        "  join TipoUsuario tu on u.idTipoUsuario = tu.idTipoUsuario" +
                                                        "  WHERE u.idUsuario = ?");
            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                nombreTipo = rs.getString("descripcion");
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
        
        return nombreTipo;
    }
    public int obtenerUsuarioPorNombre(String descripcion)
    {
        int idUsuario = 0;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT u.idUsuario" +
                                                        "  FROM Usuario u " +
                                                        "  join TipoUsuario tu on u.idTipoUsuario = tu.idTipoUsuario" +
                                                        "  WHERE tu.descripcion = ? ");
            st.setString(1, descripcion);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                idUsuario = rs.getInt("idUsuario");
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
        
        return idUsuario;
    }
    
    public String obtenerUltimoTerminoYCondicion()
    {
        String termino = "";
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT TOP 1 descripcion " +
                                                "FROM TerminoYCondicion " +
                                               " ORDER BY idTermino DESC");
            if(rs.next())
            {
                termino = rs.getString("descripcion");
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
        
        return termino;
    }
    
    public void agregarTerminoYCondicion(String termino)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO TerminoYCondicion (descripcion) VALUES (?)");
            ps.setString(1, termino);
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
    
    public void actualizarPassword(String pass,int idUsuario)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Usuario SET pass= ? WHERE idUsuario = ?");
            
            ps.setString(1, pass);
            ps.setInt(2, idUsuario);
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
    public void actualizarTipoUsuario(int idTipoUsuario,int idUsuario)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Usuario SET idTipoUsuario= ? WHERE idUsuario = ?");
            
            ps.setInt(1, idTipoUsuario);
            ps.setInt(2, idUsuario);
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
    
    public void vigenciaCuenta (boolean vigencia, int idUsuario)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Usuario SET vigente=? WHERE idUsuario = ?");
            
            ps.setBoolean(1, vigencia);
            ps.setInt(2, idUsuario);
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
    
    public ArrayList<VMCliente> obtenerClientesPorMontoAlquilado(String fechaA,String fechaB)
    {
        ArrayList<VMCliente> lista = new ArrayList<VMCliente>();
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("select u.idUsuario, "
                                            + "per.nombre, "
                                            + "per.apellido, "
                                            + "per.dni, "
                                            + "sum(p.importeTotal) as totalAlquilado " +
                                            "from Usuario u " +
                                            "join Presupuesto p on u.idUsuario = p.idUsuarioCliente " +
                                            "join Persona per on u.idPersona = per.idPersona " +
                                            "join Contrato c on c.idPresupuesto = p.idPresupuesto " +
                                            "where u.idTipoUsuario = 1 and c.fechaEmision between ? and ? " +
                                            "group by u.idUsuario, per.nombre, per.apellido, per.dni " +
                                            "order by totalAlquilado desc");
            st.setString(1, fechaA);
            st.setString(2, fechaB);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int idUsuario = rs.getInt("idUsuario");
                int dni = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                double totalAlquilado = rs.getDouble("totalAlquilado");
                
                VMCliente vmCliente = new VMCliente(idUsuario,nombre,apellido,dni, totalAlquilado);
                lista.add(vmCliente);
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

