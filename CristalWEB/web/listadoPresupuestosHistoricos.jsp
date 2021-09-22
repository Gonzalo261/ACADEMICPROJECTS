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
    <body>
         <c:choose>
            <c:when test="${vistaON}">
        <center>
            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-12">
                        <br>
                        <h4 align="left">Historial de Presupuestos</h4><br>
                        <table class="table table-hover table-active table-dark">
                                    <tr><th>Fecha</th><th>Fecha.Evento </th><th>Fecha.Entrega/Fecha.Retiro  </th><th>Lugar </th><th>Total </th><th>Estado </th><th> Opciones </th></tr>
                                    <c:forEach items="${ presupuestosCliente }" var="item">
                                        <tr>
                                            <td><b>del</b> ${ item.fechaInicioEvento } <b>al</b> ${ item.fechaFinEvento }</td>
                                            <td>${ item.fechaEmision }<b> // </b>${ item.fechaEntrega }</td> 
                                            <td> ${ item.fechaRetiro }</td>
                                            <td> ${ item.lugarEntrega }</td>
                                            <td> ${ item.importeTotal}</td>
                                            <td> <c:choose>
                                                    <c:when test="${ item.vigente == 'true'}"> 
                                                        <label style="color: gren">CONFIRMADO</label>
                                                        </c:when>
                                                    <c:otherwise>
                                                        <label style="color: red">CANCELADO</label>
                                                    </c:otherwise>
                                                </c:choose>
                                                </td>
                                            <td>
                                                <button style="text-align:center; color:white " onclick="abrirVentana(${ item.id })" class="btn  btn-group-sm">Detail</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                         </div>
                </div>
            </div>
        </center>
       </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
    <%@include file="/footer.jsp" %>
    <script> 
        function abrirVentana (id){ 
           window.open("http://localhost:8080/CristalWEB/Presupuestos?modo=detalle&idPresupuesto="+id , "Detalle del presupuesto" , "width=800,height=400,scrollbars=SI,left=300,top=100"); 
        } 
</script>
    </body>
</html>
