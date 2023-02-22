package com.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdminService;
import com.admin.model.AdminVO;

public class AdminLoginoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();

		if ("login".equals(action)) {

			String adminAccount = req.getParameter("adminAccount");
			String adminPassword = req.getParameter("adminPassword");

			AdminService adminSvc = new AdminService();
			AdminVO adminVO = adminSvc.findByAccount(adminAccount);

			if (adminVO == null) {// 帳號為null
				req.setAttribute("loginAccount_error", "帳號輸入錯誤或不存在！");
				req.getRequestDispatcher("/backend/adminlogin.jsp").forward(req, res);
				return;// 程式中斷

			} else if (!adminVO.getAdminPassword().equals(adminPassword)) {// 比對密碼false
				req.setAttribute("loginPassword_error", "密碼輸入錯誤！");
				req.getRequestDispatcher("/backend/adminlogin.jsp").forward(req, res);
				return;

			} else if(adminVO.getAdminAccountStatus() == 0) {
				req.setAttribute("adminVO", adminVO);
				req.getRequestDispatcher("/backend/admin_suspended_account.jsp").forward(req, res);
				return;
				
			} else {// 成功登入
					session.setAttribute("admin", adminVO);// 保存session
					res.sendRedirect(req.getContextPath()+"/back_end/index.jsp");
					
			}
			
		}

		if ("logout".equals(action)) {
			session.invalidate();
			res.sendRedirect(req.getContextPath()+"/backend/adminlogin.jsp");
		}
	}

}
