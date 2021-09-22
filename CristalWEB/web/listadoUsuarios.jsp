<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.DTOUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de clientes</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body>
                 <center><br>
                <c:choose>
            <c:when test="${not empty seccion1}">
                <div class="row"> 
                    <div class="col-1"></div>
                    <div class="col-10"><br>
                        <table class="table table-hover table-active table-dark" style="background-color:gray ">
                                    <tr><th>Cliente</th>
                                        <th>D N I  </th>
                                        <th>Email </th>
                                        <th>Celular</th>
                                        <th>Opciones </th>
                                    </tr>
                                    <tr><td>${ seccion1.persona.nombre } ${ seccion1.persona.apellido }</td>
                                        <td>${ seccion1.persona.dni }</td> 
                                        <td>${ seccion1.email }</td>
                                        <td>${ seccion1.persona.celular }</td>
                                        <td><a style="text-align:center; color:white" class="btn btn-group-sm my-2 my-sm-0" onclick="window.location = 'MisCuentas?idCliente=${seccion1.id}'">Consultar</a></td>
                                    </tr>
                                </table>
                         </div>
                                    <div class="col-1"></div>
                </div>
                        </c:when>
            <c:when test="${not empty seccion2}">
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10">
                        <table class="table table-hover table-active table-dark" style="background-color:gray ">
                                    <tr><th>Cliente</th>
                                        <th>D N I  </th>
                                        <th>Email </th>
                                        <th>Celular</th>
                                    </tr>
                                    <c:forEach items="${ seccion2 }" var="item">
                                    <tr><td><a style="text-align:center; color:white" class="btn btn-group-sm my-2 my-sm-0" onclick="window.location = 'MisCuentas?idCliente=${item.id}'">${ item.persona.nombre } ${ item.persona.apellido }</a></td>
                                        <td>${ item.persona.dni}</td> 
                                        <td>${ item.email }</td>
                                        <td>${ item.persona.celular }</td>
                                    </tr>
                                    </c:forEach>
                                </table>
                         </div>
                                    <div class="col-2"></div>
                </div>
                        </c:when>
            </c:choose>
            </div>
        
        </center>
    <%@include file="/footer.jsp" %>
            
    </body>
</html>
