<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>困难任务管理</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/inc/adminnav.jsp"%>
	<div class="container">
		<div class="row">
			<!-- row-fluid -->
			<div class="row">
				<div class="col-md-12">
					<h4>困难任务管理</h4>
				</div>
			</div>

			<div class="row myrow">
				<div class="col-sm-12">
					<ul class="nav nav-tabs mytab">
						<li role="presentation" class="active"><a
							href="suspendmng.html">主管任务池</a></li>
						<li role="presentation"><a href="normalmng.html">普通任务池</a></li>
					</ul>
				</div>
			</div>

			<div class="row myrow">
				<div class="col-md-12">
					<table class="table table-striped table-hover">
						<tr>
							<th><b>Ticet标题</b></th>
							<th><b>客户姓名</b></th>
							<th><b>联系电话</b></th>
							<th><b>录入时间</b></th>
							<th><b>更新时间</b></th>
							<th><b>状态</b></th>
							<th><b>客服</b></th>
							<th><b>操作</b></th>
						</tr>
						<c:forEach items="${ticketmnglist}" var="var"
							varStatus="rowCounter">
							<tr>
								<td>${var.title}</td>
								<td>${var.last_name} ${var.first_name}</td>
								<td>${var.phone}</td>
								<td><fmt:formatDate value="${var.created_at}"
										pattern="MM/dd/YYYY HH:mm" /></td>
								<td><fmt:formatDate value="${var.modified_at}"
										pattern="MM/dd/YYYY HH:mm" /></td>
								<td><c:set var="status_code" value="${var.status_code}" />
									${applicationScope.ticketStatusMap[status_code]}</td>
								<td>${var.processor_id}</td>
								<td><a class="openEditPage "
									href="operate.html?id=${var.id}&userLevel=super">
									<span class="glyphicon glyphicon-edit text-success" title="处理"></span>
									</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>


		</div>
		<!-- row-fluid -->

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
     
     
     $(document).ready(function(){
    	 var currentpage=parseInt($("#currentPage").val());
    	 $(".pagination li:eq("+currentpage+")").addClass("active");
    	 
	    $(".openEditPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ 
	    	location.reload();
	    }});
	    
	    $(".openViewPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ } });
    	 
     });
   </script>

</body>
</html>

</body>
</html>