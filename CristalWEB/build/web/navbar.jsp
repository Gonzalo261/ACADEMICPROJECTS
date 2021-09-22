

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="translated-ltr">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
       <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"rel="stylesheet"/>
      <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"rel="stylesheet"/>
      <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.6.0/mdb.min.css"rel="stylesheet"/>
  </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-warning">
                      <div class="container-fluid">
                        <button
                          class="navbar-toggler"
                          type="button"
                          data-mdb-toggle="collapse"
                          data-mdb-target="#navbarExample01"
                          aria-controls="navbarExample01"
                          aria-expanded="false"
                          aria-label="Toggle navigation"
                        >
                          <i class="fas fa-bars"></i>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav mr-auto my-2 my-lg-0">
                        <li class="nav-item"> <h5><a class="btn btn-group-lg" href="Principal">Home</a></h5></li>
                        <li class="nav-item dropdown">
                                            <a class="btn btn-group-sm" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                             Servicios
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-light bg-warning" aria-labelledby="navbarDarkDropdownMenuLink">
                                                <c:forEach items="${ categoriaNav }" var="item">
                                                <li class="nav-item"> <h6><a class="btn btn-group-sm" href="Articulos?idCategoria=${item.idCategoria}"><b>${item.descripcion}</b></a></h6></li>
                                                </c:forEach>
                                            </ul>
                                          </li>
                        
                        <c:choose>
                                <c:when test="${usuario=='CLIENTE'}">
                                        <li class="nav-item"><a class="btn btn-group-sm" href="Presupuestos"><b>Mis Presupuestos</b></a></li>
                                        <li class="nav-item"><a class="btn btn-group-sm" href="Contratos"><b>Mis Alquileres</b></a></li>
                                        <li class="nav-item"><a class="btn btn-group-sm" href="Cobros"><b>Mis Pagos</b></a></li>
                                    </c:when>
                                    <c:when test="${usuario=='CAJERO'}">
                                        <li class="nav-item"><a class="btn btn-group-sm" href="Cobros"><b>Cobranzas</b></a></li>
                                        <li class="nav-item"><a class="btn btn-group-sm" href="Usuarios?modo=verCuentas&tipo=CLIENTE">Clientes </a></li>
                                    </c:when>
                                    <c:when test="${usuario=='VENDEDOR'}">
                                        <li class="nav-item"><a class="btn btn-group-sm" href="Presupuestos"><b>Presupuestos</b></a></li>
                                        <li class="nav-item"><a class="btn btn-group-sm" href="Contratos"><b>Alquileres</b></a></li>
                                        <li class="nav-item"><a class="btn btn-group-sm" href="Usuarios?modo=verCuentas&tipo=CLIENTE">Clientes </a></li>
                                    </c:when>
                                    <c:when test="${usuario=='CADETE'}">
                                        <li class="nav-item dropdown">
                                            <a class="btn btn-group-sm" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                             Entregas
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-light bg-warning" aria-labelledby="navbarDarkDropdownMenuLink">
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Entregas?modo=pendientesAnual"><b>Pendientes</b></a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Entregas?modo=entregasRealizadasAnual"><b>Realizadas</b></a></li>
                                            </ul>
                                          </li>
                                          <li class="nav-item dropdown">
                                            <a class="btn btn-group-sm" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                             Retiros
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-light bg-warning" aria-labelledby="navbarDarkDropdownMenuLink">
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Retiros?modo=pendientesAnual"><b>Pendientes</b></a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Retiros?modo=retirosRealizadosAnual"><b>Realizados</b></a></li>
                                            </ul>
                                          </li> 
                                          <li class="nav-item"><a class="btn btn-group-sm" href="Usuarios?modo=verCuentas&tipo=CLIENTE">Clientes </a></li>
                                   </c:when>
                                    <c:when test="${usuario=='ADMINISTRADOR'}">
                                         <li class="nav-item dropdown" >
                                            <a class="btn btn-group-sm" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                             Comencial
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-light bg-warning" aria-labelledby="navbarDarkDropdownMenuLink">
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Presupuestos"><b>Presupuestos</b></a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Contratos"><b>Alquileres</b></a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Cobros"><b>Cobranzas</b></a></li>
                                            </ul>
                                          </li>
                                        <li class="nav-item dropdown">
                                            <a class="btn btn-group-sm" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                             Logística
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-light bg-warning" aria-labelledby="navbarDarkDropdownMenuLink">
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Entregas?modo=pendientesAnual"><b>Entregas Pendientes</b></a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Retiros?modo=pendientesAnual"><b>Retiros Pendientes</b></a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Entregas?modo=entregasRealizadasAnual"><b>Entregas Realizadas</b></a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Retiros?modo=retirosRealizadosAnual"><b>Retiros Realizados</b></a></li>
                                            </ul>
                                          </li>
                                        
                                         <li class="nav-item dropdown">
                                            <a class="btn btn-group-sm" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                             Cuentas
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-light bg-warning" aria-labelledby="navbarDarkDropdownMenuLink">
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Usuarios?modo=verCuentas&tipo=ADMINISTRADOR">Administrativos </a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Usuarios?modo=verCuentas&tipo=CLIENTE">Clientes </a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Usuarios?modo=verCuentas&tipo=CADETE">Cadetes </a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Usuarios?modo=verCuentas&tipo=CAJERO">Cajeros </a></li>
                                            <li class="nav-item"><a class="btn btn-group-sm" href="Usuarios?modo=verCuentas&tipo=VENDEDOR">Vendedores </a></li>
                                            </ul>
                                          </li>
                                        <li class="nav-item dropdown">
                                            <a class="btn btn-group-sm" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                             Herramientas
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-light bg-warning" aria-labelledby="navbarDarkDropdownMenuLink">
                                                <li class="nav-item"><a class="btn btn-group-sm" href="Registros?modo=alta">Crear Usuario</a></li>
                                                <li class="nav-item"><h6><a class="btn btn-group-sm" href="Articulos?modo=alta">Nuevo Articulo</a></h6></li>
                                                <li class="nav-item"><a class="btn btn-group-sm" href="Terminos?modo=editar">Editar Terminos y Condiciones</a></li>
                                                <li class="nav-item"><a class="btn btn-group-sm " href="Reportes">Informes / Estadísticas</a></li>
                                            </ul>
                                          </li>
                                    </c:when>
                                            </c:choose>
                            </ul>
                            
                            <ul class="navbar-nav ms-auto my-2 my-lg-0">
                                 <c:choose>
                                    <c:when test="${usuario!='CLIENTE'&& not empty usr}">
                                        <c:choose>
                                    <c:when test="${not empty cliente}">
                                        <li class="nav-item"><h6><a class="btn btn-group-sm"  href="MisCuentas?idCliente=${cliente.id}">${cliente.persona.nombre}</a></h6></li>
                                         </c:when> 
                                             </c:choose>
                                        <li class="nav-item">
                                                    <input name="dni" id="dni" class="form-control mr-sm-2" onkeyup = "if(event.keyCode === 13)buscar()" type="search" placeholder="Ingrese un Dni" required >${mensaje}
                                        </li>
                                            </c:when> 
                                             </c:choose>
                                   <c:choose>
                                         <c:when test="${not empty usr}">
                                             <li class="nav-item dropdown">
                                            <a class="btn btn-group-sm" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                             ${usr.persona.nombre}
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-light bg-warning" aria-labelledby="navbarDarkDropdownMenuLink">
                                                <li class="nav-item"><a class="btn btn-group-sm" href="MisCuentas">Mi Cuenta</a></li>
                                                <li class="nav-item"><a style="color:red " class="btn btn-group-sm" href="CerrarSesion">Salir</a></li>
                                            </ul>
                                          </li>
                                             </c:when>
                                          <c:otherwise>
                                                 <a style="color:blue " class="btn btn-group-sm" onclick="window.location = 'Logins'" >Ingresar</a>
                                 </c:otherwise>
                                      </c:choose>
                    </ul>
                </div>
        </div>
      </nav>
                    <!-- Navbar -->
            
        <script> 
            function buscar(){ 
                var dni = document.getElementById('dni').value;
                
                if(dni === "" || isNaN(dni) || dni.length !== 8 )
                {
                    alert('Ingrese un dni real de 8 caracteres');
                }
                else
                {
                    window.location = "BuscarDni?dni="+dni; 
                }
               
            } 
            </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src = " https://unpkg.com/sweetalert/dist/sweetalert.min.js " ></script> 
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.6.0/mdb.min.js" ></script>
    </body>
</html>
