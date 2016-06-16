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

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ckfinder.connector.configuration.Configuration;
import com.ckfinder.connector.configuration.ConfigurationFactory;
import com.ckfinder.connector.configuration.Constants;
import com.ckfinder.connector.configuration.Events.EventTypes;
import com.ckfinder.connector.configuration.IConfiguration;
import com.ckfinder.connector.data.BeforeExecuteCommandEventArgs;
import com.ckfinder.connector.errors.ConnectorException;
import com.ckfinder.connector.handlers.command.Command;
import com.ckfinder.connector.handlers.command.CopyFilesCommand;
import com.ckfinder.connector.handlers.command.CreateFolderCommand;
import com.ckfinder.connector.handlers.command.DeleteFilesCommand;
import com.ckfinder.connector.handlers.command.DeleteFolderCommand;
import com.ckfinder.connector.handlers.command.DownloadFileCommand;
import com.ckfinder.connector.handlers.command.ErrorCommand;
import com.ckfinder.connector.handlers.command.FileUploadCommand;
import com.ckfinder.connector.handlers.command.GetFilesCommand;
import com.ckfinder.connector.handlers.command.GetFoldersCommand;
import com.ckfinder.connector.handlers.command.IPostCommand;
import com.ckfinder.connector.handlers.command.InitCommand;
import com.ckfinder.connector.handlers.command.LoadCookiesCommand;
import com.ckfinder.connector.handlers.command.MoveFilesCommand;
import com.ckfinder.connector.handlers.command.QuickUploadCommand;
import com.ckfinder.connector.handlers.command.RenameFileCommand;
import com.ckfinder.connector.handlers.command.RenameFolderCommand;
import com.ckfinder.connector.handlers.command.ThumbnailCommand;
import com.ckfinder.connector.handlers.command.XMLCommand;
import com.ckfinder.connector.handlers.command.XMLErrorCommand;
import com.ckfinder.connector.utils.AccessControlUtil;

/**
 * Main connector servlet for handling CKFinder requests.
 */
public class ConnectorServlet extends HttpServlet {

	/**
	 * holds excetption if any occures during CKFinder start.
	 */
	private Exception startException;
	/**
	 */
	private static final long serialVersionUID = 2960665641425153638L;

	/**
	 * Handling get requests.
	 *
	 * @param request request
	 * @param response response
	 * @throws IOException .
	 * @throws ServletException .
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		getResponse(request, response, false);
	}

	/**
	 * Handling post requests.
	 *
	 * @param request request
	 * @param response response
	 * @throws IOException .
	 * @throws ServletException .
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		getResponse(request, response, true);
	}

	/**
	 * Creating reponse for every command in request param.
	 *
	 * @param request request
	 * @param response response
	 * @param post if it's post command.
	 * @throws ServletException when error occurs.
	 */
	private void getResponse(final HttpServletRequest request,
			final HttpServletResponse response, final boolean post)
			throws ServletException {
		if (startException != null
				&& Boolean.valueOf(getServletConfig().getInitParameter("debug"))) {
			throw new ServletException(startException);
		}
		String command = request.getParameter("command");
		IConfiguration configuration;
		try {
			configuration = ConfigurationFactory.getInstace().getConfiguration(request);
			if (configuration == null) {
				throw new Exception("Configuration wasn't initialized correctly. Check server logs.");
			}
		} catch (Exception e) {
			if (Boolean.valueOf(getServletConfig().getInitParameter("debug"))) {
				e.printStackTrace();
			}
			throw new ServletException(e);
		}
		try {

			if (command == null || command.equals("")) {
				throw new ConnectorException(
						Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_COMMAND, false);
			}

			configuration.setDebugMode(
					Boolean.valueOf(getServletConfig().getInitParameter("debug")));

			CommandHandlerEnum cmd;

			try {
				cmd = CommandHandlerEnum.valueOf(command.toUpperCase());
				// checks if command should go via POST request or it's a post request
				// and it's not upload command
				if ((cmd.getCommand() instanceof IPostCommand || post)
						&& !CommandHandlerEnum.FILEUPLOAD.equals(cmd)
						&& !CommandHandlerEnum.QUICKUPLOAD.equals(cmd)) {
					checkPostRequest(request);
				}
			} catch (IllegalArgumentException e1) {
				//Ignore custom plugins commands 
			}

			BeforeExecuteCommandEventArgs args = new BeforeExecuteCommandEventArgs();
			args.setCommand(command);
			args.setRequest(request);
			args.setResponse(response);

			if (configuration.getEvents() != null) {
				if (configuration.getEvents().run(EventTypes.BeforeExecuteCommand,
						args, configuration)) {
					cmd = CommandHandlerEnum.valueOf(command.toUpperCase());
					cmd.execute(
							request, response, configuration, getServletContext());
				}
			} else {
				cmd = CommandHandlerEnum.valueOf(command.toUpperCase());
				cmd.execute(
						request, response, configuration, getServletContext());
			}
		} catch (IllegalArgumentException e) {
			if (Boolean.valueOf(getServletConfig().getInitParameter("debug"))) {
				e.printStackTrace();
				response.reset();
				throw new ServletException(e);
			} else {
				handleError(
						new ConnectorException(
						Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_COMMAND, false),
						configuration, request, response, command);
			}
		} catch (ConnectorException e) {
			if (Boolean.valueOf(getServletConfig().getInitParameter("debug"))
					&& e.getException() != null) {
				e.getException().printStackTrace();
				response.reset();
				throw new ServletException(e.getException());
			} else {
				handleError(e, configuration, request, response, command);
			}
		} catch (Exception e) {
			if (Boolean.valueOf(getServletConfig().getInitParameter("debug"))) {
				e.printStackTrace();
				response.reset();
				throw new ServletException(e);
			} else {
				handleError(new ConnectorException(e), configuration,
						request, response, command);
			}
		}
	}

	/**
	 * checks post request if it's ckfinder command.
	 *
	 * @param request request
	 * @throws ConnectorException when param isn't set or has wrong value.
	 */
	private void checkPostRequest(final HttpServletRequest request)
			throws ConnectorException {
		if (request.getParameter("CKFinderCommand") == null
				|| !request.getParameter("CKFinderCommand").equals("true")) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_REQUEST, true);
		}

	}

	/**
	 * handles error from execute command.
	 *
	 * @param e exception
	 * @param request request
	 * @param response response
	 * @param configuration connector configuration
	 * @param currentCommand current command
	 * @throws ServletException when error handling fails.
	 */
	private void handleError(final ConnectorException e,
			final IConfiguration configuration,
			final HttpServletRequest request, final HttpServletResponse response,
			final String currentCommand)
			throws ServletException {
		try {
			if (currentCommand != null) {
				Command command = CommandHandlerEnum.valueOf(
						currentCommand.toUpperCase()).getCommand();
				if (command instanceof XMLCommand) {
					CommandHandlerEnum.XMLERROR.execute(request, response, configuration,
							getServletContext(), e);
				} else {
					CommandHandlerEnum.ERROR.execute(request, response, configuration,
							getServletContext(), e);
				}
			} else {
				CommandHandlerEnum.XMLERROR.execute(request, response, configuration,
						getServletContext(), e);
			}



		} catch (Exception e1) {
			throw new ServletException(e1);
		}
	}

	@Override
	public void init() throws ServletException {
		ServletContextFactory.setServletContext(getServletContext());
		IConfiguration configuration;
		try {
			String className = getServletConfig().getInitParameter(
					"configuration");
			if (className != null) {
				Class<?> clazz = Class.forName(className);

				if (clazz.getConstructor(ServletConfig.class) != null) {
					configuration = (IConfiguration) clazz.getConstructor(
							ServletConfig.class).newInstance(getServletConfig());

				} else {
					configuration = (IConfiguration) clazz.newInstance();
				}
			} else {
				configuration = new Configuration(getServletConfig());
			}
		} catch (Exception e) {
			configuration = new Configuration(getServletConfig());
		}
		try {
			configuration.init();
			AccessControlUtil.getInstance(configuration).loadACLConfig();
		} catch (Exception e) {
			if (Boolean.valueOf(getServletConfig().getInitParameter("debug"))) {
				e.printStackTrace();
			}
			this.startException = e;
			configuration = null;
		}
		ConfigurationFactory.getInstace().setConfiguration(configuration);
	}

	/**
	 * Enum with all command handles by servlet.
	 *
	 */
	private enum CommandHandlerEnum {

		/**
		 * init command.
		 */
		INIT(new InitCommand()),
		/**
		 * get subfolders for seleted location command.
		 */
		GETFOLDERS(new GetFoldersCommand()),
		/**
		 * get files from current folder command.
		 */
		GETFILES(new GetFilesCommand()),
		/**
		 * sends cookies from the server.
		 */
		LOADCOOKIES(new LoadCookiesCommand()),
		/**
		 * get thumbnail for file command.
		 */
		THUMBNAIL(new ThumbnailCommand()),
		/**
		 * download file command.
		 */
		DOWNLOADFILE(new DownloadFileCommand()),
		/**
		 * create subfolder.
		 */
		CREATEFOLDER(new CreateFolderCommand()),
		/**
		 * rename file.
		 */
		RENAMEFILE(new RenameFileCommand()),
		/**
		 * rename folder.
		 */
		RENAMEFOLDER(new RenameFolderCommand()),
		/**
		 * delete folder.
		 */
		DELETEFOLDER(new DeleteFolderCommand()),
		/**
		 * copy files.
		 */
		COPYFILES(new CopyFilesCommand()),
		/**
		 * move files.
		 */
		MOVEFILES(new MoveFilesCommand()),
		/**
		 * delete files.
		 */
		DELETEFILES(new DeleteFilesCommand()),
		/**
		 * file upload.
		 */
		FILEUPLOAD(new FileUploadCommand()),
		/**
		 * quick file upload.
		 */
		QUICKUPLOAD(new QuickUploadCommand()),
		/**
		 * XML error command.
		 */
		XMLERROR(new XMLErrorCommand()),
		/**
		 * error command.
		 */
		ERROR(new ErrorCommand());
		/**
		 * command class for enum field.
		 */
		private Command command;

		/**
		 * Enum contructor to set command.
		 *
		 * @param command1 command name
		 */
		private CommandHandlerEnum(final Command command1) {
			this.command = command1;
		}

		/**
		 * Execute command.
		 *
		 * @param request request
		 * @param response response
		 * @param configuration connector configuraion
		 * @param sc servletContext
		 * @param params params for command.
		 * @throws ConnectorException when error occurs
		 */
		private void execute(final HttpServletRequest request,
				final HttpServletResponse response, final IConfiguration configuration,
				final ServletContext sc, final Object... params)
				throws ConnectorException {
			Command com;
			try {
				com = command.getClass().newInstance();
			} catch (IllegalAccessException e1) {
				throw new ConnectorException(
						Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_COMMAND);
			} catch (InstantiationException e1) {
				throw new ConnectorException(
						Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_COMMAND);
			}
			if (com == null) {
				throw new ConnectorException(
						Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_COMMAND);
			}
			com.runCommand(request, response, configuration, params);
		}

		/**
		 * gets command.
		 *
		 * @return command
		 */
		public Command getCommand() {
			return this.command;
		}
	}
}
