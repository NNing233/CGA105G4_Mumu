<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>會員中心-首頁</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #FFB5B5;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 1px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: white;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>

<body bgcolor = 'white'>
<table id="table-1" >
   <tr><td><h3>會員中心</h3><h4><img src="images/main.png" width="100" height="100" border="0"></h4></td></tr>
</table>


<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<ul>
  <li><a href='listAllMem.jsp'>點我查看</a> 所有會員資料 <br><br></li>
 
  <li>
    <FORM METHOD="post" ACTION="mem.do" >
        <b>輸入會員ID (如1):</b>
        <input type="text" name="memberID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
 
 <jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />
  
  <li>
     <FORM METHOD="post" ACTION="mem.do" >
       <b>選擇會員ID:</b>
       <select size="1" name="memberID">
         <c:forEach var="memVO" items="${memberSvc.all}" > 
          <option value="${memVO.memberID}">${memVO.memberID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>
<h3>會員管理</h3>

<ul>
  <li><a href='addMem.jsp'>點這裡</a>新增一個會員資料</li>
</ul>  
</body>
</html>