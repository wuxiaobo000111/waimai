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
    <div class="col-md-12">
        <form class="form-horizontal" id="form" >
            <br><<br>
            <div class="form-group">
                <label class="col-sm-3 control-label">用户名：</label>

                <div class="col-sm-8">
                    <input type="text" id="flag" value="${user.userName}" name="userName"  class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">邮箱：</label>

                <div class="col-sm-8">
                    <input type="text" value="${user.userEmail}" name="userEmail"  class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">用户电话：</label>

                <div class="col-sm-8">
                    <input type="text" name="userPhone" value="${user.userPhone}" id="password"  class="form-control">
                </div>
            </div>


            <div class="form-group" hidden>

                <div class="col-sm-8">
                    <input type="text" name="userId" value="${user.userId}" id="userId" class="form-control" hidden>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-8">
                    <a class="btn btn-sm btn-white" href="javascript:void(0)" id="button" onclick="addOrUpdate()">修改</a>
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
<script type="text/javascript">
    $(function () {
        var flag=$("#userId").val();
        var button=$("#button").text();
        if(flag!=""){
            $("#button").text("更改");
        }else {
            $("#button").text("新增");
        }
    })
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

    function ajaxRequest(url,user) {

        $.ajax({
            url:url,
            data:JSON.stringify(user),
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
                            window.location.href="/user/index.action"
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