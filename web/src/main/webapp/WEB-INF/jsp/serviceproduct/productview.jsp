<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>服务产品</title>
<!--#include file="header.shtml" -->
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css" />

<link rel="stylesheet" type="text/css" href="http://www.shieldui.com/shared/components/latest/css/shieldui-all.min.css" />
<link rel="stylesheet" type="text/css" href="http://www.shieldui.com/shared/components/latest/css/light/all.min.css" />

</head>
<body>
	<div class="wrap">
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/topnav.jsp"%>
		<!--globalnav end-->
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/globalnav.jsp"%>
		<!--globalnav end-->


		<div class="container tbscontainer">
			<div class="main tbsservice">
				<div class="col-sm-12" id="treeLabel">
					<a>居家生活</a> > <a>美容美发</a> > <a>美甲</a> > <a href="#">音速美甲</a> >
					ABC美甲服务
				</div>

				<div class="col-sm-12 tbsservicetitile">
					<div class="row">
						<div class="col-sm-10">
							<div class="tbsservicetitileleft">
								<span class="glyphicon glyphicon-bookmark"
									style="color: #ff772f;"></span>&nbsp;&nbsp;<a href="#">${businessProfile.business_name}</a>
								> ${serviceProduct.service_title}
							</div>
						</div>
						<div class="col-sm-2">
							<a id="creationlink" href="../serviceorder/create.html?service_id=${serviceProduct.service_id}" 
								class="btn btn-block tbs-button tbs-button-orange tbs-orange openEditPage">立即订购&nbsp;<span class="glyphicon glyphicon-circle-arrow-right"
									aria-hidden="true" style="font-size: 13px; color: #ffffff"></span>
							</a>
<!-- 							<button type="button" -->
<!-- 								class="btn btn-block tbs-button tbs-button-orange tbs-orange"> -->
<!-- 								立即订购&nbsp;<span class="glyphicon glyphicon-circle-arrow-right" -->
<!-- 									aria-hidden="true" style="font-size: 13px; color: #ffffff"></span> -->
<!-- 							</button> -->
						</div>
					</div>
				</div>

				<div class="col-sm-12 tbservicecontent">
					<div class="row">
						<div class="col-sm-5">
							<img src="${serviceProduct.cover_img}" class="img-responsive">

							<div class="row">
								<div class="col-sm-12">
									<div class="tbsleftbarblockgrey">
										<div class="tbsleftbarblockbolditem" style="font-size: 16px;">服务商信息：</div>
										<div class="tbsleftbarblockbolditem">
											服务商名称： <a href="#">${businessProfile.business_name}</a>
										</div>
										<div class="tbsleftbarblockbolditem" id="categoryLabel">
											分类标签：<a>居家生活</a> > <a>美容美发</a> > <a>美甲</a> > <a
												href="WS21_ServiceProvider.shtml">音速美甲</a> > ABC美甲服务
										</div>
										<div class="tbsleftbarblockbolditem">联系方式：</div>
										<div class="tbsleftbarblockitem">
											<span class="glyphicon glyphicon-map-marker"></span> &nbsp;
											${businessProfile.address} &nbsp; ${businessProfile.postcode}
										</div>

										<div class="tbsleftbarblockitem">
											<span class="glyphicon glyphicon-envelope"></span> &nbsp;
											${businessProfile.email}
										</div>
										<div class="tbsleftbarblockitem">
											<span class="glyphicon glyphicon-phone-alt"></span> &nbsp;
											${businessProfile.phone}
										</div>
										<div class="tbsleftbarblockitem">
											<span class="glyphicon glyphicon-send"></span> &nbsp;
											wechat:&nbsp; ${businessProfile.wechat}
										</div>

									</div>

								</div>
							</div>
						</div>

						<div class="col-sm-7">
							<div class="row">
								<div class="col-sm-12">
									<div class="tbstitle">产品详情：</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="tbsleftbarblockbolditem">产品名称：
										${serviceProduct.service_title}</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="tbsleftbarblockbolditem">推荐指数：
										<div class="rating noPointClass" id="recommend_level"></div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="tbsleftbarblockitem">${serviceProduct.description}
									</div>
									
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="tbsleftbarblockgrey">
										<div class="tbsleftbarblockbolditem">产品价格：</div>
										<div class="tbsleftbarblockbolditem">服务产品单价：${serviceProduct.unit_price}</div>
										<div class="tbsleftbarblockbolditem">服务起步价：${serviceProduct.basic_price}</div>
										
										<c:if test="${serviceProduct.tax_included=='false'}">
										
										<div class="tbsleftbarblockbolditem">不含税GST、PST :${serviceProduct.tax_included} </div>
										</c:if>
										<c:if test="${serviceProduct.tax_included=='true'}">
										
										<div class="tbsleftbarblockbolditem">含税GST、PST  </div>
										</c:if>
										
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="tbsleftbarblockbolditem">服务区域范围：${area}</div>
									<div class="tbsleftbarblockbolditem">月销量：201 | 累计销量：6022</div>
									<div class="tbsleftbarblockbolditem">质保承诺：${serviceProduct.warrant}</div>
								</div>
							</div>

							

							<div class="row tbsblock">
								<div class="col-md-4 col-md-offset-8 tbssectionbutton">
									<a id="creationlink" href="../serviceorder/create.html?service_id=${serviceProduct.service_id}" 
										class="btn btn-block tbs-button tbs-button-orange tbs-orange openEditPage">立即订购&nbsp;<span class="glyphicon glyphicon-circle-arrow-right"
											aria-hidden="true" style="font-size: 13px; color: #ffffff"></span>
									</a>
									<!-- <button type="button"
										class="btn btn-block tbs-button tbs-button-orange tbs-orange">
										立即订购&nbsp;<span class="glyphicon glyphicon-circle-arrow-right"
											aria-hidden="true" style="font-size: 13px; color: #ffffff"></span>
									</button> -->
								</div>
							</div>

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
	
	<script type="text/javascript" src="http://www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
	<script>
				function readCategoryTreeList() {
					var treeItemList = [];
					<c:forEach items="${categorytreelist}" var="category">
					treeItemList
							.push("<c:out value="${category.category_id}" />");
					treeItemList
							.push("<c:out value="${category.category_name}" />");
					</c:forEach>

					var length = treeItemList.length;

					$("#treeLabel").empty();
					generateTree(treeItemList, "#treeLabel");
				}

				function generateTree(treeItemList, parientElementId) {
					var length = treeItemList.length;
					var childId = "";
					var category_id = "";
					var category_name = "";
					var link = "";
					var i = 0;
					var bussinessname = "<a href= '#'> ${businessProfile.business_name} </a> >";
					var serviceproduct = "${serviceProduct.service_title}";

					while (i < length) {
						childId = "child" + i;
						category_id = treeItemList[i];
						category_name = treeItemList[i + 1];
						// link = "browse.html?parent_id=" + category_id;    		 
						childElement = "<a>" + category_name + "</a> >"

						$(parientElementId).append(childElement);

						i = i + 2;
						//parientElementId = "#" + childId;
					}
					$(parientElementId).append(bussinessname);
					$(parientElementId).append(serviceproduct);
					$("#categoryLabel").empty();
					$("#categoryLabel").append("分类标签：");
					$("#categoryLabel").append($(parientElementId).html());
					console.log("categoryLabel = "+ $("#categoryLabel").html());

				}

				function initializeRatings() {
					$('#recommend_level').shieldRating({
						max : 3,
						step : 0.1,
						value : 3,
						markPreset : false
					});
					
					$('#recommend_level').swidget().enabled(false);
					var recommend_level = '${serviceProduct.recommend_level_id}';
					console.log("recommend_level = "+ recommend_level);
					 $('#recommend_level').swidget().value(recommend_level);
					
				}

				$(document).ready(function() {
					readCategoryTreeList();
					initializeRatings();
					
					$(".openEditPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ 
						location.reload();
				    }});
				    
				    $(".openViewPage").colorbox({iframe:true, width:"65%", height:"80%", onClosed:function(){ } });
				});
			</script>
</body>
</html>