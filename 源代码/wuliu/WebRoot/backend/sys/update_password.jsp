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
	<title></title>
<script  src="js/jquery-1.9.1.min.js"></script>
<script  src="js/validate_form.js"></script>
<script>
$.noConflict();
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
input, select,textarea {
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
function check(){
var rs;
		  jQuery.ajax({
          type: "POST",
          url: 'SysServlet',
          cache: false,
          async: false,
          data:{
        	 method:"checkOld",
           	 password:jQuery("#old").val()
          },
          success: function(data){
          		if(data==0){
          			jQuery("#checkold").text("密码不正确,请重新输入").css("color","red");
          			rs=0;
          		}
          		else{
          			jQuery("#checkold").text("恭喜你，密码正确").css("color","green");
          			rs=1;
          		}
          }
       });
if(rs==0){
return false;
}else{
return true;
}
}
function v_password(password){
	  if (isNull(password.value)){
		  jQuery("#check_new").text("*密码不能为空");
		  return false;
		  }
	  jQuery("#check_new").text("");
	  return true;
}
function v_confirm_password(){
	  if ((jQuery("#c_new").val()!=jQuery("#newpass").val())){
		  jQuery("#check_c_new").text("*两次输入的密码不一致");
		  return false;
		  }
	  jQuery("#check_c_new").text("");
	  return true;
}

function validate_form(thisform){
	
with (thisform)

{

  if(
		  (check())
		  &(v_password(newpass))
		  &(v_confirm_password(c_new))
	
		  )

  return true;
  else
  return false;
}
}
</script>
</head>
<body>
<div id="formwrapper" >
	<h3>编辑密码</h3>
	<form action="SysServlet?method=updatePassword"  onsubmit="return validate_form(this)" method="post" >
	<fieldset>
		<legend>修改</legend>
		<div>
			<label for="old">原始密码</label>
			<input type="password" name="old" id="old" value="" size="20" maxlength="20" /> 
			<input type="button" id="che"  onclick="check()" value="查询密码是否正确"/>
			<div  style="display: inline"><font id="checkold" color="red"></font></div>
			<br />	
		</div>
		<div>
			<label for="new">新密码</label>
			<input type="password" name="new" id="newpass" value="" size="20" maxlength="20" onblur="v_password(this)"/> 
			<div  style="display: inline"><font id="check_new" color="red"></font></div>
			<br />	
		</div>
		<div>
			<label for="c_new">新密码确认</label>
			<input type="password" name="c_new" id="c_new" value="" size="20" maxlength="20" onblur="v_confirm_password(this)"/> 
			<div  style="display: inline"><font id="check_c_new" color="red"></font></div>
			<br />	
		</div>
	
		<div class="enter">
		    <input name="submit" type="submit" class="buttom" value="提交" />
		    <input name="reset" type="reset" class="buttom" value="重置" />
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

