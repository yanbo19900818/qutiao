<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script language="javascript">
	function bookmarkit() {
		$("#mark_button").click(function(e) {

			var url = "http://www.qutiao.com";
			var title = "趣挑网";
			try {
				if (window.sidebar) { // Mozilla Firefox Bookmark
					window.sidebar.addPanel(title, url, "");
				} else if (window.external) { // IE Favorite
					window.external.AddFavorite(url, title);
				} else if (window.opera) { // Opera 7+
					return false; // do nothing
				} else {
					alert("请按ctrl+D添加收藏夹");
				}

			} catch (e) {
				alert("请按ctrl+D添加收藏夹");
			}
		});
	}
</script>
<div id="top" class="clearfix">
	<div class="bd">
		<div class="sctop-ctn clearfix">
			<div class="sctop-baoliao">
				<a href="/loginByOp.html?go=sina"><img
					src="/assets/images/login_sina.gif" width="108" height="18"
					alt="新浪微博登录" /></a>
			</div>
			<div class="sctop-baoliao">
				<a href="/loginByOp.html?go=qq"><img
					src="/assets/images/login_qq.gif" width="108" height="18"
					alt="QQ账号登录" /></a>
			</div>
			<div class="sctop-user">
				<%
					if (session.getAttribute("user") == null) {
				%>
				<a href="<%=request.getContextPath()%>/jsp/login.jsp" rel="nofollow">登录</a>
				<a href="<%=request.getContextPath()%>/jsp/register.jsp"
					rel="nofollow">注册</a>
				<%
					} else {
				%>
				<a href="selfInfo.html" rel="nofollow">我的账户</a> <a href="logOut.do"
					rel="nofollow">登出</a>
				<%
					}
				%>
			</div>
			<div class="sctop-help">
				<a href="" class="sctop-help-alink" rel="nofollow"><span
					class="ico-more-down">帮助中心</span> </a>
				<div class="show-sctop-help">
					<a href="" target="_blank" rel="nofollow">帮助</a> <a href=""
						target="_blank" rel="nofollow">意见反馈</a>
				</div>
			</div>

		</div>
	</div>
</div>
<div id="hd">
	<div class="layrow clearfix">
		<div id="logo">
			<h1>
				<a href="/index.html" title="趣挑"> <img src="/assets/images/logo.png"
					alt="趣挑" />
				</a>
			</h1>
		</div>
		<div class="mod-search-box clearfix">每日推荐超值有趣的商品，让你一次挑个够!</div>
	</div>

	<div id="nav">
		<div class="bd-ctn clearfix">
			<ul class="nav clearfix">
				<li class="nav1"><a href="/index.html" class="current "
					title="首页">首页</a></li>

			</ul>
			<div class="nav-toolbar clearfix">
				<a href="javascript:bookmarkit()" title="加入收藏"
					class="toolbar-collect" id="mark_button" rel="nofollow">加入收藏</a> <a
					href="" target="_blank" title="微博" class="toolbar-weibo">微博</a>
			</div>
		</div>
	</div>
</div>
