/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.dto;

import java.util.ArrayList;

/**
 *
 * @author stephen
 */
public class PairProductLinks {
	private String sex;
	private ArrayList<String> links;

	public PairProductLinks(String sex, ArrayList<String> links) {
		this.sex = sex;
		this.links = links;
	}

	public String getSex() {
		return sex;
	}

	public ArrayList<String> getLinks() {
		return links;
	}
	
	
	
}
