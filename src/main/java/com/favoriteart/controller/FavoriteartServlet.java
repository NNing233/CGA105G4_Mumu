package com.favoriteart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favoriteart.model.FavoriteArtService;
import com.favoriteart.model.FavoriteArtVO;
import com.mem.model.MemVO;




@WebServlet("/front_end/art/Favoriteart.do")
public class FavoriteartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public FavoriteartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		HttpSession session = req.getSession();
		String action = request.getParameter("action");
		//嚙踐�EDIS�����蕭???
		
		
		if(("addArticleToFavorList").equals(action)) {
//			Integer memberId=Integer.valueOf(request.getParameter("memberId"));
			Integer memberId= ((MemVO)(request.getSession().getAttribute("mem"))).getMemberId();
			System.out.println("會員: "+memberId);
			Integer articleId= Integer.valueOf(request.getParameter("articleId"));
			System.out.println("文章: "+articleId);
			//TktList 
			FavoriteArtService favoriteArtSvc=new FavoriteArtService();
			try {
				System.out.println("收藏成功");
				favoriteArtSvc.addFavoriteArt(articleId, memberId);
				
			}catch (Exception e) {
				System.out.println("移除收藏");
				favoriteArtSvc.deleteFavoriteArt(articleId, memberId);
				
			}
			List<FavoriteArtVO> list=favoriteArtSvc.findByMemberID(memberId);
			
			//TktImg
//			ArtPicService artPicSvc =new ArtPicService();
//			List<ArtPicVO>list_img=artPicSvc.findByArticleId(articleId);
			
			request.setAttribute("list", list);
			request.setAttribute("articleId", articleId);
//			request.setAttribute("articleId", articleId);
			String url = "/front_end/art/"+request.getPathInfo();
			System.out.println(url);
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
//		if(("deleteArticleToFavorList").equals(action)) {
//Integer memberId= ((MemVO)(request.getSession().getAttribute("mem"))).getMemberId();
//			
//			Integer articleId= Integer.valueOf(request.getParameter("articleId"));
//			System.out.println(articleId);
//			//TktList 
//			FavoriteArtService favoriteArtSvc=new FavoriteArtService();
//			favoriteArtSvc.deleteFavoriteArt(articleId, memberId);
//			List<FavoriteArtVO> list=favoriteArtSvc.findByMemberID(memberId);
//			
//			//TktImg
////			ArtPicService artPicSvc =new ArtPicService();
////			List<ArtPicVO>list_img=artPicSvc.findByArticleId(articleId);
//			
//			request.setAttribute("list", list);
//			request.setAttribute("articleId", articleId);
////			request.setAttribute("articleId", articleId);
//			String url = "/front_end/art/test.jsp";
//			RequestDispatcher successView = request.getRequestDispatcher(url);
//			successView.forward(request, response);
//		}
	}


}

