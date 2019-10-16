package com.ait.core.config;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.xpath.XPathAPI;
import org.apache.xpath.objects.XObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlPathUtils extends XmlUtils {

	protected XObject eval(Document doc, String str) throws TransformerException {

		Node root = doc.getDocumentElement();
		return XPathAPI.eval(root, str);
	}

	public String evalToString(Document doc, String str) throws ConfigurationException {
		try {
			return eval(doc, str).str();

		} catch (TransformerException e) {
			Logger.getLogger("com.ait.core.config.XmlPathUtils").debug("Cannot get a data for " + str);
			throw new ConfigurationException("Cannot get a data for " + str, e);
		}
	}

	public NodeList evalToNodeList(Document doc, String str) throws ConfigurationException {
		try {
			return (NodeList) eval(doc, str).nodelist();

		} catch (TransformerException e) {
			Logger.getLogger("com.ait.core.config.XmlPathUtils").debug("Cannot get a data for " + str);
			throw new ConfigurationException("Cannot get a data for " + str, e);
		}
	}

	public Node selectSingleNode(Document doc, String str) throws ConfigurationException {
		try {
			return XPathAPI.selectSingleNode(doc, str);

		} catch (TransformerException e) {
			Logger.getLogger("com.ait.core.config.XmlPathUtils").debug("Cannot get a data for " + str);
			throw new ConfigurationException("Cannot get a data for " + str, e);
		}
	}
}
