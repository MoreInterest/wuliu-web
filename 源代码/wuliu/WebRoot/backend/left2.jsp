<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;overflow:hidden">
	<div class="lefttop"><span></span>管理菜单</div>
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>员工管理
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="StaffServlet" target="rightFrame">查看个人信息</a><i></i></li>
        </ul>    
    </dd>
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>用户管理
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="MemberServlet" target="rightFrame">查看用户信息</a><i></i></li>
        </ul>    
    </dd>
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>快件管理
    </div>
    	<ul class="menuson">
        <li ><cite></cite><a href="ExpressServlet?method=addInput" target="rightFrame">添加快件</a><i></i></li>
        <li><cite></cite><a href="ExpressServlet" target="rightFrame">查看快件信息</a><i></i></li>
        </ul>    
    </dd>
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>业务管理
    </div>
    	<ul class="menuson">
        <li ><cite></cite><a href="ExpressServlet?chaxun=ri" target="rightFrame">查看每日快件下单数量</a><i></i></li>
        <li><cite></cite><a href="ExpressServlet?chaxun=wancheng" target="rightFrame">查看已完成的订单</a><i></i></li>
        </ul>    
    </dd>
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>车辆管理
    </div>
    	<ul class="menuson">
        <li ><cite></cite><a href="CarsServlet?method=addInput" target="rightFrame">添加车辆</a><i></i></li>
        <li><cite></cite><a href="CarsServlet" target="rightFrame">查看车辆信息</a><i></i></li>
        </ul>    
    </dd>
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>资讯信息管理
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="RoleServlet" target="rightFrame">查看资讯信息信息</a><i></i></li>
        </ul>    
    </dd>
    
    
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>系统管理
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="sys/update_password.jsp" target="rightFrame">修改个人密码</a><i></i></li>
        </ul>    
    </dd>
    
    </dl>
    
</body>
</html>

