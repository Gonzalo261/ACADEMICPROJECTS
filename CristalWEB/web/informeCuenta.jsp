<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>

<%@page import="Modelo.Persona"%>
<%@page import="Modelo.VMDeuda"%>
<%@page import="Modelo.DTOUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Mi Cuenta</title>
        <%@include file="/navbar.jsp" %>
    </head>
    
    <body>
        <center>
             <c:choose>
            <c:when test="${not empty usr}">
            <div class="container fluid-3"> 
                <div class="row">
                    <div class="col-7">
                        <br><h3>Mi Historial de Alquileres  </h3><br>
                        <table class="table table-hover table-active table-dark" style="background-color:gray ">
                                    <tr><th>Nº Contrato</th><th>Fecha</th><th>Vendedor  </th><th>Saldo</th><th>Estado</th></tr>
                              <c:choose>
                                    <c:when test="${not empty historial}">
                                    <c:forEach items="${ historial }" var="item">
                                            <tr><td><a style="text-align:center; color:white " onclick="window.location ='Contratos?modo=detalle&idContrato=${ item.idContrato }'" class="btn btn-group-sm">${ item.idContrato }</a></td>
                                                <td>${ item.fechaEmision }</td>
                                                <td>${ item.nombreVendedor }</td> 
                                                <td>$ ${ item.saldo }</td>
                                                <td>${ item.estado }</td>
                                            </tr>
                                        </c:forEach>
                                            </c:when>
                                             <c:otherwise>
                                       <tr><td colspan="8">NO POSEE ALQUILERES CANCELADOS NI FINALIZADOS HASTA EL MOMENTO</td></tr>
                                    </c:otherwise>
                                </c:choose>
                                </table>
                        </div>
                            <br>
                                <div class="col-5">
                                    <br><h3>Mis Deudas </h3><br>
                                    <table class="table table-hover table-active table-danger" style="background-color:gray ">
                                    <tr><th>Nº Contrato</th>
                                        <th>Estado</th>
                                        <th>Saldo </th>
                                        <th>  </th>
                                    </tr>
                                    <c:choose>
                                    <c:when test="${not empty deuda}">
                                            <c:forEach items="${ deuda }" var="item">
                                                 <tr><td><a style="text-align:center; color:white" onclick="abrirVentanaDetalle(${ item.idContrato })" target="_blank" class="btn btn-group-sm">${ item.idContrato }</a></td>
                                                     <td>${ item.estado }</td>
                                                     <td>$ ${ item.saldo }</td>
                                                     <td><a style="text-align:center; color:green " onclick="window.location = 'Cobros?modo=alta&idContrato=${ item.idContrato }';"  class="btn btn-group-sm">Pagar</a></td>
                                                 </tr>
                                               </c:forEach>
                                        </c:when>
                                    <c:otherwise>
                                <tr><td colspan="4"> NO POSEE DEUDAS HASTA EL MOMENTO</td></tr>
                                    </c:otherwise>
                                </c:choose>
                                         </table>
                                    
                                </div>
                    <br>
                </div>
            </div>    
            </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
        </center>
    <script>
        function confirmarEliminacion(){
            
            if(confirm('Estas seguro de eliminar la cuenta?')) 
            {
                window.location = "MisCuentas?modo=eliminarCuenta";
            }
        }
        
    </script>
    <script> 
            function abrirVentanaDetalle (id){ 
               window.open("http://localhost:8080/CristalWEB/Contratos?modo=detalle&idContrato="+id , "Detalle del Contrato" , "width=800,height=400,scrollbars=SI,left=300,top=100");
            } 
            </script>
    
        <%@include file="/footer.jsp" %>
    </body>
</html>
