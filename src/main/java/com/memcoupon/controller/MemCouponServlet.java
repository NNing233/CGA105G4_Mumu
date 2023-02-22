package com.memcoupon.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.model.CouponService;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.memcoupon.model.CouponStatusStatus;
import com.memcoupon.model.MemCouponService;
import com.memcoupon.model.MemCouponVO;

/**
 * Servlet implementation class MemCouponServlet
 */
@WebServlet("/memCoupon/memCoupon.do")
public class MemCouponServlet extends HttpServlet {
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
		
//***************選擇性別****************//
		String memType = request.getParameter("memType");
		int couponId = Integer.parseInt(request.getParameter("couponId"));
		CouponService couponSvc = new CouponService();
		String couponName = couponSvc.getOneCoupon(couponId).getCouponName();

		System.out.println("memType:" + memType);
		System.out.println("couponId: " + couponId);

		if ("all".equals(memType)) {
			MemService memSvc = new MemService();
			List<MemVO> memList = memSvc.getAll();

			for (MemVO memVO : memList) {
				MemCouponService memCouponSvc = new MemCouponService();
				//因為方法是public static CouponStatusStatus from(int value)所以方法需要寫CouponStatusStatus.from()
				memCouponSvc.addMemCoupon(memVO.getMemberId(), couponId, couponName, CouponStatusStatus.from(1));
			}
		}

		else if ("male".equals(memType)) {
			MemService memSvc = new MemService();
			List<MemVO> memList = memSvc.findByGender("男");

			for (MemVO memVO : memList) {
				MemCouponService memCouponSvc = new MemCouponService();
				//因為方法是public static CouponStatusStatus from(int value)所以方法需要寫CouponStatusStatus.from()
				memCouponSvc.addMemCoupon(memVO.getMemberId(), couponId, couponName, CouponStatusStatus.from(1));

			}
		}
		else if ("female".equals(memType)) {
			MemService memSvc = new MemService();
			List<MemVO> memList = memSvc.findByGender("女");

			for (MemVO memVO : memList) {
				MemCouponService memCouponSvc = new MemCouponService();
				//因為方法是public static CouponStatusStatus from(int value)所以方法需要寫CouponStatusStatus.from()
				memCouponSvc.addMemCoupon(memVO.getMemberId(), couponId, couponName,  CouponStatusStatus.from(1));
			}
		}
		

		/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
		String url = "/back_end/coupon/listAllMemberCouponTest1.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
		System.out.println(777);
		
	

// ***************列出特定會員自己的優惠券(類似查詢全部作法)*************************//
		if ("getOneMemberAllCoupon".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer memberID = Integer.valueOf(request.getParameter("memberID"));
				
				/***************************2.開始查詢資料****************************************/
				MemCouponService memCouponSvc = new MemCouponService();
				MemCouponVO memCouponVO = (MemCouponVO) memCouponSvc.getMmeCoupon(memberID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("memCouponVO", memCouponVO);         // 資料庫取出的memCouponVO物件,存入request
				String url22 = "/front_end/coupon/listOneMemberCouponTest1.jsp";
				RequestDispatcher successView22 = request.getRequestDispatcher(url22);// 轉交 listOneMemberCouponTest1.jsp
				successView22.forward(request, response);
		}
	}

}

