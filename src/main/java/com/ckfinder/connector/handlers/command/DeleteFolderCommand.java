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

import org.w3c.dom.Element;

import com.ckfinder.connector.configuration.Constants;
import com.ckfinder.connector.errors.ConnectorException;
import com.ckfinder.connector.utils.AccessControlUtil;
import com.ckfinder.connector.utils.FileUtils;

/**
 * Class to handle
 * <code>DeleteFolder</code> command.
 */
public class DeleteFolderCommand extends XMLCommand implements IPostCommand {

	@Override
	protected void createXMLChildNodes(final int errorNum, final Element rootElement)
			throws ConnectorException {
	}

	/**
	 * @return error code or 0 if ok. Deletes folder and thumb folder.
	 */
	@Override
	protected int getDataForXml() {

		if (!AccessControlUtil.getInstance(this.configuration).checkFolderACL(this.type,
				this.currentFolder,
				this.userRole,
				AccessControlUtil.CKFINDER_CONNECTOR_ACL_FOLDER_DELETE)) {
			return Constants.Errors.CKFINDER_CONNECTOR_ERROR_UNAUTHORIZED;
		}
		if (this.currentFolder.equals("/")) {
			return Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_REQUEST;
		}

		if (FileUtils.checkIfDirIsHidden(this.currentFolder, configuration)) {
			return Constants.Errors.CKFINDER_CONNECTOR_ERROR_INVALID_REQUEST;
		}

		File dir = new File(configuration.getTypes().get(this.type).getPath()
				+ this.currentFolder);

		try {
			if (!dir.exists() || !dir.isDirectory()) {
				return Constants.Errors.CKFINDER_CONNECTOR_ERROR_FOLDER_NOT_FOUND;
			}

			if (FileUtils.delete(dir)) {
				File thumbDir = new File(configuration.getThumbsPath()
						+ File.separator
						+ type
						+ this.currentFolder);
				FileUtils.delete(thumbDir);
			} else {
				return Constants.Errors.CKFINDER_CONNECTOR_ERROR_ACCESS_DENIED;
			}
		} catch (SecurityException e) {
			if (configuration.isDebugMode()) {
				throw e;
			} else {
				return Constants.Errors.CKFINDER_CONNECTOR_ERROR_ACCESS_DENIED;
			}
		}

		return Constants.Errors.CKFINDER_CONNECTOR_ERROR_NONE;
	}
}
