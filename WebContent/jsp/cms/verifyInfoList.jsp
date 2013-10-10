<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/qutiao" prefix="m"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>审核资讯列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path %>/images/main.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
    <table border="1">
           <tr>
           	<td>id</td>
           	<td>标题</td>
           	<td>描述</td>
           	<td>操作</td>
           </tr>
   		   <c:forEach var="product" items="${requestScope.verifyInfoPage.result}">
    		<tr>
           	<td>${product.id}</td>
           	<td>${product.title}</td>
           	<td>${product.description}</td>
           	<td><a href="<%=path %>/bg/info/verifyInfo${requestScope.verifyInfoPage.now}_${product.id}.html">操作</a>&nbsp;&nbsp;<a href="<%=path %>/bg/info/deleteInfoById.do?id=${product.id}&page=${requestScope.verifyInfoPage.now}">删除</a></td>
           	</tr>
           </c:forEach>
           <div class="ft">
           <div class="huihui-paging">
				<m:pageTag before="${requestScope.verifyInfoPage.before}" now="${requestScope.verifyInfoPage.now}" next="${requestScope.verifyInfoPage.next}" totalPage="${requestScope.verifyInfoPage.totalPage}"
				 url="/bg/info/verifyInfoList{num}.html" placeholder="{num}"></m:pageTag>
		   </div>
		   </div>
     </table>
  </body>
</html>
