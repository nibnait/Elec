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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ckfinder.connector.ServletContextFactory;
import com.ckfinder.connector.configuration.Constants;
import com.ckfinder.connector.configuration.IConfiguration;
import com.ckfinder.connector.errors.ConnectorException;
import com.ckfinder.connector.utils.FileUtils;
import com.ckfinder.connector.utils.PathUtils;

/**
 * Base class for all command handlers.
 */
public abstract class Command {

	/**
	 * exception caught in fileUpload in debug mode should be thrown to servlet
	 * response.
	 */
	protected Exception exception;
	/**
	 * connector configuration.
	 */
	protected IConfiguration configuration;
	protected String userRole;
	protected String currentFolder;
	protected String type;

	/**
	 * standard constructor.
	 */
	public Command() {
		configuration = null;
		userRole = null;
		currentFolder = null;
		type = null;
	}

	/**
	 * Runs command. Initialize, sets response and execute command.
	 *
	 * @param request request
	 * @param response response
	 * @param configuration1 connector configuration
	 * @param params additional execute params.
	 * @throws ConnectorException when error occured
	 */
	public void runCommand(final HttpServletRequest request,
			final HttpServletResponse response,
			final IConfiguration configuration1,
			final Object... params) throws ConnectorException {
		this.initParams(request, configuration1, params);
		try {
			setResponseHeader(response, ServletContextFactory.getServletContext());
			execute(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (ConnectorException e) {
			throw e;
		} catch (IOException e) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_ACCESS_DENIED, e);
		} catch (Exception e) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_ACCESS_DENIED, e);
		}
	}

	/**
	 * initialize params for command handler.
	 *
	 * @param request request
	 * @param configuration connector configuration
	 * @param params execute additional params.
	 * @throws ConnectorException to handle in error handler.
	 */
	public void initParams(final HttpServletRequest request,
			final IConfiguration configuration, final Object... params)
			throws ConnectorException {
		if (configuration != null) {
			this.configuration = configuration;
			this.userRole = (String) request.getSession().getAttribute(
					configuration.getUserRoleName());


			getCurrentFolderParam(request);

			if (checkConnector(request) && checkParam(this.currentFolder)) {
				this.currentFolder = PathUtils.escape(this.currentFolder);
				if (!checkHidden() && ((this.currentFolder == null || this.currentFolder.equals(""))
						|| checkIfCurrFolderExists(request))) {
						this.type = getParameter(request, "type");
				}

			}
		}


	}

	/**
	 * check if connector is enabled and checks authentication.
	 *
	 * @param request current request.
	 * @return true if connector is enabled and user is authenticated
	 * @throws ConnectorException when connector is disabled
	 */
	protected boolean checkConnector(final HttpServletRequest request)
			throws ConnectorException {
		if (!configuration.enabled() || !configuration.checkAuthentication(request)) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_CONNECTOR_DISABLED, false);
		}
		return true;
	}

	/**
	 * checks if current folder exists.
	 *
	 * @param request request
	 * @return true if exists
	 * @throws ConnectorException if doesn't exists
	 */
	protected boolean checkIfCurrFolderExists(final HttpServletRequest request)
			throws ConnectorException {
		String tmpType = getParameter(request, "type");
		if (tmpType != null) {
			File currDir = new File(
					configuration.getTypes().get(tmpType).getPath()
					+ this.currentFolder);
			if (!currDir.exists() || !currDir.isDirectory()) {
				throw new ConnectorException(
						Constants.Errors.CKFINDER_CONNECTOR_ERROR_FOLDER_NOT_FOUND,
						false);
			} else {
				return true;
			}
		}
		return true;

	}

	/**
	 * checks if current folder is hidden.
	 *
	 * @return false if isn't.
	 * @throws ConnectorException when is hidden
	 */
	protected boolean checkHidden() throws ConnectorException {
		if (FileUtils.checkIfDirIsHidden(this.currentFolder, configuration)) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_REQUEST,
					false);
		}
		return false;
	}

	/**
	 * executes command and writes to response.
	 *
	 * @param out response output stream
	 * @throws ConnectorException when error occurs
	 */
	public abstract void execute(final OutputStream out)
			throws ConnectorException;

	/**
	 * sets header in response.
	 *
	 * @param response servlet response
	 * @param sc sevletcontext
	 */
	public abstract void setResponseHeader(final HttpServletResponse response,
			final ServletContext sc);

	/**
	 * check request for security issue.
	 *
	 * @param reqParam request param
	 * @return true if validation passed
	 * @throws ConnectorException if valdation error occurs.
	 */
	protected boolean checkParam(final String reqParam)
			throws ConnectorException {
		if (reqParam == null || reqParam.equals("")) {
			return true;
		}
		if (Pattern.compile(Constants.INVALID_PATH_REGEX).matcher(reqParam).find()) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_NAME,
					false);
		}

		return true;
	}

	/**
	 * Gets request param value with correct encoding.
	 *
	 * @param request reqeust
	 * @param paramName request param name
	 * @return param value
	 */
	protected String getParameter(final HttpServletRequest request,
			final String paramName) {
		if (request.getParameter(paramName) == null) {
			return null;
		}
		return FileUtils.convertFromUriEncoding(
				request.getParameter(paramName), configuration);
	}

	/**
	 * gets current folder request param or sets default value if it's not set.
	 *
	 * @param request request
	 */
	protected void getCurrentFolderParam(final HttpServletRequest request) {
		String currFolder = getParameter(request, "currentFolder");
		if (currFolder == null || currFolder.equals("")) {
			this.currentFolder = "/";
		} else {
			this.currentFolder = PathUtils.addSlashToBeginning(PathUtils.addSlashToEnd(currFolder));
		}
	}
}
