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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ckfinder.connector.data.AccessControlLevel;
import com.ckfinder.connector.data.PluginInfo;
import com.ckfinder.connector.data.ResourceType;

/**
 * Interface for configuration.
 */
public interface IConfiguration {

	int DEFAULT_IMG_WIDTH = 500;
	int DEFAULT_IMG_HEIGHT = 400;
	int DEFAULT_THUMB_MAX_WIDTH = 100;
	int DEFAULT_THUMB_MAX_HEIGHT = 100;
	float DEFAULT_IMG_QUALITY = 0.8f;
	String DEFAULT_THUMBS_URL = "_thumbs/";
	String DEFAULT_THUMBS_DIR = "%BASE_DIR%_thumbs/";
	boolean DEFAULT_CHECKAUTHENTICATION = true;
	String DEFAULT_URI_ENCODING = "UTF-8";
	String DEFAULT_BASE_URL = "/userfiles";

	/**
	 * creates new instace of configuration for request.
	 *
	 * @return new configuration instace
	 */
	public IConfiguration cloneConfiguration();

	/**
	 * inits configuration.
	 *
	 * @throws Exception when error during init occurse
	 */
	public void init() throws Exception;

	/**
	 * method to check if user is authentificated.
	 *
	 * @param request current request
	 * @return true if is
	 */
	public boolean checkAuthentication(final HttpServletRequest request);

	/**
	 * gets user role name sets in config.
	 *
	 * @return role name
	 */
	public String getUserRoleName();

	/**
	 * gets resources map types with names as map keys.
	 *
	 * @return resources map
	 */
	public Map<String, ResourceType> getTypes();

	/**
	 * gets location of ckfinder in app. For ex. /ckfinder/.
	 *
	 * @return base directory
	 */
	public String getBaseDir();

	/**
	 * returns path to ckfinder with app name for ex. /webapp/ckfinder/.
	 *
	 * @return base url
	 */
	public String getBaseURL();

	/**
	 * returns license key.
	 *
	 * @return license key
	 */
	public String getLicenseKey();

	/**
	 * returns license name.
	 *
	 * @return license name.
	 */
	public String getLicenseName();

	/**
	 * gets image max width.
	 *
	 * @return max image height
	 */
	public Integer getImgWidth();

	/**
	 * get image max height.
	 *
	 * @return max image height
	 */
	public Integer getImgHeight();

	/**
	 * get image quality.
	 *
	 * @return image quality
	 */
	public float getImgQuality();

	/**
	 * check if connector is enabled.
	 *
	 * @return if connector is enabled
	 */
	public boolean enabled();

	/**
	 * check if thums are enabled.
	 *
	 * @return true if thums are enabled
	 */
	public boolean getThumbsEnabled();

	/**
	 * gets url to thumbs dir(path from baseUrl).
	 *
	 * @return thumbs url
	 */
	public String getThumbsURL();

	/**
	 * gets path to thumbs directory.
	 *
	 * @return thumbs directory
	 */
	public String getThumbsDir();

	/**
	 * gets path to thumbs directory.
	 *
	 * @return thumbs directory
	 */
	public String getThumbsPath();

	/**
	 * gets thumbs quality.
	 *
	 * @return thumbs quality
	 */
	public float getThumbsQuality();

	/**
	 * Sets path to thumbs directory.
	 *
	 * @param directory thumbs directory
	 */
	public void setThumbsPath(final String directory);

	/**
	 * checks if thumbs are accessed direct.
	 *
	 * @return true if thumbs can be accessed directly
	 */
	public boolean getThumbsDirectAccess();

	/**
	 * gets max width of thumb.
	 *
	 * @return max width of thumb
	 */
	public int getMaxThumbWidth();

	/**
	 * gets max height of thumb.
	 *
	 * @return max height of thumb
	 */
	public int getMaxThumbHeight();

	/**
	 * get list of access control levels.
	 *
	 * @return list of access control levels
	 */
	public List<AccessControlLevel> getAccessConrolLevels();

	/**
	 * get regex for hidden folders.
	 *
	 * @return regex for hidden folders
	 */
	public List<String> getHiddenFolders();

	/**
	 * get regex for hidden files.
	 *
	 * @return regex for hidden files
	 */
	public List<String> getHiddenFiles();

	/**
	 * get double extensions configuration.
	 *
	 * @return configuration value.
	 */
	public boolean ckeckDoubleFileExtensions();

	/**
	 * flag to check if force ASCII.
	 *
	 * @return true if force ASCII.
	 */
	public boolean forceASCII();

	/**
	 * Checks if disallowed characters in file and folder names are turned on.
	 *
	 * @return disallowUnsafeCharacters
	 */
	public boolean isDisallowUnsafeCharacters();

	/**
	 * flag if check image size after resizing image.
	 *
	 * @return true if check.
	 */
	public boolean checkSizeAfterScaling();

	/**
	 * Gets uri encoding.
	 *
	 * @return uri encoding name.
	 */
	public String getUriEncoding();

	/**
	 * prepares configuration for single request.
	 *
	 * @param request request
	 */
	public void prepareConfigurationForRequest(HttpServletRequest request);

	/**
	 * gets a list of resource type in order like in configuration.
	 *
	 * @return list of ordered resource types.
	 */
	public List<String> getResourceTypesOrder();

	/**
	 * checks if reload config.
	 *
	 * @return true if reloading config is nessesery.
	 * @throws Exception when error occurs during reading config file
	 */
	public boolean checkIfReloadConfig() throws Exception;

	/**
	 * gets a list of plugins.
	 *
	 * @return list of plugins.
	 */
	public List<PluginInfo> getPlugins();

	/**
	 * gets events.
	 *
	 * @return events.
	 */
	public Events getEvents();

	/**
	 * gets param SecureImageUploads.
	 *
	 * @return true if is set
	 */
	public boolean getSecureImageUploads();

	/**
	 * gets html extensions.
	 *
	 * @return list of html extensions.
	 */
	public List<String> getHTMLExtensions();

	/**
	 * gets a list of default resource types.
	 *
	 * @return list of default resouce types
	 */
	public List<String> getDefaultResourceTypes();

	/**
	 * sets new value of thumbs URL.
	 *
	 * @param url new url
	 */
	public void setThumbsURL(String url);

	/**
	 * sets new value of thumbs folder.
	 *
	 * @param dir new thumbs folder.
	 */
	public void setThumbsDir(String dir);

	/**
	 * checks if connector is in debug mode.
	 *
	 * @return true if is
	 */
	public boolean isDebugMode();

	/**
	 * sets connector debug mode.
	 *
	 * @param mode debug mode flag
	 */
	public void setDebugMode(final boolean mode);

	/**
	 * gets UserFilePathBuilder implemetation from configuration.
	 *
	 * @return IUserFilePathBuilder implemetation
	 */
	public IBasePathBuilder getBasePathBuilder();
}
