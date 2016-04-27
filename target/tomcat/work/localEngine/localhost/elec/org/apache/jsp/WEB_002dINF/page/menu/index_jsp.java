package org.apache.jsp.WEB_002dINF.page.menu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005factionerror_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fs_005factionerror_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fs_005factionerror_005fnobody.release();
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Language\" content=\"zh-cn\">\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<LINK href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/buttonstyle.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("<LINK href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/MainPage.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("<script type='text/javascript' src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/script/pub.js'></script>\r\n");
      out.write("<script type=\"text/javascript\" src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/script/validate.js'></script>\r\n");
      out.write("<SCRIPT type=\"text/javascript\">\r\n");
      out.write("function returnMethod(){\r\n");
      out.write("\tif(event.keyCode==13){\r\n");
      out.write("\t\treturn checkFunction()\r\n");
      out.write("\t}\r\n");
      out.write("\treturn false;\r\n");
      out.write("}\r\n");
      out.write("function ini(){\r\n");
      out.write("   document.all.name.focus();\r\n");
      out.write("}\r\n");
      out.write("function check(){\r\n");
      out.write("    var theForm = document.forms[0];\r\n");
      out.write("\tif(Trim(theForm.name.value)==\"\"){\r\n");
      out.write("\t\t\talert(\"请输入用户名\");\r\n");
      out.write("\t\t\ttheForm.name.focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t    }\r\n");
      out.write("\ttheForm.submit();\r\n");
      out.write("\treturn true; \r\n");
      out.write("}\r\n");
      out.write("function checkNumberImage(){\r\n");
      out.write("\tvar imageNumber = document.getElementById(\"imageNumber\");\r\n");
      out.write("\timageNumber.src = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/image.jsp?timestamp=\"+new Date().getTime();\r\n");
      out.write("}\r\n");
      out.write("function checkFunction(){\r\n");
      out.write("\treturn check();\r\n");
      out.write("}\r\n");
      out.write("</SCRIPT>\r\n");
      out.write("<STYLE type=text/css>\r\n");
      out.write("BODY { margin: 0px; }\r\n");
      out.write("FORM {\r\n");
      out.write("\tMARGIN: 0px; BACKGROUND-COLOR: #ffffff\r\n");
      out.write("} \r\n");
      out.write("</STYLE>\r\n");
      out.write("<title>国家电力监测中心</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body onload=\"ini()\">\r\n");
      out.write("<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/system/elecMenuAction_menuHome.do\" method=\"post\" target=\"_top\">\r\n");
      out.write("<table border=\"0\" width=\"100%\" id=\"table1\" height=\"532\" cellspacing=\"0\" cellpadding=\"0\" >\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td>　</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td height=\"467\">\r\n");
      out.write("\t\t<table border=\"0\" width=\"1024\" id=\"table2\" height=\"415\" cellspacing=\"0\" cellpadding=\"0\" >\r\n");
      out.write("\t\t<br><br><br><br><br>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=12%></td>\r\n");
      out.write("\t\t\t\t<td align=center background=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/index.jpg\">\r\n");
      out.write("\t\t\t\t<table border=\"0\" width=\"98%\" id=\"table3\" height=\"412\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t\t\t<tr height=122>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=2></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td height=\"313\" width=\"73%\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td height=\"99\" width=\"27%\">\r\n");
      out.write("\t\t\t\t\t\t\t<table border=\"0\" width=\"70%\" id=\"table4\">\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_005factionerror_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td width=\"100\"><img border=\"0\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/yonghu.jpg\" width=\"75\" height=\"20\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"name\" style=\"width: 125 px\" size=\"20\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  maxlength=\"25\"></td>\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td width=\"100\"><img border=\"0\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/mima.jpg\" width=\"75\" height=\"20\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><input type=\"password\" name=\"password\" style=\"width: 125 px\" size=\"20\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.password}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"  maxlength=\"25\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td width=\"100\"><img border=\"0\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/check.jpg\" width=\"75\" height=\"20\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"checkNumber\" id=\"checkNumber\" value=\"\"  maxlength=\"4\" size=\"7\"  onkeydown=\"returnMethod()\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/image.jsp\" name=\"imageNumber\" id=\"imageNumber\" style=\"cursor:hand\" title=\"点击可更换图片\" height=\"20\" onclick=\"checkNumberImage()\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td width=\"100\"><img border=\"0\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/remeber.jpg\" width=\"75\" height=\"20\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"remeberMe\" id=\"remeberMe\" value=\"yes\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.checked }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td width=\"100\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td width=\"100\"><input type=\"button\" class=btn_mouseout onmouseover=\"this.className='btn_mouseover'\" onmouseout=\"this.className='btn_mouseout'\" value=\"登   录\" name=\"huifubtn\" onclick=\"checkFunction()\"></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td width=13%></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t      <td align=\"center\" colspan=3>&nbsp;</td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
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

  private boolean _jspx_meth_s_005factionerror_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:actionerror
    org.apache.struts2.views.jsp.ui.ActionErrorTag _jspx_th_s_005factionerror_005f0 = (org.apache.struts2.views.jsp.ui.ActionErrorTag) _005fjspx_005ftagPool_005fs_005factionerror_005fnobody.get(org.apache.struts2.views.jsp.ui.ActionErrorTag.class);
    _jspx_th_s_005factionerror_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005factionerror_005f0.setParent(null);
    int _jspx_eval_s_005factionerror_005f0 = _jspx_th_s_005factionerror_005f0.doStartTag();
    if (_jspx_th_s_005factionerror_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005factionerror_005fnobody.reuse(_jspx_th_s_005factionerror_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005factionerror_005fnobody.reuse(_jspx_th_s_005factionerror_005f0);
    return false;
  }
}
