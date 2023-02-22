package com.artcarousel.controller;

import java.io.*;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.artcarousel.model.ArtCarouselService;
import com.artcarousel.model.ArtCarouselVO;


@MultipartConfig
public class ArtCarouselServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String str = req.getParameter("articleCarouselId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入輪播圖編號");

			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/artcarousel/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			Integer articleCarouselId = null;
			try {
				articleCarouselId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("輪播圖編號不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/artcarousel/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArtCarouselService artCarouselSvc = new ArtCarouselService();
			ArtCarouselVO artCarouselVO = artCarouselSvc.getOneArtCarousel(articleCarouselId);
			if(artCarouselVO == null) {
				errorMsgs.add("查無資料");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/artcarousel/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/	
			req.setAttribute("artCarouselVO", artCarouselVO);
			String url = "/back_end/artcarousel/listOneCarouse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
			if("getOne_For_Update".equals(action)) {
				List<String>errorMag = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMag);
				
				/***************************1.接收請求參數****************************************/
				Integer articleCarouselId = Integer.valueOf(req.getParameter("articleCarouselId"));
				/***************************2.開始查詢資料****************************************/
				ArtCarouselService artCarouselSvc = new ArtCarouselService();
				ArtCarouselVO artCarouselVO = artCarouselSvc.getOneArtCarousel(articleCarouselId);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("artCarouselVO", artCarouselVO);
				String url = "/back_end/updateArtCarouselInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			
			if("update".equals(action)) {
				List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			   ArtCarouselService artCarouselSvc = new ArtCarouselService();
			     
			    Integer articleCarouselId = Integer.valueOf(req.getParameter("articleCarouselId").trim());
				
				Integer articleCarouselPosition = Integer.valueOf(req.getParameter("articleCarouselPosition").trim());
				
				Integer articleTypeId = Integer.valueOf(req.getParameter("articleTypeId").trim());
				
				
				String articleLink = req.getParameter("articleLink");
//				
				
				ArtCarouselVO artCarouselVO = new ArtCarouselVO();
				artCarouselVO.setArticleCarouselId(articleCarouselId);
				artCarouselVO.setArticleCarouselPosition(articleCarouselPosition);
				artCarouselVO.setArticleTypeId(articleTypeId);
				artCarouselVO.setArticleCarouselPicture(null);
				artCarouselVO.setArticleLink(articleLink);
				
				byte[]articleCarouselPicture = req.getPart("upload").getInputStream().readAllBytes();
				if(articleCarouselPicture.length == 0) {
					artCarouselVO = artCarouselSvc.getOneArtCarousel(articleCarouselId);
					articleCarouselPicture = artCarouselVO.getArticleCarouselPicture();
				}
				
				
				
				/***************************2.開始修改資料*****************************************/	
				
				artCarouselVO = artCarouselSvc.updateArtCarousel(articleCarouselId, articleCarouselPosition, articleTypeId,articleCarouselPicture,articleLink);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("artCarouselVO", artCarouselVO);
				String url = "/back_end/listArtCarousel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			
			if("insert".equals(action)) {
				List<String>errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer articleCarouselPosition =null;
				try {
					articleCarouselPosition = Integer.valueOf(req.getParameter("articleCarouselPosition").trim());
				} catch (NumberFormatException e) {
					articleCarouselPosition = 0;
					errorMsgs.add("請填入順序(數字)");
				}
				
				Integer articleTypeId =null;

				try {

					articleTypeId = Integer.valueOf(req.getParameter("articleTypeId").trim());
				} catch (NumberFormatException e) {
					articleTypeId = 0;
					errorMsgs.add("請填入類別(數字)");
				}
				
				String articleLink = req.getParameter("articleLink");
				String articleLinkReg ="^[(a-zA-Z0-9.)]{1,100}$";
				if(articleLink == null || articleLink.trim().length()==0){
					errorMsgs.add("網址:請勿空白");
					}else if(!articleLink.trim().matches(articleLinkReg)) {
						errorMsgs.add("網址:只能是英文字母、數字，且長度必需再1~100之間");
					}
				
				byte[] buf = req.getPart("upfile1").getInputStream().readAllBytes();
				
//				Integer articleCarouselId = Integer.valueOf(req.getParameter("articleCarouselId").trim());
				
				ArtCarouselVO artCarouselVO = new ArtCarouselVO();
				artCarouselVO.setArticleCarouselPosition(articleCarouselPosition);
				artCarouselVO.setArticleTypeId(articleTypeId);
				artCarouselVO.setArticleLink(articleLink);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("artCarouselVO", artCarouselVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/addArtCarousel.jsp");
					failureView.forward(req,res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ArtCarouselService artCarouselSrc = new ArtCarouselService();
				artCarouselVO = artCarouselSrc.addArtCarousel(articleCarouselPosition, articleTypeId, buf, articleLink);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/listArtCarousel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			if ("delete".equals(action)) {
				List<String>errorMags = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMags);
				
				/***************************1.接收請求參數***************************************/
				
				Integer articleCarouselId = Integer.valueOf(req.getParameter("articleCarouselId"));
				/***************************2.開始刪除資料***************************************/
				ArtCarouselService artCarouselSvc = new ArtCarouselService();
				artCarouselSvc.deleteArtCarousel(articleCarouselId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/listArtCarousel.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}		
	}
}
