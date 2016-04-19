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

import com.ckfinder.connector.utils.FileUtils;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Class to handle
 * <code>QuickUpload</code> command.
 */
public class QuickUploadCommand extends FileUploadCommand {

	@Override
	protected void handleOnUploadCompleteResponse(final OutputStream out,
			final String errorMsg) throws IOException {
		out.write("window.parent.OnUploadCompleted(".getBytes("UTF-8"));
		out.write(("" + this.errorCode + ", ").getBytes("UTF-8"));
		if (uploaded) {
			out.write(("\'" + configuration.getTypes().get(this.type).getUrl()
					+ this.currentFolder
					+ FileUtils.backupWithBackSlash(FileUtils.encodeURIComponent(this.newFileName), "'")
					+ "\', ").getBytes("UTF-8"));
			out.write(("\'" + FileUtils.backupWithBackSlash(this.newFileName, "'")
					+ "\', ").getBytes("UTF-8"));
		} else {
			out.write("\'\', \'\', ".getBytes("UTF-8"));
		}
		out.write("\'\'".getBytes("UTF-8"));
		out.write(");".getBytes("UTF-8"));
	}

	@Override
	protected void handleOnUploadCompleteCallFuncResponse(final OutputStream out,
			final String errorMsg, final String path) throws IOException {
		this.ckEditorFuncNum = this.ckEditorFuncNum.replaceAll(
				"[^\\d]", "");
		out.write(("window.parent.CKEDITOR.tools.callFunction("
				+ this.ckEditorFuncNum + ", '"
				+ path
				+ FileUtils.backupWithBackSlash(FileUtils.encodeURIComponent(this.newFileName), "'")
				+ "', '" + errorMsg + "');").getBytes("UTF-8"));
	}

	@Override
	protected boolean checkFuncNum() {
		return this.ckEditorFuncNum != null;
	}
}
