<%@ page language="java" import="java.util.*,com.qutiao.domain.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>趣挑网-个人中心</title>
<link href="/images/main.css" rel="stylesheet" type="text/css" />
<link href="/images/about.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="http://www.qutiao.com/images/qutiao.ico" type="image/x-icon"/>
</head>

<body>
	<jsp:include page="/jsp/common/header.jsp" />

	<div id="crumb" class="mod mod-crumb" style="display:none">
		<a href="/" class="" data-log="">首页</a> <span class="simsun">&gt;</span>
		<strong>我的账户</strong>
	</div>

	</div>


	<div id="bd" class="clearfix">
		<div id="yao-main">
			<div class="yao-b">
				<div id="ucAccountBind" class="mod mod-title"></div>
				<div id="ucFaq" class="mod mod-title mod-uc-faq">
					<div class="hd"><h2>个人信息</h2></div>
					<div style=" min-height: 300px;">
						<table class="table-two" border="0" cellpadding="0" cellspacing="0">
							<tr>
                                <td class="td-title">用户名：</td>
								<td class="td-text"><b>${self.email}</b></td>
							</tr>
							<tr>
								<td class="td-title">昵称：</td>
								<td class="td-text">${self.name}</td>
							</tr>
							<tr>
								<td class="td-title">注册时间:</td>
								<td class="td-text">${self.createTime}</td>
							</tr>
							<tr>
								<td class="td-title">上次登录时间:</td>
								<td class="td-text">${self.lastLoginTime}</td>
							</tr>
						</table>
					</div>

				</div>
			</div>
		</div>

			<jsp:include page="user_menu.jsp"/>
	</div>
	<jsp:include page="common/footer.jsp" />
</body>
</html>
