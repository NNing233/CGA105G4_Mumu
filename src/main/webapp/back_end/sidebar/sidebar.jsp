<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading">Mumu</div>

						<!-- <a class="nav-link" href="index.html">
                            <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                            Dashboard
                        </a> -->
						<!-- 備用 -->

						<c:if test="${fn:contains(sessionScope.admin.adminAcess, '2')}">
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseProposal" aria-expanded="false"
								aria-controls="collapseProposal">
								<div class="sb-nav-link-icon">
									<i class="fas fa-columns"></i>
								</div> 募資管理
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseProposal"
								aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav">
									<a class="nav-link"
										href="<%=request.getContextPath()%>/proptype/proptype.do?action=propLayoutManage">募資頁面管理</a>
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/prop/proposerManagement.jsp">提案者管理</a>
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/prop/proposal_mangement.jsp">募資中專案管理</a>
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/prop/successProp.jsp">已達標專案管理</a>
								</nav>
							</div>
						</c:if>


						<c:if test="${fn:contains(sessionScope.admin.adminAcess, '3')}">
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseShop" aria-expanded="false"
								aria-controls="collapseShop">
								<div class="sb-nav-link-icon">
									<i class="fa-solid fa-shop"></i>
								</div> 商城管理
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseShop"
								aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav accordion"
									id="sidenavAccordionPages">
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/product/listAllProduct.jsp">商品管理</a>
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/order/listAllMemberOrderHistoryTest3.jsp">訂單管理</a>
									<a class="nav-link collapsed" href="#"
										data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth"
										aria-expanded="false" aria-controls="pagesCollapseAuth">
										優惠券管理
										<div class="sb-sidenav-collapse-arrow">
											<i class="fas fa-angle-down"></i>
										</div>
									</a>
									<div class="collapse" id="pagesCollapseAuth"
										aria-labelledby="headingOne"
										data-bs-parent="#sidenavAccordionPages">
										<nav class="sb-sidenav-menu-nested nav">
											<a class="nav-link"
												href="<%=request.getContextPath()%>/back_end/coupon/addCouponTest1.jsp">新增優惠券</a>
											<a class="nav-link"
												href="<%=request.getContextPath()%>/back_end/coupon/listAllMemberCouponTest1.jsp">優惠券列表</a>
										</nav>
									</div>


								</nav>
							</div>
						</c:if>


						<c:if test="${fn:contains(sessionScope.admin.adminAcess, '1')}">

							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseBlog" aria-expanded="false"
								aria-controls="collapseBlog">
								<div class="sb-nav-link-icon">
									<i class="fas fa-book-open"></i>
								</div> 交流區管理
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseBlog"
								aria-labelledby="headingThree"
								data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav">
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/listArtCarousel.jsp">輪播圖管理</a>
								</nav>
							</div>
						</c:if>

						<c:if test="${fn:contains(sessionScope.admin.adminAcess, '4')}">

							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseReview" aria-expanded="false"
								aria-controls="collapseReview">
								<div class="sb-nav-link-icon">
									<i class="fa-solid fa-check"></i>
								</div> 審核管理
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseReview"
								aria-labelledby="headingFour" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav">
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/prop/proposalReview.jsp">募資提案審核</a>
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/prop/listProposalPassed.jsp">提案審核結果</a>
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/prop/propRerportMangement.jsp">檢舉提案審核</a>
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/prop/listPropReportPassed.jsp">檢舉審核結果</a>

								</nav>
							</div>
						</c:if>


						<c:if test="${fn:contains(sessionScope.admin.adminAcess, '5')}">
							<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
								data-bs-target="#collapseMember" aria-expanded="false"
								aria-controls="collapseMember">
								<div class="sb-nav-link-icon">
									<i class="fa-solid fa-users"></i>
								</div> 前台會員管理
								<div class="sb-sidenav-collapse-arrow">
									<i class="fas fa-angle-down"></i>
								</div>
							</a>
							<div class="collapse" id="collapseMember"
								aria-labelledby="headingFive" data-bs-parent="#sidenavAccordion">
								<nav class="sb-sidenav-menu-nested nav">
									<a class="nav-link"
										href="<%=request.getContextPath()%>/back_end/mem/member_list.jsp">會員資料管理</a>


								</nav>
							</div>
						</c:if>

						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseAdmin" aria-expanded="false"
							aria-controls="collapseAdmin">
							<div class="sb-nav-link-icon">
								<i class="fa-solid fa-shield"></i>
							</div> 後台人員管理
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseAdmin"
							aria-labelledby="headingSix" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link"
									href="<%=request.getContextPath()%>/back_end/admin/admin_list.jsp">帳號管理</a>
								<a class="nav-link"
									href="<%=request.getContextPath()%>/back_end/adminaccessmanage/admin_access.jsp">權限管理</a>


							</nav>
						</div>

					</div>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Logged in as:</div>
					Start Bootstrap
				</div>
			</nav>
		</div>