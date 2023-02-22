package com.showProductImg.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopprod.model.ShopProdService;

/**
 * Servlet implementation class ShowProdImgServlet
 */
@WebServlet("/ShowProdImgServlet")
public class ShowProdImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/png");
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		try {
			ShopProdService shopProdSvc = new ShopProdService();
			InputStream is = shopProdSvc.getOneShopProd(productId).getProductPicture();
			BufferedImage bf = ImageIO.read(is);
			ImageIO.write(bf, "png", response.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("Show Default Img");
		}
		
		
	}

	

}
