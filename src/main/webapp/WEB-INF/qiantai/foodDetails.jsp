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
    <div class="layui-container" style="width: 100%;height:auto;background-color: #E6E6E6">
        <div class="layui-row">
            <div class="layui-col-md1 layui-col-lg-offset8">
                <a class="layui-btn layui-btn-primary" href="javasrript:void(0)" onclick="car()">购物车</a>
            </div>
            <div class="layui-col-md1 ">
                <c:choose>
                    <c:when test="${! empty sessionScope.user}">
                        <a class="layui-btn layui-btn-primary" href="/waimai/qiantaiuser/center.action">${sessionScope.user.userName}的中心</a>
                    </c:when>
                    <c:otherwise>
                        <a class="layui-btn layui-btn-primary" href="/waimai/qiantaiuser/loginPage.action">登录</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="layui-col-md1">
                <a class="layui-btn layui-btn-primary" href="/waimai/register/register.action">注 册</a>
            </div>
            <div class="layui-col-md1">
                <a class="layui-btn layui-btn-primary" href="/waimai/qiantaiuser/lagout.action">登出</a>
            </div>
        </div>
    </div>
    <br><br>
    <div  style="width: 100%">
        <ul class="layui-nav layui-bg-green daohang" lay-filter="">
            <li class="layui-nav-item layui-col-md-offset2 layui-col-md1">
                <a href="/waimai/qiantaiFood/index.action">外卖</a>
                <dl class="layui-nav-child">
                    <c:forEach items="${foodTypes}" var="foodType">
                        <dd><a href="/waimai/qiantaiNews/index.action?newsTypeId=${foodType.foodTypeId}">${foodType.foodTypeName}</a></dd>
                    </c:forEach>
                </dl>
            </li>
            <li class="layui-nav-item layui-col-md-offset1 layui-col-md1">
                <a href="/waimai/qiantaiNews/index.action" id="news" onmouseover="loadnewsType()">新闻</a>
                <dl class="layui-nav-child">
                    <c:forEach items="${newsTypes}" var="newsType">
                        <dd><a href="/waimai/qiantaiNews/index.action?newsTypeId=${newsType.newsTypeId}">${newsType.newsTypeName}</a></dd>
                    </c:forEach>
                </dl>
            </li>
            <li class="layui-nav-item layui-col-md-offset2 layui-col-md1"><a href="/waimai/qiantaiFeedback/index.action">反馈</a></li>

            <li class="layui-nav-item layui-col-md-offset1 "><a href="/waimai/aboutus.action">关于我们</a></li>
        </ul>
    </div>
    <br><br>
    <div class="layui-container" >

        <div class="layui-row">
            <div class="layui-col-md6">
                <form class="layui-form" >
                    <label class="layui-form-label">外卖图片</label>
                    <div class="layui-input-block">
                        <img src="/waimai${food.foodPictureUrl}">
                    </div>
                    <br>
                    <label class="layui-form-label">外卖描述</label>
                    <div class="layui-input-block">
                        <input type="text" readonly value="${food.foodDescription}" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                    </div>
                    <br>
                    <label class="layui-form-label">外卖价格</label>
                    <div class="layui-input-block">
                        <input type="text" readonly value="${food.foodPrice}" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                    </div>
                    <br>

                </form>
                <br><br><br>
                <div class="layui-container" >
                    <h1>卖家评论秀:</h1><br>
                    <div class="layui-collapse" id="discuess" lay-accordion>

                    </div>

                </div>
            </div>
            <div class="layui-col-md4 layui-col-md-offset2" >
                <form class="layui-form" id="form" action="" method="post">
                    <div class="layui-input-block" hidden="hidden">
                        <input type="text" name="foodId" value="${food.foodId}" hidden="hidden"  autocomplete="off" placeholder="请输入用户名" class="layui-input">
                    </div>
                    <br>
                    <label class="layui-form-label">购买数量</label>
                    <div class="layui-input-block">
                        <input type="text" name="caritemNumber"  autocomplete="off" placeholder="请输入购买数量" class="layui-input">
                        <p id="message"></p>
                    </div>

                    <br>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit="" lay-filter="formDemo">加入购物车</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

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
                url:"/waimai/qiantaiCaritem/add.action",
                data:JSON.stringify(data.field),
                type:"post",
                dataType:"json",
                contentType:"application/json",
                success:function (r) {
                    if (r.code==1){
//                   这里表示在数据库中验证成功；
                        layer.open({
                            title:"提示信息",
                            content:r.data,
                            btn:["确定"],
                            yes:function (index,layero) {
                                window.location.href="/waimai/index.action"
                            }
                        })
                    }else {
                        layer.open({
                            title:"提示信息",
                            content:r.data,
                            btn:["确定"],
                            yes:function (index,layero) {
                                window.location.href="/waimai/register/register.action"
                            }
                        })
                    }
                }
            })
            return false;
        });
    });
    function car() {
        $.get("/waimai/qiantaiCaritem/car.action",function (r) {
            var data=JSON.parse(r);
            if(data.code==1){
                window.location.href="/waimai/qiantaiCaritem/carList.action";
            }else{
                layer.open({
                    title:"提示信息",
                    content:data.data,
                    btn:["确定"],
                    yes:function (index,layero) {
                        window.location.href="/waimai/qiantaiuser/loginPage.action";
                    }
                })
            }
        })
    }
    $(function () {
        $.get("/waimai/qiantaiFood/listDiscuss.action",function (r) {
            var data=JSON.parse(r);
            if(data.code==1){
                var discusses=$("#discuess");
                for(var i=0;i<data.data.length;i++){
                    var dis=data.data[i];
                    var flag=" <div class='layui-colla-item'><h2 class='layui-colla-title'>"+dis.userName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;购买日期:"+dis.discussCreateTime+"</h2><div class='layui-colla-content layui-show'>"+dis.discussMessage+"</div></div>"
                    discusses.append(flag);
                }
            }

        })
    })
</script>
</body>
</html>
