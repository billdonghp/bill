package com.ait.core.config;

import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.xml.sax.EntityResolver;
//import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class XmlUtils {

	protected static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	protected static DocumentBuilderFactory vfactory = DocumentBuilderFactory.newInstance();
	static {
		factory.setValidating(false);
		vfactory.setValidating(true);
	}

	public static DocumentBuilder createBuilder(boolean validating)
		throws ConfigurationException {
		try {
			DocumentBuilder builder = null;

			if (validating)
				builder = vfactory.newDocumentBuilder();
			else
				builder = factory.newDocumentBuilder();

			/*builder.setEntityResolver(new EntityResolver() {

				public InputSource resolveEntity(String publicID, String systemID) {

					publicID = System.getProperty("laf.home") + "/conf/" + publicID;
					systemID = System.getProperty("laf.home") + "/conf/" + systemID;

					if (publicID != null && publicID.endsWith(".dtd")) {
						try {
							FileInputStream in = new FileInputStream(publicID);
							if (in != null) {
								return new InputSource(in);
							}
						} catch (FileNotFoundException e) {
							throw new InternalError(e.getMessage());
						}
					}
					return null;
				}
			});*/
			return builder;
		} catch (InternalError e) {
			Logger.getLogger("com.ait.core.config.XmlUtils").debug("Error caused by InternalError on parsing",e);
			throw new ConfigurationException("Error caused by InternalError on parsing",e);
		} catch (ParserConfigurationException e) {
			Logger.getLogger("com.ait.core.config.XmlUtils").debug("createBuilder(boolean validating)",e);
			throw new ConfigurationException("Error caused by ParserConfigurationException on parsing",e);
		}
	}

	//* DOCUMENT UTILS
	public static Document createEmptyDocument() throws Exception {
		try {
			return createBuilder(false).newDocument();
		} catch (Exception e) {
			throw e;
		}

	}

	public static Document parse(File file, boolean validating) throws ConfigurationException {
		try {
			return createBuilder(validating).parse(file);
		} catch (IOException e) {
			Logger.getLogger("com.ait.core.config.XmlUtils").debug("parse(File file, boolean validating)",e);
			throw new ConfigurationException("Error caused by IOException on parsing",e);
		} catch (SAXException e) {
			Logger.getLogger("com.ait.core.config.XmlUtils").debug("parse(File file, boolean validating)",e);
			throw new ConfigurationException(
				"Error caused by SAXException on parsing",
				e);
		}
	}

	public static Document parse(InputStream in, boolean validating)
		throws ConfigurationException {
		try {
			return createBuilder(validating).parse(in);
		} catch (IOException e) {
			Logger.getLogger("com.ait.core.config.XmlUtils").debug("parse(InputStream in, boolean validating)",e);
			throw new ConfigurationException(
				"Error caused by IOException on parsing",
				e);

		} catch (SAXException e) {
			Logger.getLogger("com.ait.core.config.XmlUtils").debug("parse(InputStream in, boolean validating)",e);
			throw new ConfigurationException(
				"Error caused by SAXException on parsing",
				e);
		}
	}

	//* XPATH UTILS
	public abstract String evalToString(Document doc, String str) throws ConfigurationException;

	public abstract NodeList evalToNodeList(Document doc, String str)
		throws ConfigurationException;

	public abstract Node selectSingleNode(Document doc, String str) throws ConfigurationException;

	//* CLASS LOADING
	protected static Class utilsClass = null;

	static {

		// Load the class
		String className = "com.ait.core.config.XmlPathUtils";

		try {
			//utilsClass = Class.forName(className);		
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			utilsClass = classLoader.loadClass(className);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//* INSTANCE CREATION
	public static XmlUtils getImpl() throws ConfigurationException {

		try {
			if (utilsClass == null)
				throw new InternalError("Don't have a XmlUtils implementation");

			// Return a new instance of the utils class
			return (XmlUtils) utilsClass.newInstance();
		} catch (InternalError e) {
			Logger.getLogger("com.ait.core.config.XmlUtils").debug("getImpl()",e);
			throw new ConfigurationException("XmlUtils class exists", e);
		} catch (Exception e) {
			Logger.getLogger("com.ait.core.config.XmlUtils").debug("getImpl()",e);
			throw new ConfigurationException(
				"Couldn't instantiate the XmlUtils implementation",
				e);
		}
	}
}
