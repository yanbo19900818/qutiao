<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>趣挑网-忘记密码</title>
<link href="/images/main.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="http://www.qutiao.com/images/qutiao.ico"
	type="image/x-icon" />
</head>

<body>
	<jsp:include page="/jsp/common/header.jsp"></jsp:include>
	<div align="center" width="800px" height="400px"
		style="padding-top: 100px; padding-bottom: 100px">
		<form action="/sendPasswordEmail.do" method="post">
			<table>
				<tr>
					<td>请输入注册的email：</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="    确定    " class="btni04" /></td>
				</tr>
			</table>
		</form>
		<span><font color="red">${param.message}</font></span>
	</div>
	<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

</body>
</html>
