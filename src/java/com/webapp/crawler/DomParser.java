/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author stephen
 */
public class DomParser {
	
	public static Document returnDocument(String filePath) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		File f = new File(filePath); 
		return db.parse(f);
	}
	
	public static NodeList returnNodeList(Document doc) throws Exception {
		doc.getDocumentElement().normalize();
		Element root = doc.getDocumentElement();
		//NodeList nodes = ;
		return root.getChildNodes();
		//System.out.println("length: " + nodes.getLength());
	}
	
}
