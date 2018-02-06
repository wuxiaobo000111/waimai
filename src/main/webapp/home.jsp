<%@ taglib prefix="v-bind" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bobo
  Date: 2018/2/5
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>河大外卖</title>
    <!--fonts-->
    <link href='http://fonts.useso.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!--//fonts-->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/waimai.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${pageContext.request.contextPath}/js/immersive-slider.css" rel="stylesheet" type="text/css">
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Favorites Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
		Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //for-mobile-apps -->
    <!-- js -->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <!-- js -->
    <!-- cart -->
    <script src="js/simpleCart.min.js"> </script>
    <!-- cart -->
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script src="${pageContext.request.contextPath}/libs/vue.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.immersive-slider.js"></script>
    <script src="${pageContext.request.contextPath}/script/wangzhan/home.js"></script>
    <script src="${pageContext.request.contextPath}/script/wangzhan/commons.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#immersive_slider").immersive_slider({
                animation: "bounce", //反弹
                slideSelector: ".slide",
                container: ".main",
                cssBlur: false,    //模糊属性
                pagination: true,
                loop: true,      //循环
                autoStart: 5000  //将此更改为0或false来禁用自动启动
            });

            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
            });
        });
    </script>
</head>
<body>
<div id="dtApp">
    <div class="header">
        <div class="container">
            <div class="top-header">
                <div class="login-section">
                    <ul>
                        <li><a href="login.html">登录</a>  </li> |
                        <li><a href="register.html">注册</a> </li>
                    </ul>
                </div>
                <!-- start search-->
                <div class="search-box">
                    <div id="sb-search" class="sb-search">
                        <form>
                            <input class="sb-search-input" placeholder="请输入想要搜索的外卖" type="search" name="search" id="search">
                            <input class="sb-search-submit" type="submit" value="">
                            <span class="sb-icon-search"> </span>
                        </form>
                    </div>
                </div>
                <!-- search-scripts -->
                <script src="js/classie.js"></script>
                <script src="js/uisearch.js"></script>
                <script>
                    new UISearch( document.getElementById( 'sb-search' ) );
                </script>
                <!-- //search-scripts -->
                <div class="header-right">
                    <div class="cart box_1">
                        <a href="checkout.html">
                            <h3> <span class="simpleCart_total"> $0.00 </span> (<span id="simpleCart_quantity" class="simpleCart_quantity"> 0 </span> items)<img src="images/bag.png" alt=""></h3>
                        </a>
                        <p><a href="javascript:;" class="simpleCart_empty">Empty cart</a></p>
                        <div class="clearfix"> </div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>

    <div class="main">
        <div class="page_container">
            <div id="immersive_slider">
                <div v-for="advert in adverts" class="slide" data-blurred="img/slide1_blurred.jpg">
                    <div class="content">
                        <h2><a href="http://www.bucketlistly.com" target="_blank">BucketListly</a></h2>
                        <p>{{advert.advertDescription}}</p>
                    </div>
                    <div class="image">
                        <a href="javascript:void(0)" target="_blank">
                            <img v-bind:src="advert.advertPictureUrl" alt="Slider 1">
                        </a>
                    </div>
                </div>
                <a href="#" class="is-prev">&laquo;</a>
                <a href="#" class="is-next">&raquo;</a>
            </div>
        </div>
    </div>

    <div>
        <ul>
            <li v-for="advert in adverts">
                {{advert.advertDescription}}
            </li>
        </ul>
    </div>
    <!-- //header -->
    <!-- banner -->
    <div class="banner-slider" style="height:200px;">
        <div class="banner-pos">
            <!-- responsiveslides -->
            <script src="js/responsiveslides.min.js"></script>
            <script>
                // You can also use "$(window).load(function() {"
                $(function () {
                    // Slideshow 4
                    $("#slider3").responsiveSlides({
                        auto: true,
                        pager: false,
                        nav: false,
                        speed: 500,
                        namespace: "callbacks",
                        before: function () {
                            $('.events').append("<li>before event fired.</li>");
                        },
                        after: function () {
                            $('.events').append("<li>after event fired.</li>");
                        }
                    });
                });
            </script>

            <div  id="top" class="callbacks_container">
                <ul class="rslides" id="slider3">
                    <li>
                        <div class="banner one">
                            <div class="container">
                                <div class="navigation text-center">
                                    <span class="menu"><img src="images/menu.png" alt=""/></span>
                                    <ul class="nav1">
                                        <li><a class="active" href="index.html">首页</a></li>
                                        <li><a href="about.html">外卖</a></li>
                                        <li><a href="menu.html">新闻</a></li>
                                        <li><a href="gallery.html">反馈</a></li>
                                        <li><a href="gallery.html">我们</a></li>
                                        <li><a href="gallery.html">我们</a></li>
                                        <div class="clearfix"></div>
                                    </ul>


                                </div>
                                <div class="logo">
                                    <a href="index.html">
                                        <div class="burst-36 ">
                                            <div>
                                                <div><span><img src="images/logo.png" alt=""/></span></div>
                                            </div>
                                        </div>
                                        <h1>河大外卖</h1>
                                    </a>
                                </div>
                                <!-- //point burst circle -->

                                <div class="banner-info text-center">
                                    <p>最好吃，最优惠的外卖</p>
                                </div>
                            </div>
                        </div>
                    </li>


                    <li>
                        <div class="banner two">
                            <div class="container">
                                <div class="navigation text-center">
                                    <span class="menu"><img src="images/menu.png" alt=""/></span>
                                    <ul class="nav1">
                                        <li><a class="active" href="index.html">首页</a></li>
                                        <li><a href="about.html">外卖</a></li>
                                        <li><a href="menu.html">新闻</a></li>
                                        <li><a href="gallery.html">反馈</a></li>
                                        <li><a href="gallery.html">我们</a></li>
                                        <li><a href="gallery.html">我们</a></li>
                                        <div class="clearfix"></div>
                                    </ul>

                                </div>
                                <!-- point burst circle -->
                                <div class="logo">
                                    <a href="index.html">
                                        <div class="burst-36 ">
                                            <div>
                                                <div><span><img src="images/logo.png" alt=""/></span></div>
                                            </div>
                                        </div>
                                        <h1>河大外卖</h1>
                                    </a>
                                </div>
                                <!-- //point burst circle -->

                                <div class="banner-info text-center">
                                    <p>最好吃，最优惠的外卖</p>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>







    <br><br><br><br>
    <!-- delicious -->
    <div class="delicious">
        <div class="container">
            <div class="delicious-info">
                <h3>商家推荐</h3>
                <div class="strip-line"></div>
            </div>
            <div class="delicious-grids">
                <div class="col-md-3 delicious-grid">
                    <h3>PASTA SPECIAL</h3>
                    <img src="images/g3.jpg" alt=""/>
                    <p>Pellentesque ut urna eu mauris scele risque auctor volutpat et massa pers lectus consectetur pellentesque blandit nec orci</p>
                    <a href="#">加入购物车</a>
                </div>
                <div class="col-md-3 delicious-grid">
                    <h3>FRIED CHICKEN</h3>
                    <img src="images/g6.jpg" alt=""/>
                    <p>Pellentesque ut urna eu mauris scele risque auctor volutpat et massa pers lectus consectetur pellentesque blandit nec orci</p>
                    <a href="#">加入购物车</a>
                </div>
                <div class="col-md-3 delicious-grid">
                    <h3>SAUSAGES</h3>
                    <img src="images/g5.jpg" alt=""/>
                    <p>Pellentesque ut urna eu mauris scele risque auctor volutpat et massa pers lectus consectetur pellentesque blandit nec orci</p>
                    <a href="#">加入购物车</a>
                </div>
                <div class="col-md-3 delicious-grid">
                    <h3>BREAD SLICE</h3>
                    <img src="images/g1.jpg" alt=""/>
                    <p>Pellentesque ut urna eu mauris scele risque auctor volutpat et massa pers lectus consectetur pellentesque blandit nec orci</p>
                    <a href="#">加入购物车</a>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <!-- //delicious -->
    <!-- footer-top -->
    <div class="footer-top">
        <div class="container">
            <div class="col-md-3 footer-grid">
                <h3><a href="#">河大外卖</a></h3>
            </div>
            <div class="col-md-3 footer-grid">
                <h4>营业时间</h4>
                <p>星期一 - 星期日<span>7 : 00 - 21 : 00</span></p>
            </div>
            <div class="col-md-3 footer-grid">
                <h4>地址</h4>
                <p>河南省开封市河南大学</p>
            </div>
            <div class="col-md-3 footer-grid">
                <h4>联系方式</h4>
                <ul>
                    <li class="list-one">开封·河南</li>
                    <li class="list-two"><a href="mailto:info@example.com">2244027504@qq.com</a></li>
                    <li class="list-three">+86 18339290851</li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- //footer-top -->
    <!-- footer -->
    <div class="footer">
        <div class="container">
            <div class="footer-left">
                <p>Copyright &copy; 2018.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/"></a></p>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<!-- //footer -->
<!-- smooth scrolling -->
<script type="text/javascript">
    $(document).ready(function() {
        /*
            var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
            };
        */
        $().UItoTop({ easingType: 'easeOutQuart' });
    });
</script>
<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!-- //smooth scrolling -->

</div>

<!-- start-smoth-scrolling -->
</body>
</html>
