<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>

<%@page import="Modelo.Persona"%>
<%@page import="Modelo.DTOUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Modificar mis datos</title>
        <%@include file="/navbar.jsp" %>
    </head>
    
    <body>
        <center>
             <c:choose>
            <c:when test="${usuario=='ADMINISTRADOR'}">
            <br><h3>${ accion } Usuario</h3><br>
            
            <div class="container fluid-5"> 
                
                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-8">
                        <form action="MisCuentas" method="post" class="form-control" style="background-color:gray " >
                            <table class="table table-hover table-active table-secondary">
                                    <tr><th>Nombre:  </th><td><input name="nombre" required class="form-control" value="<jsp:getProperty name="modeloUsuario" property="nombre"></jsp:getProperty>"/></td></tr>
                                    <tr><th>Apellido: </th><td><input name="apellido" required class="form-control" value="<jsp:getProperty name="modeloUsuario" property="apellido"></jsp:getProperty>"/></td></tr>
                                    <tr><th>Celular: </th><td><input name="celular" required class="form-control" value="<jsp:getProperty name="modeloUsuario" property="celular"></jsp:getProperty>"/></td></tr>
                                    <tr><th>N° de documento:  </th><td><input name="dni" required class="form-control" value="<jsp:getProperty name="modeloUsuario" property="dni"></jsp:getProperty>"/></td></tr>
                                    <tr><th>Tipo de Usuario: </th><td><select name="tipoUsuario" required class="form-control">
                                                                        <option selected="true" disabled>Seleccione un tipo de usuario</option>
                                                                        <c:forEach items="${ tiposUsuarios }" var="item">
                                                                            <option value="${ item.id }"<c:if test="${ item.id == idTipo}">selected</c:if>> ${ item.descripcion }</option>
                                                                        </c:forEach>
                                                                    </select></td></tr>
                                    <tr><th></th><td align="center"><input type="submit" value="Aceptar" class="btn btn-outline-primary"></td></tr>
                                </table>
                         </form>
                    </div>
                    <div class="col-2"></div>
                </div>
            </div>
            </center>
            </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
        <%@include file="/footer.jsp" %>
    </body>
</html>
