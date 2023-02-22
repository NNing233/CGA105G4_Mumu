package com.proptype.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proptype.model.*;


@WebServlet("/proptype/proptype.do")
public class PropTypeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("propLayoutManage".equals(req.getParameter("action"))) {
			
			HttpSession session = req.getSession();
			if(session.getAttribute("admin") == null) {
				resp.sendRedirect(req.getContextPath()+"/backend/adminlogin.jsp");
			} else {
				PropTypeService propTypeSvc = new PropTypeService();
				
				List<PropTypeVO> propTypeList = propTypeSvc.getAll();
				req.setAttribute("propTypeList", propTypeList);
				
				req.getRequestDispatcher("/back_end/prop/propLayoutManage.jsp").forward(req, resp);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if("propTypeStatusChange".equals(req.getParameter("action"))) {
			String[] propTypeOn = req.getParameterValues("propType");
			String[] propTypeNames = req.getParameterValues("propTypeName");
			String[] propTypeIds = req.getParameterValues("propTypeId");
			
			PropTypeService propTypeSvc = new PropTypeService();
			List<PropTypeVO> propTypeList = propTypeSvc.getAll();
			
			for(PropTypeVO propTypeVO: propTypeList) {
				//先全部停用
				propTypeSvc.updateStatus(propTypeVO.getProposalTypeId(), 2);
			}
			
			for(String propType : propTypeOn) {
				propTypeSvc.updateStatus(Integer.parseInt(propType), 1);
			}
			
//			更新募資分類名稱
			for(int i = 0; i<propTypeIds.length; i++) {
				propTypeSvc.updatePropType(Integer.parseInt(propTypeIds[i]), propTypeNames[i]);
			}
			
			
			resp.sendRedirect(req.getContextPath() + "/proptype/proptype.do?action=propLayoutManage&change=success");
			
		} else if("addPropType".equals(req.getParameter("action"))) {
			PropTypeService propTypeSvc = new PropTypeService();
			propTypeSvc.addPropType(req.getParameter("propTypeName"));
			
			resp.sendRedirect(req.getContextPath() + "/proptype/proptype.do?action=propLayoutManage&add=success");
		}
	}
	
}
