package com.myfavorite.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.model.CouponService;
import com.myfavorite.model.MyFavoriteService;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.memcoupon.model.MemCouponService;
import com.memcoupon.model.MemCouponVO;
import com.myfavorite.model.MyFavoriteVO;
import com.order.model.OrderService;
import com.order.model.OrderVO;

/**
 * Servlet implementation class MemCouponServlet
 */
@WebServlet("/myfavorite/myfavorite.do")
public class MyFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

		System.out.println("123");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

// ***************列出特定會員自己的收藏(類似查詢全部)*************************//
		// 命名getOneMemberAllFavorite
		if ("getOneMemberAllFavorite".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer memberID = Integer.valueOf(request.getParameter("memberID"));

			/*************************** 2.開始查詢資料 ****************************************/
			MemCouponService myFavoriteSvc = new MemCouponService();
			MyFavoriteVO myFavoriteVO = (MyFavoriteVO) myFavoriteSvc.getMmeCoupon(memberID);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			request.setAttribute("myFavoriteVO", myFavoriteVO); // 資料庫取出的myFavoriteVO物件,存入request
			String url = "/front_end/shop/listOneMemberFavorite.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);// 轉交 listOneMemberCouponTest1.jsp
			successView.forward(request, response);
		}
		
		
//********************刪除特定會員收藏的特定商品*************************//
		if ("delete".equals(action)) { // 來自listOneMemberFavorite.jsp的刪除請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
//			要刪掉的特定會員的特定產品會需要memberId和productId才能確定所以都要帶入
			Integer memberId = Integer.valueOf(request.getParameter("memberId"));
			Integer productId = Integer.valueOf(request.getParameter("productId"));
			/*************************** 2.開始刪除資料 ***************************************/
			MyFavoriteService myFavoriteSvc = new MyFavoriteService();
			myFavoriteSvc.deleteMyFavorite(memberId, productId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/shop/listOneMemberFavorite.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(request, response);
		}

	}
}
