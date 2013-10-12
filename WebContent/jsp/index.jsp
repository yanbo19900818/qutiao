<%@ page language="java" import="java.util.*"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/qutiao" prefix="m"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta property="qc:admins" content="450110165761541176375" />
<title>趣挑网-首页</title>
<link href="/resource/images/main.css" rel="stylesheet" type="text/css" />
<script src="/resource/js/index_ding_cai.js"></script>
<script src="/resource/js/jquery.js"></script>
</head>
<body>
	<jsp:include page="common/header.jsp"></jsp:include>
	<div id="bd" class="clearfix">
		<div class="layrow clearfix">
			<div class="layrow-left">
				<div class="bd">
					<ul class="hui-list">
						<c:forEach var="product" items="${page.result}">
							<li class="clearfix ">
								<h3>
									<a href="detail_${product.id}_1.html" target="_blank">${product.title}</a>
								</h3>
								<div class="list-info clearfix">
									<span class="linfo-time">${product.createTime}</span> <span
										class="linfo-tags"> <c:forEach var="productKeyword"
											items="${product.keywords}">
											<a class="beta-tag">${productKeyword.keyWordName}</a>
										</c:forEach> </span>
								</div>
								<div class="hui-list-ctn clearfix">
									<div class="hlist-list-pic">
										<a href="detail_${product.id}_1.html" target="_blank"><img
											src='${fn:replace(product.image,"xgsize","240x320")}'
											alt="${product.title}" /> </a>
									</div>
									<div class="hlist-list-text">
										<div class="hui-content">
											<div class="hui-content-text">
												<p>${product.description}</p>
											</div>
										</div>
										<a href="detail_${product.id}_1.html" class="readmore"
											target="_blank"><b></b>继续阅读</a>
									</div>
								</div>
								<div class="hui-list-btm clearfix">
									<a href="${product.url}" rel="nofollow" class="goto-shop"
										target="_blank">马上去买</a>
									<div class="hui-list-relevant clearfix">
										<span class="huili-relevant-cai"> <a
											href="javascript:disagree(${product.id})" rel="nofollow"><span
												class="hico-cai"></span><b id="disagree_${product.id}">${product.disagree}</b>
										</a> </span> <a href="javascript:agree(${product.id})" rel="nofollow"
											class="huili-relevant-ding"><span class="hico-ding"></span><b
											id="agree_${product.id}">${product.agree}</b> </a>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>

				<div class="ft">
					<div class="huihui-paging">
						<m:pageTag before="${page.before}" now="${page.now}"
							next="${page.next}" totalPage="${page.totalPage}"
							url="index_{num}.html" placeholder="{num}"></m:pageTag>
					</div>
				</div>
			</div>
			<jsp:include page="common/right.jsp"></jsp:include>
		</div>
	</div>

	<div class="fixed-leftmenu">
		<ul class="menu">
			<li class="menu-index"><a href="index.html">趣挑首页</a></li>
			<li class="menu-login"><a
				href="<%=request.getContextPath()%>/jsp/login.jsp">登录</a><a
				href="<%=request.getContextPath()%>/jsp/register.jsp"
				target="_blank" rel="nofollow">注册</a> <a href="">收藏</a></li>
			<li class="menu-pnav"><a
				href="<%=request.getContextPath()%>/keyword_1_1.html">日用百货</a></li>
			<li class="menu-pnav"><a
				href="<%=request.getContextPath()%>/keyword_2_1.html">服饰鞋包</a></li>
			<li class="menu-pnav"><a
				href="<%=request.getContextPath()%>/keyword_3_1.html">数码电脑</a></li>
			<li class="menu-pnav"><a
				href="<%=request.getContextPath()%>/keyword_4_1.html">家用电器</a></li>
			<li class="menu-pnav"><a
				href="<%=request.getContextPath()%>/keyword_5_1.html">食品酒茶</a></li>
			<li class="menu-pnav"><a
				href="<%=request.getContextPath()%>/keyword_6_1.html">美容美体</a></li>
			<li class="menu-pnav"><a
				href="<%=request.getContextPath()%>/keyword_7_1.html">母婴儿童</a></li>
			<li class="menu-pnav"><a
				href="<%=request.getContextPath()%>/keyword_8_1.html">手表饰品</a></li>
			<li class="menu-pnav"><a
				href="<%=request.getContextPath()%>/keyword_9_1.html">图书软件</a></li>
		</ul>
	</div>
	<div style="border-top:1px solid #e6e6e6">
		<jsp:include page="common/footer.jsp" /></div>
</body>
</html>
