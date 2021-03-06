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
        <form  name="saveNews" class="form-horizontal" id="form" method="post" action="/waimai/news/saveNews.action" enctype="multipart/form-data" >
            <br><br>

            <div class="form-group">
                <label class="col-sm-3 control-label">新闻标题：</label>
                <div class="col-sm-8">
                    <input type="text" value="${news.newsTitle}" name="newsTitle"  class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">新闻编辑：</label>

                <div class="col-sm-8">
                    <textarea style="width:800px;height:300px;visibility:hidden;" name="newsText" id="editor" value='${news.newsText}'>${news.newsText}</textarea>
                </div>
            </div>

            <c:if test="${news.newsTypeId!=null}">
                <div class="form-group">
                    <label class="col-sm-3 control-label">新闻原类型：</label>

                    <div class="col-sm-8">
                        <input type="text" value="${news.newsType.newsTypeName}"  class="form-control">
                    </div>
                </div>
            </c:if>


            <div class="form-group">
                <label class="col-sm-3 control-label">新闻作者：</label>
                <div class="col-sm-8">
                    <input type="text" value="${news.newsAuthorName}" name="newsAuthorName"  class="form-control">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">新闻类型：</label>

                <div class="col-sm-8">
                    <select class="form-control m-b" name="newsTypeId">
                        <c:forEach items="${newsTypes}" var="newsType">
                         <option value="${newsType.newsTypeId}">${newsType.newsTypeName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group" hidden>
                <div class="col-sm-8">
                    <input style="width:800px;height:300px;visibility:hidden;" type="text" name="newsId" value="${news.newsId}" id="newsId"></input>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-8">
                    <button class="btn btn-white"  id="button" type="submit" >修改
                    </button>
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
        var editor=KindEditor.create($("#editor"),{
            filePostName  : "uploadFile",//指定上传文件参数名称
            uploadJson:'/news/savePicture.action',//指定上传文件请求的url。
            dir:"image"
        });
        updateButtonText();

    })

    function getNews() {
        var newsId=$("#newsId");
        if (newsId!=null){
            $.get("/waimai/news/getNews.action?newsId="+newsId,function (r) {
                if(r.code==1){
                    news=r.data;
                }
            })
        }
    }
    function updateButtonText() {
        var flag=$("#newsId").val();
        var button=$("#button").text();
        if(flag!=""){
            $("#button").text("更改");
        }else {
            $("#button").text("新增");
        }

    }

    function addOrUpdate() {
        var flag=$("#userId").val();
        var $user=$("#form").serializeJSON();
        if(flag!=""){
            $("#button").text("更改");
            ajaxRequest("/user/updateUser.action",$user);
        }else {
            $("#button").text("新增");
            ajaxRequest("/user/addUser.action",$user);
        }
    }

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
                            window.location.href="/waimai/food/index.action"
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