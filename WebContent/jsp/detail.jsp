<%@ page language="java" import="java.util.*,com.qutiao.dao.common.*"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/qutiao" prefix="m"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>趣挑网-${product.title}</title>
<script src="/resource/js/detail_ding_cai.js"></script>
<script src="/resource/js/jquery.js"></script>

<link href="/resource/images/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="common/header.jsp"></jsp:include>
	<div id="bd" class="clearfix">
		<div class="layrow clearfix">
			<div class="layrow-left">
				<div class="bd">
					<ul class="hui-list">
						<li class="clearfix ">
							<h3>
								<a href="" target="_blank">${product.title}</a>
							</h3>
							<div class="list-info clearfix">
								<span class="linfo-time">${product.createTime}</span> <span
									class="linfo-advitory"><c:forEach var="productKeyword"
										items="${product.keywords}">
										<a class="beta-tag">${productKeyword.keyWordName}</a>
									</c:forEach> </span>
							</div>


							<div class="hui-content">
								${product.content}
								<div class="goto-shop-red">
									<a href="${product.url}" target="_blank">走你~去看看</a>
								</div>
							</div>

							<div class="hui-list-btm">
								<div class="huili-comment clearfix">
									<a href="javascript:agreeProduct(${product.id})" rel="nofollow"
										class="huili-relevant-ding"> <span class="hico-ding"></span>
										<b id="agree_${product.id}">${product.agree}</b> </a> <span
										class="huili-relevant-cai"> <a
										href="javascript:disagreeProduct(${product.id})"
										rel="nofollow"> <span class="hico-cai"></span> <b
											id="disagree_${product.id}">${product.disagree}</b> </a> </span>
								</div>
							</div>

							<div class="Strategy-editor-tips">
								<p class="editer-content">
									本文地址：http://www.qutiao.com/qutiao/detail_${product.id}.html
									请转发给你的朋友，或者点击下面的按钮分享，请<a
										href="javascript:share('http://www.qutiao.com/qutiao/detail_${product.id}.html','${fn:substring(interestedProduct.title,0,40)}')">点击这里分享</a>！
								</p>
							</div></li>
					</ul>
				</div>

				<div class="hd">
					<h2>
						<a href="">你可能感兴趣</a>
					</h2>
				</div>
				<div class="content-wraper">
					<ul>
						<c:forEach var="interestedProduct" items="${interestedList}">
							<li>
								<div class="pic">
									<a href="detail_${interestedProduct.id}_1.html"
										title="${interestedProduct.title}" class="js-log"> <img
										width="189px" height="174px"
										src='${fn:replace(interestedProduct.image,"xgsize","240x320")}'
										title="${interestedProduct.title}"
										alt="${interestedProduct.title}" /> </a>
								</div>
								<div class="content">
									<a href="detail_${interestedProduct.id}_1.html"> <span>${fn:substring(interestedProduct.title,0,34)}</span>
									</a>
								</div></li>
						</c:forEach>
					</ul>
				</div>
				<br />

				<div class="hd">
					<h2>
						<a href="">我也说几句</a>
					</h2>
				</div>
				<%
					if (session.getAttribute("user") != null) {
				%>

				<form id="review_form" action="/addReview.do" method="post">
					<div id="comment">
						<div class="bd clearfix">
							<div class="add-comment-wrap clearfix">
								<div class="user-info-comment">
									<span class="face"> <a href="/account/info"
										rel="nofollow" target="_blank"> <img alt="用户头像"
											src="../../images/image.jpg" width="50" height="50">
									</a> </span> <span class="set-username"> ${user.name}</span>
								</div>
								<div class="add-comment-ctn" node-type="comment-main-box">

									<div class="add-comment" node-type="comment-box">
										<div class="add-comment-bg">
											<textarea name="content" spellcheck="false"
												data-rules="len2000"></textarea>
											<span class="def-tips" node-type="default-tip">也来说几句吧</span>
											<div class="tips-dailog" node-type="succ-tip">
												<span class="ico-success"></span>评论提交成功！
											</div>
										</div>

										<div class="row-submit">
											<input type="button" class="submit" value="发表评论" onclick="$('#review_form').submit()" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="pid" value="${product.id}" />
					<input type="hidden" name="uid" value="${user.id}" />
				</form>
				<%
					} else {
				%>
				<div id="comment" style="text-align:center">
					
                    <div class="bd clearfix">
							<div class="add-comment-wrap clearfix">
								<div class="user-info-comment">
									<span class="face"> <a href="/account/info"
										rel="nofollow" target="_blank"> <img alt="用户头像"
											src="../../images/image.jpg" width="50" height="50">
									</a> </span> 
								</div>
								<div class="add-comment-ctn" node-type="comment-main-box">
                                	<div class="nologin clearfix">
                                        <div class="nologin-text">
                                            <a href="/jsp/login.jsp" class="login">登录</a> | <a href="/jsp/register.jsp">注册</a>
                                        </div>
                                     </div>   

									<div class="add-comment" node-type="comment-box">
										<div class="add-comment-bg">
											<textarea name="content" spellcheck="false"
												data-rules="len2000"></textarea>
											<span class="def-tips" node-type="default-tip">您需要登录后才可以评论 </span>
											<div class="tips-dailog" node-type="succ-tip">
												<span class="ico-success"></span>评论提交成功！
											</div>
										</div>

										<div class="row-submit">
											<input type="button" class="submit" value="发表评论" />
										</div>
									</div>
								</div>
							</div>
						</div>
                    
				</div>

				<%
					}
				%>
				<div class="hd">
					<h2>
						<a href="">看看大家怎么说</a>
					</h2>
				</div>

				<div class="commenteven">
					<ul>
						<c:forEach var="review" items="${product.reviewPage.result}">
							<li>
								<div class="commentLeft">
									<div class="floor2"></div>
									<div class="avatarImgBox">
										<img src='images/small-logo.jpg'
											class='avatar avatar- photo avatar-default' height='40'
											width='40' alt='${review.username}' />
									</div>
								</div>
								<div class="commentRight">
									<div class="floor">
										<div class="commentTime"><span class="username">${review.username}</span>  ${review.createTime}</div>
										<div class="up supportBox">
											<div class="agree ">
												<span> <a rel="nofollow" href="">顶(${review.agree})</a>
												</span>
											</div>
											<div class="opposition ">
												<span> <a rel="nofollow" href="">踩(${review.disagree})</a>
												</span>
											</div>
											<div class="clear"></div>
										</div>
									</div>
									<div class="test">
										<span></span>
										<p>${review.content}</p>
										<div class="clear"></div>
									</div>
								</div>
								<div class="clear"></div></li>
						</c:forEach>
					</ul>
					<div class="huihui-paging">
						<m:pageTag before="${product.reviewPage.before}"
							now="${product.reviewPage.now}" next="${product.reviewPage.next}"
							totalPage="${product.reviewPage.totalPage}"
							url="detail_${product.id}_{num}.html" placeholder="{num}"></m:pageTag>
					</div>
					<div class="clear"></div>


				</div>
			</div>

			<jsp:include page="common/right.jsp"></jsp:include>

		</div>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
