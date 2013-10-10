<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>添加模块信息</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.css">
	<script src="${basePath}/js/jquery/jquery.js" language="javascript"
		type="text/javascript"></script>
	<script language="javascript" src="${basePath}/js/dialog.js"></script>
	<script language="javascript" src="${basePath}/js/dedeajax2.js"></script>
	<script language="javascript" src="${basePath}/js/edit.js"></script>
	<script language="javascript" src="${basePath}/js/jquery/jquery-1.7.2.min.js"></script>
	<style type="text/css">
<!--
body {
	background-image: url(../../images/allbg.gif);
}

.multipic {
	border: 1px dashed #FC6;
}

.albCt {
	border-bottom: 1px dashed #FC0;
	margin-bottom: 10px;
	padding-bottom: 10px;
}

.albCt img {
	cursor: pointer;
}
-->
</style>
</head>
<script language="javascript">
    function radioCheck(){
        document.all("apiType")[0].checked=true;//第二个radio选中  
        nextStep();
    }
	function nextStep(){
	 var moduleType= $("input[name=apiType]:checked").val();
		if(moduleType=="1"){
		 $("#sctp").hide();
		 $("#kw").show();
		}else {
		 $("#sctp").show();
		 $("#kw").hide();
		}
	}
</script>
<body topmargin="8" onload = "radioCheck()">
	
	<form method="post" id="addModuleFrom" name="addModuleFrom" action="${basePath}/cms/square/addModule.do" ENCTYPE="multipart/form-data">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tbody>
				<tr>
					<td width="65%" height="30"><img height="14"
						src="${basePath}/images/book1.gif" width="20">&nbsp;<a
							href="${basePath}/cms/square/moduleList.do?clickType=3"><u>广场模块列表</u>
						</a> &gt;&gt; 模块信息
					</td>

					<td width="1%">&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<table width="8%" border="0" align="left" cellpadding="0"
			cellspacing="0" id="head1">
			<tbody>
				<tr>
					<td colspan="2">
						<table width="800" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td width="84" height="24" align="center"
										background="${basePath}/images/itemnote1.gif">&nbsp;添加模块&nbsp;</td>
									<td width="84"></td>
									<td>&nbsp;</td>
								</tr>
							</tbody>
						</table></td>
				</tr>
			</tbody>
		</table>
		<div style="text-align:left">
			<table width="100%" border="0" align="left" cellpadding="2"
				cellspacing="2" id="needset"
				style="border:1px solid #cfcfcf;background:#ffffff;">
				<tbody align="left">
					<tr>
						<td height="24" colspan="4" class="bline">
							<table width="800" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<td width="90" align="right"><font color="red">*</font>模块标题：</td>
										<td><input type="text" id="moduleTitle" name="moduleTitle"
											 style="width:388px">
										</td>
									</tr>
									<tr>
										<td></td>
										<td align="left"><font color="red"><span
												id="nameMessage"></span>
										</font></td>
									</tr>
								</tbody>
							</table></td>
					</tr>

					<tr>
						<td height="24" colspan="4" class="bline">
							<table width="800" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<td width="90" align="right"><font color="red">*</font>图片地址：</td>
										<td><input type="file" name="icon" id="icon"
											style="width:388px">
										</td>
									</tr>
								</tbody>
							</table></td>
					</tr>
					
					<tr>
						<td height="24" colspan="4" class="bline">
						   
							<table width="800" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<td width="90" align="right"><font color="red">*</font>模块类型：</td>
										<td>
										     购物<input type="radio" name="apiType" id="apiType"  value ="1" onchange="nextStep();">
										     团购<input type="radio" name="apiType" id="apiType"  value ="2" onchange="nextStep();">
										     彩票<input type="radio" name="api_type" id="apiType"  value ="3" onchange="nextStep();">
										     充值<input type="radio" name="apiType" id="apiType"  value ="4" onchange="nextStep();">
										</td>
									</tr>
								</tbody>
							</table>
						
							</td>
					</tr>
					<tr>
						<td height="24" colspan="4" class="bline">
						   <div id="kw" >
							<table width="800" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<td width="90" align="right"><font color="red">*</font>关键字：</td>
										<td><input type="text" id="keyWords" name="keyWords"
											 style="width:388px"><font color="red">(多组关键字用逗号隔开)</font>
										</td>
									</tr>
								</tbody>
							</table>
						   </div>
						</td>
					</tr>
					<tr>
						<td height="24" colspan="4" class="bline">
						 <div id="sctp" >
							<table width="800" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<td width="90" align="right"><font color="red">*</font>链接地址：</td>
										<td><input type="text" name="linkUrl" id="linkUrl"
											 style="width:388px">
										</td>
									</tr>
								</tbody>
							</table>
						  </div>
							</td>
					</tr>
					<tr>
						<td height="24" colspan="4" class="bline">
							<table width="800" border="0" cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<td width="90" align="right"><font color="red">*</font>跳转类型：</td>
										<td>
										   <select  id="linkAction" name="linkAction" style="width:388px">
										    <option value = "">请选择</option>
				                            <option value = "1">调用顶部带导航栏的webview</option>
										    <option value = "2">调用底部带半透明导航栏的webbiew</option>
				                            <option value = "3">下载应用(只针对android)</option>
				                            <option value = "4">调用浏览器</option>
										</td>
									</tr>
								</tbody>
							</table></td>
					</tr>
				</tbody>
			</table>
					<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" bgcolor="#F9FCEF"
				style="border:1px solid #cfcfcf;border-top:none;">
				<tbody>
					<tr>
						<td height="35">
							<table width="98%" border="0" cellspacing="1" cellpadding="1">
								<tbody>
									<tr>
										<td width="17%">&nbsp;</td>
										<td width="83%"><table width="214" border="0"
												cellspacing="0" cellpadding="0">
												<tbody>
													<tr>
														<td width="115"><input type="button" value="　确定　"
															style="height: 22px" onclick="addModuleSubmit();" /></td>
														<td width="99"><input type="reset" value="重置" /></td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>
