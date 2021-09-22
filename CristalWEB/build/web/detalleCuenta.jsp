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
        
        <title>Consulta de cuenta</title>
        <%@include file="/navbar.jsp" %>
    </head>
    
    <body>
        <center>
             <c:choose>
            <c:when test="${not empty usr}">
            
            
            <div class="container fluid-5"> 
                <div class="row" style="background-color:#686868 ">
                    <div class="col-2"></div>
                    <div class="col-8">
                        
                        <br><h3 style="color:white">Consulta de cuenta  </h3><br>
                        <table class="table table-hover table-active table-dark" style="background-color:gray ">
                                    <tr><th>Nombre:  </th><td>${cuenta.persona.nombre}</td></tr>
                                    <tr><th>Apellido: </th><td>${cuenta.persona.apellido}</td></tr>
                                    <tr><th>Celular: </th><td>${cuenta.persona.celular}</td></tr>
                                    <tr><th>N° de documento:  </th><td>${cuenta.persona.dni}</td></tr>
                                    <tr><th>Email: </th><td>${cuenta.email}</td></tr>
                                    <tr><th>Password:  </th><td>*********</td></tr>
                                    <tr><th>Tipo de Usuario: </th><td>${descripcionTipo}</td></tr>
                                </table>
                            <br>
                    </div>
                                <div class="col-2" style="background-color:#686868 "><br><br><br>
                                    <label style="color:white">Opciones</label>
                                        <div class="row"><a style="text-align:center; color:#34ce57 " onclick="window.location = 'MisCuentas?modo=informe&idCliente=${ cuenta.id }'" class="btn btn-group-sm">Consultas</a></div>
                                         <div class="row"><a style="text-align:center; color:yellow " class="btn btn-group-sm" href="MisCuentas?modo=editarCuenta">Modificar</a></div>
                                         <div class="row"><a style="text-align:center; color:red " class="btn btn-group-sm" onClick="confirmarEliminacion()" >Eliminar</a></div>
                                         <div class="row"><a style="text-align:center; color:yellow" class="btn btn-group-sm" href="MisCuentas?modo=editarPassword">Cambiar Password</a><div>
                                    </div>
                    <br>
                </div>
            </div>
                        </center>
            </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>

    <script>
        function confirmarEliminacion(){
            
            if(confirm('Estas seguro de eliminar la cuenta?')) 
            {
                window.location = "MisCuentas?modo=eliminarCuenta";
            }
        }
        
    </script>
    
        <%@include file="/footer.jsp" %>
    </body>
</html>
