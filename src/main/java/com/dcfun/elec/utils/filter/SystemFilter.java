package com.dcfun.elec.utils.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dcfun.elec.domain.ElecUser;

public class SystemFilter implements Filter{

	List<String> list = new ArrayList<String>();//存放一些 本来就没有Session的URL
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		list.add("/index.jsp");
		list.add("/image.jsp");
		list.add("/system/elecMenuAction_menuHome.do");
		list.add("/system/elecMenuAction_menutitle.do");
		list.add("/system/elecMenuAction_menuleft.do");
		
		//5秒倒计时的页面
		list.add("/error.jsp");
		list.add("/system/elecMenuAction_logout.do");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String path = request.getServletPath();
		
		//在跳转index.jsp页面之前 先获取Cookie, 传值的方式给index.jsp
		this.initIndexPage(request, path);
		
		/**添加粗颗粒权限控制*/
		//没有Session需要放行的连接
		if (list.contains(path)) {
			//放行
			chain.doFilter(request, response);
			return;
		}
		//判断Session是否存在
		ElecUser elecUser = (ElecUser)request.getSession().getAttribute("globle_user");
		//Session中存在数据，放行，指定对应的URL的页面
		if(elecUser!=null){
			//放行
			chain.doFilter(request, response);
			return;
		}
		//如果Session中不存在数据，重定向到error.jsp的页面上
		response.sendRedirect(request.getContextPath()+"/error.jsp");
		
	}

	private void initIndexPage(HttpServletRequest request, String path) {
		if (path!=null && path.equals("/index.jsp")) {
			
			String name = "";
			String password = "";
			String checked = "";
			
			Cookie[] cookies = request.getCookies();
			
			if (cookies!=null && cookies.length>0) {
				for(Cookie cookie:cookies){
					if (cookie.getName().equals("name")) {
						//得到用户名
						name = cookie.getValue();
						
						//如果是中文 需转码
						try {
							name = URLDecoder.decode(name, "utf-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//复选框 选中
						checked = "checked";
					}
					if (cookie.getName().equals("password")) {
						password = cookie.getValue();						
					}
				}
			}
			
			//存放到request中
			request.setAttribute("name", name);
			request.setAttribute("password", password);
			request.setAttribute("checked", checked);
			
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
