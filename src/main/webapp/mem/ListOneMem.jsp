<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html>
<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>會員資料 - listOneEmp.jsp</title>

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
	width:1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/main.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>暱稱</th>
		<th>註冊日期</th>
		<th>會員狀態</th>
		<th>會員姓名</th>
		<th>性別</th>
		<th>生日</th>
		<th>Email</th>
		<th>手機號碼</th>
		<th>地址</th>
		<th>照片</th>
		<th>銀行帳號</th>
	</tr>
	<tr>
		<td><%=memVO.getMemberId()%></td>
		<td><%=memVO.getMemberAccount()%></td>
		<td><%=memVO.getMemberPassword()%></td>
		<td><%=memVO.getMemberNickname()%></td>
		<td><%=memVO.getSignUpDatetime()%></td>
		<td><%=memVO.getMemberStatus()%></td>
		<td><%=memVO.getMemberName()%></td>
		<td><%=memVO.getMemberGender()%></td>
		<td><%=memVO.getDateOfBirth()%></td>
		<td><%=memVO.getMemberEmail()%></td>
		<td><%=memVO.getMemberPhone()%></td>
		<td><%=memVO.getMemberAddress()%></td>
		<td><img src="<%=request.getContextPath()%>/ImageServlet?memberID=${memVO.memberID}"width=300px height=200px></td>
		<td><%=memVO.getBankAccount()%></td>
	</tr>
</table>

</body>
</html>