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

	private Thread t;
	private String threadName;
	private String contextPath;

	public BLKCrawler(String threadName, String contextPath) {
		this.contextPath = contextPath;
		this.threadName = threadName;
	}
	
	@Override
	public void run() {
		BaseCrawler blkCrawler = new BaseCrawler();
		blkCrawler.setWebPageName(threadName);
		blkCrawler.setPropertiesReading(new PropertiesReading(contextPath + Constants.BLUEKID_CONFIG_DIRECTORY));
		blkCrawler.setContextPath(contextPath);
		blkCrawler.setXslLinkDirectory(contextPath + Constants.BLUEKID_XSL_DIRECTORY);
		blkCrawler.setXslDetailDirectory(contextPath + Constants.BLUEKID_XSL_DETAIL_DIRECTORY);
		blkCrawler.setXmlOutputLinksFile(contextPath + Constants.BLUEKID_XML_OUTPUT_ALL_LINKS);
		blkCrawler.setXmlOutputDetailFile(contextPath + Constants.BLUEKID_XML_OUTPUT_ALL_PRODUCT_DETAILS);
		try {
			blkCrawler.crawl();
			//blkCrawler.insertToDB();
			//blkCrawler.test();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	public boolean isThreadDead() {
//		return t.isAlive();
//	}

	public void start() {
		System.out.println("Starting thread: " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
		
}
