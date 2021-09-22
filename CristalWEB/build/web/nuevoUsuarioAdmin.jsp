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
        
        <title>Registrarme</title>
        <%@include file="/navbar.jsp" %>
    </head>
    
    <body>
        <center>
             <c:choose>
            <c:when test="${usuario=='ADMINISTRADOR'}">
            <br><h3>${ accion } de Usuario</h3><br>
            
            <div class="container fluid-5"> 
                <form action="Registros" method="post" class="form-control" >
                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <label>Nombre </label>
                            <input name="nombre" id="nombre" required class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Apellido </label>
                            <input name="apellido" id="apellido" required class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Celular </label>
                            <input name="celular" required id="celular" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>N° de documento </label>
                            <input name="documento" id="documento" required class="form-control"/>
                        </div>
                   </div>
                        <div class="col-6">
                        <div class="form-group">
                            <label>Email </label>
                            <input name="email" required type="email" id="email" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Password </label>
                            <input name="pass" type="password" id="pass" required class="form-control"/>
                        </div>
                            <div class="form-group">
                            <label>Confirmar Password </label>
                            <input name="confirmaPass" onInput="verificar()" onChange="habilitarButton()" type="password" id="confirmaPass" required class="form-control"/>
                            <label id="mensaje" disabled style="color:darkred"  ></label>
                            </div>
                            <div class="form-group">
                                <label>Tipo de Usuario </label>
                            <select name="tipoUsuario" required class="form-control">
                                    <option selected="true" disabled style="color:#818182 ">Seleccione un tipo</option>
                                    <c:forEach items="${ tiposUsuarios }" var="item">
                                        <option value="${ item.id }"> ${ item.descripcion }</option>
                                    </c:forEach>
                                </select>
                        </div> <br>
                            <div class="form-group">  
                            <input type="checkbox" name="terminos" onChange="habilitarButton()" id="terminos" required >
                            <a href="" onclick="abrirVentana()" >Aceptas los Términos y Condiciones</a>
                          </div>
                            <input type="submit" value="Aceptar" id="aceptar" disabled style="text-align:center; color:blue " class="btn btn-group-sm my-2 my-sm-0"><br>
                            <label id="mensaje" disabled style="color:darkred"  ></label>
                            <br>
                    </div>
                    <br>
                </div>
                    </form>
            </div>
                
                        </center>
            </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
<%@include file="/footer.jsp" %>
    <script>
            function verificar(){
                var pass =  document.getElementById('pass');
                var confirmaPass = document.getElementById('confirmaPass');
                var mensaje = document.getElementById('mensaje');
                var aceptar = document.getElementById('aceptar');

                if(pass.value === confirmaPass.value)
                {
                    pass.setAttribute("style", "border-color:green;");
                    confirmaPass.setAttribute("style", "border-color:green;");
                    mensaje.innerHTML = "";
                    return true;
                } 
                else
                {
                    aceptar.disabled = true;
                    mensaje.disabled = false;
                    mensaje.innerHTML = "Las contraseñas no coinciden";
                    pass.setAttribute("style", "border-color:red;");
                    confirmaPass.setAttribute("style", "border-color:red;");
                    return false;
                }
            }
        </script>
        <script>
            function habilitarButton(){
                if(document.getElementById('terminos').checked && verificar()===true)
                {
                    document.getElementById('aceptar').disabled = false;
                } 
                else
                {
                    document.getElementById('aceptar').disabled = true;
                }
            }
        </script>
    <script> 
        function abrirVentana (){ 
            window.open("http://localhost:8080/CristalWEB/Terminos?modo=leerTermino", "Terminos y condiciones" , "width=800,height=400,scrollbars=SI,left=300,top=100"); 
        } 
        </script>
        
    </body>
</html>
