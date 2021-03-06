<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>评价</title>
<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css">
<link rel="stylesheet" type="text/css" href="http://www.shieldui.com/shared/components/latest/css/shieldui-all.min.css" />
<link rel="stylesheet" type="text/css" href="http://www.shieldui.com/shared/components/latest/css/light/all.min.css" />
<style type="text/css">
.noPointClass {pointer-events: none;}
</style>
</head>
<body>
	<div class="container">
		<!-- Contact Information -->

		<div class="row">
			<div class="col-sm-12">
			
				
<%-- 				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-10">
						<c:if test="${not empty orderRate.feedbackMessage}">
							<div class="alert alert-success">${orderRate.feedbackMessage}</div>
						</c:if>
					</div>
				</div> --%>
				<c:if test="${orderRate.operate=='add'}">
					<c:set var="actionurl" value="createorderrate.html"></c:set>
				</c:if>						
	
				<form:form id="myform" modelAttribute="orderRate" method="post"
					action="${actionurl}" cssClass="form-horizontal" role="form"
					enctype="multipart/form-data">
					<form:hidden path="created_by" />
					<form:hidden path="operate" />
					<form:hidden path="order_id" />
					<form:hidden path="attitude" />
					<form:hidden path="time" />
					<form:hidden path="price" />
					<form:hidden path="quality" />
					
					<div class="row">
						<div class="col-sm-12">
							<h4>
								<c:if test="${orderRate.operate=='add'}">
								新建评价
							   	</c:if>
							   	<c:if test="${orderRate.operate=='view'}">
								查看评价
							   	</c:if>						
							</h4>
						</div>
					</div>
								
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="time" class="col-md-2 control-label">反应时间：</label>
								<div class="col-sm-4">
									<c:if test="${orderRate.operate=='view'}">
										<div class="rating noPointClass" id="rateTime"></div>
									</c:if>
									<c:if test="${orderRate.operate=='add'}">
										<div class="rating" id="rateTime"></div>
									</c:if>	
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="quality" class="col-md-2 control-label">服务质量：</label>
								<div class="col-md-4">
									<c:if test="${orderRate.operate=='view'}">
										<div class="rating noPointClass" id="rateQuality"></div>
									</c:if>
									<c:if test="${orderRate.operate=='add'}">
										<div class="rating" id="rateQuality"></div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="attitude" class="col-md-2 control-label">服务态度：</label>
								<div class="col-md-4">
									<c:if test="${orderRate.operate=='view'}">
										<div class="rating noPointClass" id="rateAttitude"></div>
									</c:if>
									<c:if test="${orderRate.operate=='add'}">
										<div class="rating" id="rateAttitude"></div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="price" class="col-md-2 control-label">性价比：</label>
								<div class="col-md-4">
									<c:if test="${orderRate.operate=='view'}">
										<div class="rating noPointClass" id="ratePrice"></div>
									</c:if>
									<c:if test="${orderRate.operate=='add'}">
										<div class="rating" id="ratePrice"></div>
									</c:if>
								</div>
							</div>
						</div>
					</div>						
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="comment" class="col-md-2 control-label">评语</label>
								<div class="col-md-6">
									<form:textarea path="comment" cssClass="form-control input-sm" placeholder="评语" rows="6" readonly="${orderRate.operate=='view'}" />	
								</div>
							</div>
						</div>
					</div>									
					<div class="row">
						<div class="col-md-12" style="text-align: center">
							<c:if test="${orderRate.operate=='add'}">
								<button type="submit" class="btn btn-success">新建</button>
								<button type="reset" class="btn btn-warning">重填</button>
							</c:if>							
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<!-- contact over -->

	</div>
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script src="/js/ui/jquery.ui.timepicker.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/tinymce/tinymceinit.js"></script>
	<script type="text/javascript" src="http://www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {			
			$(".openEditPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ 
	 	    	document.forms[0].submit();
	 	    }});
	 	    
	 	    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });
	 	    
	 	   initializeRatings();
		});	

	    function initializeRatings() {
	        $('#rateTime').shieldRating({
	            max: 5,
	            step: 0.1,
	            value: $('#time').val(),
	            markPreset: false
	        });
	        $('#rateQuality').shieldRating({
	            max: 5,
	            step: 0.1,
	            value: $('#quality').val(),
	            markPreset: false
	        });
	        $('#ratePrice').shieldRating({
	            max: 5,
	            step: 0.1,
	            value: $('#price').val(),
	            markPreset: false
	        });
	        $('#rateAttitude').shieldRating({
	            max: 5,
	            step: 0.1,
	            value: $('#attitude').val(),
	            markPreset: false
	        });
	    }
	    
	    $("form").submit(function(e) {	    	
	    	var first1 = $('#rateTime div:first-child');
	    	var maxLength = first1[0].offsetWidth;	    	
	    	var first11 = first1[1];
	    	var firstLength = first11.offsetWidth;
	    	var val1 = firstLength / maxLength * 5;	    	
	    	$("#time").val(val1);
	    	
	    	var first1 = $('#rateQuality div:first-child');
	    	var maxLength = first1[0].offsetWidth;	    	
	    	var first11 = first1[1];
	    	var firstLength = first11.offsetWidth;
	    	var val1 = firstLength / maxLength * 5;	    	
	    	$("#quality").val(val1);
	    	
	    	var first1 = $('#rateAttitude div:first-child');
	    	var maxLength = first1[0].offsetWidth;	    	
	    	var first11 = first1[1];
	    	var firstLength = first11.offsetWidth;
	    	var val1 = firstLength / maxLength * 5;	    	
	    	$("#attitude").val(val1);
	    	
	    	var first1 = $('#ratePrice div:first-child');
	    	var maxLength = first1[0].offsetWidth;	    	
	    	var first11 = first1[1];
	    	var firstLength = first11.offsetWidth;
	    	var val1 = firstLength / maxLength * 5;	    	
	    	$("#price").val(val1);    		    	
	    		    	
	    });
	</script>
</body>
</html>