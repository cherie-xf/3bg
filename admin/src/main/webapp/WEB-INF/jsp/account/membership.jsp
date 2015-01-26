<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>用户账户管理</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
<link href="/css/treeview.css" rel="stylesheet" />
</head>
<body>

	<%@ include file="/WEB-INF/jsp/inc/adminnav.jsp"%>
	<div class="container">
	
	   <div class="modal fade" id="confirm-delete" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">确认删除</h4>
					</div>

					<div class="modal-body">
						<p class="user-info"></p>
						<p></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">取消</button>
						<a href="#" class="btn btn-danger danger openEditPage" id="modal-delete">删除</a>
					</div>
				</div>
			</div>
		</div>	
	
		<div class="row">
			<%@ include file="/WEB-INF/jsp/account/accountheader.jsp"%>
			<div class="row myrow">
				<div class="col-md-2"></div>
				<div class="col-md-10">
					<c:if test="${not empty account.feedbackMessage}">
						<div class="alert alert-success">${account.feedbackMessage}</div>
					</c:if>
				</div>
			</div>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="accout">
					<a id="creationlink" href="../membership/create.html?user_id=${id}"
								class="btn btn-success btn-sm openEditPage">新建会员</a>
					<h4>消费者会员</h4>
					<div class="tablebox">
						<table class="table table-striped table-hover">
						    <tr>
						       <th><b>会员ID</b></th>
						       <th><b>会员类型</b></th>
						       <th><b>积分</b></th>
						       <th><b>生效日期</b></th>
						       <th><b>失效日期</b></th>
						       <th><b>状态</b></th>
						       <th><b>操作</b></th>
						    </tr>
						    <c:forEach items="${cmembershiplist}" var="var" varStatus="rowCounter">
						      <tr>  
					           <td>${var.member_id}</td>
						       <td>
						         <c:set var="type_code" value="${var.type_code}"/>
			         			 ${applicationScope.memberTypeMap[type_code]}
						       </td>
						       <td>
						         ${var.credit}
						       </td>
						       <td>
						         <fmt:formatDate value="${var.valid_from}" pattern="MM/dd/YYYY"/>
						       </td>
						       <td>
						         <fmt:formatDate value="${var.valid_to}" pattern="MM/dd/YYYY"/>
						       </td>
						       <td>
						           	<c:set var="status" value="${var.status}" /> <c:if
										test="${var.status==0}">
										<img src="/images/icon-fail.png" alt="未激活">
									</c:if> <c:if test="${var.status==1}">
										<img src="/images/icon-ok.png" alt="激活">
									</c:if> <c:if test="${var.status==2}">
										<img src="/images/lock.png" alt="锁死">
									</c:if>
					           </td>
						       <td>
									<a class="openEditPage" href="../membership/update.html?member_id=${var.member_id}">
										<span class="glyphicon glyphicon-edit text-success" title="修改"></span>
									</a>
									<a data-href="../membership/delete/${var.member_id}.html" data-toggle="modal" data-target="#confirm-delete" href="#" id="delete-operate" title="${var.member_id}">
										<span class="glyphicon glyphicon-trash text-danger" title="删除"></span>
									</a>								 	
								</td>			       
						      </tr>
						    </c:forEach>
						 </table>					
					</div>
					<h4>供应商会员</h4>
					<div class="tablebox">
						<table class="table table-striped table-hover">
						    <tr>
						       <th><b>会员ID</b></th>
						       <th><b>公司名</b></th>
						       <th><b>业主姓名</b></th>
						       <th><b>会员类型</b></th>
						       <th><b>积分</b></th>
						       <th><b>生效日期</b></th>
						       <th><b>失效日期</b></th>
						       <th><b>状态</b></th>
						       <th><b>推荐</b></th>
						       <th><b>操作</b></th>
						    </tr>
						    <c:forEach items="${bmembershiplist}" var="var" varStatus="rowCounter">
						      <tr>  
					           <td>${var.member_id}</td>
						       <td>
						         ${var.business_name}
						       </td>
						       <td>
						         ${var.owner_name}
						       </td>
						       <td>
						         <c:set var="type_code" value="${var.type_code}"/>
			         			 ${applicationScope.memberTypeMap[type_code]}
						       </td>
						       <td>
						         ${var.credit}
						       </td>
						       <td>
						         <fmt:formatDate value="${var.valid_from}" pattern="MM/dd/YYYY"/>
						       </td>
						       <td>
						         <fmt:formatDate value="${var.valid_to}" pattern="MM/dd/YYYY"/>
						       </td>
						       <td>
						           	<c:set var="status" value="${var.status}" /> <c:if
										test="${var.status==0}">
										<img src="/images/icon-fail.png" alt="未激活">
									</c:if> <c:if test="${var.status==1}">
										<img src="/images/icon-ok.png" alt="激活">
									</c:if> <c:if test="${var.status==2}">
										<img src="/images/lock.png" alt="锁死">
									</c:if>
					           </td>
						       <td>
						       		<c:choose> 
										<c:when test="${var.isRecommended == true}"> 
											是
										</c:when> 
										<c:when test="${var.isRecommended == false}"> 
											否 
										</c:when>
										<c:otherwise> 								 
										</c:otherwise>
									</c:choose>			       
						       </td>
						       <td>
									<a class="openEditPage" href="../membership/update.html?member_id=${var.member_id}">
										<span class="glyphicon glyphicon-edit text-success" title="修改"></span>
									</a>
									<a data-href="../membership/delete/${var.member_id}.html" data-toggle="modal" data-target="#confirm-delete" href="#" id="delete-operate" title="${var.member_id}">
										<span class="glyphicon glyphicon-trash text-danger" title="删除"></span>
									</a>								 	
								</td>			       
						      </tr>
						    </c:forEach>
						  </table> 
					</div>
					<h4>内部会员</h4>
					<div class="tablebox">
						<table class="table table-striped table-hover">
						    <tr>
						       <th><b>会员ID</b></th>
						       <th><b>会员类型</b></th>
						       <th><b>积分</b></th>
						       <th><b>生效日期</b></th>
						       <th><b>失效日期</b></th>
						       <th><b>状态</b></th>
						       <th><b>操作</b></th>
						    </tr>
						    <c:forEach items="${smembershiplist}" var="var" varStatus="rowCounter">
						      <tr>  
					           <td>${var.member_id}</td>
						       <td>
						         <c:set var="type_code" value="${var.type_code}"/>
			         			 ${applicationScope.memberTypeMap[type_code]}
						       </td>
						       <td>
						         ${var.credit}
						       </td>
						       <td>
						         <fmt:formatDate value="${var.valid_from}" pattern="MM/dd/YYYY"/>
						       </td>
						       <td>
						         <fmt:formatDate value="${var.valid_to}" pattern="MM/dd/YYYY"/>
						       </td>
						       <td>
						           	<c:set var="status" value="${var.status}" /> <c:if
										test="${var.status==0}">
										<img src="/images/icon-fail.png" alt="未激活">
									</c:if> <c:if test="${var.status==1}">
										<img src="/images/icon-ok.png" alt="激活">
									</c:if> <c:if test="${var.status==2}">
										<img src="/images/lock.png" alt="锁死">
									</c:if>
					           </td>
						       <td>
									<a class="openEditPage" href="../membership/update.html?member_id=${var.member_id}">
										<span class="glyphicon glyphicon-edit text-success" title="修改"></span>
									</a>
									<a data-href="../membership/delete/${var.member_id}.html" data-toggle="modal" data-target="#confirm-delete" href="#" id="delete-operate" title="${var.member_id}">
										<span class="glyphicon glyphicon-trash text-danger" title="删除"></span>
									</a>
								</td>			       
						      </tr>
						    </c:forEach>
						 </table>					
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#accountTabList #membershipTab").addClass("active");
			
			$(".openEditPage").colorbox({
				iframe : true,
				width : "90%",
				height : "90%",
				onClosed : function() {
					location.reload();
				}
			});

			$(".openViewPage").colorbox({
				iframe : true,
				width : "90%",
				height : "90%",
				onClosed : function() {
				}
			});

		});
		
		$('#delete-operate ').click(function(){
			var user = $(this).attr('title');			
			
			$('#confirm-delete').on('show.bs.modal', function(e) {
			    $(this).find('.danger').attr('href', $(e.relatedTarget).data('href'));
			    $('.user-info').html('即将删除ID为: <strong>' +user + '</strong> 的会员。 删除后需要联系管理员才能恢复数据，您确定删除吗?');
			});
			
			$('#modal-delete ').click(function(){
				$('#confirm-delete').modal('hide');
			});
			
		});
	</script>
</body>
</html>
