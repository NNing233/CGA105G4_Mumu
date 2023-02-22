package com.order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderService;
import com.order.model.OrderStatusStatus;
import com.order.model.OrderVO;

@MultipartConfig
public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//=================================================================================================//			

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1 ****************************************/
			Integer orderId = Integer.valueOf(req.getParameter("orderId"));
			/*************************** 2 ****************************************/
			OrderService orderSvc = new OrderService();
			OrderVO orderVO = orderSvc.getOneOrder(orderId);

			/*************************** 3. ************/
			req.setAttribute("orderVO", orderVO);
			String url = "/front_end/order/update_OneMemberOrder_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// ==orderStatus更新狀態資料按鈕後送出=========================================================================================//
		if ("狀態更新".equals(action)) {
			System.out.println("123");
			OrderService orderSvc = new OrderService();
			Integer orderId = Integer.valueOf(req.getParameter("orderId").trim());
			OrderVO orderVO = orderSvc.getOneOrder(orderId);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		System.out.println(req.getParameter("orderStatus"));
			System.out.println("444");

			OrderStatusStatus updateOrderStatus = OrderStatusStatus
					.from(Integer.valueOf(req.getParameter("orderStatus")));
			System.out.println("這是 updateOrderStatus:" + updateOrderStatus);
			System.out.println("555");

			/*************************** 2.開始修改資料 *****************************************/

			orderVO = orderSvc.updateOrderStatus(updateOrderStatus, orderId);
			System.out.println("666");
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//		req.setAttribute("orderVO", orderVO);
			String url = "/back_end/order/listAllMemberOrderHistoryTest3.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			System.out.println("777");
		}

		// =================================================================================================//

		if ("insertCheckoutOrder".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1 ****************************************/
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			String orderAddress = req.getParameter("orderAddress");
			Integer orderAfterDiscount = Integer.valueOf(req.getParameter("orderAfterDiscount"));
			Integer orderDelivery = Integer.valueOf(req.getParameter("orderDelivery"));
			Integer orderDiscount = Integer.valueOf(req.getParameter("orderDiscount"));
			Integer orderPayment = Integer.valueOf(req.getParameter("orderPayment"));
			Timestamp orderTime = new Timestamp(System.currentTimeMillis());
			Integer orderTotalAmount = Integer.valueOf(req.getParameter("orderTotalAmount"));
			Integer orderStatus = 1;

			/*************************** 2 ****************************************/
			OrderService orderSvc = new OrderService();
//			OrderVO orderVO = orderSvc.addOrder(memberId, memberId, orderAddress, action, null, null, null, memberId, memberId, memberId);

			/*************************** 3. ************/
//			req.setAttribute("orderVO", orderVO);
			String url = "/front_end/order/update_OneMemberOrder_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

// ***************列出特定會員自己的訂單(類似查詢全部)*************************//
		if ("getOneMemberAllOrder".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the req scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));

			/*************************** 2.開始查詢資料 ****************************************/
			OrderService orderSvc = new OrderService();
			OrderVO orderVO = (OrderVO) orderSvc.getMmeOrder(memberId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("orderVO", orderVO); // 資料庫取出的OrdermemCouponVO物件,存入req
			String url = "/front_end/order/listOneMemberOrderHistoryTest2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		// *********************刪除訂單*************************//
		if ("delete".equals(action)) { // 來自listOneMemberOrderHistoryTest2.jsp的刪除請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ***************************************/
			Integer orderId = Integer.valueOf(req.getParameter("orderId"));
			/*************************** 2.開始刪除資料 ***************************************/
			OrderService orderSvc = new OrderService();
			orderSvc.deleteOrder(orderId);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/front_end/order/listOneMemberOrderHistoryTest2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		// *********************傳送資料去"會員修改訂單資料頁面"(都需要經過這個步驟)*************************//
		if ("update_data".equals(action)) {
			Integer orderId = Integer.valueOf(req.getParameter("orderId"));

			OrderService orderSvc = new OrderService();
			OrderVO orderVO = orderSvc.getOneOrder(orderId);

			req.setAttribute("orderVO", orderVO);
			String url = "/front_end/order/update_OneMemberOrder_inputTest1.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// *********************修改會員訂單資料(只有地址)*************************//
		if ("update".equals(action)) { // 來自listAllMemberorderVOTest1.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String orderAddress = req.getParameter("orderAddress");
			Integer orderId = Integer.valueOf(req.getParameter("orderId"));
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/order/update_OneMemberOrder_inputTest1.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			OrderService orderSvc = new OrderService();
			OrderVO orderVO = orderSvc.updateOrderAddress(orderAddress, orderId);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orderVO", orderVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/front_end/order/listOneMemberOrderHistoryTest2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		}
	}
}
