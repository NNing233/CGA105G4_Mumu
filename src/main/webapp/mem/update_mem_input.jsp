<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_mem_input.jsp</title>

<style>
  table#table-1 {
	background-color: #FFB5B5;
    border: 1px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 700px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員資料修改 - update_mem_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/main.png" width="100" height="85" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="mem.do" name="form1"enctype="multipart/form-data">
		<table>
		
		<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMemberId()%></td>
	</tr>
			<tr>
				<td>會員帳號:</td>
				<td><input type="TEXT" name="memberAccount" size="45" value="<%=memVO.getMemberAccount()%>" /></td>
			</tr>
			<tr>
				<td>會員密碼:</td>
				<td><input type="TEXT" name="memberPassword" size="45" value="<%=memVO.getMemberPassword()%>" /></td>		
			</tr>

			<tr>
				<td>暱稱:</td>
				<td><input type="TEXT" name="memberNickName" size="45" value="<%=memVO.getMemberNickname()%>" /></td>
			</tr>

			<tr>
				<td>身分證字號:</td>
				<td><input type="TEXT" name="idNumber" size="45" value="<%=(memVO==null)? "10000" : memVO.getIdNumber()%>" /></td>
			</tr>

			<tr>
				<td>會員狀態:</td>
				<td><input type="TEXT" name="memberStatus" size="45" value="<%=memVO.getMemberStatus()%>" /></td>
			</tr>

			<tr>
				<td>會員名稱:</td>
				<td><input type="TEXT" name="memberName" size="45" value="<%=memVO.getMemberName()%>" /></td>
					
			</tr>

				<tr>
				<td>性別:</td>
				<td><input type="TEXT" name="memberGender" size="45" value="<%=memVO.getMemberGender()%>" /></td>
			</tr>

			<tr>
				<td>出生日期:</td>
				<td><input name="dateOfBirth" id="f_date2" type="text"></td>
			</tr>

				<tr>
				<td>電郵地址:</td>
				<td><input type="TEXT" name="memberEmail" size="45" value="<%=memVO.getMemberEmail()%>" /></td>
			</tr>

			<tr>
				<td>會員電話:</td>
				<td><input type="TEXT" name="memberPhone" size="45" value="<%=memVO.getMemberPhone()%>" /></td>
			</tr>

			<tr>
				<td>會員地址:</td>
				<td><input type="TEXT" name="memberAddress" size="45" value="<%=memVO.getMemberAddress()%>" /></td>
			</tr>

			<tr>
				<td>修改圖片:</td>
						<td><input type="file" name="upfile1" size="45" value="<%=memVO.getProfilePhoto()%>"/></td>
	
			</tr>
<tr>
<td><img src="<%=request.getContextPath()%>/ImageServlet?memberID=${memVO.memberID}"width=300px height=200px></td>
</tr>
			<tr>
				<td>銀行帳號:</td>
				<td><input type="TEXT" name="bankAccount" size="45" value="<%=memVO.getBankAccount()%>" /></td>
			</tr>
		</table>
		<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memberID" value="<%=memVO.getMemberId()%>">
<input type="submit" value="送出修改"></FORM>

</body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=memVO.getDateOfBirth()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

        </script>
</html>