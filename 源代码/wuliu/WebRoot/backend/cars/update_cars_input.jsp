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
	<title>编辑车辆</title>
<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxcalendar.css">
<script  src="codebase/dhtmlxcommon.js"></script>
<script  src="codebase/dhtmlxcalendar.js"></script>
<script src="js/jquery-1.9.1.min.js" language="javascript" /></script>
	<script src="js/js.js" language="javascript" /></script>
	<script src="ckeditor/ckeditor.js"></script>
<script>
		function initCKEditor(){
			CKEDITOR.replace( 'xiulicontent' ,
			{
				width:900,
				height:500
			});
			CKEDITOR.replace( 'beizhucontent' ,
			{
				width:900,
				height:500
			});
			CKEDITOR.replace( 'jiashiyuancontent' ,
			{
				width:900,
				height:500
			});
			CKEDITOR.replace( 'luxiancontent' ,
			{
				width:900,
				height:500
			});
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
<script>
 function addxiulicontent(){
	var ret = window.showModalDialog("upload.jsp",window,"dialogWidth=400px;dialogHeight=200px");
	if(ret!=null&&ret!=""){
		filename=ret.split("::")[0];
		contentType=ret.split("::")[1];
		var url="";
		if(contentType.indexOf("image")>-1){
			url="<img src=\"upload_image/"+filename+"\"  style='max-width:100%;' />";
		}else{
			url="<a href=\"upload_file/"+filename+"\" />下载文件</a>";
		}
		
		//向CKEDITOR插入html代码 url
		var editor = CKEDITOR.instances.xiulicontent;
		// Check the active editing mode.
		if ( editor.mode == 'wysiwyg' )
		{
			// Insert HTML code.
			// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
			editor.insertHtml( url );
		}
		else{
			alert( 'You must be in WYSIWYG mode!' );
		}
	
	}
}
 function addbeizhucontent(){
	var ret = window.showModalDialog("upload.jsp",window,"dialogWidth=400px;dialogHeight=200px");
	if(ret!=null&&ret!=""){
		filename=ret.split("::")[0];
		contentType=ret.split("::")[1];
		var url="";
		if(contentType.indexOf("image")>-1){
			url="<img src=\"upload_image/"+filename+"\"  style='max-width:100%;' />";
		}else{
			url="<a href=\"upload_file/"+filename+"\" />下载文件</a>";
		}
		
		//向CKEDITOR插入html代码 url
		var editor = CKEDITOR.instances.beizhucontent;
		// Check the active editing mode.
		if ( editor.mode == 'wysiwyg' )
		{
			// Insert HTML code.
			// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
			editor.insertHtml( url );
		}
		else{
			alert( 'You must be in WYSIWYG mode!' );
		}
	
	}
}
 function addjiashiyuancontent(){
	var ret = window.showModalDialog("upload.jsp",window,"dialogWidth=400px;dialogHeight=200px");
	if(ret!=null&&ret!=""){
		filename=ret.split("::")[0];
		contentType=ret.split("::")[1];
		var url="";
		if(contentType.indexOf("image")>-1){
			url="<img src=\"upload_image/"+filename+"\"  style='max-width:100%;' />";
		}else{
			url="<a href=\"upload_file/"+filename+"\" />下载文件</a>";
		}
		
		//向CKEDITOR插入html代码 url
		var editor = CKEDITOR.instances.jiashiyuancontent;
		// Check the active editing mode.
		if ( editor.mode == 'wysiwyg' )
		{
			// Insert HTML code.
			// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
			editor.insertHtml( url );
		}
		else{
			alert( 'You must be in WYSIWYG mode!' );
		}
	
	}
}
 function addluxiancontent(){
	var ret = window.showModalDialog("upload.jsp",window,"dialogWidth=400px;dialogHeight=200px");
	if(ret!=null&&ret!=""){
		filename=ret.split("::")[0];
		contentType=ret.split("::")[1];
		var url="";
		if(contentType.indexOf("image")>-1){
			url="<img src=\"upload_image/"+filename+"\"  style='max-width:100%;' />";
		}else{
			url="<a href=\"upload_file/"+filename+"\" />下载文件</a>";
		}
		
		//向CKEDITOR插入html代码 url
		var editor = CKEDITOR.instances.luxiancontent;
		// Check the active editing mode.
		if ( editor.mode == 'wysiwyg' )
		{
			// Insert HTML code.
			// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
			editor.insertHtml( url );
		}
		else{
			alert( 'You must be in WYSIWYG mode!' );
		}
	
	}
}
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
	<h3>编辑车辆</h3>
	<form action="CarsServlet?method=update&pageNo=${page.pageNo }&pageSize=${page.pageSize }" method="post">
	<input type="hidden" name="id" value="${cars.id }">

	<fieldset>
		<legend>车辆信息</legend>
		<div>
			<label for="name">车辆编号</label>
			<input type="text" name="name" id="name" value="${cars.name }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="leixing">车辆类型</label>
			<input type="text" name="leixing" id="leixing" value="${cars.leixing }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="zaizhong">载重</label>
			<input type="text" name="zaizhong" id="zaizhong" value="${cars.zaizhong }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="xiulicontent">修理记录</label>
			<div style="margin:0px 0px 0px 130px;">
			<textarea rows="10" cols="100" name="xiulicontent" id="xiulicontent" >${cars.xiulicontent }</textarea>
			</div>
			<br />	
		</div>
		<div>
			<label for="attachs">添加修理记录附件</label>
			<input type="button" onclick="addxiulicontent()" id="attachs" value="添加">
			<br />	
		</div>
		<div>
			<label for="jiayou">最后一次加油时间</label>
			<input type="text" name="jiayou" id="jiayou" value="${cars.jiayou }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="beizhucontent">备注</label>
			<div style="margin:0px 0px 0px 130px;">
			<textarea rows="10" cols="100" name="beizhucontent" id="beizhucontent" >${cars.beizhucontent }</textarea>
			</div>
			<br />	
		</div>
		<div>
			<label for="attachs">添加备注附件</label>
			<input type="button" onclick="addbeizhucontent()" id="attachs" value="添加">
			<br />	
		</div>
		<div>
			<label for="jiashiyuancontent">驾驶员信息</label>
			<div style="margin:0px 0px 0px 130px;">
			<textarea rows="10" cols="100" name="jiashiyuancontent" id="jiashiyuancontent" >${cars.jiashiyuancontent }</textarea>
			</div>
			<br />	
		</div>
		<div>
			<label for="attachs">添加驾驶员信息附件</label>
			<input type="button" onclick="addjiashiyuancontent()" id="attachs" value="添加">
			<br />	
		</div>
		<div>
			<label for="carno">车牌号</label>
			<input type="text" name="carno" id="carno" value="${cars.carno }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div>
			<label for="luxiancontent">路线</label>
			<div style="margin:0px 0px 0px 130px;">
			<textarea rows="10" cols="100" name="luxiancontent" id="luxiancontent" >${cars.luxiancontent }</textarea>
			</div>
			<br />	
		</div>
		<div>
			<label for="attachs">添加路线附件</label>
			<input type="button" onclick="addluxiancontent()" id="attachs" value="添加">
			<br />	
		</div>
		<div>
			<label for="youfei">加油费</label>
			<input type="text" name="youfei" id="youfei" value="${cars.youfei }" size="60" maxlength="200" /> 
			<br />	
		</div>
		<div class="enter">
		    <input name="submit" type="submit" class="buttom" value="提交" />
		    <input name="reset" type="reset" class="buttom" value="重置" />
		    <input name="return" type="button" class="buttom" value="返回列表页面" onclick="window.location = 'CarsServlet'"/>
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

