<%-- 
    Document   : altaOrdenes
    Created on : 18-dic-2020, 10:05:01
    Author     : gonza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>NUEVO PAGO</title>
        <%@include file="/navbar.jsp" %>
    </head>
    
    <body>
        <center>
        <div>
            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-12">

                <br><br><h3>Registrar Pago</h3><br>
                
                <form action="AltaPago" method="post" >
                    
                   <br> <p>
                        <label>CONTRATO </label>
                        <select name="idContrato" required>
                            <option value=""selected="true" disabled>Elija un contrato</option>
                            <c:forEach items="${ contrato }" var="item" >
                                <option value="${ item.idContrato }">${ item.datos }</option>
                            </c:forEach>
                        </select>
                    </p>
                   <br> <p>
                        <label>MES </label>
                        <select name="idMes" required>
                            <option value=""selected="true" disabled>Días</option>
                            <option value="1">ENERO</option>
                            <option value="2">FEBRERO</option>
                            <option value="3">MARZO</option>
                            <option value="4">ABRIL</option>
                            <option value="5">MAYO</option>
                            <option value="6">JUNIO</option>
                            <option value="7">JULIO</option>
                            <option value="8">AGOSTO</option>
                            <option value="9">SEPTIEMBRE</option>
                            <option value="10">OCTUBRE</option>
                            <option value="11">NOVIEMBRE</option>
                            <option value="12">DICIEMBRE</option>
                        </select>
                    </p>
                  <br> <p>
                        <label>AÑO </label>
                        <select name="idAnio" required>
                            <option value=""selected="true" disabled>Años</option>
                            <option value="2020">2020</option>
                            <option value="2021">2021</option>
                            <option value="2022">2022</option>
                            <option value="2023">2023</option>
                        </select>
                    </p>
                    <p>
                        <label>DÍAS DE DEMORA </label>
                        <select name="demora" required="">
                        <option value="" selected="true" disabled>Demora</option>
                        <c:forEach var = "i" begin = "0" end = "60">
                            <option value="${i}">Dias de demora <c:out value = "${i}"/><p></option>
                        </c:forEach>  
                        </select>
                    </p>
                    <input type="submit" value="Aceptar" class="btn btn-primary"/><br> <br> 
                </form>
                        </div>
                </div>
            </div>
        </div>
        
        </center>

    </body>
</html>
