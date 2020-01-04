<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>CMS 后台管理工作平台</title>
	<link rel="STYLESHEET" type="text/css" href="codebase/dhtmlxcalendar.css">
<script  src="codebase/dhtmlxcommon.js"></script>
<script  src="codebase/dhtmlxcalendar.js"></script>
<script src="js/jquery-1.9.1.min.js" language="javascript" /></script>
	<script src="js/js.js" language="javascript" /></script>
	<script src="ckeditor/ckeditor.js"></script>
<script>

var 
settime,
mCal,
mDCal,
newStyleSheet;
var dateFrom = null;
var dateTo = null;
window.onload = function() {
    settime = new dhtmlxCalendarObject('key');
}

</script>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
A:active,A:visited,A:link {
	COLOR: #0629FD;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #FF6600;
	TEXT-DECORATION: none
}

A.relatelink:active,A.relatelink:visited,A.relatelink:link { 
	color: white;
	TEXT-DECORATION: none
}

A.relatelink:hover {
	COLOR: #FF6600;
	TEXT-DECORATION: none
}

td {
	font-size: 12px;
	color: #003366;
	height:24px
}

.STYLE1 a{
	COLOR: white;
}
.STYLE1 A:active,.STYLE1 A:visited,.STYLE1 A:link {
	COLOR: white;
	TEXT-DECORATION: none
}
.STYLE1 a:hover{
	COLOR: red;
}
-->
</style>

</head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 快件信息列表</span></td>
              </tr>
            </table></td>
            <script>
            	function checkAll(item){
            		var chk = document.getElementsByName("expressCheckbox");
            		for ( var i = 0; i < chk.length; i++) {
						chk[i].checked = item.checked;
					}
            	}
            	function del(){
					document.getElementById("form_express_betch").action="ExpressServlet?method=deleteBetch&pageNo=${page.pageNo }&pageSize=${page.pageSize }";
            		document.getElementById("form_express_betch").submit();
            	}
            </script>
            <td><div align="right"><span class="STYLE1">
<c:if test="${login_user.role.id eq '1'}">
             &nbsp;&nbsp;<img src="images/add.gif" width="10" height="10" /> <a href="ExpressServlet?method=addInput">添加</a>   
             &nbsp; <img src="images/del.gif" width="10" height="10" /> <a href="javascript:del()">批量删除</a>    &nbsp;&nbsp;   &nbsp;
&nbsp; <img src="images/edit.gif" width="10" height="10" /> <a href="ExpressServlet?method=dayin">打印报表</a>    &nbsp;&nbsp;   &nbsp;
&nbsp; <img src="images/edit.gif" width="10" height="10" /> <a href="../upload_file/快件信息表.xls">下载报表</a>    &nbsp;&nbsp;   &nbsp;
</c:if>
             </span><span class="STYLE1"> &nbsp;</span></div></td>
          </tr>
           <tr><td colspan="2"> 
           <div align="left" style="color:white"> 
 				<form action="ExpressServlet" method="post"> 
 					<input type="hidden" name="chaxun" value="${param.chaxun }"  /> 
 					<input type="hidden" name="pageNo" value="${page.pageNo }"  /> 
 					<input type="hidden" name="pageSize" value="${page.pageSize }"/> 
 					<c:if test="${param.chaxun ne 'ri'}">
 					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按姓名查询：<input type="text" name="key" value="${param.key}"><input type="submit"> 
 					</c:if>
 					<c:if test="${param.chaxun eq 'ri'}">
 					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下单时间：<input type="text" name="key" id="key" value="${param.key}"><input type="submit"> 
 					</c:if>
 				</form> 
 		  </div> 
           </td><td></td></tr> 
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="4%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">
          <input type="checkbox"  onclick="checkAll(this)"/>
        </div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">快件编号</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">发件人</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">出发地</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">收件人姓名</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">收件人联系方式</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">目的地</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">快件类别</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">下单时间</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">备注</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">状态</span></div></td>
        <td width="200" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <c:if test="${not empty expressList}">
      <form id="form_express_betch" method="post">
      <c:forEach items="${expressList}" var="express">
      <c:if test="${login_user.role.id ne '3'}">
<tr>
        <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="expressCheckbox" value="${express.id }"/>
        </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.name }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.member.name }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.chufa }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.shoujianname }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.tel }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.place }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.leibie }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.settime }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.descp }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.state }</div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
        <script>
        	function delconfirm(str){
        		var c = confirm("确认要删除此项吗？");
				if(c){
				  window.location.href=str;
				}else{
				
				}

        	}
        </script>
<c:if test="${login_user.role.id ne '3'}">
        <a href="javascript:delconfirm('ExpressServlet?method=delete&id=${express.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }')" title="点击删除项目信息">删除</a> |
        <a href="ExpressServlet?method=modify&id=${express.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }" title="点击编辑项目信息">编辑</a>
         <br><a href="ExpressServlet?method=modifyState&id=${express.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }" title="点击编辑项目信息">编辑状态</a>
</c:if>
<c:if test="${login_user.role.id eq '3'}">
        <a href="javascript:delconfirm('ExpressServlet?method=delete&id=${express.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }')" title="点击删除项目信息">删除</a> |
        <a href="ExpressServlet?method=modify&id=${express.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }" title="点击编辑项目信息">编辑</a>
         <br><a href="ExpressServlet?method=modifyState&id=${express.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }" title="点击编辑项目信息">编辑状态</a>
</c:if>
        </div></td>
      </tr>
</c:if>
      <c:if test="${login_user.role.id eq '3'}">
<c:if test="${express.member.users.id eq login_user.id}">
<tr>
        <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="expressCheckbox" value="${express.id }"/>
        </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.name }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.member.name }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.chufa }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.shoujianname }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.tel }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.place }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.leibie }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.settime }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.descp }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${express.state }</div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
        <script>
        	function delconfirm(str){
        		var c = confirm("确认要删除此项吗？");
				if(c){
				  window.location.href=str;
				}else{
				
				}

        	}
        </script>
<c:if test="${login_user.role.id eq '1'}">
        <a href="javascript:delconfirm('ExpressServlet?method=delete&id=${express.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }')" title="点击删除项目信息">删除</a> |
        <a href="ExpressServlet?method=modify&id=${express.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }" title="点击编辑项目信息">编辑</a>
         <br><a href="ExpressServlet?method=modifyState&id=${express.id }&pageNo=${page.pageNo }&pageSize=${page.pageSize }" title="点击编辑项目信息">编辑状态</a>
</c:if>
        </div></td>
      </tr>
</c:if>
</c:if>
      </c:forEach>
      </form>
      </c:if>
      <c:if test="${ empty expressList }">
      <tr>
          <td height="20" colspan="9" bgcolor="#FFFFFF" class="STYLE19"><div align="center">没有快件信息可以显示</div></td>
      </tr>
      </c:if>
    </table></td>
  </tr>
<form id="form_page" action="ExpressServlet" method="post">
	<input type="hidden" name="pageNo" value="${page.pageNo }" id="pageNo" />
	<input type="hidden" name="pageSize" value="${page.pageSize }" id="pageSize"/>
</form>  
<%--   <%@include file="/backend/commons/page.jsp" %> --%>
<%@include file="/backend/commons/page.jsp" %>
</table>
</body>
</html>

