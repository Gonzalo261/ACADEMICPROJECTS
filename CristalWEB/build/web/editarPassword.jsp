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
        <header><!-- Intro settings -->
                    <style>
                      /* Default height for small devices */
                      #intro-example {
                        height: 400px;
                      }

                      /* Height for devices larger than 992px */
                      @media (min-width: 992px) {
                        #intro-example {
                          height: 600px;
                        }
                      }
                    </style>
                    <!-- Background image -->
                    <div
                      id="intro-example"
                      class="p-5 text-center bg-image"
                      style="background-image: url('img/imgCarpa4.jpg');" >
                      <div class="mask" style="background-color: rgba(0, 0, 0, 0.7);">
                        <div class="d-flex justify-content-center align-items-center h-100">
                          <div class="text-white">
                            <br><h3>${ accion }</h3><br>
                            <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 800px;"> <br>
                    <div class="container fluid-5"> 
                        <div class="row">
                            <div class="col-2"></div>
                            <div class="col-8">
                                <form id="frmEditarPassword" action="MisCuentas" method="post" class="form-control" style="background-color:gray ">
                                    <table class="table table-hover table-active table-secondary">
                                        <input id="passActual" name="passActual" type="hidden" value="<jsp:getProperty name="modeloUsuario" property="pass"></jsp:getProperty>"/>
                                            <tr><th>Contraseña Actual  </th><td><input id="passIngresaUsuario" name="passIngresaUsuario" type="password" required class="form-control" onInput="habilitar()" /></td></tr>
                                            <tr><th>Nueva Contraseña </th><td><input id="nuevaPass" name="nuevaPass" type="password" required disabled class="form-control"/></td></tr>
                                            <tr><th>Confirme Nueva Contraseña  </th><td><input id="confirmaPass" name="confirmaPass" type="password" required disabled class="form-control" onInput="verificar()"/></td></td></tr>
                                            <tr><th></th><td align="center"><input disabled id="aceptar" type="submit" value="Aceptar" <a style="text-align:center; color:blue " class="btn btn-group-sm"></td></tr>
                                            <label id="mensaje" disabled style="color:darkred"  ></label>
                                </table> 
                                </form>
                            </div>
                            <div class="col-2"></div>
                        </div>
                    </div><br><br>
                          </div>
                            
                        </div>
                          
                      </div>
                        
                    </div>
                    <!-- Background image -->
                  </header>
     <script>
            function habilitar(){
                var passIngresaUsuario =  document.getElementById('passIngresaUsuario');
                var passActual = document.getElementById('passActual');
                var nuevaPass = document.getElementById('nuevaPass');
                var confirmaPas = document.getElementById('confirmaPass');
                var mensaje = document.getElementById('mensaje');
                
                
                if(passIngresaUsuario.value===passActual.value)
                {
                    mensaje.innerHTML = "";
                    nuevaPass.disabled = false;
                    confirmaPas.disabled = false;
                    passIngresaUsuario.setAttribute("style", "border-color:green;");   
                } 
                else
                {
                    nuevaPass.value = "";
                    confirmaPas.value = "";
                    nuevaPass.disabled = true;
                    confirmaPas.disabled = true;
                    mensaje.disabled = false;
                    mensaje.innerHTML = "La contraseña no coincide con la actual";
                    passIngresaUsuario.setAttribute("style", "border-color:red;");
                }
            }
        </script>
        <script>
            function verificar(){
                var nuevaPass =  document.getElementById('nuevaPass');
                var confirmaPass = document.getElementById('confirmaPass');
                var mensaje = document.getElementById('mensaje');
                var aceptar = document.getElementById('aceptar');

                if(nuevaPass.value === confirmaPass.value)
                {
                    aceptar.disabled = false;
                    nuevaPass.setAttribute("style", "border-color:green;");
                    confirmaPass.setAttribute("style", "border-color:green;");
                    mensaje.innerHTML = "";
                } 
                else
                {
                    aceptar.disabled = true;
                    mensaje.disabled = false;
                    mensaje.innerHTML = "Las nuevas contraseñas no coinciden";
                    nuevaPass.setAttribute("style", "border-color:red;");
                    confirmaPass.setAttribute("style", "border-color:red;");
                }
            }
        </script>
        <%@include file="/footer.jsp" %>
    </body>
    
</html>
