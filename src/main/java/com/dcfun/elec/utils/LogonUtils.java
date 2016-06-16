package com.dcfun.elec.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.print.attribute.standard.MediaSize.NA;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class LogonUtils {

	private LogonUtils() {
	}

	public static boolean checkImageNumber(HttpServletRequest request) {

		// 获取页面的验证码
		String imageNumber = request.getParameter("checkNumber");
		if (StringUtils.isBlank(imageNumber)) {
			return false;
		}
		// 从Session中获取生成的验证码
		String CHECK_NUMBER_KEY = (String) request.getSession().getAttribute(
				"CHECK_NUMBER_KEY");
		if (StringUtils.isBlank(CHECK_NUMBER_KEY)) {
			return false;
		}
		return imageNumber.equalsIgnoreCase(CHECK_NUMBER_KEY);
	}

	public static void remeberMe(String logonName, String logonPwd,
			HttpServletRequest request, HttpServletResponse response) {
		//创建2个Cookie,分别存放用户名、密码
		//Cookie中不能存放中文
		try {
			logonName = URLEncoder.encode(logonName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cookie nameCookie = new Cookie("name",logonName);
		Cookie passwordCookie = new Cookie("password",logonPwd);
		//设置有效路径
		nameCookie.setPath(request.getContextPath()+"/");
		passwordCookie.setPath(request.getContextPath()+"/");
		
		String remeberMe = request.getParameter("remeberMe");
		if (remeberMe!=null && remeberMe.equals("yes")) {
			//设置有效时间
			nameCookie.setMaxAge(7*24*60*60);
			passwordCookie.setMaxAge(7*24*60*60);
			
		}else{
			//清空Cookie
			nameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
			nameCookie.setPath(null);
			passwordCookie.setPath(null);
		}
		
		//将Cookie放到response对象中
		response.addCookie(nameCookie);
		response.addCookie(passwordCookie);
		
	}

}
