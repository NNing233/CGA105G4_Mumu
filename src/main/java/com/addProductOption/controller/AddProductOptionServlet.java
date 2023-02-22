package com.addProductOption.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.prodopt.model.ProdOptService;
import com.shopprod.model.ShopProdService;

/**
 * Servlet implementation class AddProductOptionServlet
 */
@WebServlet("/AddProductOptionServlet")
@MultipartConfig
public class AddProductOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductOptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if ("addProductOption".equals(request.getParameter("action"))) {
			int productId = Integer.parseInt(request.getParameter("productId"));

			String[] productOptionName = request.getParameterValues("productOptionName");
			String[] productOptionPriceString = request.getParameterValues("productOptionPrice");
			String[] productOptionInventoryString = request.getParameterValues("productOptionInventory");
			int[] productOptionPrice = new int[productOptionPriceString.length];
			int[] productOptionInventory = new int[productOptionInventoryString.length];

			for (int i = 0; i < productOptionPriceString.length; i++) {
				productOptionPrice[i] = Integer.parseInt(productOptionPriceString[i]);
				productOptionInventory[i] = Integer.parseInt(productOptionInventoryString[i]);
			}
			
			for (int i = 0; i < productOptionPriceString.length; i++) {
				Part filePart = request.getPart("productOptionPicture"+(i+1));
				InputStream fileContent = filePart.getInputStream();
				
				
				ProdOptService prodOptSvc = new ProdOptService();
				prodOptSvc.addProdOpt(productId, fileContent, productOptionName[i], productOptionPrice[i], productOptionInventory[i]);

			}

			response.sendRedirect(request.getContextPath() + "/back_end/product/addProductOption.jsp?productId="+productId);
		}
	}
}
