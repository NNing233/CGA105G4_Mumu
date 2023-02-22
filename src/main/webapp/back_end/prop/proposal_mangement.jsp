<%@page import="java.util.stream.Collectors"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import = "com.prop.model.*" import = "com.proptype.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
HttpSession Asession = request.getSession();
String location = (request.getQueryString() == null)
? request.getRequestURL().toString()
: request.getRequestURL().append("?").append(request.getQueryString()).toString();

Asession.setAttribute("location", location);
%> 
<%-- <c:if test="${empty sessionScope.mem}"> --%>
<%--  <jsp:forward page="../login.jsp"/> --%>
<%-- </c:if> --%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	request.setCharacterEncoding("UTF-8");
    PropService propSrc = new PropService(); 
	List<PropVO> list = propSrc.getAll();
    pageContext.setAttribute("list",list);
    
    PropTypeService propTypeSvc = new PropTypeService(); 
    List<PropTypeVO> proposalTypeList = propTypeSvc.getAll();
    pageContext.setAttribute("proposalTypeList",proposalTypeList);
    
    List<PropVO> filterList = list.stream().filter(propVO -> (propVO.getProposalStatus() == ProposalStatus.未上架) || (propVO.getProposalStatus() == ProposalStatus.已上架未達標) || (propVO.getProposalStatus() == ProposalStatus.已上架達標)).collect(Collectors.toList());
    pageContext.setAttribute("filterList",filterList);
    
//     List<PropVO> ProposalType = list.stream().filter(propVO -> (propVO.getProposalStatus() == ProposalStatus.未上架) || (propVO.getProposalStatus() == ProposalStatus.已上架未達標) || (propVO.getProposalStatus() == ProposalStatus.已上架達標)).collect(Collectors.toList());
//     pageContext.setAttribute("filterList",filterList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
	
<!--     <meta http-equiv="X-UA-Compatible" content="IE=edge" />	 -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>MuMu後台 - 募資中專案管理</title>
    <link href="../css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
	<style>
		.modeltitle-center {
		    text-align: center;
		}
	</style>
</head>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
	
<!--     <meta http-equiv="X-UA-Compatible" content="IE=edge" />	 -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>MuMu後台 - 募資中專案管理</title>
    <link href="../css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
	<script src="../js/picture.js"></script>
	<style>
		.modeltitle-center {
		    text-align: center;
		}

	</style>
</head>
<body class="sb-nav-fixed">
    <%@ include file="/back_end/navbar/navbar.jsp"%>
	<div id="layoutSidenav">
	<%@ include file="/back_end/sidebar/sidebar.jsp"%>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">募資中專案管理</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="index.html">MuMu後台</a></li>
                        <li class="breadcrumb-item active">募資中專案管理</li>
                    </ol>
                    
                    <div class="card mb-4">
                        <div class="card-body table-responsive-sm">
                           <table class="table table-hover">
 								<thead style="white-space: nowrap;">
								    <tr>
								      <th class="text-center" scope="col">提案案號</th>
								      <th class="text-center" scope="col">提案帳號</th>
<!-- 								      <th scope="col">分類</th> -->
								      <th class="text-center" scope="col">名稱</th>
								      <th class="text-center" scope="col">開始時間</th>
								      <th class="text-center" scope="col">結束時間</th>
								      <th class="text-center" scope="col">延長天數</th>
<!-- 								      <th class="text-center" scope="col">延長次數</th> -->
								    </tr>
								 </thead>
								
<!-- 								<form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"> -->
<!-- 						            <div class="input-group" style="width:300px"> -->
<!-- 						                <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." -->
<!-- 						                    aria-describedby="btnNavbarSearch" style="width:250px"/> -->
<!-- 						                <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i -->
<!-- 						                        class="fas fa-search" style="display:inline"></i></button> -->
<!-- 						            </div> -->
<!-- 						        </form> -->
<!-- 						        <div > -->
								 <%@ include file="pagewb1.file" %>
<!-- 								 </div> -->
								  <tbody>
<%-- 								  <%@ include file="page1.file" %>  --%>
								  <c:forEach var="propVO" items="${filterList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								    <tr>
								      <th class="text-center" scope="row">${propVO.proposalId}</th>
								      <td class="text-center">${propVO.memberId}</td>
<%-- 								      <td>${propVO.proposalTypeId}</td> --%>
								      <td class="text-center">${propVO.proposalName}</td>
<%-- 								      <td>${propVO.proposalPicture}</td> --%>
<%-- 								      <td>${propVO.proposalArticle}</td> --%>
								      <td class="text-center">${propVO.proposalStartDatetime}</td>
								      <td class="text-center">${propVO.proposalEndDatetime}</td>
								      <td class="text-center">${propVO.extendedDays}</td>
<%-- 								      <td class="text-center">${propVO.extendedTimes}</td> --%>
								      <td>
<%-- 								      	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/prop/prop.do" style="margin-bottom: 0px;"> --%>
	   								      	 <button type="submit" class="btn btn-outline-primary btn-sm" data-bs-toggle="modal" data-bs-target="#getAll-${propVO.proposalId}">提案詳情</button>
										     <input type="hidden" name="proposalId"  value="${propVO.proposalId}">
										     <input type="hidden" name="query" value="allQuery">
										     
										    <div class="modal fade" id="getAll-${propVO.proposalId}" tabindex="-1" aria-labelledby="getAllLabel" aria-hidden="true">
											  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
											    <div class="modal-content">
											      <div class="modal-header">
											        <h5 class="modal-title text-center" id="getAllLabel" ">&nbsp;&nbsp;&nbsp;提案號: ${propVO.proposalId} &nbsp;---&nbsp; ${propVO.proposalName}</h5>
											        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
											      </div>
											      <div class="modal-body">
<%-- 											      <img alt="" src="<%=request.getContextPath()%>/back-end/prop/prop.do?action=showImg&proposalId=${propVO.proposalId}" onerror="this.src=images/image_not_available.png"></img> --%>
											        <ul class="list-group">                                                  
													  <li class="list-group-item"><b>提案分類: <span style="margin-left: 10px"><c:if test="${propVO.articleId == 0}" >${propVO.getPropTypeVO().proposalTypeName}</c:if>
													  											<c:if test="${propVO.articleId != 0}" >
																							   	 	許願 ----- 文章編號: &nbsp;${propVO.articleId}
																								</c:if>
													  										  </span></b></li>
													  <li class="list-group-item"><b>提案狀態: <span style="margin-left: 10px">${propVO.proposalStatus}</span></b></li>
													  <li class="list-group-item"><b>提案文案: <span style="margin-left: 10px">${propVO.proposalArticle}</span></b></li>
													  <li class="list-group-item"><b>提案圖片: <img alt="" src="<%=request.getContextPath()%>/back_end/prop/prop.do?action=showPropImg&proposalId=${propVO.proposalId}" style="width:120px" onerror="this.src=images/Meow.jpg"></img></b></li>
													  <li class="list-group-item"><b>累積金額: <span style="margin-left: 10px">${propVO.accumulativeDonateMoney}</span></b></li>
													  <li class="list-group-item"><b>目標金額: <span style="margin-left: 10px">${propVO.targetDonateMoney}</span></b></li>
													  <li class="list-group-item"><b>開始時間: <span style="margin-left: 10px">${propVO.proposalStartDatetime}</span></b></li>
													  <li class="list-group-item"><b>結束時間: <span style="margin-left: 10px">${propVO.proposalEndDatetime}</span></b></li>
													  <li class="list-group-item"><b>製作時間: <span style="margin-left: 10px">${propVO.productProduceTime}</span></b></li>
													  <li class="list-group-item"><b>配送時間: <span style="margin-left: 10px">${propVO.targetDeliveryTime}</span></b></li>  
													  <li class="list-group-item"><b>延長天數: <span style="margin-left: 10px">${propVO.extendedDays}</span></b></li>  
													  <li class="list-group-item"><b>延長次數: <span style="margin-left: 10px">${propVO.extendedTimes}</span></b></li>  
													  <li class="list-group-item"><b>審核狀態: <span style="margin-left: 10px">${propVO.proposalApprovalStatus}</span></b></li>
													  <li class="list-group-item"><b>審核失敗原因: <span style="margin-left: 10px">${propVO.proposalRejectedReason}</span></b></li>
<%-- 													  <li class="list-group-item"><b>文章編號: <span style="margin-left: 10px">${propVO.articleId}</span></b></li>   --%>
													</ul>
											      </div>
											      <div class="modal-footer d-flex justify-content-center">
											        <h6 class="text-center">提案帳號: ${propVO.memberId}</h6>
											      </div>
											    </div>
											  </div>
											</div>
<!-- 										</FORM> -->
									  </td>
<!-- 								      <td> -->
<%-- 								      	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/prop/prop.do" style="margin-bottom: 0px;"> --%>
<!-- 										     <button type="submit" class="btn btn-outline-primary btn-sm">修改提案</button> -->
<%-- 										     <input type="hidden" name="couponID"  value="${couponVO.couponID}"> --%>
<!-- 										     <input type="hidden" name="action" value="update"> -->
							     
<!-- 										</FORM> -->
<!-- 								      </td> -->
									 <td>
<%-- 								      	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/prop/prop.do" style="margin-bottom: 0px;"> --%>
	   								      	 <button type="submit" class="btn btn-outline-primary btn-sm" data-bs-toggle="modal" data-bs-target="#getAll2-${propVO.proposalId}">修改提案</button>
										     <input type="hidden" name="proposalId"  value="${propVO.proposalId}">
										     <input type="hidden" name="query" value="allQuery">
										   <FORM id="updateForm" enctype='multipart/form-data' METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/prop/prop.do" style="margin-bottom: 0px;">  
										    <div class="modal fade" id="getAll2-${propVO.proposalId}" tabindex="-1" aria-labelledby="getAll2Label" aria-hidden="true">
											  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
											    <div class="modal-content">
											      <div class="modal-header">
											        <h5 class="modal-title text-center" id="getAll2Label" >&nbsp;&nbsp;&nbsp;提案號: ${propVO.proposalId} &nbsp;--&nbsp; ${propVO.proposalName}</h5>
											        <h6 class="modal-title text-right">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 提案帳號: ${propVO.memberId}</h6>
											        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="margin-left:0;"></button>
											      </div>
											  		
											      <div class="modal-body">
											        <ul class="list-group">      
											          <li class="list-group-item"><b>提案名稱: </b><input type="text" value="${propVO.proposalName}" name="proposalName" class="form-control is-valid" required><div class="invalid-feedback" >不可空白</div></li>
<%-- 											          <li class="list-group-item"><b>提案名稱: <span style="margin-left: 10px"><input type="text" value="${propVO.proposalName}" name="proposalName" class="form-control"></span></b><div class="invalid-feedback">延長天數為0至120天</div></li> --%>
											          <li class="list-group-item"><b>提案帳號: </b><input type="text" value="${propVO.memberId}" name="memberId" class="form-control is-valid" pattern="^[a-zA-Z0-9]{1,20}$" required><div class="invalid-feedback">不可空白，請填入欲更新的提案者帳號</div></li>
<%-- 											          <li class="list-group-item"><b>提案帳號: <span style="margin-left: 10px"><input type="text" value="${propVO.memberId}" name="memberId" class="form-control"></span></b><div class="invalid-feedback">延長天數為0至120天</div></li> --%>
													  <li class="list-group-item"><b>提案分類: <c:if test="${propVO.articleId == 0}" var="haveArticleId"><span style="margin-left: 10px">
													  											
													  											<select id="ProposalTypeSelect" name="selectedProposalType">
<%-- 													  											<option disabled>${propVO.articleId != 0 ? "許願": propVO.getPropType().proposal_type_name}</option> --%>
													  											<c:forEach items="${proposalTypeList}" var="proposalType">
																								  <option id="${propVO.proposalId}-option-${proposalType.proposalTypeId}" value="${proposalType.proposalTypeId}">${propVO.articleId != 0 ? "許願": proposalType. proposalTypeName}</option>
																								  <script>
																								  	if(${propVO.articleId == 0}){
																								  		document.querySelectorAll("option[value='7']").forEach(function(item) {
																								  		    item.disabled = true;
																								  		  });
																								  	}
// 																								  	}else if(${propVO.articleId} != "0")
// 																								  		document.getElementById(#option).disabled = true;
																								</script>
																								</c:forEach>
																							   </select>
<!-- 																							   <input type="hidden" name="proposalType"> -->
													  										  </span></c:if>
													  										  <c:if test="${propVO.articleId != 0}" >
																							   	 	&nbsp;&nbsp;許願 ----- 文章編號: &nbsp;${propVO.articleId}
																							   	 	<input type="hidden" name="selectedProposalType" value="7">
																							   	 </c:if>
													  										  </b></li>
													  <li class="list-group-item"><b>提案狀態: <span style="margin-left: 10px">
													  											<select name="selectedProposalStatus">
																			 					  <option selected disabled  style="text-align: justify;" value="0">${propVO.proposalStatus}</option>
																                                  <option style="text-align: justify;" value="1">未上架</option>
																                                   <option style="text-align: justify;" value="2">已上架未達標</option>
																                                   <option style="text-align: justify;" value="3">已上架達標</option>
																							   </select>					
																							   <input type="hidden" name="proposalStatus"> 
																							   </span></b></li>
													  <li class="list-group-item"><b class="text-center">提案文案: </b><div style="margin-left: 10px;"><textarea  name="proposalArticle" class="form-control is-valid" required>${propVO.proposalArticle}</textarea><div class="invalid-feedback" >不可空白</div></div></li>
													  <li class="list-group-item"><b>提案圖片: </b><input type="file" id="showimg" multiple name="uploadProposalImg" value="${propVO.proposalPicture}"/>  <div class='row'><div id='previewMultiple'></div></div></li>
													  <li class="list-group-item"><b>累積金額: </b><input type="text" value="${propVO.accumulativeDonateMoney}" name="accumulativeDonateMoney" class="form-control is-valid" pattern="^[1-9]\d*$" required><div class="invalid-feedback">請輸入一正整數</div></li>
													  <li class="list-group-item"><b>目標金額: </b><input type="text" value="${propVO.targetDonateMoney}" name="targetDonateMoney" class="form-control is-valid" pattern="^[1-9]\d*$" required><div class="invalid-feedback">請輸入一正整數</div></li>
													  <li class="list-group-item"><b>開始時間: </b><input name="proposalStartDatetime" type="date"  value="${propVO.proposalStartDatetime}" class="form-control is-valid" required><div class="invalid-feedback">不可為空，請選擇日期</div> </li>
													  <li class="list-group-item"><b>結束時間: </b><input name="proposalEndDatetime" type="date"  value="${propVO.proposalEndDatetime}" class="form-control is-valid" required><div class="invalid-feedback">不可為空，請選擇日期</div> </li>
													  <li class="list-group-item"><b>製作時間: </b><input name="productProduceTime" type="date"  value="${propVO.productProduceTime}" class="form-control is-valid" required><div class="invalid-feedback">不可為空，請選擇日期</div> </li>
													  <li class="list-group-item"><b>配送時間: </b><input name="targetDeliveryTime" type="date"  value="${propVO.targetDeliveryTime}" class="form-control is-valid" required><div class="invalid-feedback">不可為空，請選擇日期</div> </li>
													  <li class="list-group-item"><b>延長天數: </b><input name="extendedDays" type="text" value="${propVO.extendedDays}" class="form-control is-valid" pattern="^(?:[1-9]?\d|100)$" required><div class="invalid-feedback">延長天數為0至100天</div></li>  
													  <li class="list-group-item"><b>延長次數: </b><input name="extendedTimes" type="text" value="${propVO.extendedTimes}" class="form-control is-valid" required></li>  
													  <li class="list-group-item"><b>文章編號: ${propVO.articleId}</b></li>  
													</ul>
													<script>
																								  	if(${propVO.articleId} == 0){
																								  		document.getElementById(${propVO.proposalId}+"option-7").disabled = true;
																								  	}
																								</script>
<!-- 													<script> -->
<%-- // 													 if (typeof ${propVO.articleId} !== "undefined" && ${propVO.articleId} !== null) { --%>
<!-- // 														    document.getElementById("selectedProposalType").value = "許願"; -->
<!-- // 														  } -->
<!-- 													</script> -->
											      </div>
											      
											      <div class="modal-footer d-flex justify-content-center">
											         <div class="text-center">
											         <input type="hidden" name="proposalId" value="${propVO.proposalId}">
                                                    <button type="submit" class="btn btn-outline-danger"
                                                        style="height: 10%;" name="action" value="updateFundingProposal">修改</button>
                                                  </div>
                                                 
											      </div>
											      
											    </div>
											  </div>
											  </div>
											</Form>
<!-- 										</FORM> -->
									  </td>
								      <td>
								      	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/prop/prop.do" style="margin-bottom: 0px;">
	   								      	 <button type="submit" class="btn btn-outline-primary btn-sm">延長提案</button>
										     <input type="hidden" name="proposalId"  value="${propVO.proposalId}">
										     <input type="hidden" name="action" value="extend">
										</FORM>
									  </td>
								      <td>
								      	<FORM METHOD="post"  
								      	ACTION="<%=request.getContextPath()%>/back_end/prop/prop.do" style="margin-bottom: 0px;">
	   								      	 <button type="submit" class="btn btn-outline-primary btn-sm">下架提案</button>
										     <input type="hidden" name="proposalId"  value="${propVO.proposalId}">
										     <input type="hidden" name="action" value="drop">
										</FORM>
								      </td>                        
								    </tr>
								    </c:forEach>
								  </tbody>
							</table>
                        </div>
                    </div>
                </div>
                <div> <%@ include file="pagewb2.file"%> </div> 
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
    
    <script>
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function() {
		'use strict';
		window.addEventListener('load', function() {
			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.getElementsByClassName('needs-validation');
			// Loop over them and prevent submission
			var validation = Array.prototype.filter.call(forms, function(form) {
				form.addEventListener('submit', function(event) {
					if (form.checkValidity() === false) {
						event.preventDefault();
						event.stopPropagation();
					}
					form.classList.add('was-validated');
				}, false);
			});

			//Below code to check every input field
			var inputs = document.getElementsByClassName('form-control');
			var input_Validation = Array.prototype.filter.call(inputs,
					function(input) {

						input.addEventListener('blur', function(event) {

							input.classList.remove('is-invalid')
							input.classList.remove('is-valid')

							if (input.checkValidity() === false) {
								input.classList.add('is-invalid')
							} else {
								input.classList.add('is-valid')
							}
							// form.classList.add('was-validated');

						}, false);
					});

			//End of block that checks each input field

		}, false);
	})();

// 	$("#account").on('blur', function() {
// 		console.log('處理驗證');
// 	})
</script>
<script>
$(document).ready(function() {
	$("#showimg").change(function(){
	$("#previewMultiple").html(""); // 清除預覽
	readURL(this);
	});

	function readURL(input){
	if (input.files && input.files.length >= 0) {
	for(var i = 0; i < input.files.length; i ++){
	var reader = new FileReader();
	reader.onload = function (e) {

	var img = '<div class=col-md-6><div class=thumbnail><img src="' + e.target.result + '"></div></div>';
	$("#previewMultiple").append(img);
	}
	reader.readAsDataURL(input.files[i]);
	}
	}else{
	var noPictures = $("<p>目前沒有圖片</p>");
	$("#previewMultiple").append(noPictures);
	}
	}

	});
</script>

<!--    	<script src="../js/dropdown.js"></script> -->
	<script src="../js/picture.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="../js/scripts.js"></script>
    
     <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>

</body>
</html>