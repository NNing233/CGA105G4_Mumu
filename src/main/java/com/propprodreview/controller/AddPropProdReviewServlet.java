package com.propprodreview.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.propopt.model.PropOptService;
import com.propopt.model.PropOptVO;
import com.propprodreview.model.PropProdReviewService;
import com.propprodreview.model.PropProdReviewVO;
import com.propprodreview.model.Status;

@MultipartConfig

public class AddPropProdReviewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("add");

		PropProdReviewService PropProdReviewSvc = new PropProdReviewService();

		PropOptService PropOptSvc = new PropOptService();

		if ("送出申請".equals(action)) {

			

			Integer proposalId = Integer.valueOf(req.getParameter("proposalId").trim());

			PropProdReviewVO propProdReveiwVO = PropProdReviewSvc.addNew(proposalId, Status.待審核);
			
			System.out.println("value:" + propProdReveiwVO.getReviewProductId());

			String[] proposalOptionIds = req.getParameterValues("options");

			for (String opt : proposalOptionIds) {

				int propOptId = Integer.parseInt(opt);
				PropOptSvc.updateReview(propProdReveiwVO.getReviewProductId(), propOptId);
			}
			
			String url = "/front_end/prop/succeedAddToShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

		}

	}

}
