<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>

<%
    String location = (request.getQueryString()==null)? request.getRequestURL().toString() :request.getRequestURL().append("?").append(request.getQueryString()).toString();
    HttpSession session1 = request.getSession();
    session1.setAttribute("location", location);
%>
<!DOCTYPE html>
<%
//CouponVO couponVO = (CouponVO) request.getAttribute("couponVO"); EmpServlet.java (Concroller) 存入req的orderListVO物件 (包括幫忙取出的orderListVO, 也包括輸入資料錯誤時的orderListVO物件)
%>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Dashboard - SB Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="../css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>優惠券資料修改</title>

<style>
table#table-1 {
	background-color: white;
/* 	border: 2px solid black; */
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
 	width: 550px;	/*450改550 */
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
<body class="sb-nav-fixed">
    <%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
        <div id="layoutSidenav_content">
            <main>

<!--=========================請注意: 寫你要的內容 -->
<table id="table-1">
	<tr><td>
		 <h3>優惠券修改</h3>
<!-- 		 <h4><a href="listAllMemberCouponTest1.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回到歷史訂單</a></h4> -->
	</td></tr>
</table>

<!-- <h3>優惠券修改:</h3> -->

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="coupon.do" name="form1">
<table>
    <tr>
		<td>優惠券編號:<font color=red><b>*</b></font></td>
		<td>${couponVO.couponID==null?param.couponID:couponVO.couponID}</td>
	</tr>
	<tr>
		<td>優惠券名稱:</td>
		<td><input type="TEXT" name="couponName" size="45" 
			 value="${couponVO.couponName==null?param.couponName:couponVO.couponName}"/></td><td>${errorMsgs.couponName}</td>
	</tr>
	<tr>
		<td>優惠券折數:</td>
		<td><input type="TEXT" name="discountPercentage" size="45" 
			 value="${couponVO.discountPercentage==null?param.discountPercentage:couponVO.discountPercentage}"/></td><td>${errorMsgs.discountPercentage}</td>
	</tr>
	<tr>
		<td>優惠券折讓:</td>
		<td><input type="TEXT" name="discountAmount" size="45" 
			 value="${couponVO.discountAmount==null?param.discountAmount:couponVO.discountAmount}"/></td><td>${errorMsgs.discountAmount}</td>
	</tr>
	<tr>
		<td>優惠券開始時間:</td>
		<td><input name="couponStartDatetime" id="start_date" type="text"/></td><td>${errorMsgs.couponStartDatetime}</td>
	</tr>
	<tr>
		<td>優惠券結束時間:</td>
		<td><input name="couponEndDatetime" id="end_date" type="text"/></td><td>${errorMsgs.couponEndDatetime}</td>
	</tr>	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="couponID" value="${couponVO.couponID=null?param.couponID:couponVO.couponID}">
<input type="submit"  value="送出修改"></FORM>
<!--=========================請注意: 結束要的內容 -->

            </main>
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; Your Website 2022</div>
                        <div>
                            <a href="#">Privacy Policy</a>
                            &middot;
                            <a href="#">Terms &amp; Conditions</a>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath()%>/back_end/assets/demo/chart-area-demo.js"></script>
    <script src="<%=request.getContextPath()%>/back_end/assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
        $('#start_date').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '${couponVO.couponStartDatetime==null?param.couponStartDatetime:couponVO.couponStartDatetime}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>
<script>
        $.datetimepicker.setLocale('zh');
        $('#end_date').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '${couponVO.couponEndDatetime==null?param.couponEndDatetime:couponVO.couponEndDatetime}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>   
</html>