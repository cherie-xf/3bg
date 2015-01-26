<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>三桥服务</title>
<!--#include file="header.shtml" -->
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css" />

</head>
<body>
	<div class="wrap">
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/globalnav.jsp"%>
		<!--globalnav end-->

        <div class="container-fluid tbscontainer">
			<div class="container-fluid tbsslider">
				<div class="container">
					<div id="carousel-example-generic" class="carousel slide"
						data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item active">
								<img src="/images/slide1.jpg" alt="1">
								<div class="carousel-caption">
									<h3>三桥服务</h3>
									<p>网罗高品质生活服务</p>
									<!--<p>海外华人生活好帮手</p>-->
								</div>
							</div>
							<div class="item">
								<img src="/images/slide2.jpg" alt="2">
								<div class="carousel-caption">
									<h3>三桥安保</h3>
									<p>时刻关注您的住宅安全</p>
									<!--<p>智能家居系统安心之选</p>-->
								</div>
							</div>
							<div class="item">
								<img src="/images/slide3.jpg" alt="3">
								<div class="carousel-caption">
									<h3>三桥租售</h3>
									<p>海外华人首选房屋管家</p>
									<!--<p>提升物业投资回报</p>-->
								</div>
							</div>
						</div>

						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic"
							role="button" data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>

			<div class="col-md-12" style="border-top: 5px solid #dbdbdb;"></div>

			<!-- Advertise -->
			<div class="container-fluid tbsadvertise">
				<div class="container">
					<div class="tbsadvertiseswitch">
						<a data-toggle="collapse" href="#collapseExample"
							aria-expanded="false" aria-controls="collapseExample">展开广告&nbsp;&nbsp;<span
							class="glyphicon glyphicon-chevron-down" aria-hidden="true"
							style="font-size: 13px; color: #666666"></span></a>
					</div>
					<div class="collapse" id="collapseExample">
						<div class="well">三桥集团兄弟公司推介广告</div>
					</div>
				</div>
			</div>

			<!-- Recommended Service -->
			<div class="container-fluid tbsrecommended tbsindexsection">
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4 tbssectionheader">推荐服务</div>
						<div class="col-md-2 col-md-offset-2 tbssectionbutton">
							<button type="button"
								class="btn btn-block tbs-button tbs-button-blue tbs-blue">
								更多服务&nbsp;<span class="glyphicon glyphicon-circle-arrow-right"
									aria-hidden="true" style="font-size: 13px; color: #ffffff"></span>
							</button>
						</div>
					</div>
					<div class="row">
						<!-- start a recommended section -->
						<c:forEach items="${recommendlist}" var="var" varStatus="rowCounter">
						<div class="col-sm-4 tbsrecommendedsection">
							<img class="img-responsive" src="${var.cover_img}"/>
							<div class="row" style="overflow:hidden;height:20px;">
								<div class="col-sm-8">
									<a href="<c:url value="/serviceproduct/productview.html?product_id=${var.service_id}"/>" class="tbsrecommenedtitle">${var.service_title}</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div class="col-sm-4" style="text-align:right;">
								    <a href=""><span class="glyphicon glyphicon-earphone"
										style="color: #03a9dc; font-size: 10px;"></span>&nbsp;预约</a>
									<span class="glyphicon glyphicon-check"
										style="color: #ff713c; text-align: right;"></span>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 tbsrecommenedintro">
									${var.meta_desc }
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
								    <c:set var="categoryid" value="${var.category_id}"/>
								    <c:set var="topcategoryid" value="${fn:substring(categoryid,0,2)}"/>
								    <c:set var="navList" value="${applicationScope.globalnav}"/>
								    <c:forEach items="${navList}" var="var2" varStatus="rowCounter">
							           <c:if test="${var2.category_id eq topcategoryid}">
                                          <span class="tbs-tag-${var2.english_name}">${var2.category_name}</span>							             
							           </c:if>
								    </c:forEach>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="col-xs-12 col-sm-6">
										<div class="tbsrecommendedshare row">
											&nbsp;推荐指数：
											<div style="display: inline-block">
												<input value="${var.recommend_level_id}" type="number" class="rating" min=0 max=5
													step=0.5 data-size="sm" disabled="disabled">
											</div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-3">
										<div class="tbsrecommendedshare row">
											&nbsp;<span class="glyphicon glyphicon-comment"
												style="color: #ffffff; font-size: 10px;"></span>&nbsp;查看评价
										</div>
									</div>
									<div class="col-xs-12 col-sm-3">
										<div class="tbsrecommendedshare row">
											&nbsp;<span class="weibo-s"></span><span class="fb-s"></span>&nbsp;<span
												class="glyphicon glyphicon-share-alt"
												style="color: #ffffff; font-size: 10px;"></span>分享
										</div>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
						<!-- end of a recommended section -->
						
					</div>
				</div>
			</div>

			<!-- Featured Service -->
			<div class="container-fluid tbsfeatured tbsindexsection">
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4 tbssectionheader">特色服务</div>
						<div class="col-md-2 col-md-offset-2 tbssectionbutton">
							<button type="button"
								class="btn btn-block tbs-button tbs-button-blue tbs-blue">
								更多服务&nbsp;<span class="glyphicon glyphicon-circle-arrow-right"
									aria-hidden="true" style="font-size: 13px; color: #ffffff"></span>
							</button>
						</div>
					</div>
					<div class="row">
						<!-- start a featured section -->
						<div class="col-sm-6 tbsfeaturedsection">
							<img class="img-responsive" src="/images/service-package.jpg" />
							<div class="carousel-caption">
								<h3>服务宝</h3>
								<p>量身定制的7*24全方位生活服务套装</p>
								<p>提供最贴心的服务</p>
								<a href="#">了解详情</a>
							</div>
						</div>
						<!-- end of a featured section -->
						<!-- start a featured section -->
						<div class="col-sm-6 tbsfeaturedsection">
							<img class="img-responsive" src="/images/home-security.jpg" />
							<div class="carousel-caption">
								<h3>智能家居</h3>
								<p>随时随地掌握家居安全</p>
								<p>免安装费、设备费</p>
								<a href="#">了解详情</a>
							</div>
						</div>
						<!-- end of a featured section -->
					</div>
				</div>

			</div>

			<div class="col-sm-12" style="border-top: 5px solid #dbdbdb;"></div>

			<!-- News -->
			<div class="container-fluid tbsnews tbsindexsection">
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4 tbssectionheader">服务资讯</div>
					</div>
					<div class="row">
						<div class="col-sm-3 tbsnewssection">
							<img class="img-responsive tbsnewsimage" src="/images/test.png" />
						</div>
						<div class="col-sm-9 tbsnewssection">
						<c:forEach items="${newslist}" var="var" varStatus="rowCounter">
							<div class="col-md-10 tbsnewstitle">
								<a href="#">${var.title}</a>
							</div>
							<div class="col-sm-2 tbsnewsdate">
							   <fmt:formatDate value="${var.created_at}" pattern="YYYY/MM/DD" />
							  
							
							</div>
							<div class="col-sm-10 tbsnewsexcerpt">
								${var.brief}
								<a href="#">[全文阅读]</a>
							</div>
						</c:forEach>	
							
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 col-md-offset-10 tbssectionbutton">
							<button type="button"
								class="btn btn-block tbs-button tbs-button-orange tbs-orange">
								更多&nbsp;<span class="glyphicon glyphicon-circle-arrow-right"
									aria-hidden="true" style="font-size: 13px; color: #ffffff"></span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--container-->
	</div>
	<!--wrap end-->
	<!--#include file="footer.shtml" -->
	<%@include file="/WEB-INF/inc/globalfooter.jsp"%>
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
    <script src="/js/star-rating.min.js"></script> 
    <script src="/js/image-slideshow-5.js"></script> 	
	<script>
	$(document).ready(function() {
		var getting = $.get("navigation/loadnavigation.html");
		getting.done(function(data) {
			console.log(data);
		});			
		//rating
    	$(".rating-kv").rating();

	});
	</script>
</body>
</html>