<%-- 
    Document   : nuevoPareja
    Created on : 07/11/2020, 21:11:52
    Author     : gonza
--%>

<%@page import="Modelo.Pago"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>MENU</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body>
             <center>
            <div class="container"> 
                <div class="row">
                    
                       <h3>REPORTES</h3> <br><br>
                        <table class="table table-striped">
                                    <tr><th>TITULO  </th><th>DESCRIPCION </th><th> </th></tr>
                                    <tr>
                                        <td>Pagos sin demoras</td>
                                        <td>
                                            <table class="table table-striped">
                                                <tr><td>${ pagoSin.mes }  </td>    
                                                <td>${ pagoSin.año }  </td>
                                                <td>${ pagoSin.demora }  </td>
                                                    
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    
                                    
                        </table>

                </div>
            </div>
 </center> 
    </body>
</html>
