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
	

</head>
<body class="my-content">
<div id="dtApp" v-cloak>
    <br><br>
    <div class="col-md-12">
        <form class="form-horizontal" id="form" method="post" action="/advert/addAdvert.action" enctype="multipart/form-data" >
            <br><br>
            <div class="form-group">
                <label class="col-sm-3 control-label">广告名称：</label>

                <div class="col-sm-8">
                    <input type="text" id="flag" value="${advert.advertName}" name="advertName"  class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">广告描述：</label>

                <div class="col-sm-8">
                    <input type="text" value="${advert.advertDescription}" name="advertDescription"  class="form-control">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">广告按钮内容：</label>

                <div class="col-sm-8">
                    <input type="text" value="${advert.advertButtonText}" name="advertButtonText"  class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">是否显示：</label>

                <div class="col-sm-8">
                    <select class="form-control m-b" name="advertShow">
                        <option value="1" selected>显示</option>
                        <option value="2">不显示</option>
                    </select>
                </div>
            </div>
            <c:if test="${advert.advertId!=null}">
                <div class="form-group">
                    <label class="col-sm-3 control-label">绑定外卖名称：</label>

                    <div class="col-sm-8">
                        <input type="text" value="${advert.food.foodName}" readonly  class="form-control">
                    </div>
                </div>
            </c:if>
            <div class="form-group">
                <label class="col-sm-3 control-label">绑定新外卖名称：</label>

                <div class="col-sm-8">
                    <select class="form-control m-b" name="foodId">
                        <option v-for="food in foods"  v-bind:value="food.foodId">{{food.foodName}}</option>
                    </select>
                </div>
            </div>

            <c:if test="${advert.advertPictureUrl!=null}">
                <div class="form-group">
                    <label class="col-sm-3 control-label">食物图片：</label>
                    <div class="col-sm-8">
                        <img src="${advert.advertPictureUrl}" width="200px" height="120px" style="border: 5px">
                    </div>
                </div>
            </c:if>

            <div class="form-group">
                <c:choose>
                    <c:when test="${not empty advert.advertPictureUrl}">
                        <label class="col-sm-3 control-label">广告新图片：</label>
                    </c:when>
                    <c:otherwise>
                        <label class="col-sm-3 control-label">广告图片：</label>
                    </c:otherwise>
                </c:choose>
                <div class="col-sm-8">
                    <input type="file"name="uploadFile">
                </div>
            </div>

            <div class="form-group" hidden>
                <div class="col-sm-8">
                    <input type="text" name="advertId" value="${advert.advertId}" id="advertId" class="form-control" hidden>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-8">
                    <button class="btn btn-white" type="submit" id="button">修改</button>
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
<!-- 全局js设置 -->
<script src="${pageContext.request.contextPath}/script/common.js"></script>
<script src="${pageContext.request.contextPath}/script/houtai/advert/addOrUpdate.js"></script>
<script type="text/javascript">
    $(function () {
        var flag=$("#advertId").val();
        var button=$("#button").text();
        if(flag!=""){
            $("#button").text("更改");
        }else {
            $("#button").text("新增");
        }
        $.get("/food/all.action",function (r) {
            if (r.code==1){
               vm.foods=r.data;
            }
        })
    })
    function ajaxRequest(url,food) {

        $.ajax({
            url:url,
            data:JSON.stringify(food),
            type:"post",
            dataType:"json",
            contentType:"application/json",
            success:function (r) {
                if (r.code==1){
//                   这里表示在数据库中验证成功；
                    layer.open({
                        title:"提示信息",
                        content:"修改成功",
                        btn:["确定"],
                        yes:function (index,layero) {
                            window.location.href="/food/index.action"
                        }
                    })
                }else {
                    layer.open({
                        title:"提示信息",
                        content:r.data,
                        btn:["确定"]
                    })
                }
            }
        })
    }
</script>
</body>
</html>