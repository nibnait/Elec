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
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import com.ckfinder.connector.ServletContextFactory;

/**
 * Path builder that creates default values of baseDir and baseURL.
 */
public class DefaultPathBuilder implements IBasePathBuilder {

	/**
	 * gets default value of baseDir (based on baseURL).
	 *
	 * @param request request
	 * @return default baseDir value
	 */
	public String getBaseDir(final HttpServletRequest request) {
		String newBaseUrl = getBaseUrl(request);
		if (Pattern.matches(Constants.URL_REGEX, getBaseUrl(request))) {
			newBaseUrl = newBaseUrl.substring(newBaseUrl.indexOf(
					request.getContextPath()));
		}
		newBaseUrl = newBaseUrl.replace(request.getContextPath(), "");

		try {
			return ServletContextFactory.getServletContext().getRealPath(newBaseUrl);
		} catch (Exception e) {
			return newBaseUrl;
		}
	}

	/**
	 * gets default value of baseURL.
	 *
	 * @param request request
	 * @return default baseURL value
	 */
	public String getBaseUrl(final HttpServletRequest request) {
		return request.getContextPath().concat(IConfiguration.DEFAULT_BASE_URL);
	}
}
