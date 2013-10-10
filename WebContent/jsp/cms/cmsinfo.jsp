<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
 
    <title>广告管理后台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="${basePath}/css/base.css" rel="stylesheet" type="text/css">
<link href="${basePath}/css/login.css" rel="stylesheet" type="text/css">
<script src="${basePath}/js/jquery/jquery.js" language="javascript" type="text/javascript"></script>
 <script src="${basePath}/js/jquery/jquery-1.7.2.min.js" language="javascript" type="text/javascript"></script>
 <script language="JavaScript">  
if (window != top)  
top.location.href = "login.jsp";  
</script>
  </head>
  <script type="text/javascript">
  $(function() {
	  var error=$("#error").val();
	  if(error==1){
		  alert("用户不存在！");
	  }else if(error==2){
		  alert("您输入的密码错误！");
	  }
  });
		function login(){
			if($("#userName").val()=="") {
				alert("用户名不能为空！");
				return false;
			}else if($("#password").val()==""){
				alert("密码不能为空！");
				return false;
			}else{
				document.adForm.submit();
			}
		}	
	
   </script>
<body >
<div id="login-box">
<input type="hidden" id="error" name="error" value="<%=request.getParameter("error") %>" />
   <div class="login-top"><a href="#" target="_blank" title="返回网站主页"></a></div>
      <div class="login-main">
    <form name="adForm" id="adForm"  method="post" action="${basePath}/cms/user/login.do" >
      <input type="hidden" name="gotopage" value="/demo/admin/index.php">
      <input type="hidden" name="dopost" value="login">
      <input name="adminstyle" type="hidden" value="newdedecms">
      <dl>
	   <dt>用户名：</dt>
	   <dd><input type="text" name="userName" id="userName"></dd>
	   <dt>密&nbsp;&nbsp;码：</dt>
	   <dd><input type="password" class="alltxt" id="password" name="password"></dd>
	   	 
				<dt>&nbsp;</dt>
		<dd><button type="button" name="sm1" class="login-btn"  onclick="login();">登录</button></dd>
	 </dl>
	</form>
   </div>
   <div class="login-power">Powered by<a href="http://www.qutiao.com/" title="趣挑官网"><strong>qutiao</strong></a>© 2004-2011 <a href="http://www.qutiao.com/" target="_blank">趣挑</a> Inc.</div>
</div>
 
</body>
</html>
