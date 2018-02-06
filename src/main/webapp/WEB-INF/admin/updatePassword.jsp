<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>河大外卖管理系统</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo.ico">

    <link href="${pageContext.request.contextPath}/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    
    
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	

</head>
<body class="gray-bg">
<div class="col-md-12">
    <form class="form-horizontal" >
        <br><<br>
        <div class="form-group">
            <label class="col-sm-3 control-label">用户名：</label>

            <div class="col-sm-8">
                <input type="text" value="${sessionScope.admin.managerName}" readonly class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">原始密码：</label>

            <div class="col-sm-8">
                <input type="text" value="${sessionScope.admin.managerPassword}" readonly class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">新密码：</label>

            <div class="col-sm-8">
                <input type="text" name="newPassword" id="password" placeholder="请输入新的密码" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-8">
                <a class="btn btn-sm btn-white" href="javascript:void(0)" onclick="updatePass()">修改</a>
            </div>
        </div>
    </form>
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
        function updatePass() {
            var newPassword=$("#password").val();
            $.get("/admin/updatePassword.action?newPassword="+newPassword,function (r) {
                var data=JSON.parse(r);
                if (data.code==1){
                    layer.open({
                        title:"提示信息",
                        content:"修改成功，请重新登录",
                        btn:["确定"],
                        yes:function (index,layero) {
                            window.location.href="/index.html"
                        }
                    })
                }
                else{
                    layer.open({
                        title:"提示信息",
                        content:data.data,
                        btn:["确定"],
                        yes:function (index,layero) {
                            window.location.href="/admin/updatePasswordPage.action"
                        }
                    })
                }
            })
        }
    </script>
</body>
</html>