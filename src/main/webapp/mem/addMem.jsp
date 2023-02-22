<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員資料新增</title>
<style>
table#table-1 {
	background-color: #FFB5B5;
	border: 1px solid gray;
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
	width: 450px;
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
		<tr>
			<td>
				<h3>會員資料新增 - 這是測試</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/6.png" width="100"
						height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>資料新增:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="mem.do" name="form1"
		enctype="multipart/form-data">
		<table>
		
	
		
			<tr>
				<td>會員帳號:</td>
				<td><input type="TEXT" name="memberAccount" size="45"
					value="<%=(memVO == null) ? "InputAccount" : memVO.getMemberAccount()%>" /></td>
			</tr>
			<tr>
				<td>會員密碼:</td>
				<td><input type="TEXT" name="memberPassword" size="45"
					value="<%=(memVO == null) ? "InputPassword" : memVO.getMemberPassword()%>" /></td>
			</tr>

			<tr>
				<td>暱稱:</td>
				<td><input type="TEXT" name="memberNickName" size="45"
					value="<%=(memVO == null) ? "InputNickname" : memVO.getMemberNickname()%>" /></td>
			</tr>

			<tr>
				<td>身分證字號:</td>
				<td><input type="TEXT" name="idNumber" size="45"
					value="<%=(memVO == null) ? "IDNumber" : memVO.getIdNumber()%>" /></td>
			</tr>


			<tr>
				<td>會員狀態:<font color=red><b>*</b></font></td>
				<td><select id="Status" size="1" name="memberStatus">
						<option value="1" ${MemberStatus==1?'selected':''}>1</option>
						<option value="2" ${MemberStatus==2?'selected':''}>2</option>
				</select></td>
			</tr>
			<tr>
				<td>會員名稱:</td>
				<td><input type="TEXT" name="memberName" size="45"
					value="<%=(memVO == null) ? "InputMemberName" : memVO.getMemberName()%>" /></td>
			</tr>

			<tr>
				<td>性別:<font color=red><b>*</b></font></td>
				<td><select id="Gender" size="1" name="memberGender">
						<option value="男" ${Gender=="男"?'selected':''}>男</option>
						<option value="女" ${Gender=="女"?'selected':''}>女</option>
				</select></td>
			</tr>

			<tr>
				<td>出生日期:</td>
				<td><input name="dateOfBirth" id="f_date2" type="text"></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><input type="TEXT" name="memberEmail" size="45"
					value="<%=(memVO == null) ? "inputEmail" : memVO.getMemberEmail()%>" /></td>
			</tr>

			<tr>
				<td>會員電話:</td>
				<td><input type="TEXT" name="memberPhone" size="45"
					value="<%=(memVO == null) ? "0900999000" : memVO.getMemberPhone()%>" /></td>
			</tr>

			<tr>
				<td>會員地址:</td>
				<td><input type="TEXT" name="memberAddress" size="45"
					value="<%=(memVO == null) ? "InputAddress" : memVO.getMemberAddress()%>" /></td>
			</tr>


			<tr>
				<td>新增圖片:</td>
				<td><input type="file" name="upfile1"></td>
			</tr>

			<tr>
				<td>銀行帳號:</td>
				<td><input type="TEXT" name="bankAccount" size="45"
					value="<%=(memVO == null) ? "InputBankAccount" : memVO.getBankAccount()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>
<%
java.sql.Date dateOfBirth = null;
try {
	dateOfBirth = memVO.getDateOfBirth();
} catch (Exception e) {
	dateOfBirth = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        
$.datetimepicker.setLocale('zh');
$('#f_date2').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=dateOfBirth%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});
</script>
</html>