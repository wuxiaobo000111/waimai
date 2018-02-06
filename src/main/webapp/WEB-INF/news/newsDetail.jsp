<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>河大外卖管理系统</title>

    <link href="${pageContext.request.contextPath}/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/kindeditor/themes/default/default.css">
</head>
<body class="my-content">
<div id="dtApp" v-cloak>
    <br><br>
    <div class="col-md-12">
        <form  name="saveNews" class="form-horizontal">
            <br><br>

            <div class="ibox-content">
                <div class="pull-right">
                    <button class="btn btn-white btn-xs" type="button"> 作者:${news.newsAuthorName}</button>
                    <button class="btn btn-white btn-xs" type="button">日期:${news.newsCreateTime}</button>
                </div>
                <div class="text-center article-title">
                    <h1>
                        ${news.newsTitle}
                    </h1>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-8 col-sm-offset-3">
                    <textarea style="width:800px;height:300px;visibility:hidden;" name="newsText" id="editor" value='${news.newsText}'>${news.newsText}</textarea>
                </div>
            </div>
        </form>
    </div>
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
<script src="${pageContext.request.contextPath}/libs/jquery.serializejson.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/jquery.datetimepicker/jquery.datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
<script src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
<!-- 全局js设置 -->
<script src="${pageContext.request.contextPath}/script/common.js"></script>


<script type="text/javascript">

    $(function () {
        var editor=KindEditor.create($("#editor"));
    })
</script>
</body>
</html>