package com.deleteProdOpt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prodopt.model.ProdOptService;

/**
 * Servlet implementation class DeleteProdOptServlet
 */
@WebServlet("/DeleteProdOptServlet")
public class DeleteProdOptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProdOptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int productOptionId = Integer.parseInt(request.getParameter("productOptionId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		ProdOptService prodOptSvc = new ProdOptService();
		prodOptSvc.deleteProdOpt(productOptionId);
		
		response.sendRedirect(request.getContextPath() + "/back_end/product/addProductOption.jsp?productId="+productId);
	}

}
