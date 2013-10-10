<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>审核资讯详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/assets/js/jquery.js"></script>
	<script type="text/javascript">
	function goSaveInfo(){
		var keyword=$("#keyword").val();
		var saveInfoHref="<%=path %>/bg/info/verifyInfoById.do?id=${product.id}&page=${page}&keyword="+keyword;
		window.location.href=saveInfoHref;
	}
	</script>
  </head>
  
  <body>
    <table border="1">
           <tr>
           	<td>id</td>
           	<td>${product.id}</td>
           </tr>
           <tr>
           	<td>标题</td>
           	<td>${product.title}</td>
           </tr>
           <tr>
           	<td>图片</td>
           	<td><img src="${product.image}"/></td>
           </tr>
           <tr>
           	<td>内容</td>
			<td>${product.content}</td>
           </tr>
           <tr>
           	<td>关键字(用","隔开)</td>
           	<td><input type="text" name="keyword" id="keyword" size="100" /></td>
           </tr>
           <tr>
           	<td>
           		<a id="saveInfo" href="javascript:goSaveInfo();" >保存</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		<a href="<%=path %>/bg/info/deleteInfoById.do?id=${product.id}&page=${page}">删除</a>
           	</td>
           </tr>
     </table>
  </body>
</html>
