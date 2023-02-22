<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemService memSvc = new MemService();
    List<MemVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<title>所有會員資料 - 這是測試~!!!</title>
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
    color: deeppink;
    display: inline;
  }
</style>

<style>
  table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid black;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料</h3>
		<img src="images/images.png" width="100" height="65" border="0">
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員暱稱</th>
		<th>註冊日期</th>
		<th>會員狀態</th>
		<th>真實姓名</th>
		<th>性別</th>
		<th>生日</th>
		<th>Email</th>
		<th>電話</th>
		<th>住址</th>
		<th>照片</th>
		<th>銀行帳號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memVO.memberId}</td>
			<td>${memVO.memberAccount}</td>
			<td>${memVO.memberPassword}</td>
			<td>${memVO.memberNickname}</td>
			<td>${memVO.signUpDatetime}</td>
			<td>${memVO.memberStatus}</td> 
			<td>${memVO.memberName}</td>
			<td>${memVO.memberGender}</td>
			<td>${memVO.dateOfBirth}</td>
			<td>${memVO.memberEmail}</td>
			<td>${memVO.memberPhone}</td>
			<td>${memVO.memberAddress}</td>
			<td><img src="<%=request.getContextPath()%>/ImageServlet?memberID=${memVO.memberId}"width=300px height=200px></td>
			<td>${memVO.bankAccount}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memberID"  value="${memVO.memberId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="memberID"  value="${memVO.memberId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>