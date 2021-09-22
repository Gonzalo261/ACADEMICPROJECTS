<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.DTOContrato"%>
<%@page import="Modelo.DTOPresupuesto"%>
<%@page import="Modelo.DTODetallePresupuesto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle Contrato</title>
        <%@include file="/navbarReporte.jsp" %>
    </head>
    <body>
         <c:choose>
            <c:when test="${not empty usr}">
        <center>
            <div class="container fluid-5"> 
                <h4 align="left">Contrato Nº: 001-0000${dtoContrato.idContrato}</h4>
                <br>
                <div class="row">
                    <div class="col-12">
                        <h6 align="left">Fecha de emision: ${dtoContrato.fechaEmision}, 
                            Cliente : ${dtoPresupuesto.nombreCliente}, 
                            Vendedor: ${dtoContrato.nombreVendedor}
                        </h6>
                        <h6 align="left">Inicio: ${dtoPresupuesto.fechaInicioEvento}, Fin: ${dtoPresupuesto.fechaFinEvento}, 
                           Entrega: ${dtoPresupuesto.fechaEntrega}, Retiro ${dtoPresupuesto.fechaRetiro}, 
                           Lugar Entrega: ${dtoPresupuesto.lugarEntrega}, 
                           Estado Actual: ${dtoContrato.estado} 
                       </h6>
                        </div> 
                </div> 
                       <br>
                <div class="row">
                    <div class="col-12">
                    <table class="table table-hover table-active table-dark">
                                    <tr><th>Importe Total: $ ${dtoPresupuesto.importeTotal}   </th><th>Costo del flete: ${dtoPresupuesto.costoFlete}   </th><th>Saldo: ${dtoContrato.saldo} </th></tr>
                                    <tr><th>Cantidad   </th><th>Artículo   </th><th>Precio </th><th>Importe </th></tr>
                                    <c:forEach items="${ detallePresupuesto }" var="item">
                                        <tr><td>${ item.cantidad }</td><td>${ item.descripcion }</td> <td> $ ${ item.precio }</td><td> $ ${ item.precio*item.cantidad }</td></tr>
                                    </c:forEach>
                                </table>
                        <br>
                         </div>
                </div>     
            </div>
                
        </center>
       </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
         </c:choose>
    </body>
</html>
