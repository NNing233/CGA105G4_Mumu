package com.shop.controller;

import java.io.IOException;
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
import com.memcoupon.model.MemCouponService;
import com.shopprod.model.ShopProdService;
import com.shopprod.model.ShopProdVO;

/**
 * Servlet implementation class MemCouponServlet
 */
//檔案送到地點且完成註冊
@WebServlet("/shoptype/shoptype.do")
public class ShopPageTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		//當listProductByType做action的動作
		if("listProductByType".equals(request.getParameter("action"))) {
			//看我檔案寫在哪裡下面就寫資料從哪裡來的
			int prodType = Integer.parseInt(request.getParameter("prodType"));
			ShopProdService shopProdSvc = new ShopProdService(); 
			List<ShopProdVO> shopProdList = shopProdSvc.findByType(prodType);
			
			request.setAttribute("shopProdList", shopProdList);
			//傳送到此地址
			request.getRequestDispatcher("/front_end/shop/shop-category.jsp").forward(request, response);
		}
	}
}
