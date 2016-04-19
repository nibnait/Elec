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
package com.ckfinder.connector.configuration;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface IBasePathBuilder {

	/**
	 * create baseDir value.
	 *
	 * @param request erquest
	 * @return value of baseDir
	 */
	public String getBaseDir(HttpServletRequest request);

	/**
	 * create baseURL value.
	 *
	 * @param request erquest
	 * @return value of baseURL
	 */
	public String getBaseUrl(HttpServletRequest request);
}
