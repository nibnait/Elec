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
package com.ckfinder.connector.utils;

import com.ckfinder.connector.configuration.Constants;
import java.util.regex.Pattern;

/**
 * Utils used to change paths in connector.
 */
public class PathUtils {

	private PathUtils() {
	}

	/**
	 * Escapes double slashes (//) and replace all backslashes (\) with slashes
	 * (/). Additionally preserves UNC paths.
	 *
	 * @param string - string to escape.
	 * @return escaped string.
	 */
	public static String escape(String string) {
		if (string == null || string.equals("")) {
			return string;
		}
		String prefix = "";
		if (string.indexOf("://") != -1) {
			prefix = string.substring(0, string.indexOf("://") + 3);
			string = string.replaceFirst(prefix, "");
		}
		string = string.replaceAll("\\\\", "/");

		// preserve // at the beginning for UNC paths
		if (string.startsWith("//")) {
			string = "/" + string.replaceAll("/+", "/");
		} else {
			string = string.replaceAll("/+", "/");
		}
		return prefix.concat(string);
	}

	/**
	 * Adds slash at the end of String if it doesn't already start with slash
	 *
	 * @param string - input string.
	 * @return String with slash at the end, null or empty String.
	 */
	public static String addSlashToEnd(String string) {
		if (string != null && !string.equals("")
				&& string.charAt(string.length() - 1) != '/') {
			return string.concat("/");
		}
		return string;
	}

	/**
	 * Adds slash at the beginning of String provided that String is not ULR
	 *
	 * @param string - input string.
	 * @return String with slash at the beginning, null or empty String.
	 */
	public static String addSlashToBeginning(String string) {
		if (string == null || string.charAt(0) == '/'
				|| Pattern.matches(Constants.URL_REGEX, string)) {
			return string;
		}
		return "/".concat(string);
	}

	/**
	 * Removes slash from the beginning if necessary.
	 *
	 * @param string - input string.
	 * @return String without slash at the beginning, null or empty String.
	 */
	public static String removeSlashFromBeginning(String string) {
		if (string != null && !string.equals("") && string.charAt(0) == '/') {
			return string.substring(1, string.length());
		}
		return string;
	}

	/**
	 * Removes slash from the end if necessary.
	 *
	 * @param string - input string.
	 * @return String without slash at the end, null or empty String.
	 */
	public static String removeSlashFromEnd(String string) {
		if (string != null && !string.equals("")
				&& string.charAt(string.length() - 1) == '/') {
			return string.substring(0, string.length() - 1);
		}
		return string;
	}
}