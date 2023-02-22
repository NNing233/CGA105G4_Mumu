<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.stream.Collectors"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import = "com.prop.model.*" import="com.mem.model.*" import="com.propopt.model.*" import="com.sponrecord.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
HttpSession Asession = request.getSession();
String location = (request.getQueryString() == null)
? request.getRequestURL().toString()
: request.getRequestURL().append("?").append(request.getQueryString()).toString();

Asession.setAttribute("location", location);
%> 

<c:if test="${empty sessionScope.mem}">
 <jsp:forward page="../login.jsp"/>
</c:if>

<%
	request.setCharacterEncoding("UTF-8");
    PropService propSrc = new PropService();
    
// 	List<PropVO> list = propSrc.getTheProposal(((MemVO)(request.getSession().getAttribute("mem"))).getMemberId());
	List<PropVO> list = propSrc.getTheProposal(((MemVO)(session.getAttribute("mem"))).getMemberId());
    pageContext.setAttribute("list",list);	
    
	List<PropVO> filterList = list.stream().filter(propVO -> (propVO.getProposalStatus() == ProposalStatus.已上架未達標) || (propVO.getProposalStatus() == ProposalStatus.已上架達標)).collect(Collectors.toList());
	pageContext.setAttribute("filterList",filterList);
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
    <link rel="shortcut icon" type="image/x-icon" href="../assets/img/favicon.png">
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/fb61cff1c9.js" crossorigin="anonymous"></script>
    <!--請注意: 要引入 fontawesome -->
    <link rel="stylesheet" href="../assets/scss/main.css">
    <!--請注意: 可寫css 在main.css內 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
    	.black-bold{
    		color: black;
    		font-weight: bold;
    		margin-top:3px;
    	}
    	   	.list-group-item:hover {
    background-color: #f5f5f5;
	}
	.proposal-name a {
    	text-decoration: none;
  	}
	.sideBarButtons {
    	text-decoration: none;
  	}
    </style>

</head>
<body>
    <!-- preloader -->
    <div id="preloader">
        <div class="preloader">
            <span></span>
            <span></span>
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
            <div class="content col-8" style="text-align: center;">
                <section>
                     <h3 >未達標提案管理</h3> <!--style="display: inline-block" -->
                    <%@ include file="page/pagewf1.file" %>
                </section>
                <hr style="border: 1px solid">
                <br>
                <div>

                    <!--請注意: 以下填入你要寫的東西 開始-->
                    <!-- Button trigger modal -->
					<c:forEach var="propVO" items="${filterList}">
                    <table class="mx-auto table table-bordered">
                        <tr>
                            <td class="text-center align-middle">
                                <span>
                                    <br>
                                    <h4 class="proposal-name" style="margin-bottom: 0px; text-align: center;"><a href="https://www.example.com" target="_blank">${propVO.proposalName}<br><b style="font-size: 10px">${propVO.proposalStartDatetime}</b><b style="font-size: 10px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;------ 倒數 ${propVO.daysDifference(propVO.proposalStartDatetime,propVO.proposalEndDatetime)} 天 ------>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><b style="font-size: 10px">${propVO.proposalEndDatetime}</b></a></h4>
                                  
<%--                                     <b>${propVO.TampStamp2Date(propVO.proposalStartDatetime)}</b><b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;------ 倒數 ${propVO.daysDifference(propVO.proposalStartDatetime,propVO.proposalEndDatetime)} 天 ------> --%>
<%--                                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><b>${propVO.TampStamp2Date(propVO.proposalEndDatetime)}</b> --%>
	                                 	
                                </span>

                                <span class="progress" style="position: relative;">
                                    <div id="processbar-${propVO.proposalId}"
                                        class="progress-bar progress-bar-striped progress-bar-animated"
                                        role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"
                                        display: inline-block;">
                                        <span id="progresstext-${propVO.proposalId}" style="text-align: center; position: absolute;  left:48%">
                                        	
                                        </span>
                                        <span id="progresstext1-${propVO.proposalId}" style="text-align: center; position: absolute;  left:1%;  color:black">
										    <b style="font-size: 10px">累積金額: ${propVO.accumulativeDonateMoney}</b>
										</span>
										<span id="progresstext2-${propVO.proposalId}" style="text-align: center; position: absolute;  right:1%;  color:black">
										    <b style="font-size: 10px">目標金額: ${propVO.targetDonateMoney}</b>
										</span>
                                        <!-- transform: translate(450%, -5%) -->
                                    </div>
                                </span>

                            </td>

                            <td class="text-center align-middle">
                                <button type="button" class="btn btn-outline-primary btn-lg" data-bs-toggle="modal"
                                    data-bs-target="#viewSponsorList1-${propVO.proposalId}">
                                    檢視贊助名單
                                </button>


                                <div class="modal fade" id="viewSponsorList1-${propVO.proposalId}" tabindex="-1"
                                    aria-labelledby="viewSponsorList1" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="viewSponsorList1">贊助名單</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
<%--                                              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/proposal/prop.do" style="margin-bottom: 0px;">  --%>
                                           
                                            <div class="modal-body">
<%--                                             <c:set var="proposalId" value="${propVO.proposalId}" /> --%>
<%-- 												<%  --%>
<!-- // 													pageContext.setAttribute("proposalId", proposalId); -->
<!-- // 													PropOptService propOptSrc = new  PropOptService(); -->
<!-- // 													List<PropOptVO> list2 = propOptSrc.getAllOption(pageContext.getAttribute("proposalId")); -->
<!-- // 													pageContext.setAttribute("list",list);  -->
<%-- 												%> --%>
												 <c:forEach var="propOptVO" items="${propVO.getAllPropOptVO()}">
												 
                                                <nav class="nav-tabs nav-justified">
                                                    <div class="nav nav-tabs " id="nav-tab"
                                                        role="tablist">
<%--                                                         <input type="hidden" name="proposalId" value="${prop.proposalId}"> --%>
<!--                                                         <input type="hidden" name="query" value="getAllOption"> -->
                                                        
                                                        <button class="nav-link active" id="nav-home-tab"
                                                            data-bs-toggle="tab" data-bs-target="#nav-home-${propOptVO.proposalOptionId}"
                                                            type="button" role="tab" aria-controls="nav-home"
                                                            aria-selected="true"><b>${propOptVO.proposalOptionName}</b></button>
                                                    		
                                                    </div>
                                                </nav>
                                              	 
                                                <div class="tab-content" id="nav-tabContent">
                                                    <div class="tab-pane fade show active" id="nav-home-${propOptVO.proposalOptionId}" role="tabpanel"
                                                        aria-labelledby="nav-home-tab">
                                                        <br>
                                                        <table class="table table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th scope="col">贊助者</th>
                                                                    <th scope="col">數量</th>
                                                                    <th scope="col">贊助金額</th>
                                                                </tr>
                                                            </thead>
<%--                                                             <c:forEach var="sponRecordVO" items="${propVO.getSponRecordVO()}"> --%>
 															<c:set var="total" value="0"/>
 															<c:set var="sum" value="0"/>
                                                        	<c:set var="amount" value="0"/>
 															<c:forEach var="sponRecordVO" items="${propOptVO.getSponRecordVO()}">
                                                             <tbody> 
                                                                 <tr> 
                                                                    <td>${sponRecordVO.memberId}</td>
                                                                    <td>${sponRecordVO.proposalOptionQuantity}</td>
                                                                    <td>${sponRecordVO.sponsorTotalAmount}</td>
                                                                </tr>
                                                            </tbody>
                                                              <c:if test="${sponRecordVO.memberId != previousValue}">
															    <c:set var="total" value="${total + 1}"/>
															    <c:set var="previousValue" value="${sponRecordVO.memberId}"/>
															  </c:if>
                                                            	<c:set var="sum" value="${sum + sponRecordVO.sponsorTotalAmount}"/>
                                                            	<c:set var="amount" value="${amount + sponRecordVO.proposalOptionQuantity}"/>
                                                            </c:forEach>
                                                            <tfoot class="border border-2">
																<tr>
																	<td>贊助人數: ${total}</td>
																	<td>贊助總數: ${amount}</td>
																	<td>贊助總額: ${sum}</td>
																</tr>
                                                            </tfoot>
                                                        </table>
                                                    </div>
                                                </div>
                                               </c:forEach>
                                            </div>
                                             
<!--                                             </FORM> -->
                                            <div class="modal-footer d-flex justify-content-center">
                                                <button type="button" class="btn btn-secondary"
                                                    data-bs-dismiss="modal">Close</button>
                                            </div>
                                        </div>


                                    </div>
                                </div>
                            </td>
                            <td class="text-center align-middle">
                                <button type="button" class="btn btn-outline-primary btn-lg" data-bs-toggle="modal"
                                    data-bs-target="#abandonment-${propVO.proposalId}">
                                    放棄/延長提案
                                </button>
								

                                <div class="modal fade" id="abandonment-${propVO.proposalId}" tabindex="-1" aria-labelledby="abandonment"
                                    aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">

                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="abandonment">放棄/延長 提案確認!</h5>
                                                <button type="button" class="btn-close btn-sm" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/prop/prop.do" style="margin-bottom: 0px;"> 
                                            <div class="modal-body" style="width:auto">
                                                <div class="text-left" style="margin-left:20%;  margin-top:5%">
                                                	<c:if test="${not empty errorMsgs}">
														<font style="color:red">請修正以下錯誤:</font>
														<ul>
															<c:forEach var="message" items="${errorMsgs}">
																<li style="color:red">${message}</li>
															</c:forEach>
														</ul>
													</c:if>
													
                                                    <br>
                                                    <label class="black-bold">帳號: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${propVO.getMemVO().memberAccount} </lable>
                                                    <br>
                                                    <br>
                                                    <label class="black-bold" for="inputPassword">確認密碼: &nbsp;&nbsp;</label>
                                                    <input type="password" id="inputPassword" name="confirmPassword" display="block" aria-labelledby placeholder="請輸入密碼確認" style="float: right;">
                                                    <br>
                                                    <br>
                                                    <label class="black-bold" for="delayed-days">延長天數: &nbsp;&nbsp;</label>
                                                    <input type="text" id="delayed-days" name="extendedDays" display="block" aria-labelledby placeholder="不欲延長，不需填入天數" style="float: right; ">
                                                    <br>
                                                    <br>
                                                    <br>

                                                </div>
                                                <div class="text-center">
                                                    <input class="abandoncheck" type="radio" display="block" name="isAbandon">
                                                    <b class="abandoncheck">已同意，放棄提案聲明 </b>
                                                </div>
                                            </div>
                                            <div class="modal-footer d-flex justify-content-center">
                                            	<input type="hidden" name="proposalId" value="${propVO.proposalId}">
                                            	<input type="hidden" name="memberId" value="${propVO.memberId}">
                                                <div class="text-center">
                                                    <button type="submit" class="btn btn-outline-danger"
                                                        style="height: 10%;" name="action" value="confirmSubmit">確認並提交</button>
                                                </div>

                                            </div>
                                            </FORM>
                                        </div>
                                    </div>
                                </div>
                        </tr>
                    </table>
                
                    <hr>
                    <script>
    
//     var precent = (${propVO.accumulativeDonateMoney})/(${propVO.targetDonateMoney}) * 100;
//     $(function() {
//         $("#progress-bar").css("width", precent + "%");
//     });
	    var accumulativeDonateMoney = ${propVO.accumulativeDonateMoney};
	    var targetDonateMoney = ${propVO.targetDonateMoney};
	    var percent = (accumulativeDonateMoney / targetDonateMoney * 100).toFixed(1);
	    
	    document.getElementById("processbar-"+${propVO.proposalId}).setAttribute("style", "width: " + percent + "%");
// 	    if(percent<=10){
// 	    	document.getElementById("processbar-"+${propVO.proposalId}).setAttribute("style", "width: " + percent + "%"; background-color: #888888; color: #000000");
// 	    }else if(10<percent<=25){
// 	    	document.getElementById("processbar-"+${propVO.proposalId}).setAttribute("style", "width: " + percent + "%"; background-color: #28a745; color: #000000");
// 	    }else if(25<percent<=50){
// 	 		document.getElementById("processbar-"+${propVO.proposalId}).setAttribute("style", "width: " + percent + "%"; background-color: #ffc107; color: #ffffff");
// 	    }else if(50<percent<=75){
// 	    	document.getElementById("processbar-"+${propVO.proposalId}).setAttribute("style", "width: " + percent + "%; background-color: #28a745; color: #ffffff");
// 	    }else if(75<percent<100){
// 	    	document.getElementById("processbar-"+${propVO.proposalId}).setAttribute("style", "width: " + percent + "%; background-color: #bbbbbb; color: #ffffff");
// 	    }else{
// 	    	document.getElementById("processbar-"+${propVO.proposalId}).setAttribute("style", "width: " + percent + "%; background-color: #28a745; color: #ffffff");
// 	    }
	    document.getElementById("progresstext-"+${propVO.proposalId}).innerHTML = percent+"%";
//     $(function() {
//         $("#process-bar").css("width", percent + "%");
//         $("#process-bar").attr("aria-valuenow", percent);
//         $("#process-bar").text(percent + "%");
//     });
       
    </script>
					</c:forEach>
                 <c:if test="${empty filterList}">
					<p style="margin-left: 15px; font-size: 16px; color: #444; font-weight: 600; letter-spacing: 1.5px;"> 目前沒有募資中的提案~~</p>
				</c:if>     
                    
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
                                <a href="#"><i class="fab fa-facebook-f"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                                <a href="#"><i class="fab fa-behance"></i></a>
                                <a href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a href="#"><i class="fab fa-youtube"></i></a>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"> </script>


    <!-- 請注意: 要引入下方 js，確保 dropdown功能 -->
    <script src="../assets/js/dropdown.js"></script>
    
</body>
</html>