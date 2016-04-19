package org.apache.jsp.menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class alermDevice_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <title>load</title>\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/Style.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n");
      out.write("   </head>\r\n");
      out.write("  \r\n");
      out.write("  <body>\r\n");
      out.write("    <table width=\"100%\" border=\"0\" id=\"table8\">\r\n");
      out.write("    \r\n");
      out.write("\t\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td align=\"left\" valign=\"middle\"  style=\"word-break: break-all\">\r\n");
      out.write("\t\t\t\t\t<span class=\"style1\">\r\n");
      out.write("\t\t\t\t\t设备运行情况：<br>\r\n");
      out.write("\t\t\t\t\t9:00--正常<br>\r\n");
      out.write("\t\t\t\t\t10:00--正常<br>\r\n");
      out.write("\t\t\t\t\t11:00--正常<br>\r\n");
      out.write("\t\t\t\t\t12:00--正常<br>\r\n");
      out.write("\t\t\t\t\t13:00--正常<br>\r\n");
      out.write("\t\t\t\t\t14:00--正常<br>\r\n");
      out.write("\t\t\t\t\t15:00--正常<br>\r\n");
      out.write("\t\t\t\t\t16:00--正常<br>\r\n");
      out.write("\t\t\t\t\t17:00--发电机出现故障！请技术人员维护<br>\r\n");
      out.write("\t\t\t\t\t18:00--正常<br>\r\n");
      out.write("\t\t\t\t\t</span></td>\r\n");
      out.write("\t\t\t\t</tr>\t\t\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td align=\"left\" valign=\"middle\"  style=\"word-break: break-all\">\r\n");
      out.write("\t\t\t\t\t<span class=\"style1\">\r\n");
      out.write("\t\t\t\t\t<font color=\"red\">2012-10-21 18:10:58</font>\r\n");
      out.write("\t\t\t\t\t</span></td>\r\n");
      out.write("\t\t\t\t</tr>\t\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t</table>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
