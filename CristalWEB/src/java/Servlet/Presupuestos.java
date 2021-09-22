
package Servlet;

import Controlador.GestorArticulo;
import Controlador.GestorCiudad;
import Controlador.GestorDetallePresupuesto;
import Controlador.GestorLogin;
import Controlador.GestorPresupuesto;
import Modelo.DetallePresupuesto;
import Modelo.Presupuesto;
import Modelo.Carrito;
import Modelo.DTOPresupuesto;
import Modelo.DTOUsuario;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Presupuestos", urlPatterns = {"/Presupuestos"})
public class Presupuestos extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        if(request.getSession().getAttribute("usr")!= null)
        {
            GestorLogin gestorL = new GestorLogin();
            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
            String descripcionTipo = gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());
            String modo = request.getParameter("modo");
            GestorPresupuesto gestorP = new GestorPresupuesto ();
            GestorArticulo gestorA = new GestorArticulo ();
            GestorCiudad gestorC =  new GestorCiudad();
            GestorDetallePresupuesto gestorDP = new GestorDetallePresupuesto();
            
            int idUsuarioCliente = 0;
            if(descripcionTipo.equals("ADMINISTRADOR")||descripcionTipo.equals("VENDEDOR")){
                if(request.getSession().getAttribute("cliente")!=null)
                {
                    idUsuarioCliente = ((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
                    request.setAttribute("presupuestosCliente", gestorP.obtenerPresupuestosActivosPorCliente(idUsuarioCliente));
                }
                else request.setAttribute("presupuestosCliente", gestorP.obtenerPresupuestosActivos());
            }
            else 
            {
                idUsuarioCliente = dtoUsuario.getId();
                request.getSession().setAttribute("cliente",dtoUsuario);
                request.setAttribute("presupuestosCliente", gestorP.obtenerPresupuestosActivosPorCliente(idUsuarioCliente));
            }
            
            int idPresupuesto=0;
            if(request.getParameter("idPresupuesto")!=null)
            idPresupuesto = Integer.parseInt(request.getParameter("idPresupuesto"));
            
            if(modo == null || modo.isEmpty())
            {
                request.setAttribute("mensaje", "");
                
                RequestDispatcher rd = request.getRequestDispatcher("/listadoPresupuestos.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("historicos"))
            {
                request.setAttribute("vistaON","true");
                request.setAttribute("presupuestosCliente", gestorP.obtenerPresupuestosInactivosPorCliente(idUsuarioCliente));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoPresupuestosHistoricos.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("cancelar"))
            {
                
                gestorP.vigenciaPresupuesto(idPresupuesto,false);
                RequestDispatcher rd = request.getRequestDispatcher("/listadoPresupuestos.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("detalle"))
            {
                DTOPresupuesto dTOPresupuesto = gestorP.obtenerDTOPresupuestoPorId(idPresupuesto);
                request.setAttribute("dTOPresupuesto", dTOPresupuesto);
                request.setAttribute("idPresupuesto", idPresupuesto);
                request.setAttribute("detalle", gestorDP.obtenerDetallePresupuestoPorId(idPresupuesto));
                RequestDispatcher rd = request.getRequestDispatcher("/detallePresupuesto.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("editar"))
            {
                request.setAttribute("vistaON","true");
                request.setAttribute("accion", "Edición");
                request.setAttribute("ciud", gestorC.obtenerCiudades());
                Presupuesto presupuesto = gestorP.obtenerPresupuestoPorId(idPresupuesto);
                request.setAttribute("modeloPresupuesto", presupuesto);
                RequestDispatcher rd = request.getRequestDispatcher("/editarPresupuesto.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("alta"))
            {
                request.setAttribute("vistaON","true");
                request.setAttribute("idCliente",idUsuarioCliente);
                request.setAttribute("ciud", gestorC.obtenerCiudades());
                request.setAttribute("art", gestorA.obtenerArticulos());
                RequestDispatcher rd = request.getRequestDispatcher("/nuevoPresupuesto.jsp");
                rd.forward(request, response);
            }
        }
        else
        {
            RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
            rd.forward(request, response);
        }
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            GestorPresupuesto gestorP = new GestorPresupuesto ();
            GestorDetallePresupuesto gestorDP =new GestorDetallePresupuesto();
            GestorCiudad gestorC =  new GestorCiudad();
            GestorLogin gestorL = new GestorLogin();
            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String id = (String)(request.getParameter("idPresupuesto")) ;
            String fechaEmision = dtf4.format(LocalDateTime.now()); 
            
            int idUsuarioCliente=0;
            if(request.getSession().getAttribute("cliente")!=null)
                idUsuarioCliente=((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
            
            ArrayList<Carrito> listaCarrito = null;
            if(request.getSession().getAttribute("carrito")!=null)
                listaCarrito = (ArrayList)request.getSession().getAttribute("carrito");
            
            String fechaInicioEvento = (String)request.getParameter("fechaInicioEvento");
            String fechaFinEvento = (String)request.getParameter("fechaFinEvento");
            String fechaEntrega = (String)request.getParameter("fechaEntrega");
            String fechaRetiro = (String)request.getParameter("fechaRetiro");
            int lugarEntrega = Integer.parseInt(request.getParameter("lugarEntrega"));
            int lugarRetiro = Integer.parseInt(request.getParameter("lugarEntrega"));
            int kmFlete = gestorC.obtenerKmPorIdCiudad(lugarRetiro);
            double costoFlete = kmFlete * 250;
            double importeTotal = costoFlete;
            
            String observaciones ="";
            if(!request.getParameter("observaciones").equals("")) observaciones = (String)request.getParameter("observaciones");

            if(id == null)
            {
                    Presupuesto pres = new Presupuesto(0,fechaEmision, idUsuarioCliente, importeTotal,fechaInicioEvento,fechaFinEvento, fechaEntrega,fechaRetiro, lugarEntrega, lugarRetiro,costoFlete,true,observaciones);
                    gestorP.agregarPresupuesto(pres);
                    int idPresupuesto = gestorP.obtenerIdUltimoPresupuestoCreado();
                    for (Carrito c : listaCarrito) {

                            if(c != null)
                            {
                                int cantidad = c.getCantidad();
                                double importe = cantidad * c.getArticulo().getPrecio();
                                DetallePresupuesto dp = new DetallePresupuesto(0,c.getArticulo().getId(),idPresupuesto,importe,cantidad);
                                gestorDP.agregarDetallePresupuesto(dp);
                            }
                        }
                        gestorP.actualizarImportePresupuesto(gestorDP.obtenerImporteTotalPorIdPresupuesto(idPresupuesto), idPresupuesto);
            }
            else
            {
                int idPresup = Integer.parseInt(id);
                Presupuesto pres = new Presupuesto(idPresup,fechaEmision, idUsuarioCliente, importeTotal, fechaInicioEvento,fechaFinEvento,fechaEntrega,fechaRetiro, lugarEntrega, lugarRetiro,costoFlete,true,observaciones);
                gestorP.actualizarPresupuesto(pres);
            }
            
            request.getSession().setAttribute("carrito",null);
            request.setAttribute("cliente", gestorL.obtenerUsuarioPorId(idUsuarioCliente));
            request.setAttribute("presupuestosCliente", gestorP.obtenerPresupuestosActivosPorCliente(idUsuarioCliente));
            RequestDispatcher rd = request.getRequestDispatcher("/listadoPresupuestos.jsp");
            rd.forward(request, response);

        }
            @Override
        public String getServletInfo() {
            return "Short description";
    }
        
    
}

    
    


