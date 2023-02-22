package com.shop.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopprod.model.ShopProdService;
import com.shopprod.model.ShopProdVO;



@WebServlet("/shoppagesearch/shoppagesearch.do")
public class ShopPageSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("000");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("111");
		
// ******************模糊查詢*******************//
		if ("listProducts_ByKeyword".equals(action)) { // 來自shop-main.jsp的複合查詢請求

			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("222");
			/***************************1.接收請求參數****************************************/
			
			String str = req.getParameter("productName");
			System.out.println("str="+str);
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入商品名稱");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/shop/shop-main.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}		
			
			
			/***************************2.開始查詢資料****************************************/
			
			ShopProdService shopProdSvc = new ShopProdService();
			List<ShopProdVO> shopProdList = shopProdSvc.selectKeywordProductName(req.getParameter("productName"));	
			if(shopProdList ==null||shopProdList.size()==0) {
				errorMsgs.add("請輸入商品名稱");
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("shopProdList", shopProdList); // 資料庫取出的list物件,存入request
			System.out.println("4441");
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/shop/shop-search.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			System.out.println("555");
	}
		
//***********複合查詢**************//
//@WebServlet("/shoppagesearch/shoppagesearch.do")
//public class ShopPageSearchServlet extends HttpServlet {
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		System.out.println("000");
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		System.out.println("111");
//		// *******************複合查詢*******************//
//		if ("listProducts_ByCompositeQuery".equals(action)) { // 來自shop-main.jsp的複合查詢請求
//
//			String productNameString = req.getParameter("productName");
//			ShopProdService shopProdSvc = new ShopProdService();
//			List<ShopProdVO> shopProdList = shopProdSvc.getShopProdBySearch(productNameString);
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			System.out.println("222");
//			/*************************** 1.將輸入資料轉為Map **********************************/
//			// 採用Map<String,String[]> getParameterMap()的方法
//			// 注意:an immutable java.util.Map
//			Map<String, String[]> map = req.getParameterMap();
//			System.out.println("333");
//			/*************************** 2.開始複合查詢 ***************************************/
//
//			/***************************
//			 * 3.查詢完成,準備轉交(Send the Success view)listProducts_ByCompositeQuery做跟首頁一樣就好
//			 ************/

//		}
	}
}
