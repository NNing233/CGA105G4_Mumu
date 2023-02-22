package com.comment.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comment.model.CommentService;
import com.comment.model.CommentVO;
import com.mem.model.MemVO;
import com.mysql.cj.Session;

import oracle.security.o3logon.a;

@MultipartConfig
public class CommentServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			    Integer commentId = Integer.valueOf(req.getParameter("commentId"));

				/***************************2.�}�l�d�߸��*****************************************/
				CommentService commentSvc = new CommentService();
				CommentVO commentVO = commentSvc.getOneComment(commentId);

				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("commentVO", commentVO); 
				String url = "/back_end/comment/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

				/***************************1.�����ШD�Ѽ�****************************************/
				Integer commentId = Integer.valueOf(req.getParameter("commentId"));
				
				/***************************2.�}�l�d�߸��****************************************/
				CommentService commentSvc = new CommentService();
				CommentVO commentVO = commentSvc.getOneComment(commentId);				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("commentVO", commentVO);         
				String url = "/front_end/art/update_comment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			   CommentService commentSvc = new CommentService();
			
			    Integer commentId = Integer.valueOf(req.getParameter("commentId").trim());
				
				String commentContent = req.getParameter("commentContent");
				
				Integer commentStatus = 1;
				
//				InputStream inputStream = req.getPart("commentPicture").getInputStream();
//				byte[]commentPicture = null;
//				if(inputStream.available()!=0) {
//				
//					InputStream in = null;
//					commentPicture = new byte[in.available()];
//					in.read(commentPicture);
//					in.close();
//				}else {
//					commentSvc = new CommentService();
//					commentPicture = commentSvc.getOneComment(commentId).getCommentPicture();}
					
				byte[] commentPicture = req.getPart("upfile1").getInputStream().readAllBytes();
				System.out.println("12345");
				if (commentPicture.length == 0) {
					CommentVO comcmentVO = commentSvc.getOneComment(commentId);
					commentPicture = comcmentVO.getCommentPicture();
				}
				
				CommentVO commentVO = new CommentVO();
				commentVO.setCommentContent(commentContent);
				commentVO.setCommentStatus(commentStatus);
//				commentVO.setCommentPicture(null);
				
				List<CommentVO> comment = commentSvc.getAll();
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commentVO", commentVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/art/update_comment_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				commentVO = commentSvc.updateComment(commentId, commentContent, commentStatus, commentPicture);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("commentVO", commentVO); 
				String url = "/front_end/art/listcomment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
		}
				
			HttpSession session = req.getSession();

        if ("insert".equals(action)) { 
        	
        	
        	HttpSession session2 = req.getSession();
        	if(session2.getAttribute("mem") != null) {
//			    Integer articleId = Integer.valueOf(req.getParameter("articleId"));
			    Integer articleId = Integer.parseInt(req.getParameter("articleId"));
			    
			    Integer memberId = ((MemVO) req.getSession().getAttribute("mem")).getMemberId();
			
			    String commentContent = req.getParameter("commentContent");
				
				Integer commentStatus = 1;
				
				byte[] commentPicture = req.getPart("commentpic").getInputStream().readAllBytes();

				/***************************2.�}�l�s�W���***************************************/
				CommentService commentSvc = new CommentService();
				CommentVO commentVO = commentSvc.addComment(articleId, memberId, commentContent, commentStatus, commentPicture);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/art/art.do?action=getOne_article&articleId="+articleId;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);	
        	} else {
        		res.sendRedirect(req.getContextPath()+"/front_end/login.jsp"); 	
        	}
			
			
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.�����ШD�Ѽ�***************************************/
			     Integer commentId = Integer.valueOf(req.getParameter("commentId"));
				
				/***************************2.�}�l�R�����***************************************/
				CommentService commentSvc = new CommentService();
				commentSvc.deleteComment(commentId);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front_end/art/listcomment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
		if("getArticleId".equals(action)) {
			System.out.print(123);
			
			Integer articleId = Integer.valueOf(req.getParameter("articleId").trim());
			
			CommentService commentsvc = new CommentService();
			
			List<CommentVO> list = commentsvc.getCommentByArtId(articleId);
			
			req.setAttribute("list", list);    
			
			String url = "/front_end/art/artDetails.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			
			successView.forward(req, res);
		}
	}
}