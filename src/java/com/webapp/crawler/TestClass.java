/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;

import com.webapp.jaxb.ProductItem;
import com.webapp.jaxb.Products;
import com.webapp.settings.PropertiesReading;
import com.webapp.util.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 *
 * @author stephen
 */
public class TestClass extends BaseCrawler{
	
	 
	public static void main(String[] args) {
		StringBuilder htmlDocs = new StringBuilder();
		ArrayList<String> configs = new ArrayList<>();
    try {
		   BaseCrawler b = new BaseCrawler();
			 PropertiesReading p = new PropertiesReading("web/CONFIG/jadinyConfig.xml");
			 TextUtils t = new TextUtils();
			 p.PropertiesReading();
			 configs = p.getConfigs();
			 //System.out.println("config: " + configs);
			 //or (int i = 1; i <= Integer.parseInt(configs.get(2)); i++) {
				  //String link = configs.get(3) + i;
					//System.out.println("link: " + link);
				  //htmlDocs = b.getHtmlDocsBody(configs.get(3) + 1, p.getStartElement(), p.getEndElement());
					//System.out.println("res: " + htmlDocs);
					//FileWriter fw = new FileWriter("web/XML/crawled.xml");
					//fw.write(htmlDocs);
					//fw.close();
					//System.out.println("wrote crawled to file !");
					//b.readDataAndTransformToXML("web/XSLT/jadiny.xsl", htmlDocs);
//					ArrayList<String> links = getEachProductDetail("web/XML/output.xml");
//					for(int i = 0; i < links.size(); i++) {
//						//System.out.println("start craw: " + p.getStartDetailCrawl());
//						//System.out.println("end craw: " + p.getEndDetailCrawl());
//						String link = links.get(i).toString();
//						try {
//							StringBuilder htmlDetail = new StringBuilder().append("<product>" + b.getHtmlDocsBody(link, p.getStartDetailCrawl(), p.getEndDetailCrawl())+ addLinkProductTag(link) + "</product>");
//							String res = t.refineHtml(htmlDetail.toString());
//							
//							htmlDocs.append(res);
//							System.out.println("product " + (i + 1) + " crawled successfully !");
//						} catch (Exception e) {
//							System.out.println("error when crawling product " + (i + 1));
//							e.printStackTrace();
//						}
//					}
//					String htmlRes = "<products>" + htmlDocs.toString() + "</products>";
//					final Path path = Paths.get("web/XML/crawled.xml");
//							Files.write(path, Arrays.asList(htmlRes), StandardCharsets.UTF_8,
//											Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
//							System.out.println("Crawled completed !");
//					TransformerFactory transformerFactory = TransformerFactory.newInstance();
//					StreamSource xslt = new StreamSource("web/XSLT/jadinyDetail.xsl");
//					Transformer trans = transformerFactory.newTransformer(xslt);
//					StreamSource xmlFile = new StreamSource("web/XML/crawled.xml");
//					StreamResult streamResult = new StreamResult(new FileOutputStream("web/XML/jadiny.xml"));
//					trans.transform(xmlFile, streamResult);
//						System.out.println("Transform completed !");
					JAXBContext jaxbc = JAXBContext.newInstance("com.webapp.jaxb");
					Unmarshaller u = jaxbc.createUnmarshaller();
					SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
					Schema schema = factory.newSchema(new File("web/XSD/BabyProduct.xsd"));
					u.setSchema(schema);
					//Validator validator = schema.newValidator();
				  File f = new File("web/XML/jadiny.xml");
					Products products  = (Products) u.unmarshal(f);
					ProductItem productItem = products.getProduct().get(1);
					System.out.println("product id: " + productItem.getId());
					//validator.validate(new SAXSource(xmlRes));
						System.out.println("validate completed !");
					//b.readDataAndTransformToXML(,, htmlRes);
					//} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getEachProductDetail(String filePath) throws Exception {
		ArrayList<String> dataConfig = new ArrayList<>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		File f = new File(filePath); 
	  Document doc = db.parse(f);
		doc.getDocumentElement().normalize();
		//Element root = doc.getDocumentElement();
		//System.out.println("root: " + root.getNodeName());
		NodeList children = doc.getElementsByTagName("Link");
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			//System.out.println("node content: " + );
		  dataConfig.add(node.getTextContent());
		}
		return dataConfig;
	}
	
	private static String addLinkProductTag(String link) {
		return "<link href='" + link + "' />";
	}
	
//	public static String getHtml(String link) {
//		String htmlDocs = "";
//		TextUtils t = new TextUtils();
//		try {
//			htmlDocs = t.getHtmlDocsBody(link);
//			
//		} catch (Exception  e) {
//			e.printStackTrace();
//			System.out.println("crawled err !");
//		}
//		htmlDocs = t.refineHtml(htmlDocs);
//		return htmlDocs;
//	}
	
	public static void getConfig(String filePath) throws Exception{
	  
		//System.out.println("length: " + children.item(0));
	}
	
	

}
