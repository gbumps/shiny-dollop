/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;


import com.webapp.dto.PairProductLinks;
import com.webapp.util.DBUtils;
import com.webapp.jaxb.Products;
import com.webapp.settings.Constants;
import com.webapp.settings.PropertiesReading;
import com.webapp.util.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
/**
 *
 * @author stephen
 */
public class BaseCrawler {
	
	private PropertiesReading propertiesReading;
	private String xslLinkDirectory;
	private String xslDetailDirectory;
	private String xmlOutputLinksFile;
	private String xmlOutputDetailFile;
	private String webPageName;
	private String contextPath;
	
	private static final String UserAgent = "User-Agent";
	//private static final String PRODUCTS = "<Products>";

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public void setWebPageName(String webPageName) {
		this.webPageName = webPageName;
	}

	public void setPropertiesReading(PropertiesReading propertiesReading) {
		this.propertiesReading = propertiesReading;
	}

	public void setXslLinkDirectory(String xslLinkDirectory) {
		this.xslLinkDirectory = xslLinkDirectory;
	}

	public void setXslDetailDirectory(String xslDetailDirectory) {
		this.xslDetailDirectory = xslDetailDirectory;
	}

	public void setXmlOutputLinksFile(String xmlOutputLinksFile) {
		this.xmlOutputLinksFile = xmlOutputLinksFile;
	}

	public void setXmlOutputDetailFile(String xmlOutputDetailFile) {
		this.xmlOutputDetailFile = xmlOutputDetailFile;
	}

  public String getHtmlDocsBody(String urlString, String startElement, String endElement) throws MalformedURLException, IOException {
		String document = "";
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection() ;
		connection.addRequestProperty(UserAgent, Constants.GOOGLE_BOT);
		InputStream is = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
	  document = reader.lines().collect(Collectors.joining());
	  document = TextUtils.subStringHtml(document.toString(), document.indexOf(startElement), document.indexOf(endElement, document.indexOf(startElement)));
		document = TextUtils.removeUnusedTag(document);
		document = TextUtils.refineHtml(document);
		reader.close();
		return document;
	}
	
	private void readDataAndTransformToXML(String pathToXSL, String outputFileXML, String html) throws Exception{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		StreamSource xslt = new StreamSource(pathToXSL);
		Transformer trans = transformerFactory.newTransformer(xslt);
		StreamResult streamResult = new StreamResult(new FileOutputStream(outputFileXML));
		trans.transform(new StreamSource(new StringReader(html)), streamResult);
	}	
	
	private void setDocumentConfig() throws Exception {  
		propertiesReading.setNodes();
	}
	
 	public void crawl() throws Exception {
	  setDocumentConfig();
		StringBuilder htmlDocs = new StringBuilder();
	  for (int i = 1; i <= propertiesReading.getPages(); i++) {
		  String boyLink = propertiesReading.getBoyPage() + i, 
						 girlLink = propertiesReading.getGirlPage() + i;
		  String boyHtmlProductDetailLinks = returnHtmlBasedOnGender(boyLink, true), 
							girlHtmlProductDetailLinks = returnHtmlBasedOnGender(girlLink, false);
		  htmlDocs.append(boyHtmlProductDetailLinks);
			System.out.println("page " + i + " of " + webPageName +  " boy finished !");
			htmlDocs.append(girlHtmlProductDetailLinks);
			System.out.println("page " + i + " of " + webPageName +  " girl finished !");
		}
	  htmlDocs.insert(0, "<root>").insert(htmlDocs.length(), "</root>");
		System.out.println("finished get link of all products !");
		readDataAndTransformToXML(xslLinkDirectory, xmlOutputLinksFile, htmlDocs.toString());
    System.out.println("finished transform to xml link file of web " + webPageName);
		htmlDocs.setLength(0);
    ArrayList<PairProductLinks> p = getListProductDetail(xmlOutputLinksFile);
		p.forEach(product -> {
			String sex = product.getSex();
			product.getLinks().forEach(link -> {
				try {
					htmlDocs.append("<product>"+ getHtmlDocsBody(link, propertiesReading.getStartDetailCrawl(), propertiesReading.getEndDetailCrawl()) + "<link href='"+ link +"'/>" + "<sex>" + sex + "</sex>" + "</product>");
					System.out.println("link " + link + " completed");
				} catch (Exception e) {
					System.out.println("links " + link + " of " + webPageName + " error");
					e.printStackTrace();
				}
			});
		});
		htmlDocs.insert(0, "<products>").insert(htmlDocs.length(), "</products>");

		readDataAndTransformToXML(xslDetailDirectory, xmlOutputDetailFile, htmlDocs.toString());

	  System.out.println("Transform to xml output detail completed !");
		
		insertToDB();
	}
	
	private static ArrayList getListProductDetail(String file) throws Exception {
			ArrayList<PairProductLinks> res = new ArrayList();
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader reader = factory.createXMLEventReader(new FileReader(file));
			ArrayList<String> lists = null;
			String sex = "";
			while (reader.hasNext()) {
				XMLEvent eventReader = reader.nextEvent();
				if (eventReader.isStartElement()) {
					String start = eventReader.asStartElement().getName().getLocalPart();
					switch (start) {
						case "Links": 
							lists = new ArrayList<String>();
							break;
						case "Link":
							eventReader = (XMLEvent) reader.next();
							String character = eventReader.asCharacters().getData();
							lists.add(character);
							break;
						case "Sex":
							eventReader = (XMLEvent) reader.next();
							sex = eventReader.asCharacters().getData();
							break;
					}
				}
				if (eventReader.isEndElement() && eventReader.asEndElement().getName().getLocalPart().equals("Product")) {
					res.add(new PairProductLinks(sex, lists));
				}
			}
			return res;
	}
	
	private String returnHtmlBasedOnGender(String html, boolean b) throws Exception {
		return "<product>" + "<links>" + getHtmlDocsBody(html, propertiesReading.getStartElement(), propertiesReading.getEndElement()) + "</links>" + "<sex>" + b +"</sex>" + "</product>"; 
	}
	
	private void insertToDB() throws Exception {
		JAXBContext jaxbc = JAXBContext.newInstance(Constants.PACKAGE_JAXB);
	  Unmarshaller u = jaxbc.createUnmarshaller();
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	  Schema schema = factory.newSchema(new File(contextPath + Constants.SCHEMA_FILE_DIRECTORY));
		u.setSchema(schema);
	  File f = new File(xmlOutputDetailFile);
	  Products products  = (Products) u.unmarshal(f);
		System.out.println(webPageName + " has total: " + products.getProduct().size());
		DBUtils.insertDataCrawledToDB(products.getProduct());
	} 

}
