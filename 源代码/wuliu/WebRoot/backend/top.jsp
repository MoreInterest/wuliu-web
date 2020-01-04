<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script  src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
			$(document).ready(function(){
			    $(".sysName").load("sysName.txt",function(data,x){
			    	document.title=data;
			    });	
			});
	</script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.jsp" target="_parent"><img src="images/logo.png" title="系统首页" /><b class="sysName"></b></a>
    </div>
        <ul class="nav">
    <li><a href="index.jsp" target="rightFrame" class="selected"><img src="images/icon1.png" width="40px" title="工作台" /><h2>首页</h2></a></li>
    <li><a href="javascript:history.go(-1);" target="rightFrame"><img src="images/icon3.png" width="40px" title="后退" /><h2>后退</h2></a></li>
    <li><a href="javascript:history.go(1);"  target="rightFrame"><img src="images/icon2.png"width="40px"  title="前进" /><h2>前进</h2></a></li>
    <li><a href="javascript:window.parent.location.reload();"  target="rightFrame"><img src="images/icon4.png"width="40px"  title="刷新" /><h2>刷新</h2></a></li>
    <li><a href="sys/update_password.jsp"  target="rightFrame"><img src="images/icon5.png"width="40px"  title="修改个人密码" /><h2>修改个人密码</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="javascript:void(0)">帮助</a></li>
    <li><a href="javascript:void(0)">关于</a></li>
    <li><a href="LoginServlet?method=loginout" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span>${login_user.username}</span>
    <i>消息</i>
    <b>0</b>
    </div>    
    
    </div>

</body>
</html>

