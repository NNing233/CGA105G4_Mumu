package com.propcollection.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.propcollection.model.PropCollectionService;
import com.propcollection.model.PropCollectionVO;

@WebServlet("/propcollection/propcollection.do")
public class PropCollectionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if("MyPropCollection".equals(req.getParameter("action"))) {
			
			HttpSession session = req.getSession();
			
			if(session.getAttribute("mem") == null) {
				session.setAttribute("location", req.getContextPath()+ "/propcollection/propcollection.do?action=MyPropCollection");
				resp.sendRedirect(req.getContextPath() + "/front_end/login.jsp");
				
			} else {
				PropCollectionService propCollectionSvc = new PropCollectionService();
				MemVO memVO = (MemVO)session.getAttribute("mem");
				List<PropCollectionVO> propCollectionList = propCollectionSvc.getPropCollectionByMem(memVO.getMemberId());
				
				System.out.println(123);
				
				for(PropCollectionVO a: propCollectionList) {
					System.out.println(a.getProposalID());
				}
				
				System.out.println(propCollectionList.get(0).getProposalID());
				
				req.setAttribute("propCollectionList", propCollectionList);
				req.getRequestDispatcher("/front_end/mem/getPropCollection.jsp").forward(req, resp);
			}
			
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession();
		
		System.out.println("這是外面");
		System.out.println(req.getParameter("action"));

		if ("likeProposal".equals(req.getParameter("action"))) {

			if (session.getAttribute("mem") == null) {
				
				// 送登入頁
				resp.sendRedirect(req.getContextPath() + "/front_end/login.jsp");

			} else {
				int memberId = Integer.parseInt(req.getParameter("memberId"));
				int proposalId = Integer.parseInt(req.getParameter("proposalId"));

				PropCollectionService propCollectionSvc = new PropCollectionService();

				if (propCollectionSvc.getOnePropCollection(memberId, proposalId) != null) {

					propCollectionSvc.deletePropCollection(memberId, proposalId);
				} else {
					propCollectionSvc.addPropCollection(memberId, proposalId);
				}
			}

			resp.sendRedirect(req.getContextPath() + "/front_end/prop/index.jsp");
		} else if("deleteLikes".equals(req.getParameter("action"))) {
			
			System.out.println(123);
			
			int memberId = Integer.parseInt(req.getParameter("memberId"));
			int proposalId = Integer.parseInt(req.getParameter("proposalId"));
			System.out.println(456);
			
			PropCollectionService propCollectionSvc = new PropCollectionService();
			
			if (propCollectionSvc.getOnePropCollection(memberId, proposalId) != null) {

				propCollectionSvc.deletePropCollection(memberId, proposalId);
			}
		}

	}
}
