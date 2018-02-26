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
    <title>河大外卖支付</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link href="${pageContext.request.contextPath}/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">


    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
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
    <div class="layui-container"  style="width: 100%;height:auto;">
        <div class="layui-row" >
            <div class="layui-col-md8" >
                <table id="table"></table>
            </div>
            <div class="layui-col-md3 layui-col-md-offset1" id="container" >
                <br>
                <input type="text" value="" name="totalMoney" id="totalMoney" hidden="hidden">
            </div>
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
<script src="${pageContext.request.contextPath}/libs/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/jquery.serializejson.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/layer/layer.min.js"></script>
<!-- bootstrap table -->
<script src="${pageContext.request.contextPath}/libs/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
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
                url:"/waimai/qiantaiuser/login.action",
                data:JSON.stringify(data.field),
                type:"post",
                dataType:"json",
                contentType:"application/json",
                success:function (r) {
                    if (r.code==1){
//                   这里表示在数据库中验证成功；
                        layer.open({
                            title:"提示信息",
                            content:"登录成功",
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
    function getNewsList() {
        $('#table').bootstrapTable({
            url:"/waimai/qiantaiOrder/list.action",
            pageSize:10000,
            classes:"table table-hover",
            pagination: true,	//显示分页条
            sidePagination: 'server',//服务器端分页
            pageList:[2,4,5,10],
            strictSearch: true,
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,               //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列

            detailView: false,                  //是否显示父子表
            toolbar: '#toolbar',
            striped: true,     //设置为true会有隔行变色效果
            columns:[
                {
                    field: 'foodId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                { title: '食物名称', field: 'foodName'},
                { title: '美图', field: 'foodPictureUrl',formatter:function (value,row,index) {
                    var pic='<img src="/waimai'+value+'" style="width: 100px" height="60px">'
                    return pic
                }},
                { title: '价钱', field: 'money'},
                { title: '购买数量', field: 'number'}
            ]
        });

    }

    function refresh(){
        window.location.reload();//刷新当前页面.
    }
    $(function () {
        var newsTypeId=$("#newsTypeId").val();
        getNewsList(newsTypeId);
//        1.加载地址

//        2.加载支付总金额
        $.get("/waimai/qiantaiOrder/getOther.action",function (r) {
            var data=JSON.parse(r);
            if (data.code==1){
                var container=$("#container");
                var addressName="<p>送餐地址:&nbsp"+data.data.addressName+"</p>"
                var addressLianxi="<p>送餐联系人:&nbsp"+data.data.addressLianxi+"</p>"
                var addressPhone="<p>送餐电话:&nbsp"+data.data.addressPhone+"</p>"
                var money="<h1>应支付金额:&nbsp"+data.data.totalMoney+"元</h1>"
                container.append(addressName);
                container.append(addressLianxi);
                container.append(addressPhone);
                container.append(money);
                var button="<button class='layui-btn layui-btn-lg layui-btn-radius layui-btn-normal' onclick='pay()'>马上支付</button>";
                container.append(button);
                var totalMoney=$("#totalMoney");
                totalMoney.val(data.data.totalMoney);
            }
        })
    })
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
    function pay() {
        var totalMoney=$("#totalMoney").val();
        $.get("/waimai/qiantaiOrder/zhifu.action?totalMoney="+totalMoney,function (r) {
           var data=JSON.parse(r);
           if(data.code==1){
               layer.open({
                   title:"提示信息",
                   content:"支付成功",
                   btn:["确定"],
                   yes:function (index,layero) {
                       window.location.href="/waimai/index.action";
                   }
               })
           }
        })
    }
</script>
</body>
</html>
