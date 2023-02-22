package com.uploadProduct.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.shopprod.model.ShopProdService;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(123);
		System.out.println(request.getParameter("action"));
		System.out.println(request.getParameter("productType"));
		
		
		if("addProduct".equals(request.getParameter("action"))) {
			
			int productTypeId =  Integer.parseInt(request.getParameter("productType"));
			String productName = request.getParameter("productName");
			String productDescription = request.getParameter("productDescription");
			
			Part filePart = request.getPart("productPicture");
			InputStream fileContent = filePart.getInputStream();
			
			ShopProdService shopProdSvc = new ShopProdService();
			
			shopProdSvc.addGeneralShopProd(productTypeId, productDescription, fileContent, productName);
			
			
			response.sendRedirect(request.getContextPath() + "/back_end/product/listAllProduct.jsp");

		}
	}

}
