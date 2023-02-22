package com.art.controller;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.art.model.ArtService;
import com.art.model.ArtVO;
import com.artpic.model.ArtPicService;

@MultipartConfig
public class ArtServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
        
		
		if ("getOne_article".equals(action)) { // 來自select_page.jsp??��?��??

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer articleId = Integer.valueOf(req.getParameter("articleId"));

			/*************************** 2.開始查詢資料 *****************************************/
			ArtService artSvc = new ArtService();
			ArtVO artVO = artSvc.getOneArticle(articleId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫取出的artVO物件,存入req
			
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/art/artDetails.jsp"); // 成功轉交
																											// listOneArticle.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer articleId = Integer.valueOf(req.getParameter("articleId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ArtService artSvc = new ArtService();
			ArtVO artVO = artSvc.getOneArticle(articleId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("artVO", artVO); // 資料庫取出的artVO物件,存入req

			RequestDispatcher successView = req.getRequestDispatcher("/front_end/art/update_art_input.jsp");// 成功轉交
																												// update_article_input.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleTypeId");
		
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入分類編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/art/article_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer articleTypeId = null;
			try {
				articleTypeId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("分類編號格式不正確");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/art/article_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArtService artSvc = new ArtService();
			ArtVO artVO = artSvc.getOneArticle(articleTypeId);
			if (artVO == null) {
				errorMsgs.add("查無此文章");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/art/article_select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫取出的artVO物件,存入req

			RequestDispatcher successView = req.getRequestDispatcher("/back_end/art/artDetails.jsp"); // 成功轉交
																											// listOneArticle.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_article_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.?��?��請�?��?�數 - 輸入?��式�?�錯誤�?��??
			 **********************/
			Integer articleId = Integer.valueOf(req.getParameter("articleId").trim());

			String articleTitle = req.getParameter("articleTitle");

			String articleContent = req.getParameter("articleContent").trim();

			Integer articleStatus = 2;//因發文狀態是2，不能null

			Integer articleTypeId = Integer.valueOf(req.getParameter("articleTypeId").trim());

			ArtVO artVO = new ArtVO();
			artVO.setArticleId(articleId);
			artVO.setArticleTitle(articleTitle);
			artVO.setArticleContent(articleContent);
			artVO.setArticleStatus(articleStatus);
			artVO.setArticleTypeId(articleTypeId);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artVO", artVO); // 含有輸入格式錯誤的artVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("front_end/art/update_art_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ArtService artSvc = new ArtService();
			artVO = artSvc.updateArticle(articleId, articleTitle, articleContent, articleStatus, articleTypeId);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/front_end/art/artManage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addArt.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String articleTitle = req.getParameter("articleTitle");

			String articleContent = req.getParameter("articleContent");

			Integer articleStatus = 2;//因發文狀態是2，不能null

			Integer articleTypeId = Integer.valueOf(req.getParameter("articleTypeId").trim());
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));

			ArtVO artVO = new ArtVO();
			artVO.setArticleTitle(articleTitle);
			artVO.setArticleContent(articleContent);
			artVO.setArticleStatus(articleStatus);
			artVO.setArticleTypeId(articleTypeId);
//			artVO.setMemberId(memberId);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artVO", artVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/art/addArt.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ArtService artSvc = new ArtService();
			artVO = artSvc.addArticle(articleTitle, articleContent, articleStatus, articleTypeId, memberId);
			
//			ArtPicService artPicSvc = new ArtPicService();
//			artPicVO = artPicSvc.addArtPic(articlePicture);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/art/art.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
		}
		if ("delete".equals(action)) { // 來自listAllArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer articleId = Integer.valueOf(req.getParameter("articleId"));

			/*************************** 2.開始刪除資料 ***************************************/
			ArtService ArtSvc = new ArtService();
			ArtSvc.deleteArticle(articleId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/art/artManage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
			
//					/***************************文章關鍵字查詢***********/
		if ("articlekey".equals(action)) { // 來自select_page.jsp??��?��??

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("article");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入關鍵字");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/art/art.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			String articleTitle = null;

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/art/art.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArtService artSvc = new ArtService();
			List<ArtVO> artList = artSvc.selectArticle(req.getParameter("article"));
			System.out.println("size = " + artList.size());
			if (artList == null || artList.size() == 0) {
				errorMsgs.add("查無文章");
			
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/art/art.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			
			
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artList", artList); // 資料庫取出的artVO物件,存入req
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/art/artsearch.jsp"); // 成功轉交
																											// listOneArticle.jsp
			successView.forward(req, res);
		}
		
		/*************************** 從文章分類搜尋文章**********************/
		
		if ("送出".equals(action)) { // 來自select_page.jsp??��?��??

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleTypeId");
			
			Integer articleTypeId = Integer.valueOf(str);
			System.out.println(articleTypeId);
			
			/*************************** 2.開始查詢資料 *****************************************/
			ArtService artSvc = new ArtService();
			List<ArtVO> artVO = artSvc.select(articleTypeId);
			if (artVO == null) {
				errorMsgs.add("查無此文章");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/art/arttypelist.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫取出的artVO物件,存入req
//				String url = "/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/art/listOneArticle.jsp"); // 成功轉交
																											// listOneArticle.jsp
			successView.forward(req, res);
		}
	}
	
	

}
