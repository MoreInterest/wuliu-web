<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.bs.model.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	font-size: 12px;z
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
            <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1">在线用户</span></td>
              </tr>
              <tr>
                <td width="6%" height="19" valign="bottom"></td>
                <td width="94%" valign="bottom"><span class="STYLE1">
					当前在线人数：${fn:length(users) }
                </td>
              </tr>
            </table>
            </td>
            <script type="text/javascript">
            	function betch(flag){
            		if(flag){
            			document.getElementById("checkboxForm").action = "UserServlet?method=deleteBetch&key=${param.key}&pageSize=${page.pageSize}&pageNo=${page.pageNo}";
            		}else{
            			document.getElementById("checkboxForm").action = "UserServlet?method=publishBetch&key=${param.key}&pageSize=${page.pageSize}&pageNo=${page.pageNo}";
            		}
            			document.getElementById("checkboxForm").submit();
            	}
            </script>
            <td><div align="right"><span class="STYLE1">
             </span><span class="STYLE1"> &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <script type="text/javascript">
    	function checkAll(){
    		var userCheckboxs = document.getElementsByName("userCheckbox");
    		for ( var i = 0; i < userCheckboxs.length; i++) {
				userCheckboxs[i].checked = document.getElementById("checkAll").checked;
			}
    	}
    </script>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="4%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">
          <input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll()" />
        </div></td>
        <td width="50" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户名</span></div></td>
        <td width="80" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户角色</span></div></td>
      </tr>
    <form id="checkboxForm" action="" method="post">
	 <c:forEach items="${ users }" var="s">
      <tr>
        <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="userCheckbox" id="userCheckbox${s.id} " value="${s.id }" />
        </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${s.username}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${s.role.name}
        </div></td>
        
      </tr>
	</c:forEach>
	</form>
	 <c:if test="${empty users }">
      <tr>
          <td height="20" colspan="9" bgcolor="#FFFFFF" class="STYLE19"><div align="center">不存在相关用户</div></td>
      </tr>
      </c:if>
    </table></td>
  </tr>

  <%@include file="/backend/commons/page.jsp" %>

</table>
</body>
</html>

