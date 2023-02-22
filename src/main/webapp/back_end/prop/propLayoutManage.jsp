<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/back_end/css/styles.css"
	rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>
<style>
.switch {
	position: relative;
	display: inline-block;
	width: 60px;
	height: 34px;
}

.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 26px;
	width: 26px;
	left: 4px;
	bottom: 4px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}
</style>
</head>
<body class="sb-nav-fixed">
	<%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
	
		
		<div id="layoutSidenav_content">
			<main>

				<!-- 請注意: 寫你要的內容 -->
				<!-- <h1>測試測試</h1> -->
				<div class="container-fluid px-4">


					<div class="row">
						<div class="col-2">
							<h4 class="my-4">募資分類列表</h4>
						</div>
						<div class="col-7"></div>
						<div class="col-3 d-flex align-items-center">
							<!-- Button trigger modal -->
							<button type="button" class="btn btn-primary"
								data-bs-toggle="modal" data-bs-target="#exampleModal"
								style="position: relative; left: 20px;">新增募資分類</button>
						</div>
					</div>



					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="role-form" method="post"
									action="<%=request.getContextPath()%>/proptype/proptype.do">
									<div class="modal-header">

										<h4 class="modal-title" style="text-align: left;">新增募資分類</h4>
									</div>
									<div class="modal-body">


										<div class="form-group col-md-12">
											<label for="propTypeName">分類名稱</label> <input
												name="propTypeName" id="propTypeName" class="form-control">
										</div>
										<input type="hidden" name="action" value="addPropType" />

										<div class="clearfix"></div>
									</div>
									<div class="modal-footer">

										<button type="submit" class="btn btn-success">新增</button>
										<button type="button" class="btn btn-default"
											data-bs-dismiss="modal">關閉</button>

									</div>
								</form>
							</div>
						</div>
					</div>

					<form action="<%=request.getContextPath()%>/proptype/proptype.do"
						method="post">
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col" class="col-2">#</th>
									<th scope="col" class="col-8">分類名稱</th>
									<th scope="col" class="col-2">啟用/停用</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="propType" items="${propTypeList }"
									varStatus="loop">
									<tr>
										<th scope="row">${loop.index+1}</th>
										<td><span class="propTypeName"
											style="min-width: 95px; display: inline-block; margin-right: 30px; padding: 4px; padding-right: 10px; padding-left: 10px; border-radius: 6px;">${propType.proposalTypeName}</span>
											<button class="editBtn"
												style="border-radius: 10px; border: 0; text-align: center; background: transparent; border: solid 2px #222;">
												<i class="fa-solid fa-pen-to-square"></i>
											</button> <input type="hidden" name="propTypeName"
											value="${ propType.proposalTypeName}" /><input type="hidden"
											name="propTypeId" value="${ propType.proposalTypeId}" /></td>
										<td class="status"><label class="switch"> <input
												type="checkbox" name="propType"
												value="${propType.proposalTypeId }"
												${(propType.proposalTypeStatus==1)?'checked':'' }> <span
												class="slider round"></span>
										</label></td>

									</tr>
								</c:forEach>
								<input type="hidden" name="action" value="propTypeStatusChange" />

							</tbody>
						</table>
						<input type="submit" />
					</form>
				</div>


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
	<script src="<%=request.getContextPath()%>/back_end/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/assets/demo/chart-area-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/back_end/js/datatables-simple-demo.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


	<c:if test="${param.change == 'success'}">
		<script>
			Swal.fire('更新成功', '', 'success');
		</script>
	</c:if>

	<c:if test="${param.add == 'success'}">
		<script>
			Swal.fire('新增分類成功', '', 'success');
		</script>
	</c:if>

	<script>
		$('tbody').on('click', '.editBtn', function(e) {
			e.preventDefault();
			console.log(123);
			let editTarget = $(this).prev();
			let value = editTarget.attr('contenteditable');

			if (value == 'false' || value == null) {
				editTarget.attr('contenteditable', 'true');
				editTarget.focus();
				editTarget.css("border", "solid 2px black");
			} else {
				editTarget.attr('contenteditable', 'false');
				editTarget.css("border", "");
				$(this).next().val(editTarget.text());
			}
		})
	</script>
</body>
</html>