/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author stephen
 */
public class SuggestionDTO implements Serializable {
	private String productId;
	private String productName;
	private Integer point;
	private ArrayList<String> productImages;

	public SuggestionDTO(String productId, String productName, Integer point, ArrayList<String> productImages) {
		this.productId = productId;
		this.productName = productName;
		this.point = point;
		this.productImages = productImages;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public ArrayList<String> getProductImages() {
		return productImages;
	}

	public void setProductImages(ArrayList<String> productImages) {
		this.productImages = productImages;
	}
	
  
}
