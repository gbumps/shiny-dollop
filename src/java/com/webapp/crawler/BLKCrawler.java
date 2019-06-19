package com.webapp.crawler;

import com.webapp.settings.Constants;
import com.webapp.settings.PropertiesReading;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stephen
 */
public class BLKCrawler extends BaseCrawler implements Runnable {

	@Override
	public void run() {
		BaseCrawler blkCrawler = new BaseCrawler();
		blkCrawler.setPropertiesReading(new PropertiesReading(Constants.BLUEKID_CONFIG_DIRECTORY));
		blkCrawler.setXslLinkDirectory(Constants.BLUEKID_XSL_DIRECTORY);
		blkCrawler.setXslLinkDetailDirectory(Constants.BLUEKID_XSL_DETAIL_DIRECTORY);
		blkCrawler.setXmlOutputLinksFile(Constants.BLUEKID_XML_OUTPUT_ALL_LINKS);
		blkCrawler.setXmlOutputDetailFile(Constants.BLUEKID_XML_OUTPUT_ALL_PRODUCT_DETAILS);
		try {
			//blkCrawler.crawl();
			blkCrawler.test();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}
