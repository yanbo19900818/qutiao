<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
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
</head>
<script language="javascript" type="text/javascript">
     function test(){
		var mycars="";
		var modGrId=$("#groupId").val();
		if(confirm("确定删除模块分类ID: "+modGrId+"的所有推荐商品吗" )){
		   window.location = "/adcms/cms/square/delAllIndexGoods.do?&groupId="+modGrId;
		}
	}
	 function delIndexGoods(){
	   var mycars="";
	   $('input[name="arcID"]:checked').each(function(){ 
	    	mycars+=','+$(this).val(); 
	      });
	   var modGrId=$("#groupId").val();
	   if(mycars.length > 1){
	       window.location = "/adcms/cms/square/delIndexGoodsS.do?groupId="+modGrId+"&goodsIds="+mycars;
	   }else{
	      alert("请选择要删除的商品 ^_^");
	   }
	    
	 }
	 
	 $(document).ready(
    function() {
      $("#btnCreatPage").click(function() {
        var url ="/adcms/cms/square/checkNullPage.do";
        jQuery.ajax( {
          type : 'GET',
          contentType : 'application/json',
          url : url,
          dataType : 'json',
          success : function(data) {
        	  if (data.flag == "true"){
        	    window.location = "/adcms/cms/goods/createPage.do";
              }else{
                if(data.flag == "erro1"){
                     var modNameStr ="";
                     $.each(data.firModList, function(i, item) {
                        modNameStr += item;
                        if(i<data.firModList.length-1){
                          modNameStr += ",";
                        }
                     });
                     alert("分类模块["+modNameStr+"]"+"的推荐商品为空!");
                     return;
                }
                if(data.flag == "erro2"){
                      var modNameStr ="";
                     $.each(data.secModList, function(i, item) {
                        modNameStr += item;
                        if(i<data.secModList.length-1){
                          modNameStr += ",";
                        }
                     });
                     alert("子模块("+modNameStr+")"+"的商品列表为空!");
                     return;
                }
                if(data.flag == "erro3"){
                   var firModNameStr ="";
                   var secModNameStr ="";
                     $.each(data.firModList, function(i, item) {
                        firModNameStr += item;
                        if(i<data.firModList.length-1){
                          firModNameStr += ",";
                        }
                     });
                     $.each(data.secModList, function(i, item) {
                        secModNameStr += item;
                        if(i<data.secModList.length-1){
                          secModNameStr += ",";
                        }
                     });
                     alert("分类模块["+firModNameStr+"],"+"子模块("+secModNameStr+")"+"的商品列表为空!");
                     return;
                }
              }
          },
          error : function(data) {
            alert("error");
          }
        });
      });
    });
</script>
<script type='text/javascript'>
    function submitForm(frm,event){
      var event=window.event?window.event:event; if(event.keyCode==13){
        frm.submit();
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
					    <input type="button" id="btnCreatPage" name="btnCreatPage" class="coolbg np" value="创建页面"  />
					    <input type="hidden" id="currentPage" name="currentPage" value="${page.currPage}"/>
					</td>
				  </tr>
				</table></td>
		</tr>
	</table>
	<form name="form2" id="form2" action="${basePath}/cms/square/getIndexGoodsList.do" method="post">
		<table width="98%" border="0" cellpadding="2" cellspacing="1"
			bgcolor="#CFCFCF" align="center" style="margin-top:8px">
			<tr bgcolor="#E7E7E7">
			<td align="right" height="28" colspan="12" background="${basePath}/images/tbg.gif" style="padding-left:10px;">
		                模块分类名称:
		         <select  id="groupId" name="groupId" onkeydown='submitForm(this.form,event)' >
			       <c:forEach items="${goodsGrIdList}" var="module">
			         <c:choose>  
			            <c:when test="${ module.groupId == groupId}">      
			               <option value="${groupId }" selected="selected">${module.moduleTitle }</option>
		                </c:when>  
      		           <c:otherwise>   
		                  <option value="${module.groupId }" >${module.moduleTitle }</option>
		               </c:otherwise>  
		             </c:choose>
			       </c:forEach>
			    </select>
			    <input type="hidden"  name="clickType" value="3" />
				<input type="submit" id="sub" name="sub" value="查找" />
				<input type='button'  onClick="test();" value='清空推荐商品' />
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
				<td width="6%">商品交易量</td>
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
					<td>${goods.groupId }</td>
					<td>${goods.childId }</td>
					<td>${goods.moduleTitle }</td>
					<td align="right">${goods.volume}</td>
					<td align="right">${goods.credit}</td>
					<td>${goods.location}</td>
				   <td>${goods.putDate}</td>
				</tr>
			</c:forEach>
			<tr bgcolor="#ffffff">
				<td height="36" colspan="12">&nbsp; <a
					href="javascript:selAll()" class="coolbg">全选</a> <a
					href="javascript:noSelAll()" class="coolbg">取消</a>
					<input type="button" class="coolbg np" value="删除" onClick="delIndexGoods()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>