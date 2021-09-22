<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Términos y Condiciones</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body> 
    <center><br>
                    <div class="container fluid-5"> 
                        <div class="row">
                            <div class="col-12">
                                <h4 align="left">Editar Términos y Condiciones</h4><br>
                                <form action="Terminos" method="post" class="form-control form-control-sm" >
                                    <textarea class="form-control" name="terminos" rows="30" required>${modeloTermino}</textarea>
                                    <br>
                                    <input type="submit" value="Aceptar" style="text-align:center; color:blue " class="btn btn-group-sm"><br>
                                    </form>
                            </div>
                        </div>     
                    </div>
                </center>
                                    <%@include file="/footer.jsp" %>
    </body>
</html>
