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
public class ProductDetailServlet extends HttpServlet {

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
		  	String id = request.getParameter("id"),
							 type = request.getParameter("type"),
							 sql = "SELECT Name, Price, OldPrice, Sale, Distributor, Description, Link, (SELECT ImageLink FROM tblProductImage i WHERE p.ID = OfProductID " + Constants.returnDBXMLRoot("Images", "") + ") FROM tblProduct p WHERE ID = '" + id + "'" +  Constants.returnDBXMLRoot(Constants.PRODUCT, ""),
							 sql1 = "SELECT TOP 5 ID, Name, Price, Type, (SELECT TOP 1 ImageLink FROM tblProductImage i WHERE p.ID = OfProductID) AS Images FROM tblProduct p WHERE Type = N'" + type + "'" +  Constants.returnDBXMLRoot(Constants.PRODUCTS, Constants.PRODUCT),
							 resDetail = DBUtils.getDataFromDB(sql);
				System.out.println("res detail: " + resDetail);
				request.setAttribute("PRODUCT_DETAIL", resDetail);
				request.setAttribute("PRODUCT_RELATED", DBUtils.getDataFromDB(sql1));
			  request.getRequestDispatcher("productdetail.jsp").forward(request, response);
		} catch (Exception e) {
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
