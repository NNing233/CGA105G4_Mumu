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
    <title>Bakix - Crowdfunding Startup Fundraising HTML5 Template</title>
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
    <!--?????????: ????????? fontawesome -->
    <link rel="stylesheet" href="../assets/scss/main.css">
    <!--?????????: ??????css ???main.css??? -->
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


                <!--?????????: ?????????????????????????????? ??????-->

            <!-- checkout-area start -->
            <div class="checkout-area pb-90">
                <div class="container">
                    <form>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="checkbox-form">

                                    <!-- <h3>????????????</h3> -->
                                    <div class="row">
                                        <!-- tw zip code start -->
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-12">

                                                    


                                                    <!-- <form action=""> -->
                                                        <div class="card w-100">
                                                            <br>
                                                            <!-- <div class="card-header bg-primary"> -->
                                                                <h3>???????????????</h3>
                                                            <!-- </div> -->
                                                            <div class="card-body bg-transparents">
                                    
                                                                <div class="form-row">
                                                                    <div class="form-group col-md-6">
                                                                        <input type="text" class="form-control" placeholder="??????" required value="<%= memVO.getMemberName() %>">
                                                                    </div>
                                                                    <div class="form-group col-md-6">
                                                                        <select id="inputState" class="form-control">
                                                                            <option selected hidden value="">??????*</option>
                                                                            <option value="1">??????</option>
                                                                            <option value="2">??????</option>
                                                                            <option value="3">??????</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                    
                                                                <div class="form-row">
                                                                    <div class="form-group col-md-6">
                                                                        <input type="tel" class="form-control" placeholder="????????????*" required value="<%= memVO.getMemberPhone() %>">
                                                                    </div>
                                                                    <div class="form-group col-md-6">
                                                                        <input type="email" class="form-control" placeholder="E-Mail" required value="<%= memVO.getMemberEmail() %>">
                                                                    </div>
                                                                </div>
                                    
                                                                <div id="twzipcode" class="form-row">
                                                                    <div class="form-group col">
                                                                        <div data-role="county" data-style="form-control" data-name="county" data-value=""></div>
                                                                    </div>
                                                                    <div class="form-group col">
                                                                        <div data-role="district" data-style="form-control" data-name="district" data-value=""></div>
                                                                    </div>
                                                                    <div class="form-group col">
                                                                        <div data-role="zipcode" data-style="form-control" data-name="zipcode" data-value=""></div>
                                                                    </div>
                                                                </div>
                                                                
                                                                <div class="form-group">
                                                                    <input type="text" class="form-control" id="inputAddress2" placeholder="????????????" required>
                                                                </div>

                                                                <h3>?????????????????????</h3>
                                                                <div class='card-wrapper'></div>
                                                                <!-- CSS is included via this JavaScript file -->
                                                                <script src="./card/card.js"></script>
                                                                <!-- <form id="card-form" action=""> -->
                                                                <br>
                                                                <div style="justify-content: center; display: flex;margin: auto;flex-wrap: wrap;">
                                                                    <input placeholder="??????" type="text" name="number" required>
                                                                    <input placeholder="??????" type="text" name="name" required>
                                                                    <input placeholder="???/???" type="text" name="expiry" required>
                                                                    <input placeholder="CVC" type="text" name="cvc" required>
                                                                </div>
                                                                <!-- </form> -->

                                                            </div>
                                                        </div>
                                                    <!-- </form> -->
                                                </div>
                                            </div>
                                        </div>
                                        <!-- tw zip code start -->

                                    </div>

                                    

                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="your-order mb-30 ">
                                    <h3>????????????</h3>
                                    <div class="your-order-table table-responsive">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th class="product-name">??????</th>
                                                    <th class="product-total">??????</th>
                                                </tr>
                                            </thead>
                                            <tbody id="memShopCartList">
<!--                                             ???javascript??????html -->
<!--                                                 <tr class="cart_item"> -->
<!--                                                     <td class="product-name"> -->
<!--                                                         Vestibulum suscipit -->
<!--                                                         <strong class="product-quantity"> ?? 1</strong> -->
<!--                                                     </td> -->
<!--                                                     <td class="product-total"> -->
<!--                                                         <span class="amount">$165.00</span> -->
<!--                                                     </td> -->
<!--                                                 </tr> -->
                                                
                                            </tbody>
                                            <tfoot>
                                                <tr class="cart-subtotal">
                                                    <th>???????????????</th>
                                                    <td><span class="amount">9527</span></td>
                                                </tr>

                                                <tr class="coupon">
                                                    <th>????????????</th>
                                                    <td><span class="amount">0</span></td>
                                                </tr>
                                                <%-- <tr class="shipping">
                                                    <th>Shipping</th>
                                                    <td>
                                                        <ul>
                                                            <li>
                                                                <input type="radio" />
                                                                <label>
                                                                    Flat Rate: <span class="amount">$7.00</span>
                                                                </label>
                                                            </li>
                                                            <li>
                                                                <input type="radio" />
                                                                <label>Free Shipping:</label>
                                                            </li>
                                                            <li></li>
                                                        </ul>
                                                    </td>
                                                </tr> --%>
                                                <tr class="order-total">
                                                    <th>?????????</th>
                                                    <td>
                                                    	<strong>
                                                    		<span class="amount">9527</span>
                                                    	</strong>
                                                    </td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>

                                    <div class="payment-method">
                                        <div class="order-button-payment mt-20">
                                            <button id="PlaceOrder" type="submit" class="btn">Place Order</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- checkout-area end -->

                <!--?????????: ?????????????????????????????? ??????-->
        <!--?????????: ???????????????????????????????????????????????? section????????????main????????? -->


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
                            <h4 class="footer-title">?????? Mumu</h4>
                            <ul class="footer-link">
                                <li><a href="#">????????????</a></li>

                            </ul>
                        </div>
                    </div>
                    <div class="col-xl-2 col-lg-3 col-md-4">
                        <div class="footer-widget mb-40">
                            <h4 class="footer-title">????????????</h4>
                            <ul class="footer-link">
                                <li><a href="#">??????????????????</a></li>


                            </ul>
                        </div>
                    </div>

                    <div class="col-xl-2 col-lg-3 col-md-4">
                        <div class="footer-widget mb-40">
                            <h4 class="footer-title">????????????</h4>
                            <ul class="footer-link">
                                <li><a href="#">?????????</a></li>
                                <li><a href="#">- ????????????????????????</a></li>
                                <li><a href="#">- ???????????????????????????</a></li>
                                <li><a href="#">- ??????????????????</a></li>
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

    <!-- ?????????: ??????????????? js????????? dropdown?????? -->
    <script src="../assets/js/dropdown.js"></script>
    <!-- include checkout credit card js-->
    <script src="./card/jquery.card.js"></script>
    <!-- include tw zip code -->
    <script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
    
    <script>
        $(document).ready(function(){
            $("#twzipcode").twzipcode({
            });

            // document.getElementById('card-form').card({
            $('form').card({
                // a selector or DOM element for the container
                // where you want the card to appear
                container: '.card-wrapper', // *required*
                // all of the other options from above
                placeholders: {
                number: '???????????? ???????????? ???????????? ????????????',
                name: 'Full Name',
                expiry: '??????/??????',
                cvc: '?????????'
                },

                formSelectors: {
                numberInput: 'input[name="number"]', // optional ??? default input[name="number"]
                expiryInput: 'input[name="expiry"]', // optional ??? default input[name="expiry"]
                cvcInput: 'input[name="cvc"]', // optional ??? default input[name="cvc"]
                nameInput: 'input[name="name"]' // optional - defaults input[name="name"]
                },

                messages: {
                validDate: 'valid\ndate', // optional - default 'valid\nthru'
                monthYear: 'mm/yy', // optional - default 'month/year'
                }
            });
        })
    </script>
    
	<script type="text/javascript">
		const shopCartList = JSON.parse(JSON.stringify(${list}));
	</script>
	

	<script type="text/javascript">
        $(document).ready(function() {
            let cartListData = "";
            let sumTotalAmount = 0;
            for(let i = 0 ; i < shopCartList.length; i++){
                let prodSubtotalAmount = (shopCartList[i].PRODUCT_OPTION_PRICE * shopCartList[i].PRODUCT_OPTION_QUANTITY);
                cartListData +=`
				                <tr class="cart_item">
				                    <td class="product-name">
				                  		  \${shopCartList[i].PRODUCT_NAME}<br>\${shopCartList[i].PRODUCT_OPTION_NAME}
				                        <strong class="product-quantity"> ?? \${shopCartList[i].PRODUCT_OPTION_QUANTITY}</strong>
				                    </td>
				                    <td class="product-total">
				                        <span class="amount"> \${prodSubtotalAmount} </span>
				                    </td>
				                </tr>
				                `;
                sumTotalAmount += prodSubtotalAmount;
            }
            $("#memShopCartList").append(cartListData);
            $('tfoot tr.cart-subtotal td span.amount').text(sumTotalAmount);
            $('tfoot tr.order-total td span.amount').text(sumTotalAmount); 
        });
	</script>
	
    <script>
        function syncDelay(milliseconds){
            var start = new Date().getTime();
            var end=0;
            while( (end-start) < milliseconds){
                end = new Date().getTime();
            }
        }

        function redirectShopMain(){
            window.location.href="<%= request.getContextPath() %>/front_end/shop/shop-main.jsp"
        }
        
        $(document).ready(function(){
            $('#PlaceOrder').click(function(){
                syncDelay(1000);
                alert("??????????????????, ??????????????????");
                // ??????QueryString
                let uri = window.location.toString();
                if (uri.indexOf("?") > 0) {
                    let clean_uri = uri.substring(0, uri.indexOf("?"));
                    window.history.replaceState({}, document.title, clean_uri);
                }
                // ????????????
                redirectShopMain();
                return false;
            })
        });
    </script>

</body>

</html>