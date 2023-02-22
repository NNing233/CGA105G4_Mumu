package com.propopt.controller.user;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.propopt.model.PropOptService;
import com.propopt.model.PropOptVO;


@MultipartConfig
public class PropOptServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public PropOptServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
	
		String query = request.getParameter("query");
		String action = request.getParameter("action");

		PropOptService propOptSvc = new PropOptService();
		List<PropOptVO> propOptList = new ArrayList<PropOptVO>();

		if ("繼續新增".equals(action)) { 
			

			Integer proposalId = (Integer)( request.getSession().getAttribute("proposalId"));
			

			String optionName = request.getParameter("optionName").trim();

			Integer optionPrice = Integer.valueOf( request.getParameter("optionPrice").trim());

			byte[] optionPicture =  request.getPart("upload").getInputStream().readAllBytes();

			PropOptService propSvc = new PropOptService();

			PropOptVO propOptVO = propSvc.addPropOpt(proposalId, optionName, optionPrice, optionPicture);

			

			String url = "/front_end/prop/addProposalOption.jsp";
			RequestDispatcher successView =  request.getRequestDispatcher(url); 
			successView.forward( request, response);
		} else if ("送出申請".equals(action)) {
			
			Integer proposalId = (Integer)(request.getSession().getAttribute("proposalId"));

			String optionName = request.getParameter("optionName").trim();

			Integer optionPrice = Integer.valueOf(request.getParameter("optionPrice").trim());

			byte[] optionPicture = request.getPart("upload").getInputStream().readAllBytes();

			PropOptService propSvc = new PropOptService();

			PropOptVO propOptVO = propSvc.addPropOpt(proposalId, optionName, optionPrice, optionPicture);

			
			String url = "/front_end/prop/succeedAddPropoposal.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}

	
	
	if ((query != null) && !(query.isEmpty())) {

		switch (query) {

		case "proposalOptionNameQuery":
		case "proposalOptionQuery":

			int proposalOptionId = Integer.parseInt(request.getParameter("proposalOptionId"));

			propOptList.add((propOptSvc.getOnePropOpt(proposalOptionId)));
			request.setAttribute("propOptList", propOptList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listOnePropOpt.jsp");
			dispatcher.forward(request, response);

			break;

		case "proposalIdQuery":

			response.getWriter().append("This is proposal Id:").append(request.getParameter("proposalId"))
					.append("\n");
			int proposalId = Integer.parseInt(request.getParameter("proposalId"));

			propOptList = propOptSvc.findByProposalId(proposalId);

			request.setAttribute("propOptList", propOptList);

			RequestDispatcher proposalIdDispatcher = request.getRequestDispatcher("listOnePropOpt.jsp");
			proposalIdDispatcher.forward(request, response);
			break;

		}
	}
	// �s�W�B�R���B�s��
	else if ((action != null) && !(action.isEmpty())) {

		switch (action) {

		case "delete":
			try {
				propOptSvc.deletePropOpt(Integer.parseInt(request.getParameter("proposalOptionId")));
				request.setAttribute("del_status", "success");
				RequestDispatcher dispatcher = request.getRequestDispatcher("listAllPropOpt.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				request.setAttribute("del_status", "fail");
				RequestDispatcher dispatcher = request.getRequestDispatcher("listAllPropOpt.jsp");
				dispatcher.forward(request, response);
			}
			break;

		case "getOne_For_Update":
			response.getWriter().append("This is Update");

			RequestDispatcher updateDispatcher = request.getRequestDispatcher("update_propopt_input.jsp");
			updateDispatcher.forward(request, response);
			break;

		case "update":
			response.getWriter().append("This is Update2");
			int proposalOptionId = Integer.parseInt(request.getParameter("proposalOptionId"));
			String proposalOptionNameUpdate = request.getParameter("proposalOptionName");
			int proposalOptionPriceUpdate = Integer.parseInt(request.getParameter("proposalOptionPrice"));

			propOptSvc.updatePropOpt(proposalOptionId, proposalOptionNameUpdate, proposalOptionPriceUpdate);
			RequestDispatcher listAllDispatcher = request.getRequestDispatcher("listAllPropOpt.jsp");
			listAllDispatcher.forward(request, response);

			break;

		case "addProposalOption":
			System.out.println(123);
			response.getWriter().append("This is Insert");
			int proposalId = Integer.parseInt(request.getParameter("proposalId"));
			String proposalOptionName = request.getParameter("proposalOptionName");
			int proposalOptionPrice = Integer.parseInt(request.getParameter("proposalOptionPrice"));
			Part filePart = request.getPart("proposalOptionPicture");
			InputStream fileContent = filePart.getInputStream();
			byte[] imgBytes = fileContent.readAllBytes();

			propOptSvc.addPropOpt(proposalId, proposalOptionName, proposalOptionPrice, imgBytes);

			RequestDispatcher dispatcher = request.getRequestDispatcher("listAllPropOpt.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}

}
}
