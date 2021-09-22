
package Servlet;


import Controlador.GestorContrato;
import Controlador.GestorDetallePresupuesto;
import Controlador.GestorLogin;
import Controlador.GestorPresupuesto;
import java.io.IOException;
import Modelo.Contrato;
import Modelo.DTOContrato;
import Modelo.DTOUsuario;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gonza
 */
@WebServlet(name = "Contratos", urlPatterns = {"/Contratos"})
public class Contratos extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getSession().getAttribute("usr")!= null)
        {
            GestorLogin gestorL = new GestorLogin();
            GestorContrato gestorCON = new GestorContrato();
            GestorPresupuesto gestorP = new GestorPresupuesto ();
            GestorDetallePresupuesto gestorDP = new GestorDetallePresupuesto();
            String modo = request.getParameter("modo");
            
            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
            String descripcionTipo = gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());
            
            int idUsuarioCliente = 0;
            if(descripcionTipo.equals("ADMINISTRADOR")||descripcionTipo.equals("VENDEDOR")){
                if(request.getSession().getAttribute("cliente")!=null)
                {
                    idUsuarioCliente = ((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
                    request.setAttribute("contratosCliente", gestorCON.obtenerContratosPorCliente(idUsuarioCliente));
                }
                else request.setAttribute("contratosCliente", gestorCON.obtenerContratos());
            }
            else 
            {
                idUsuarioCliente = dtoUsuario.getId();
                request.getSession().setAttribute("cliente",dtoUsuario);
                request.setAttribute("contratosCliente", gestorCON.obtenerContratosPorCliente(idUsuarioCliente));
            }

            int idPresupuesto=0;
            if(request.getParameter("idPresupuesto")!=null)
            idPresupuesto = Integer.parseInt(request.getParameter("idPresupuesto"));
            
            int idContrato=0;
            if(request.getParameter("idContrato")!=null)
            idContrato = Integer.parseInt(request.getParameter("idContrato"));
            
            String venta = "";
            if(gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()).equals("CLIENTE"))
            venta="ONLINE";
            else venta=gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());
            
            if(modo == null || modo.isEmpty())
            {
                request.setAttribute("mensaje", "");
                RequestDispatcher rd = request.getRequestDispatcher("/listadoContratos.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("alta"))
            {
                request.setAttribute("vistaON","true");
                request.setAttribute("idCliente",idUsuarioCliente);
                request.setAttribute("tipoVenta",venta);
                request.setAttribute("idPresupuesto",idPresupuesto );
                request.setAttribute("saldo", gestorP.obtenerPresupuestoPorId(idPresupuesto).getImporteTotal());
                RequestDispatcher rd = request.getRequestDispatcher("/nuevoContrato.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("detalle"))
            {
                DTOContrato dtoContrato = gestorCON.obtenerDTOContratoPorId(idContrato);
                request.setAttribute("dtoContrato", dtoContrato);
                request.setAttribute("dtoPresupuesto", gestorP.obtenerDTOPresupuestoPorId(dtoContrato.getDTOPresupuesto().getId()));
                request.setAttribute("detallePresupuesto", gestorDP.obtenerDetallePresupuestoPorId(dtoContrato.getDTOPresupuesto().getId()));
                RequestDispatcher rd = request.getRequestDispatcher("/detalleContrato.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("cancelar"))
            {
                gestorCON.condicionContrato(idContrato, false,6);
                RequestDispatcher rd = request.getRequestDispatcher("/listadoContratos.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("restaurar"))
            {
                gestorCON.condicionContrato(idContrato,true,1);
                RequestDispatcher rd = request.getRequestDispatcher("/listadoContratos.jsp");
                rd.forward(request, response);
            }
            
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
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
        
            GestorContrato gestorCON = new GestorContrato();
            GestorLogin gestorL = new GestorLogin();
            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaEmision = dtf4.format(LocalDateTime.now());
            
            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
            int idUsuarioVendedor = 0;
            if(gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()).equals("CLIENTE"))
            idUsuarioVendedor=gestorL.obtenerUsuarioPorNombre("ONLINE");
            else idUsuarioVendedor=dtoUsuario.getId();

            int idPresupuesto = Integer.parseInt(request.getParameter("idPresupuesto"));
            double saldo = Double.parseDouble(request.getParameter("saldo"));
            String observaciones = (String)request.getParameter("observaciones");

            Contrato contrato = new Contrato(0,fechaEmision, idUsuarioVendedor, idPresupuesto, saldo, 1,true,observaciones);
            gestorCON.agregarContrato(contrato);
           
            String descripcionTipo = gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());
            int idUsuarioCliente = 0;
            if(descripcionTipo.equals("ADMINISTRADOR")||descripcionTipo.equals("VENDEDOR")) {
                if(request.getParameter("idCliente")!=null) idUsuarioCliente  = Integer.parseInt(request.getParameter("idCliente"));}
            else idUsuarioCliente = dtoUsuario.getId();
            
            request.getSession().setAttribute("cliente", gestorL.obtenerUsuarioPorId(idUsuarioCliente));
            request.setAttribute("contratosCliente", gestorCON.obtenerContratosPorCliente(idUsuarioCliente));
            RequestDispatcher rd = request.getRequestDispatcher("/listadoContratos.jsp");
            rd.forward(request, response);

        }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
