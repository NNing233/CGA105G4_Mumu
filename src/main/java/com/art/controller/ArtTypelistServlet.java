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
import com.arttype.model.ArtTypeService;
import com.arttype.model.ArtTypeVO;



    public class ArtTypelistServlet extends HttpServlet {
	
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//	doPost(req, res);
    	
    	if("getArtlist".equals(req.getParameter("action"))) {
			
			System.out.println("123");
			
			Integer articleTypeId = Integer.valueOf(req.getParameter("articleTypeId").trim());
			
			ArtTypeService artsvc = new ArtTypeService();
			
			ArtTypeVO artTypeVO = artsvc.getOneArtType(articleTypeId);
			
			req.getSession().setAttribute("artTypeVO",artTypeVO);
			
			String url = "/front_end/art/arttypelist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,res);}
    	
    	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("送出".equals(action)) {
			
			System.out.println("123");
			
			Integer articleTypeId = Integer.valueOf(req.getParameter("articleTypeId").trim());
			
			ArtTypeService artsvc = new ArtTypeService();
			
			ArtTypeVO artTypeVO = artsvc.getOneArtType(articleTypeId);
			
			req.getSession().setAttribute("artTypeVO",artTypeVO);
			
			
			String url = "/back_end/art/arttypelist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,res);
					
		}
		
	}
    }


