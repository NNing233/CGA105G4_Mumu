<!-- 目前最新都用這個檔案 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--需要裝兩個檔案才不會出現錯誤 --%>
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.orderlist.model.*"%>



<%
String location = (request.getQueryString() == null)
		? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();
HttpSession session1 = request.getSession();
session1.setAttribute("location", location);
%>
<%
OrderService orderSvc = new OrderService();
List<OrderVO> list = orderSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%
OrderListService orderlistSvc = new OrderListService();
List<OrderListVO> orderList = orderlistSvc.getAll();
pageContext.setAttribute("orderList", orderList);
%>

<!DOCTYPE html>



<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<title>所有訂單資料</title>

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
	width: 1000px;
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
<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
		<div id="layoutSidenav_content">

				<!--=========================請注意: 寫你要的內容 -->
			<main>
				<div class="container ">
				<table id="table-1">
				<tr>
					<h3>所有訂單資料</h3>
				</tr>
				</table>

				<table class="table table-striped table-hover">
					<thead style="white-space: nowrap;">
					<tr>
						<th>訂單編號</th>
						<th>會員編號</th>
						<th>收貨地址</th>
						<th>優惠券折扣碼</th>
						<th>訂單狀態</th>
						<th>付款方式</th>
						<th>物流方式</th>
						<th>總價</th>
						<th>訂單折扣金額</th>
						<th>實付</th>
						<th>下單時間</th>
						<th>詳細訂單資料</th>
						<th>更新訂單狀態</th>
						<th>狀態更新</th>
					</tr>
					</thead>
					<%@ include file="page1.file"%>
					<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">

						<tr>
							<td>${orderVO.orderId}</td>
							<td>${orderVO.memberId}</td>
							<td>${orderVO.orderAddress}</td>
							<td>${orderVO.couponCodeNumber}</td>
							<td>${orderVO.orderStatus}</td>
							<td>${orderVO.orderPayment}</td>
							<td>${orderVO.orderDelivery}</td>
							<td>${orderVO.orderTotalAmount}</td>
							<td>${orderVO.orderDiscount}</td>
							<td>${orderVO.orderAfterDiscount}</td>
							<td>${orderVO.orderTime}</td>
							<td>
								<!-- Button to Open the Modal -->

								<button type="button" class="btn btn-outline-primary"
									data-bs-toggle="modal"
									data-bs-target="#myModal_${orderVO.orderId}">詳細資料</button>

								<div class="modal" id="myModal_${orderVO.orderId}">
									<div class="modal-dialog modal-xl">
										<div class="modal-content">

											<!-- Modal Header -->
											<div class="modal-header">
												<h4 class="modal-title">訂單詳細資料</h4>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal"></button>
											</div>

											<!-- Modal body -->

											<div class="modal-body">
												<table class="table table-striped">
													<thead>
														<tr>
															<th>產品選項編號</th>
															<th>產品選項數量</th>
															<th>訂單明細金額</th>
														</tr>
													</thead>
													<tbody>

														<c:forEach var="orderListVO" items="${orderList}">
															<c:if test="${orderListVO.orderId == orderVO.orderId}">
																<tr>
																	<td>${orderListVO.prodOptId}</td>
																	<td>${orderListVO.prodOptQuantity}</td>
																	<td>${orderListVO.orderExtendedListPrice}</td>
																</tr>
															</c:if>

														</c:forEach>


													</tbody>
												</table>
											</div>

											<!-- Modal footer -->
											<div class="modal-footer">
												<button type="button" class="btn btn-danger"
													data-bs-dismiss="modal">關閉</button>
											</div>

										</div>
									</div>
								</div>

							</td>
							<td><select class="custom-select" name="orderStatus"
								value="${orderVO.orderStatus}">
									<option selected>狀態選擇</option>

									<option value="1">待出貨</option>
									<option value="2">已出貨</option>
									<option value="3">已到貨</option>
									<option value="4">訂單完成</option>
									<option value="5">換貨</option>
									<option value="6">退貨</option>
									<option value="7">已取消</option>
							</select></td>

							<td><form method="post"
									action="<%=request.getContextPath()%>/back_end/order/order.do"
									style="margin-bottom: 0px;" enctype="multipart/form-data">

									<input type="hidden" name="orderStatus" class="orderStatus"
										value="${orderVO.orderStatus.getValue()}"> <input
										type="hidden" name="orderId" value="${orderVO.orderId}">
									<input type="submit" name="action" class="statusChangBtn"
										value="狀態更新">
								</form></td>


						</tr>
					</c:forEach>


				</table>

				<%@ include file="page2.file"%>
					</div>
				</div>
				<!--**********************請注意: 結束要的內容**********************-->
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Your Website 2022</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"
		integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/back_end/assets/demo/chart-area-demo.js"></script>
	<script src="<%=request.getContextPath()%>/back_end/assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="js/datatables-simple-demo.js"></script>

	<script>
		$('.custom-select').on(
				'change',
				function() {
					$(this).parents('td').next().find('.orderStatus').val(
							$(this).val());
					// 											$(this).prev('orderIdValue').val()
				})
	</script>
</body>
</html>