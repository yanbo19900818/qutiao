<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>趣挑网-注册成功</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="Shortcut Icon" href="http://www.qutiao.com/images/qutiao.ico" type="image/x-icon"/>
<link href="/images/main.css" rel="stylesheet" type="text/css" /><!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
<jsp:include page="common/header.jsp" />
	<div align="center" width="800px" height="400px" style="padding-top:100px;padding-bottom:100px"><font size="+4">操作成功，请前往注册邮箱激活。</font></div>
	<jsp:include page="common/footer.jsp" />
</body>
</html>
