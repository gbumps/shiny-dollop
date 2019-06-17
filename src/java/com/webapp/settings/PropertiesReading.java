/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.settings;

import java.io.File;
import java.io.Serializable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author stephen
 */
public class PropertiesReading implements Serializable {
	
	private String configFilePath;
	private Document doc;
	
	public PropertiesReading(String configFilePath) {
		this.configFilePath = configFilePath;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		File f = new File(configFilePath); 
		this.doc = db.parse(f);
	}
	
	public String getConfigFilePath() {
		return configFilePath;
	}

	public void setConfigFilePath(String configFilePath) {
		this.configFilePath = configFilePath;
	}

	public String getStartElement() {
		Element node = (Element) doc.getElementsByTagName(Constants.XML_CONFIG[0]);
		return node.getTextContent();
	}

	public String getEndElement() {
		Element node = (Element) doc.getElementsByTagName(Constants.XML_CONFIG[1]);
		return node.getTextContent();
	}

	public String getStartDetailCrawl() {
		Element node = (Element) doc.getElementsByTagName(Constants.XML_CONFIG[2]);
		return node.getTextContent();
	}

	public String getEndDetailCrawl() {
		Element node = (Element) doc.getElementsByTagName(Constants.XML_CONFIG[3]);
		return node.getTextContent();
	}
	
	public Integer getPages() {
		Element node = (Element) doc.getElementsByTagName(Constants.XML_CONFIG[4]);
		return Integer.parseInt(node.getTextContent());
	}
	
	public String getBoyPage() {
		Element node = (Element) doc.getElementsByTagName(Constants.XML_CONFIG[5]);
		return node.getTextContent();
	}
	
	public String getGirlPage() {
		Element node = (Element) doc.getElementsByTagName(Constants.XML_CONFIG[6]);
		return node.getTextContent();
	}
	
} 
