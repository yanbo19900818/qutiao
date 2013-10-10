
<%@ page language="java" import="java.util.*,com.qutiao.dao.common.*"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="layrow-right">
	<br>
	<div id="minisite">
		<div class="hd">
			<h2>
				<a href="">商家推广</a>
			</h2>
		</div>
		<div class="minisite-bd">
			<ul>
				<li>
                <script type="text/javascript"><!--
google_ad_client = "ca-pub-0897484870076298";
/* 250x250, 创建于 09-7-26 */
google_ad_slot = "4195509734";
google_ad_width = 250;
google_ad_height = 250;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
				</li>
			</ul>
		</div>
	</div>

	<div id="ad">
		<ul>
			<li>
            <script type="text/javascript"><!--
google_ad_client = "ca-pub-0897484870076298";
/* qutiao */
google_ad_slot = "2465085521";
google_ad_width = 120;
google_ad_height = 600;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
<script type="text/javascript"><!--
google_ad_client = "ca-pub-0897484870076298";
/* qutiao */
google_ad_slot = "2465085521";
google_ad_width = 120;
google_ad_height = 600;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
			</li>
		</ul>
	</div>

	<div class="catacon">
		<div id="discount">
			<h2>
				<a href="" target="_blank" hidefocus="true">热门关键词</a>
			</h2>
		</div>
		<div class="category">
			<a class="ishot" href="">服装服饰</a> <a href="">鞋包配饰</a> <a href="">运动户外</a>
			<a href="">珠宝手表</a> <a href="">数码</a> <a href="">家电</a> <a href="">美容护发</a>
			<a class="ishot" href="">母婴用品</a> <a class="isin" href="">家居建材</a> <a
				href="">美食特产</a> <a href="">日用百货</a> <a href="">汽车,车品</a>
			<div class="clear_f"></div>
		</div>
	</div>


	<div id="discount">
		<h2>
			<a href="" target="_blank" hidefocus="true">近一周热门商品</a>
		</h2>
		<ul>
			<c:forEach var="rightProduct" items="${rightPage.result}">
				<li class="clearfix">
					<div class="pic">
						<span class="num num1"></span> <a href="" target="_blank"> <img
							width="50px" height="50px"
							src='${fn:replace(rightProduct.image,"xgsize","100x100")}'
							alt="${rightProduct.title}"> </a>
					</div>
					<p class="content">
						<a href="detail_${rightProduct.id}_1.html" target="_blank">${rightProduct.title}</a>
						<span class="read"></span>
					</p>
				</li>
			</c:forEach>
		</ul>
		<div style="display: block;" class="sider-ad">
			<a target="_blank" href=""><img alt="全网优惠券"
				src="/assets/images/image.gif"> </a>
		</div>
	</div>
</div>