<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>广告代发布</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${basePath}/css/frame.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${basePath}/themes/pepper-grinder/easyui.css" id="swicth-style">
	
	<script src="${basePath}/js/jquery/jquery.js" language="javascript" type="text/javascript"></script>
	<script src="${basePath}/js/frame.js" language="javascript" type="text/javascript"></script>
 	<script type="text/javascript" src="${basePath}/js/jquery/jquery.easyui.min.js"></script>
	<script language="javascript" type="text/javascript" src="${basePath}/js/leftmenu.js"></script>
	<link rel="stylesheet"  href="${basePath}/css/base.css" type="text/css" />
	<script language="javascript" type="text/javascript" src="${basePath}/js/dedeajax2.js"></script>
 	<style>
div {
	padding:0px;
	margin:0px;
}

dl.bitem {
	clear:both;
	width:140px;
	margin:0px 0px 5px 12px;
	background:url(../../images/menunewbg.gif) repeat-x;
}
dl.bitem2 {
	clear:both;
	width:140px;
	margin:0px 0px 5px 12px;
	background:url(../../images/menunewbg2.gif) repeat-x;
}
dl.bitem dt, dl.bitem2 dt {
	height:25px;
	line-height:25px;
	padding-left:35px;
	cursor:pointer;
}
dl.bitem dt b, dl.bitem2 dt b {
	color:#4D6C2F;
}
dl.bitem dd, dl.bitem2 dd {
	padding:3px 3px 3px 3px;
	background-color:#fff;
}
div.items {
	clear:both;
	padding:0px;
	height:0px;
}
.fllct {
	float:left;
	width:85px;
}
.flrct {
	padding-top:3px;
	float:left;
}
.sitemu li {
	padding:0px 0px 0px 18px;
	line-height:22px;
	background:url(../../images/arr4.gif) no-repeat 5px 9px;
}
ul {
	padding-top:3px;
}
li {
	height:22px;
}
a.mmac div {
	background:url(images/leftbg2.gif) no-repeat;
	height:37px!important;
	height:47px;
	padding:6px 4px 4px 10px;
	
	font-weight:bold;
	color:#325304;
}
a.mm div {
	background:url(images/leftmbg1.gif) no-repeat;
	height:37px!important;
	height:47px;
	padding:6px 4px 4px 10px;
	
	font-weight:bold;
	color:#475645;
	cursor:pointer;
}
a.mm:hover div {
	background:url(images/leftbg2.gif) no-repeat;
	color:#4F7632;
}
.mmf {
	height:1px;
	padding:5px 7px 5px 7px;
}
#mainct {
	padding-top:8px;
	background: url(../../images/idnbg1.gif) repeat-y;
}
</style>
 
  </head>
<script type="text/javascript">
function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);//选中并刷新
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != '首页') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	} else {
		var content = createFrame(url);
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	tabClose();
}
function createFrame(url) {
	var s = '<iframe id="main" name="main" frameborder="0" src="'+url+'"></iframe>';

	return s;
}
		
function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}		
//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != '首页') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != '首页') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != '首页') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != '首页') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

$(function() {
$('#main').height(document.documentElement.clientHeight);
	tabCloseEven();

	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});


});


function setCookie(name,value) {//两个参数，一个是cookie的名子，一个是值
    var Days = 30; //此 cookie 将被保存 30 天
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {//取cookies函数        
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;
}

function logout(){
	if(confirm("确定要退出广告系统？"))
	{
		 window.location.href="/adcms/cms/user/logout.do"; 
	}
	
	
}
</script>
  
  <body class="showmenu">
<div class="pagemask"></div>
<iframe class="iframemask"></iframe>

<div class="head">
  <div class="top">
    <div class="top_logo"> 
    </div>
    <div class="top_link">
      <ul>
        <li class="welcome">您好：<%=request.getParameter("userName") %> ，欢迎使用趣挑后台管理系统！</li>
       <li><a onclick="logout();" target="_top">注销</a></li>
      </ul>
    <!--   <div class="quick"> 
    <a href="#" class="ac_qucikmenu" id="ac_qucikmenu">快捷方式</a> 
    <a href="#" class="ac_qucikadd" id="ac_qucikadd">
       
        </a> </div> -->
    </div>
  </div>
  <div class="topnav">
    <!-- <div class="menuact">
     	<a href="#" id="togglemenu">隐藏菜单</a> 
     
      </div>-->
    <div id="skin">
        <div>
          <ul id="skinlist">
            <li id="s1">
              <div class="sel"><img alt="织梦绿" src="../../images/blank.gif"></div>
            </li>
            <li id="s2">
              <div><img alt="淡蓝" src="../../images/blank.gif"></div>
            </li>
            <li id="s3">
              <div><img alt="咖啡" src="../../images/blank.gif"></div>
            </li>
            <li id="s4">
              <div><img alt="水墨" src="../../images/blank.gif"></div>
            </li>
          </ul>
        </div>

    </div>
    <div class="nav" id="nav"> </div>
  
  </div>
</div>
<div class="left">
  <div class="menu" id="menu"  style="position:absolute;overflow:auto;overflow-x:hidden;background:#EEEEEE;">
    <table width="200" align="left" border='0' cellspacing='0' cellpadding='0' style="text-align:left;">
  
  <tr>
    <td valign='top' style='padding-top:10px' width='20'> 
      
            <div class='mmf'></div></td>
    <td width='160' id='mainct' valign="top"><div id='ct1'>
        <!-- Item 2 Strat -->


 <c:forEach items="${menuList}" var="zenList" > 
 <c:if test="${zenList.accessFlag ==1&&zenList.menuId==500}">  

<dl class='bitem' id='sunitems4_1'>
    <dt onClick='showHide("items4_1")'><b>广场管理</b></dt>
    <dd style='display:block' class='sitem' id='items4_1'>
      <ul class='sitemu'>
         <li>
            <div class='fllct'><a href="javascript:void(0);" src='${basePath}/cms/goods/goodsModuleList.do?clickType=1'  class="cs-navi-tab" target='main'>模块分组</a></div>
         </li>
         <li>
            <div class='fllct'><a href="javascript:void(0);" src='${basePath}/cms/goods/taobaoItemList.do?clickType=3'  class="cs-navi-tab" target='main'>商品采集</a></div>
         </li>
         <li>
            <div class='fllct'><a href="javascript:void(0);" src='${basePath}/cms/goods/goodsList.do?clickType=1'   class="cs-navi-tab" target='main'>商品详情</a></div>
         </li>
         <li>
            <div class='fllct'><a href="javascript:void(0);" src='${basePath}/cms/goods/getIndexGoodsList.do?clickType=1'   class="cs-navi-tab" target='main'>推荐商品</a></div>
         </li>
      </ul>
    </dd>
</dl>

</c:if>

</c:forEach>

<!-- Item 2 End -->

      </div>
      <div id='ct100'></div>
      <div id='ct3'></div>
      <div id='ct5'></div>
      <div id='ct6'></div>
      <div id='ct7'></div>
      <div id='ct20'></div>
      <div id='ct10'></div></td>
  </tr>


  <tr>
    <td width='26'></td>
    <td width='160' valign='top'><img src='../../images/idnbgfoot.gif' /></td>
  </tr>
</table>
    
  </div>
</div>
<div class="right">
  <div id="mainPanle" region="center" border="true" border="false" class="main">
		<div id="tabs" class="easyui-tabs"  fit="true" border="false" >
                <div title="首页">
				<div class="cs-home-remark">

    <iframe id="main" name="main" frameborder="0" src="${basePath}/testJsp.do"></iframe>
 	</div>
				</div>
        </div>
		
  </div>
  <!--<div id="help"><span id="content"><a href="#">栏目管理操作使用说明</a></span></div>-->
</div>
<div class="qucikmenu" id="qucikmenu">
  <ul>
    <li><a href="content_list.html" target="main">文档列表</a></li>
<li><a href="feedback_main.html" target="main">评论管理</a></li>
<li><a href="public_guide.html" target="main">内容发布</a></li>
<li><a href="catalog_main.html" target="main">栏目管理</a></li>
<li><a href="sys_info.html" target="main">修改参数</a></li>
  </ul>
</div>

</body>
</html>
