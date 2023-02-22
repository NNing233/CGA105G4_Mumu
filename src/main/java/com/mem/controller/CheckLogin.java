package com.mem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;


@WebServlet("/mem/checkLogin.do")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
    	HttpSession session = req.getSession();
    	if(session.getAttribute("mem") != null) {
    		res.getWriter().append("logged");
    	} else {
    		res.getWriter().append("sendLogin");
    	}
    } 

}
