package com.ait.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Configuration extends Observable {

	private XmlUtils utils;

	private Document doc;

	private static Configuration xc;

	private static String em2_home = null;

	private static String EM2_HOME_TAG = "#home";

	private static int EM2_HOME_TAG__SIZE = EM2_HOME_TAG.length();

	private final static String default_file_name = "em2.xml";

	private static LinkedHashMap configurationMap = new LinkedHashMap();

	private static char DELIM = '/';
	
	private static String root_node = "em2";
	
	private static String confFileLocation = null;

	//  separator000. 

	public static String getConfFileLocation(String filename) throws ConfigurationException {
		try {
			if (confFileLocation != null){
				if (confFileLocation.endsWith("\\") || confFileLocation.endsWith("/"))
					em2_home = confFileLocation + "WEB-INF/conf/";
				else
					em2_home = confFileLocation + "/WEB-INF/conf/";
			}
			else
				em2_home = "/WEB-INF/conf/";
			Logger.getLogger(Configuration.class).debug(em2_home+filename);
			File default_file = new File(em2_home+filename);
			if (!default_file.exists()) {
				throw new Exception("File not Exists, Check the Configuration "+filename+" file");
			}
			return default_file.getAbsolutePath();
		} catch (Exception e) {
            e.printStackTrace();
			Logger.getLogger(Configuration.class).debug("Check the laf.xml file location");
			throw new ConfigurationException("Check the em2.xml file location", e);
		}
	}
	
	public static void setConfFileLocation(String path){
		confFileLocation = path;
	}
	
	public static String getEm2Home() { 
		return System.getProperty("laf.home");		 
	}
	
	public static synchronized Configuration getInstance() throws ConfigurationException {
		try {
			if (xc == null) {
				xc = new Configuration(getConfFileLocation(default_file_name), false);
			}
			return xc;
		} catch (ConfigurationException e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on loading Configuration Instance");
			throw new ConfigurationException(
				"Error on loading Configuration Instance",
				e);
		}
        
	}

	private void init(Document doc) throws ConfigurationException {

		this.utils = XmlUtils.getImpl();
		this.doc = doc;

		configurationMap = null; // init  
		configurationMap = new LinkedHashMap();

		Node root = doc.getDocumentElement();
		// Check the existence of the root element
		if (root == null) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on initiating DOM : Check the root element of em2.xml document");
			throw new ConfigurationException(
				"Error on initiating DOM : Check the root element of laf.xml document");
		} else {

			createConfigurationMap(root);

			NodeList children = root.getChildNodes();
			String rootNodeNm = root.getNodeName();
			for (int inx = 0; inx < children.getLength(); inx++) {
				Node childNode = children.item(inx);
				if (!childNode.getNodeName().equals(root_node)
					&& childNode.getNodeType() == Node.ELEMENT_NODE) {

					String childNodeName = childNode.getNodeName();
					String childNodeAttrValue = childNode.getAttributes().item(0).getNodeValue();
					//					NodeList childList= childNode.getChildNodes();

					String fullPath =
						rootNodeNm
							+ DELIM
							+ childNodeName
							+ "<"
							+ childNodeAttrValue
							+ ">"
							+ DELIM
							+ "src";
					String srcPath = get(fullPath);

					fullPath =
						rootNodeNm
							+ DELIM
							+ childNodeName
							+ "<"
							+ childNodeAttrValue
							+ ">"
							+ DELIM
							+ "validate";
					String validate = get(fullPath);

					boolean validateBool = new Boolean(validate).booleanValue();
					Document subDoc = parsing(srcPath, validateBool);

					createConfigurationMap(
						subDoc.getDocumentElement(),
						rootNodeNm + DELIM + childNodeName + "<" + childNodeAttrValue + ">");

					NodeList nlst = subDoc.getDocumentElement().getChildNodes();
					int len = nlst.getLength();
					Node nd = null;
					for (int k = 0; k < len; k++) {
						nd = doc.importNode(nlst.item(k), true);
						children.item(inx).appendChild(nd);
					}
				}
			}
		}
	}

	private Configuration(String name, boolean validating) throws ConfigurationException {
		try {
			validating = false;
			doc = parsing(name, validating);

			init(doc);
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on parsing : Check the em2.xml");
			throw new ConfigurationException(
				"Error on parsing : Check the laf.xml",
				e);
		}
	}

	private static void createConfigurationMap(Node node) throws ConfigurationException {
		createConfigurationMap(node, node.getNodeName());
	}

	private static void createConfigurationMap(Node node, String header)
		throws ConfigurationException {
		try {
			short NODE_TYPE = node.getNodeType();

			if (NODE_TYPE == Node.ELEMENT_NODE) {
				NodeList children = node.getChildNodes();
				if (children != null) { 			 
					for (int i = 0, size = children.getLength(); i < size; i++) {
						Node childNode = children.item(i);
						String childNodeName = childNode.getNodeName();
						short CHILD_NODE_TYPE = childNode.getNodeType();
						String childNodeValue = childNode.getNodeValue();

						NamedNodeMap attrNodeList = childNode.getAttributes();
						if (attrNodeList != null && attrNodeList.getLength() > 0) {

							for (int attrListInx = 0, sizeInx = attrNodeList.getLength();
								attrListInx < sizeInx;
								attrListInx++) {
								createConfigurationMap(
									childNode,
									header
										+ DELIM
										+ childNodeName
										+ "<"
										+ attrNodeList.item(attrListInx).getNodeValue()
										+ ">");
							}
						} else {
							if (CHILD_NODE_TYPE == Node.TEXT_NODE
								&& compareWhiteSpace(childNodeValue)) {
								createConfigurationMap(childNode, header);
							} else {
								createConfigurationMap(childNode, header + DELIM + childNodeName);
							}

						}

					}
				}
			} else if (NODE_TYPE == Node.TEXT_NODE) {
				String mapValue = node.getNodeValue();
				if (mapValue != null && compareWhiteSpace(mapValue)) {
					mapValue = mapValue.trim();
					String mapKey = header;
					if (mapValue.startsWith(EM2_HOME_TAG)) {
						mapValue = em2_home + mapValue.substring(EM2_HOME_TAG__SIZE);
					}
					node.setNodeValue(mapValue);
					configurationMap.put(mapKey, mapValue);
				}

			}else if (NODE_TYPE == Node.CDATA_SECTION_NODE) {	
				String mapValue = node.getNodeValue();
				if (mapValue != null && compareWhiteSpace(mapValue)) {
					mapValue = mapValue.trim();
					String mapKey = header.substring(0,header.indexOf("#cdata")-1);
					if (mapValue.startsWith(EM2_HOME_TAG)) {
						mapValue = em2_home + mapValue.substring(EM2_HOME_TAG__SIZE);
					}
					node.setNodeValue(mapValue);
					configurationMap.put(mapKey, mapValue);
				}

			}
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on generating configurationMap");
			throw new ConfigurationException(
				"Error on generating configurationMap",
				e);
		}
	}

	private static boolean compareWhiteSpace(String str) {

		char[] charStr = str.toCharArray();

		for (int i = 0, size = charStr.length; i < size; i++) {
			if (!Character.isWhitespace(charStr[i]))
				return true; 
		}
		return false;

	}

	public synchronized void refresh() throws ConfigurationException {
		try {
			doc = parsing(getConfFileLocation(default_file_name), false);
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on refreshing the em2.xml DOM");

			throw new ConfigurationException(
				"Error on refreshing the em2.xml DOM ",
				e);
		}

		init(doc);
		setChanged();
		notifyObservers();
	}

	private Document parsing(String name, boolean validating) throws ConfigurationException {
		try {
			Document temp;

			File conf_file = new File(name);
			FileInputStream in = new FileInputStream(conf_file);

			if (conf_file == null || !conf_file.exists() || !conf_file.canRead()) {
				throw new ConfigurationException(
					"Error on parsing ",
					new IOException("Failed open the [" + name + "] File : "));
			}
			try {
				this.utils = XmlUtils.getImpl();
				temp = XmlUtils.parse(new java.io.BufferedInputStream(in), validating);
				return temp;
			} catch (ConfigurationException e) {
				Logger.getLogger("com.ait.core.config.Configuration").debug("parsing(String name, boolean validating)",e);
				throw new ConfigurationException("Error on parsing ", e);
			} finally {
				if (in != null)
					in.close();
			}

		} catch (FileNotFoundException e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("parsing(String name, boolean validating)",e);
			throw new ConfigurationException("Error on parsing ", e);
		} catch (IOException e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("parsing(String name, boolean validating)",e);
			throw new ConfigurationException("Error  on parsing ", e);
		}
	}

	public String get(String key) throws ConfigurationException {
		if (configurationMap == null) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on loading configurationMap");
			throw new ConfigurationException("Error on loading configurationMap");
		}
		if (key.charAt(0) == DELIM) {
			key = key.substring(1);
		}

		String value = (String) configurationMap.get(key);

		if (value == null) {
			return "";
		} else {
			return value;
		}
	}

	public String getString(String elemPath, String defaultValue) throws ConfigurationException {
		try {
			String value = get(elemPath);

			if (value.equals("")) {
				return defaultValue;
			} else {
				return value;
			}
		} catch (Exception e) {

			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of configurationMap");
			throw new ConfigurationException(
				"Error on getter method of configurationMap",
				e);
		}
	}

	public boolean getBoolean(String elemPath, boolean defaultValue)
		throws ConfigurationException {
		try {
			String value = get(elemPath);
			String temp = value.toUpperCase();
			if ("TRUE".equals(temp) || "FALSE".equals(temp)) {
				return new Boolean(value.trim()).booleanValue();
			} else {
				return defaultValue;
			}

		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of configurationMap");
			throw new ConfigurationException(
				"Error on getter method of configurationMap",
				e);
		}
	}

	public int getInt(String elemPath, int defaultValue) throws ConfigurationException {
		try {
			String value = get(elemPath);
			if (value.equals("")) {
				return defaultValue;
			} else {
				return new Integer(value.trim()).intValue();
			}
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of configurationMap");
			throw new ConfigurationException(
				"Error on getter method of configurationMap",
				e);
		}
	}

	public long getLong(String elemPath, long defaultValue) throws ConfigurationException {
		try {
			String value = get(elemPath);
			if (value.equals("")) {
				return defaultValue;
			} else {
				return new Long(value.trim()).longValue();
			}
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of configurationMap");
			throw new ConfigurationException(
				"Error on getter method of configurationMap",
				e);
		}
	}

	public Element getElement(String elemPath)
		throws ConfigurationException, IllegalArgumentException {
		try {
			NodeList list = utils.evalToNodeList(doc, elemPath);
			Node node = (list.getLength() > 0) ? list.item(0) : null;
			if (node instanceof Element)
				return (Element) node;
			else
				Logger.getLogger("com.ait.core.config.Configuration").debug
						("IllegalArguementException : "
							+ elemPath
							+ " isn't the path of an element");

			throw new ConfigurationException(
				"IllegalArguementException : " + elemPath + " isn't the path of an element",
				new IllegalArgumentException());
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of DOM",e);
			throw new ConfigurationException("Error on getter method of DOM", e);
		}
	}

	public int getElementCount(String parentPath, String elemName) throws ConfigurationException {
		try {
			String path = parentPath + DELIM + elemName;
			NodeList list = utils.evalToNodeList(doc, path);
			return list.getLength();
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of DOM");
			throw new ConfigurationException("Error on getter method of DOM", e);
		}
	}

	public Document getDom() {
		return doc;
	}

	public NodeList getNodeList(String elemPath) throws ConfigurationException {
		try {
			return utils.evalToNodeList(doc, elemPath);
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of DOM");
			throw new ConfigurationException("Error on getter method of DOM", e);
		}
	}

	public Node getNode(String elemPath) throws ConfigurationException {
		try {
			return utils.selectSingleNode(doc, elemPath);
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of DOM");
			throw new ConfigurationException("Error on getter method of DOM", e);
		}
	}

	public String getAttribute(String elemPath, String attrName) throws ConfigurationException {
		String path = elemPath + DELIM + '@' + attrName;
		try {
			return get(path);
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of DOM");
			throw new ConfigurationException("Error on getter method of DOM", e);
		}
	}

	public String[] getArray(String arrayPath, String arrName, String elemName)
		throws ConfigurationException {
		try {
			int length = 0;
			length = getElementCount(arrayPath, arrName);
			String array[] = new String[length];
			for (int i = 0, j = 1; i < length; i++, j++) {
				String path = arrayPath + DELIM + arrName + '[' + j + ']' + DELIM + elemName;
				array[i] = get(path);
			}
			return array;
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.Configuration").debug("Error on getter method of DOM");
			throw new ConfigurationException("Error on getter method of DOM", e);
		}
	}

	public void printConfDom(String gb) {
		if ((gb.trim()).equals("file"))
			ShowDomTree.saveDocAsFile(doc, em2_home + "/conf/dom.xml");
		else {
			System.out.println(ShowDomTree.returnDocAsString(doc));
		}
	}

	public String showConfDom() {
		return ShowDomTree.returnDocAsString(doc);
	}
	
	public static void main(String[] args){
		String s = (String)null;
		System.out.println(s);
		try{
			System.out.println("jndi="+Configuration.getInstance().getString("/configuration/em2/datasource-provider",""));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		System.getProperties().list(System.out);
	}
}
