<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>未命中关键字管理</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/inc/adminnav.jsp"%>
	<div class="container">
		<div class="row">

			<div class="row">
				<div class="col-md-12">
					<h4>未命中关键字管理</h4>
				</div>
			</div>


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
			
			<form:form modelAttribute="missedkeyQuery" method="post"
				action="missedkeymng.html" cssClass="form-inline" role="form">
				<div class="row myrow">
					<div class="col-md-12">

						<!--read only properties, generated by system-->
						<form:hidden path="pagination.rowCount" />
						<form:hidden path="pagination.pageCount" id="pageCount" />
						<!-- read only end -->
						<!--writable properties, control the currentpage, pagesize and order rule -->
						<form:hidden path="pagination.currentPage" id="currentPage" />
						<form:hidden path="pagination.pageSize" />
						<form:hidden path="orderByClause" />
						<div class="form-group">
							<form:label path="keyword" cssClass="sr-only">关键字:</form:label>
							<form:input path="keyword" cssClass="form-control input-sm"
								placeholder="关键字" />
						</div>
						<button type="submit" class="btn btn-primary btn-sm">Search</button>
					</div>
				</div>
				<div class="row myrow">
					<div class="col-md-12">
						<table class="table table-striped table-hover">
							<tr>
								<th><b>关键字</b></th>

								<th><b>搜索录入人</b></th>
								<th><b>搜索录入IP</b></th>
								<th><b>搜索时间</b></th>
								<th><b>操作</b></th>

							</tr>
							<c:forEach items="${missedkeymnglist}" var="var"
								varStatus="rowCounter">
								<tr>
									<td>${var.keyword}</td>
									<td><a href="../account/detail.html?id=${var.user_id}"
										target="../account/detail">${var.user_id}</a></td>
									<td>${var.user_ip_address}</td>
									<td><fmt:formatDate value="${var.searched_at}"
											pattern="MM/dd/YYYY HH:mm" /></td>
									<td><a class="openEditPage"
										href="addkeywordtotag.html?id=${var.id}"> <span
											class="glyphicon glyphicon-edit text-success" title="添加到同义词表"></span>
									</a> &nbsp;&nbsp; &nbsp;&nbsp; 
									<!--
									<a href="delete.html?id=${var.id}"
										onclick="addModal()"><span
											class="glyphicon glyphicon-trash text-danger" title="删除"></span></a>-->
									<a data-href="delete.html?id=${var.id}"
										data-toggle="modal" data-target="#confirm-delete" href="#"
										id="delete-operate" title="${var.keyword}"  >
							    	<span class="glyphicon glyphicon-trash text-danger" title="删除"></span></a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>

				<div class="row myrow">
					<div class="col-md-12">
						<ul class="pagination pagination-sm" style="margin: 0">
							<li><a href="#" onclick="javascript:turnpage(-1)">&laquo;</a></li>
							<c:forEach items="${pageNumList}" var="var"
								varStatus="rowCounter">
								<li><a href="#" onclick="javascript:gotopage(${var})">${var}</a></li>
							</c:forEach>
							<li><a href="#" onclick="javascript:turnpage(1)">&raquo;</a></li>
						</ul>
					</div>
				</div>
			</form:form>

			<!-- row-fluid -->
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script type="text/javascript">
     function gotopage(pagenum){
    	 $("#currentPage").val(pagenum);
    	 document.forms[0].submit();
     }
     function turnpage(offset){
    	 var currentpage=parseInt($("#currentPage").val());
    	 var pagecount = parseInt($("#pageCount").val());
    	 currentpage += offset;
    	 $("#currentPage").val(currentpage);
    	 if(currentpage>0&&currentpage<=pagecount){
    	   document.forms[0].submit();
    	 }
     }
     
     function archiveUser(userid){
    	 if(confirm("删除后，只有系统管理员可以恢复. 确认删除吗?")){
    		 $.colorbox({iframe:true,fixed:true, href:"archiveUser.html?userid="+userid, width:"50%", height:"50%", onClosed:function(){ 
    			 document.forms[0].submit();
    		 }});
    	 }
     }     
     
     function addModal()
   	 {
   		 $('form').before('<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"><p>update success!</p></div></div> </div> </div>' );
   		 $('#confirm-delete').modal('show');
   	 }
     
     $(document).ready(function(){
    	 var currentpage=parseInt($("#currentPage").val());
    	 $(".pagination li:eq("+currentpage+")").addClass("active");
    	 
	    $(".openEditPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ 
	    	location.reload();
	    }});
	    
	    $(".openViewPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ } });
	    
	    $('#delete-operate ').click(function(){
			var user = $(this).attr('title');
			console.log("***********11"+user);
			
			$('#confirm-delete').on('show.bs.modal', function(e) {
			    $(this).find('.danger').attr('href', $(e.relatedTarget).data('href'));
			    $('.user-info').html('即将删除关键字: <strong>' +user + '</strong>');
			});
			
			$('#modal-delete ').click(function(){
				$('#confirm-delete').modal('hide');
			});
		});
    	 
     });
    
   </script>

</body>
</html>