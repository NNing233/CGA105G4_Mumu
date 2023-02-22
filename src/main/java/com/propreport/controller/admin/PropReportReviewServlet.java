package com.propreport.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.propreport.model.PropReportService;
import com.propreport.model.PropReportVO;
import com.propreport.model.Status;
@MultipartConfig
public class PropReportReviewServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("1234");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
		if ("通過".equals(action)) {
			System.out.println("12345");
			PropReportService propReportSvc = new PropReportService();
			Integer proposalReportId = Integer.valueOf(req.getParameter("proposalReportId").trim());
			PropReportVO propReportVO = propReportSvc.getOnePeport(proposalReportId);
			Integer adminId = Integer.valueOf(1);
			String reportResult = req.getParameter("reportResult").trim();
			
			Status pass = Status.from(1);

			System.out.println("0000");
			propReportVO = propReportSvc.updateStatus(adminId,reportResult,pass,proposalReportId);

			req.setAttribute("propReportVO", propReportVO);
			String url = "/back_end/prop/propRerportMangement.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			System.out.println("123");
		} else if ("不通過".equals(action)) {
			PropReportService propReportSvc = new PropReportService();
			Integer proposalReportId = Integer.valueOf(req.getParameter("proposalReportId").trim());
			PropReportVO propReportVO = propReportSvc.getOnePeport(proposalReportId);
			Integer adminId = Integer.valueOf(1);
			String reportResult = req.getParameter("reportResult").trim();
			Status fail = Status.from(0);

			propReportVO = propReportSvc.updateStatus(adminId,reportResult,fail,proposalReportId);
			String url = "/back_end/prop/propRerportMangement.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		

	}
}
