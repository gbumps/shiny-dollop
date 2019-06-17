/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;

import com.webapp.checker.XMLSyntaxChecker;
import com.webapp.jaxb.ProductItem;
import com.webapp.jaxb.Products;
import com.webapp.settings.PropertiesReading;
import com.webapp.util.TextUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
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
import javax.xml.stream.XMLStreamException;
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
	private String xslLinkDetailDirectory;

	public BaseCrawler(PropertiesReading propertiesReading, String xslLinkDirectory, String xslLinkDetailDirectory) {
		this.propertiesReading = propertiesReading;
		this.xslLinkDirectory = xslLinkDirectory;
		this.xslLinkDetailDirectory = xslLinkDetailDirectory;
	}
	
	

//	public BaseCrawler(PropertiesReading propertiesReading) {
//		this.propertiesReading = propertiesReading;
//	}
//	public XMLEventReader parseStringtoXMLEventReader(String xmlSection) throws UnsupportedEncodingException, XMLStreamException {
//			byte[] array = xmlSection.getBytes("UTF-8");
//			ByteArrayInputStream inputStream = new ByteArrayInputStream(array);
//			XMLInputFactory inputFactory = XMLInputFactory.newFactory();
//			XMLEventReader reader = inputFactory.createXMLEventReader(inputStream);
//			return reader;
	//}
	
//	public BufferedReader parseXMLFileToXMLEventReader(String filePath) throws Exception {
//		  FileInputStream fis = new FileInputStream(filePath);
//			BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
//			return br;
//	}
	
  private String getHtmlDocsBody(String urlString, String startElement, String endElement) throws MalformedURLException, IOException {
		  StringBuilder document = new StringBuilder();
			XMLSyntaxChecker checker = new XMLSyntaxChecker();
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			connection.addRequestProperty("User-Agent", "Unknown");
			InputStream is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			String resultDoc = document.append(checker.check(reader.lines().collect(Collectors.joining()))).substring(document.indexOf(startElement), document.indexOf(endElement, document.indexOf(startElement)));
			//System.out.println("document: " + resultDoc);
			reader.close();
			return resultDoc;
	}
	
	private void readDataAndTransformToXML(String pathToXSL, String outputFileXML, String html) throws Exception{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		StreamSource xslt = new StreamSource(pathToXSL);
		Transformer trans = transformerFactory.newTransformer(xslt);
		//StreamSource xmlFile = new StreamSource(pathToXML);
		StreamResult streamResult = new StreamResult(new FileOutputStream(outputFileXML));
		trans.transform(new StreamSource(new StringReader(html)), streamResult);
	}	
	
	private void setDocumentConfig(String configFilePath) throws Exception { 
		propertiesReading = new PropertiesReading(configFilePath);  
		propertiesReading.setDoc();
	}
	
	private void getEachProductLink(String numberOfPages) {
		
	}
	
 	public void crawl(String configFilePath) throws Exception {
		setDocumentConfig(configFilePath);
		StringBuilder htmlDocs = new StringBuilder();
		//ArrayList<String> configs = propertiesReading.getConfigs();
	  //TextUtils t = new TextUtils();
	  for (int i = 1; i <= propertiesReading.getPages(); i++) {
		  String boyLink = propertiesReading.getBoyPage() + i, 
						 girlLink = propertiesReading.getGirlPage() + i;
				  //System.out.println("link: " + link);
		  String boyHtmlProductDetailLinks = getHtmlDocsBody(boyLink, propertiesReading.getStartElement(), propertiesReading.getEndElement()), 
							girlHtmlProductDetailLinks = getHtmlDocsBody(girlLink, propertiesReading.getStartElement(), propertiesReading.getEndElement());
		  htmlDocs.append(boyHtmlProductDetailLinks);
			htmlDocs.append(girlHtmlProductDetailLinks);
		}
	  htmlDocs.insert(0, "<root>").insert(htmlDocs.length(), "</root>");
		readDataAndTransformToXML(configFilePath, configFilePath, configFilePath);
					//System.out.println("res: " + htmlDocs);
//		  FileWriter fw = new FileWriter("web/XML/crawled.xml");
//					fw.write(htmlDocs);
//					fw.close();
//					System.out.println("wrote crawled to file !");
		  //
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
	}
	
	
	
//	public void getEachProductDetail(String product) throws Exception {
//		 FileInputStream fis = new FileInputStream(new File("web/XML/crawled"));
//	} 
	
//	 public String getWebData(String link) {
////			final String REGEX = "(>|<)((\\s){1,})"; 
////			final String SUBSTANCE = "$1"; 
//			BufferedReader r = null;
//			String document = "";
//			try {
//				 r = getBufferedReaderForURL(link);
//				 document = r.lines().collect(Collectors.joining(""));
//				 //document = Pattern.compile(REGEX, Pattern.MULTILINE).matcher(document).replaceAll(SUBSTANCE);
//				 //int start = document.indexOf(startElement), end = document.indexOf(endElement, start);
//				 //document = document.substring(start, end);
//				 //System.out.println("crawled: " + document);
//				 r.close();
//			} catch (Exception e) {
//				 e.printStackTrace();
//			}
//			return document;
//	 }
//	 
	
}
