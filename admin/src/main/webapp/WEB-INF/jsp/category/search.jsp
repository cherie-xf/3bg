<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<html>
<head>
  <title>分类标签管理</title>
  <%@ include file="/WEB-INF/jsp/inc/adminhead.jsp" %>
</head>
<body>
  <%@ include file="/WEB-INF/jsp/inc/adminnav.jsp" %>
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
       <div class="row">
	       <div class="col-md-12">
		     <h4>分类标签管理</h4>
		   </div>
	   </div>
	   <div class="row myrow">
	     <div class="col-sm-12">
		   <ul class="nav nav-tabs mytab">
			  <li role="presentation"><a href="browse.html">浏览</a></li>		  
			  <li role="presentation" class="active"><a href="search.html">查询</a></li>		  
			</ul>
		 </div>
		</div>
   		<form:form modelAttribute="categoryQuery" method="post" action="search.html" cssClass="form-inline" role="form">
		   <div class="row myrow">
			   <div class="col-md-12">		     
		   			    <form:hidden path="orderByClause"/>
		           <div class="form-group">
		                <form:label path="category_id"  cssClass="sr-only">分类ID:</form:label>
		                <form:input path="category_id" cssClass="form-control input-sm" placeholder="分类ID"/>
		           </div>
		           <div class="form-group">
		                <form:label path="category_name"  cssClass="sr-only">分类名称:</form:label>
		                <form:input path="category_name" cssClass="form-control input-sm" placeholder="分类名称"/>
		           </div>         
		           <button type="submit" class="btn btn-primary btn-sm">查询</button>	           
			    </div>
		   </div>
		   <div class="row myrow">
			   <div class="col-md-12">
				 <table class="table table-striped table-hover">
				    <tr>
				       <th><b>分类ID</b></th>
				       <th><b>分类名</b></th>
				       <th><b>子节点</b></th>
				       <th><b>启用</b></th>
				       <th><b>优先级</b></th>
				       <th><b>操作</b></th>
				    </tr>
				    <c:forEach items="${categorymnglist}" var="var" varStatus="rowCounter">
				      <tr>  
			           <td>${var.category_id}</td>
				       <td>
							<c:if test="${var.leaf.booleanValue()}">
								${var.category_name}
							</c:if>
							<c:if test="${not var.leaf.booleanValue()}">
								<a href="browse.html?parent_id=${var.category_id}">${var.category_name}</a>
							</c:if>
				       </td>
				       <td>
				         ${var.leaf.booleanValue() == true? "是" : "否"}
				       </td>
				       <td>
				         ${var.enable.booleanValue() == true? "是" : "否"}
				       </td>
				       <td>
				         ${var.priority}
				       </td>	       
				       <td>
							<a class="openEditPage" href="update.html?category_id=${var.category_id}">
								<span class="glyphicon glyphicon-edit text-success" title="修改"></span>
							</a>
							<a data-href="delete/${var.category_id}.html" data-toggle="modal" data-target="#confirm-delete" href="#" id="delete-operate" title="${var.category_id}">
								<span class="glyphicon glyphicon-trash text-danger" title="删除"></span>
							</a>
	                      	<a class="openEditPage" href="tagmanage.html?category_id=${var.category_id}">同义词</a>
				       </td>			       
				      </tr>
				    </c:forEach>
				  </table> 
			   </div>
		   </div>	   

		</form:form>
	
   </div><!-- row-fluid -->
	   
   </div>	
   <%@include file="/WEB-INF/jsp/inc/loadjs.jsp" %>
   <script type="text/javascript">
     
     $(document).ready(function(){   	 
	    $(".openEditPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ 
	    	document.forms[0].submit();
	    }});
	    
	    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });
    	 
     });
     
		$('#delete-operate ').click(function(){
			var user = $(this).attr('title');			
			
			$('#confirm-delete').on('show.bs.modal', function(e) {
			    $(this).find('.danger').attr('href', $(e.relatedTarget).data('href'));
			    $('.user-info').html('即将删除ID为: <strong>' +user + '</strong> 的分类。 删除后需要联系管理员才能恢复数据，您确定删除吗?');
			});
			
			$('#modal-delete ').click(function(){
				$('#confirm-delete').modal('hide');
			});
			
		});
   </script>
   
</body>
</html>