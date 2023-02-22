package com.coupon.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.model.*;
import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;
import com.memcoupon.model.MemCouponService;
import com.memcoupon.model.MemCouponVO;

public class CouponServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//==新增資料=========================================================================================//
		if ("insert".equals(action)) { // 來自select_page.jsp的請求
			//使用<td>${errorMsgs.ename}</td>就需要用Map<String, String>才能做
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer couponID = Integer.valueOf(req.getParameter("couponID").trim());
			String couponName = req.getParameter("couponName");
			//String couponNameReg這段是判斷錯誤然猴回傳給couponName,因為是判斷所以要寫另外一個String couponName只有抓資料而已
			//errorMsgs.put(couponName , "優惠券名稱: 請勿空白" );-->需寫put couponName因為判斷完要傳回couponName
			String couponNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (couponName == null || couponName.trim().length() == 0) {
				errorMsgs.put("couponName" , "優惠券名稱: 請勿空白" );
			} else if (!couponName.trim().matches(couponNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("couponName", "優惠券名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			Integer discountPercentage = Integer.valueOf(req.getParameter("discountPercentage").trim());

			Integer discountAmount = Integer.valueOf(req.getParameter("discountAmount").trim());

			java.sql.Timestamp couponStartDatetime = null;
			try {
				couponStartDatetime = java.sql.Timestamp.valueOf(req.getParameter("couponStartDatetime").trim());
			} catch (IllegalArgumentException e) {
				couponStartDatetime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.put("couponStartDatetime", "請輸入開始日期!");
			}

			java.sql.Timestamp couponEndDatetime = null;
			
			try {
				couponEndDatetime = java.sql.Timestamp.valueOf(req.getParameter("couponEndDatetime").trim());
			} catch (IllegalArgumentException e) {
				couponEndDatetime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.put("couponEndDatetime", "請輸入結束日期!");
			}

			CouponVO couponVO = new CouponVO();
			couponVO.setCouponID(couponID);
			couponVO.setCouponName(couponName);
			couponVO.setDiscountPercentage(discountPercentage);
			couponVO.setDiscountAmount(discountAmount);
			couponVO.setCouponStartDatetime(couponStartDatetime);
			couponVO.setCouponEndDatetime(couponEndDatetime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的couponVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/coupon/addCouponTest1.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			CouponService couponSvc = new CouponService();
			couponVO = couponSvc.addCoupon(couponID, couponName, discountPercentage, discountAmount,
					couponStartDatetime, couponEndDatetime);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/coupon/listAllMemberCouponTest1.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMemberCoupon.jsp
			successView.forward(req, res);
		}
//==傳送資料去修改頁面(都需要經過這個步驟)=========================================================================================//			
		if ("update_data".equals(action)) {
			Integer couponID = Integer.valueOf(req.getParameter("couponID"));

			CouponService couponSvc = new CouponService();
			CouponVO couponVo = couponSvc.getOneCoupon(couponID);

			req.setAttribute("couponVO", couponVo);
			String url = "/back_end/coupon/update_coupon_inputTest1.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//==修改資料======================================================================================================//
		if ("update".equals(action)) { // 來自listAllMemberCouponTest1.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			System.out.println(111);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer couponID = Integer.valueOf(req.getParameter("couponID").trim());
			System.out.println(222);
			String couponName = req.getParameter("couponName");
			String couponNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (couponName == null || couponName.trim().length() == 0) {
				errorMsgs.put("優惠券名稱: 請勿空白", couponNameReg);
			} else if (!couponName.trim().matches(couponNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("優惠券名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間", couponNameReg);
			}
			System.out.println(223);
			Integer discountPercentage = Integer.valueOf(req.getParameter("discountPercentage").trim());

			System.out.println(224);
			Integer discountAmount = Integer.valueOf(req.getParameter("discountAmount").trim());

			System.out.println(333);
			java.sql.Timestamp couponStartDatetime = null;
			try {
				couponStartDatetime = java.sql.Timestamp.valueOf(req.getParameter("couponStartDatetime"));
			} catch (IllegalArgumentException e) {
//					couponStartDatetime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.put("couponStartDatetime", "請輸入開始日期");
			}
			System.out.println(444);
			java.sql.Timestamp couponEndDatetime = null;
			try {
				couponEndDatetime = java.sql.Timestamp.valueOf(req.getParameter("couponEndDatetime"));
			} catch (IllegalArgumentException e1) {
//					couponEndDatetime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.put("couponEndDatetime", "請輸入結束日期");
			}
			System.out.println(555);
//				 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coupon/update_coupon_inputTest1.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			System.out.println(999);
			/*************************** 2.開始修改資料 *****************************************/
			CouponService couponSvc = new CouponService();
			CouponVO couponVO = couponSvc.updateCoupon(couponID, couponName, discountPercentage, discountAmount,
					couponStartDatetime, couponEndDatetime);
			System.out.println(666);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couponVO", couponVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back_end/coupon/listAllMemberCouponTest1.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			System.out.println(777);
		}
	
//==傳送資料去新增優惠券持有會員頁面(都需要經過這個步驟)=========================================================================================//			
			if ("sendMemCoupon_Data".equals(action)) {
				Integer couponID = Integer.valueOf(req.getParameter("MemHascoupon"));

				CouponService couponSvc = new CouponService();
				CouponVO couponVo = couponSvc.getOneCoupon(couponID);

				req.setAttribute("couponVO", couponVo);
				String url = "/back_end/coupon/sendMemcoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

//==送出優惠券持有會員======================================================================================================//
			if ("sendMemCoupon".equals(action)) { // 來自listAllMemberCouponTest1.jsp的請求

				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);

				System.out.println(111);
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
				System.out.println(222);
				
//					 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coupon/sendMemcoupon.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				System.out.println(999);
				/*************************** 2.開始修改資料 *****************************************/
				CouponService couponSvc = new CouponService();
//				CouponVO couponVO = couponSvc.sendcoupon(couponID, couponName);
				System.out.println(666);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("couponVO", couponVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/coupon/listAllMemberCouponTest1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				System.out.println(777);
			}
		}	
	
	

}
