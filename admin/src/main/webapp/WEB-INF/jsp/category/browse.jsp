<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>分类标签管理</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
<link href="/css/treeview.css" rel="stylesheet"/>
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
			<div class="row">
				<div class="col-md-12">
					<h4>分类标签管理</h4>
				</div>
			</div>
		   <div class="row myrow">
		     <div class="col-sm-12">
			   <ul class="nav nav-tabs mytab">
				  <li role="presentation" class="active"><a href="browse.html">浏览</a></li>		  
				  <li role="presentation"><a href="search.html">查询</a></li>		  
				</ul>
			 </div>
			</div>
			<form:form modelAttribute="categoryQuery" method="post"
				action="browse.html" cssClass="form-inline" role="form">
				
				<div class="row myrow">
						<ul class="tree">
							<li id="treeroot"></li>
						</ul>
				</div>

				<div class="row myrow">
					<div class="col-md-12">
						<table class="table table-striped table-hover">
							<tr>
								<th><b>分类ID</b></th>
								<th><b>分类名</b></th>
								<th><b>叶节点</b></th>
								<th><b>启用</b></th>
								<th><b>优先级</b></th>
								<th><b>操作</b></th>
							</tr>
							<c:forEach items="${categorymnglist}" var="var"
								varStatus="rowCounter">
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
									<td>${var.leaf.booleanValue() == true? "是" : "否"}</td>
									<td>${var.enable.booleanValue() == true? "是" : "否"}</td>
									<td>${var.priority}</td>
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
				
				<div class="row myrow">
					<div class="col-md-12">
						<form:hidden path="orderByClause" />
						<a id="creationlink" href="create.html" class="btn btn-success btn-sm openEditPage">新建分类</a>
					</div>
				</div>
				
			</form:form>

		</div>
		<!-- row-fluid -->

	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script type="text/javascript">

     function readCategoryTreeList(){
    	 var treeItemList = [];
    	 <c:forEach items="${categorytreelist}" var="category">    	   
    	 	treeItemList.push("<c:out value="${category.category_id}" />");
    	 	treeItemList.push("<c:out value="${category.category_name}" />");    	  
    	 </c:forEach>
    	 
    	 var length = treeItemList.length;
    	 if(length >=2){
    		 var creatLink = "create.html?parent_id=" + treeItemList[length-2];
    		 $("a[id='creationlink']").attr('href', creatLink);
    	 }   	 
    	 
    	 $("#treeroot").empty();
    	 
    	 $("#treeroot").append("<a href=\"browse.html\">Root</a>");
    	 
    	 generateTree(treeItemList, "#treeroot")
    }
     
     function generateTree(treeItemList, parientElementId){
    	 var length = treeItemList.length;
    	 var childId = "";
    	 var category_id = "";
    	 var category_name = "";
    	 var link = "";
    	 var i = 0;
    	 while (i < length) {    		 
    		 childId = "child" + i;
    		 category_id = treeItemList[i];
    		 category_name = treeItemList[i+1];
    		 link = "browse.html?parent_id=" + category_id;    		 
    		 childElement = "<ul><li id=\"" + childId +"\">" + "<a href=\"" + link + "\">" + category_name + "</a></li></ul>"
   		    
    		$(parientElementId).append(childElement);
    		 
    		i = i+2;    		
    		parientElementId = "#" + childId;
   		}    	     	 
     }
     
     $(document).ready(function(){    	 
	    $(".openEditPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ 
	    	location.reload();
	    }});
	    
	    $(".openViewPage").colorbox({iframe:true, width:"80%", height:"90%", onClosed:function(){ } });
	    
	    readCategoryTreeList();	    
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