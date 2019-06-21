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
	
	private static final Integer START_ELEMENT = 0;
	private static final Integer END_ELEMENT = 1;
	private static final Integer START_DETAIL_ELEMENT = 2;
	private static final Integer END_DETAIL_ELEMENT = 3;
	private static final Integer NUMBER_OF_PAGES = 4;
	private static final Integer BOY_PAGE = 5;
	private static final Integer GIRL_PAGE = 6;
	
	
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
		return returnNodeContext(Constants.XML_CONFIG[START_ELEMENT]);
		
	}

	public String getEndElement() {
		return returnNodeContext(Constants.XML_CONFIG[END_ELEMENT]);
		
	}

	public String getStartDetailCrawl() {
		return returnNodeContext(Constants.XML_CONFIG[START_DETAIL_ELEMENT]);
	}

	public String getEndDetailCrawl() {
		return returnNodeContext(Constants.XML_CONFIG[END_DETAIL_ELEMENT]);
		
	}
	
	public Integer getPages() {
		return Integer.parseInt(returnNodeContext(Constants.XML_CONFIG[NUMBER_OF_PAGES]));
	}
	
	public String getBoyPage() {
		return returnNodeContext(Constants.XML_CONFIG[BOY_PAGE]);
		
	}
	
	public String getGirlPage() {
		return returnNodeContext(Constants.XML_CONFIG[GIRL_PAGE]);
		
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
