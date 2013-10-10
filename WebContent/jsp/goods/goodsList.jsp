<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>
<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>广场商品列表</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.css">
<script src="${basePath}/js/jquery/jquery.js" language="javascript"
	type="text/javascript"></script>
<script src="${basePath}/js/list.js" language="javascript"
	type="text/javascript"></script>
<script type="text/javascript" src="${basePath}/js/emptyselect.js"></script>
<script language="javascript" src="${basePath}/js/dialog.js"></script>
<script language="javascript" src="${basePath}/js/dedeajax2.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery/jquery-1.7.2.min.js"></script>
<script language="javascript" src="${basePath}/js/linkSelect.js"></script>
</head>
<script language="javascript" type="text/javascript">
     function test(){
		var mycars="";
		var modGrId=$("#groupId").val();
		var childId=$("#childId").val();
		var currentPage = $("#currentPage").val();
	    $('input[name="arcID"]:checked').each(function(){ 
	    	mycars+=','+$(this).val(); 
	      });
	    if(mycars.length > 1){
	       window.location = "/qutiao/cms/square/addIndexGoods.do?idArray="+mycars+"&modGrId="+modGrId+"&currentPage="+currentPage+"&childId="+childId;
	    }else{
	       alert("请勾选要保存的商品 ^_^");
	    }
		
		
		//alert(mycars);
		//document.all("form2").setAttribute("action","/adcms/cms/operation/batchDelete.do?idArray="+mycars+"&groupId=1");
		//document.form2.attributes["action"].value = "/adcms/cms/operation/batchDelete.do?idArray="+mycars+"&groupId=1";
		//document.form2.submit();
	}
	
	function delGoods(){
	   var mycars="";
	    $('input[name="arcID"]:checked').each(function(){ 
	    	mycars+=','+$(this).val(); 
	      });
	    if(mycars.length > 1){
	       window.location = "/qutiao/cms/goods/delGoods.do?idArray="+mycars;
	    }else{
	      alert("请勾选要删除的商品 ^_^");
	    }
		
	  
	}
</script>

<body leftmargin="8" topmargin="8" background='${basePath}/images/allbg.gif'>
	<!--  内容列表   -->
	<table width="98%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="#ccd9b9" align="center">
		<tr>
			<td height="26" background="${basePath}/images/newlinebg3.gif">
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td>
					    <input type="button" id="btnIndexGoods" name="btnIndexGoods" class="coolbg np" value="保存推荐商品" onclick="test();" />
					    <input type="hidden" id="currentPage" name="currentPage" value="${page.currPage}"/>
					</td>
				  </tr>
				</table></td>
		</tr>
	</table>
	<form name="form2" id="form2" action="${basePath}/cms/goods/goodsList.do" method="post">
		<table width="98%" border="0" cellpadding="2" cellspacing="1"
			bgcolor="#CFCFCF" align="center" style="margin-top:8px">
			<tr bgcolor="#E7E7E7">
				<td align="right" height="28" colspan="12" background="${basePath}/images/tbg.gif" style="padding-left:10px;">
				        分类名称:<select id="groupId" name ="groupId" ></select>
					模块名称:<select id="childId" name="childId" ></select> 
<%-- 				        分类ID:<input type="text" id="groupId" name="groupId" value="${groupId }"/>
					模块ID:<input type="text" id="childId" name="childId" value="${childId}"/> --%>
					<%-- 模块名称:<input type="text" id="moduleName" name="moduleName"  value="${moduleName }"/> --%>
					<input type="hidden"  name="clickType" value="2" />
					<input type="hidden" id = "selectGrId" name="selectGrId" value = "${selectGrId}"/>
					<input type="hidden" id = "selectChildId" name="selectChildId" value = "${selectChildId}"/>
					<input type="submit" id="sub" name="sub" value="查找" />
			  </td>
			</tr>
			<tr align="center" bgcolor="#FBFCE2" height="25">
				<td width="4%">选择</td>
				<td width="12%">商品图片</td>
				<td width="7%">商品标题</td>
				<td width="8%">商品价格</td>
				<td width="6%">分类ID</td>
				<td width="6%">模块ID</td>
				<td width="8%">模块名称</td>
				<td width="4%">商品交易量</td>
				<td width="4%">卖家信誉度</td>
				<td width="10%">商品发货地</td>
				<td width="9%">入库日期</td>
				<td width="5%">操作</td>
			</tr>
			<c:forEach items="${goodsList}" var="goods">
				<tr align='center' bgcolor="#FFFFFF" height="26" align="center"
					onMouseMove="javascript:this.bgColor='#FCFDEE';"
					onMouseOut="javascript:this.bgColor='#FFFFFF';">
					<td>
					  <input name="arcID" type="checkbox" id="arcID" value="${goods.goodsId}" class="np" />
					</td>
				    <td>
					  <img src='${goods.picUrl }' title="${goods.title}" alt="${goods.title}" height="80" width="80"/>
					</td>
					<td nowrap>${goods.title}</td>
					<td align="right">${goods.price}</td>
					<td>${goods.groupId }</td>
					<td>${goods.childId }</td>
					<td>${goods.moduleTitle }</td>
					<td align="right">${goods.volume}</td>
					<td align="right">${goods.credit}</td>
					<td>${goods.location}</td>
				   <td>${goods.putDate}</td>
					<td><img src='${basePath}/images/file_del.gif' title="删除" alt="删除"
							onClick="location.href='${basePath}/cms/goods/deleteGoods.do?goodsId=${goods.goodsId}'" style='cursor:pointer' border='0'
						width='16' height='16' />
					</td>
				</tr>
			</c:forEach>
			<tr bgcolor="#ffffff">
				<td height="36" colspan="12">&nbsp; <a
					href="javascript:selAll()" class="coolbg">全选</a> 
					<a href="javascript:noSelAll()" class="coolbg">取消</a>
					<input type="button" class="coolbg np" value="删除" onClick="delGoods()" />
				</td>
					
			</tr>

			
		</table>
	</form>
<form action="${basePath}/cms/goods/goodsList.do" method="post" name="form" id="form" >
    <input type="hidden"  name="clickType" value="3" />
	<table width="80%" border="0" >
	  <tr>
	    <td align="right" valign="middle"  colspan="5"> 
		    <%@ include file="../common/page.jsp"%>
	     </td>
	  </tr>
	</table>
</form>
</body>
</html>