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

<title>趣挑网-重置密码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="Shortcut Icon" href="http://www.qutiao.com/images/qutiao.ico"
	type="image/x-icon" />
	<link href="/images/main.css" rel="stylesheet" type="text/css" />
<link href="/images/about.css" rel="stylesheet" type="text/css" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<jsp:include page="/jsp/common/header.jsp" />
	<form actiont="/resetPassword.do" method="post">
	<div id="bd" class="clearfix">
		<div id="yao-main">
			<div class="yao-b">
				<div id="ucAccountBind" class="mod mod-title"></div>
				<div id="ucFaq" class="mod mod-title mod-uc-faq">
					<div class="hd"><h2>重置密码</h2></div>
					<div class="bd clearfix">
					
						<div class="info-base">
                        <table class="table-one" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="td-title">原密码：</td>
								<td class="td-text"><input type="password" name="oldpassword" class="input-s" /></td>
							</tr>
							<tr>
								<td class="td-title">新密码：</td>
								<td class="td-text"><input  type="password" name="newpassword" class="input-s"/></td>
							</tr>
                            <tr>
								<td class="td-title">确认新密码：</td>
								<td class="td-text"><input   type="password" name="newpassword" class="input-s" /></td>
							</tr>
							<tr>
                            <td class="td-title"></td>
                            <td class="td-text"><span class="btni04"> <span> <input type="submit" value="&nbsp;&nbsp;确认修改&nbsp;&nbsp;" > </span> </span></td>
                            </tr>
						</table>
                        </div>
					</div>

				</div>
			</div>
		</div>
		</form>
			<jsp:include page="user_menu.jsp"/>
	</div>
  
	<jsp:include page="common/footer.jsp" />
</body>
</html>
