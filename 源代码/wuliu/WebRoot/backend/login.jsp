<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
request.setAttribute("isSupportCheckCode",config.getServletContext().getInitParameter("isSupportCheckCode").equals("1")?true:false);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script  src="js/jquery-1.9.1.min.js"></script>
	<script  src="js/js.js"></script>
	<script type="text/javascript">
			$(document).ready(function(){
			    $(".sysName").load("sysName.txt",function(data,x){
			    	document.title=data;
			    });	
			});
	</script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>
<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎使用<b class="sysName"></b></span>    
    <ul>
<!--     <li><a href="#">回首页</a></li> -->
<!--     <li><a href="#">帮助</a></li> -->
<!--     <li><a href="#">关于</a></li> -->
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"><b class="sysName"></b></span> 
       
    <div class="loginbox">
    
    <ul>
    <form id="login" action="LoginServlet" method="post">
    <li><input name="username" type="text" class="loginuser" value="用户名" onclick="JavaScript:this.value=''"/></li>
    <li><input name="password" type="password" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
    <li>
    <c:if test="${isSupportCheckCode}">
        <input name="code" type="text" value="验证码" id="chknumber" maxlength="4" class="chknumber_input inputtext" onclick="JavaScript:this.value=''"/>
  <script>
  	function reloadImg(){
  		document.getElementById("safecode").src="CheckCode.jpg?x="+Math.random();
  	}
  </script>
        <img src="CheckCode.jpg" id="safecode" onclick="reloadImg();"/>
      </c:if>
    <input  type="submit" class="loginbtns" value="登录"   />&nbsp;&nbsp;
    <input  type="button" class="loginbtns" value="注册"   onclick="javascript:window.location.href='regist.jsp';"/>
    <br>
    <label style="padding:10px 0 ">${login_error}</label></li>
    
    </form>
    </ul>
    
    
    </div>
    
    </div>
    
    
    
    <div class="loginbm">毕业设计 &copy; <b class="sysName"></b>的设计与实现 All Rights Reserved</div>
	
    
</body>
</html>

