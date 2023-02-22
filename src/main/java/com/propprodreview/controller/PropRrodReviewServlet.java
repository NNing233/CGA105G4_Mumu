package com.propprodreview.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prop.model.PropService;
import com.prop.model.PropVO;
import com.propopt.model.PropOptService;
import com.propprodreview.model.PropProdReviewService;


/**
 * Servlet implementation class PropOptServlet
 */


public class PropRrodReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropRrodReviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PropOptService propOptSvc = new PropOptService();

		if ("showImg".equals(request.getParameter("action"))) {
			response.setContentType("image/png");
			byte[] imgBytes = propOptSvc.getOnePropOpt(Integer.parseInt(request.getParameter("proposalOptionId")))
					.getProposalOptionPicture();
			InputStream is = null;
			try {
				is = new ByteArrayInputStream(imgBytes);
				BufferedImage bf = ImageIO.read(is);
				ImageIO.write(bf, "png", response.getOutputStream());
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("show Default Img");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String query = request.getParameter("query");
		String action = request.getParameter("action");
	
		PropProdReviewService propRrodReviewSvc = new PropProdReviewService();
	
		List<String> errorMsgs = new LinkedList<String>();

		request.setAttribute("errorMsgs", errorMsgs);
		// 
		if ((query != null) && !(query.isEmpty())) {

		}
		// 
		else if ((action != null) && !(action.isEmpty())) {

			switch (action) {
			
			case "toShop":
				
					propRrodReviewSvc.setPropProdReviewByProposalId(Integer.parseInt(request.getParameter("proposalId")));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/front_end/prop/addProposalToShop.jsp"); //需修改為proposalRecords那個頁面
					dispatcher.forward(request, response);
					return;
			
			
			
			
			}
		}
		

		
		String action2 = request.getParameter("toShop");
		
		if ("申請上架".equals(action2)) {
			
			System.out.println("12345");

			
			Integer proposalId = Integer.valueOf(request.getParameter("proposalId").trim());
			
			PropService propSvc = new PropService();
			
			
			PropVO propVO = propSvc.getOneProp(proposalId);
			
			request.getSession().setAttribute("propVO", propVO);
		
			
			String url = "/front_end/prop/selectProposalOption.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
			return;
		}
		
	}

	
}
