/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.settings;

import com.webapp.crawler.DomParser;
import java.io.File;
import java.io.Serializable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author stephen
 */
public class PropertiesReading implements Serializable {
	
	private String configFilePath;
	private NodeList nodes;
	private Document doc;
	
	
	public PropertiesReading(String configFilePath) {
		this.configFilePath = configFilePath;
	}

	public NodeList getNodes() {
		return nodes;
	}

	public void setNodes() throws Exception {
		doc = DomParser.returnDocument(configFilePath);
		this.nodes = DomParser.returnNodeList(doc);
	}
	
	public String getConfigFilePath() {
		return configFilePath;
	}

	public void setConfigFilePath(String configFilePath) {
		this.configFilePath = configFilePath;
	}

	public String getStartElement() {
		return returnNodeContext(Constants.XML_CONFIG[0]);
		
	}

	public String getEndElement() {
		return returnNodeContext(Constants.XML_CONFIG[1]);
		
	}

	public String getStartDetailCrawl() {
		return returnNodeContext(Constants.XML_CONFIG[2]);
	}

	public String getEndDetailCrawl() {
		return returnNodeContext(Constants.XML_CONFIG[3]);
		
	}
	
	public Integer getPages() {
		return Integer.parseInt(returnNodeContext(Constants.XML_CONFIG[4]));
	}
	
	public String getBoyPage() {
		return returnNodeContext(Constants.XML_CONFIG[5]);
		
	}
	
	public String getGirlPage() {
		return returnNodeContext(Constants.XML_CONFIG[6]);
		
	}
	
	private String returnNodeContext(String findElement) {
		for (int i = 0;i < nodes.getLength();i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE &&
	    nodes.item(i).getNodeName() == findElement) {
				return nodes.item(i).getTextContent();
			}
		}
		return "";
	}
} 
