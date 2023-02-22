package com.prop.controller.user;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prop.model.PropService;
import com.prop.model.PropVO;
import com.prop.model.ProposalStatus;
import com.prop.model.Status;
import com.propopt.model.PropOptService;
import com.propprodreview.model.PropProdReviewService;
import com.propreport.model.PropReportVO;
import com.sponrecord.model.SponRecordService;
import com.mem.model.MemService;
import com.mem.model.MemVO;

@MultipartConfig
public class PropServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public PropServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PropService propSvc = new PropService();
		PropOptService propOptSvc = new PropOptService();

		if ("showImg".equals(request.getParameter("action"))) {
			response.setContentType("image/png");
			byte[] imgBytes = propOptSvc.getOnePropOpt(Integer.parseInt(request.getParameter("proposalOptionId")))
					.getProposalOptionPicture();
			InputStream is = null;
			try {
				is = new ByteArrayInputStream(imgBytes);
				BufferedImage bf = ImageIO.read(is);
				ImageIO.write(bf, "png", response.getOutputStream());
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("show Default Img");
			}
		}
		
		else if("showPropImg".equals(request.getParameter("action"))) {
			response.setContentType("image/png");
			byte[] imgBytes = propSvc.getOneProp(Integer.parseInt(request.getParameter("proposalId"))).getProposalPicture();
			InputStream is = null;
			try {
				is = new ByteArrayInputStream(imgBytes);
				BufferedImage bf = ImageIO.read(is);
				ImageIO.write(bf, "png", response.getOutputStream());
			}catch (Exception e) {
				String imgPath = getServletContext().getRealPath("/back_end/images/noimage.png");
				FileInputStream fin = new FileInputStream(imgPath);
				BufferedImage bf = ImageIO.read(fin);
				ImageIO.write(bf, "png", response.getOutputStream());
//				System.out.println("show Default Img");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String query = request.getParameter("query");
		
		RequestDispatcher dispatcher = null;
		
		
		
		HttpSession session = request.getSession();

		
		if ("insert".equals(action)) { 
			System.out.println("12345");

			Integer memberId = ((MemVO)session.getAttribute("mem")).getMemberId();
		
			
			Integer proposalTypeId = Integer.valueOf(request.getParameter("propType").trim());
			
			String proposalName = request.getParameter("proposalName");

			Status proposalApprovalStatus = Status.from(2);
	
			ProposalStatus proposalStatus = ProposalStatus.from(1);

			String proposalArticle = request.getParameter("description").trim();

			
			byte[] proposalPicture = request.getPart("upload").getInputStream().readAllBytes();

			java.sql.Date proposalStartDatetime = null;
			proposalStartDatetime = java.sql.Date.valueOf(request.getParameter("from-date"));
			java.sql.Date proposalEndDatetime = null;
			proposalEndDatetime = java.sql.Date.valueOf(request.getParameter("to-date"));

			Integer targetDonateMoney = null;
			targetDonateMoney = Integer.valueOf(request.getParameter("target-amount").trim());

			Integer accumulativeDonateMoney = null;
			accumulativeDonateMoney = Integer.valueOf(0);
			
			Integer extendedTimes = Integer.valueOf(0);
			java.sql.Date productProduceTime = null;
			productProduceTime = java.sql.Date.valueOf(request.getParameter("target-product-date"));
			java.sql.Date targetDeliveryTime = null;
			targetDeliveryTime = java.sql.Date.valueOf(request.getParameter("target-delivery-date"));

			PropService propSvc = new PropService();
			
			PropVO propVO = propSvc.addProp(memberId, proposalTypeId, proposalName, proposalApprovalStatus,proposalStatus, proposalStartDatetime,proposalEndDatetime, targetDonateMoney, accumulativeDonateMoney, proposalArticle, proposalPicture, extendedTimes, productProduceTime, targetDeliveryTime  );
			
			
			
			List<PropVO> propList = propSvc.getAll();
			
			for (PropVO prop : propList) {
				PropVO lastProp = propList.get(propList.size() - 1);
				request.getSession().setAttribute("proposalId", lastProp.getProposalId());
			
			
			System.out.println(lastProp.getProposalId());}
			String url = "/front_end/prop/addProposalOption.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}

	
	
	PropVO propVO = new PropVO(); 
	PropService propSvc = new PropService();
	PropProdReviewService propRrodReviewSvc = new PropProdReviewService();
	PropOptService propOptSvc = new PropOptService();
	SponRecordService sponRecordSvs = new SponRecordService();
	List<PropVO> propList = new ArrayList<PropVO>();
	
	List<String> errorMsgs = new LinkedList<String>();
	// Store this set in the request scope, in case we need to
	// send the ErrorPage view.
	request.setAttribute("errorMsgs", errorMsgs);
	// 
	if ((query != null) && !(query.isEmpty())) {

		switch (query) {
		
		case "allQuery":
			propSvc.getOneProp(Integer.parseInt(request.getParameter("proposalId")));
		break;
		
		case "getAllOption":
		break;

		}
	}
	// 
	else if ((action != null) && !(action.isEmpty())) {
		
		switch (action) {
		case "updateFundingProposal":
			
			int proposalId = Integer.parseInt(request.getParameter("proposalId"));
			String proposalName = request.getParameter("proposalName");
			System.out.println(proposalName);
			int memberId = Integer.parseInt(request.getParameter("memberId"));
			int selectedProposalType = Integer.parseInt(request.getParameter("selectedProposalType"));
//			String selectedProposalType = request.getParameter("selectedProposalType");
			String proposalArticle = request.getParameter("proposalArticle");
			byte[] uploadProposalImg = request.getPart("uploadProposalImg").getInputStream().readAllBytes();
			int accumulativeDonateMoney = Integer.parseInt(request.getParameter("accumulativeDonateMoney"));
			int targetDonateMoney = Integer.parseInt(request.getParameter("targetDonateMoney"));
			int extendedDays = Integer.parseInt(request.getParameter("extendedDays"));
			int extendedTimes = Integer.parseInt(request.getParameter("extendedTimes"));
			Date proposalStartDatetime = java.sql.Date.valueOf(request.getParameter("proposalStartDatetime"));
			Date proposalEndDatetime = java.sql.Date.valueOf(request.getParameter("proposalEndDatetime"));
			Date productProduceTime = java.sql.Date.valueOf(request.getParameter("productProduceTime"));
			Date targetDeliveryTime = java.sql.Date.valueOf(request.getParameter("targetDeliveryTime"));
			
			int selectedProposalStatus = 0;
			if(request.getParameter("selectedProposalStatus") == null) {
				propSvc.updateFundingProposalWithoutProposalStatus(proposalId, proposalName, memberId, selectedProposalType, proposalArticle, uploadProposalImg, accumulativeDonateMoney, targetDonateMoney, extendedDays, extendedTimes, proposalStartDatetime, proposalEndDatetime, productProduceTime, targetDeliveryTime);
			}else if(request.getParameter("selectedProposalStatus") != null){
				selectedProposalStatus = Integer.parseInt(request.getParameter("selectedProposalStatus"));
				propSvc.updateFundingProposal(proposalId, proposalName, memberId, selectedProposalType, selectedProposalStatus, proposalArticle, uploadProposalImg, accumulativeDonateMoney, targetDonateMoney, extendedDays, extendedTimes, proposalStartDatetime, proposalEndDatetime, productProduceTime, targetDeliveryTime);
			}
			
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			dispatcher = request.getRequestDispatcher("/back_end/prop/proposal_mangement.jsp");
			dispatcher.forward(request, response);
			break;
			
		case "updateSnonRecordStatus":
//			System.out.println(request.getParameterValues("proposalOptionId"));
			System.out.println((Integer.parseInt(request.getParameter("updateSelected").trim())));
			String[] selectedCheckboxes = request.getParameterValues("cbox-" + request.getParameter("proposalOptionId"));
			   if (selectedCheckboxes != null) {
			      // 可以透過 for 迴圈來枚舉每個被選擇的 checkbox
			      for (String selectedCheckbox : selectedCheckboxes) {
			         System.out.println("Selected checkbox: " + selectedCheckbox);
			         try {
//							propSvc.updatePropsalStatus(Integer.parseInt(request.getParameter("proposalId")), Integer.parseInt(request.getParameter("updateSelected").trim()));
							
							if((Integer.parseInt(request.getParameter("updateSelected").trim())) == 4) {
								sponRecordSvs.updateTheSponRecordStatus(Integer.parseInt(selectedCheckbox), 9);
							}else if((Integer.parseInt(request.getParameter("updateSelected").trim())) == 5) {
								sponRecordSvs.updateTheSponRecordStatus(Integer.parseInt(selectedCheckbox), 3);
							}else if((Integer.parseInt(request.getParameter("updateSelected").trim())) == 6) {
								sponRecordSvs.updateTheSponRecordStatus(Integer.parseInt(selectedCheckbox), 7);
							}else if((Integer.parseInt(request.getParameter("updateSelected").trim())) == 7) {
								sponRecordSvs.updateTheSponRecordStatus(Integer.parseInt(selectedCheckbox), 5);
							}
					} catch (NumberFormatException e) {
								System.out.println("error");
							}
			        
			      }
			      dispatcher = request.getRequestDispatcher("/front_end/prop/addProposalToShop2.jsp"); //需修改為proposalRecords那個頁面
				dispatcher.forward(request, response);
			   }
//			      else {
//				   System.out.println("erroe");
//			   }
		break;
		
		case "updatePropStatus":
			try {
			propSvc.updatePropsalStatus(Integer.parseInt(request.getParameter("proposalId")), Integer.parseInt(request.getParameter("updateSelected").trim()));
			int test = Integer.parseInt(request.getParameter("proposalId"));
			System.out.println(test);
			if((Integer.parseInt(request.getParameter("updateSelected").trim())) == 4) {
				sponRecordSvs.updateSponRecordStatus(Integer.parseInt(request.getParameter("proposalId")), 9);
			}else if((Integer.parseInt(request.getParameter("updateSelected").trim())) == 5) {
				sponRecordSvs.updateSponRecordStatus(Integer.parseInt(request.getParameter("proposalId")), 3);
			}else if((Integer.parseInt(request.getParameter("updateSelected").trim())) == 6) {
				sponRecordSvs.updateSponRecordStatus(Integer.parseInt(request.getParameter("proposalId")), 7);
			}else if((Integer.parseInt(request.getParameter("updateSelected").trim())) == 7) {
				sponRecordSvs.updateSponRecordStatus(Integer.parseInt(request.getParameter("proposalId")), 5);
			}
			dispatcher = request.getRequestDispatcher("/front_end/prop/addProposalToShop2.jsp"); //需修改為proposalRecords那個頁面
			dispatcher.forward(request, response);
			} catch (NumberFormatException e) {
				System.out.println("XXX");
				dispatcher = request.getRequestDispatcher("/front_end/prop/addProposalToShop2.jsp"); //需修改為proposalRecords那個頁面
				dispatcher.forward(request, response);
			}
//			propSvc.updatePropsalStatus(Integer.parseInt(request.getParameter("proposalId")), Integer.parseInt(request.getParameter("updateSelected")));
			
			
			
			
		break;
		
		case "drop":
			try {
				
				propSvc.dropProp(Integer.parseInt(request.getParameter("proposalId")));
				
				request.setAttribute("del_status", "success");
				request.setCharacterEncoding("UTF-8");
				 dispatcher = request.getRequestDispatcher("/back_end/prop/proposal_mangement.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				request.setAttribute("del_status", "fail");
				request.setCharacterEncoding("UTF-8");
				 dispatcher = request.getRequestDispatcher("/back_end/prop/proposal_mangement.jsp");
				dispatcher.forward(request, response);
			}
			break;
			
			case "extend":
//				response.getWriter().append("This is extend");
//				int extendedDays = Integer.parseInt(request.getParameter("extendedDays"));
				proposalId = Integer.parseInt(request.getParameter("proposalId"));
				propSvc.extendedDays(proposalId);
				response.setContentType("text/html;charset=UTF-8");
				request.setCharacterEncoding("UTF-8");
				RequestDispatcher listAllDispatcher = request.getRequestDispatcher("/back_end/prop/proposal_mangement.jsp");
				listAllDispatcher.forward(request, response);

			break;
			case "confirmSubmit":
				MemService memSvc = new MemService();
				Integer test = Integer.parseInt(request.getParameter("memberId"));
				MemVO memVO = memSvc.getOneMem(test);
				
				int proposalId2 = Integer.parseInt(request.getParameter("proposalId"));
				propVO= propSvc.getOneProp(proposalId2);
//				String memberPassward = propVO.getMemVO().getMemberPassword();
//				String memberPassward = propVO.getMemVO().getMemberPassword();
				if(request.getParameter("confirmPassword").equals(memVO.getMemberPassword())) {
					if(request.getParameter("isAbandon") != null) {
						propSvc.dropProp(Integer.parseInt(request.getParameter("proposalId")));
					}
					
					else if(request.getParameter("extendedDays") != "") {
						propSvc.setExtendedDays(proposalId2, Integer.parseInt(request.getParameter("extendedDays")));
					}
					else {
						errorMsgs.add("選擇放棄提案 或 延長天數");
					}
				}else if(!(request.getParameter("confirmPassword").equals(memVO.getMemberPassword()))){
						errorMsgs.add("密碼錯誤，請重新輸入");
				}
				
				
				response.setContentType("text/html;charset=UTF-8");
				request.setCharacterEncoding("UTF-8");
				RequestDispatcher listAllDispatcher2 = request.getRequestDispatcher("/front_end/prop/funding_proposal_management.jsp");
				listAllDispatcher2.forward(request, response);
				

		}
	}

}
}
