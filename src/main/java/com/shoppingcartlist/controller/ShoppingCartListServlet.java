package com.shoppingcartlist.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prodopt.model.ProdOptService;
import com.prodopt.model.ProdOptVO;
import com.shoppingcartlist.model.ShoppingCartListService;
import com.shopprod.model.ShopProdService;

public class ShoppingCartListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("User browser action : " + action);
		
		// ============== 顯示單筆查詢資料
		
		if ("delOneProdFromShopCartList".equals(action)) { // 來自shop-cart.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer productOptionId = Integer.valueOf(req.getParameter("productOptionId"));
				
				/***************************2.開始刪除資料***************************************/
				System.out.println("刪除商品品項ID:" + productOptionId);
				ShoppingCartListService shopCartListSvc = new ShoppingCartListService();
				shopCartListSvc.deleteProdOpt(productOptionId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/shop/shop-cart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}

		// ============================

		if ("addOneProdToShopCartList".equals(action)) { // 來自shop-product-details.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

			Integer memberId = null;

			try {
				memberId = Integer.valueOf(req.getParameter("memberId"));
			} catch (Exception e) {
				System.out.println("會員ID為空值, 轉導登入頁 " +req.getContextPath() + "/front_end/login.jsp");
				res.sendRedirect(req.getContextPath() + "/front_end/login.jsp");
				return;
			}

			Integer productOptionId = Integer.valueOf(req.getParameter("productOptionId"));
//			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			Integer productOptionQuantity = Integer.valueOf(req.getParameter("productOptionQuantity"));

			/***************************2.開始新增資料***************************************/

			ShoppingCartListService shopCartListSvc = new ShoppingCartListService();
			shopCartListSvc.addProdOpt(productOptionId, memberId, productOptionQuantity);

			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			ProdOptService dao = new ProdOptService();
			ProdOptVO prodOptVO = dao.getOneProdOpt(productOptionId);
			Integer productId = prodOptVO.getProductId();

			String url = "/front_end/shop/productDetail.do?action=getOne_For_Display&productId=" + productId;
			System.out.println(url);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後導回原商品頁面的jsp
			successView.forward(req, res);
		}
	}

}
