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

import javax.servlet.ServletContext;

/**
 * Access to servletcontex outside from servlet.
 */
public class ServletContextFactory {

	/**
	 * ServletContext object.
	 */
	private static ServletContext servletContext;

	/**
	 * sets servlet context.
	 *
	 * @param servletContext1 context
	 */
	static void setServletContext(final ServletContext servletContext1) {
		servletContext = servletContext1;
	}

	/**
	 * returns servlet context object or throws exception if it isn't set.
	 *
	 * @return ServletContext object
	 * @throws Exception when ServletcCntext is not set for an object.
	 */
	public static ServletContext getServletContext() throws Exception {
		if (servletContext != null) {
			return servletContext;
		} else {
			throw new Exception("Servlet contex is null. Try to restart server.");
		}

	}
}
