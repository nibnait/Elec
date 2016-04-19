package org.apache.jsp.menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<HTML>\r\n");
      out.write("<HEAD>\r\n");
      out.write("<TITLE>国家电力监测中心首页</TITLE>\r\n");
      out.write("<LINK href=\"/css/Font.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("<STYLE>BODY {\r\n");
      out.write("\tSCROLLBAR-ARROW-COLOR: #ffffff; SCROLLBAR-BASE-COLOR: #dee3f7\r\n");
      out.write("}\r\n");
      out.write("</STYLE>\r\n");
      out.write("\r\n");
      out.write("<SCRIPT type=\"text/javascript\">\r\n");
      out.write("function submitrequest(action){\r\n");
      out.write("eval(\"document.location='\"+action+\"'\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</SCRIPT>\r\n");
      out.write("</HEAD>\r\n");
      out.write("\r\n");
      out.write("<FRAMESET border=0 frameSpacing=0 rows=82,* frameBorder=0 id=\"mainparent\">\r\n");
      out.write("<FRAME name=topFrame src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/menu/title.jsp\" noResize scrolling=no>\r\n");
      out.write("<FRAMESET id=\"main\" border=\"0\" frameSpacing=\"0\" frameBorder=\"0\" cols=\"153,1%,*\">\r\n");
      out.write("<FRAME name=\"leftFrame\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/menu/left.jsp\" noResize>\r\n");
      out.write("<frame name=\"changeButton\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/menu/change.jsp\" frameBorder=0 marginHeight=0 marginWidth=0 scrolling=no noresize>\r\n");
      out.write("<FRAME name=\"mainFrame\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/menu/loading.jsp\" >\r\n");
      out.write("</FRAMESET>\r\n");
      out.write("</FRAMESET>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</HTML>\r\n");
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
