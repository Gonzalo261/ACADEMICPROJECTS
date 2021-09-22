<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.DTOContrato"%>
<%@page import="Modelo.Carrito"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de alquileres </title>
                <%@include file="/navbar.jsp" %>
    </head>
    <body><center><br> 
        <c:choose>
             <c:when test="${not empty usr}"> 
       
                        <div class="container fluid-5">
                             <div class="row">
                                    <div class="col-8">
                                        <h4 align="left">Lista de alquileres</h4><br>
                                                    </div>
                                    <div class="col-4">
                                        <c:choose>
                                <c:when test="${not empty cliente ||usuario=='CLIENTE'}">
                                        <a style="text-align:center; color:green" onclick="window.location = 'Carritos?idCliente=${ cliente.id }'" class="btn btn-group-sm">Nuevo</a> 
                                                    </c:when> 
                              </c:choose>
                                </div>
                                     
                                     </div>
                                    <div class="row">
                                        <c:choose>
                                        <c:when test="${not empty contratosCliente}"> <br> 
                                            <table class="table table-hover table-active table-dark">
                                                        <tr><th>Nº Contrato</th><th>Fecha</th><th>Vendedor  </th><th>Saldo</th><th>Estado</th><th>  </th></tr>
                                                        <c:forEach items="${ contratosCliente }" var="item">
                                                            <tr><td><a style="text-align:center; color:white " onclick="abrirVentanaDetalle(${ item.idContrato })" class="btn btn-group-sm">${ item.idContrato }</a></td>
                                                                <td>${ item.fechaEmision }</td>
                                                                <td>${ item.nombreVendedor }</td> 
                                                                <td>$ ${ item.saldo }</td>
                                                                <td>${ item.estado }</td>
                                                                <c:choose>
                                                                        <c:when test="${usuario=='CLIENTE'||usuario=='ADMINISTRADOR'||usuario=='CAJERO'}">
                                                                <td><a style="text-align:center; color:green " onclick="window.location = 'Cobros?modo=alta&idContrato=${ item.idContrato }';"  class="btn btn-group-sm">Pagar</a>
                                                                    </c:when>
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${usuario=='CLIENTE'||usuario=='ADMINISTRADOR'}">
                                                                            <a style="text-align:center; color:red " onclick="window.location = 'Contratos?modo=cancelar&idContrato=${ item.idContrato }';"  class="btn btn-group-sm">Eliminar</a>
                                                                        </td>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </tr>
                                                        </c:forEach>
                                                    </table>
                                            </c:when>
                                        </c:choose>
                                        </div>
                            </div>
                    
                         </c:when>
                <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
    </center>
    <%@include file="/footer.jsp" %>
    <script> 
function abrirVentanaDetalle (id){ 
    window.open("http://localhost:8080/CristalWEB/Contratos?modo=detalle&idContrato="+id , "Detalle del Contrato" , "width=800,height=400,scrollbars=SI,left=300,top=100"); 
} 
</script>
    </body>
</html>
