package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		HttpSession session = req.getSession();
		String location = (String) session.getAttribute("location");

		if ("insert".equals(action)) {//註冊

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			MemService memSvc = new MemService();

			String memberAccount = req.getParameter("memberAccount");
			String accountReg = "^[(a-zA-Z0-9_)]{6,20}$";
			if (memberAccount == null || memberAccount.length() == 0) {
				errorMsgs.put("memberAccount", "請填寫帳號");
			} else if (!memberAccount.trim().matches(accountReg)) {
				errorMsgs.put("memberAccount", "請使用英文、數字和底線_，且長度在6到20之間");
			} else if (memSvc.findByAccount(memberAccount) != null) {// 帳號是否註冊過
				errorMsgs.put("memberAccount", "此帳號已經被註冊，請重新填寫");
			}

			String memberPassword = req.getParameter("memberPassword");
			String passwordReg = "^[(a-zA-Z0-9)]{6,20}$";
			if (memberPassword == null || memberPassword.length() == 0) {
				errorMsgs.put("memberPassword", "請填寫密碼");
			} else if (!memberPassword.trim().matches(passwordReg)) {
				errorMsgs.put("memberPassword", "請使用英文和數字，且長度在6到20之間");
			}
			String memberPassword2 = req.getParameter("memberPassword2");
			if (memberPassword2 == null || memberPassword2.length() == 0) {
				errorMsgs.put("memberPassword2", "請填寫確認密碼");
			} else if (!memberPassword2.equals(memberPassword)) {// 與密碼比對
				errorMsgs.put("memberPassword2", "必須與密碼相同");
			}

			String memberName = req.getParameter("memberName");
			
			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memberName == null || memberName.trim().length() == 0) {
				errorMsgs.put("memberName","請填寫姓名");
			} else if(!memberName.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("memberName","只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			

			String memberNickname = req.getParameter("memberNickname");
			if (memberNickname == null || memberNickname.trim().length() == 0) {
				memberNickname = memberAccount;
			} else if (!(2 <= memberNickname.trim().length() && memberNickname.trim().length() <= 20)) {
				errorMsgs.put("memberNickname", "長度在2到20之間");
			}

			String memGender = req.getParameter("gender");
			if (memGender == null || memGender.trim().length() == 0) {
				errorMsgs.put("gender", "請選取性別");
			}

			java.sql.Date dateOfBirth = null;
			try {
				dateOfBirth = java.sql.Date.valueOf(req.getParameter("dateOfBirth"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("dateOfBirth", "請選取日期");
			}

			String memberEmail = req.getParameter("memberEmail");
			String emailReg = "^[a-zA-Z0-9]+([-_.][A-Za-zd]+)*@([a-zA-Z0-9]+[-.])+[A-Za-zd]{2,5}$";
			if (memberEmail == null || memberEmail.trim().length() == 0) {
				errorMsgs.put("memberEmail", "請填寫信箱");
			} else if (!memberEmail.trim().matches(emailReg)) {
				errorMsgs.put("memberEmail", "請填寫正確的信箱格式");
			}

			String memberPhone = req.getParameter("memberPhone");
			if (memberPhone == null || memberPhone.trim().length() == 0) {
				errorMsgs.put("memberPhone", "請填寫手機號碼");
			}

			String idNumber = req.getParameter("idNumber");
			String idReg = "^[A-Z][12]\\d{8}$";
			if (idNumber == null || idNumber.trim().length() == 0) {
				errorMsgs.put("idNumber", "請填寫身份證字號");
			} else if (!idNumber.trim().matches(idReg)) {
				errorMsgs.put("idNumber", "請填寫大寫英文開頭的十位證號");
			}

			String address1 = req.getParameter("address1");
			if ("請選擇".equals(address1)) {
				errorMsgs.put("address1", "請選擇縣市、");
			}
			String address2 = req.getParameter("address2");
			if ("請選擇".equals(address2)) {
				errorMsgs.put("address2", "請選擇區域/鄉鎮、");
			}
			String address3 = req.getParameter("address3");
			if (address3 == null || address3.trim().length() == 0) {
				errorMsgs.put("address3", "請填寫地址");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/register.jsp");
				failureView.forward(req, res);
				return;
			}

			// 3個String組成一個地址 address2要切郵遞區號
			String[] address = address2.split("-");
			String memberAddress = address[1] + address1 + address[0] + address3;

			// 預設狀態
			Integer memberStatus = 1;
			byte[] profilePhoto = null;
			String bankAccount = "";
			
			MemVO memVO = memSvc.addMem(memberAccount, memberPassword, memberNickname, memberStatus, idNumber, memberName,
										memGender, dateOfBirth, memberEmail, memberPhone, memberAddress, profilePhoto, bankAccount);
					
			// 註冊完成後即保持登入狀態
			session.setAttribute("mem", memVO);// 保存session
			if (location == null) {
				res.sendRedirect(req.getContextPath()+"/front_end/prop/index.jsp");
			} else {
				res.sendRedirect(location);
			}

		}
		
		if("getOne_For_Update".equals(action)) {//轉跳編輯個人資料頁面
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(memberId);
			String bankAccount ;
			if(memVO.getBankAccount()==null) {
				bankAccount = "";
			}else {
				bankAccount = memVO.getBankAccount();
			}
			
			String param ="?memberbId"        +memVO.getMemberId()+
					      "&memberAccount="   +memVO.getMemberAccount()+
					      "&memberPassword="  +memVO.getMemberPassword()+
					      "&memberNickname="  +memVO.getMemberNickname()+
					      "&signupDatetime="  +memVO.getSignUpDatetime()+
					      "&memberStatus="	  +memVO.getMemberStatus()+
					      "&idNumber="		  +memVO.getIdNumber()+
					      "&memberGender="	  +memVO.getMemberGender()+
					      "&memberName="	  +memVO.getMemberName()+
						  "&memberEmail="	  +memVO.getMemberEmail()+
						  "&dateOfBirth="	  +memVO.getDateOfBirth()+
					      "&memberPhone="	  +memVO.getMemberPhone()+
					      "&memberAddress="   +memVO.getMemberAddress()+
					      "&bankAccount="	  +bankAccount;
			String url = "/front_end/mem/editpersonal.jsp"+param;
			req.getRequestDispatcher(url).forward(req, res);
		}
		
		
		if("update".equals(action)) {//更新個人資料
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			String memberAccount = req.getParameter("memberAccount");

			String memberName = req.getParameter("memberName");
			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memberName == null || memberName.trim().length() == 0) {
				errorMsgs.put("memberName","請填寫姓名");
			} else if(!memberName.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("memberName","只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			String memberNickname = req.getParameter("memberNickname");
			if (memberNickname == null || memberNickname.trim().length() == 0) {
				memberNickname = memberAccount;
			} else if (!(2 <= memberNickname.trim().length() && memberNickname.trim().length() <= 20)) {
				errorMsgs.put("memberNickname", "長度在2到20之間");
			}

			String memGender = req.getParameter("gender");
			if (memGender == null || memGender.trim().length() == 0) {
				errorMsgs.put("gender", "請選取性別");
			}

			java.sql.Date dateOfBirth = null;
			try {
				dateOfBirth = java.sql.Date.valueOf(req.getParameter("dateOfBirth"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("dateOfBirth", "請選取日期");
			}

			String memberEmail = req.getParameter("memberEmail");
			String emailReg = "^[a-zA-Z0-9]+([-_.][A-Za-zd]+)*@([a-zA-Z0-9]+[-.])+[A-Za-zd]{2,5}$";
//			String emailReg = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" + "@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			if (memberEmail == null || memberEmail.trim().length() == 0) {
				errorMsgs.put("memberEmail", "請填寫信箱");
			} else if (!memberEmail.trim().matches(emailReg)) {
				errorMsgs.put("memberEmail", "請填寫正確的信箱格式");
			}

			String memberPhone = req.getParameter("memberPhone");
//			String phoneReg = "^09[0-9]{8}$";
			if (memberPhone == null || memberPhone.trim().length() == 0) {
				errorMsgs.put("memberPhone", "請填寫手機號碼");
			}
//			else if (!memberEmail.trim().matches(phoneReg)) {
//				errorMsgs.put("memberPhone", "請填寫正確");
//			}

			String idNumber = req.getParameter("idNumber");
			String idReg = "^[A-Z][12]\\d{8}$";
			if (idNumber == null || idNumber.trim().length() == 0) {
				errorMsgs.put("idNumber", "請填寫身份證字號");
			} else if (!idNumber.trim().matches(idReg)) {
				errorMsgs.put("idNumber", "請填寫大寫英文開頭的十位證號");
			}
			
			String memberAddress = req.getParameter("memberAddress");
			if (memberAddress == null || memberAddress.trim().length() == 0) {
				errorMsgs.put("memberAddress", "請填寫地址");
			}
			
			InputStream in = req.getPart("upFiles").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
			byte[] profilePhoto = null;
			if(in.available()!=0){
				profilePhoto = new byte[in.available()];
				in.read(profilePhoto);
				in.close();
			}  else {
				MemService memSvc = new MemService();
				profilePhoto = memSvc.getOneMem(memberId).getProfilePhoto();
			}
			
			String bankAccount= req.getParameter("bankAccount");
			if(bankAccount == null) {
				bankAccount = "";
			}
			
			if (!errorMsgs.isEmpty()) {
				errorMsgs.put("Exception","修改資料失敗");
				req.getRequestDispatcher("/front_end/mem/editpersonal.jsp").forward(req, res);
				return; //程式中斷
			}
			
			MemService memSvc = new MemService();
			MemVO  memVO = memSvc.updateMem(memberId, memberAccount, memberNickname, idNumber, memberName,
					memGender, dateOfBirth, memberEmail, memberPhone, memberAddress, profilePhoto, bankAccount);
			session.setAttribute("mem", memVO);//更新session
			req.getRequestDispatcher("/front_end/mem/personalpage.jsp").forward(req, res);
			
		}
		
		if("change_pw".equals(action)) {
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(memberId);
			
			String oldPassword = req.getParameter("oldPassword") ;
			if(oldPassword == null || oldPassword.length() == 0) {
				errorMsgs.put("oldPassword", "請輸入密碼。");
			}else if(!oldPassword.equals(memVO.getMemberPassword())){
				errorMsgs.put("oldPassword", "密碼不正確。");				
			}
			
			String newPassword = req.getParameter("newPassword");
			String passwordReg = "^[(a-zA-Z0-9)]{6,20}$";
			if(newPassword == null || newPassword.length() == 0) {
				errorMsgs.put("newPassword", "請輸入新密碼。");
			}else if (!newPassword.trim().matches(passwordReg)) {
				errorMsgs.put("newPassword", "請使用英文和數字，且長度在6到20之間。");
			}
			String newPassword2 = req.getParameter("newPassword2");
			if (newPassword2 == null || newPassword2.length() == 0) {
				errorMsgs.put("newPassword2", "確認新密碼。");
			} else if (!newPassword2.equals(newPassword)) {
				errorMsgs.put("newPassword2", "兩次新密碼必須相同。");
			}
			
			if(!errorMsgs.isEmpty()) {
				req.getRequestDispatcher("/front_end/mem/personalpage.jsp").forward(req, res);
				return; //程式中斷
			}
			
			memVO = memSvc.changePassword(memberId, newPassword);
			if(!newPassword.equals(memVO.getMemberPassword())) {
				req.setAttribute("error_msg", "密碼修改失敗!");
			}else {
				session.invalidate();//登出
				req.getRequestDispatcher("/front_end/mem/success.jsp").forward(req, res);
			}
		}
	
		
		//後台
		if("edit_status".equals(action)) {//轉跳編輯狀態頁面
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(memberId);
			req.setAttribute("memVO", memVO);
			req.getRequestDispatcher("/back_end/mem/edit_member_status.jsp").forward(req, res);
		}
		
		if("save_status".equals(action)) {//更新狀態
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			String memberStatus = req.getParameter("status");
			MemService memSvc = new MemService();
			
			if(memberStatus!=null) {//狀態1
				memSvc.updateMemberStatus(memberId, 1);
			}else {//狀態0
				memSvc.updateMemberStatus(memberId, 0);
			}
			
			MemVO memVO = memSvc.getOneMem(memberId);
			req.setAttribute("memVO", memVO);
			req.getRequestDispatcher("/back_end/mem/success_member_status.jsp").forward(req, res);
		}
		
		if("getOne".equals(action)) {//查看詳細資料
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(memberId);
			req.setAttribute("memVO", memVO);
			req.getRequestDispatcher("/back_end/mem/one_member.jsp").forward(req, res);
		}
	
	
	
	}

}
