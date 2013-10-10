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
<body topmargin="8">
	
	<form method="post" id="addModuleFrom" name="addModuleFrom" action="${basePath}/cms/square/addMod.do" ENCTYPE="multipart/form-data">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tbody>
				<tr>
					<td width="65%" height="30"><img height="14"
						src="${basePath}/images/book1.gif" width="20">&nbsp;<a
							href="${basePath}/cms/goods/goodsModuleList.do""><u>广场模块列表</u>
						</a> &gt;&gt; 模块信息
					</td>

					<td width="1%">&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<table width="8%" border="0" align="left" cellpadding="0" cellspacing="0" id="head1">
	
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
			<table width="100%" border="0" align="left" cellpadding="2" cellspacing="2" id="needset" style="border:1px solid #cfcfcf;background:#ffffff;">
				<input type="hidden" name="apiType" id="apiType"  value ="1" ">
			    <input type="hidden" id="groupId" name="groupId" value= ${groupId}>
		       <input type="hidden" id="childId" name="childId" value= ${childId}>
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
															style="height: 22px" onclick="addChildModuleSubmit();" /></td>
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
