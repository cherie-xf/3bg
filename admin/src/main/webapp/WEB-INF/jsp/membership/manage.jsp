<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<html>
<head>
  <title>会员查询</title>
  <%@ include file="/WEB-INF/jsp/inc/adminhead.jsp" %>
</head>
<body>
  <%@ include file="/WEB-INF/jsp/inc/adminnav.jsp" %>
   <div class="container">
     <div class="row">
       <div class="row">
	       <div class="col-md-12">
		     <h4>会员查询</h4>
		   </div>
	   </div> 
	   
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
		
	   <form:form modelAttribute="membershipQuery" method="post" action="manage.html" cssClass="form-inline" role="form">
	   <div class="row myrow">
	   <div class="col-md-12">	     
   			     <!--read only properties, generated by system-->
				<form:hidden path="pagination.rowCount"/>
				<form:hidden path="pagination.pageCount" id="pageCount"/>
				<!-- read only end -->
				<!--writable properties, control the currentpage, pagesize and order rule -->
				<form:hidden path="pagination.currentPage" id="currentPage"/>
				<form:hidden path="pagination.pageSize"/>
				<form:hidden path="orderByClause"/>
           <div class="form-group">
                <form:label path="member_id"  cssClass="sr-only">会员ID:</form:label>
                <form:input path="member_id" cssClass="form-control input-sm" placeholder="会员ID"/>
           </div>
           <div class="form-group">
                <form:label path="phone"  cssClass="sr-only">会员电话:</form:label>
                <form:input path="phone" cssClass="form-control input-sm" placeholder="会员电话"/>
           </div>            
           <div class="form-group">
                <form:label path="business_name"  cssClass="sr-only">公司名:</form:label>
                <form:input path="business_name" cssClass="form-control input-sm" placeholder="公司名"/>
           </div>         
           <div class="form-group">
                 <form:label path="type_code"  cssClass="sr-only">会员类型:</form:label>
                 <form:select path="type_code" cssClass="form-control input-sm">
                   <form:option value="">--所有类型--</form:option>
                   <form:options items="${applicationScope.memberTypeMap}"/>
                 </form:select>
           </div>
           <div class="checkbox">
    		  <form:label path="searchRecommended">
      			<form:checkbox path="searchRecommended"/> 仅显示推荐
    		  </form:label>
  		   </div>           
           <button type="submit" class="btn btn-primary btn-sm">查询</button> 
        </div>
	   </div>
	   <div class="row myrow">
		   <div class="col-md-12">
			 <table class="table table-striped table-hover">
			    <tr>
			       <th><b>会员ID</b></th>
			       <th><b>公司名</b></th>
			       <th><b>姓名</b></th>
			       <th><b>电话</b></th>
			       <th><b>会员类型</b></th>
			       <th><b>积分</b></th>
			       <th><b>生效日期</b></th>
			       <th><b>失效日期</b></th>
			       <th><b>状态</b></th>
			       <th><b>推荐</b></th>
			       <th><b>操作</b></th>
			    </tr>
			    <c:forEach items="${membershiplist}" var="var" varStatus="rowCounter">
			      <tr>  
		           <td>${var.member_id}</td>
			       <td>
			         ${var.business_name}
			       </td>
			       <td>
			         ${var.firstLastName}
			       </td>
			       <td>
			         ${var.phone}
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
						<a class="openEditPage" href="update.html?member_id=${var.member_id}">
							<span class="glyphicon glyphicon-edit text-success" title="修改"></span>
						</a>
					 	<a data-href="delete/${var.member_id}.html" data-toggle="modal" data-target="#confirm-delete" href="#" id="delete-operate" title="${var.member_id}">
							<span class="glyphicon glyphicon-trash text-danger" title="删除"></span>
						</a>
						<a href="../account/detail.html?id=${var.user_id}&message=default">查看用户</a>
					</td>			       
			      </tr>
			    </c:forEach>
			  </table> 
		   </div> 
	   </div>
	   
	   <div class="row myrow">
	     <div class="col-md-12">
	       <ul class="pagination pagination-sm" style="margin:0">
	         <li><a href="#" onclick="javascript:turnpage(-1)">&laquo;</a></li>
	         <c:forEach items="${pageNumList}" var="var" varStatus="rowCounter">
	         <li><a href="#" onclick="javascript:gotopage(${var})">${var}</a></li>
	         </c:forEach>
		     <li><a href="#" onclick="javascript:turnpage(1)">&raquo;</a></li>
			</ul>
	     </div>
	   </div>
	</form:form>	   
	
   </div><!-- row-fluid -->
	   
   </div>	
   <%@include file="/WEB-INF/jsp/inc/loadjs.jsp" %>
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
	     
	     $(document).ready(function(){
	    	 var currentpage=parseInt($("#currentPage").val());
	    	 $(".pagination li:eq("+currentpage+")").addClass("active");
	    	 
		    $(".openEditPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ 
		    	document.forms[0].submit();
		    }});
		    
		    $(".openViewPage").colorbox({iframe:true, width:"90%", height:"90%", onClosed:function(){ } });
	    	 
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