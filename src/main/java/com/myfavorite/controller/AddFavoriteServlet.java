package com.myfavorite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.myfavorite.model.MyFavoriteService;

/**
 * Servlet implementation class AddFavoriteServlet
 */
@WebServlet("/AddFavoriteServlet")
public class AddFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("addToFavorite".equals(request.getParameter("action"))) {
			
			HttpSession session = request.getSession();
			
			if(session.getAttribute("mem") == null) {
				response.sendRedirect(request.getContextPath()+"/front_end/login.jsp");
			} else {
				
				MyFavoriteService myFavoriteSvc = new MyFavoriteService();
				MemVO memVO = (MemVO)session.getAttribute("mem");
				
				//拿需要的參數
				int prodId = Integer.parseInt(request.getParameter("productId"));
				int memberId = memVO.getMemberId();
				System.out.println(11111119);
				
				if(myFavoriteSvc.getOneMyFavorite(memberId, prodId) != null) {
					myFavoriteSvc.deleteMyFavorite(memberId, prodId);
				} else {
					myFavoriteSvc.addMyFavorite(prodId, memberId);
				}
				
			}
			
			
		}
	}

}
