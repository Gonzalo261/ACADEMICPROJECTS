

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
}

li {
  float: right;
  color: white;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

/* Change the link color to #111 (black) on hover */
li a:hover {
}

input{
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: none;
  background-color: #f1f1f1;
  color: black;
}
select {
  padding: 16px 20px;
  border: none;
  border-radius: 4px;
  background-color: #f1f1f1;
}
menuPrin{
height:30px;
margin-bottom:50px;
border-bottom:#CCC dotted 2px;
width:900px;
float:left;
}
        </style>
    </head>
    <body background="https://thumbs.dreamstime.com/z/fondo-cuadrado-del-papel-texturizado-marr%C3%B3n-claro-48271705.jpg">
        <h1 >
                <!--<img src="https://static.btcdn.co/2807/logo/original/logo-Logo_masparamihogar.png" />-->
            </h1>
            
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                      <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                      <ul class="navbar-nav mr-auto">
                        <li>
                          <a class="nav-link" > INICIO <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="AltaPago">Nuevo Pago</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="Reporte">Reportes</a>
                        </li>
                         
                      </ul>
                      
                    </div>
                  </nav>
    </body>
</html>
