package com.propopt.controller;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.prop2.model.PropService;
import com.prop2.model.PropVO;
import com.propopt2.model.PropOptService;
import com.propopt2.model.PropOptVO;

/**
 * Servlet implementation class PropOptServlet
 */

@WebServlet("/propopt/propopt.do")
public class PropOptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropOptServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		PropOptService propOptSvc = new PropOptService();

		if ("showImg".equals(request.getParameter("action"))) {
			response.setContentType("image/png");
			System.out.println(request.getParameter("proposalOptionId"));
			byte[] imgBytes = propOptSvc.getOnePropOpt(Integer.parseInt(request.getParameter("proposalOptionId")))
					.getProposalOptionPicture();
			System.out.println(imgBytes);
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
		} else if("listPropOpt".equals(request.getParameter("action"))) {
//			response.sendRedirect(request.getContextPath()+"/front_end/test.jsp");
//			return;
			
          int proposalId = Integer.parseInt(request.getParameter("proposalId"));
			
			PropService propSvc = new PropService();
			PropVO propVO = propSvc.getOneProp(proposalId);
			
			List<PropOptVO> propOptList = propOptSvc.getPropOptByProposalId(proposalId);
			
			request.setAttribute("propVO", propVO);
			request.setAttribute("propOptList", propOptList);
			
			
//			RequestDispatcher proposalIdDispatcher = request.getRequestDispatcher("localhost:8081"+request.getContextPath()+"/front_end/test.jsp");
			RequestDispatcher listPropOptDispatcher = request.getRequestDispatcher("/front_end/propopt/listPropOpt.jsp");
			System.out.println(request.getContextPath()+"/front_end/test.jsp");
			listPropOptDispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("This is proposalOption Id:").append(request.getParameter("proposalOptionId")).append("\n");
//		response.getWriter().append("This is proposal Id:").append(request.getParameter("proposalId")).append("\n");
//		response.getWriter().append("This is proposalOption Name:").append(request.getParameter("proposalOptionName")).append("\n");

		
		
		
		
		String query = request.getParameter("query");
		String action = request.getParameter("action");

		PropOptService propOptSvc = new PropOptService();
		List<PropOptVO> propOptList = new ArrayList<PropOptVO>();

		
		if("checkout".equals(request.getParameter("action"))) {
			HttpSession session = request.getSession(false);
			
			if(session == null || session.getAttribute("mem") == null) {
				response.sendRedirect(request.getContextPath() + "/front_end/login.jsp");
				return;
			}
			
			
			request.setAttribute("propOpt", propOptSvc.getOnePropOpt(Integer.parseInt(request.getParameter("proposalOptionId"))));
			RequestDispatcher checkoutDispatcher = request.getRequestDispatcher("/front_end/propopt/checkout.jsp");
			checkoutDispatcher.forward(request, response);
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
//					e.printStackTrace();
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
