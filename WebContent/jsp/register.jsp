<%@ page language="java" import="java.util.*,com.qutiao.dao.common.*"
	pageEncoding="utf-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>趣挑网-注册</title>
<link href="/images/main.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="http://www.qutiao.com/images/qutiao.ico" type="image/x-icon"/>
<script type="text/javascript" src="../../js/register.js"></script>
		<script src="../js/jquery.js"></script>
</head>

<body>
<div  id="bd"  class="mod clearfix">
    <div class="bd_logo">	
        <h1><a href="/index.html" title="趣挑">  <img src="../images/logo.png" alt="趣挑"/> </a></h1>
    </div>
    

<div id="register" class="mod mod-oauth mod-oauth-login clearfix">
    <div class="oauth-login-ctn">
        <div class="hd">
            <h2>新用户注册</h2>
        </div>
        <div class="bd clearfix">
            
<div class="register-tips">
        <form action="/register.do" method="post" onsubmit="return checkAll();">
        <div class="row">
            <label class="form-title">常用邮箱：</label>
            <input type="text" id="email" name="email" autocomplete="off" class="input-s color-gray" value="" def-data="如name@qq.com" onblur="checkEmail()"/>
            <span class="ico-success" style="display:block;margin-top:17px;margin-left:98px" id="emailMessage"></span>
            <span class="oauth-tips">如果忘记密码，亦可用此邮箱找</span>
        </div>
        <div class="row">
            <label class="form-title">密&nbsp;码：</label>
            <input type="password" class="input-s" id="password" name="password" onblur="checkPwd()"/>
            <span class="ico-success" id="pwdMessage" style="display:block;margin-top:17px;margin-left:98px"></span>
            <span class="oauth-tips">由6-18位字母或数字组成，区分大小写</span>
        </div>
        <div class="row">
            <label class="form-title">确认密码：</label>
            <input type="password" class="input-s" id="password2" name="password2" onblur="checkPwd2()" />
            <span class="ico-success" id="pwd2Message" style="display:block;margin-top:17px;margin-left:98px"></span>
            <span class="oauth-tips">请重复输入密码</span>
        </div>
        <div class="row">
            <label class="form-title">昵&nbsp;称：</label>
            <input type="text" class="input-s" id="username" name="username" onblur="checkUsername()"/>
            <span class="ico-success" id="usernameMessage" style="display:block;margin-top:17px;margin-left:98px"></span>
            <span class="oauth-tips">在趣挑网的昵称,2-8个字符</span>
        </div>
        <div class="row login-state login-tip-psw">
            <input type="checkbox" value="1" name="savelogin" id="savelogin"   data-log="page=p-login&type=login_auto_login"  checked />
            <label for="savelogin"  data-log="page=p-login&type=login_auto_login"><a href="" target="_blank">同意趣挑网的注册条款</a></label>
            
            <div id="rememberPsw" class="remember_psw1">
                <div class="login-tiparea-top"></div>
                <a title="关闭" href="javascript:void(0)" class="login-tiparea-close"></a>
            </div>
        </div>
                <div><font color="red">${param.message}</font></div>
        <div class="row row-login-btn">
            <span class="btni04">
            <span>
                <input type="submit" 
                value="&nbsp;&nbsp;注 册&nbsp;&nbsp;" 
                hidefocus="true" 				
                data-log="page=p-login&type=login" />
            </span> 
            </span>  
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
                <a href="/loginByOp.html?go=qq" class="login-logo login-qq " title="QQ"></a>
        <a href="/loginByOp.html?go=sina" class="login-logo login-tsina " title="新浪微博"></a>
    </div>
    
</div>
    </div>
</div>
</div>

<jsp:include page="common/footer.jsp"/>
</body>
</html>
