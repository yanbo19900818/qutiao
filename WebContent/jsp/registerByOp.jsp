<%@ page language="java" import="java.util.*,com.qutiao.dao.common.*"
	pageEncoding="utf-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>趣挑网-开放平台注册</title>
<link href="/images/main.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="http://www.qutiao.com/images/qutiao.ico" type="image/x-icon"/>
</head>

<body>
    
<div  id="bd"  class="mod clearfix">
    <div class="bd_logo">
        <h1><a href="" title="趣挑">  <img src="../images/logo.png" alt="趣挑"> </a></h1>
    </div>
    

<div id="register" class="mod mod-oauth mod-oauth-login clearfix">
    <div class="oauth-login-ctn">
        <div class="hd">
            <h2>开放平台用户用户注册</h2>
        </div>
        <div class="bd clearfix">
            
<div class="register-tips">
        <form action="/registerByOp.do" method="post">
        <div class="row">
            <label class="form-title">常用邮箱：</label>
            <input type="text" id="email" name="email" autocomplete="off" class="input-s color-gray" value="" def-data="如name@qq.com" />
            <span class="ico-success"></span>
            <span class="oauth-tips">如果忘记密码，亦可用此邮箱找</span>
        </div>
        <div class="row">
            <label class="form-title">昵&nbsp;称：</label>
            <input type="text" class="input-s" id="username" name="username" />
            <span class="ico-success"></span>
            <span class="oauth-tips">在趣挑网的昵称</span>
        </div>
        <div class="row login-state login-tip-psw">
            <input type="checkbox" value="1" name="savelogin" id="savelogin"   data-log="page=p-login&type=login_auto_login"  checked />
            <label for="savelogin"  data-log="page=p-login&type=login_auto_login"><a href="" target="_blank">同意趣挑网的注册条款</a></label>
            
            <div id="rememberPsw" class="remember_psw1">
                <div class="login-tiparea-top"></div>
                <a title="关闭" href="javascript:void(0)" class="login-tiparea-close"></a>
            </div>
        </div>
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
        <input type="hidden" id="accessToken" name="accessToken" value="${accessToken}" />
        <input type="hidden" id="openType" name="openType" value="${openType}" />
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
                <a href="" class="login-logo login-qq " title="QQ"></a>
        <a href="" class="login-logo login-tsina " title="新浪微博"></a>
                        <a href="" class="login-logo login-renren " title="人人网"></a>
        <a href="" class="login-logo login-kaixin " title="开心网" ></a>
    </div>
    
</div>
    </div>
</div>
</div>

<jsp:include page="common/footer.jsp"/>
</body>
</html>
