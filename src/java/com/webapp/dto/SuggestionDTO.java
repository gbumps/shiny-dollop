/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.dto;

import java.io.Serializable;

/**
 *
 * @author stephen
 */
public class SuggestionDTO implements Serializable {
	private String productId;
	private String productName;
	private int price;
	private int oldPrice;
	private boolean sale;
	private float rating;
	private int review;
	private String productImages;
	private int point;

	public SuggestionDTO(String productId, String productName, int price, int oldPrice, boolean sale, float rating, int review, String productImages, int point) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.oldPrice = oldPrice;
		this.sale = sale;
		this.rating = rating;
		this.review = review;
		this.productImages = productImages;
		this.point = point;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(int oldPrice) {
		this.oldPrice = oldPrice;
	}

	public boolean isSale() {
		return sale;
	}

	public void setSale(boolean sale) {
		this.sale = sale;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	public String getProductImages() {
		return productImages;
	}

	public void setProductImages(String productImages) {
		this.productImages = productImages;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
