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
package com.ckfinder.connector.handlers.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

import org.w3c.dom.Element;

import com.ckfinder.connector.configuration.Constants;
import com.ckfinder.connector.configuration.IConfiguration;
import com.ckfinder.connector.data.XmlAttribute;
import com.ckfinder.connector.data.XmlElementData;
import com.ckfinder.connector.errors.ConnectorException;
import com.ckfinder.connector.utils.AccessControlUtil;

/**
 * Class to handle
 * <code>LoadCookies</code> command.
 */
public class LoadCookiesCommand extends XMLCommand implements IPostCommand {

	/**
	 * list of cookies
	 */
	private Cookie[] cookies;
	/**
	 * Session cookie name
	 */
	private String sessionCookieName;
	/**
	 * Session path parameter name
	 */
	private String sessionPathParamName;

	/**
	 * gets data to XML response.
	 */
	protected int getDataForXml() {

		if (!AccessControlUtil.getInstance(configuration).checkFolderACL(
				this.type, this.currentFolder, this.userRole,
				AccessControlUtil.CKFINDER_CONNECTOR_ACL_FILE_VIEW)) {
			return Constants.Errors.CKFINDER_CONNECTOR_ERROR_UNAUTHORIZED;
		}

		return Constants.Errors.CKFINDER_CONNECTOR_ERROR_NONE;
	}

	@Override
	protected void createXMLChildNodes(final int errorNum, final Element rootElement)
			throws ConnectorException {
		if (errorNum == Constants.Errors.CKFINDER_CONNECTOR_ERROR_NONE) {
			createCookiesData(rootElement);
		}
	}

	/**
	 * Init params for LoadCookies command.
	 *
	 * @param request request
	 * @param configuration connector configuration
	 * @param params execute additional params.
	 * @throws ConnectorException when error occurs.
	 */
	@Override
	public void initParams(final HttpServletRequest request,
			final IConfiguration configuration, final Object... params)
			throws ConnectorException {

		super.initParams(request, configuration, params);
		this.cookies = request.getCookies();
		this.sessionCookieName = ((String) request.getAttribute("session.cookie.name"));
		this.sessionPathParamName = ((String) request.getAttribute("session.parameter.name"));
	}

	/**
	 * creates Cookies data node in XML response.
	 *
	 * @param rootElement root element from XML.
	 */
	private void createCookiesData(final Element rootElement) {
		Element element = creator.getDocument().createElement("Cookies");

		if (this.sessionCookieName != null) {
			element.setAttribute("sessionCookieName", this.sessionCookieName);
		}

		if (this.sessionPathParamName != null) {
			element.setAttribute("sessionParameterName", this.sessionPathParamName);
		}

		for (int i = 0; i < this.cookies.length; i++) {
			if (!cookies[i].getName().startsWith("CKFinder_")) {
				XmlElementData elementData = new XmlElementData("Cookie");
				XmlAttribute attribute = new XmlAttribute("name", cookies[i].getName());
				elementData.getAttributes().add(attribute);
				attribute = new XmlAttribute("value", cookies[i].getValue());
				elementData.getAttributes().add(attribute);
				elementData.addToDocument(this.creator.getDocument(), element);
			}
		}
		rootElement.appendChild(element);
	}
}
