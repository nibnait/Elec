package org.apache.jsp.menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loading_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>     \r\n");
      out.write("    <title></title>\r\n");
      out.write("    \r\n");
      out.write("    <meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("    <meta http-equiv=\"cache-control\" content=\"no-cache\"><link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/Style.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("<!--\r\n");
      out.write("body {\r\n");
      out.write("\tbackground-color: #FFFFFF;\r\n");
      out.write("\tmargin-left: 0px;\r\n");
      out.write("\tmargin-top: 0px;\r\n");
      out.write("\tmargin-right: 0px;\r\n");
      out.write("\tmargin-bottom: 0px;\r\n");
      out.write("}\r\n");
      out.write("body,td,th {\r\n");
      out.write("\tcolor: #000000;\r\n");
      out.write("}\r\n");
      out.write("-->\r\n");
      out.write("    </style>\r\n");
      out.write("<style>\r\n");
      out.write("BODY {SCROLLBAR-FACE-COLOR: #cccccc; SCROLLBAR-HIGHLIGHT-COLOR: #ffffFF; SCROLLBAR-SHADOW-COLOR: #ffffff; SCROLLBAR-3DLIGHT-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR:  #ffffff; SCROLLBAR-TRACK-COLOR: #ffffFF; SCROLLBAR-DARKSHADOW-COLOR: #cccccc; }\r\n");
      out.write("</style>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("\tfunction  shiftiframe(value)\r\n");
      out.write("\t{    \r\n");
      out.write("\t    if(value==1){\r\n");
      out.write("\t        if(document.all.station.width==500)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tdocument.all.station.width=1100;\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"devtd\").style.display=\"none\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse if(document.all.station.width==1100)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tdocument.all.station.width=500;\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"devtd\").style.display=\"\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t    }\r\n");
      out.write("\t    else{\r\n");
      out.write("\t        if(document.all.dev.width==500)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tdocument.all.dev.width=1100;\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"stationtd\").style.display=\"none\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse if(document.all.dev.width==1100)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tdocument.all.dev.width=500;\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"stationtd\").style.display=\"\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t    }\r\n");
      out.write("\t}\r\n");
      out.write("\t/**添加10分钟后自动刷新页面，站点和设备运行的实时性*/\r\n");
      out.write("\twindow.onload=function(){\r\n");
      out.write("\t\tsetTimeout('refresh10()',1000*60*10) ;\r\n");
      out.write("    }\r\n");
      out.write("\tfunction refresh10(){\r\n");
      out.write("\t\twindow.location.reload();\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/login.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<form name=\"Form1\" method=\"post\" action=\"name.aspx\" id=\"Form1\">\r\n");
      out.write("\r\n");
      out.write("\t<table width=\"100%\" border=\"0\" height=\"88\" border=\"1\" background=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/back1.jpg>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td colspan=3 class=\"ta_01\" align=\"center\" background=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/b-info.gif\"><strong>系统首页</strong></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td width=\"50%\" height=\"84\" align=\"left\" valign=\"top\" id=\"stationtd\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t    <fieldset id=\"stationset\" style=\"width: 500px; height: 430px; padding: 1 background:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/back1.JPG\"><legend>\r\n");
      out.write("\t\t\t\t    <font color=\"#0000FF\">\r\n");
      out.write("\t\t\t\t    <img border=\"0\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/zoom.gif\" width=\"14\" height=\"14\"><a href=\"#\" onclick=\"shiftiframe('1')\">站点运行情况</a></font></legend>\r\n");
      out.write("\t\t\t\t\t<IFRAME src=\"alermStation.jsp\"  name=\"station\" id=\"station\" frameBorder=\"0\" width=\"500\" scrolling=\"auto\" height=\"400\"></IFRAME>\t\r\n");
      out.write("\t\t\t\t\t     \r\n");
      out.write("\t\t\t    </fieldset>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("           \t\t\t\r\n");
      out.write("\t\t\t<td width=\"50%\" align=\"left\" valign=\"top\" id=\"devtd\">\r\n");
      out.write("\t\t\t\t<fieldset id=\"devset\" style=\"width: 500px; height: 430px; padding: 1 background:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/back1.JPG\"><legend>\r\n");
      out.write("\t\t\t\t\t<font color=\"#0000FF\">\r\n");
      out.write("\t\t\t\t\t<img border=\"0\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/zoom.gif\" width=\"14\" height=\"14\"><a href=\"#\" onclick=\"shiftiframe('2')\">设备运行情况</a></font></legend>\r\n");
      out.write("\t             \t<IFRAME src=\"alermDevice.jsp\"  name=\"dev\" id=\"dev\" frameBorder=\"0\" width=\"500\" scrolling=\"auto\" height=\"400\"></IFRAME>\t\r\n");
      out.write("\t\t\t\t</fieldset>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr><td height=2></td></tr>\r\n");
      out.write("\t\r\n");
      out.write("\t</table>\r\n");
      out.write("\r\n");
      out.write("\t</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
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
