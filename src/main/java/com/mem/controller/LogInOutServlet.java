package com.mem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;

public class LogInOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		// res.getWriter().append("123");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		String location = (String) session.getAttribute("location");

		if ("login".equals(action)) {

			String memberAccount = req.getParameter("memberAccount");
			String memberPassword = req.getParameter("memberPassword");

			MemService memSvc = new MemService();
			MemVO memVO = memSvc.findByAccount(memberAccount);

			if (memVO == null) {// 帳號為null
				req.setAttribute("loginAccount_error", "帳號輸入錯誤或不存在！");
				req.getRequestDispatcher("/front_end/login.jsp").forward(req, res);
				return;// 程式中斷

			} else {
				if (!memVO.getMemberPassword().equals(memberPassword)) {// 比對密碼false
					req.setAttribute("loginPassword_error", "密碼輸入錯誤！");
					req.getRequestDispatcher("/front_end/login.jsp").forward(req, res);

				} else if(memVO.getMemberStatus() != 1) {
					req.setAttribute("memVO", memVO);
					req.getRequestDispatcher("/front_end/suspended_account.jsp").forward(req, res);
					return;
					
				} else {// 成功登入
					session.setAttribute("mem", memVO);// 保存session
					if (location == null) {
						res.sendRedirect(req.getContextPath()+"/front_end/prop/index.jsp");
					} else {
						res.sendRedirect(location);
					}

				}
			}
		}

		if ("logout".equals(action)) {
			session.invalidate();

			if (location == null) {
				res.sendRedirect(req.getContextPath()+"/front_end/prop/index.jsp");
			} else {
				res.sendRedirect(location);
			}
		}
	}

}
