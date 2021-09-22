<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.DTOEntrega"%>
<%@page import="Modelo.DTOContrato"%>
<%@page import="Modelo.DTOPresupuesto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos Retirados</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body>
        <center><br>
            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-4">
                    <h4 align="left">Lista de pedidos Retirados</h4>
                                </div> 
                        <div class="col-8"> 
                            <b>desde</b> <input class="btn btn-group-sm my-2 my-sm-0" value="${fechaSessionA}" onChange="validarA()" name="fechaA" id="fechaA" type="date" >
                            <b>hasta</b> <input class="btn btn-group-sm my-2 my-sm-0" value="${fechaSessionB}" onChange="validarB()" name="fechaB" id="fechaB" type="date" >
                            <button style="text-align:center; color:grey " class="btn btn-group-sm my-2 my-sm-0" onclick="filtrar(1)">Buscar</button>
                            <a class="btn-link" href="javascript:filtrar(2);">Entregadas de hoy</a>
                        </div>
                    </div><br>
                    <div class="row">
                            <div class="col-12">
                        <table class="table table-hover table-active table-secondary">
                            <tr><th>Fecha Entrega</th><th>Lugar</th><th>Cliente</th><th>Nº Contrato</th><th> Fecha Evento</th><th>Estado</th><th>Comentarios</th></tr>
                            <c:choose>
                                        <c:when test="${not empty retirosRealizados}">  
                            <c:forEach items="${ retirosRealizados }" var="item">
                                        <tr>
                                            <td>${ item.fechaYHoraRetiro } </td>
                                            <td> ${ item.DTOContrato.DTOPresupuesto.lugarRetiro }</td>
                                            <td>${ item.DTOContrato.DTOPresupuesto.nombreCliente } </td>
                                            <td>${ item.DTOContrato.idContrato} </td>
                                            <td>${ item.DTOContrato.DTOPresupuesto.fechaInicioEvento } </td>
                                            <td> ${ item.DTOContrato.estado }</td>
                                            <td> ${ item.observaciones }</td>
                                       </tr>
                                        </c:forEach>
                                           </c:when>
                                    <c:otherwise>
                                        <tr> <th colspan="8">Sin datos para mostrar. Seleccione otra fecha</th></tr> <br>
                                </c:otherwise>
                            </c:choose>
                                </table>
                                <br><br><br>
                         </div>
                </div>
            </div>
        </center>
                        <%@include file="/footer.jsp" %>
    <script>
        function filtrar(num)
        {
            if(num===1)
            {
                var fechaA = document.getElementById('fechaA').value;
                var fechaB = document.getElementById('fechaB').value;
                window.location = "Retiros?modo=retirosRealizadosFiltro&fechaA="+ fechaA+"&fechaB="+ fechaB; 
            }
            else if(num===2) window.location = "Entregas?modo=retirosRealizadosAnual";   
        }
              
    </script>
    <script>
        function validarA()
        {
            var fechaA = document.getElementById("fechaA");
            var fechaB = document.getElementById("fechaB");
            
            fechaB.setAttribute("min", fechaA.value);
         }
    </script>
        <script>
        function validarB()
        {
            var fechaA = document.getElementById("fechaA");
            var fechaB = document.getElementById("fechaB");
            
            fechaA.setAttribute("max", fechaB.value);
         }
    </script>
    </body>
</html>
