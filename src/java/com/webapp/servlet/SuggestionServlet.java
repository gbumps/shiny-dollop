/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.servlet;

import com.webapp.settings.Constants;
import com.webapp.util.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stephen
 */
public class SuggestionServlet extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			String yearOld = request.getParameter("yearOld"),
						 sex = request.getParameter("sex"),
						 type = request.getParameter("type"),
						 priceOption = request.getParameter("desirePrice"), 
						 suggestionOption = request.getParameter("suggestionOption");
			System.out.println("sex: " + sex);
			System.out.println("yearold: " + yearOld);
			System.out.println("type: " + type);
			ArrayList priceBetween = returnPriceInBetween(priceOption);
			String res = DBUtils.getDataFromDB(
							returnSqlSuggestionString(
											"%" + yearOld + "%", returnType(type), 
											Integer.parseInt(sex), 
											(Integer) priceBetween.get(0), 
											(Integer) priceBetween.get(1),
											returnSuggestionOption(suggestionOption))
		  );
			System.out.println("res: " + res);
			request.setAttribute("DATA", res);
			request.getRequestDispatcher("suggestionpage.jsp").forward(request, response);
		} catch(Exception ex) {
			System.out.println("error at suggestion servlet");
			ex.printStackTrace();
		}
	}
	
	private String returnType(String type) throws Exception {
		int i = Integer.parseInt(type);
		String res = "";
		//System.out.println("type: " + i);
		switch (i) {
			case 1:
				res = "N'Áo'";
				break;
			case 2:
				res = "N'Quần'";
				break;
			case 3:
				res = "N'Đồ', N'Bộ', N'Set'";
				break;
			case 4:
				res = "N'Váy', N'Đầm'";
				break;
		}
		System.out.println("res: " + res);
		return res;
	}

	private String returnSuggestionOption(String sOption) throws Exception {
		int i = Integer.parseInt(sOption);
		String res = "";
		//System.out.println("type: " + i);
		switch (i) {
			case 1:
				res = "3 ASC, 6 DESC, 7 DESC";
				break;
			case 2:
				res = "6 DESC, 3 ASC, 7 DESC";
				break;
			case 3:
				res = "7 DESC, 6 DESC, 3 ASC";
				break;
		}
		//System.out.println("res: " + res);
		return res;
	}
	
	private String returnSqlSuggestionString(String option, String type, Integer sex, Integer priceFrom, Integer priceTo, String suggestionOption) {
		return "SELECT TOP 20 ID, Name, Price, OldPrice, Sale, Rating, Review, (SELECT TOP 1 ImageLink FROM tblProductImage WHERE ID = OfProductID) AS Images FROM tblProduct WHERE ID IN (SELECT DISTINCT ID FROM tblProduct WHERE ID IN (SELECT OfProductID FROM tblProductOption WHERE OptionProduct LIKE '" + option + "')) AND Type IN (" + type + ") AND Sex = " + sex + " AND Price BETWEEN " + priceFrom + " AND " + priceTo + " ORDER BY " + suggestionOption + Constants.returnDBXMLRoot(Constants.PRODUCTS, Constants.PRODUCT);
	}
	
	private ArrayList returnPriceInBetween(String priceOption) throws Exception {
		int i = Integer.parseInt(priceOption);
		//System.out.println("price: " + i);
		ArrayList res = new ArrayList();
		switch (i) {
			case 1:
				res.add(0);
				res.add(500000);
				break;
			case 2:
				res.add(500000);
				res.add(1000000);
				break;
			case 3:
			  res.add(1000000);
				res.add(1600000);
				break;
		}
		return res;
	}
	
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
