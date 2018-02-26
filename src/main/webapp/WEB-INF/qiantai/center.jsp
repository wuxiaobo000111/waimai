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
    <title>河大外卖我的中心</title>
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
                        <dd><a href="/waimai/qiantaiFood/index.action?foodTypeId=${foodType.foodTypeId}">${foodType.foodTypeName}</a></dd>
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
    <div class="layui-container" style="width: 100%;height:auto;" >
        <div class="layui-row">
            <div class="layui-col-md2">
                <ul class="layui-nav layui-nav-tree" lay-filter="test">
                    <li class="layui-nav-item">
                        <a href="javascript:void(0)" onclick="myaddress()">我的地址</a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:void(0)" onclick="myConsume()">我的消费记录</a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:void(0)" onclick="myAccount()">我的个人信息</a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:void(0)" onclick="myDiscuss()">我的评价</a>
                    </li>
                </ul>

            </div>
            <div class="layui-col-md10"  id="container">
                <table id="table"></table>
                <form class='layui-form' id='form'></form>
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
    });


    $(function () {
        var foodTypeId=$("#foodTypeId").val();
        getFoodsList(foodTypeId);
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
    function myAccount() {
        var container=$("#container");
        var userform=$("#form");
        container.children("a").remove();
        userform.html("");
        $.get("/waimai/qiantaiuser/personal.action",function (r) {
            var data=JSON.parse(r);
            if(data.code==1){

                $("#table").bootstrapTable('destroy');
                var user=data.data;

                var username="<label class='layui-form-label layui-col-md3'>用户名</label><div class='layui-input-block'><input type='text' name='userName' id='userName' autocomplete='off' readonly value='"+user.userName+"' class='layui-input'></div><br>";
                var userPhone="<label class='layui-form-label layui-col-md3 '>用户电话</label><div class='layui-input-block'><input type='text' name='userPhone' id='userPhone' autocomplete='off' readonly value='"+user.userPhone+"' class='layui-input'></div><br>";
                var userEmail="<label class='layui-form-label layui-col-md3'>邮箱</label><div class='layui-input-block'><input type='text' name='userEmail' id='userEmail' autocomplete='off'  readonly value='"+user.userEmail+"' class='layui-input'></div><br>";
                userform.append(username);
                userform.append(userPhone);
                userform.append(userEmail);
                var update="<a href='/waimai/qiantaiuser/updatePage.action' class='btn btn-success ' type='button'><i class='fa fa-plus'></i>&nbsp;&nbsp;<span class='bold'>修改个人信息</a>";
                container.append(update);
            }
        })
    }

    function myDiscuss() {
        var container=$("#container");
        container.children("a").remove();
        container.children("form").remove();
        $("#table").bootstrapTable('destroy');
        $('#table').bootstrapTable({
            url:"/waimai/qiantaiDiscuss/list.action",
            pageSize:4,
            classes:"table table-hover",
            cache:false,
            pagination: true,	//显示分页条
            sidePagination: 'server',//服务器端分页
            strictSearch: true,
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,               //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            toolbar: '#toolbar',
            columns:[
                {
                    field: 'discussId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                { title: '消费外卖', field: 'food.foodName'},
                { title: '消费评论', field: 'discussMessage'}
            ]
        });
    }
    function myaddress() {
        var container=$("#container");
        var addbutton="<a href='/waimai/qiantaiAddress/index.action' class='btn btn-success ' type='button'><i class='fa fa-plus'></i>&nbsp;&nbsp;<span class='bold'>新增</span></a>"
        container.append(addbutton);
        $("#table").bootstrapTable('destroy');
        $('#table').bootstrapTable({
            url:"/waimai/qiantaiAddress/listAddresses.action",
            pageSize:4,
            classes:"table table-hover",
            cache:false,
            pagination: true,	//显示分页条
            sidePagination: 'server',//服务器端分页
            strictSearch: true,
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,               //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            toolbar: '#toolbar',
            columns:[
                {
                    field: 'addressId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                { title: '联系人', field: 'addressLianxi'},
                { title: '联系电话', field: 'addressPhone'},
                { title: '联系地址', field: 'addressName'},
                {
                    title:'删除地址',field:'addressId' ,formatter:function (value,row,index) {
                    var edit = '<a href="javascript:void(0)" class="btn btn-danger btn-w-m" onclick="deleteAddress('+value+')">删除地址</a>';
                    return edit;
                }
                }
            ]
        });

    }

    function myConsume() {
        var container=$("#container");
        container.children("a").remove();
        container.children("form").remove();
        $("#table").bootstrapTable('destroy');
        $('#table').bootstrapTable({
            url:"/waimai/qiantaiConsume/listConsumes.action",
            pageSize:4,
            classes:"table table-hover",
            cache:false,
            pagination: true,	//显示分页条
            sidePagination: 'server',//服务器端分页
            strictSearch: true,
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,               //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            toolbar: '#toolbar',
            columns:[
                {
                    field: 'consumeId',
                    title: '序号',
                    width: 40,
                    formatter: function (value, row, index) {
                        var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                        var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                        return pageSize * (pageNumber - 1) + index + 1;
                    }
                },
                { title: '消费金额', field: 'consumeMoney'},
                { title: '消费外卖', field: 'consumeFood'},
                {
                    title:'添加评论',field:'isDiscuss' ,formatter:function (value,row,index) {
                    if(value==1){
                        var edit = '<a href="/waimai/qiantaiDiscuss/index.action?consumeId='+row.consumeId+'" class="btn btn-primary btn-w-m">添加评论</a>';
                        return edit;
                    }else{
                        var edit = '<a href="javascript:void(0)" class="btn btn-default btn-w-m" onclick="javascript:void(0)">已经评论过</a>';
                        return edit;
                    }
                }
                }
            ]
        });

    }
    function deleteAddress (addressId) {
        layer.open({
            title:"提示信息",
            content:"你确定要删除？",
            btn:["确定"],
            yes:function (index,layero) {
                $.get("/waimai/qiantaiAddress/deleteAddress.action?addressId="+addressId,function (r) {
                    var data=JSON.parse(r);
                    if (data.code==1){
                        $("#table").bootstrapTable('refresh');
                    }
                    else{
                        layer.alert(data.data);
                    }
                })
            }
        })
    }
</script>
</body>
</html>
