package com.prop.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemVO;
import com.prop.model.PropService;
import com.prop.model.PropVO;
import com.prop.model.Status;

@MultipartConfig
public class PropReviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("1234");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("123456");
		if ("通過".equals(action)) {
			System.out.println("12345");
			PropService propSvc = new PropService();
			Integer proposalId = Integer.valueOf(req.getParameter("proposalId").trim());
			PropVO propVO = propSvc.getOneProp(proposalId);
			
			Status pass = Status.from(1);

			
			propVO = propSvc.updateStatus(proposalId,pass);

			req.setAttribute("propVO", propVO);
			String url = "/back_end/prop/proposalReview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			System.out.println("123");
		} else if ("不通過".equals(action)) {
			PropService propSvc = new PropService();

			Integer proposalId = Integer.valueOf(req.getParameter("proposalId").trim());
			Status fail = Status.from(0);
	

			PropVO propVO = new PropVO();
			propVO = propSvc.updateStatus(proposalId,fail);
			
			String url = "/back_end/prop/proposalReview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		
	}
}
