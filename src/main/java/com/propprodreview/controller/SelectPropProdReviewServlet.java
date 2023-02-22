package com.propprodreview.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemVO;
import com.prop.model.PropService;
import com.prop.model.PropVO;
import com.prop.model.ProposalStatus;
import com.prop.model.Status;

public class SelectPropProdReviewServlet  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("toShop");
		
		if ("申請上架".equals(action)) {
			
			System.out.println("12345");

			
			Integer proposalId = Integer.valueOf(req.getParameter("proposalId").trim());
			
			PropService propSvc = new PropService();
			
			
			PropVO propVO = propSvc.getOneProp(proposalId);
			
			req.getSession().setAttribute("propVO", propVO);
		
			
			String url = "/front_end/prop/selectProposalOption.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
	}
	
}
