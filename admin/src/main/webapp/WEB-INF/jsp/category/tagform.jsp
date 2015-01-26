<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<html>
<head>
  <title>分类同义词管理</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
<link rel="stylesheet" href="/css/themes/base/jquery.ui.all.css">
<style type="text/css">
.checkboxdiv {
	display: inline-block;
	width: 120px;
}
</style>
</head>
<body>
   <div class="container">
     <div class="row">
       <div class="row">
	       <div class="col-md-12">
		     <h4>分类同义词管理</h4>
		   </div>
	   </div> 
	   <div class="row">
	       <div class="col-md-12">
		     <h5>分类ID: "${category.category_id}"</h5>
		   </div>
	   </div>
	   <div class="row">
	       <div class="col-md-12">
		     <h5>分类名: "${category.category_name}"</h5>
		   </div>
	   </div>
	   <form:form modelAttribute="categorytag" method="post" action="createtag.html" cssClass="form-inline" role="form">
	       <form:hidden path="category_id" />
		   <div class="row myrow">
			   <div class="col-md-12">
				 <table class="table table-striped table-hover">
				    <tr>
				       <th><b>同义词</b></th>
				       <th><b>操作</b></th>
				    </tr>
				    <c:forEach items="${taglist}" var="var" varStatus="rowCounter">
				      <tr>  
			           <td>${var.tag}</td>
				       <td>
	                      <a href="deletetag.html?category_id=${category.category_id}&tag_id=${var.tag_id}">
							<span class="glyphicon glyphicon-trash text-danger" title="删除"></span>
						  </a>                      
				       </td>			       
				      </tr>
				    </c:forEach>
				  </table> 
			   </div>
		   </div>
		   
		   <div class="row myrow">
				<div class="col-md-12" style="text-align: center">
					<div class="col-md-8 item-content">
						<form:input path="tag" cssClass="form-control input-sm" placeholder="同义词" maxlength="120"
							data-validation="required" />
					</div>				
					<button type="submit" class="btn btn-success">增加同义词</button>				
				</div>
			</div>	   
	
		</form:form>	   
	
	 </div><!-- row-fluid -->
	   
   </div>	
   <%@include file="/WEB-INF/jsp/inc/loadjs.jsp" %>
   <script type="text/javascript">
     
     $(document).ready(function(){
    	 var currentpage=parseInt($("#currentPage").val());
    	 $(".pagination li:eq("+currentpage+")").addClass("active");
    	 
	    $(".openEditPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ 
	    	document.forms[0].submit();
	    }});
	    
	    $(".openViewPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ } });
	    
	    $.validate();
    	 
     });
   </script>
   
</body>
</html>