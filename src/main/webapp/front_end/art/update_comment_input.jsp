<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.comment.model.*"%>

<%
CommentVO commentVO = (CommentVO) request.getAttribute("commentVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章修改</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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
				<h3>留言修改</h3>
				<h4>
					<a href="comment_select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>留言修改:</h3>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/art/Comment.do" enctype="multipart/form-data">
		<table>
			<tr>
				<td>留言編號:<font color=red><b>*</b></font></td>
				<td><%=commentVO.getCommentId()%></td>
			</tr>
			<tr>
				<td>文章內容:</td>
				<td><input type="TEXT" name="commentContent" size="45"
					value="<%=commentVO.getCommentContent()%>" /></td>
			</tr>
			<tr>
				<td>文章狀態:</td>
				<td><input type="TEXT" name="memberId" size="45"
					value="<%=commentVO.getCommentStatus()%>" /></td>
			</tr>

<!-- 			<tr> -->
<!-- 				<td>新增圖片:</td> -->
<!-- 				<td><input type="file" name="upfile1"><br></td> -->
<!-- 			</tr> -->
			<tr>
				<td>圖片:</td>
				<td><input class="form-control" type="file" name="upfile1"
					size="45" value="<%=commentVO.getCommentPicture()%>" /><br></td>
			</tr>

			<tr>
				<td><img
					src="<%=request.getContextPath()%>/ImageServlet?commentId=${commentVO.commentId}"
					width=300px height=200px></td>
			</tr>
			
<!-- 			<div> -->
<!-- 			<label for="commentPicture">照片:</label> -->
<!-- 			<input id ="commentPicture" name="upFiles" type="file" onclick="previewImage()" multiple="multiple" /> -->
<%--             可以不修改圖片 <span  id ="upFiles.errors" class="error">${errorMsgs.upFiles}</span> --%>
<%-- 			<div id="blob_holder"><img src="<%=request.getContextPath()%>/front_end/art/DBGifReader?commentId=${commentVO.commentId}" width="100px"></div> --%>
<!-- 			</div> -->
			
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="commentId" value="${commentVO.commentId}">
		<input type="submit" value="送出修改">
	</FORM>
	


</script>
</body>
</html>