<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>模块分组</title>
  
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="${basePath}/css/tree/style.css" rel="stylesheet">
  </head>
<script language="javascript" src="${basePath}/js/dialog.js"></script>
<script language="javascript" src="${basePath}/js/dedeajax2.js"></script>
<script>
function del(grid,childid,name){
	  if(confirm("确定要删除"+name+"吗？"))
	   {
	      window.location="/adcms/cms/square/delMod.do?groupId="+grid+"&childId="+childid;
	   }
}
</script>
<body>
<div id="J_ajax_loading" class="ajax_loading">提交请求中，请稍候...</div>
    <div class="subnav">
		<div class="content_menu ib_a blue line_x">
			<a onClick="location='${basePath}/cms/square/addModView.do';" 
			class="J_showdialog"  data-id="edit" >添加分类</a>
						
		</div>
	</div>
	<!--栏目列表-->
	<div class="pad_lr_10">
		<div class="J_tablelist table_list"
			data-acturi="/pinphp/index.php?g=admin&amp;m=item_cate&amp;a=ajax_edit">
			<table width="100%" cellspacing="0" id="J_cate_tree"
				class="treeTable">
				<thead>
					<tr>
						<th width="30"><input type="checkbox" name="checkall"
							class="J_checkall">
						</th>
						<th width="30"><span data-tdtype="order_by" data-field="id"
							class="sort_th">ID</span>
						</th>
						<th>分类名称</th>
						<th>模块图标</th>
						<th>模块类型</th>
						<th width="80"><span data-tdtype="order_by" data-field="type"
							class="sort_th">分组Id</span>
						</th>
						<th width="60"><span data-tdtype="order_by"
							data-field="ordid" class="sort_th">排序</span>
						</th>
						
						<th width="180">管理操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${firModList}" var="firMod">
					<tr id="node-${firMod.groupId}" class="initialized parent collapsed">
						<td align="center">
							<input type="checkbox" value="${firMod.groupId}" class="J_checkitem">
						</td>
						<td align="center">${firMod.moduleId}</td>
						<td>
							<span style="padding-left: 20px" class="expander"></span>
							<span data-tdtype="edit" data-field="name" data-id="1"
								class="tdedit" style="color:">${firMod.moduleTitle}</span>
						</td>
					    <td align="center">
					     <img src='${firMod.modulePic}' title="${firMod.moduleTitle}" alt="${firMod.moduleTitle}" height="30" width="30"/>
					    </td>
						<td align="center">
						 <c:choose>
					          <c:when test="${firMod.apiType==1}">
					                         购物
					          </c:when>
					          <c:when test="${firMod.apiType==2}">
					                         团购
					          </c:when>
					          <c:when test="${firMod.apiType==3}">
					                         彩票
					          </c:when>
					          <c:otherwise>
					                        充值
					          </c:otherwise>
					     </c:choose> 
						 </td>
						<td align="center">${firMod.groupId}</td>
						<td align="center">
							<span data-tdtype="edit" data-field="ordid" data-id="1" class="tdedit">1</span>
						</td>
						
						<td align="center">
						    <c:if test="${firMod.apiType==1}">
							<a  class="add fb J_showdialog"  onClick="location='${basePath}/cms/square/addModView.do?groupId=${firMod.groupId}';"
								data-title="添加分类" data-id="add" data-width="520" data-height="360">添加子分类</a> |
							</c:if>
						    <a onClick="location='${basePath}/cms/square/editModView.do?grid=${firMod.groupId}&childid=${firMod.childId}';" 
						    	class="J_showdialog"  data-id="edit" >编辑</a> | 
							<img src='${basePath}/images/file_del.gif' title="删除" alt="删除" 
							onclick="del(${firMod.groupId},${firMod.childId},'${firMod.moduleTitle}')"
							style='cursor:pointer' border='0'width='16' height='16' />
						</td>
					</tr>
					<c:forEach items="${secModsList}" var="secMod">
						<c:if test="${secMod.groupId==firMod.groupId}">
						<tr id="node-${secMod.groupId}" class="child-of-node-${firMod.groupId}">
								<td align="center">
									<input type="checkbox" value="2" class="J_checkitem"/>
								</td>
								<td align="center">${secMod.moduleId}</td>
								<td style="padding-left: 24px;">
									<span  style="padding-left: 20px" class="expander"></span>
										&nbsp;&nbsp;&nbsp;├─
									<span data-tdtype="edit" data-field="name" data-id="2" class="tdedit" style="color:">${secMod.moduleTitle}</span>
								</td>
								<td align="center">无</td>
								<td align="center">${firMod.moduleTitle}</td>
								<td align="center">${secMod.childId}</td>
								<td align="center">
									<span data-tdtype="edit" data-field="ordid" data-id="2" class="tdedit">1</span>
								</td>
								<td align="center">
						    		<a onClick="location='${basePath}/cms/square/editModView.do?grid=${secMod.groupId}&childid=${secMod.childId}';" 
						    		class="J_showdialog"  >编辑</a> | 
						<img src='${basePath}/images/file_del.gif' title="删除" alt="删除"
							onclick="del(${secMod.groupId},${secMod.childId},'${secMod.moduleTitle}')" style='cursor:pointer' border='0' 
							   width='16' height='16' />
								</td>
							</tr>
						</c:if>
						</c:forEach>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="btn_wrap_fixed">
			<label class="select_all mr10"> <input type="checkbox"
				name="checkall" class="J_checkall">全选/取消 </label> <input
				type="button" class="btn btn_submit" data-tdtype="batch_action"
				data-acttype="ajax_form" data-id="move"
				data-uri="/pinphp/index.php?g=admin&amp;m=item_cate&amp;a=move"
				data-name="id" data-title="移动分类" value="移动"> <input
				type="button" class="btn" data-tdtype="batch_action"
				data-acttype="ajax"
				data-uri="/pinphp/index.php?g=admin&amp;m=item_cate&amp;a=delete"
				data-name="id" data-msg="您确定要删除吗？" value="删除">
		</div>
	</div>
	<script language="javascript" type="text/javascript" src="${basePath}/js/jquery/jquery-1.7.2.min.js"></script>
	<script src="${basePath}/js/tree/jquery.tools.min.js" language="javascript"
		type="text/javascript"></script>
	<script src="${basePath}/js/tree/pinphp.js" language="javascript" type="text/javascript"></script>

	<script src="${basePath}/js/tree/jquery.treetable.js"></script>
<script>$(function(){
    //initialState:'expanded'
    $("#J_cate_tree").treeTable({indent:20,treeColumn:2});
	$("span").click(function(){//获取页面所有的span添加点击事件
		if($(this).attr("class")=="expander"){//判断所获取的span的class是否为加号
			var id = $(this).parent().parent().attr("id");//通过span获取其父元素的父元素也就是tr的id
			var node = $(".child-of-"+id);//获取对应此id的子节点
			if(node.attr("class")==undefined){
				var onenode = $(this).parent().parent();
				if(onenode.hasClass("isOpen")){
					document.getElementById(""+id+"").className ="initialized parent collapsed";
				}else{
					document.getElementById(""+id+"").className ="initialized parent expanded isOpen";
				}
			}else{
			if(node.hasClass("isOpen")){//如果子节点有isOpen这个class属性则表明已经打开
				var close = $(".child-of-"+id+".isOpen");//通过id跟打开状态唯一确定一个节点
				close.removeClass("isOpen");
				close.css("display","none");//把打开的元素关闭,通过修改css
				document.getElementById(""+id+"").className ="initialized parent collapsed";

			}else{
				node.addClass("isOpen");
				node.css("display","table-row");//把元素打开
				document.getElementById(""+id+"").className ="initialized parent expanded";

			}
			}
			
		}
			
		
});

    $(".J_preview").preview();//重新整理视图
});        

</script>
  </body>
</html>
