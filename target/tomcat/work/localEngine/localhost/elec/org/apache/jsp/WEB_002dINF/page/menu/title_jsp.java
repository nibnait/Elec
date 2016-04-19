package org.apache.jsp.WEB_002dINF.page.menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class title_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<HTML>\r\n");
      out.write("<HEAD>\r\n");
      out.write("<TITLE>Top</TITLE>\r\n");
      out.write("\r\n");
      out.write("<STYLE type=text/css>BODY {\r\n");
      out.write("\tFONT-SIZE: 12px; MARGIN: 0px; COLOR: #000000; BACKGROUND-COLOR: #ffffff\r\n");
      out.write("}\r\n");
      out.write("TD {\r\n");
      out.write("\tFONT-SIZE: 12px; COLOR: #000000\r\n");
      out.write("}\r\n");
      out.write("TH {\r\n");
      out.write("\tFONT-SIZE: 12px; COLOR: #000000\r\n");
      out.write("}\r\n");
      out.write("</STYLE>\r\n");
      out.write("<SCRIPT type=\"text/javascript\">\r\n");
      out.write("function submitrequest(action){\r\n");
      out.write("eval(\"document.location='\"+action+\"'\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</SCRIPT>\r\n");
      out.write("</HEAD>\r\n");
      out.write("<BODY>\r\n");
      out.write("<FORM id=\"Form1\" name=\"Form1\"  method=\"post\">\r\n");
      out.write("<table border=\"0\" width=\"100%\" height=\"9\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"100%\" colspan=\"4\" background=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/title.jpg\" height=\"58\">　</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"100%\" height=\"1\" bgcolor=\"#66C89C\" colspan=\"4\"><img border=\"0\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/titleline.jpg\" width=\"100%\" height=\"3\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"20%\" height=\"19\" bgcolor=\"#0965CA\">\r\n");
      out.write("    <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td width=\"15%\" align=\"center\"><font color=\"#FFFFFF\"><b>|</b></font></td>\r\n");
      out.write("        <td width=\"70%\" align=\"center\">\r\n");
      out.write("        <FONT color=#FFFFFF>\r\n");
      out.write("            <b>\r\n");
      out.write("            <SCRIPT language=JavaScript>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<!--\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttmpDate = new Date();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdate = tmpDate.getDate();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmonth= tmpDate.getMonth() + 1 ;\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tyear= tmpDate.getFullYear();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.write(year);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.write(\"年\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.write(month);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.write(\"月\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.write(date);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.write(\"日 \");\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmyArray=new Array(6);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmyArray[0]=\"星期日\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmyArray[1]=\"星期一\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmyArray[2]=\"星期二\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmyArray[3]=\"星期三\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmyArray[4]=\"星期四\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmyArray[5]=\"星期五\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmyArray[6]=\"星期六\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tweekday=tmpDate.getDay();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tif (weekday==0 | weekday==6)\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.write(myArray[weekday])\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\telse\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t{document.write(myArray[weekday])\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t};\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t// -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</SCRIPT>\r\n");
      out.write("            </b>\r\n");
      out.write("            </FONT></td>\r\n");
      out.write("        <td width=\"15%\" align=\"center\"><font color=\"#FFFFFF\"><b>|</b></font></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("    \r\n");
      out.write("    <td width=\"16%\" height=\"19\" bgcolor=\"#0965CA\">\r\n");
      out.write("    <table cellSpacing=\"2\" height=\"19\" cellPadding=\"0\" width=\"100%\" border=\"0\" ><tr><td valign=\"top\">\r\n");
      out.write("    <b><font color=\"#FFFFFF\">欢迎您!&nbsp;&nbsp;&nbsp;admin</font></b>\r\n");
      out.write("    </td></tr></table>\r\n");
      out.write("    </td>\r\n");
      out.write("    <td width=\"46%\" height=\"19\" bgcolor=\"#0965CA\">　</td>\r\n");
      out.write("    <td width=\"18%\" height=\"19\" bgcolor=\"#0965CA\" align=\"center\">\r\n");
      out.write("      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"100%\" align=\"center\">\r\n");
      out.write("          <A href=\"elecMenuAction_loading.do\" target=\"mainFrame\"><font color=\"#FFFFFF\"><b>返回首页</b></font></a>\r\n");
      out.write("          <font color=\"#FFFFFF\"><b>|</b></font>\r\n");
      out.write("          <A href=\"elecMenuAction_logout.do\"   target=\"_top\"> <font color=\"#FFFFFF\"><b>重新登录</b></font></A>\r\n");
      out.write("          <font color=\"#FFFFFF\"><b>|</b></font>\r\n");
      out.write("          </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</FORM>\r\n");
      out.write("</BODY>\r\n");
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
