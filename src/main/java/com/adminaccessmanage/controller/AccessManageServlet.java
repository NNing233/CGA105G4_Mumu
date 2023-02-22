package com.adminaccessmanage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.AdminService;
import com.admin.model.AdminVO;
import com.adminaccessmanage.model.AdminAccessManageService;
import com.adminaccessmanage.model.AdminAccessManageVO;

@WebServlet("/AccessManageServlet")
public class AccessManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// <option name="交流區" value="open" ${(requestScope. !=null)? 'selected': ''}
		// >開啟<optin>
		// <option name="交流區" value="close" >關閉<optin>

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("ready_update".equals(action)) {
			Integer adminId = Integer.valueOf(req.getParameter("adminId"));

			AdminService adminSvc = new AdminService();
			AdminVO adminVO = adminSvc.getOneAdmin(adminId);

			AdminAccessManageService aamSvc = new AdminAccessManageService();
			Integer[] adminAccess = new Integer[7];

			for (AdminAccessManageVO aamVO : aamSvc.getOneAdmin(adminId)) {
				switch (aamVO.getAccessFunctionId()) {
				case 1:
					adminAccess[0] = aamVO.getAccessFunctionId();
					break;
				case 2:
					adminAccess[1] = aamVO.getAccessFunctionId();
					break;
				case 3:
					adminAccess[2] = aamVO.getAccessFunctionId();
					break;
				case 4:
					adminAccess[3] = aamVO.getAccessFunctionId();
					break;
				case 5:
					adminAccess[4] = aamVO.getAccessFunctionId();
					break;
				case 6:
					adminAccess[5] = aamVO.getAccessFunctionId();
					break;
				case 7:
					adminAccess[6] = aamVO.getAccessFunctionId();
					break;
				}
			}

			req.setAttribute("adminVO", adminVO);
			req.setAttribute("adminAccess", adminAccess);
			req.getRequestDispatcher("/back_end/adminaccessmanage/access_manage.jsp").forward(req, res);
		}

		if ("update".equals(action)) {
			Integer adminId = Integer.valueOf(req.getParameter("adminId"));

			AdminAccessManageService aamSvc = new AdminAccessManageService();
			Integer[] adminAccess = new Integer[7];

			for (AdminAccessManageVO aamVO : aamSvc.getOneAdmin(adminId)) {
				switch (aamVO.getAccessFunctionId()) {
				case 1:
					adminAccess[0] = aamVO.getAccessFunctionId();
					break;
				case 2:
					adminAccess[1] = aamVO.getAccessFunctionId();
					break;
				case 3:
					adminAccess[2] = aamVO.getAccessFunctionId();
					break;
				case 4:
					adminAccess[3] = aamVO.getAccessFunctionId();
					break;
				case 5:
					adminAccess[4] = aamVO.getAccessFunctionId();
					break;
				case 6:
					adminAccess[5] = aamVO.getAccessFunctionId();
					break;
				case 7:
					adminAccess[6] = aamVO.getAccessFunctionId();
					break;
				}
			}

			String oneForum = req.getParameter("oneForum");
			if (oneForum == null && adminAccess[0] != null) {// 刪除
				aamSvc.deleteOne(adminId, 1);
			} else if (oneForum != null && adminAccess[0] == null) {// 新增
				aamSvc.addAdminAccessManage(adminId, 1);
			}
			String towProposal = req.getParameter("towProposal");
			if (towProposal == null && adminAccess[1] != null) {
				aamSvc.deleteOne(adminId, 2);
			} else if (towProposal != null && adminAccess[1] == null) {
				aamSvc.addAdminAccessManage(adminId, 2);
			}
			String threeShop = req.getParameter("threeShop");
			if (threeShop == null && adminAccess[2] != null) {
				aamSvc.deleteOne(adminId, 3);
			} else if (threeShop != null && adminAccess[2] == null) {
				aamSvc.addAdminAccessManage(adminId, 3);
			}
			String fourReport = req.getParameter("fourReport");
			if (fourReport == null && adminAccess[3] != null) {
				aamSvc.deleteOne(adminId, 4);
			} else if (fourReport != null && adminAccess[3] == null) {
				aamSvc.addAdminAccessManage(adminId, 4);
			}
			String fiveMember = req.getParameter("fiveMember");
			if (fiveMember == null && adminAccess[4] != null) {
				aamSvc.deleteOne(adminId, 5);
			} else if (fiveMember != null && adminAccess[4] == null) {
				aamSvc.addAdminAccessManage(adminId, 5);
			}
			String sixMember = req.getParameter("sixMember");
			if (sixMember == null && adminAccess[5] != null) {
				aamSvc.deleteOne(adminId, 6);
			} else if (sixMember != null && adminAccess[5] == null) {
				aamSvc.addAdminAccessManage(adminId, 6);
			}
			String sevenAdmin = req.getParameter("sevenAdmin");
			if (sevenAdmin == null && adminAccess[6] != null) {
				aamSvc.deleteOne(adminId, 7);
			} else if (sevenAdmin != null && adminAccess[6] == null) {
				aamSvc.addAdminAccessManage(adminId, 7);
			}

			// 增刪完畢，找出修改後資料
			AdminService adminSvc = new AdminService();
			AdminVO adminVO = adminSvc.getOneAdmin(adminId);

			req.setAttribute("adminVO", adminVO);
			req.getRequestDispatcher("/back_end/adminaccessmanage/access_update_success.jsp").forward(req, res);

		}

	}
}
