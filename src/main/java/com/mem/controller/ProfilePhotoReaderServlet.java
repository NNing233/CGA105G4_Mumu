package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mem.model.MemService;


@WebServlet("/ProfilePhotoReaderServlet")
public class ProfilePhotoReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			MemService memSvc = new MemService();
			out.write(memSvc.getOneMem(memberId).getProfilePhoto());
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/front_end/assets/img/mumu_mem/default_photo.png");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();

		}
	}

}
