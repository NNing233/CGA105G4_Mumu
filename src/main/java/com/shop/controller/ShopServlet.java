package com.shop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopprod.model.ShopProdService;
import com.shopprod.model.ShopProdVO;

public class ShopServlet extends HttpServlet{

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
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("productId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("productId","請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer productId = null;
				try {
					productId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("productId","商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShopProdService shopProdSvc = new ShopProdService();
				ShopProdVO shopProdVO = shopProdSvc.getOneShopProd(productId);
				if (shopProdVO == null) {
					errorMsgs.put("productId","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopProdVO", shopProdVO); // 資料庫取出的shopProdVO物件,存入req
				String url = "/shop/listOneShopProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneShopProd.jsp
				successView.forward(req, res);
		}
		
		
		// ============== 從listAllShopProd.jsp 更新單筆資料
		if ("getOne_For_Update".equals(action)) { // 來自listAllShopProd.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer productId = Integer.valueOf(req.getParameter("productId"));
				
				/***************************2.開始查詢資料****************************************/
				ShopProdService shopProdSvc = new ShopProdService();
				ShopProdVO shopProdVO = shopProdSvc.getOneShopProd(productId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				
				String param =  "?productId="            +shopProdVO.getProductId()+
								"&productName="          +shopProdVO.getProductName()+
								"&productTypeId="        +shopProdVO.getProductTypeId()+
								"&productStatus="        +shopProdVO.getProductStatus()+
								"&proposalId="           +shopProdVO.getProposalId()+
								"&productPictureString=" +shopProdVO.getProductPictureString()+
								"&productDescription="   +shopProdVO.getProductDescription();
				
				String url = "/shop/update_shopprod_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_shopprod_input.jsp
				successView.forward(req, res);
		}
		
		// ============== 從 update_shopprod_input 來的update請求
		if ("update".equals(action)) { // 來自update_shopprod_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//=============== productId
				String productIdStr = req.getParameter("productId");
				String productIdReg = "^\\d{1,15}$";
				if (productIdStr == null || productIdStr.trim().length() == 0) {
					errorMsgs.put("productId","商品編號: 請勿空白");
				} else if(!productIdStr.trim().matches(productIdReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("productId","請輸入長度在1到15之間的商品名稱");
				}
				Integer productId = Integer.valueOf(productIdStr);
				
	//			Integer productId = Integer.valueOf(req.getParameter("productId").trim());
				
				//=============== productName
				String productName = req.getParameter("productName");
	//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				String productNameReg = "^.{1,50}$";
				if (productName == null || productName.trim().length() == 0) {
					errorMsgs.put("productName","商品名稱: 請勿空白");
				} else if(!productName.trim().matches(productNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("productName","請輸入長度在1到50之間的商品名稱");
				}
				
				
				//=============== productTypeId
				String productTypeIdStr = req.getParameter("productTypeId").trim();
				String productTypeIdReg = "^[0-9]$";
				if (productTypeIdStr == null || productTypeIdStr.trim().length() == 0) {
					errorMsgs.put("productTypeIdStr","商品類別ID請勿空白");
				}else if(!productTypeIdStr.trim().matches(productTypeIdReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("productTypeIdStr","請輸入1~99之間的商品類別編號");
				}
				Integer productTypeId = Integer.valueOf(productTypeIdStr);
				
				//=============== productStatus
				
				String productStatusStr = req.getParameter("productStatus").trim();
				String productStatusReg = "^(1|2)$";
				if(productStatusStr == null || productStatusStr.trim().length() == 0) {
					errorMsgs.put("productStatus", "商品狀態請勿空白");
				}else if(!productStatusStr.trim().matches(productStatusReg)){
					errorMsgs.put("productStatus", "請輸入正確的產品狀態(1上架, 2下架)");
				}
				Integer productStatus = Integer.valueOf(productStatusStr);
				
				//=============== proposalId
				
				String proposalIdStr = req.getParameter("proposalId").trim();
				String proposalIdReg = "^\\d{0,30}$";
				Integer proposalId = null;
				if(proposalIdStr == null || proposalIdStr.trim().length() == 0) {
					System.out.println("使用者輸入的提案案號為空白, proposalId塞null");
				}else if(!proposalIdStr.trim().matches(proposalIdReg)){
					errorMsgs.put("proposalIdStr", "提案案號, 請留空或輸入數字 ; 空值=平台方創建的商品, 非空值=提案轉商城之商品)");
				}else {
					proposalId = Integer.valueOf(proposalIdStr);
				}
				
				//=============== productDescription
	
				String productDescription = req.getParameter("productDescription").trim();
				String productDescriptionReg = "^.{1,2000}$";
				if(productDescription == null || productDescription.trim().length() == 0) {
					System.out.println("使用者輸入的商品敘述為空白");
				}else if(!productDescription.trim().matches(productDescriptionReg)){
					errorMsgs.put("productDescription", "商品敘述不得高於2000個字");
				}
				
				//=============== productPicture 還沒寫
				
				InputStream productPicture = null;
	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/addShopProd.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ShopProdService shopProdSvc = new ShopProdService();
//				ShopProdVO shopProdVO = shopProdSvc.updateShopProd(productId);
//				ShopProdVO shopProdVO = shopProdSvc.updateShopProd(productId, productTypeId, proposalId, productDescription, productPicture, productName, productStatus);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("shopProdVO", shopProdVO); // 資料庫update成功後,正確的的shopProdVO物件,存入req
//				String url = "/shop/listOneShopProd.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneShopProd.jsp
//				successView.forward(req, res);
		}

		
		// ============== 從 addShopProd.jsp 來的insert請求

        if ("insert".equals(action)) { // 來自addShopProd.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				//=============== productId
				String productIdStr = req.getParameter("productId");
				String productIdReg = "^\\d{1,15}$";
				if (productIdStr == null || productIdStr.trim().length() == 0) {
					errorMsgs.put("productId","商品編號: 請勿空白");
				} else if(!productIdStr.trim().matches(productIdReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("productId","請輸入長度在1到15之間的商品名稱");
				}
				Integer productId = Integer.valueOf(productIdStr);
				
//				Integer productId = Integer.valueOf(req.getParameter("productId").trim());
				
				//=============== productName
				String productName = req.getParameter("productName");
	//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				String productNameReg = "^.{1,50}$";
				if (productName == null || productName.trim().length() == 0) {
					errorMsgs.put("productName","商品名稱: 請勿空白");
				} else if(!productName.trim().matches(productNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("productName","請輸入長度在1到50之間的商品名稱");
				}
				
				
				//=============== productTypeId
				String productTypeIdStr = req.getParameter("productTypeId").trim();
				String productTypeIdReg = "^[0-9]$";
				if (productTypeIdStr == null || productTypeIdStr.trim().length() == 0) {
					errorMsgs.put("productTypeIdStr","商品類別ID請勿空白");
				}else if(!productTypeIdStr.trim().matches(productTypeIdReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("productTypeIdStr","請輸入1~99之間的商品類別編號");
				}
				Integer productTypeId = Integer.valueOf(productTypeIdStr);
				
				//=============== productStatus
				
				String productStatusStr = req.getParameter("productStatus").trim();
				String productStatusReg = "^(1|2)$";
				if(productStatusStr == null || productStatusStr.trim().length() == 0) {
					errorMsgs.put("productStatus", "商品狀態請勿空白");
				}else if(!productStatusStr.trim().matches(productStatusReg)){
					errorMsgs.put("productStatus", "請輸入正確的產品狀態(1上架, 2下架)");
				}
				Integer productStatus = Integer.valueOf(productStatusStr);
				
				//=============== proposalId
				
				String proposalIdStr = req.getParameter("proposalId").trim();
				String proposalIdReg = "^\\d{0,30}$";
				Integer proposalId = null;
				if(proposalIdStr == null || proposalIdStr.trim().length() == 0) {
					System.out.println("使用者輸入的提案案號為空白, proposalId塞null");
				}else if(!proposalIdStr.trim().matches(proposalIdReg)){
					errorMsgs.put("proposalIdStr", "提案案號, 請留空或輸入數字 ; 空值=平台方創建的商品, 非空值=提案轉商城之商品)");
				}else {
					proposalId = Integer.valueOf(proposalIdStr);
				}
				
				//=============== productDescription
	
				String productDescription = req.getParameter("productDescription").trim();
				String productDescriptionReg = "^.{1,2000}$";
				if(productDescription == null || productDescription.trim().length() == 0) {
					System.out.println("使用者輸入的商品敘述為空白");
				}else if(!productDescription.trim().matches(productDescriptionReg)){
					errorMsgs.put("productDescription", "商品敘述不得高於2000個字");
				}
				
				//=============== productPicture 還沒寫
				
				InputStream productPicture = null;
	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/addShopProd.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				ShopProdService shopProdSvc = new ShopProdService();
				shopProdSvc.addShopProd(productId, productTypeId, proposalId, productDescription, productPicture, productName, productStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/shop/listAllShopProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listShopProd.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer productId = Integer.valueOf(req.getParameter("productId"));
				
				/***************************2.開始刪除資料***************************************/
				ShopProdService shopProdSvc = new ShopProdService();
				shopProdSvc.deleteShopProd(productId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/shop/listShopProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
	
}
