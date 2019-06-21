/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;

import com.webapp.checker.XMLSyntaxChecker;
import com.webapp.jaxb.ProductItem;
import com.webapp.jaxb.Products;
import com.webapp.settings.Constants;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.cert.Certificate;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author stephen
 */
public class BaseCrawler {
	
	private PropertiesReading propertiesReading;
	private String xslLinkDirectory;
	private String xslLinkDetailDirectory;
	private String xmlOutputLinksFile;
	private String xmlOutputDetailFile;
	private String webPageName;
	
	private static final String UserAgent = "User-Agent";
	//private static final String PRODUCTS = "<Products>";


	public void setWebPageName(String webPageName) {
		this.webPageName = webPageName;
	}

	public void setPropertiesReading(PropertiesReading propertiesReading) {
		this.propertiesReading = propertiesReading;
	}

	public void setXslLinkDirectory(String xslLinkDirectory) {
		this.xslLinkDirectory = xslLinkDirectory;
	}

	public void setXslLinkDetailDirectory(String xslLinkDetailDirectory) {
		this.xslLinkDetailDirectory = xslLinkDetailDirectory;
	}

	public void setXmlOutputLinksFile(String xmlOutputLinksFile) {
		this.xmlOutputLinksFile = xmlOutputLinksFile;
	}

	public void setXmlOutputDetailFile(String xmlOutputDetailFile) {
		this.xmlOutputDetailFile = xmlOutputDetailFile;
	}

  private String getHtmlDocsBody(String urlString, String startElement, String endElement) throws MalformedURLException, IOException {
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
		//StreamSource xmlFile = new StreamSource(pathToXML);
		StreamResult streamResult = new StreamResult(new FileOutputStream(outputFileXML));
		trans.transform(new StreamSource(new StringReader(html)), streamResult);
	}	
	
	private void setDocumentConfig() throws Exception {  
		propertiesReading.setNodes();
	}
	
 	public void crawl() throws Exception {
	  setDocumentConfig();
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
			System.out.println("page " + i + " boy finished !");
			htmlDocs.append(girlHtmlProductDetailLinks);
			System.out.println("page " + i + " girl finished !");
		}
	  htmlDocs.insert(0, "<root>").insert(htmlDocs.length(), "</root>");
		System.out.println("finished get link of all products !");
		readDataAndTransformToXML(xslLinkDirectory, xmlOutputLinksFile, htmlDocs.toString());
    System.out.println("finished transform !");
		
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

//					JAXBContext jaxbc = JAXBContext.newInstance("com.webapp.jaxb");
//					Unmarshaller u = jaxbc.createUnmarshaller();
//					SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//					Schema schema = factory.newSchema(new File("web/XSD/BabyProduct.xsd"));
//					u.setSchema(schema);
//					//Validator validator = schema.newValidator();
//				  File f = new File("web/XML/jadiny.xml");
//					Products products  = (Products) u.unmarshal(f);
//					ProductItem productItem = products.getProduct().get(1);
//					System.out.println("product id: " + productItem.getId());
//					//validator.validate(new SAXSource(xmlRes));
//						System.out.println("validate completed !");
//					//b.readDataAndTransformToXML(,, htmlRes);
//					//} 
	}
	
	public ArrayList getListProductDetail(String file) throws Exception {	
		//FileInputStream fis = new FileInputStream(new File(xmlOutputLinks));
		 ArrayList<String> listProductLinks = new ArrayList<>();
		 Document d = DomParser.returnDocument(file);
		 NodeList nodes = DomParser.returnNodeList(d);
		 for (int i=0; i<nodes.getLength(); i++){
			 if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE && nodes.item(i).getNodeName() == "Link"){					listProductLinks.add(nodes.item(i).getTextContent());
			 }
		 }
		 return listProductLinks;
	} 
	
	public void test() throws Exception {
		setDocumentConfig();
		ArrayList<String> list = getListProductDetail(xmlOutputLinksFile);
	  //list.forEach(t -> System.out.println("link: " + t.toString()));
		String html = list.get(0).toString();
		//System.out.println("html: " + html);
		//System.out.println("detail: " + propertiesReading.getStartDetailCrawl() + propertiesReading.getEndDetailCrawl());
		String doc = "<products>" + "<product>"+ getHtmlDocsBody(html, propertiesReading.getStartDetailCrawl(), propertiesReading.getEndDetailCrawl()) + "<link href='"+ html +"'/>" + "</product>" + "</products>";
	  TransformerFactory transformerFactory = TransformerFactory.newInstance();
		StreamSource xslt = new StreamSource(xslLinkDetailDirectory);
		Transformer trans = transformerFactory.newTransformer(xslt);
		StreamSource xmlFile = new StreamSource(new StringReader(doc));
		StreamResult streamResult = new StreamResult(new FileOutputStream(xmlOutputDetailFile));
		trans.transform(xmlFile, streamResult);
	  System.out.println("Transform completed !");
	  //System.out.println("test: " + doc);
		
	} 

}
