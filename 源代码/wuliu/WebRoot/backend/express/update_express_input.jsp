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
	<title>编辑快件</title>
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
settime,
mCal,
mDCal,
newStyleSheet;
var dateFrom = null;
var dateTo = null;
window.onload = function() {
    settime = new dhtmlxCalendarObject('settime');
    initCKEditor();
}
</script>
<script>
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
</head>
<body>
<div id="formwrapper">
	<h3>编辑快件</h3>
	<form action="ExpressServlet?method=update&pageNo=${page.pageNo }&pageSize=${page.pageSize }" method="post">
	<input type="hidden" name="id" value="${express.id }">

	<fieldset>
		<legend>快件信息</legend>
		<div>
			<label for="name">快件编号</label>
			<input type="text" name="name" id="name" value="${express.name }" size="60" maxlength="200" /> 
			<br />	
		</div>
<div>
			<label for="memberIds">发件人</label>
			<select name="member" id="memberIds" >
				<c:forEach items="${memberList }" var="s" >
				<option value="${s.id }"  <c:if test="${s.id eq express.member.id }">selected="selected"</c:if>
				>${s.name}</option>
				</c:forEach>
			</select> 
		</div>
		<div>
			<label for="chufa">出发地</label>
			<input type="text" name="chufa" id="chufa" value="${express.chufa }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="shoujianname">收件人姓名</label>
			<input type="text" name="shoujianname" id="shoujianname" value="${express.shoujianname }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="tel">收件人联系方式</label>
			<input type="text" name="tel" id="tel" value="${express.tel }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="place">目的地</label>
			<input type="text" name="place" id="place" value="${express.place }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="leibie">快件类别</label>
			<input type="text" name="leibie" id="leibie" value="${express.leibie }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="settime">下单时间</label>
			<input type="text" name="settime" id="settime" value="${express.settime }" size="8" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="descp">备注</label>
			<input type="text" name="descp" id="descp" value="${express.descp }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="state">状态</label>
			<input type="text" name="state" id="state" value="${express.state }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div class="enter">
		    <input name="submit" type="submit" class="buttom" value="提交" />
		    <input name="reset" type="reset" class="buttom" value="重置" />
		    <input name="return" type="button" class="buttom" value="返回列表页面" onclick="window.location = 'ExpressServlet'"/>
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

