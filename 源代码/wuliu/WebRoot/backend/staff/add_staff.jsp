<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>添加员工</title>
<script  src="js/jquery-1.9.1.min.js"></script>
<script  src="js/validate_form.js"></script>
<script type="text/javascript">
function isUsername(str){
	  var boo=1;
	  jQuery.ajax({
          type: "POST",
          url: 'UserServlet',
          cache: false,
          async: false,
          data:{
        	 method:"validate",
           	 username:str
          },
          success: function(data){
        	  boo=data;
          }
       });
	  return boo == 0 ? true : false;
}
</script>

<script type="text/javascript">

function v_username(username){
	  if (isNull(username.value)){
		  jQuery("#validate_username").text("*用户名不能为空").css("color","red");
		  return false;
		  }
	  if (!isUsername(username.value)){
		  jQuery("#validate_username").text("*此用户名已被注册").css("color","red");
		  return false;
		  }
	  else{
		  jQuery("#validate_username").text(" √此用户名可用").css("color","green");
	  }
	  jQuery("#validate_email").text("");
	  return true;
}
function v_password(password){
	  if (isNull(password.value)){
		  jQuery("#validate_password").text("*密码不能为空");
		  return false;
		  }
	  jQuery("#validate_password").text("");
	  return true;
}
function v_confirm_password(confirm_password){
	  if ((jQuery("#confirm_password").val()!=jQuery("#password").val())){
		  jQuery("#validate_confirm_password").text("*两次输入的密码不一致");
		  return false;
		  }
	  jQuery("#validate_confirm_password").text("");
	  return true;
}
function v_name(name){
	  if (isNull(name.value)){
		  jQuery("#validate_name").text("*请输入姓名");
		  return false;
		  }
	  jQuery("#validate_name").text("");
	  return true;
}
function v_no(no){
	  if (isNull(no.value)){
		  jQuery("#validate_no").text("*学号不能为空");
		  return false;
		  }
	  jQuery("#validate_no").text("");
	  return true;
}

</script>

<script type="text/javascript">
function validate_form(thisform){
	
with (thisform)

{

  if(
		  (v_username(username))
		  &(v_password(password[0]))
		  &(v_confirm_password(confirm_password))
	
		  )

  return true;
  else
  return false;
}
}
</script>
<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxcalendar.css">
<script  src="codebase/dhtmlxcommon.js"></script>
<script  src="codebase/dhtmlxcalendar.js"></script>
<script src="js/jquery-1.9.1.min.js" language="javascript" /></script>
	<script src="js/js.js" language="javascript" /></script>
	<script src="ckeditor/ckeditor.js"></script>
<script>
		function initCKEditor(){
		}

var 
mCal,
mDCal,
newStyleSheet;
var dateFrom = null;
var dateTo = null;
window.onload = function() {
    initCKEditor();
}

</script>
<style type="text/css">
<!--
body {
	font-family: Arial, Helvetica, sans-serif;
	font-size:12px;
	color:#666666;
	background:#fff;
	text-align:center;

}

* {
	margin:0;
	padding:0;
}

a {
	color:#1E7ACE;
	text-decoration:none;	
}

a:hover {
	color:#000;
	text-decoration:underline;
}
h3 {
	font-size:14px;
	font-weight:bold;
}

pre,p {
	color:#1E7ACE;
	margin:4px;
}
input, select,textarea{
	padding:1px;
	margin:2px;
	font-size:12px;
}
.buttom{
	padding:1px 10px;
	font-size:12px;
	border:1px #1E7ACE solid;
	background:#D0F0FF;
}
#formwrapper {
	width:95%;
	margin:15px auto;
	padding:20px;
	text-align:left;
}

fieldset {
	padding:10px;
	margin-top:5px;
	border:1px solid #1E7ACE;
	background:#fff;
}

fieldset legend {
	color:#1E7ACE;
	font-weight:bold;
	background:#fff;
}

fieldset label {
	float:left;
	width:120px;
	text-align:right;
	padding:4px;
	margin:1px;
}

fieldset div {
	clear:left;
	margin-bottom:2px;
}

.enter{ text-align:center;}
.clear {
	clear:both;
}

-->
</style>
<script type="text/javascript">
function image(str){
	var ret = window.showModalDialog("upload.jsp",window,"dialogWidth=400px;dialogHeight=200px");
	if(ret!=null&&ret!=""){
		filename=ret.split("::")[0];
		contentType=ret.split("::")[1];
		var url="";
		if(contentType.indexOf("image")>-1){
			if($("#"+str+"_image").size()>0){
			$("#"+str+"_image").attr("src","upload_image/"+filename+"");
			}else{
			$("#"+str).after("<img id='"+str+"_image' max-width='100%' src='upload_image/"+filename+"'>");
			}
		}else{
			if($("#"+str+"_file").size()>0){
			$("#"+str+"_file").attr("href","upload_file/"+filename+"");
			}else{
			$("#"+str).after("<a id='"+str+"_file'  href='upload_file/"+filename+"'>下载文件</a>");
			}
		}
		document.getElementById(str).value = filename;
		
	}
}
function InsertHTML(value){
	var editor = CKEDITOR.instances.content;
	// Check the active editing mode.
	if ( editor.mode == 'wysiwyg' )
	{
		// Insert HTML code.
		// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
		editor.insertHtml( value );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}
</script>
</head>
<body>
<div id="formwrapper">
	<h3>添加员工</h3>
	<form action="StaffServlet?method=add1" onsubmit="return validate_form(this)" method="post">
<input type="hidden" name="roles" value="2">	<fieldset>
		<legend>员工信息</legend>
<div>
			<label for="username">用户名</label>
			<input type="text" name="username" id="username" value="" size="20" maxlength="20" onblur="v_username(this)"/> 
			<div  style="display: inline"><font id="validate_username" color="red"></font></div>
			<br />	
		</div>
		<div>
			<label for="password">密码</label>
			<input type="password" name="password" id="password" value="" size="20" maxlength="20" onblur="v_password(this)"/> 
			<div  style="display: inline"><font id="validate_password" color="red"></font></div>
			<br />	
		</div>
		<div>
			<label for="confirm_password">确认密码</label>
			<input type="password" name="password" id="confirm_password" value="" size="20" maxlength="20" onblur="v_confirm_password(this)" /> 
			<div  style="display: inline"><font id="validate_confirm_password" color="red"></font></div>
			<br />	
		</div>
		<br>
		<div>
			<label for="name">姓名</label>
			<input type="text" name="name" id="name" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="sex">性别</label>
			<input type="text" name="sex" id="sex" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="age">年龄</label>
			<input type="text" name="age" id="age" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="tel">联系电话</label>
			<input type="text" name="tel" id="tel" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="address">住址</label>
			<input type="text" name="address" id="address" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="email">邮箱</label>
			<input type="text" name="email" id="email" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="zhandian">所属站点</label>
			<input type="text" name="zhandian" id="zhandian" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="gongzi">工资</label>
			<input type="text" name="gongzi" id="gongzi" value="" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div class="enter">
		    <input name="submit" type="submit" class="buttom" value="提交" />
		    <input name="reset" type="reset" class="buttom" value="重置" />
<c:if test="${login_user ne null}">
		    <input name="return" type="button" class="buttom" value="返回列表页面" onclick="window.location = 'StaffServlet'"/>
</c:if>
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

