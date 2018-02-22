<%--
  Created by IntelliJ IDEA.
  User: tianrun-bobo
  Date: 2018/2/21
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>河大外卖登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">

    <style type="text/css">
        .daohang li a{
            font-size: 18px;
        }
        .font{
            font-size: 24px;
        }
    </style>
</head>
<body style="width: 100%;">

<div class="layui-container" id="head" style="width: 100%">
    <div class="layui-row">
        <div class="layui-col-md1 layui-col-lg-offset8">
            <a class="layui-btn layui-btn-primary" href="javasrript:void(0)" onclick="car()">购物车</a>
        </div>
        <div class="layui-col-md1 ">
            <c:choose>
                <c:when test="${! empty sessionScope.user}">
                    <a class="layui-btn layui-btn-primary" href="#">${sessionScope.user.userName}的中心</a>
                </c:when>
                <c:otherwise>
                    <a class="layui-btn layui-btn-primary" href="/qiantaiuser/loginPage.action">登录</a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="layui-col-md1">
            <a class="layui-btn layui-btn-primary" href="/register/register.action">注 册</a>
        </div>
        <div class="layui-col-md1">
            <a class="layui-btn layui-btn-primary" href="/qiantaiuser/lagout.action">登出</a>
        </div>
    </div>
    <br><br>
    <div  style="width: 100%">
        <ul class="layui-nav layui-bg-green daohang" lay-filter="">
            <li class="layui-nav-item layui-col-md-offset2 layui-col-md1">
                <a href="/qiantaiFood/index.action">外卖</a>
                <dl class="layui-nav-child">
                    <c:forEach items="${foodTypes}" var="foodType">
                        <dd><a href="/qiantaiNews/index.action?newsTypeId=${foodType.foodTypeId}">${foodType.foodTypeName}</a></dd>
                    </c:forEach>
                </dl></li>
            <li class="layui-nav-item layui-col-md-offset1 layui-col-md1">
                <a href="/qiantaiNews/index.action" id="news" onmouseover="loadnewsType()">新闻</a>
                <dl class="layui-nav-child">
                    <c:forEach items="${newsTypes}" var="newsType">
                        <dd><a href="/qiantaiNews/index.action?newsTypeId=${newsType.newsTypeId}">${newsType.newsTypeName}</a></dd>
                    </c:forEach>
                </dl>
            </li>
            <li class="layui-nav-item layui-col-md-offset2 layui-col-md1"><a href="/qiantaiFeedback/index.action">反馈</a></li>

            <li class="layui-nav-item layui-col-md-offset1 "><a href="/aboutus.action">关于我们</a></li>
        </ul>
    </div>
    <br><br>
    <div class="layui-container" >
        <form class="layui-form" id="form" action="" method="post">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text"  id="userName" autocomplete="off" readonly value="${sessionScope.user.userName}" class="layui-input">

            </div>

            <br>
            <div class="layui-input-block"  hidden="hidden" >
                <input type="text" name="userId" autocomplete="off" readonly value="${sessionScope.user.userId}" class="layui-input">

            </div>

            <br>
            <label class="layui-form-label">反馈标题</label>
            <div class="layui-input-block">
                <input type="text" name="feedbackName" autocomplete="off"  class="layui-input">
            </div>
            <br>


            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">反馈详细内容</label>
                <div class="layui-input-block">
                    <textarea name="feedbackMessage" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
            </div>
            <br>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="formDemo">反馈</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>

    <br><br>
    <div id="footer" style="width: 100%;height: 40px">
        <div class="layui-col-md4" style="height: 300px">
            <div style="border: 10px">
                <p style="position: relative">地址:</p>
                <p class="font">河南省开封市河南大学</p>
            </div>
        </div>
        <div class="layui-col-md4" style="height: 300px">
            <p>联系电话:</p>
            <p class="font">18339290851</p>
        </div>
        <div class="layui-col-md4" style="height: 300px">
            <p>制作人:</p>
            <p class="font">吴晓波</p>
        </div>

    </div>
</div>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/libs/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/jquery.serializejson.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/layer/layer.min.js"></script>
<script type="text/javascript">
    layui.config({
        version: '1515376178738' //为了更新 js 缓存，可忽略
    });
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element','form'], function(){
        var laydate = layui.laydate //日期
            ,laypage = layui.laypage //分页
            ,layer = layui.layer //弹层
            ,table = layui.table //表格
            ,carousel = layui.carousel //轮播
            ,upload = layui.upload //上传
            ,element = layui.element //元素操作
            ,form=layui.form;//表单元素
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,height: "400px"
            ,arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });
        //监听提交
        form.on('submit(formDemo)', function(data){
            $.ajax({
                url:"/qiantaiFeedback/addFeedback.action",
                data:JSON.stringify(data.field),
                type:"post",
                dataType:"json",
                contentType:"application/json",
                success:function (r) {
                    if (r.code==1){
//                   这里表示在数据库中验证成功；
                        layer.open({
                            title:"提示信息",
                            content:"反馈成功",
                            btn:["确定"],
                            yes:function (index,layero) {
                                window.location.href="/index.action"
                            }
                        })
                    }else {
                        layer.open({
                            title:"提示信息",
                            content:r.data,
                            btn:["确定"],
                            yes:function (index,layero) {
                                window.location.href="/qiantaiFeedback/index.action"
                            }
                        })
                    }
                }
            })
            return false;
        });
    });
    function car() {
        $.get("/qiantaiCaritem/car.action",function (r) {
            var data=JSON.parse(r);
            if(data.code==1){
                layer.open({
                    title:"提示信息",
                    content:data.data,
                    btn:["确定"],
                    yes:function (index,layero) {
                        window.location.href="/qiantaiCaritem/carList.action";
                    }
                })
            }else{
                layer.open({
                    title:"提示信息",
                    content:data.data,
                    btn:["确定"],
                    yes:function (index,layero) {
                        window.location.href="/qiantaiuser/loginPage.action";
                    }
                })
            }
        })
    }
</script>
</body>
</html>
