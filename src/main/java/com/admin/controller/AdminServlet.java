package com.admin.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdminService;
import com.admin.model.AdminVO;


public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("edit".equals(action)) {
			Integer adminId = Integer.valueOf(req.getParameter("adminId"));
			
			AdminService adminSvc = new AdminService();
			AdminVO adminVO = adminSvc.getOneAdmin(adminId);
//			byte adminAccountStatus = adminVO.getAdminAccountStatus();
			
			req.setAttribute("adminVO", adminVO);
			req.getRequestDispatcher("/back_end/admin/edit_admin_satus.jsp").forward(req, res);
		}
		
		if("save".equals(action)) {
			Integer adminId = Integer.valueOf(req.getParameter("adminId"));
			String adminAccountStatus = req.getParameter("status");
			AdminService adminSvc = new AdminService();

			if(adminAccountStatus!=null) {//狀態1
				adminSvc.updateAdminStatus(adminId, (byte) 1);
			}else {//狀態0
				adminSvc.updateAdminStatus(adminId, (byte) 0);
			}
			
			AdminVO adminVO = adminSvc.getOneAdmin(adminId);
			req.setAttribute("adminVO", adminVO);
			req.getRequestDispatcher("/back_end/admin/success_admin_satus.jsp").forward(req, res);

		}
		
		if("add_admin".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			AdminService adminSvc = new AdminService();

			String adminAccount = req.getParameter("adminAccount");
			String accountReg = "^[(a-zA-Z0-9_)]{6,20}$";
			if (adminAccount == null || adminAccount.length() == 0) {
				errorMsgs.put("adminAccount", "請填寫帳號");
			} else if (!adminAccount.trim().matches(accountReg)) {
				errorMsgs.put("adminAccount", "請使用英文、數字和底線_，且長度在6到20之間");
			} else if (adminSvc.findByAccount(adminAccount) != null) {// 帳號是否註冊過
				errorMsgs.put("adminAccount", "此帳號已經被註冊，請重新填寫");
			}

			String adminPassword = req.getParameter("adminPassword1");
			String passwordReg = "^[(a-zA-Z0-9)]{6,20}$";
			if (adminPassword == null || adminPassword.length() == 0) {
				errorMsgs.put("memberPassword", "請填寫密碼");
			} else if (!adminPassword.trim().matches(passwordReg)) {
				errorMsgs.put("memberPassword", "請使用英文和數字，且長度在6到20之間");
			}
			String adminPassword2 = req.getParameter("adminPassword2");
			if (adminPassword2 == null || adminPassword2.length() == 0) {
				errorMsgs.put("memberPassword2", "請填寫確認密碼");
			} else if (!adminPassword2.equals(adminPassword)) {// 與密碼比對
				errorMsgs.put("memberPassword2", "必須與密碼相同");
			}

			String adminAccountName = req.getParameter("adminAccountName");
			if (adminAccountName == null || adminAccountName.trim().length() == 0) {
				adminAccountName = adminAccount;
			} else if (!(2 <= adminAccountName.trim().length() && adminAccountName.trim().length() <= 20)) {
				errorMsgs.put("adminAccountName", "長度在2到20之間");
			}
			
			if(!errorMsgs.isEmpty()) {
				req.getRequestDispatcher("/back_end/admin/add_admin.jsp").forward(req, res);
				return;//程式中斷
			}
			
			//預設
			byte adminAccountStatus = 1;
			
			adminSvc.addAdmin(adminAccount, adminPassword, adminAccountStatus, adminAccountName);
			AdminVO adminVO = adminSvc.findByAccount(adminAccount);
			System.out.println(adminVO==null);
			req.setAttribute("adminVO", adminVO);
			req.getRequestDispatcher("/back_end/admin/add_success.jsp").forward(req, res);
		}
		
		if("edit_personal_pw".equals(action)) {
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer adminId = Integer.valueOf(req.getParameter("adminId"));
			AdminService adminSvc = new AdminService();
			AdminVO adminVO = adminSvc.getOneAdmin(adminId);
			
			String adminPassword = req.getParameter("adminPassword");
			if(adminPassword == null || adminPassword.length() == 0) {
				errorMsgs.put("adminPassword", "請輸入密碼。");
			}else if(!adminPassword.equals(adminVO.getAdminPassword())){
				errorMsgs.put("adminPassword", "密碼不正確。");				
			}
			
			String adminPassword1 = req.getParameter("adminPassword1");
			String passwordReg = "^[(a-zA-Z0-9)]{6,20}$";
			if(adminPassword1 == null || adminPassword1.length() == 0) {
				errorMsgs.put("adminPassword1", "請輸入新密碼。");
			}else if (!adminPassword1.trim().matches(passwordReg)) {
				errorMsgs.put("adminPassword1", "請使用英文和數字，且長度在6到20之間。");
			}
			
			String adminPassword2 = req.getParameter("adminPassword2");
			if (adminPassword2 == null || adminPassword2.length() == 0) {
				errorMsgs.put("adminPassword2", "確認新密碼。");
			} else if (!adminPassword2.equals(adminPassword1)) {
				errorMsgs.put("adminPassword2", "兩次新密碼必須相同。");
			}
			
			if(!errorMsgs.isEmpty()) {
				req.getRequestDispatcher("/back_end/admin/edit_personal_pw.jsp").forward(req, res);
				return; 
			}
			
			adminVO = adminSvc.updateAdminPassword(adminId, adminPassword1);
			if(!adminPassword1.equals(adminVO.getAdminPassword())) {
				req.setAttribute("error_msg", "密碼修改失敗!");
				req.getRequestDispatcher("/back_end/admin/edit_personal_pw.jsp").forward(req, res);
				return; 
			}else {
				HttpSession session = req.getSession();
				session.setAttribute("admin", adminVO);//更新登入資訊
				
				req.setAttribute("success_msg", "您的密碼修改成功!");
				req.getRequestDispatcher("/back_end/admin/edit_personal_pw.jsp").forward(req, res);
			}
			
			
		}
		
		if("edite_personal_name".equals(action)) {
			Integer adminId = Integer.valueOf(req.getParameter("adminId"));
			String adminAccountName = req.getParameter("adminAccountName");
			if (adminAccountName == null || adminAccountName.trim().length() == 0) {
				req.setAttribute("adminAccountName_error", "請填寫名稱");
				req.getRequestDispatcher("/back_end/admin/edit_personal_name.jsp").forward(req, res);
				return;
			} else if (!(2 <= adminAccountName.trim().length() && adminAccountName.trim().length() <= 20)) {
				req.setAttribute("adminAccountName_error", " 長度在2到20之間");
				req.getRequestDispatcher("/back_end/admin/edit_personal_name.jsp").forward(req, res);
				return;
			}
			
			AdminService adminSvc = new AdminService();
			adminSvc.updateAdminAccountName(adminId, adminAccountName);
			AdminVO adminVO = adminSvc.getOneAdmin(adminId);
			
			HttpSession session = req.getSession();
			session.setAttribute("admin", adminVO);//更新登入資訊
			
			req.setAttribute("success_msg", "您已經修改名稱成功!");
			req.getRequestDispatcher("/back_end/admin/edit_personal_name.jsp").forward(req, res);
			
		}
		
		
	}

}
