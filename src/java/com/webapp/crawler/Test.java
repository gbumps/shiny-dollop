/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;

import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author stephen
 */
public class Test {
	
	public static void main(String[] args) throws Exception {
		//BaseCrawler b = new BaseCrawler();
		Map<String, ArrayList> p = getListProductDetail("web/XML/jadinyOutputLinks.xml");
	  for (Map.Entry<String, ArrayList> entry : p.entrySet()) {
			String sex = entry.getKey();
			System.out.println("sex: " + sex);
			ArrayList<String> links = entry.getValue();
			links.forEach(t -> {
				System.out.println("link: " + t);
//				try {
//					String docDetail = "<product>" + b.getHtmlDocsBody(t, "<div id=\"single-product\" class=\"inner-top-50\">", "<div class=\"container inner-top-sm\">") + "<link href='"+ t +"'/>" + "<sex>" + sex + "</sex>" + "</product>";
//					System.out.println("links " + t + " completed");
//					htmlDocs.append(docDetail);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			});
		}
	}
//		String basedoc = "<product>" + "<links>" + b.getHtmlDocsBody("https://jadiny.vn/thoi-trang-be-gai_c314.html?p=1", "<div class=\"catalog-products row clearfix\">", "<div class=\"pagination-holder\">") + "</links>" + "<sex>false</sex>" + "</product>";
//		//System.out.println("links: " + links);
//	  String basedocboy = "<product>" + "<links>" + b.getHtmlDocsBody("https://jadiny.vn/thoi-trang-be-trai_c335.html?p=1", "<div class=\"catalog-products row clearfix\">", "<div class=\"pagination-holder\">") + "</links>" + "<sex>true</sex>" + "</product>";
//		
// 	  String root = "<root>" +  basedoc + basedocboy + "</root>";
//		readDataAndTransformToXML("web/XSLT/crawl/jadiny.xsl", "web/XML/jadiny.xml", root);
//		Map<String, ArrayList> p = getListProductDetail("web/XML/jadiny.xml");
//		StringBuilder htmlDocs = new StringBuilder();
//		for (Map.Entry<String, ArrayList> entry : p.entrySet()) {
//			String sex = entry.getKey();
//			System.out.println("sex: " + sex);
//			ArrayList<String> links = entry.getValue();
//			links.forEach(t -> {
//				System.out.println("link: " + t);
//				try {
//					String docDetail = "<product>" + b.getHtmlDocsBody(t, "<div id=\"single-product\" class=\"inner-top-50\">", "<div class=\"container inner-top-sm\">") + "<link href='"+ t +"'/>" + "<sex>" + sex + "</sex>" + "</product>";
//					System.out.println("links " + t + " completed");
//					htmlDocs.append(docDetail);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			});
//		}
//		htmlDocs.insert(0, "<products>").insert(htmlDocs.length(), "</products>");
//		readDataAndTransformToXML("web/XSLT/crawl/jadinyDetail.xsl", "web/XML/jadinyDetail.xml", htmlDocs.toString());
//	}
//	
//	private static void readDataAndTransformToXML(String pathToXSL, String outputFileXML, String html) throws Exception{
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		StreamSource xslt = new StreamSource(pathToXSL);
//		Transformer trans = transformerFactory.newTransformer(xslt);
//		StreamResult streamResult = new StreamResult(new FileOutputStream(outputFileXML));
//		trans.transform(new StreamSource(new StringReader(html)), streamResult);
//	}	
	
	private static Map<String, ArrayList> getListProductDetail(String file) throws Exception {	
			Map<String, ArrayList> res = new HashMap<>();
		  Document d = DomParser.returnDocument(file);
		  NodeList nodes = DomParser.returnNodeList(d);
			String sex = "";
//			for (int i = 0; i < nodes.getLength(); i++) {
//				ArrayList<String> listProductLinks = new ArrayList<>();
//				//NodeList nodeList = nodes.getElementsByTagName("*");
//			}
		  for (int i = 0; i < nodes.getLength(); i++) {
			  ArrayList<String> listProductLinks = new ArrayList<>();
			  if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE && nodes.item(i).getNodeName() == "Product") {	
						NodeList productNodes = nodes.item(i).getChildNodes();
						for (int j = 0; j < productNodes.getLength();j++) {
							if (productNodes.item(j).getNodeType() == Node.ELEMENT_NODE && productNodes.item(j).getNodeName() == "Links") {					
							NodeList linkNodes = productNodes.item(j).getChildNodes();
							for (int k = 0; k < linkNodes.getLength(); k++) {
								if (linkNodes.item(k).getNodeType() == Node.ELEMENT_NODE && linkNodes.item(k).getNodeName() == "Link"){					
								listProductLinks.add(linkNodes.item(k).getTextContent());
								}
							}
						}
						if (productNodes.item(j).getNodeType() == Node.ELEMENT_NODE && productNodes.item(j).getNodeName() == "Sex") {
							sex = productNodes.item(j).getTextContent();
							
						}
					}
				  res.put(sex, listProductLinks); // Sex
				}
		  }
		return res;
	}
	
	
}
