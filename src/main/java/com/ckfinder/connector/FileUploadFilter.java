/*
 * CKFinder
 * ========
 * http://ckfinder.com
 * Copyright (C) 2007-2013, CKSource - Frederico Knabben. All rights reserved.
 *
 * The software, this file and its contents are subject to the CKFinder
 * License. Please read the license.txt file before using, installing, copying,
 * modifying or distribute this file or part of its contents. The contents of
 * this file is part of the Source Code of CKFinder.
 */
package com.ckfinder.connector;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUploadFilter implements Filter {

	private static final String CONTENT_LENGTH = "content-length";
	// name of param from flash upload with session id
	private static final String JSESSIONID = "jsessionid";
	//Default name of session cookie param
	private static final String JSID_PARAM_NAME = "JSESSIONID";
	//Alternative name of session cookie
	private static final String USER_SESSION_COOKIE_NAME = "sessionCookieName";
	//Alternative name for the session path parameter
	private static final String USER_SESSION_PATH_PARAMETER_NAME = "sessionParameterName";
	//Filter configuration
	private FilterConfig config = null;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if ("LoadCookies".equalsIgnoreCase(request.getParameter("command"))) {
			request.setAttribute("session.cookie.name", getSessionCookieName());
			request.setAttribute("session.parameter.name", getSessionParameterName());
		}
		// only for fileupload
		if ("FILEUPLOAD".equalsIgnoreCase(request.getParameter("command"))
			&& (request instanceof HttpServletRequest) 
			&& (response instanceof HttpServletResponse)) {
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				String contentLength = httpRequest.getHeader(CONTENT_LENGTH);
				if (contentLength != null && Integer.parseInt(contentLength) == 0) {
					HttpServletResponse httpResponse = (HttpServletResponse) response;
					setSessionCookie((HttpServletResponse) response,
							httpRequest);
					return;
				}
		}
		filterChain.doFilter(request, response);
	}

	/*
	 * write all cookies back to the flex test response
	 */
	@SuppressWarnings("unchecked")
	private void setSessionCookie(HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {

		if (httpRequest.getParameter(getSessionParameterName()) != null) {
			Cookie userCookie = new Cookie(getSessionCookieName(), httpRequest.getParameter(getSessionParameterName()));
			httpResponse.addCookie(userCookie);
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	public void destroy() {
	}

	private String getSessionCookieName() {
		return config.getInitParameter(USER_SESSION_COOKIE_NAME) == null ? JSID_PARAM_NAME
				: config.getInitParameter(USER_SESSION_COOKIE_NAME);
	}

	private String getSessionParameterName() {
		return config.getInitParameter(USER_SESSION_PATH_PARAMETER_NAME) == null ? JSESSIONID
				: config.getInitParameter(USER_SESSION_PATH_PARAMETER_NAME);
	}
}
