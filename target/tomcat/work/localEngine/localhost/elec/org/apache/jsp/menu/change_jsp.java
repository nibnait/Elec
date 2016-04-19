package org.apache.jsp.menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class change_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<HTML xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns=\"http://www.w3.org/TR/REC-html40\"><HEAD><TITLE>name</TITLE>\r\n");
      out.write("<META http-equiv=Content-Type content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<META content=\"Microsoft FrontPage 4.0\" name=GENERATOR>\r\n");
      out.write("<META content=C# name=CODE_LANGUAGE>\r\n");
      out.write("<META content=JavaScript name=vs_defaultClientScript>\r\n");
      out.write("<META content=http://schemas.microsoft.com/intellisense/ie5 name=vs_targetSchema>\r\n");
      out.write("\r\n");
      out.write("<STYLE type=text/css>BODY {\r\n");
      out.write("\tMARGIN: 0px; BACKGROUND-COLOR: #ffffff\r\n");
      out.write("}\r\n");
      out.write("BODY {\r\n");
      out.write("\tCOLOR: #000000\r\n");
      out.write("}\r\n");
      out.write("TD {\r\n");
      out.write("\tCOLOR: #000000\r\n");
      out.write("}\r\n");
      out.write("TH {\r\n");
      out.write("\tCOLOR: #000000\r\n");
      out.write("}\r\n");
      out.write("</STYLE>\r\n");
      out.write("\r\n");
      out.write("<STYLE>BODY {\r\n");
      out.write("\tSCROLLBAR-FACE-COLOR: #cccccc; SCROLLBAR-HIGHLIGHT-COLOR: #ffffff; SCROLLBAR-SHADOW-COLOR: #ffffff; SCROLLBAR-3DLIGHT-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR: #ffffff; SCROLLBAR-TRACK-COLOR: #ffffff; SCROLLBAR-DARKSHADOW-COLOR: #cccccc\r\n");
      out.write("}\r\n");
      out.write("</STYLE>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("\tfunction  shiftwindow()\r\n");
      out.write("\t{   \r\n");
      out.write("\t\tif(parent.document.getElementById(\"main\").cols==\"153,1%,*\")\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tparent.document.getElementById(\"main\").cols='0,1%,99%';\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tdocument.all.image.src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/you.gif\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(parent.document.getElementById(\"main\").cols==\"0,1%,99%\")\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tparent.document.getElementById(\"main\").cols='153,1%,*';\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\tdocument.all.image.src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/zuo.gif\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</HEAD>\r\n");
      out.write("<BODY MS_POSITIONING=\"GridLayout\">\r\n");
      out.write("<table width=1 style=\"cursor: hand\" height=\"100%\" background=\"\" cellspacing=\"0\" cellpadding=\"0\" >\r\n");
      out.write("\t<tr><td onclick=\"shiftwindow()\" title=\"全屏/半屏\" background=\"\" width=\"20\">\r\n");
      out.write(" \t\t \r\n");
      out.write("    <p align=\"center\">\r\n");
      out.write(" \t\t<img id=\"image\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/zuo.gif\" width=\"9\" height=\"79\" >       \r\n");
      out.write("    </p>\r\n");
      out.write(" \t\t \r\n");
      out.write("\t</td></tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
