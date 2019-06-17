/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.crawler;

import com.webapp.settings.PropertiesReading;

/**
 *
 * @author stephen
 */
public class JDNCrawler extends BaseCrawler implements Runnable{

	public JDNCrawler(PropertiesReading propertiesReading) {
		super(propertiesReading);
	}

	@Override
	public void run() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	
}
