package org.apache.jsp.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class textAdd_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("   <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/My97DatePicker/WdatePicker.js\"></script>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>测试专用jsp</title>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/Style.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("  <script language=\"javascript\"> \r\n");
      out.write("   function checkchar(){\r\n");
      out.write("  \t\tdocument.Form1.action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/system/elecTextAction_save.do\";\r\n");
      out.write("  \t\tdocument.Form1.submit();\r\n");
      out.write("  }\r\n");
      out.write("  function addEnter(element){\r\n");
      out.write("   document.getElementById(element).value = document.getElementById(element).value+\"<br>\";\r\n");
      out.write("   \r\n");
      out.write("  }\r\n");
      out.write("  </script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<form name=\"Form1\" id=\"Form1\" method=post>\r\n");
      out.write("\r\n");
      out.write("\t<table cellspacing=\"1\" cellpadding=\"5\" width=\"90%\" align=\"center\" bgcolor=\"#f5fafe\" style=\"border:1px solid #8ba7e3\" border=\"0\">\r\n");
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("\t\t\t<td class=\"ta_01\" colspan=2 align=\"center\" background=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/b-info.gif\">\r\n");
      out.write("\t\t\t<font face=\"宋体\" size=\"2\"><strong>测试专用jsp</strong></font>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<TR height=10><td></td><td></td></TR>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"ta_01\" align=\"center\" bgcolor=\"#f5fafe\" width=\"15%\">测试名称：</td>\r\n");
      out.write("\t\t\t<td class=\"ta_01\" bgcolor=\"#ffffff\" style=\"word-break: break-all\">\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t<textarea name=\"textName\" id=\"textName\"   style=\"width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt\" onkeydown=\"if(event.keyCode==13)addEnter('textName');\"></textarea>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"ta_01\" align=\"center\" bgcolor=\"#f5fafe\" width=\"15%\">测试日期：</td>\r\n");
      out.write("\t\t\t<td class=\"ta_01\" bgcolor=\"#ffffff\" style=\"word-break: break-all\">\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t<input name=\"textDate\" type=\"text\" maxlength=\"50\" size=20 onclick=\"WdatePicker()\">\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"ta_01\" align=\"center\" bgcolor=\"#f5fafe\" width=\"15%\">测试备注：</td>\r\n");
      out.write("\t\t\t<td class=\"ta_01\" bgcolor=\"#ffffff\" style=\"word-break: break-all\">\r\n");
      out.write("\t\t\t<textarea name=\"textRemark\" id=\"textRemark\"  style=\"width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt\" onkeydown=\"if(event.keyCode==13)addEnter('textRemark');\"></textarea>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("\t\t\t<td class=\"ta_01\" style=\"width: 100%\" align=\"center\" bgcolor=\"#f5fafe\" colspan=\"2\">\r\n");
      out.write("\t\t\t<input type=\"button\" name=\"BT_Submit\" value=\"保存\" onclick=\"checkchar()\" id=\"BT_Submit\" style=\"font-size:12px; color:black; height=20;width=50\">\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t　 \r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
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
