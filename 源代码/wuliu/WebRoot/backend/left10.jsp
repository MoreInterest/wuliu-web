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
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body>
<div  style="height:100%;">
  <ul id="navigation">

	<li> <a class="head">员工管理</a>
      <ul>
        <li><a href="StaffServlet?method=addInput" target="rightFrame">添加员工</a></li>
        <li><a href="StaffServlet" target="rightFrame">查看员工信息</a></li>
      </ul>
    </li>

	<li> <a class="head">用户管理</a>
      <ul>
        <li><a href="MemberServlet?method=addInput" target="rightFrame">添加用户</a></li>
        <li><a href="MemberServlet" target="rightFrame">查看用户信息</a></li>
      </ul>
    </li>

	<li> <a class="head">快件管理</a>
      <ul>
        <li><a href="ExpressServlet?method=addInput" target="rightFrame">添加快件</a></li>
        <li><a href="ExpressServlet" target="rightFrame">查看快件信息</a></li>
      </ul>
    </li>
	<li> <a class="head">业务管理</a>
      <ul>
        <li><a href="ExpressServlet?chaxun=ri" target="rightFrame">查看每日快件下单数量</a></li>
        <li><a href="ExpressServlet?chaxun=wancheng" target="rightFrame">查看已完成的订单</a></li>
      </ul>
    </li>

	<li> <a class="head">车辆管理</a>
      <ul>
        <li><a href="CarsServlet?method=addInput" target="rightFrame">添加车辆</a></li>
        <li><a href="CarsServlet" target="rightFrame">查看车辆信息</a></li>
      </ul>
    </li>

	<li> <a class="head">财务收支管理</a>
      <ul>
        <li><a href="FinanceServlet?method=addInput" target="rightFrame">添加财务收支</a></li>
        <li><a href="FinanceServlet" target="rightFrame">查看财务收支信息</a></li>
      </ul>
    </li>
    <li> <a class="head">资讯信息管理</a>
      <ul>
        <li><a href="MessageServlet?method=addInput" target="rightFrame">添加资讯信息</a></li>
        <li><a href="MessageServlet" target="rightFrame">查看资讯信息信息</a></li>
      </ul>
    </li>
    <li> <a class="head">角色管理</a>
      <ul>
        <li><a href="role/add_role.jsp" target="rightFrame">添加角色</a></li>
        <li><a href="RoleServlet" target="rightFrame">查看角色信息</a></li>
      </ul>
    </li>
    <li> <a class="head">系统管理</a>
      <ul>
        <li><a href="sys/update_password.jsp" target="rightFrame">修改个人密码</a></li><!--
        <li><a href="/log.log" target="rightFrame">查看登录日志</a></li>
        --><li><a href="UserServlet?method=users" target="rightFrame">查看在线用户</a></li>
      </ul>
    </li>
  </ul>
</div>
</body>
</html>

