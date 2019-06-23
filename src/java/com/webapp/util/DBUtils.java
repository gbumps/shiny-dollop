/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.util;


import com.webapp.settings.ConnectionClass;
import com.webapp.jaxb.ProductItem;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


/**
 *
 * @author stephen
 */
public class DBUtils implements Serializable {

	public DBUtils() {
		super();
	}
	
	 public void insertDataCrawledToDB(List<ProductItem> productItems) throws Exception{
			Connection con = ConnectionClass.GetConnection();
			PreparedStatement p = con.prepareStatement("INSERT INTO tblProduct(ID, Name, Sale, Price, OldPrice, Type, Link, Sex, Distributor, Description) VALUES(?,N'(?),?,?,?,?,?,?,?,N'(?))");
			productItems.forEach(t -> {
			  String     id					 = t.getId(),
							     name				 = t.getName(),
							     type				 = t.getType(),
								   link        = t.getLink(),
 							     distributor = t.getDistributor(),
							     description = t.getDescription();
				Byte       sale        = parseByte(t.isSale()),
							     sex				 = parseByte(t.isSex());
				Integer		 price			 = t.getPrice().intValue(),
								   oldprice    = t.getOldprice().intValue();
				List			 images	     = t.getImages().getImage(),
									 options     = t.getOptions().getOption();
				try {
					p.setString(1, id);
					p.setString(2, name);
					p.setByte(3, sale);
					p.setInt(4, price);
					p.setInt(5, oldprice);
					p.setString(6, type);
					p.setString(7, link);
					p.setByte(8, sex);
					p.setString(9, distributor);
					p.setString(10, description);
					p.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("error at product id: " + id);
					//e.printStackTrace();
				}
				
				images.forEach(image -> {
					try {
						if (image != null) {
							PreparedStatement pImage = con.prepareStatement("INSERT INTO tblProductImage(ImageLink, OfProductID) VALUES(?,?)");
							pImage.setString(1, image.toString());
							pImage.setString(2, id);
							pImage.executeUpdate();
							pImage.close();
						}
					} catch(Exception e) {
						//System.out.println("error insert image of product " + id + ", image data: " + image);
						//e.printStackTrace();
					}
				});
				
				options.forEach(option -> {
					//System.out.println("option: " + option);
				  try {
						if (option != null) {
PreparedStatement pOption = con.prepareStatement("INSERT INTO tblProductOption(Option, OfProductID) VALUES(?,?)");
							pOption.setString(1, option.toString());
							pOption.setString(2, id);
							pOption.executeUpdate();
							pOption.close();
						}
					} catch(Exception e) {
						//System.out.println("error insert option of product " + id + ", option data: " + option);
						e.printStackTrace();
					}
				});
				
			});
			if (p != null) {
				p.close();
			}
			if (con != null) {
        con.close(); 
			}
			System.out.println("Insert item completed !");
		}
	 
	 private Byte parseByte(boolean b) {
		 return Byte.parseByte(b ? "1": "0");
	 }
}
