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
public class SearchProductServlet extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private static String xml = "";
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			String query = request.getParameter("query"), 
						 navigate = request.getParameter("navigate");
			System.out.println("navigate: " + navigate);
			System.out.println("Search Servlet at XMLProject_Summer: " + query);
		  xml = DBUtils.getDataFromDB("SELECT TOP 7 ID, Type, Name, Price, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID) AS Images FROM tblProduct p WHERE Name LIKE '%" + query + "%'" + Constants.returnDBXMLRoot(Constants.PRODUCTS, Constants.PRODUCT));
			System.out.println("xml: " + xml);
			response.setCharacterEncoding("UTF-8");
			if (navigate.equals("true")) {
				request.setAttribute("SEARCH_DATA", xml);
				request.setAttribute("QUERY", query);
				request.getRequestDispatcher("searchresult.jsp").forward(request, response);
			}
			else {
				response.getWriter().write(xml);
			}
		} catch (Exception e) {
			System.out.println("error at search product servlet !");
			e.printStackTrace();
		}
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
