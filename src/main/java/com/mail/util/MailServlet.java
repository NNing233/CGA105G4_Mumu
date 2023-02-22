package com.mail.util;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;


public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if("send_code".equals(action)) {
			
	        String memberAccount = req.getParameter("memberAccount");
	        String memberEmail = req.getParameter("memberEmail");
	     
	        MemService memSvc = new MemService();
	        MemVO memVO = memSvc.findByAccount(memberAccount);
	        
	        if(memVO == null) {
	        	req.setAttribute("memberAccount_error", "帳號輸入錯誤或不存在！");
	        	req.getRequestDispatcher("/front_end/mem/find_password01.jsp").forward(req, res);
	        	return;// 程式中斷
	        }else if(!memberEmail.equals(memVO.getMemberEmail())) {
	        	req.setAttribute("memberEmail_error", "Email輸入錯誤!");
	        	req.getRequestDispatcher("/front_end/mem/find_password01.jsp").forward(req, res);
	        	return;
	        }else{
	        	//帳號正確
	        	session.setAttribute("memberAccount",memberAccount);
	        	
	            //產生驗證碼
	            String base = "0123456789ABCDEFGHIJKLMNOPQRSDUVWXYZabcdefghijklmnopqrsduvwxyz";
	            int size = base.length();
	            Random random = new Random();
	            StringBuilder code = new StringBuilder();
	            for( int i=1 ; i<=4 ; i++ ){
	                int index = random.nextInt(size);
	                char c = base.charAt(index);
	                code.append(c);
	            }
	            //比對用
	            session.setAttribute("emailCode",code.toString());
	            
	            String to = memberEmail;
	            String subject = "您向募募MUMU申請了重設密碼";
	            String messageText = "您向募募MUMU申請了重設密碼，您的驗證碼為:"+code.toString()+
	            		"\n此驗證碼只有一次輸入機會，請正確輸入。如非本人操作，請刪除此信件，謝謝。";
	            
	            MailService mailSvc = new MailService();
	            mailSvc.sendMail(to, subject, messageText);
	            
	            if(!mailSvc.sendMail(to, subject, messageText)) {
	            	req.setAttribute("sedEmail_error", "驗證碼寄出失敗，請重新申請。");
	            	req.getRequestDispatcher("/front_end/mem/find_password01.jsp").forward(req,res);
	            	return;
	            }else {
	              req.getRequestDispatcher("/front_end/mem/find_password02.jsp").forward(req,res);
	            	
	            }
	        }
		}
        
        if("verify_code".equals(action)) {
        	String emailCode = req.getParameter("emailCode");
        	String code = (String)session.getAttribute("emailCode");
        	if(!emailCode.equals(code)) {
        		session.invalidate();//驗證碼失效
        		req.setAttribute("code_error", "驗證碼輸入錯誤，請重新申請驗證碼!");
        		req.getRequestDispatcher("/front_end/mem/find_password02.jsp").forward(req, res);
        		return;
        		
        	}else {
        		req.getRequestDispatcher("/front_end/mem/find_password03.jsp").forward(req, res);
        	}
        	
        }
        
        if("reset_password".equals(action)) {
        	

        	String newPassword = req.getParameter("newPassword");
			String passwordReg = "^[(a-zA-Z0-9)]{6,20}$";
			if (!newPassword.trim().matches(passwordReg)) {
				req.setAttribute("newPassword_error", "請使用英文和數字，且長度在6到20之間。");
				req.getRequestDispatcher("/front_end/mem/find_password03.jsp").forward(req, res);
				return;
			}
			
			String newPassword2 = req.getParameter("newPassword2");
			if (!newPassword2.equals(newPassword)) {
				req.setAttribute("newPassword2_error", "兩次密碼必須相同。");
				req.getRequestDispatcher("/front_end/mem/find_password03.jsp").forward(req, res);
				return;
			}
			
		
			String memberAccount = (String)session.getAttribute("memberAccount");
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.findByAccount(memberAccount);
        	memVO = memSvc.changePassword(memVO.getMemberId(), newPassword);
        	
        	if(!newPassword.equals(memVO.getMemberPassword())) {
        		req.setAttribute("error_msg","密碼修改失敗，請重新輸入!");
        		req.getRequestDispatcher("/front_end/mem/find_password03.jsp");
        	}else {
        		session.invalidate();
        		req.getRequestDispatcher("/front_end/mem/find_password04.jsp").forward(req, res);
        	}
        }
		
	}

}
