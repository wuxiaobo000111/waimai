<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>河大外卖管理系统</title>


    <link href="${pageContext.request.contextPath}/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
	<div id="wrapper" v-cloak>
		<!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <img alt="image" class="img-circle" src="/waimai/waimai/touxiang/touxiang.jpg"
                            style="width: 60px" height="60px">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               	<span class="text-muted text-xs block">${sessionScope.admin.managerName}<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            	<li class="divider"></li>
                                <li><a class="J_menuItem" href="/waimai/admin/updatePasswordPage.action">修改密码</a></li>
                                <li><a href="javascript:void(0)" onclick="layout()">安全退出</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a class="J_menuItem" href="/waimai/index/home.action" data-index="0"><i class="fa fa-home"></i> <span class="nav-label">主页</span></a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-user"></i>
                            <span class="nav-label">用户管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/waimai/user/index.action">用户列表</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="#">
                            <i class="fa fa-photo"></i>
                            <span class="nav-label">系统监控</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/waimai/druid">数据库监控</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="#">
                            <i class="fa fa-clipboard"></i>
                            <span class="nav-label">食物管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/waimai/food/index.action">食物列表</a>
                            </li>
                        </ul>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/waimai/foodType/index.action">食物类型列表</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-clipboard"></i>
                            <span class="nav-label">媒体管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/waimai/advert/index.action">广告管理</a>
                            </li>
                        </ul>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/waimai/news/index.action">新闻管理</a>
                            </li>
                        </ul>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/waimai/newsType/index.action">新闻类型管理</a>
                            </li>
                        </ul>
                    </li>
                    <menu-item v-for="item in menuList" v-bind:item="item"></menu-item>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
        	<div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i> 主题
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        	<div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:void(0);" class="active J_menuTab" data-id="home">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="javascript:void(0)" onclick="layout()" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
        	<div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="/waimai/index/home.action" frameborder="0" data-id="home" seamless></iframe>
            </div>
        	<div class="footer">
                <div class="pull-right">&copy; 2017-2099 <a href="http://www.bobo.com/" target="_blank">bobo</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        
        <!--右侧边栏开始-->
        <div id="right-sidebar">
            <div class="sidebar-container">

                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                            <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">主题设置</div>
                            <div class="setings-item">
                                <span>收起左侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                        <label class="onoffswitch-label" for="collapsemenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定顶部</span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                        <label class="onoffswitch-label" for="fixednavbar">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定宽度 </span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                        <label class="onoffswitch-label" for="boxedlayout">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="title">皮肤选择</div>
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         			<a href="#" class="s-skin-0">默认皮肤</a>
                    			</span>
                            </div>
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        			<a href="#" class="s-skin-1">蓝色主题</a>
                    			</span>
                            </div>
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        			<a href="#" class="s-skin-3">黄色主题</a>
                    			</span>
                            </div>
                        </div>
                    </div>
                   
                </div>

            </div>
        </div>
		<!--右侧边栏结束-->
	</div>
	
	<!-- 全局js -->
    <script src="${pageContext.request.contextPath}/libs/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/libs/bootstrap/js/bootstrap.min.js"></script>
    
    <!-- vue -->
    <script src="${pageContext.request.contextPath}/libs/vue.min.js"></script>
    
    <!-- fastjson -->
    <script src="${pageContext.request.contextPath}/libs/FastJson-1.0.min.js"></script>
    
	<!--导航-->
	<script src="${pageContext.request.contextPath}/libs/metisMenu/jquery.metisMenu.js"></script>
    <!--滚动条-->
    <script src="${pageContext.request.contextPath}/libs/slimscroll/jquery.slimscroll.min.js"></script>
	<!--浮层-->
	<script src="${pageContext.request.contextPath}/libs/layer/layer.min.js"></script>
	<!--进度条 -->
    <script src="${pageContext.request.contextPath}/libs/pace/pace.min.js"></script>
	<!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/script/hplus.js"></script>
    <script src="${pageContext.request.contextPath}/script/contabs.js"></script>
    <script type="text/javascript">
        function layout() {
            $.get("/waimai/admin/layout.action",function (r) {
                var data=JSON.parse(r);
                if (data.code==1) {
                   layer.open({
                       title:"提示信息",
                       content:"退出成功",
                       btn:["确定"],
                       yes:function (index,layero) {
                           window.location.href="/waimai/index.html"
                       }
                   })
                }
            })
        }
    </script>
</body>
</html>