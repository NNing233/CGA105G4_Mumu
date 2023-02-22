package com.arttype.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.arttype.model.*;

public class ArtTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				String str = req.getParameter("articleTypeId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章分類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/arttype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer articleTypeId = null;
				try {
					articleTypeId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("文章分類編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/arttype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ArtTypeService artTypeSvc = new ArtTypeService();
				ArtTypeVO artTypeVO = artTypeSvc.getOneArtType(articleTypeId);
				if (artTypeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/arttype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("artTypeVO", artTypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/arttype/listOneArtType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllArtType.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer articleTypeId = Integer.valueOf(req.getParameter("articleTypeId"));
				
				
				/***************************2.開始查詢資料****************************************/
				ArtTypeService artTypeSvc = new ArtTypeService();
				ArtTypeVO artTypeVO = artTypeSvc.getOneArtType(articleTypeId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("artTypeVO", artTypeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/arttype/update_artType_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer articleTypeId = Integer.valueOf(req.getParameter("articleTypeId").trim());
				
				String articleTypeName = req.getParameter("articleTypeName");
				String articleTypeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (articleTypeName == null || articleTypeName.trim().length() == 0) {
					errorMsgs.add("文章分類名稱: 請勿空白");
				} else if(!articleTypeName.trim().matches(articleTypeNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章分類名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

				ArtTypeVO artTypeVO = new ArtTypeVO();
				artTypeVO.setArticleTypeId(articleTypeId);
				artTypeVO.setArticleTypeName(articleTypeName);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("artTypeVO", artTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/arttype/update_artType_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ArtTypeService artTypeSvc = new ArtTypeService();
				artTypeVO = artTypeSvc.updateArtType(articleTypeId, articleTypeName);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("artTypeVO", artTypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/arttype/listOneArtType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String articleTypeName = req.getParameter("articleTypeName");
				String articleTypeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (articleTypeName == null || articleTypeName.trim().length() == 0) {
					errorMsgs.add("分類名稱: 請勿空白");
				} else if(!articleTypeName.trim().matches(articleTypeNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("分類名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				

				ArtTypeVO artTypeVO = new ArtTypeVO();
				artTypeVO.setArticleTypeName(articleTypeName);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("artTypeVO", artTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/arttype/addArtType.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ArtTypeService artTypeSvc = new ArtTypeService();
				artTypeVO = artTypeSvc.addArtType(articleTypeName);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/arttype/listAllArtType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer articleTypeId = Integer.valueOf(req.getParameter("articleTypeId"));
				
				/***************************2.開始刪除資料***************************************/
				ArtTypeService artTypeSvc = new ArtTypeService();
				artTypeSvc.deleteArtType(articleTypeId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/arttype/listAllArtType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
