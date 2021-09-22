package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class nuevoUsuario_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/navbar.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        \n");
      out.write("        <title>Registrarme</title>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html class=\"translated-ltr\">\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <style>\n");
      out.write("            body{\n");
      out.write("                background-color:white;\n");
      out.write("                color:#000;\n");
      out.write("            }\n");
      out.write("            table{\n");
      out.write("                background-color:menu;\n");
      out.write("                color:threeddarkshadow;\n");
      out.write("            }\n");
      out.write("            h3{\n");
      out.write("                background-color:menu;\n");
      out.write("                color:threeddarkshadow;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            nav{\n");
      out.write("                background-color:menu;\n");
      out.write("                color:white;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            ul {\n");
      out.write("                    list-style-type: none;\n");
      out.write("                    margin: 0;\n");
      out.write("                    padding: 0;\n");
      out.write("                    overflow: hidden;\n");
      out.write("                  }\n");
      out.write("\n");
      out.write("                  li {\n");
      out.write("                    float: right;\n");
      out.write("                    color: white;\n");
      out.write("                  }\n");
      out.write("\n");
      out.write("                  li a {\n");
      out.write("                    display: block;\n");
      out.write("                    color: white;\n");
      out.write("                    text-align: center;\n");
      out.write("                    padding: 14px 16px;\n");
      out.write("                    text-decoration: none;\n");
      out.write("                  }\n");
      out.write("\n");
      out.write("                  /* Change the link color to #111 (black) on hover */\n");
      out.write("                  li a:hover {\n");
      out.write("                  }\n");
      out.write("\n");
      out.write("                  input{\n");
      out.write("                    padding: 12px 20px;\n");
      out.write("                    margin: 8px 0;\n");
      out.write("                    box-sizing: border-box;\n");
      out.write("                    border: none;\n");
      out.write("                    background-color: #f1f1f1;\n");
      out.write("                    color: black;\n");
      out.write("                  }\n");
      out.write("                  select {\n");
      out.write("                    padding: 16px 20px;\n");
      out.write("                    border: none;\n");
      out.write("                    border-radius: 4px;\n");
      out.write("                    background-color:white;\n");
      out.write("                  }\n");
      out.write("                  menuPrin{\n");
      out.write("                  height:30px;\n");
      out.write("                  margin-bottom:50px;\n");
      out.write("                  border-bottom:#CCC dotted 2px;\n");
      out.write("                  width:900px;\n");
      out.write("                  float:left;\n");
      out.write("                  }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body >\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <body>\n");
      out.write("        <center>\n");
      out.write("            <br><h3>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ accion }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" Usuario</h3><br>\n");
      out.write("            \n");
      out.write("            <div class=\"container fluid-5\"> \n");
      out.write("                <form action=\"Registros\" method=\"post\" class=\"form-control\" >\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-2\"></div>\n");
      out.write("                    <div class=\"col-3\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Nombre </label>\n");
      out.write("                            <input name=\"nombre\" required class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Apellido </label>\n");
      out.write("                            <input name=\"apellido\" required class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Email </label>\n");
      out.write("                            <input name=\"email\" required class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Celular </label>\n");
      out.write("                            <input name=\"celular\" required class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>N° de documento </label>\n");
      out.write("                            <input name=\"documento\" required class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                   </div>\n");
      out.write("                    <div class=\"col-2\"></div>\n");
      out.write("                        <div class=\"col-3\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Alias </label>\n");
      out.write("                            <input name=\"alias\" required class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Password </label>\n");
      out.write("                            <input name=\"pass\" type=\"password\" required class=\"form-control\"/>\n");
      out.write("                        </div>  <br>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-2\"></div>\n");
      out.write("                    <br>\n");
      out.write("                        <input type=\"submit\" value=\"Aceptar\" class=\"btn btn-primary\"><br>\n");
      out.write("                </div>\n");
      out.write("                    </form>\n");
      out.write("            </div>\n");
      out.write("                \n");
      out.write("                        </center>\n");
      out.write("\n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
