/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.settings;


/**
 *
 * @author stephen
 */
public class Constants {
	
	public static final String PAGES = "pages";
	public static final String LINK = "link";
	public static final String START_ELEMENT = "startelement";
	public static final String END_ELEMENT = "endelement";
	public static final String START_ELEMENT_DETAIL = "startelementdetail";
	public static final String END_ELEMENT_DETAIL = "endelementdetail";
	public static final String BOY_LINK = "boy";
	public static final String GIRL_LINK = "girl";
	public static final String[] XML_CONFIG = {START_ELEMENT, END_ELEMENT,START_ELEMENT_DETAIL, END_ELEMENT_DETAIL, PAGES, BOY_LINK, GIRL_LINK};
	
	public static final String JADINY = "Jadiny";
	public static final String JADINY_CONFIG_DIRECTORY = "CONFIG/jadinyConfig.xml";
	public static final String JADINY_XSL_DIRECTORY = "XSLT/crawl/jadiny.xsl";
	public static final String JADINY_XSL_DETAIL_DIRECTORY = "XSLT/crawl/jadinyDetail.xsl";
	public static final String JADINY_XML_OUTPUT_ALL_LINKS = "XML/jadinyOutputLinks.xml";
	public static final String JADINY_XML_OUTPUT_ALL_PRODUCT_DETAILS = "XML/jadinyOutputDetails.xml";
	
	public static final String BLUEKID = "Bluekids";
	public static final String BLUEKID_CONFIG_DIRECTORY = "CONFIG/BluekidsConfig.xml";
	public static final String BLUEKID_XSL_DIRECTORY = "XSLT/crawl/bluekids.xsl";
	public static final String BLUEKID_XSL_DETAIL_DIRECTORY = "XSLT/crawl/bluekidsDetail.xsl";
	public static final String BLUEKID_XML_OUTPUT_ALL_LINKS = "XML/bluekidsOutputLinks.xml";
	public static final String BLUEKID_XML_OUTPUT_ALL_PRODUCT_DETAILS = "XML/bluekidsOutputDetails.xml";
	
	public static final String PRODUCTS = "Products";
	public static final String PRODUCT = "Product";
	
	public static final String SCHEMA_FILE_DIRECTORY = "XSD/BabyProduct.xsd";
	
	public static final String PACKAGE_JAXB = "com.webapp.jaxb"; 
	
	public static final String GOOGLE_BOT = "Googlebot/2.1 (+http://www.googlebot.com/bot.html)";
	
	public static final String NO_DESCRIPTION = "Không có mô tả";
	
	public static String returnDBXMLRoot(String root, String item) {
		return "FOR XML PATH ('" + item + "'), ROOT ('" + root + "'), TYPE";
	}
}
