<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.DTOPresupuesto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Presupuesto </title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body><center>
        <c:choose>
             <c:when test="${not empty usr}"> 
        
                <div class="container fluid-5"><br> 
                            <c:choose>
                    <c:when test="${not empty cliente||usuario=='CLIENTE'}">
                        <div class="row">
                        <div class="col-8">
                            <h4 align="left">Lista de Presupuestos </h4><br>
                                        </div>
                        <div class="col-4">
                            <a style="text-align:center; color:green" onclick="window.location = 'Carritos'" class="btn btn-group-sm">Nuevo</a>
                            <a style="text-align:center; color:black" onclick="window.location = 'Presupuestos?modo=historicos'" class="btn btn-group-sm">Historial</a> 
                                        </div>
                        </div>
                            </c:when>
                 </c:choose>
                    <div class="row">
                        <c:choose>
                    <c:when test="${not empty presupuestosCliente}"> <br> 
                        <table class="table table-hover table-active table-dark">
                                    <tr><th>Nº Presupuesto</th><th>Fecha</th><th>Evento </th><th>Entrega<b>//</b>Retiro  </th><th>Lugar </th><th>Total </th><th> Opciones </th></tr>
                                    <c:forEach items="${ presupuestosCliente }" var="item">
                                        <tr>
                                            <td><a style="text-align:center; color:white" onclick="abrirVentanaDetalle(${ item.id })" class="btn btn-group-sm">${ item.id }</a></td>
                                            <td>${ item.fechaEmision }</td>
                                            <td><b>del</b> ${ item.fechaInicioEvento } <b>al</b> ${ item.fechaFinEvento }</td> 
                                            <td>${ item.fechaInicioEvento }  <b>//</b> ${ item.fechaRetiro }</td>
                                            <td> ${ item.lugarEntrega }</td>
                                            <td> ${ item.importeTotal}</td>
                                            <td>
                                                <a style="text-align:center; color:orange " onclick="window.location = 'Presupuestos?modo=editar&idPresupuesto=${ item.id }'" class="btn btn-group-sm">Edit</a>
                                                <a style="text-align:center; color:green " onclick="window.location = 'Contratos?modo=alta&idPresupuesto=${ item.id }&idCliente=${ cliente.id }'" class="btn btn-group-sm">Conf</a>
                                                <a style="text-align:center; color:red " onclick="window.location = 'Presupuestos?modo=cancelar&idPresupuesto=${ item.id }'" class="btn btn-group-sm">Cancel</a></td>
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
               window.open("http://localhost:8080/CristalWEB/Presupuestos?modo=detalle&idPresupuesto="+id , "Detalle del Presupuestos" , "width=800,height=400,scrollbars=SI,left=300,top=100");
            } 
            </script>
       
    </body>
</html>
