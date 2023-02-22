package com.image.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ImageServlet extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String articleCarouselId =req.getParameter("articleCarouselId").trim();
			ResultSet rs = stmt.executeQuery(
				"SELECT ARTICLE_CAROUSEL_PICTURE FROM article_carousel where ARTICLE_CAROUSEL_ID ="+articleCarouselId);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("ARTICLE_CAROUSEL_PICTURE"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		try {
			Statement stmt = con.createStatement();
			String commentId =req.getParameter("commentId").trim();
			ResultSet rs = stmt.executeQuery(
				"SELECT COMMENT_PICTURE FROM comment where COMMENT_ID ="+commentId);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("COMMENT_PICTURE"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		  try {
		   Context ctx = new javax.naming.InitialContext();
		   DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumu_new");
		   con = ds.getConnection();
		  } catch (NamingException e) {
		   e.printStackTrace();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		 }

		 public void destroy() {
		  try {
		   if (con != null) con.close();
		  } catch (SQLException e) {
		   System.out.println(e);
		  }
		 }

}