/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.util;


import com.webapp.settings.ConnectionClass;
import com.webapp.jaxb.ProductItem;
import com.webapp.settings.Constants;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


/**
 *	
 * @author stephen
 */
public class DBUtils implements Serializable {
	private static Connection con;
	private static PreparedStatement p;
	private static ResultSet rs;
	
	 public static void insertDataCrawledToDB(List<ProductItem> productItems) throws Exception{
			con = ConnectionClass.GetConnection();
			productItems.forEach(t -> {
				List images	 = t.getImages().getImage(),
						 options = t.getOptions().getOption();
	
				try {
					p = con.prepareStatement("INSERT INTO tblProduct(ID, Name, Sale, Price, OldPrice, Type, Link, Sex, Distributor, Description, CanCombine) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
					if (!t.getId().equals("")) {
						p.setString(1, t.getId());
						p.setString(2, t.getName());
						p.setByte(3, parseByte(t.isSale()));
						p.setInt(4, t.getPrice().intValue());
						p.setInt(5, t.getOldprice().intValue());
						p.setString(6, returnType(t.getType()));
						p.setString(7, t.getLink());
						p.setByte(8, parseByte(t.isSex()));
						p.setString(9, t.getDistributor());
						p.setString(10, t.getDescription().equals("") ? Constants.NO_DESCRIPTION : t.getDescription());
					  p.setByte(11, parseByte(t.isCancombine()));
						p.executeUpdate();
					}
				
				} catch (Exception e) {
					System.out.println("error at product id: " + t.getId());
					
				}
				
				images.forEach(image -> {
					try {
						if (image != null) {
							p = con.prepareStatement("INSERT INTO tblProductImage(ImageLink, OfProductID) VALUES (?,?)");
							p.setString(1, image.toString());
							p.setString(2, t.getId());
							p.executeUpdate();
							p.close();
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
				});
				
				options.forEach(option -> {
				  try {
						if (option != null) {
							p = con.prepareStatement("INSERT INTO tblProductOption(OptionProduct, OfProductID) VALUES (?,?)");
							p.setString(1, option.toString());
							p.setString(2, t.getId());
							p.executeUpdate();
							p.close();
						}
					} catch(Exception e) {
						System.out.println("error insert option of product " + t.getId() + ", option data: " + option);
						e.printStackTrace();
					}
				});
			});
			closeConnection();
			System.out.println("Insert item completed !");
		}
	 
	 private static String returnType(String type) {
		 if (type.equals("Đầm") || type.equals("Chân")) {
			 return "Váy";
		 } else if (type.equals("Set") || type.equals("Đồ") || type.equals("Bmn")) {
			 return "Bộ";
		 } else if (type.equals("Sơ")) {
			 return "Áo";
		 }
		 return type;
	 }
	 
	 public static boolean checkProductsCrawled() throws Exception {
		 con = ConnectionClass.GetConnection();
		 p = con.prepareStatement("SELECT TOP 300 * FROM tblProduct");
		 rs = p.executeQuery();
		 int count = 0;
		 while(rs.next()) {
			 count++;
		 }
		 closeConnection();
		 if (count < 300) return false;
		 else return true;
	 } 
	 
	public static String getDataFromDB(String sql) throws Exception {
		 String res = "";
		 con = ConnectionClass.GetConnection();
		 p = con.prepareStatement(sql);
		 rs = p.executeQuery();
		 if (rs.next()) {
			 res = rs.getString(1);
		 }
		 closeConnection();
		 return res;
	}
	
	public static Integer countProduct(String sql) throws Exception {
		int i = 0;
		con = ConnectionClass.GetConnection();
		p = con.prepareStatement(sql);
		rs = p.executeQuery();
		if (rs.next()) {
			i = Integer.parseInt(rs.getString(1));
		}
		closeConnection();
		return i;
	}
	 
	private static Byte parseByte(boolean b) {
		return Byte.parseByte(b ? "1": "0");
	}
	 
	private static void closeConnection() throws Exception{
		if (rs != null) {
			rs.close();
		}
		if (p != null) {
			p.close();
		}
		if (con != null) {
      con.close(); 
		}
	}
	 
}
