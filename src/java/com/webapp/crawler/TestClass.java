/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;

/**
 *
 * @author stephen
 */
public class TestClass extends BaseCrawler{
	
	 
	public static void main(String[] args) {
		//JDNCrawler jdnc = new JDNCrawler();
		//jdnc.run();
		BLKCrawler bxsc = new BLKCrawler();
	  bxsc.run();
	}
	
}
