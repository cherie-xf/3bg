<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>Blog管理</title>
<!--#include file="header.shtml" -->
<%@ include file="/WEB-INF/inc/head.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css" />
<style type="text/css">
.myrow{
          margin-bottom:10px;
       }
</style>
</head>
<body>
	<div class="wrap">
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/topnav.jsp"%>
		<!--globalnav end-->
		<!--globalnav start-->
		<%@ include file="/WEB-INF/inc/globalnav.jsp"%>
		<!--globalnav end-->




		<!----------------左侧边栏---------------->
		<!--#include file="leftbar.html" -->

		<!----------------右侧边栏---------------->
		<!--<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main1">-->

		<div class="container tbscontainer">
			<div class="main">

				<div class="row">
					<div class="col-sm-2">
						<%@ include file="/WEB-INF/inc/leftbar.jsp"%>
					</div>
					<div class="col-md-10">
						<div class="modal fade" id="confirm-delete" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
									</div>

									<div class="modal-body">
										<p class="user-info"></p>
										<p>此操作将不能撤回，确定删除?</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Cancel</button>
										<a href="#" class="btn btn-danger danger openEditPage"
											id="modal-delete">Delete</a>
									</div>
								</div>
							</div>
						</div>
						<!-- form begin -->
						<form:form modelAttribute="blogQuery" method="post"
							action="/web/blog/manage.html" cssClass="form-inline" role="form">

							<div class="row">
								<div class="col-sm-12">
									<ol class="breadcrumb mybreadcrumb">
										<li class="active">我的服务宝</li>
										<li class="active">Blog管理</li>
										<li class="active">Blog管理</li>
									</ol>
								</div>
							</div>
							<div class="row myrow">
								<div class="form-group">
									<form:label path="title" cssClass="sr-only">标题:</form:label>
									<form:input path="title" cssClass="form-control input-sm"
										placeholder="标题" />
								</div>
								<div class="form-group">
									<form:label path="meta_keyword" cssClass="sr-only">关键字:</form:label>
									<form:input path="meta_keyword"
										cssClass="form-control input-sm" placeholder="关键字" />
								</div>

								<div class="form-group">
									<form:select path="blog_status"
										cssClass="form-control input-sm">
										<form:option value="">--所有状态--</form:option>
										<form:options items="${applicationScope.blogStatusMap}" />
									</form:select>
								</div>
								<div class="form-group">
									<form:select path="class_id" cssClass="form-control input-sm">
										<form:option value="">--分类--</form:option>
										<form:options items="${applicationScope.blogClassMap}" />
									</form:select>
								</div>

								<button type="submit" class="btn btn-primary btn-sm">Search</button>
								<a href="create.html"
									class="btn btn-success btn-sm openEditPage">创建blog</a>
							</div>

							<div class="row myrow">
								<table class="table table-striped table-hover">
									<tr>
										<th><b>标题</b></th>
										<th><b>作者</b></th>
										<th><b>关键字</b></th>
										<th><b>分类</b></th>
										<th><b>录入时间</b></th>
										<th><b>更新时间</b></th>
										<th><b>状态</b></th>
										<th><b>操作</b></th>
									</tr>
									<c:forEach items="${blogmnglist}" var="var"
										varStatus="rowCounter">
										<tr>
											<td>${var.title}</td>
											<td>${var.last_name}${var.first_name}</td>
											<td>${var.meta_keyword}</td>
											<td><c:set var="class_id" value="${var.class_id}" />
												${applicationScope.blogClassMap[class_id]}</td>
											<td><fmt:formatDate value="${var.created_at}"
													pattern="MM/dd/YYYY HH:mm" /></td>
											<td><fmt:formatDate value="${var.modified_at}"
													pattern="MM/dd/YYYY HH:mm" /></td>
											<td><c:set var="blog_status" value="${var.blog_status}" />
												<!-- 
												${applicationScope.blogStatusMap[blog_status]}
												--> <c:if test="${var.blog_status==10}">
													<img src="/images/icon-blog-plus-16.png" title="草稿">
												</c:if> <c:if test="${var.blog_status==20}">
													<img src="/images/icon-blog-check-16.png" title="发布">
												</c:if> <c:if test="${var.blog_status==30}">
													<img src="/images/icon-blog-sign-error-16.png" title="不发布">
												</c:if></td>
											<td><a class="openEditPage"
												href="update.html?id=${var.blog_id}"> <span
													class="glyphicon glyphicon-edit text-success" title="更新"></span>
											</a>&nbsp;&nbsp; <a data-href="delete.html?id=${var.blog_id}"
												data-toggle="modal" data-target="#confirm-delete" href="#"
												id="delete-operate" title="${var.title}"> <span
													class="glyphicon glyphicon-trash text-danger" title="删除"></span></a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</form:form>
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
	<script>
		$(document).ready(function() {

			$.validate();

			$(".openEditPage").colorbox({
				iframe : true,
				width : "80%",
				height : "80%",
				onClosed : function() {
					location.reload();
				}
			});

			$(".openViewPage").colorbox({
				iframe : true,
				width : "80%",
				height : "80%",
				onClosed : function() {
				}
			});
			
			$('#delete-operate ').click(function(){
				var user = $(this).attr('title');
				console.log("***********11"+user);
				
				$('#confirm-delete').on('show.bs.modal', function(e) {
				    $(this).find('.danger').attr('href', $(e.relatedTarget).data('href'));
				    $('.user-info').html('即将删除Blog: <strong>' +user + '</strong>');
				});
				
				$('#modal-delete ').click(function(){
					$('#confirm-delete').modal('hide');
				});
			});

		});
	</script>
</body>
</html>