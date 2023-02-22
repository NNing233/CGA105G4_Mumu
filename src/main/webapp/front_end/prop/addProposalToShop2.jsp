<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.stream.Collectors"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.prop.model.*" import="com.mem.model.*"
	import="com.propopt.model.*" import="com.sponrecord.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
HttpSession Asession = request.getSession();
String location = (request.getQueryString() == null)
		? request.getRequestURL().toString()
		: request.getRequestURL().append("?").append(request.getQueryString()).toString();

Asession.setAttribute("location", location);
%>

<c:if test="${empty sessionScope.mem}">
	<jsp:forward page="../login.jsp" />
</c:if>

<%
request.setCharacterEncoding("UTF-8");
PropService propSrc = new PropService();

List<PropVO> list = propSrc.getTheProposal(((MemVO) (session.getAttribute("mem"))).getMemberId());
pageContext.setAttribute("list", list);

List<PropVO> filterList = list.stream().filter(propVO -> (propVO.getProposalStatus() == ProposalStatus.已上架達標)
		|| (propVO.getProposalStatus() == ProposalStatus.生產階段) || (propVO.getProposalStatus() == ProposalStatus.產品寄送)
		|| (propVO.getProposalStatus() == ProposalStatus.違信退款) || (propVO.getProposalStatus() == ProposalStatus.結案))
		.collect(Collectors.toList());
pageContext.setAttribute("filterList", filterList);
//請將第一個forEach list -> filterList
// 	<c:forEach var="propVO" items="${filterList}">
%>



<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>funding-proposal-management.jsp</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="manifest" href="site.webmanifest">
<link rel="shortcut icon" type="image/x-icon"
	href="../assets/img/favicon.png">
<!-- Place favicon.png in the root directory -->

<!-- CSS here -->
<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
<link rel="stylesheet" href="../assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="../assets/css/animate.min.css">
<link rel="stylesheet" href="../assets/css/magnific-popup.css">
<link rel="stylesheet" href="../assets/css/fontawesome-all.min.css">
<link rel="stylesheet" href="../assets/css/meanmenu.css">
<link rel="stylesheet" href="../assets/css/flaticon.css">
<link rel="stylesheet" href="../assets/css/slick.css">
<!-- <link rel="stylesheet" href="assets/css/style.css"> -->
<link rel="stylesheet" href="../assets/css/responsive.css">

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous"> -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/fb61cff1c9.js"
	crossorigin="anonymous"></script>
<!--請注意: 要引入 fontawesome -->
<link rel="stylesheet" href="../assets/scss/main.css">
<!--請注意: 可寫css 在main.css內 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.black-bold {
	color: black;
	font-weight: bold;
	margin-top: 3px;
}

.list-group-item:hover {
	background-color: #f5f5f5;
}

#carousel-example-generic a.carousel-control {
	height: 26%;
	top: 33%;
	width: 86px;
	background: black;
}
</style>

</head>
<body>
	<!-- preloader -->
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>
	<!-- preloader end  -->

	<%@ include file="/front_end/header/header.jsp"%>
	<main>
		<div class="maincontainer">
			<!-- Button trigger modal -->
			<div class="side col-3">
                <aside class="sidebar">
                    <header class="sidebar-header">
                        <h3>&nbsp; &nbsp; &nbsp; 提案管理<img id="logo-icon" src="../images/proposal-logo.png">
                            </h3>
                            <!-- <i class='bx bxs-bulb bx-flip-horizontal' style='color:#e8ec7f'></i> -->
                            <!-- <i class='bx bx-bulb'></i> -->
                    </header>
                    <nav>
                        <button onclick="location.href='<%=request.getContextPath()%>/front_end/prop/funding_proposal_management.jsp'">
                        
                            <span>
                     
                                &nbsp;<img src="../images/proposal-funding.png">
                                <span>&nbsp; &nbsp;未達標提案 </span>
                            	
                            </span>
                          
                        </button>
                        <button onclick="location.href='<%=request.getContextPath()%>/front_end/prop/addProposalToShop2.jsp'">
                            <span>
                                &nbsp;<img id="u2standard-img" src="../images/search.png" class="logo-img">
                                <span>&nbsp; &nbsp;已達標提案 </span>
                            </span>
                        </button>
                        
                        
                        <button onclick="location.href='<%=request.getContextPath()%>/front_end/prop/listAllPendingProposal.jsp'">
                            <span>
                                &nbsp;<img src="../images/proposal-record.png">
                                <span>&nbsp; &nbsp;提案紀錄</span>
                            </span>
                        </button>
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                    </nav>
                </aside>
            </div>
			<div class="content col-8">
				<section>
					<div style="text-align: center;">
						<h3>已達標提案</h3>
						<%@ include file="page/pagewf1.file"%>
					</div>
				</section>
				<hr style="border: 1px solid">

				<div>
					<div class="accordion-item">
						<c:forEach var="propVO" items="${filterList}">
							<div class="row">
								<div class="col-sm-6"
									style="padding-right: 0px; display: fixed;">
									<h2 class="accordion-header text-center"
										id="panelsStayOpen-headingOne" style="display: inline;">
										<button class="accordion-button text-truncate" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#panelsStayOpen-collapseOne-${propVO.proposalId}"
											aria-expanded="true"
											aria-controls="panelsStayOpen-collapseOne"
											style="text-align: right;">
											[${propVO.getPropTypeVO().proposalTypeName}] &nbsp;-
											&nbsp;${propVO.proposalName}
											<%--  <b style="font-size: 10px; ">${propVO.TampStamp2Date(propVO.proposalStartDatetime)} &nbsp;&nbsp;->  --%>
											<%--                                         &nbsp;&nbsp;${propVO.TampStamp2Date(propVO.proposalEndDatetime)}</b> --%>
											<!--         <div class="input-group mb-3" > -->

											<!-- 		</div> -->
										</button>

									</h2>
								</div>

								<div class="col-sm-6" style="padding-left: 0px">
									<div class="input-group "
										style="width: calc(0%); display: inline; padding: 0;">
										<div class="input-group-prepend"
											style="display: flex; height: 100%">

											<label class="input-group-text text-aligen"
												for="inputGroupSelect01"
												style="width: 350px; text-align: left; padding-left: 2px;">提案狀態&nbsp;
												<select class="mySelect custom-select"
												id="propStatus-${propVO.proposalId}" name="propStatus"
												onchange="mySelect(event, ${propVO.proposalId})"
												style="width: 100%; border-radius: 0px; padding-left: 3px; padding-right: 3px; text-align: justify; display: inline">
													<option selected disabled style="text-align: justify;">${propVO.proposalStatus}</option>
													<option style="text-align: justify;" value="4">生產階段</option>
													<option style="text-align: justify;" value="5">產品寄送</option>
													<option style="text-align: justify;" value="6">違信退款</option>
													<option style="text-align: justify;" value="7">提案結案</option>
											</select>
											</label>
											<FORM id="updateForm" METHOD="post"
												ACTION="<%=request.getContextPath()%>/front_end/prop/prop.do"
												style="margin: 0px; padding: 0;">
												<input type="hidden" name="proposalId"
													value="${propVO.proposalId}" style="float: right;">
												<%--                                 <input type="hidden"  name="proposalOptionId" value="${propOptVO.proposalOptionId}" --%>
												<!--                                     style="float: right;"> -->
												<input type="hidden" id="selected-${propVO.proposalId}"
													name="updateSelected" style="float: right;">
												<%--                                 <input type="hidden" id="checkboxSelected-${propOptVO.proposalOptionId}" name="checkboxSelected-${propOptVO.proposalOptionId}"  --%>
												<!--                                     style="float: right;"> -->
												<button id="updateButton-${propVO.proposalId}" type="submit"
													class="btn btn-outline-secondary" name="action"
													value="updatePropStatus"
													style="float: right; height: 100%;">修改</button>
												<!-- background-color: #e7f1ff; border-left: 1px solid; border-top: 1px solid; border-bottom: 1px solid; -->

											</FORM>
											<script>
                               			var selectedStatus = null;
                               			function mySelect(e, proposalId){
		                               		document.getElementById("selected-"+proposalId).value = e.target.value;
// 		                               		document.getElementById("selected2-"+proposalId).value = e.target.value;
		                               		selectedStatus = e.target.value;
                               			}
                               			
//                                			var select = document.querySelector('.mySelect');
//                                			var input2 = document.querySelector('.selected2');
//                                			var input3 = document.querySelector('.selected3');

//                                			select.addEventListener('change', function() {
//                                			  input2.value = this.value;
//                                			  input3.value = this.value;
//                                			});
                               			
                               			
                               		var select = $('.mySelect');
                               		var input2 = $('.selected2');
                               		var input3 = $('.selected3');
                               		                                  
                               		$('.mySelect').on('change', function(){                                   
                               			input2.val($(this).val());
                               		    input3.val($(this).val());                           
                               		    console.log("有跑");
                               		  })
                               			
								</script>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/front_end/prop/propRrodReview.do"
												style="margin: 0px; padding: 0;">
												<input type="hidden" name="proposalId"
													value="${propVO.proposalId}" style="float: right;">
												<button id="toShopBtn-${propVO.proposalId}" disabled
													type="submit" name="toShop" value="申請上架"
													class="btn btn-danger" style="float: right; height: 100%;">申請上架</button>
												<!--   background-color: #e7f1ff; border: 1px solid;  -->
												<script> 
// 								 let proposalStatus = ${propVO.proposalStatus};
// 								 let finish = ProposalStatus.結案;
								 	if (${propVO.proposalStatus == ProposalStatus.結案}) { 
								 		document.getElementById("toShopBtn-"+${propVO.proposalId}).disabled = false; 								
								 		}  								
				 			</script>

											</FORM>


										</div>
									</div>
								</div>
								<!--                     <input type="hidden" name="action" value="insert"> -->

								<div id="panelsStayOpen-collapseOne-${propVO.proposalId}"
									class="accordion-collapse collapse show"
									aria-labelledby="panelsStayOpen-headingOne">
									<div id="carouselExampleControls-${propVO.proposalId}"
										class="carousel" data-bs-interval="false">
										<div class="carousel-inner">
											<div class="carousel-item active">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/front_end/prop/prop.do"
													style="margin: 0px; padding: 0;">
													<div class="accordion-body">
														<c:forEach var="propOptVO"
															items="${propVO.getAllPropOptVO()}" end="0">
															<div>${propOptVO.proposalOptionName}</div>
															<ul class="list-group list-hover">
																<c:set var="total" value="0" />
																<c:set var="sum" value="0" />
																<c:set var="amount" value="0" />
																<c:forEach var="sponRecordVO1"
																	items="${propOptVO.getSponRecordVO()}">
																	<c:if test="${sponRecordVO1.memberId != previousValue}">
																		<c:set var="total" value="${total + 1}" />
																		<c:set var="previousValue"
																			value="${sponRecordVO1.memberId}" />
																	</c:if>
																	<c:set var="sum"
																		value="${sum + sponRecordVO1.sponsorTotalAmount}" />
																	<c:set var="amount"
																		value="${amount + sponRecordVO1.proposalOptionQuantity}" />
																</c:forEach>
																<li class="list-group-item"><b>贊助人數: <span
																		style="margin-left: 10px">${total}</span></b></li>
																<li class="list-group-item"><b>贊助總數: <span
																		style="margin-left: 10px"> ${amount}</span></b></li>
																<li class="list-group-item"><b>贊助總額: <span
																		style="margin-left: 10px">${sum}</span></b></li>
																<li class="list-group-item"><b>製作時間: <span
																		style="margin-left: 10px">${propVO.productProduceTime}</span></b></li>
																<li class="list-group-item"><b>配送時間: <span
																		style="margin-left: 10px">${propVO.targetDeliveryTime}</span></b></li>
																<li>
																	<table id="table-${propOptVO.proposalOptionId}"
																		class="table table-striped table-hover">
																		<thead style="white-space: nowrap;">
																			<tr>
																				<th class="text-center" scope="col">選取</th>
																				<th class="text-center" scope="col">贊助單號</th>
																				<th class="text-center" scope="col">贊助帳號</th>
																				<th class="text-center" scope="col">贊助數量</th>
																				<!-- 								      <th scope="col">分類</th> -->
																				<!-- 												  <th class="text-center" scope="col">贊助金額</th> -->
																				<th class="text-center" scope="col">出貨狀態</th>
																				<th class="text-center" scope="col">贊助時間</th>
																				<th class="text-center" scope="col">配送方式</th>
																				<th class="text-center" scope="col">收貨地址</th>
																				<!-- 											      <th class="text-center" scope="col">提交確認</th> -->
																			</tr>
																		</thead>

																		<c:forEach var="sponRecordVO2"
																			items="${propOptVO.getSponRecordVO()}">
																			<input type="hidden" name="proposalOptionId"
																				value="${propOptVO.proposalOptionId}">
																			<input type="hidden" class="selected2"
																				name="updateSelected" style="float: right;">

																			<!--                                    			<script> -->
																			<%-- //                                    		 		document.getElementById("propStatus-${propVO.proposalId}").addEventListener("change", function(e) { --%>
																			<%-- //                                              	  mySelect(e, ${propVO.proposalId}, ${propOptVO.proposalOptionId}); --%>
																			<!-- //                                              	}); -->
																			<!--                                    			</script> -->

																			<script>
//                                    		 		document.getElementById("selected2-"+${propOptVO.proposalOptionId}).value(selectedStatus);
//                                    					document.getElementById("selected2").value = selectedValue;
                                   			</script>
																			<tbody>
																				<tr>
																					<td class="text-center"><input type="checkbox"
																						checked name="cbox-${propOptVO.proposalOptionId}"
																						id="cbox-${sponRecordVO2.sponsorRecordId}"
																						value="${sponRecordVO2.sponsorRecordId}"></td>
																					<!--                                                     <input type="hidden" name=action> -->
																					<th class="text-center" scope="row">${sponRecordVO2.sponsorRecordId}</th>
																					<th class="text-center">${sponRecordVO2.memberId}</th>
																					<td class="text-center">${sponRecordVO2.proposalOptionQuantity}</td>
																					<%--                                                     <td class="text-center">${sponRecordVO.sponsorTotalAmount}</td> --%>
																					<td class="text-center">${sponRecordVO2.sponsorRecordStatus}</td>
																					<td class="text-center">${propVO.TampStamp2Date(sponRecordVO2.sponsorDatetime)}</td>
																					<td class="text-center">${sponRecordVO2.sponsorRecordDelivery}</td>
																					<td class="text-center">${sponRecordVO2.sponsorRecordAddress}</td>
																					<!--                                                     <td class="text-center"><input type="submit">提交</td> -->
																				</tr>
																			</tbody>

																			<!--                                             <script> -->
																			<%-- //                                             	var checkbox = document.getElementById("cbox-${sponRecordVO.sponsorRecordId}"); --%>
																			<!-- //                                             	var form = document.getElementById("updateForm"); -->
																			<%-- //                                             	var checkboxValue = document.getElementById("checkboxSelected-${propOptVO.proposalOptionId}"); --%>

																			<!-- //                                             	form.addEventListener("submit", function(event) { -->
																			<!-- //                                               	if(checkbox.checked){ -->
																			<%-- //                                               		checkboxValue.value = ${sponRecordVO.sponsorRecordId}; --%>
																			<!-- //                                               	} -->
																			<!-- //                                            		 }); -->
																			<!--                                             </script> -->

																		</c:forEach>

																	</table>
																</li>
															</ul>
														</c:forEach>
														<div class="text-center">
															<button type="submit" class="btn btn-outline-secondary"
																name="action" value="updateSnonRecordStatus"
																style="float: center; height: 100%;">提交</button>
														</div>
													</div>
												</FORM>
											</div>

											<c:forEach var="propOptVO"
												items="${propVO.getAllPropOptVO()}" begin="1">
												<div class="carousel-item" style="margin: 18px; width: 95%">
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/front_end/prop/prop.do"
														style="margin: 0px; padding: 0;">
														<div>${propOptVO.proposalOptionName}</div>
														<ul class="list-group list-hover">
															<c:set var="total" value="0" />
															<c:set var="sum" value="0" />
															<c:set var="amount" value="0" />
															<c:forEach var="sponRecordVO3"
																items="${propOptVO.getSponRecordVO()}">
																<c:if test="${sponRecordVO3.memberId != previousValue}">
																	<c:set var="total" value="${total + 1}" />
																	<c:set var="previousValue"
																		value="${sponRecordVO3.memberId}" />
																</c:if>
																<c:set var="sum"
																	value="${sum + sponRecordVO3.sponsorTotalAmount}" />
																<c:set var="amount"
																	value="${amount + sponRecordVO3.proposalOptionQuantity}" />
															</c:forEach>
															<li class="list-group-item"><b>贊助人數: <span
																	style="margin-left: 10px">${total}</span></b></li>
															<li class="list-group-item"><b>贊助總數: <span
																	style="margin-left: 10px"> ${amount}</span></b></li>
															<li class="list-group-item"><b>贊助總額: <span
																	style="margin-left: 10px">${sum}</span></b></li>
															<li class="list-group-item"><b>製作時間: <span
																	style="margin-left: 10px">${propVO.productProduceTime}</span></b></li>
															<li class="list-group-item"><b>配送時間: <span
																	style="margin-left: 10px">${propVO.targetDeliveryTime}</span></b></li>
															<li>
																<table id="table-${propOptVO.proposalOptionId}"
																	class="table table-striped table-hover">
																	<thead style="white-space: nowrap;">
																		<tr>
																			<th class="text-center" scope="col">選取</th>
																			<th class="text-center" scope="col">贊助單號</th>
																			<th class="text-center" scope="col">贊助帳號</th>
																			<th class="text-center" scope="col">贊助數量</th>
																			<!-- 								      <th scope="col">分類</th> -->
																			<!-- 												  <th class="text-center" scope="col">贊助金額</th> -->
																			<th class="text-center" scope="col">出貨狀態</th>
																			<th class="text-center" scope="col">贊助時間</th>
																			<th class="text-center" scope="col">配送方式</th>
																			<th class="text-center" scope="col">收貨地址</th>
																			<!-- 											      <th class="text-center" scope="col">提交確認</th> -->
																		</tr>
																	</thead>
																	<c:forEach var="sponRecordVO4"
																		items="${propOptVO.getSponRecordVO()}">
																		<input type="hidden" name="proposalOptionId"
																			value="${propOptVO.proposalOptionId}">
																		<input type="hidden" class="selected3"
																			name="updateSelected" style="float: right;">
																		<!--                                    			<script> -->
																		<%-- //                                    		 		document.getElementById("propStatus-${propVO.proposalId}").addEventListener("change", function(e) { --%>
																		<%-- //                                              	  mySelect(e, ${propVO.proposalId}, ${propOptVO.proposalOptionId}); --%>
																		<!-- //                                              	}); -->
																		<!--                                    			</script> -->
																		<script>
//                                    					document.getElementById("selected3").value = selectedValue;
                                   		 		</script>
																		<tbody>
																			<tr>
																				<td class="text-center"><input type="checkbox"
																					checked name="cbox-${propOptVO.proposalOptionId}"
																					id="cbox-${sponRecordVO4.sponsorRecordId}"
																					value="${sponRecordVO4.sponsorRecordId}"></td>
																				<th class="text-center" scope="row">${sponRecordVO4.sponsorRecordId}</th>
																				<td class="text-center">${sponRecordVO4.memberId}</td>
																				<td class="text-center">${sponRecordVO4.proposalOptionQuantity}</td>
																				<%--                                                     <td class="text-center">${sponRecordVO4.sponsorTotalAmount}</td> --%>
																				<td class="text-center">${sponRecordVO4.sponsorRecordStatus}</td>
																				<td class="text-center">${propVO.TampStamp2Date(sponRecordVO4.sponsorDatetime)}</td>
																				<td class="text-center">${sponRecordVO4.sponsorRecordDelivery}</td>
																				<td class="text-center">${sponRecordVO4.sponsorRecordAddress}</td>
																				<!--                                                     <td class="text-center"><input type="submit">提交</td> -->
																			</tr>
																		</tbody>
																		<script> 
// 												$(document).ready(function() { -->
// 													$('#ssubmit').on('click', function(e){
// 													e.preventDefault(); 
// 													var updateSelected = $("input[name='updateSelected']").val(selectedStatus);
// 					        						log.console(updateSelected);
										</script>
																	</c:forEach>
																</table>
															</li>
														</ul>
														<div class="text-center">
															<button type="submit" id="ssubmit"
																class="btn btn-outline-secondary" name="action"
																value="updateSnonRecordStatus"
																style="float: center; height: 100%;">提交</button>
														</div>

													</FORM>
												</div>
											</c:forEach>
										</div>
										<button class="carousel-control-prev carousel-dark"
											style="color: black; margin-left: 0px; height: 250px;"
											type="button"
											data-bs-target="#carouselExampleControls-${propVO.proposalId}"
											data-bs-slide="prev">
											<span class="carousel-control-prev-icon" aria-hidden="true"
												style="color: black; position: absolute; left: -40px;"></span>
											<span class="visually-hidden">Previous</span>
										</button>
										<button class="carousel-control-next carousel-dark"
											style="color: black; margin-right: 0px; height: 250px;"
											type="button"
											data-bs-target="#carouselExampleControls-${propVO.proposalId}"
											data-bs-slide="next">
											<span class="carousel-control-next-icon" aria-hidden="true"
												style="color: black; position: absolute; right: -40px;"></span>
											<span class="visually-hidden">Next</span>
										</button>
									</div>

								</div>

							</div>
							<br>

						</c:forEach>
						<c:if test="${empty filterList}">
							<p
								style="margin-left: 15px; font-size: 16px; color: #444; font-weight: 600; letter-spacing: 1.5px;">目前沒有已達標的專案~~</p>
						</c:if>
					</div>
				</div>

			</div>
		</div>
		<!--請注意: 依需求，可多新增一個跟上面一樣的 section，新增在main標籤內 -->
	</main>


	<footer data-background="../assets/img/bg/footer.jpg">
		<div class="footer-area pt-5">
			<div class="container">
				<div class="row d-flex justify-content-center ">
					<div class="col-xl-2 col-lg-3 col-md-4">
						<div class="footer-widget mb-40">
							<div class="footer-logo mb-25">
								<img src="../assets/img/logo/Mumu_logo.png" alt="">
							</div>
							<div class="social-icon d-flex justify-content-center">
								<a href="#"><i class="fab fa-facebook-f"></i></a> <a href="#"><i
									class="fab fa-twitter"></i></a> <a href="#"><i
									class="fab fa-behance"></i></a> <a href="#"><i
									class="fab fa-linkedin-in"></i></a> <a href="#"><i
									class="fab fa-youtube"></i></a>
							</div>
						</div>
					</div>
					<div class="col-xl-2 col-lg-2 col-md-4">
						<div class="footer-widget mb-40">
							<h4 class="footer-title">募募 Mumu</h4>
							<ul class="footer-link">
								<li><a href="#">關於我們</a></li>

							</ul>
						</div>
					</div>
					<div class="col-xl-2 col-lg-3 col-md-4">
						<div class="footer-widget mb-40">
							<h4 class="footer-title">如何使用</h4>
							<ul class="footer-link">
								<li><a href="#">網站使用條款</a></li>


							</ul>
						</div>
					</div>

					<div class="col-xl-2 col-lg-3 col-md-4">
						<div class="footer-widget mb-40">
							<h4 class="footer-title">常見問題</h4>
							<ul class="footer-link">
								<li><a href="#">問答集</a></li>
								<li><a href="#">- 如何發起一個募資</a></li>
								<li><a href="#">- 如何贊助他人的專案</a></li>
								<li><a href="#">- 如何發起許願</a></li>
							</ul>
						</div>
					</div>


				</div>
			</div>
		</div>
		<div class="copyright-area">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="copyright-text text-center">
							<p>Copyright All Right Reserved - 2022</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>



	<!-- JS here -->
	<script src="../assets/js/vendor/jquery-1.12.4.min.js"></script>
	<script src="../assets/js/popper.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/owl.carousel.min.js"></script>
	<script src="../assets/js/isotope.pkgd.min.js"></script>
	<script src="../assets/js/instafeed.min.js"></script>
	<script src="../assets/js/slick.min.js"></script>
	<script src="../assets/js/jquery.meanmenu.min.js"></script>
	<script src="../assets/js/ajax-form.js"></script>
	<script src="../assets/js/wow.min.js"></script>
	<script src="../assets/js/jquery.scrollUp.min.js"></script>
	<script src="../assets/js/imagesloaded.pkgd.min.js"></script>
	<script src="../assets/js/jquery.magnific-popup.min.js"></script>
	<script src="../assets/js/plugins.js"></script>
	<script src="../assets/js/main.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"> </script>


	<!-- 請注意: 要引入下方 js，確保 dropdown功能 -->
	<script src="../assets/js/dropdown.js"></script>
	<!-- 	<script> -->
	<!-- // 	$(document).ready(function() { -->
	<!-- // 		$('.updateSponsorRecordStatus').on('click', function(e){ -->
	<!-- // 			e.preventDefault(); -->
	<!-- // <!-- 			$(this).toggleClass('liked'); -->
	-->
	<!-- // 			var proposalOptionId = $("input[name='proposalOptionId']").val(); -->
	<!-- // 			var updateSelected = $("input[name='updateSelected']").val(selectedStatus); -->
	<!-- // 			var proposalId = $("input[name='proposalId']").val(); -->
	<!-- // 			var action = $(this).val(); -->
	<%-- <%-- 				<%if(request.getSession().getAttribute("mem")!=null) { %> --%>
	<!-- // 					$.ajax({ -->
	<!-- // 			        	  type: 'POST', -->
	<%-- <%-- 			        	  url: '<%=request.getContextPath()%>/front_end/prop/prop.do', --%>
	--%>
	<!-- // 			        	  data: { -->
	<!-- // 			                  "proposalOptionId": proposalOptionId, -->
	<!-- // 			                  "updateSelected": updateSelected, -->
	<!-- // 			                  "proposalId": proposalId, -->
	<!-- // 			                  "action": action -->
	<!-- // 			        	  } -->
	<!-- // 			        	}).then(function(res){ -->

	<!-- // 			        		console.log("成功"); -->

	<!-- // 			        	}, function(err) { -->
	<!-- // 			        		if(err.status == 404) { -->
	<!-- // 			        			console.log("這是404錯誤"); -->
	<!-- // 			        		} else { -->
	<!-- // 			        			console.log(err); -->
	<!-- // 			        		} -->

	<!-- // 			        	}); -->
	<%-- 				<% }else { %>	 --%>
	<%-- 					window.location.href = '<%=request.getContextPath()%>/front_end/login.jsp'; --%>
	<%-- 				<% } %> --%>

	<!-- // 		}); -->
	<!-- // 	});		 -->
	<!-- 	</script> -->

</body>
</html>