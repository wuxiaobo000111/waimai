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
<body class="my-content">
<div id="dtApp" v-cloak>
    <br><br>
    <div id="toolbar">
            <a href="/waimai/advert/add.action" class="btn btn-success " type="button">
                <i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">新增</span>
            </a>
            <button class="btn btn-danger " type="button" @click="deleteBatch">
                <i class="fa fa-remove"></i>&nbsp;&nbsp;<span class="bold">删除</span>
            </button>
    </div>

    <table id="table"></table>
</div>
<!-- 全局js -->
<script src="${pageContext.request.contextPath}/libs/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/bootstrap/js/bootstrap.min.js"></script>

<!-- vue -->
<script src="${pageContext.request.contextPath}/libs/vue.min.js"></script>

<!--浮层-->
<script src="${pageContext.request.contextPath}/libs/layer/layer.min.js"></script>

<!-- bootstrap table -->
<script src="${pageContext.request.contextPath}/libs/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<!-- fastjson 解析-->
<script src="${pageContext.request.contextPath}/libs/FastJson-1.0.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/jquery.datetimepicker/jquery.datetimepicker.js"></script>
<!-- 全局js设置 -->
<script src="${pageContext.request.contextPath}/script/common.js"></script>
<script src="${pageContext.request.contextPath}/script/houtai/advert/index.js"></script>
</body>
</html>