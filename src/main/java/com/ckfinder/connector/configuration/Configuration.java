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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ckfinder.connector.ServletContextFactory;
import com.ckfinder.connector.data.AccessControlLevel;
import com.ckfinder.connector.data.PluginInfo;
import com.ckfinder.connector.data.PluginParam;
import com.ckfinder.connector.data.ResourceType;
import com.ckfinder.connector.utils.FileUtils;
import com.ckfinder.connector.utils.PathUtils;

/**
 * Loads configuration from XML.a
 */
public class Configuration implements IConfiguration {

	protected static final int MAX_QUALITY = 100;
	protected static final float MAX_QUALITY_FLOAT = 100f;
	private long lastCfgModificationDate;
	protected boolean enabled;
	protected String xmlFilePath;
	protected String baseDir;
	protected String baseURL;
	protected String licenseName;
	protected String licenseKey;
	protected Integer imgWidth;
	protected Integer imgHeight;
	protected float imgQuality;
	protected Map<String, ResourceType> types;
	protected ArrayList<String> typesOrder;
	protected boolean thumbsEnabled;
	protected String thumbsURL;
	protected String thumbsDir;
	protected String thumbsPath;
	protected boolean thumbsDirectAccess;
	protected Integer thumbsMaxHeight;
	protected Integer thumbsMaxWidth;
	protected float thumbsQuality;
	protected List<AccessControlLevel> accessControlLevels;
	protected List<String> hiddenFolders;
	protected List<String> hiddenFiles;
	protected boolean doubleExtensions;
	protected boolean forceASCII;
	protected boolean checkSizeAfterScaling;
	protected String uriEncoding;
	protected String userRoleSessionVar;
	protected List<PluginInfo> plugins;
	protected boolean secureImageUploads;
	protected List<String> htmlExtensions;
	protected List<String> defaultResourceTypes;
	protected IBasePathBuilder basePathBuilder;
	protected boolean disallowUnsafeCharacters;
	private boolean loading;
	private Events events;
	private boolean debug;
	protected ServletConfig servletConf;

	/**
	 * Constructor.
	 *
	 * @param servletConfig servlet config to get parameters from web-xml
	 */
	public Configuration(final ServletConfig servletConfig) {
		this.servletConf = servletConfig;
		this.xmlFilePath = servletConfig.getInitParameter("XMLConfig");
		this.plugins = new ArrayList<>();
		this.htmlExtensions = new ArrayList<>();
		this.hiddenFolders = new ArrayList<>();
		this.hiddenFiles = new ArrayList<>();
		this.defaultResourceTypes = new ArrayList<>();
	}

	/**
	 * clears all configuration values.
	 */
	private void clearConfiguration() {
		this.debug = false;
		this.enabled = false;
		this.baseDir = "";
		this.baseURL = "";
		this.licenseName = "";
		this.licenseKey = "";
		this.imgWidth = DEFAULT_IMG_WIDTH;
		this.imgHeight = DEFAULT_IMG_HEIGHT;
		this.imgQuality = DEFAULT_IMG_QUALITY;
		this.types = new HashMap<>();
		this.typesOrder = new ArrayList<>();
		this.thumbsEnabled = false;
		this.thumbsURL = "";
		this.thumbsDir = "";
		this.thumbsPath = "";
		this.thumbsQuality = DEFAULT_IMG_QUALITY;
		this.thumbsDirectAccess = false;
		this.thumbsMaxHeight = DEFAULT_THUMB_MAX_HEIGHT;
		this.thumbsMaxWidth = DEFAULT_THUMB_MAX_WIDTH;
		this.accessControlLevels = new ArrayList<>();
		this.hiddenFolders = new ArrayList<>();
		this.hiddenFiles = new ArrayList<>();
		this.doubleExtensions = false;
		this.forceASCII = false;
		this.checkSizeAfterScaling = false;
		this.uriEncoding = DEFAULT_URI_ENCODING;
		this.userRoleSessionVar = "";
		this.plugins = new ArrayList<>();
		this.secureImageUploads = false;
		this.htmlExtensions = new ArrayList<>();
		this.defaultResourceTypes = new ArrayList<>();
		this.events = new Events();
		this.basePathBuilder = null;
		this.disallowUnsafeCharacters = false;
	}

	/**
	 * initialize configuration from XML config file.
	 *
	 * @throws Exception when error occurs.
	 */
	public void init() throws Exception {
		clearConfiguration();
		this.loading = true;
		File file = new File(getFullConfigPath());
		this.lastCfgModificationDate = file.lastModified();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.normalize();
		Node node = doc.getFirstChild();
		if (node != null) {
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node childNode = nodeList.item(i);
				if ("enabled".equals(childNode.getNodeName())) {
					this.enabled = Boolean.valueOf(childNode.getNodeName().trim());
				}
				if ("baseDir".equals(childNode.getNodeName())) {
					this.baseDir = childNode.getNodeName().trim();
					this.baseDir = PathUtils.escape(this.baseDir);
					this.baseDir = PathUtils.addSlashToEnd(this.baseDir);
				}
				if ("baseURL".equals(childNode.getNodeName())) {
					this.baseURL = childNode.getNodeName().trim();
					this.baseURL = PathUtils.escape(baseURL);
					this.baseURL = PathUtils.addSlashToEnd(this.baseURL);
				}
				if ("licenseName".equals(childNode.getNodeName())) {
					this.licenseName = childNode.getNodeName().trim();
				}
				if ("licenseKey".equals(childNode.getNodeName())) {
					this.licenseKey = childNode.getNodeName().trim();
				}
				if ("imgWidth".equals(childNode.getNodeName())) {
					String width = childNode.getNodeName().trim();
					width = width.replaceAll("//D", "");
					try {
						this.imgWidth = Integer.valueOf(width);
					} catch (NumberFormatException e) {
						this.imgWidth = null;
					}
				}
				if ("imgQuality".equals(childNode.getNodeName())) {
					String quality = childNode.getNodeName().trim();
					quality = quality.replaceAll("//D", "");
					this.imgQuality = adjustQuality(quality);
				}

				if ("imgHeight".equals(childNode.getNodeName())) {
					String height = childNode.getNodeName().trim();
					height = height.replaceAll("//D", "");
					try {
						this.imgHeight = Integer.valueOf(height);
					} catch (NumberFormatException e) {
						this.imgHeight = null;
					}
				}
				if ("thumbs".equals(childNode.getNodeName())) {
					setThumbs(childNode.getChildNodes());
				}
				if ("accessControls".equals(childNode.getNodeName())) {
					setACLs(childNode.getChildNodes());
				}
				if ("hideFolders".equals(childNode.getNodeName())) {
					setHiddenFolders(childNode.getChildNodes());
				}
				if ("hideFiles".equals(childNode.getNodeName())) {
					setHiddenFiles(childNode.getChildNodes());
				}
				if ("checkDoubleExtension".equals(childNode.getNodeName())) {
					this.doubleExtensions = Boolean.valueOf(childNode.getNodeName().trim());
				}
				if ("disallowUnsafeCharacters".equals(childNode.getNodeName())) {
					this.disallowUnsafeCharacters = Boolean.valueOf(childNode.getNodeName().trim());
				}
				if ("forceASCII".equals(childNode.getNodeName())) {
					this.forceASCII = Boolean.valueOf(childNode.getNodeName().trim());
				}
				if ("checkSizeAfterScaling".equals(childNode.getNodeName())) {
					this.checkSizeAfterScaling = Boolean.valueOf(childNode.getNodeName().trim());
				}
				if ("htmlExtensions".equals(childNode.getNodeName())) {
					String htmlExt = childNode.getNodeName();
					Scanner scanner = new Scanner(htmlExt).useDelimiter(",");
					while (scanner.hasNext()) {
						String val = scanner.next();
						if (val != null && !"".equals(val)) {
							htmlExtensions.add(val.trim().toLowerCase());
						}

					}
				}

				if ("secureImageUploads".equals(childNode.getNodeName())) {
					this.secureImageUploads = Boolean.valueOf(childNode.getNodeName().trim());
				}

				if ("uriEncoding".equals(childNode.getNodeName())) {
					this.uriEncoding = childNode.getNodeName().trim();
				}
				if ("userRoleSessionVar".equals(childNode.getNodeName())) {
					this.userRoleSessionVar = childNode.getNodeName().trim();
				}
				if ("defaultResourceTypes".equals(childNode.getNodeName())) {
					String value = childNode.getNodeName().trim();
					Scanner sc = new Scanner(value).useDelimiter(",");
					while (sc.hasNext()) {
						this.defaultResourceTypes.add(sc.next());
					}
				}
				if ("plugins".equals(childNode.getNodeName())) {
					setPlugins(childNode);
				}
				if ("basePathBuilderImpl".equals(childNode.getNodeName())) {
					setBasePathImpl(childNode.getNodeName().trim());
				}
			}
		}
		setTypes(doc);
		this.events = new Events();
		registerEventHandlers();
		this.loading = false;
	}

	private String getFullConfigPath() throws Exception {
		File file = new File(ServletContextFactory.getServletContext().getRealPath(xmlFilePath));
		if (file.exists() && file.isFile()) {
			return file.getAbsolutePath();
		} else {
			return xmlFilePath;
		}
	}

	/**
	 * set user path builder from configuration.
	 *
	 * @param value userPathBuilderImpl configuration value
	 */
	private void setBasePathImpl(final String value) {
		try {
			@SuppressWarnings("unchecked")
			Class<IBasePathBuilder> clazz = (Class<IBasePathBuilder>) Class.forName(value);
			this.basePathBuilder = clazz.newInstance();
		} catch (Exception e) {
			this.basePathBuilder = new ConfigurationPathBuilder();
		}
	}

	/**
	 * adjust image quality.
	 *
	 * @param imgQuality image quality param
	 * @return adjusted image quality
	 */
	private float adjustQuality(final String imgQuality) {
		float helper;
		try {
			helper = Math.abs(Float.parseFloat(imgQuality));
		} catch (NumberFormatException e) {
			return DEFAULT_IMG_QUALITY;
		}
		if (helper == 0 || helper == 1) {
			return helper;
		} else if (helper > 0 && helper < 1) {
			helper = (Math.round(helper * MAX_QUALITY_FLOAT) / MAX_QUALITY_FLOAT);
		} else if (helper > 1 && helper <= MAX_QUALITY) {
			helper = (Math.round(helper) / MAX_QUALITY_FLOAT);
		} else {
			helper = DEFAULT_IMG_QUALITY;
		}

		return helper;
	}

	/**
	 * register event handlers from all plugins.
	 */
	protected void registerEventHandlers() {
		for (PluginInfo item : this.plugins) {
			try {
				@SuppressWarnings("unchecked")
				Class<Plugin> clazz = (Class<Plugin>) Class.forName(item.getClassName());
				Plugin plugin = (Plugin) clazz.newInstance();
				plugin.setPluginInfo(item);
				plugin.registerEventHandlers(this.events);
				item.setEnabled(true);
			} catch (ClassCastException e) {
				item.setEnabled(false);
			} catch (ClassNotFoundException e) {
				item.setEnabled(false);
			} catch (IllegalAccessException e) {
				item.setEnabled(false);
			} catch (InstantiationException e) {
				item.setEnabled(false);
			}

		}

	}

	/**
	 * set hidden files list from XML configuration.
	 *
	 * @param childNodes list of files nodes.
	 */
	private void setHiddenFiles(final NodeList childNodes) {
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if ("file".equals(node.getNodeName())) {
				String val = node.getNodeName();
				if (!"".equals(val)) {
					this.hiddenFiles.add(val.trim());
				}
			}
		}

	}

	/**
	 * set hidden folders list from XML configuration.
	 *
	 * @param childNodes list of folder nodes.
	 */
	private void setHiddenFolders(final NodeList childNodes) {
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if ("folder".equals(node.getNodeName())) {
				String val = node.getNodeName();
				if (!"".equals(val)) {
					this.hiddenFolders.add(val.trim());
				}
			}
		}

	}

	/**
	 * set ACL configuration into list of access controll levels.
	 *
	 * @param childNodes nodes with ACL configuration.
	 */
	private void setACLs(final NodeList childNodes) {
		this.accessControlLevels = new ArrayList<>();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if ("accessControl".equals(childNode.getNodeName())) {
				AccessControlLevel acl = new AccessControlLevel();
				acl = getACLFromNode(childNode);
				if (acl != null) {
					this.accessControlLevels.add(acl);
				}
			}
		}

	}

	/**
	 * gets acl configuration from XML node.
	 *
	 * @param childNode XML access control node.
	 * @return access controll level obiect
	 */
	private AccessControlLevel getACLFromNode(final Node childNode) {
		AccessControlLevel acl = new AccessControlLevel();
		for (int i = 0; i < childNode.getChildNodes().getLength(); i++) {
			Node childChildNode = childNode.getChildNodes().item(i);
			if ("role".equals(childChildNode.getNodeName())) {
				acl.setRole(childChildNode.getNodeName().trim());
			}
			if ("resourceType".equals(childChildNode.getNodeName())) {
				acl.setResourceType(childChildNode.getNodeName().trim());
			}
			if ("folder".equals(childChildNode.getNodeName())) {
				acl.setFolder(childChildNode.getNodeName().trim());
			}
			if ("folderView".equals(childChildNode.getNodeName())) {
				acl.setFolderView(Boolean.valueOf(childChildNode.getNodeName().trim()));
			}
			if ("folderCreate".equals(childChildNode.getNodeName())) {
				acl.setFolderCreate(Boolean.valueOf(childChildNode.getNodeName().trim()));
			}
			if ("folderRename".equals(childChildNode.getNodeName())) {
				acl.setFolderRename(Boolean.valueOf(childChildNode.getNodeName().trim()));
			}
			if ("folderDelete".equals(childChildNode.getNodeName())) {
				acl.setFolderDelete(Boolean.valueOf(childChildNode.getNodeName().trim()));
			}
			if ("fileView".equals(childChildNode.getNodeName())) {
				acl.setFileView(Boolean.valueOf(childChildNode.getNodeName().trim()));
			}
			if ("fileUpload".equals(childChildNode.getNodeName())) {
				acl.setFileUpload(Boolean.valueOf(childChildNode.getNodeName().trim()));
			}
			if ("fileRename".equals(childChildNode.getNodeName())) {
				acl.setFileRename(Boolean.valueOf(childChildNode.getNodeName().trim()));
			}
			if ("fileDelete".equals(childChildNode.getNodeName())) {
				acl.setFileDelete(Boolean.valueOf(childChildNode.getNodeName().trim()));
			}
		}

		if (acl.getResourceType() == null
				|| acl.getRole() == null) {
			return null;
		}

		if (acl.getFolder() == null || "".equals(acl.getFolder())) {
			acl.setFolder("/");
		}
		return acl;
	}

	/**
	 * creates thumb configuration from XML.
	 *
	 * @param childNodes list of thumb XML nodes
	 */
	private void setThumbs(final NodeList childNodes) {
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if ("enabled".equals(childNode.getNodeName())) {
				this.thumbsEnabled = Boolean.valueOf(childNode.getNodeName().trim());
			}
			if ("url".equals(childNode.getNodeName())) {
				this.thumbsURL = childNode.getNodeName().trim();
			}
			if ("directory".equals(childNode.getNodeName())) {
				this.thumbsDir = childNode.getNodeName().trim();
			}
			if ("directAccess".equals(childNode.getNodeName())) {
				this.thumbsDirectAccess = Boolean.valueOf(childNode.getNodeName().trim());
			}
			if ("maxHeight".equals(childNode.getNodeName())) {
				String width = childNode.getNodeName().trim();
				width = width.replaceAll("//D", "");
				try {
					this.thumbsMaxHeight = Integer.valueOf(width);
				} catch (NumberFormatException e) {
					this.thumbsMaxHeight = null;
				}
			}
			if ("maxWidth".equals(childNode.getNodeName())) {
				String width = childNode.getNodeName().trim();
				width = width.replaceAll("//D", "");
				try {
					this.thumbsMaxWidth = Integer.valueOf(width);
				} catch (NumberFormatException e) {
					this.thumbsMaxWidth = null;
				}
			}
			if ("quality".equals(childNode.getNodeName())) {
				String quality = childNode.getNodeName().trim();
				quality = quality.replaceAll("//D", "");
				this.thumbsQuality = adjustQuality(quality);
			}
		}

	}

	/**
	 * creates types configuration from XML configuration.
	 *
	 * @param doc XML document.
	 */
	private void setTypes(final Document doc) {
		types = new HashMap<>();
		typesOrder = new ArrayList<>();
		NodeList list = doc.getElementsByTagName("type");

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);
			String name = element.getAttribute("name");
			if (name != null && !name.equals("")) {
				ResourceType resourceType = createTypeFromXml(
						name, element.getChildNodes());
				types.put(name, resourceType);
				typesOrder.add(resourceType.getName());
			}

		}
	}

	/**
	 * Creates type configuration from XML.
	 *
	 * @param typeName name of type.
	 * @param childNodes type XML nodes.
	 * @return resource type
	 */
	private ResourceType createTypeFromXml(final String typeName,
			final NodeList childNodes) {
		ResourceType resourceType = new ResourceType(typeName);
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if ("url".equals(childNode.getNodeName())) {
				String url = childNode.getNodeName().trim();
				resourceType.setUrl(url);
			}
			if ("directory".equals(childNode.getNodeName())) {
				String url = childNode.getNodeName().trim();
				resourceType.setPath(url);
			}
			if ("maxSize".equals(childNode.getNodeName())) {
				resourceType.setMaxSize(childNode.getNodeName().trim());
			}
			if ("allowedExtensions".equals(childNode.getNodeName())) {
				resourceType.setAllowedExtensions(childNode.getNodeName().trim());
			}
			if ("deniedExtensions".equals(childNode.getNodeName())) {
				resourceType.setDeniedExtensions(childNode.getNodeName().trim());
			}
		}
		return resourceType;
	}

	/**
	 * method to check if user is authentificated.
	 *
	 * @param request current request
	 * @return true if is
	 */
	public boolean checkAuthentication(final HttpServletRequest request) {
		return DEFAULT_CHECKAUTHENTICATION;
	}

	/**
	 * checks if connector is enabled.
	 *
	 * @return if connector is enabled
	 */
	public boolean enabled() {
		return this.enabled && !this.loading;
	}

	/**
	 * Checks if disallowed characters in file and folder names are turned on.
	 *
	 * @return disallowUnsafeCharacters
	 */
	public boolean isDisallowUnsafeCharacters() {
		return this.disallowUnsafeCharacters;
	}

	/**
	 * gets location of ckfinder in app. For ex. /ckfinder/.
	 *
	 * @return base directory
	 */
	public String getBaseDir() {
		return this.baseDir;
	}

	/**
	 * returns path to ckfinder with app name for ex. /webapp/ckfinder/.
	 *
	 * @return base url
	 */
	public String getBaseURL() {
		return this.baseURL;
	}

	/**
	 * gets image max height.
	 *
	 * @return max image height
	 */
	public Integer getImgHeight() {
		if (this.imgHeight != null) {
			return this.imgHeight;
		} else {
			return DEFAULT_IMG_HEIGHT;
		}
	}

	/**
	 * gets image max width.
	 *
	 * @return max image height
	 */
	public Integer getImgWidth() {
		if (this.imgWidth != null) {
			return this.imgWidth;
		} else {
			return DEFAULT_IMG_WIDTH;
		}
	}

	/**
	 * gets image quality.
	 *
	 * @return image quality
	 */
	public float getImgQuality() {
		return this.imgQuality;
	}

	/**
	 * returns license key.
	 *
	 * @return license key
	 */
	public String getLicenseKey() {
		return this.licenseKey;
	}

	/**
	 * returns license name.
	 *
	 * @return license name.
	 */
	public String getLicenseName() {
		return this.licenseName;
	}

	/**
	 * gets resources map types with names as map keys.
	 *
	 * @return resources map
	 */
	public Map<String, ResourceType> getTypes() {
		return this.types;
	}

	/**
	 * checks if thumbs are accessed direct.
	 *
	 * @return true if thumbs can be accessed directly
	 */
	public boolean getThumbsDirectAccess() {
		return this.thumbsDirectAccess;
	}

	/**
	 * gets max height of thumb.
	 *
	 * @return max height of thumb
	 */
	public int getMaxThumbHeight() {
		if (this.thumbsMaxHeight != null) {
			return this.thumbsMaxHeight;
		} else {
			return DEFAULT_THUMB_MAX_HEIGHT;
		}
	}

	/**
	 * gets max width of thumb.
	 *
	 * @return max width of thumb
	 */
	public int getMaxThumbWidth() {
		if (this.thumbsMaxWidth != null) {
			return this.thumbsMaxWidth;
		} else {
			return DEFAULT_THUMB_MAX_WIDTH;
		}
	}

	/**
	 * check if thums are enabled.
	 *
	 * @return true if thums are enabled
	 */
	public boolean getThumbsEnabled() {
		return this.thumbsEnabled;
	}

	/**
	 * gets url to thumbs dir(path from baseUrl).
	 *
	 * @return thumbs url
	 */
	public String getThumbsURL() {
		return this.thumbsURL;
	}

	/**
	 * gets path to thumbs directory.
	 *
	 * @return thumbs directory
	 */
	public String getThumbsDir() {
		return this.thumbsDir;
	}

	/**
	 * gets path to thumbs directory.
	 *
	 * @return thumbs directory
	 */
	public String getThumbsPath() {
		return this.thumbsPath;
	}

	/**
	 * gets thumbs quality.
	 *
	 * @return thumbs quality
	 */
	public float getThumbsQuality() {
		return this.thumbsQuality;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setThumbsPath(final String directory) {
		this.thumbsPath = directory;
	}

	/**
	 * gets list of access control levels.
	 *
	 * @return list of access control levels
	 */
	public List<AccessControlLevel> getAccessConrolLevels() {
		return this.accessControlLevels;
	}

	/**
	 * gets regex for hidden folders.
	 *
	 * @return regex for hidden folders
	 */
	public List<String> getHiddenFolders() {
		return this.hiddenFolders;
	}

	/**
	 * gets regex for hidden files.
	 *
	 * @return regex for hidden files
	 */
	public List<String> getHiddenFiles() {
		return this.hiddenFiles;
	}

	/**
	 * gets double extensions configuration.
	 *
	 * @return configuration value.
	 */
	public boolean ckeckDoubleFileExtensions() {
		return this.doubleExtensions;
	}

	/**
	 * flag to check if force ASCII.
	 *
	 * @return true if force ASCII.
	 */
	public boolean forceASCII() {
		return this.forceASCII;
	}

	/**
	 * flag if check image size after resizing image.
	 *
	 * @return true if check.
	 */
	public boolean checkSizeAfterScaling() {
		return this.checkSizeAfterScaling;
	}

	/**
	 * Gets uri encoding.
	 *
	 * @return uri encoding name.
	 */
	public String getUriEncoding() {
		if (this.uriEncoding == null
				|| this.uriEncoding.equals("")) {
			return DEFAULT_URI_ENCODING;
		}
		return this.uriEncoding;
	}

	/**
	 * gets user role name sets in config.
	 *
	 * @return role name
	 */
	public String getUserRoleName() {
		return this.userRoleSessionVar;
	}

	/**
	 * gets a list of resource type in order like in configuration.
	 *
	 * @return list of ordered resource types.
	 */
	public List<String> getResourceTypesOrder() {
		return this.typesOrder;
	}

	/**
	 * gets list of available plugins.
	 *
	 * @return list of plugins
	 */
	public List<PluginInfo> getPlugins() {
		return this.plugins;
	}

	/**
	 * gets secure image uploads option from configuration.
	 *
	 * @return secure image uploads option from configuration.
	 */
	public boolean getSecureImageUploads() {
		return Boolean.valueOf(this.secureImageUploads);
	}

	/**
	 * gets HTML extensions from configuration.
	 *
	 * @return HTML extensions from configuration.
	 */
	public List<String> getHTMLExtensions() {
		return this.htmlExtensions;
	}

	/**
	 * gets events.
	 *
	 * @return event
	 */
	public Events getEvents() {
		return this.events;
	}

	/**
	 * gets default resource types from configuration.
	 *
	 * @return default resource types from configuration.
	 */
	public List<String> getDefaultResourceTypes() {
		return this.defaultResourceTypes;
	}

	/**
	 * check if debug mode is enabled.
	 *
	 * @return true if is debug mode
	 */
	public boolean isDebugMode() {
		return this.debug;
	}

	/**
	 * gets path builder for baseDir and baseURL.
	 *
	 * @return path builder.
	 */
	public IBasePathBuilder getBasePathBuilder() {
		if (this.basePathBuilder == null) {
			this.basePathBuilder = new ConfigurationPathBuilder();
		}
		return this.basePathBuilder;

	}

	/**
	 * checks if reload config. Reloads it when date of file modifiation is
	 * other then date of last config initialization.
	 *
	 * @return true if reloading config is nessesery.
	 * @throws Exception when error occurs during reading config file
	 */
	public boolean checkIfReloadConfig() throws Exception {
		File cfgFile;
		try {
			cfgFile = new File(FileUtils.getFullPath(xmlFilePath));
		} catch (Exception e) {
			if (this.debug) {
				throw e;
			}
			return false;
		}
		return (cfgFile.lastModified() > this.lastCfgModificationDate);
	}

	/**
	 * prepares configuration for single request. Empty method. It should be
	 * overrided if needed.
	 *
	 * @param request request
	 */
	public void prepareConfigurationForRequest(final HttpServletRequest request) {
	}

	/**
	 * sets plugins from XML configuration.
	 *
	 * @param childNode childs of plugins XML node.
	 */
	private void setPlugins(final Node childNode) {
		NodeList nodeList = childNode.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childChildNode = nodeList.item(i);
			if (childChildNode.getNodeName().equals("plugin")) {
				this.plugins.add(createPluginFromNode(childChildNode));
			}
		}

	}

	/**
	 * creates plugin data from configuration.
	 *
	 * @param element XML plugin node.
	 * @return PluginInfo data
	 */
	private PluginInfo createPluginFromNode(final Node element) {
		PluginInfo info = new PluginInfo();
		NodeList list = element.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node childElem = (Node) list.item(i);
			if ("name".equals(childElem.getNodeName())) {
				info.setName(childElem.getNodeName().trim());
			}
			if ("class".equals(childElem.getNodeName())) {
				info.setClassName(childElem.getNodeName().trim());
			}
			if ("params".equals(childElem.getNodeName())) {
				NodeList list2 = childElem.getChildNodes();
				if (list.getLength() > 0) {
					info.setParams(new ArrayList<PluginParam>());
				}
				for (int j = 0; j < list2.getLength(); j++) {
					Node node = list2.item(j);
					if ("param".equals(node.getNodeName())) {
						NamedNodeMap map = node.getAttributes();
						PluginParam pp = new PluginParam();
						for (int k = 0; k < map.getLength(); k++) {
							if ("name".equals(map.item(k).getNodeName())) {
								pp.setName(map.item(k).getNodeName().trim());
							}
							if ("value".equals(map.item(k).getNodeName())) {
								pp.setValue(map.item(k).getNodeName().trim());
							}
						}
						info.getParams().add(pp);
					}
				}
			}
		}
		return info;
	}

	/**
	 * sets thums URL - used in ConfigurationFacotry.
	 *
	 * @param url current thumbs url
	 */
	public void setThumbsURL(final String url) {
		this.thumbsURL = url;
	}

	/**
	 * sets thums dir - used in ConfigurationFacotry.
	 *
	 * @param dir current thumbs dir
	 */
	public void setThumbsDir(final String dir) {
		this.thumbsDir = dir;
	}

	/**
	 * sets debug mode.
	 *
	 * @param mode current debug mode
	 */
	public final void setDebugMode(final boolean mode) {
		this.debug = mode;
	}

	/**
	 * clones currnet instance of configuratuion and copy it's all fields.
	 *
	 * @return cloned configuration
	 */
	public final IConfiguration cloneConfiguration() {
		Configuration configuration = createConfigurationInstance();
		copyConfFields(configuration);
		return configuration;
	}

	/**
	 * creates current configuration class instance. In every subclass this
	 * method should be override and return new instnce of it's class.
	 *
	 * @return new configuration instance
	 */
	protected Configuration createConfigurationInstance() {
		return new Configuration(this.servletConf);
	}

	/**
	 *
	 * @param configuration destenation configuration
	 */
	protected void copyConfFields(final Configuration configuration) {
		configuration.loading = this.loading;
		configuration.xmlFilePath = this.xmlFilePath;
		configuration.debug = this.debug;
		configuration.lastCfgModificationDate = this.lastCfgModificationDate;
		configuration.enabled = this.enabled;
		configuration.xmlFilePath = this.xmlFilePath;
		configuration.baseDir = this.baseDir;
		configuration.baseURL = this.baseURL;
		configuration.licenseName = this.licenseName;
		configuration.licenseKey = this.licenseKey;
		configuration.imgWidth = this.imgWidth;
		configuration.imgHeight = this.imgHeight;
		configuration.imgQuality = this.imgQuality;
		configuration.thumbsEnabled = this.thumbsEnabled;
		configuration.thumbsURL = this.thumbsURL;
		configuration.thumbsDir = this.thumbsDir;
		configuration.thumbsDirectAccess = this.thumbsDirectAccess;
		configuration.thumbsMaxHeight = this.thumbsMaxHeight;
		configuration.thumbsMaxWidth = this.thumbsMaxWidth;
		configuration.thumbsQuality = this.thumbsQuality;
		configuration.doubleExtensions = this.doubleExtensions;
		configuration.forceASCII = this.forceASCII;
		configuration.disallowUnsafeCharacters = this.disallowUnsafeCharacters;
		configuration.checkSizeAfterScaling = this.checkSizeAfterScaling;
		configuration.secureImageUploads = this.secureImageUploads;
		configuration.uriEncoding = this.uriEncoding;
		configuration.userRoleSessionVar = this.userRoleSessionVar;
		configuration.events = this.events;
		configuration.basePathBuilder = this.basePathBuilder;

		configuration.htmlExtensions = new ArrayList<>();
		configuration.htmlExtensions.addAll(this.htmlExtensions);
		configuration.hiddenFolders = new ArrayList<>();
		configuration.hiddenFiles = new ArrayList<>();
		configuration.hiddenFiles.addAll(this.hiddenFiles);
		configuration.hiddenFolders.addAll(this.hiddenFolders);
		configuration.typesOrder = new ArrayList<>();
		configuration.typesOrder.addAll(this.typesOrder);
		configuration.defaultResourceTypes = new ArrayList<>();
		configuration.defaultResourceTypes.addAll(this.defaultResourceTypes);
		configuration.types = new HashMap<>();
		configuration.accessControlLevels = new ArrayList<>();
		configuration.plugins = new ArrayList<>();
		copyTypes(configuration.types);
		copyACls(configuration.accessControlLevels);
		copyPlugins(configuration.plugins);
	}

	/**
	 * copy plugins for new configuration.
	 *
	 * @param plugins2 new configuration plugins list.
	 */
	private void copyPlugins(final List<PluginInfo> plugins2) {
		for (PluginInfo pluginInfo : this.plugins) {
			plugins2.add(new PluginInfo(pluginInfo));
		}
	}

	/**
	 * copy ACLs for new configuration.
	 *
	 * @param accessControlLevels2 new configuration ACLs list.
	 */
	private void copyACls(final List<AccessControlLevel> accessControlLevels2) {
		for (AccessControlLevel acl : this.accessControlLevels) {
			accessControlLevels2.add(new AccessControlLevel(acl));
		}
	}

	/**
	 * copy resource types for new configuration.
	 *
	 * @param types2 new configuration resource types list.
	 */
	private void copyTypes(final Map<String, ResourceType> types2) {
		for (String name : this.typesOrder) {
			types2.put(name, new ResourceType(this.types.get(name)));
		}

	}
}
