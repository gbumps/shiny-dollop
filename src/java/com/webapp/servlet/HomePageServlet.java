/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.servlet;

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
//	private static final String SELECT = "SELECT TOP 10 p.ID, p.Name, p.Price, p.OldPrice, (SELECT ImageLink FROM tblProductImage WHERE p.ID = OfProductID " + Constants.returnDBXMLRoot("Images", "") + ") FROM tblProduct p";
	
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
//			String sqlComboBoy = SELECT + CONDITION + Constants.returnDBXMLRoot(Constants.PRODUCTS,Constants.PRODUCT),
//						 sqlComboGirl = SELECT + CONDITION + Constants.returnDBXMLRoot(Constants.PRODUCTS, Constants.PRODUCT),
//						 sqlNumberGirl = "SELECT COUNT(p.ID) FROM tblProduct p WHERE p.Sex = 0 AND p.Type IN (N)",
//						 sqlNumberBoy = "SELECT COUNT(p.ID) FROM tblProduct p WHERE p.Sex = 1 AND p.Type IN ()",		
			//DBUtils db = new DBUtils();
//			ArrayList boyPrepareStatement = new ArrayList();
//			boyPrepareStatement.add(1);
//			ArrayList girlPrepareStatement = new ArrayList();
//			girlPrepareStatement.add(0);
//			request.setAttribute("ItemShirtBoy", DBUtils.getDataFromDBWithCondition(returnSqlString("N'Áo'"), boyPrepareStatement));
//			request.setAttribute("ItemShirtGirl", DBUtils.getDataFromDBWithCondition(returnSqlString("N'Áo'"), girlPrepareStatement));
//			request.setAttribute("ItemPantsBoy", DBUtils.getDataFromDBWithCondition(returnSqlString("N'Quần'"), boyPrepareStatement));
//			request.setAttribute("ItemPantsGirl", DBUtils.getDataFromDBWithCondition(returnSqlString("N'Quần'"), girlPrepareStatement));
//			request.setAttribute("ItemComboBoy", DBUtils.getDataFromDBWithCondition(returnSqlString("N'Đồ', N'Bộ', N'Set'"), boyPrepareStatement));
//			request.setAttribute("ItemComboGirl", DBUtils.getDataFromDBWithCondition(returnSqlString("N'Đồ', N'Bộ', N'Set'"), girlPrepareStatement));
//			request.setAttribute("ItemDressGirl", DBUtils.getDataFromDBWithCondition(returnSqlString("N'Váy'"), girlPrepareStatement));
				request.setAttribute("HomepageResult", DBUtils.getDataFromDB(returnSqlString()));
//			request.setAttribute("NumberProductListBoyCombo", db.countProduct(sqlNumberGirl));
//			request.setAttribute("ProductListGirlCombo", db.getDataFromDBWithCondition(sqlComboGirl,girlPrepareStatement));
//			request.setAttribute("NumberProductListGirlCombo", db.countProduct(sqlNumberBoy));
			request.getRequestDispatcher("homepage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String returnSqlString() {
		return "SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT ImageLink FROM tblProductImage i WHERE p.ID = OfProductID FOR XML PATH (''), ROOT ('Images'), TYPE) AS Images FROM tblProduct p WHERE Type IN (N'Đồ', N'Bộ', N'Set') AND Sex = 0) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT ImageLink FROM tblProductImage i WHERE p.ID = OfProductID FOR XML PATH (''), ROOT ('Images'), TYPE) AS Images FROM tblProduct p WHERE Type IN (N'Đồ', N'Bộ', N'Set') AND Sex = 1) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT ImageLink FROM tblProductImage i WHERE p.ID = OfProductID FOR XML PATH (''), ROOT ('Images'), TYPE) AS Images FROM tblProduct p WHERE Type IN (N'Áo') AND Sex = 1) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT ImageLink FROM tblProductImage i WHERE p.ID = OfProductID FOR XML PATH (''), ROOT ('Images'), TYPE) AS Images FROM tblProduct p WHERE Type IN (N'Áo') AND Sex = 0) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT ImageLink FROM tblProductImage i WHERE p.ID = OfProductID FOR XML PATH (''), ROOT ('Images'), TYPE) AS Images FROM tblProduct p WHERE Type IN (N'Quần') AND Sex = 1) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT ImageLink FROM tblProductImage i WHERE p.ID = OfProductID FOR XML PATH (''), ROOT ('Images'), TYPE) AS Images FROM tblProduct p WHERE Type IN (N'Quần') AND Sex = 0) AS tbl(ID, Sex, Type, Images) " +
"UNION ALL " +
"SELECT * FROM (SELECT Top 1 ID, Sex, Type, (SELECT ImageLink FROM tblProductImage i WHERE p.ID = OfProductID FOR XML PATH (''), ROOT ('Images'), TYPE) AS Images FROM tblProduct p WHERE Type IN (N'Váy') AND Sex = 0) AS tbl(ID, Sex, Type, Images) FOR XML PATH ('Product'), ROOT ('Products'), TYPE";
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
