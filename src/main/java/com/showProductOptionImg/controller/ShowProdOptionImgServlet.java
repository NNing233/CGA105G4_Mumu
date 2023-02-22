package com.showProductOptionImg.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prodopt.model.ProdOptService;
import com.shopprod.model.ShopProdService;

/**
 * Servlet implementation class ShowProdImgServlet
 */
@WebServlet("/ShowProdOptionImgServlet")
public class ShowProdOptionImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/png");
		int productOptionId = Integer.parseInt(request.getParameter("productOptionId"));
		
		try {
			ProdOptService prodOptSvc = new ProdOptService();
			InputStream is = prodOptSvc.getOneProdOpt(productOptionId).getProductOptionPicture();
			BufferedImage bf = ImageIO.read(is);
			ImageIO.write(bf, "png", response.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("Show Default Img");
		}
		
		
	}

	

}
