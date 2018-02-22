<%--
  Created by IntelliJ IDEA.
  User: tianrun-bobo
  Date: 2018/2/21
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>河大外卖首页</title>
    <link rel="stylesheet" href="layui/css/layui.css">
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

<div class="layui-container" id="dtApp" style="width: 100%">
    <div class="layui-container" style="width: 100%;height:auto;background-color: #E6E6E6">
        <div class="layui-row">
            <div class="layui-col-md1 layui-col-lg-offset8">
                <a class="layui-btn layui-btn-primary" href="/register/register.action">购物车</a>
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
    <div class="layui-container" style="width: 100%">
        <div id="vsb_content ">
            <h1 >河南大学</h1>
            <p>河南大学坐落在历史文化名城、八朝古都开封。这里曾是河南贡院的所在地，1903、1904年最后两场全国会试在这里举行，上千年的科举制度在这里划上句号。1912年，以林伯襄为代表的一批河南仁人先贤，在欧风美雨和辛亥革命胜利的曙光中创办了河南留学欧美预备学校，成为当时中国的三大留学培训基地之一。后历经中州大学、国立第五中山大学、省立河南大学等阶段，1942年改为国立河南大学，成为拥有文、理、工、农、医、法等6大学院的综合性大学，是当时学术实力雄厚、享誉国内外的国立大学之一。新中国成立后，经院系调整，河南大学农学院、医学院、行政学院分别独立设置为河南农学院、河南医学院、河南行政学院，水利、财经等院系也先后调入武汉大学、中南财经政法大学等高校，校本部更名为河南师范学院。后又经开封师范学院、河南师范大学等阶段，1984年恢复河南大学校名。2008年10月17日，河南省人民政府和教育部签订共建协议，河南大学正式进入省部共建高校行列。2016年9月，学校入选国家“111计划”。2017年9月顺利入选“双一流”建设高校。</p>
            <p>建校百余年来，河南大学严守“明德新民，止于至善”的校训，在一代代学人的精心铸造下，逐渐形成了“团结、勤奋、严谨、朴实”的优良校风和前瞻开放、面向世界，坚持真理、追求进步，百折不挠、自强不息，兼容并包、海纳百川，不事浮华、严谨朴实的河大精神，在推动社会发展、科技进步、经济建设和教育振兴的过程中实现着自身的价值。在以范文澜、冯友兰、董作宾、冯景兰、罗章龙、郭绍虞、罗廷光、萧一山、樊映川、毛礼锐、姜亮夫、嵇文甫、任访秋、党鸿辛等一大批专家学者、院士为代表的名师执教下，河南大学已培养了近60万名各类人才。在河大校友中，有院士、学部委员57人，省部级以上领导干部近150人。不少校友如侯镜如、袁宝华、王国权、赵毅敏、尹达、邓拓、白寿彝、杨廷宝、高济宇、姚雪垠、周而复、吴强、马可、赵九章、梁光烈等都成为蜚声中外的社会名家。</p>
            <p>改革开放以来，河南大学的建设步入了快速发展的时期，通过加强学科建设、培养与引进高层次人才和扩大招生、新校区建设等，在提高办学层次、教育质量、学术水平和扩大发展规模、办学空间、对外开放等方面都实现了跨越式发展，取得了历史性突破，已经成为一所拥有文、史、哲、经、管、法、理、工、医、农、教育、艺术等12个学科门类的综合性大学，先后与40多个国家和地区的120余所高校建立了友好合作关系，是世界大学联合会和亚太大学联合会成员。河南大学现设有文学院、历史文化学院、教育科学学院、哲学与公共管理学院、法学院、新闻与传播学院、外语学院、经济学院、商学院、数学与统计学院、物理与电子学院、计算机与信息工程学院、环境与规划学院、生命科学学院、化学化工学院、土木建筑学院、艺术学院、体育学院、医学院、药学院、护理学院、马克思主义学院、淮河临床学院、第一临床学院、国际教育学院、软件学院、民生学院、国际汉学院、欧亚国际学院、迈阿密学院、远程与继续教育学院、大学外语教学部、公共体育教研部等学院（部），94个本科专业，42个硕士学位授权一级学科，20种硕士专业学位授权类别，12个博士学位授权一级学科，15个博士后科研流动站。现有教职工4300多人，其中专兼职工作的院士14人，正副高级职称1700人。全日制在校生5万人，其中研究生近1万人，留学生500人。学校拥有棉花生物学国家重点实验室、纳米杂化材料应用技术国家地方联合工程研究中心、高效显示与照明技术国家地方联合工程研究中心和抗体药物开发技术国家地方联合工程实验室，特种功能材料、植物逆境生物学、黄河中下游数字地理技术实验室等教育部重点实验室3个，教育部工程技术研究中心1个，省级协同创新中心4个。建有国家教育部黄河文明与可持续发展研究中心、国家教育部体育艺术师资培训培养基地、国家体育总局社会科学研究基地、国家大学生文化素质教育基地、国家中华优秀文化艺术传承基地等5个国家级教育、科研基地。办有出版社和多种学术刊物，图书馆有纸质图书520万册、电子图书700多万件。校区总面积220万平方米，建筑面积147万平方米。其中明伦校区近代建筑群是国家重点文物保护单位。</p>
            <p>作为一所具有厚重历史的高校，河南大学的建设一直受到各级政府和领导的重视。近年来，习近平、李克强、江泽民、贾庆林、李岚清、吴官正、李长春等领导同志先后莅校视察，对河南大学的发展寄予厚望。2004年7月江泽民同志视察时亲笔书写了“与时俱进，开拓创新，把河南大学办成全国一流高校”的题词。2012年9月，时任中共中央政治局常委、国务院总理温家宝同志为河南大学建校100周年题词“办好河南大学振兴中原教育”。河南省委、省政府历来也十分重视河南大学的建设，一直把河南大学作为河南省重点建设高校，并比照“211工程”项目学校在建设资金方面给予了重点扶持。2011年，国务院《关于支持河南省加快建设中原经济区的指导意见》中明确提出“支持河南大学创建国内一流大学”，河南省人民政府也专门颁布了《百年名校河南大学振兴计划(2011—2020年)》，进一步确立河南大学重点建设、优先发展的战略地位，河南大学的发展正面临着重大的机遇。中共河南大学第十次代表大会提出学校必须坚持走国家一流、区域引领、中原风格的发展道路，并确定了学校今后一个时期的奋斗目标：到2020年左右，人才培养、科学研究、社会服务、文化传承与创新整体水平全面提升，主要办学指标和整体实力初步达到国家一流大学水平，在全国高等教育体系中的地位得到较大幅度提升；到本世纪中叶，主要办学指标和整体实力达到国家一流大学水平，学校稳居国家一流大学行列。</p>
            <p>百年的风雨和磨砺，百年的奋斗与辉煌，河南大学正乘风扬帆，充满信心，朝着建设高水平大学的方向迈进。</p>
            <p><br></p></div>
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
<script src="libs/jquery.min.js"></script>
<script src="libs/vue.min.js"></script>
<script src="layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        version: '1515376178738' //为了更新 js 缓存，可忽略
    });
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var laydate = layui.laydate //日期
            ,laypage = layui.laypage //分页
            ,layer = layui.layer //弹层
            ,table = layui.table //表格
            ,carousel = layui.carousel //轮播
            ,upload = layui.upload //上传
            ,element = layui.element; //元素操作
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,height: "400px"
            ,arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });
    });
    $(function () {

    })
</script>
</body>
</html>