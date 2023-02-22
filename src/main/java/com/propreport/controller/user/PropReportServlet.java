package com.propreport.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.propreport.model.Status;
import com.mem.model.MemVO;
import com.propreport.model.PropReportService;
import com.propreport.model.PropReportVO;


public class PropReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("送出申請".equals(action)) { 
			System.out.println("12345");

			Integer memberId = ((MemVO)req.getSession().getAttribute("mem")).getMemberId();

			Integer proposalId = Integer.valueOf(req.getParameter("proposalId").trim());

			
			Status reportStatus = Status.from(2);
	
			String reportCause = req.getParameter("reportCause");

			PropReportService propReportSvc = new PropReportService();
			
			PropReportVO propReportVO = propReportSvc.addPropReportU(memberId,proposalId, reportStatus,reportCause);

			System.out.println("abc");

			String url = "/front_end/prop/succeedAddProposalReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
}
}