/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

/**
 *
 * @author stephen
 */
public class TestClass extends BaseCrawler{
	
	

	public static void main(String[] args) {
		//JDNCrawler jdnc = new JDNCrawler("Jadiny");
		//jdnc.start();
		//BLKCrawler bxsc = new BLKCrawler("BlueKids", );
	 // bxsc.start();
		//System.out.println("Insert item completed !");
		BaseCrawler b = new BaseCrawler();
		String document = "";
		try {
		    //document = b.getHtmlDocsBody("https://jadiny.vn/collections/be-trai/products/ao-vest-tay-den-bt-avt013", "<div class=\"product-container cf\">", "<div class=\"related-products\">");
				document = b.getHtmlDocsBody("https://jadiny.vn/ao-thun-be-gai-cot-day-xanh-bien_p2567.html", "<div id=\"single-product\" class=\"inner-top-50\">", "<div class=\"container inner-top-sm\">");
//			URL url = new URL("https://jadiny.vn/collections/be-trai/products/ao-sm-tn-clt-linen-caro-xanh-cam-ast115-bt");
//			URLConnection connection = url.openConnection() ;
//			connection.addRequestProperty("User-Agent", Constants.GOOGLE_BOT);
//			InputStream is = connection.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//		  document = reader.lines().collect(Collectors.joining());
			//document = document.replaceAll("&amp;", "");
			//document = new String(document.getBytes("ISO-8859-1"),"UTF-8");
			//document = "#xC1;o thun b#xE9; g#xE1;i c#x1ED9;t d#xE2;y xanh bi#x1EC3;n";
			//byte[] iso = document.getBytes("ISO-8859-1");
			//Charset c = Charset.forName("ISO-8859-1");
			
		  //document = new String(iso,"UTF-8");
//			 byte[] parsedAsISO88591 = parseBytesWithHexLiterals(document);
//        document = new String(parsedAsISO88591, "ISO-8859-1");
//
//        System.out.println(document); // Print out the string, which is in Unicode internally.
//
//        //byte[] asUTF8 = doc.getBytes("UTF-8"); // Get a UTF-8 version of the string.
			System.out.println("doc: " + document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	}
	
}
