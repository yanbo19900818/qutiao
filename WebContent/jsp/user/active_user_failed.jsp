<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>趣挑网-登录</title>
<link href="/images/main.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="http://www.qutiao.com/images/qutiao.ico"
	type="image/x-icon" />
</head>

<body>
	<jsp:include page="/jsp/common/header.jsp"></jsp:include>
	<div align="center">
		<font color="red">${param.message}</font>
	</div>
	<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

</body>
</html>
