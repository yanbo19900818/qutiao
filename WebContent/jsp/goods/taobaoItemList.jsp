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
		var currentPage = $("#currentPage").val();
	    $('input[name="arcID"]:checked').each(function(){ 
	    	mycars+=','+$(this).val(); 
	      });
	    if(mycars.length == 0){  
	      alert("请勾选要保存的商品 ^_^");
	      return ;
	    }
		window.location = "/qutiao/cms/goods/saveTaobaoGoods.do?idArray="+mycars+"&currPage="+currentPage;
	}
	
	function checkIn(){
	  var grid = $("#groupId").val();
	  var childId = $("#childId").val();
	  var keyWords = $("#keyWords").val().trim();
	  var goodsKind = $("#goodsKind").val().trim();
	  if(grid < 1){
	    alert("模块分类ID不能为空 ^_^");
	    return ;
	  }
	  if(childId <1 ){
	    alert("模块ID不能为空 ^_^");
	    return ;
	  }
	  if(keyWords =="" && goodsKind==""){
	     alert("关键字与商品种类不能同时为空 ^_^");
	     return ;
	  }
	  $("#form2").submit();
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
					    <input type="button" class="coolbg np" value="保存" onClick="test()" />
					</td>
				  </tr>
				</table>
		   </td>
		</tr>
	</table>
	<form name="form2" id="form2" action="${basePath}/cms/goods/searchTaobaoItems.do" method="post">
		<table width="98%" border="0" cellpadding="2" cellspacing="1"
			bgcolor="#CFCFCF" align="center" style="margin-top:8px">
			<tr bgcolor="#E7E7E7">
				<td align="right" height="28" colspan="12" background="${basePath}/images/tbg.gif" style="padding-left:10px;" >
					<input type="hidden" id = "selectGrId" name="selectGrId" value = "${selectGrId}"/>
					<input type="hidden" id = "selectChildId" name="selectChildId" value = "${selectChildId}"/>
					 分类名称:<select id="groupId" name ="groupId" ></select>
					模块名称:<select id="childId" name="childId"></select> 
					关键字:<input type="text" id="keyWords" name="keyWords"  value="${taobaokeSearchBean.keyWords} " />
					商品分类:<input type="text" id="goodsKind" name="goodsKind" size='7' value="${taobaokeSearchBean.goodsKind}"/>
					价格:<input type="text" id="startPrice" size='5'name="startPrice" value="${taobaokeSearchBean.startPrice}"/>
					-<input type="text" id="endPrice" size='5'name="endPrice" value="${taobaokeSearchBean.endPrice}" />
					佣金比例:<input type="text" id="startCommissionRate" name="startCommissionRate" size='5' value="${taobaokeSearchBean.startCommissionRate}"/>%
					-<input type="text" id="endcommissionRate" name="endcommissionRate" size='5' value="${taobaokeSearchBean.endcommissionRate}"/>%
					<input type="hidden"  name="clickType" value="1" />
					 <input type="hidden" id="currentPage" name="currentPage" value="${page.currPage}"/>
					<input type="button" value = "查找 " onclick = "checkIn()"/>
					<input type="reset"  value="重置" onclick="empty();"/>
			  </td>
			</tr>
			<tr bgcolor="#E7E7E7">
					<td align="left" height="28" colspan="12" background="${basePath}/images/tbg.gif" style="padding-left:10px;">
					 <a href="javascript:selAll()" class="coolbg">全选</a>
					 <a href="javascript:noSelAll()" class="coolbg">取消</a>
			        </td>
			</tr>
			<tr align="center" bgcolor="#FBFCE2" height="25">
				<td width="4%">选择</td>
				<td width="12%">商品图片</td>
				<td width="7%">商品标题</td>
				<td width="8%">商品价格</td>
				<td width="6%">佣金比</td>
				<td width="7%">商品交易量</td>
				<td width="6%">卖家信誉度</td>
				<td width="10%">商品发货地</td>
				<td width="9%">入库日期</td>
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
					<td align="right">${goods.commissionRate }%</td>
					<td align="right">${goods.volume}</td>
					<td align="right">${goods.credit}</td>
					<td>${goods.location}</td>
				   <td>${goods.putDate}</td>
				</tr>
			</c:forEach>
			<tr bgcolor="#ffffff">
				<td height="36" colspan="12">&nbsp; <a
					href="javascript:selAll()" class="coolbg">全选</a> <a
					href="javascript:noSelAll()" class="coolbg">取消</a></td>
			</tr>
		</table>
	</form>
<form action="${basePath}/cms/goods/searchTaobaoItems.do" method="post" name="form" id="form" >
    <input type="hidden"  name="clickType" value="2" />
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