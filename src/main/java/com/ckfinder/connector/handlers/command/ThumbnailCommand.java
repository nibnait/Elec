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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ckfinder.connector.configuration.Constants;
import com.ckfinder.connector.configuration.IConfiguration;
import com.ckfinder.connector.errors.ConnectorException;
import com.ckfinder.connector.utils.AccessControlUtil;
import com.ckfinder.connector.utils.FileUtils;
import com.ckfinder.connector.utils.ImageUtils;

/**
 * Class to handle
 * <code>Thumbnail</code> command.
 */
public class ThumbnailCommand extends Command {

	/**
	 *
	 */
	private String fileName;
	/**
	 * thumb file.
	 */
	private File thumbFile;
	private String ifNoneMatch;
	private long ifModifiedSince;
	/**
	 * response field.
	 */
	private HttpServletResponse response;
	/**
	 *
	 */
	private String fullCurrentPath;

	@Override
	public void setResponseHeader(final HttpServletResponse response,
			final ServletContext sc) {
		response.setHeader("Cache-Control", "public");

		String mimetype = getMimeTypeOfImage(sc, response);

		if (mimetype != null) {
			response.setContentType(mimetype);
		}

		response.addHeader("Content-Disposition", "attachment; filename=\"" + this.fileName + "\"");

		// to fill some params later.
		this.response = response;

	}

	/**
	 * get mime type of image.
	 *
	 * @param sc servlet context.
	 * @param response response.
	 * @return mime type of image.
	 */
	private String getMimeTypeOfImage(final ServletContext sc,
			final HttpServletResponse response) {
		if (this.fileName == null || this.fileName.equals("")) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		String tempFileName = this.fileName.substring(0,
				this.fileName.lastIndexOf('.') + 1).concat(
				FileUtils.getFileExtension(this.fileName).toLowerCase());
		String mimeType = sc.getMimeType(tempFileName);
		if (mimeType == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		return mimeType;
	}

	@Override
	public void execute(final OutputStream out) throws ConnectorException {
		validate();
		createThumb();
		if (setResponseHeadersAfterCreatingFile()) {
			try {
				FileUtils.printFileContentToResponse(thumbFile, out);
			} catch (IOException e) {
				if (configuration.isDebugMode()) {
					throw new ConnectorException(e);
				}
				try {
					this.response.sendError(HttpServletResponse.SC_FORBIDDEN);
				} catch (IOException e1) {
					throw new ConnectorException(e1);
				}
			}
		} else {
			try {
				this.response.reset();
				this.response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
			} catch (IOException e1) {
				throw new ConnectorException(e1);
			}
		}
	}

	@Override
	public void initParams(final HttpServletRequest request,
			final IConfiguration configuration, final Object... params)
			throws ConnectorException {
		super.initParams(request, configuration, params);
		this.fileName = getParameter(request, "FileName");
		try {
			this.ifModifiedSince = Long.valueOf(request.getDateHeader("If-Modified-Since"));
		} catch (IllegalArgumentException e) {
			this.ifModifiedSince = 0;
		}
		this.ifNoneMatch = request.getHeader("If-None-Match");
	}

	/**
	 * validate parameters and rights.
	 *
	 * @throws ConnectorException if validation fails.
	 */
	private void validate() throws ConnectorException {


		if (!this.configuration.getThumbsEnabled()) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_THUMBNAILS_DISABLED);
		}

		if (!AccessControlUtil.getInstance(this.configuration).checkFolderACL(
				this.type, this.currentFolder, this.userRole,
				AccessControlUtil.CKFINDER_CONNECTOR_ACL_FILE_VIEW)) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_UNAUTHORIZED);
		}

		if (!FileUtils.checkFileName(this.fileName)) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_REQUEST);
		}

		if (FileUtils.checkIfFileIsHidden(this.fileName, this.configuration)) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_FILE_NOT_FOUND);
		}

		File typeThumbDir = new File(configuration.getThumbsPath()
				+ File.separator + type);

		try {
			this.fullCurrentPath = typeThumbDir.getAbsolutePath()
					+ currentFolder;
			if (!typeThumbDir.exists()) {
				FileUtils.mkdir(typeThumbDir, configuration);
			}
		} catch (SecurityException e) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_ACCESS_DENIED, e);
		}

	}

	/**
	 * if thumbs are enabled and thumb file doesn't exists then creates a thumb
	 * file.
	 *
	 * @throws ConnectorException when error occurs.
	 */
	private void createThumb() throws ConnectorException {
		this.thumbFile = new File(fullCurrentPath, this.fileName);
		try {
			if (!thumbFile.exists()) {
				File orginFile = new File(configuration.getTypes().get(this.type).getPath()
						+ this.currentFolder, this.fileName);
				if (!orginFile.exists()) {
					throw new ConnectorException(
							Constants.Errors.CKFINDER_CONNECTOR_ERROR_FILE_NOT_FOUND);
				}
				try {
					ImageUtils.createThumb(orginFile, thumbFile, configuration);
				} catch (Exception e) {
					thumbFile.delete();
					throw new ConnectorException(
							Constants.Errors.CKFINDER_CONNECTOR_ERROR_ACCESS_DENIED,
							e);
				}
			}
		} catch (SecurityException e) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_ACCESS_DENIED, e);
		}

	}

	/**
	 * fills response headers after creating file.
	 *
	 * @return true if continue returning thumb or false if break and send
	 * response code.
	 * @throws ConnectorException when io error occurs
	 */
	private boolean setResponseHeadersAfterCreatingFile() throws ConnectorException {
		// Set content size
		File file = new File(this.fullCurrentPath, this.fileName);
		try {
			String etag = Long.toHexString(file.lastModified()).concat("-").concat(Long.toHexString(file.length()));
			if (etag.equals(this.ifNoneMatch)) {
				return false;
			} else {
				response.setHeader("Etag", etag);
			}
			if (file.lastModified() <= this.ifModifiedSince) {
				return false;
			} else {
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat df = new SimpleDateFormat(
						"EEE, dd MMMM yyyy HH:mm:ss z");
				response.setHeader("Last-Modified", df.format(date));
			}
			response.setContentLength((int) file.length());
		} catch (SecurityException e) {
			throw new ConnectorException(
					Constants.Errors.CKFINDER_CONNECTOR_ERROR_ACCESS_DENIED, e);
		}
		return true;
	}
}
