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
    
    <body onload="limpiar()" >
        <center>
            <br><h3>${ accion } de Usuario</h3><br>
            <div class="container fluid-5"> 
                <form name="frmNuevoUsuario" id="frmNuevoUsuario" action="Registros" method="post" class="form-control form-control-sm" >
                <div class="row">
                    <div class="col-6">
                       <div class="form-group">
                            <label>Nombre </label>
                            <input name="nombre" id="nombre" onInput="verificar()"  required class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Apellido </label>
                            <input name="apellido" id="apellido" onInput="verificar()" required class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Celular </label>
                            <input name="celular" required id="celular" onInput="verificar()" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>N° de documento </label>
                            <input name="documento" id="documento" onInput="verificar()" required class="form-control"/>
                        </div>
                   </div>
                        <div class="col-6">
                        <div class="form-group">
                            <label>Email </label>
                            <input name="email" required id="email" type="email" onInput="verificar()" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Password </label>
                            <input name="pass" type="password" id="pass" required onInput="verificar()" class="form-control"/>
                        </div>
                            <div class="form-group">
                            <label>Confirmar Password </label>
                            <input name="confirmaPass" onInput="verificarPass()" type="password" id="confirmaPass" required class="form-control"/>
                        </div>
                        <div class="form-group">  
                            <input type="checkbox" id="terminos" name="terminos" onChange="habilitarButton()" required>
                            <a href=""  onclick="abrirVentana()" >Aceptas los Términos y Condiciones</a>
                          </div>
                            <br>
                        <input type="submit" id="aceptar" value="Aceptar" disabled style="text-align:center; color:blue " class="btn btn-group-sm my-2 my-sm-0"><br>
                         <label id="mensaje" disabled style="color:darkred"  ></label>
                       </div>  
                     </div>
                    </form>
                </div>
        </center>
            <%@include file="/footer.jsp" %>
            <script>
            function verificarPass(){
                var pass =  document.getElementById('pass');
                var confirmaPass = document.getElementById('confirmaPass');
                var mensaje = document.getElementById('mensaje');

                if(pass.value === confirmaPass.value && pass.value !=="")
                {
                    confirmaPass.setAttribute("style", "border-color:green;");
                    mensaje.innerHTML = "";
                } 
                else
                {
                    mensaje.disabled = false;
                    mensaje.innerHTML = "Las nuevas contraseñas no coinciden";
                    confirmaPass.setAttribute("style", "border-color:red;");
                }
            }
        </script>
        <script>
            function aceptar(){
                    document.frmNuevoUsuario.submit();
            }
        </script>
        <script>
            function habilitarButton(){
                
                if(document.getElementById('terminos').checked)
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
        
        <script> 
        function verificar (){ 
        var nombre =  document.getElementById('nombre');
        var apellido = document.getElementById('apellido');
        var email = document.getElementById('email');
        var celular = document.getElementById('celular');
        var documento = document.getElementById('documento');
        var pass = document.getElementById('pass');

        if(nombre.value!=="")
            nombre.setAttribute("style", "border-color:green;");
        else nombre.setAttribute("style", "border-color:red;");
        if(apellido.value!=="")
            apellido.setAttribute("style", "border-color:green;");
        else apellido.setAttribute("style", "border-color:red;");
        if(email.value!=="")
            email.setAttribute("style", "border-color:green;");
        else email.setAttribute("style", "border-color:red;");
        if(celular.value!=="")
            celular.setAttribute("style", "border-color:green;");
        else celular.setAttribute("style", "border-color:red;");
        if(documento.value!=="")
            documento.setAttribute("style", "border-color:green;");
        else documento.setAttribute("style", "border-color:red;");
        if(pass.value!=="")
            pass.setAttribute("style", "border-color:green;");
        else pass.setAttribute("style", "border-color:red;");
            
    } 
        </script>
        
        <script> 
        function limpiar (){ 
            var nombre =  document.getElementById('nombre');
            var apellido = document.getElementById('apellido');
            var email = document.getElementById('email');
            var celular = document.getElementById('celular');
            var documento = document.getElementById('documento');
            var pass = document.getElementById('pass');
            
            nombre.setAttribute("style", "border-color:red;");
            apellido.setAttribute("style", "border-color:red;");
            email.setAttribute("style", "border-color:red;");
            celular.setAttribute("style", "border-color:red;");
            documento.setAttribute("style", "border-color:red;");
            pass.setAttribute("style", "border-color:red;");
            
            document.getElementById("frmNuevoUsuario").reset();
            
    } 
        </script>
    </body>
    
</html>
