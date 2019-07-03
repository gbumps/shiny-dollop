/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;

import com.webapp.settings.Constants;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 *
 * @author stephen
 */
public class TestClass extends BaseCrawler{
	
	 
	public static void main(String[] args) {
		//JDNCrawler jdnc = new JDNCrawler("Jadiny");
		//jdnc.start();
		//BLKCrawler bxsc = new BLKCrawler("BlueKids");
	 // bxsc.start();
		//System.out.println("Insert item completed !");
		BaseCrawler b = new BaseCrawler();
		String document = "";
		try {
		    document = b.getHtmlDocsBody("https://jadiny.vn/collections/be-trai/products/ao-sm-be-trai-clt-caro-vang-ast124", "<div class=\"product-container cf\">", "<div class=\"related-products\"");
//			URL url = new URL("https://jadiny.vn/collections/be-trai/products/ao-sm-tn-clt-linen-caro-xanh-cam-ast115-bt");
//			URLConnection connection = url.openConnection() ;
//			connection.addRequestProperty("User-Agent", Constants.GOOGLE_BOT);
//			InputStream is = connection.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//		  document = reader.lines().collect(Collectors.joining());
			System.out.println("doc: " + document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	}
	
}
