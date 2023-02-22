<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.MemVO"%>
<%@ page import="com.shoppingcartlist.model.*"%>
<%
    String location = (request.getQueryString()==null)? request.getRequestURL().toString() :request.getRequestURL().append("?").append(request.getQueryString()).toString();
    HttpSession session1 = request.getSession();
    session1.setAttribute("location", location);
%>
<%
	MemVO memVO = (MemVO)session1.getAttribute("mem");
	try {
		ShoppingCartListService shopCartListSvc = new ShoppingCartListService();
		List list = shopCartListSvc.getMemShopCartList(memVO.getMemberId());
		pageContext.setAttribute("list", list);
	} catch (Exception e) {
		if(memVO == null){
			System.out.println("Member ID = null, redirect to main page\n" + e.getMessage());
			response.sendRedirect(request.getContextPath().toString() + "/front_end/shop/shop-main.jsp");
			response.flushBuffer();
			return;
		}
		e.getStackTrace();
	}
%>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>mumu - 購物車</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="../assets/css/responsive.css">


    <script src="https://kit.fontawesome.com/fb61cff1c9.js" crossorigin="anonymous"></script>
    <!--請注意: 要引入 fontawesome -->
    <link rel="stylesheet" href="../assets/scss/main.css">
    <!--請注意: 可寫css 在main.css內 -->
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

            <!-- Cart Area Strat-->
            <div class="cart-area pt-120 pb-120">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <form action="#">
                                <div class="table-content table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th class="product-thumbnail">商品圖片</th>
                                                <th class="cart-product-name">商品</th>
                                                <th class="product-price">單價</th>
                                                <th class="product-quantity">數量</th>
                                                <th class="product-stock">庫存</th>
                                                <th class="product-subtotal">金額</th>
												<!-- <th class="product-favorite">收藏</th> -->
                                                <th class="product-remove">移除</th>
                                            </tr>
                                        </thead>
                                        <tbody id="memShopCartList">
                                           <%-- 通過for迴圈添加 - start --%>
                                            <%-- <tr>
                                                <td class="product-thumbnail"><a href="#"><img src="../assets/img/shop/cart/img1.jpg" alt=""></a></td>
                                                <td class="product-name"><a href="#">Bakix Furniture</a></td>
                                                <td class="product-price"><span class="amount">$130.00</span></td>
                                                <td class="product-quantity">
                                                    <div class="cart-plus-minus"><input type="text" value="1" /></div>
                                                </td>
                                                <td class="product-stock"><span class="stock">999</span></td>
                                                <td class="product-subtotal"><span class="amount">$130.00</span></td>
                                                <td class="product-favorite"><a href="#"><i class="fa fa-heart fa-lg"></i></a></td>
                                                <td class="product-remove"><a href="#"><i class="fa fa-times fa-lg"></i></a></td>
                                            </tr> --%>
                                            <%-- 通過for迴圈添加 - end --%>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="coupon-all">
                                            <div class="coupon">
										     <FORM METHOD="get" ACTION="product_mumu.do" >
<!-- 										       <b>優惠券:</b> -->
<!-- 										       <select size="1" id="coupon_code" name="coupon_code"> -->
<!-- 										       		<option value="default" disabled selected>選擇其他商品</option> -->
<%-- 													<c:forEach var="couponVO" items="${couponVO}">  --%>
<%-- 		                                        	<option value="${shopProdVO.productOptionId}" data-price="${shopProdVO.productOptionPrice}">${shopProdVO.productOptionName}</option> --%>
<%-- 		                                        	</c:forEach>   --%>
<!-- 										       </select> -->
<!-- 										       <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 										       <input type="submit" value="送出"> -->
										     </FORM>
<!--                                                 <input id="coupon_code" class="input-text" name="coupon_code" value="" placeholder="優惠碼" -->
<!--                                                     type="text"> -->
<!--                                                 <button class="btn btn-black" name="apply_coupon" type="submit">套用優惠碼</button> -->
                                            </div>
                                            <div class="coupon2">
<!--                                                 <input class="btn btn-black" name="update_cart" value="更新購物車" type="submit"> -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-5 ml-auto">
                                        <div class="cart-page-total">
                                            <h2>總計</h2>
                                            <ul class="mb-20">
                                                <li>小計 <span   id="countSubTotalAmount">0</span></li>
                                                <li>折抵金額 <span      id="deductAmount">0</span></li>
                                                <li>總金額 <span    id="countTotalAmount">0</span></li>
                                            </ul>
                                            <a class="btn btn-theme" href="<%=request.getContextPath()%>/front_end/shop/shop-checkout.jsp">結帳</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Cart Area End-->
        <!--請注意: 依需求，可多新增一個跟上面一樣的 section，新增在main標籤內 -->





    </main>
    <footer data-background="../assets/img/bg/footer.jpg">

        <div class="footer-area pt-5">
            <div class="container">
                <div class="row">
                    <div class="col-xl-2 col-lg-3 col-md-4">
                        <div class="footer-widget mb-40">
                            <div class="footer-logo mb-25">
                                <img src="../assets/img/logo/Mumu_logo.png" alt="">
                            </div>
                            <div class="social-icon">
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

    <!-- 請注意: 要引入下方 js，確保 dropdown功能 -->
    <script src="../assets/js/dropdown.js"></script>
    
	<script type="text/javascript">
		const shopCartList = JSON.parse(JSON.stringify(${list}));
		// console.log(shopCartList);
	</script>


	<script type="text/javascript">
        $(document).ready(function() {
            let cartListData = "";
            for(let i = 0 ; i < shopCartList.length; i++){
                let prodSubtotalAmount = (shopCartList[i].PRODUCT_OPTION_PRICE * shopCartList[i].PRODUCT_OPTION_QUANTITY); 
                cartListData +=`
                                                <tr>
                                                    <td class="product-thumbnail">
                                                        <a href="<%= request.getContextPath() %>/front_end/shop/productDetail.do?action=getOne_For_Display&productId=\${shopCartList[i].PRODUCT_ID}">
                                                            <img src="<%=request.getContextPath() %>/front_end/shop/getPordOptImage.do?productOptionId=\${shopCartList[i].PRODUCT_OPTION_ID}" style="width: 100px;" alt="">
                                                        </a>
                                                    </td>
                                                    <td class="product-name">
                                                        <a href="<%= request.getContextPath() %>/front_end/shop/productDetail.do?action=getOne_For_Display&productId=\${shopCartList[i].PRODUCT_ID}">
                                                        	\${shopCartList[i].PRODUCT_NAME}<br>\${shopCartList[i].PRODUCT_OPTION_NAME}
                                                        </a>
                                                    </td>
                                                    <td class="product-price"><span id="prodPrice" class="amount">\${shopCartList[i].PRODUCT_OPTION_PRICE}</span></td>
                                                    <td class="product-quantity">
                                                        <div class="cart-plus-minus">
                                                            <input id="prodQty-\${i+1}" type="text" value="\${shopCartList[i].PRODUCT_OPTION_QUANTITY}" />
                                                        </div>
                                                    </td>
                                                    <td class="product-stock"><span class="stock">\${shopCartList[i].PRODUCT_OPTION_INVENTORY}</span></td>
                                                    <td class="product-subtotal"><span id="prodSubtotalAmount" class="amount">\${prodSubtotalAmount}</span></td>
                                                    <td class="product-remove">
                                                    	<a href="<%= request.getContextPath() %>/front_end/shop/shopCartList.do?action=delOneProdFromShopCartList&productOptionId=\${shopCartList[i].PRODUCT_OPTION_ID}">
                                                    		<i class="fa fa-times fa-lg"></i>
                                                    	</a>
                                                    </td>
                                                </tr> 
                                                `;
            }
            $("#memShopCartList").append(cartListData);
            // 在商品清單append完後, 在總計區塊顯示初始金額
            let countSubTotalAmount = 0;
            $("span[id^='prodSubtotalAmount']").each(function(){
                countSubTotalAmount += parseInt($(this).text());
            });
            $('#countSubTotalAmount').text(countSubTotalAmount);
            $('#countTotalAmount').text(countSubTotalAmount);

        	/*----- cart-plus-minus-button -----*/
        	$(".cart-plus-minus").append('<div class="dec qtybutton">-</div><div class="inc qtybutton">+</div>');
            $(".qtybutton").on("click", function () {
                let prodPrice = $(this).closest('tr').find('#prodPrice').text();
                let stockNum = parseFloat($(this).closest('tr').find('.stock').text());
                let prodSubtotalAmount = $(this).closest('tr').find('#prodSubtotalAmount');
                var $button = $(this);
                var oldValue = parseFloat($button.parent().find("input").val());
                switch ($button.text()) {
                    case '+':
                        if(oldValue < stockNum){
                            var newVal = oldValue + 1;
                            $button.parent().find("input").val(newVal);
                            prodSubtotalAmount.text(newVal * prodPrice);
                        }else if(oldValue > stockNum){
                            var newVal = stockNum ;
                            $button.parent().find("input").val(newVal);
                            prodSubtotalAmount.text(newVal * prodPrice);
                        } else {
                            // else for out of stock limit
                            var newVal = oldValue;
                        }
                        break;
                    case '-':
                        // Don't allow decrementing below zero
                        if (oldValue > 1) {
                            var newVal = oldValue - 1;
                            $button.parent().find("input").val(newVal);
                            prodSubtotalAmount.text(newVal * prodPrice);
                        } else if (oldValue < 1) {
                            var newVal = 1;
                            $button.parent().find("input").val(newVal);
                            prodSubtotalAmount.text(newVal * prodPrice);
                        } else {
                            newVal = oldValue;
                        }
                        break;
                }

                // 金額小計區塊計算
                let deductAmount = $('#deductAmount').text();
                // 點擊 + - 按鈕之後變動小計金額
                let countSubTotalAmount = 0;
                $("span[id^='prodSubtotalAmount']").each(function(){
                    countSubTotalAmount += parseInt($(this).text());
                });
                $('#countSubTotalAmount').text(countSubTotalAmount);
                $('#countTotalAmount').text(countSubTotalAmount);

                
            });
        });
	</script>
    
</body>

</html>