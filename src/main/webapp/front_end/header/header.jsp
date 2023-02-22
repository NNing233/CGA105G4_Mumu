<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header id="sticky-header " class="header">
		<div class="container">
			<div class="header-box white-bg pl-50 pr-50">
				<div class="row">
					<div
						class="col-xl-2 col-lg-2 col-md-3 col-5 d-flex align-items-center">
						<div class="header__logo">
							<a href="<%=request.getContextPath()%>/front_end/prop/index.jsp"><img
								src="<%=request.getContextPath()%>/front_end/assets/img/logo/Mumu_logo.png" alt=""></a>
						</div>
					</div>
					<div class="col-xl-10 col-lg-10 col-7 col-md-9">
						<div class="header__right f-right" style="overflow: visible;">
							<div class="header__icon f-right mt-30 position-relative">
								
								<a class="login-btn" style="width: 50px !important; height: 50px !important;" onclick="dropdownMenu(event)"><img style="width: 100%;height: 100%"  src="<%=request.getContextPath()%>/front_end/mem/photo?action=show_image&memberId=${sessionScope.mem.memberId}"></a>
<!--  下拉式選單icon有換成圖片  -->
<%--                                 	<img onclick="dropdownMenu(event)" class="rounded-circle" style="width: 50px;height: 50px" src="<%=request.getContextPath()%>/front_end/mem/photo?action=show_image&memberId=${sessionScope.mem.memberId}"> --%>
<!--  下拉式選單icon有換成圖片 -->

								<!--請注意: 會員中心的下拉選單 -->

								<div class="dropdown position-absolute">
									<button class="btn btn-secondary dropdown-toggle" type="button"
										id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"></button>
									<div class="dropdown-menu dropdown-menu-right"
										aria-labelledby="dropdownMenuButton" style="z-index: 100;">
										<c:if test="${empty sessionScope.mem}">
											<a class="dropdown-item">你好！ 訪客！</a>
											<a class="dropdown-item"
												href="<%=request.getContextPath()%>/front_end/login.jsp">登入</a>
											<a class="dropdown-item"
												href="<%=request.getContextPath()%>/front_end/register.jsp">註冊</a>
										</c:if>
										<c:if test="${not empty sessionScope.mem}">
											<a class="dropdown-item">Hi！
												${sessionScope.mem.memberNickname}！</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/mem/personalpage.jsp">個人頁面</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/sponrecord/sponrecord.do?action=getSponsorRecordByMember">贊助紀錄</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/prop/funding_proposal_management.jsp">提案管理</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/propcollection/propcollection.do?action=MyPropCollection">收藏紀錄</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/art/artManage.jsp">文章管理</a>
											<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/shop/shop-cart.jsp">商城購物車</a>
											<a class="dropdown-item"
												href="<%=request.getContextPath()%>/front_end/log.do?action=logout">登出</a>
										</c:if>
									</div>
								</div>

								<!--請注意: 會員中心的下拉選單 結束 -->


							</div>
							<div class="header__icon f-right mt-30 ml-30 d-none d-md-block">
								<a class="btn btn-white " href="<%=request.getContextPath() %>/front_end/prop/addProposal.jsp"><i
									class="fa-solid fa-sack-dollar"></i>發起募資</a>
							</div>



						</div>
						<div class="header__menu f-right">
							<nav id="mobile-menu">
								<ul>
									<li><a href="<%=request.getContextPath()%>/front_end/prop/index.jsp">募資</a>
										<ul class="submenu">
											<li><a href="<%=request.getContextPath()%>/front_end/prop/addPropReport.jsp">提案檢舉</a></li>
										</ul></li>

									<li><a href="<%=request.getContextPath()%>/front_end/shop/shop-main.jsp">商城</a>
										<ul class="submenu">
											<li><a
												href="<%=request.getContextPath()%>/front_end/order/listOneMemberOrderHistoryTest2.jsp">我的訂單</a></li>
											<li><a
												href="<%=request.getContextPath()%>/front_end/shop/listOneMemberFavorite.jsp">我的最愛</a></li>
											<li><a
												href="<%=request.getContextPath()%>/front_end/coupon/listOneMemberCouponTest1.jsp">我的優惠券</a></li>
										</ul></li>

									<li><a href="<%=request.getContextPath()%>/front_end/art/art.jsp">交流區</a></li>

									<li><a
										href="<%=request.getContextPath()%>/front_end/art/addArt.jsp">我要許願</a>
									</li>

									<li><a href="contact.html">關於我們</a></li>


								</ul>
							</nav>
						</div>
					</div>
					<div class="col-12">
						<div class="mobile-menu"></div>
					</div>
				</div>
			</div>
		</div>
	</header>