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
package com.ckfinder.connector.errors;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ckfinder.connector.ConnectorServlet;
import com.ckfinder.connector.configuration.Constants;
import com.ckfinder.connector.configuration.IConfiguration;

/**
 * Error utils.
 */
public final class ErrorUtils {

	private static ErrorUtils errorUtils;
	private static Map<String, Map<Integer, String>> langMap;

	/**
	 * gets error message by code.
	 *
	 * @param errorCode error number.
	 * @param lang connector language code.
	 * @param conf connector configuration
	 * @return error message
	 */
	public String getErrorMsgByLangAndCode(final String lang,
			final int errorCode,
			final IConfiguration conf) {
		if (lang != null && langMap.get(lang) != null) {
			return langMap.get(lang).get(errorCode);
		} else if (langMap.get(Constants.DEFAULT_LANG_CODE) != null) {
			return langMap.get(Constants.DEFAULT_LANG_CODE).get(errorCode);
		} else {
			if (conf.isDebugMode()) {
				return "Unable to load error message";
			} else {
				return "";
			}

		}

	}

	/**
	 *
	 * @return create if instance doesn't exists and returns it.
	 */
	public static ErrorUtils getInstance() {
		if (errorUtils == null) {
			errorUtils = new ErrorUtils();
		}
		return errorUtils;
	}

	/**
	 * standard constructor.
	 */
	private ErrorUtils() {
		List<String> allAvailLangCodes = getAllLangCodes();
		langMap = new HashMap<>();
		for (String langCode : allAvailLangCodes) {
			langMap.put(langCode, getMessagesByLangCode(langCode));
		}
	}

	/**
	 * gets all available lang codes that have own language files.
	 *
	 * @return list of lang codes.
	 */
	private List<String> getAllLangCodes() {
		return readJarFiles();
	}

	/**
	 * @return read files from jar that matches lang file pattern.
	 */
	private List<String> readJarFiles() {
		List<String> langFiles = new ArrayList<>();
		try {
			URL dirURL = ConnectorServlet.class.getResource("/lang/");
			// #768 there was a problem that files were loaded from work not from jar
			// in work we can get list of files from standard directory
			if ("file".equals(dirURL.getProtocol())) {
				// getPath returns path with spaces converted to "%20"
				// It's a Java feature: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4466485
				// The path needs to be decoded first with URLDecoder
				String path = URLDecoder.decode(dirURL.getPath(), "UTF-8");
				File f = new File(path);
				if (f.exists() && f.isDirectory()) {
					for (File file : f.listFiles()) {
						langFiles.add(file.getName().replaceAll(".xml", ""));
					}
				}
			} else if ("jar".equals(dirURL.getProtocol())) {
				// if url is path to jar content we have to get it other way
				// remove "file:" and all chars after !
				String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
				// changes from "%20" to " "
				JarFile jarFile = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry jarEntry = (JarEntry) entries.nextElement();
					if (checkJarEntry(jarEntry)) {
						String name = jarEntry.getName().replaceAll("lang/", "");
						langFiles.add(name.replaceAll(".xml", ""));
					}
				}
			}
		} catch (IOException e) {
			return new ArrayList<>();
		}
		return langFiles;

	}

	/**
	 * check if jar entry is lang file.
	 *
	 * @param jarEntry jar entry to check.
	 * @return true if jar entry is lang file.
	 */
	private boolean checkJarEntry(final JarEntry jarEntry) {
		return Pattern.compile("lang.+\\.xml").matcher(jarEntry.getName()).matches();
	}

	/**
	 * gets lang messages from file.
	 *
	 * @param langCode lang code.
	 * @return map of lang messages.
	 */
	private Map<Integer, String> getMessagesByLangCode(final String langCode) {
		Map<Integer, String> langCodeMap = new HashMap<>();
		try {

			InputStream is = ConnectorServlet.class.getResourceAsStream("/lang/" + langCode + ".xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);
			NodeList unkonwErrornodeList = doc.getElementsByTagName("errorUnknown");
			NodeList errorNodeList = doc.getElementsByTagName("error");
			Element unkonwErrorElem = (Element) unkonwErrornodeList.item(0);
			langCodeMap.put(1, unkonwErrorElem.getTagName());
												//什么状况

			for (int i = 0; i < errorNodeList.getLength(); i++) {
				Element element = (Element) errorNodeList.item(i);
				langCodeMap.put(Integer.valueOf(element.getAttribute("number")),
						element.getTagName());
			}						//什么状况
		} catch (Exception e) {
			return null;
		}
		return langCodeMap;

	}
}
