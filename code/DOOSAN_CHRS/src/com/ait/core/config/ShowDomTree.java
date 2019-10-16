package com.ait.core.config;


import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class ShowDomTree {

    private ShowDomTree() {
    }
    
	public static void saveDocAsFile(Document doc, String fileName) {
		try {
			TransformerFactory tfFac = TransformerFactory.newInstance();
			// use null transformation
			Transformer tf = tfFac.newTransformer();

			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			tf.transform(new DOMSource(doc), new StreamResult(new FileWriter(fileName)));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static String returnDocAsString(Document doc) {
		
		StringWriter sw = new StringWriter();
		try {
			TransformerFactory tfFac = TransformerFactory.newInstance();
			// use null transformation
			Transformer tf = tfFac.newTransformer();
			
			tf.transform(new DOMSource(doc), new StreamResult(sw));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

}