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

//	private static final String CONDITION = " WHERE p.Sex = ? AND p.Type IN (N'Đồ', N'Bộ', N'Set')";
//	
//	private static final String SELECT = "SELECT TOP 10 p.ID, p.Name, p.Price, p.OldPrice, (SELECT TOP 1 ImageLink FROM tblProductImage WHERE p.ID = OfProductID " + Constants.returnDBXMLRoot("Images", "") + ") FROM tblProduct p";
	
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
		return "SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID) FROM tblProduct p WHERE Type IN (N'Đồ', N'Bộ', N'Set') AND Sex = 0) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID) FROM tblProduct p WHERE Type IN (N'Đồ', N'Bộ', N'Set') AND Sex = 1) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID) FROM tblProduct p WHERE Type IN (N'Áo') AND Sex = 1) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID) FROM tblProduct p WHERE Type IN (N'Áo') AND Sex = 0) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID) FROM tblProduct p WHERE Type IN (N'Quần') AND Sex = 1) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID) FROM tblProduct p WHERE Type IN (N'Quần') AND Sex = 0) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID) FROM tblProduct p WHERE Type IN (N'Váy') AND Sex = 0) AS tbl(ID, Sex, Type, Images)" + Constants.returnDBXMLRoot(Constants.PRODUCTS, Constants.PRODUCT);
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
