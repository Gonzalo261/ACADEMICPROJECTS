
package Controlador;

import Modelo.DTOPresupuesto;
import Modelo.Presupuesto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class GestorPresupuesto {
    
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
    
    public void agregarPresupuesto(Presupuesto p)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Presupuesto (idUsuarioCliente , fechaEmision, importeTotal, fechaInicioEvento , fechaFinEvento, fechaEntrega, fechaRetiro, idCiudadEntrega, idCiudadRetiro, costoFlete,vigente,observaciones) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            
            ps.setInt(1, p.getIdUsuarioCliente());
            ps.setString(2, p.getFechaEmision());
            ps.setDouble(3, p.getImporteTotal());
            ps.setString(4, p.getFechaInicioEvento());
            ps.setString(5, p.getFechaFinEvento());
            ps.setString(6, p.getFechaEntrega()); 
            ps.setString(7, p.getFechaRetiro());
            ps.setInt(8, p.getLugarEntrega());
            ps.setInt(9, p.getLugarRetiro());
            ps.setDouble(10,p.getCostoFlete());
            ps.setBoolean(11,p.getVigente());
            ps.setString(12, p.getObservaciones());
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
    
    public int obtenerIdUltimoPresupuestoCreado()
    {
        int id = 0;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT TOP 1 idPresupuesto FROM Presupuesto ORDER BY 1 DESC");
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                id = rs.getInt("idPresupuesto");
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
    
    public void actualizarImportePresupuesto(double importeTotal,int idPresupuesto)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Presupuesto "
                                                            + " SET importeTotal = importeTotal + ? "
                                                            + " WHERE idPresupuesto = ?");
            
            ps.setDouble(1, importeTotal);
            ps.setInt(2, idPresupuesto);
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
    
    public void actualizarPresupuesto(Presupuesto presupuesto)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Presupuesto SET "
                                                                    + "fechaEntrega = ? , fechaRetiro = ? , "
                                                                      + "idCiudadEntrega = ? , idCiudadRetiro = ? , "
                                                                        + "costoFlete= ? , observaciones=?, "
                                                                         + "fechaInicioEvento = ? , fechaFinEvento = ?, fechaEmision = ? "
                                                                            + " WHERE idPresupuesto = ?");
            
            ps.setString(1, presupuesto.getFechaEntrega());
            ps.setString(2, presupuesto.getFechaRetiro());
            ps.setInt(3, presupuesto.getLugarEntrega());
            ps.setInt(4, presupuesto.getLugarRetiro());
            ps.setDouble(5, presupuesto.getCostoFlete());
            ps.setString(6, presupuesto.getObservaciones());
            ps.setString(7, presupuesto.getFechaInicioEvento());
            ps.setString(8, presupuesto.getFechaFinEvento());
            ps.setString(9, presupuesto.getFechaEmision());
            ps.setInt(10, presupuesto.getId());
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
    
    public void eliminarPresupuesto(int idPresupuesto)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("DELETE Presupuesto WHERE idPresupuesto = ?"); 
            ps.setInt(1, idPresupuesto);
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
    
    public ArrayList<DTOPresupuesto> obtenerPresupuestosActivos()
    {
        ArrayList<DTOPresupuesto> lista = new ArrayList<DTOPresupuesto>();
        try
        {
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.idPresupuesto, " +
                                                        "		p.fechaEmision, " +
                                                        "		per.nombre + ' ' + per.apellido as 'nombreCliente' , " +
                                                        "		p.importeTotal, " +
                                                        "		p.fechaInicioEvento," +
                                                        "		p.fechaFinEvento," +
                                                        "		p.fechaEntrega," +
                                                        "		p.fechaRetiro, " +
                                                        "		ci.descripcion, " +
                                                        "		p.costoFlete, " +
                                                        "		p.vigente, " +
                                                        "		p.observaciones " +
                                                        "FROM Presupuesto p " +
                                                        "join Ciudad ci on ci.idCiudad = p.idCiudadEntrega " +
                                                        "left join Contrato c on c.idPresupuesto = p.idPresupuesto " +
                                                        "join Usuario u on u.idUsuario = p.idUsuarioCliente " +
                                                        "join Persona per on per.idPersona = u.idPersona " +
                                                        "WHERE p.vigente = 'true' and c.idContrato is null " +
                                                        "ORDER BY 2 DESC");
            while(rs.next())
            {
                int idPresupuesto = rs.getInt("idPresupuesto");
                String fechaEmision = rs.getString("fechaEmision");
                String nombreCliente = rs.getString("nombreCliente");
                double importeTotal = rs.getDouble("importeTotal");
                String fechaInicioEvento = rs.getString("fechaInicioEvento");
                String fechaFinEvento = rs.getString("fechaFinEvento");
                String fechaEntrega = rs.getString("fechaEntrega");
                String fechaRetiro = rs.getString("fechaRetiro");
                String lugarEntrega = rs.getString("descripcion");
                double costoFlete = rs.getDouble("costoFlete");
                boolean vigente = rs.getBoolean("vigente");
                String observaciones = rs.getString("observaciones");
                
                DTOPresupuesto p = new DTOPresupuesto(idPresupuesto,fechaEmision,nombreCliente, importeTotal, fechaInicioEvento,fechaFinEvento,fechaEntrega,fechaRetiro,lugarEntrega,costoFlete,vigente,observaciones);
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
    
    public ArrayList<DTOPresupuesto> obtenerPresupuestosActivosPorCliente(int idCli)
    {
        ArrayList<DTOPresupuesto> lista = new ArrayList<DTOPresupuesto>();
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("SELECT p.idPresupuesto, " +
                                                        "		p.fechaEmision, " +
                                                        "		per.nombre + ' ' + per.apellido as 'nombreCliente' , " +
                                                        "		p.importeTotal, " +
                                                        "		p.fechaInicioEvento," +
                                                        "		p.fechaFinEvento," +
                                                        "		p.fechaEntrega," +
                                                        "		p.fechaRetiro, " +
                                                        "		ci.descripcion, " +
                                                        "		p.costoFlete, " +
                                                        "		p.vigente, " +
                                                        "		p.observaciones " +
                                                        "FROM Presupuesto p " +
                                                        "join Ciudad ci on ci.idCiudad = p.idCiudadEntrega " +
                                                        "left join Contrato c on c.idPresupuesto = p.idPresupuesto " +
                                                        "join Usuario u on u.idUsuario = p.idUsuarioCliente " +
                                                        "join Persona per on per.idPersona = u.idPersona " +
                                                        "WHERE p.idUsuarioCliente = ? and p.vigente = 'true' and c.idContrato is null " +
                                                        "ORDER BY 2 DESC");
            ps.setInt(1, idCli);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int idPresupuesto = rs.getInt("idPresupuesto");
                String fechaEmision = rs.getString("fechaEmision");
                String nombreCliente = rs.getString("nombreCliente");
                double importeTotal = rs.getDouble("importeTotal");
                String fechaInicioEvento = rs.getString("fechaInicioEvento");
                String fechaFinEvento = rs.getString("fechaFinEvento");
                String fechaEntrega = rs.getString("fechaEntrega");
                String fechaRetiro = rs.getString("fechaRetiro");
                String lugarEntrega = rs.getString("descripcion");
                double costoFlete = rs.getDouble("costoFlete");
                boolean vigente = rs.getBoolean("vigente");
                String observaciones = rs.getString("observaciones");
                
                DTOPresupuesto p = new DTOPresupuesto(idPresupuesto,fechaEmision,nombreCliente, importeTotal, fechaInicioEvento,fechaFinEvento,fechaEntrega,fechaRetiro,lugarEntrega,costoFlete,vigente,observaciones);
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
    public ArrayList<DTOPresupuesto> obtenerPresupuestosInactivosPorCliente(int idCli)
    {
        ArrayList<DTOPresupuesto> lista = new ArrayList<DTOPresupuesto>();
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("SELECT p.idPresupuesto, " +
                                                        "		p.fechaEmision, " +
                                                        "		per.nombre + ' ' + per.apellido as 'nombreCliente' , " +
                                                        "		p.importeTotal, " +
                                                        "		p.fechaInicioEvento," +
                                                        "		p.fechaFinEvento," +
                                                        "		p.fechaEntrega," +
                                                        "		p.fechaRetiro, " +
                                                        "		ci.descripcion, " +
                                                        "		p.costoFlete, " +
                                                        "		p.vigente, " +
                                                        "		p.observaciones " +
                                                        "FROM Presupuesto p " +
                                                        "join Ciudad ci on ci.idCiudad = p.idCiudadEntrega " +
                                                        "left join Contrato c on c.idPresupuesto = p.idPresupuesto " +
                                                        "join Usuario u on u.idUsuario = p.idUsuarioCliente " +
                                                        "join Persona per on per.idPersona = u.idPersona " +
                                                        "WHERE p.idUsuarioCliente = ? and ( c.idContrato is not null or p.vigente = 'false') " +
                                                        "ORDER BY 2 DESC");
            ps.setInt(1, idCli);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int idPresupuesto = rs.getInt("idPresupuesto");
                String fechaEmision = rs.getString("fechaEmision");
                String nombreCliente = rs.getString("nombreCliente");
                double importeTotal = rs.getDouble("importeTotal");
                String fechaInicioEvento = rs.getString("fechaInicioEvento");
                String fechaFinEvento = rs.getString("fechaFinEvento");
                String fechaEntrega = rs.getString("fechaEntrega");
                String fechaRetiro = rs.getString("fechaRetiro");
                String lugarEntrega = rs.getString("descripcion");
                double costoFlete = rs.getDouble("costoFlete");
                boolean vigente = rs.getBoolean("vigente");
                String observaciones = rs.getString("observaciones");
                
                DTOPresupuesto p = new DTOPresupuesto(idPresupuesto,fechaEmision,nombreCliente, importeTotal, fechaInicioEvento,fechaFinEvento,fechaEntrega,fechaRetiro,lugarEntrega,costoFlete,vigente,observaciones);
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
    public Presupuesto obtenerPresupuestoPorId(int idPresupuesto)
    {
        Presupuesto p = null;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT * FROM Presupuesto WHERE idPresupuesto = ?");
            st.setInt(1, idPresupuesto);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                int idUsuarioCliente = rs.getInt("idUsuarioCliente");
                String fechaEmision = rs.getString("fechaEmision");
                double importeTotal = rs.getDouble("importeTotal");
                String fechaInicioEvento = rs.getString("fechaInicioEvento");
                String fechaFinEvento = rs.getString("fechaFinEvento");
                String fechaEntrega = rs.getString("fechaEntrega");
                String fechaRetiro = rs.getString("fechaRetiro");
                int idCiudadEntrega = rs.getInt("idCiudadEntrega");
                int idCiudadRetiro = rs.getInt("idCiudadRetiro");
                double costoFlete = rs.getDouble("costoFlete");
                boolean vigente = rs.getBoolean("vigente");
                String observaciones = rs.getString("observaciones");
                
                p = new Presupuesto(idPresupuesto,fechaEmision,idUsuarioCliente, importeTotal,fechaInicioEvento,fechaFinEvento,fechaEntrega,fechaRetiro,idCiudadEntrega,idCiudadRetiro,costoFlete,vigente,observaciones);
                
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
        
        return p;
    }
    
    public DTOPresupuesto obtenerDTOPresupuestoPorId(int idPresupuesto)
    {
        DTOPresupuesto p = null;
        try
        {
            abrirConexion();
            PreparedStatement st = con.prepareStatement("SELECT p.fechaEmision, "+
                                                "               per.nombre + ' ' + per.apellido as 'nombreCliente' , " +
                                                "		p.importeTotal, " +
                                                "		p.fechaInicioEvento, " +
                                                "		p.fechaFinEvento, " +
                                                "		p.fechaEntrega, " +
                                                "		p.fechaRetiro, " +
                                                "		c.descripcion, " +
                                                "		p.costoFlete, " +
                                                "		p.vigente, " +
                                                "		p.observaciones " +
                                                "FROM Presupuesto p " +
                                                "join Ciudad c on c.idCiudad = p.idCiudadEntrega " +
                                                "join Usuario u on u.idUsuario = p.idUsuarioCliente  " +
                                                "join Persona per on per.idPersona = u.idPersona " +
                                                "WHERE p.idPresupuesto = ?");
            st.setInt(1, idPresupuesto);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                String fechaEmision = rs.getString("fechaEmision");
                String nombreCliente = rs.getString("nombreCliente");
                double importeTotal = rs.getDouble("importeTotal");
                String fechaInicioEvento = rs.getString("fechaInicioEvento");
                String fechaFinEvento = rs.getString("fechaFinEvento");
                String fechaEntrega = rs.getString("fechaEntrega");
                String fechaRetiro = rs.getString("fechaRetiro");
                String lugarEntrega = rs.getString("descripcion");
                double costoFlete = rs.getDouble("costoFlete");
                boolean vigente = rs.getBoolean("vigente");
                String observaciones = rs.getString("observaciones");
                
                p = new DTOPresupuesto (idPresupuesto,fechaEmision,nombreCliente, importeTotal,fechaInicioEvento,fechaFinEvento,fechaEntrega,fechaRetiro,lugarEntrega,costoFlete,vigente,observaciones);
                
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
        
        return p;
    }
    
    
    
    public void vigenciaPresupuesto(int idPresupuesto, boolean vigente)
    {
        try
        {
            abrirConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Presupuesto "
                                                            + " SET vigente = ? "
                                                            + " WHERE idPresupuesto = ?");
            
            ps.setBoolean(1, vigente);
            ps.setInt(2, idPresupuesto);
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
    
    
}
