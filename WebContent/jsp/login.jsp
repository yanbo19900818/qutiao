<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/qutiao" prefix="m"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>趣挑网-登录</title>
<link href="/images/main.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="http://www.qutiao.com/images/qutiao.ico" type="image/x-icon"/>
</head>

<body>
	<div  id="bd"  class="mod clearfix">
    <div class="bd_logo">	
        <h1><a href="/index.html" title="趣挑">  <img src="../images/logo.png" alt="趣挑"/> </a></h1>
    </div>
	<div id="login" class="mod mod-oauth mod-oauth-login clearfix"  style="height:300px;">
		<div class="oauth-login-ctn">
			<div class="hd">
				<h2>用户登录</h2>
			</div>
			<div class="bd clearfix">

				<div class="login-container">
					<div class="row">${param.message}</div>
					<form action="/login.do" method="post">
						<div class="row login-tip" style="z-index: 100;">
							<label class="form-title">邮&nbsp;箱：</label> <input type="text"
								id="username" name="email" autocomplete="off"
								class="input-s color-gray" />
						</div>
						<div class="row">
							<label class="form-title">密&nbsp;码：</label> <input
								type="password" class="input-s" id="password" name="password" />
						</div>
						<div class="row login-state login-tip-psw">
							<input type="checkbox" value="1" name="savelogin" id="savelogin"
								data-log="page=p-login&type=login_auto_login" checked /> <label
								for="savelogin" data-log="page=p-login&type=login_auto_login">记住我的登录状态</label>
							<span class="sp">|</span> <a href="/jsp/user/forgetpassword/forget_password.jsp" target="_blank"
								data-log="page=p-login&type=forget_password">忘记密码？</a>
							<div id="rememberPsw" class="remember_psw1">
								<div class="login-tiparea-top"></div>
								<a title="关闭" href="javascript:void(0)"
									class="login-tiparea-close"></a>
								<p>为了您的信息安全，请不要在网吧或公用电脑上使用此功能！</p>
							</div>
						</div>
						<div><font color="red">${param.message}</font></div>
						<div class="row row-login-btn">
							<span class="btni04"> <span> <input type="submit"
									value="&nbsp;&nbsp;登 录&nbsp;&nbsp;" hidefocus="true"
									data-log="page=p-login&type=login" /> </span> </span>
						</div>
						
					</form>
				</div>
			</div>
		</div>
		<div class="oauth-login-cooperation">

			<div class="oauth-container">
				<div class="hd">
					<h2>使用合作伙伴帐号登录：</h2>
				</div>
				<div class="bd">
					<a href="/loginByOp.html?go=qq" class="login-logo login-qq " title="QQ"></a> <a href="/loginByOp.html?go=sina"
						class="login-logo login-tsina " title="新浪微博"></a>
				</div>
				<div class="ft">
					没有帐号？<a href="<%=request.getContextPath()%>/jsp/register.jsp">10秒钟快速注册&gt;&gt;</a>
				</div>
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>
