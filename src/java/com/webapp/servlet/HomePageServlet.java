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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stephen
 */
public class HomePageServlet extends HttpServlet {

	
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
				request.setAttribute("HomepageResult", DBUtils.getDataFromDB(returnSqlString()));
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String returnSqlString() {
		String res = "";	
		String[] types = {"N'Bộ'","N'Áo'","N'Quần'","N'Váy'"};
		for (int i = 0;i < 2; i++) {
			for (int j = 0;j < types.length;j++) {
				res += "SELECT * FROM (SELECT TOP 1 ID, Sex, Type, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID), (SELECT COUNT(ID) FROM tblProduct p WHERE Type IN (" + types[j] +") AND Sex = " + i + ") AS NumberOfProducts, (SELECT AVG(Price) FROM tblProduct p WHERE Type IN (" + types[j] +") AND Sex = " + i + ") AS AveragePrice FROM tblProduct p WHERE Type IN (" + types[j] +") AND Sex = " + i + ") AS tbl(ID, Sex, Type, Images, NumberOfProducts, AveragePrice)";
				res += (i == 1 && j == types.length - 1) ? Constants.returnDBXMLRoot(Constants.PRODUCTS, Constants.PRODUCT) : " UNION ALL ";
			}
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
