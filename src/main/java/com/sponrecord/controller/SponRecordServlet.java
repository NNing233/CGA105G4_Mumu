package com.sponrecord.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.sponrecord2.model.SponRecordService;
import com.sponrecord2.model.SponRecordVO;

@WebServlet("/sponrecord/sponrecord.do")
public class SponRecordServlet extends HttpServlet {

	public SponRecordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession();
		
		if("getSponsorRecordByMember".equals(req.getParameter("action"))) {
			
				//產出贊助明細，並存入 req Attribute
				
				MemVO memVO = (MemVO)(session.getAttribute("mem"));
				SponRecordService sponRecordSvc = new SponRecordService();
				
				List<SponRecordVO> sponsorRecordList = sponRecordSvc.getSponsorRecordByMember(memVO.getMemberId());
				req.setAttribute("sponsorRecordList", sponsorRecordList);
				req.getRequestDispatcher("/front_end/mem/getSponsorRecordByMember.jsp").forward(req, resp);
						
			
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if ("createSponsorRecord".equals(req.getParameter("action"))) {
			
			MemVO memVO = (MemVO)(req.getSession(false).getAttribute("mem"));
					
			int proposalId = Integer.parseInt(req.getParameter("proposalId"));
			int proposalOptionId = Integer.parseInt(req.getParameter("proposalOptionId"));
			int proposalOptionPrice = Integer.parseInt(req.getParameter("proposalOptionPrice"));
			int proposalOptionQuantity = Integer.parseInt(req.getParameter("proposalOptionQuantity"));
			int sponsorTotalAmount = proposalOptionPrice * proposalOptionQuantity;
			
			int memberId = memVO.getMemberId();
			String address = req.getParameter("fullAddress");
			int payment = Integer.parseInt(req.getParameter("payment"));
			int delivery = Integer.parseInt(req.getParameter("delivery"));
			
			SponRecordService sponRecordSvc = new SponRecordService();
			sponRecordSvc.addSponRecord(memberId, 1, address, payment, delivery, sponsorTotalAmount, proposalOptionId, proposalOptionQuantity, proposalId);
			
			resp.sendRedirect(req.getContextPath() + "/sponrecord/sponrecord.do?action=getSponsorRecordByMember");
		} else if("modifySponsorRecordWithAddress".equals(req.getParameter("action"))) {
			
			int sponsorRecordId = Integer.parseInt(req.getParameter("sponsorRecordId"));
			String address = req.getParameter("address");
			
			SponRecordService sponRecordSvc = new SponRecordService();
			sponRecordSvc.modifySponsorRecordWithAddress(sponsorRecordId, address);
			req.setAttribute("modify", "success");
		
//			req.getRequestDispatcher("/sponrecord/sponrecord.do?action=getSponsorRecordByMember").forward(req, resp);
			resp.sendRedirect(req.getContextPath()+ "/sponrecord/sponrecord.do?action=getSponsorRecordByMember&modify=success");
			
		} else if("cancelSponsorRecord".equals(req.getParameter("action"))) {
			
			System.out.println("第一行");
			
			int sponsorRecordId = Integer.parseInt(req.getParameter("sponsorRecordId"));
			int sponsorTotalAmount = Integer.parseInt(req.getParameter("sponsorTotalAmount"));
			int proposalId = Integer.parseInt(req.getParameter("proposalId"));
			
			System.out.println("第2行");
			
			SponRecordService sponRecordSvc = new SponRecordService();
			sponRecordSvc.cancelSponsorRecord(sponsorRecordId, sponsorTotalAmount, proposalId);
			
			System.out.println("第3行");
			
			resp.sendRedirect(req.getContextPath()+ "/sponrecord/sponrecord.do?action=getSponsorRecordByMember&cancelSponsor=success");
		}

	}

}
