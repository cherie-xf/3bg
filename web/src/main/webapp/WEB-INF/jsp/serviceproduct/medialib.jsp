<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>插入图片</title>
<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
<link href="/css/themes/base/jquery.ui.all.css" rel="stylesheet" />
<link href="/css/lightbox.css" rel="stylesheet">
<style>
.piclist {
	margin: 0;
	padding: 0;
}

.picitem {
	list-style-type: none;
	margin-bottom: 15px;
	height:170px;
}

.itemtitle {
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="row">
				<div class="col-md-12">
					<h4>插入图片</h4>
				</div>
			</div>
			<form:form modelAttribute="mediaQuery" method="post"
				action="medialib.html" cssClass="form-inline" role="form">
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
							<form:label path="title" cssClass="sr-only">标题:</form:label>
							<form:input path="title" cssClass="form-control input-sm"
								placeholder="标题" />
						</div>
						<div class="form-group">
							<form:label path="beginDate" cssClass="sr-only">上传时间从:</form:label>
							<form:input path="beginDate" cssClass="form-control input-sm"
								id="datepicker1" placeholder="上传时间从" />
						</div>
						<div class="form-group">
							<form:label path="endDate" cssClass="sr-only">上传时间到:</form:label>
							<form:input path="endDate" cssClass="form-control input-sm"
								id="datepicker2" placeholder="上传时间到" />
						</div>

						<button type="submit" class="btn btn-primary btn-sm">Search</button>
						<a href="../media/create.html" class="btn btn-success btn-sm openEditPage">上传图片</a>
						<button type="button" class="btn btn-success btn-sm" onclick="insertMultipleImgtoList()">插入选定图片</button>
					</div>
				</div>
				<div class="row myrow"></div>
				<div class="row myrow">
				  <div class="col-sm-12">
					<ul class="piclist">
						<c:forEach items="${mediamnglist}" var="var" varStatus="rowCounter">
							<li class="col-md-2 picitem">
								<div class="row">
									<a href="${var.bigpic}" rel="lightbox[prf]"
										title="${var.title}"><img src="${var.thumb}" width="150" height="115"></a>
								</div>
								<div class="row">
									<div class="checkbox">
										<label> 
										    <input type="radio" value="${var.media_id}" name="onlyone" class="mediacheckbox" src="${var.thumb}" title="${var.title}"> <b>${var.title}</b> 
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;<a class="openEditPage" href="../media/update.html?media_id=${var.media_id}"><span class="glyphicon glyphicon-pencil"></span></a>    
									</div>
								</div>
								<div class="row">
									<fmt:formatDate value="${var.created_at}"
										pattern="YYYY/MM/dd HH:mm" />
								</div>
							</li>
						</c:forEach>
					</ul>
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
				
				<div class="row">
				  <button type="button" class="btn btn-success btn-sm" onclick="insertMultipleImgtoList()">插入选定图片</button>
				</div>
			</form:form>


		</div>
		<!-- row-fluid -->

	</div>

	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script src="/js/ui/jquery.ui.core.js"></script>
	<script src="/js/ui/jquery.ui.widget.js"></script>
	<script src="/js/ui/jquery.ui.datepicker.js"></script>
	<script src="/js/lightbox-2.6.min.js"></script>
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
     
   //图片插入到imglist中
  function insertMultipleImgtoList(){
	 parent.$("#insertHtml2").empty();
	 parent.$("#insertHtml2").css('display','none');
	 parent.$("#cover_img").prop("value", "");
	 var imglist = $(".mediacheckbox");
	 for(i=0;i<imglist.size();i++){
		var img=imglist[i];
		if($(img).prop("checked")){
		   var imgpath = $(img).prop("src");
		   var imgtitle = $(img).prop("title");
		   var appendhtml = "<img src=\""+imgpath+"\" class=\"img-responsive\" title=\""+imgtitle+"\" height=\"150\" width=\"150\">";
		   parent.$("#insertHtml2").append(appendhtml);
		   parent.$("#cover_img").prop("value", imgpath);
		   parent.$("#insertHtml2").css('display','block');
		}
	 }
	 parent.jQuery.colorbox.close();
  }   
     
     
     function deleteImages(){
	     var deleteimgcount=0;
	     var imglist = $(".mediacheckbox");
		 for(i=0;i<imglist.size();i++){
			var img=imglist[i];
			if($(img).prop("checked")){
			   var imgid = $(img).prop("value");
			   deleteImg(imgid);
			   deleteimgcount++;
			}
		}
		if(deleteimgcount>0){
			 location.reload();
		}
	}
	
	function deleteImg(id){
	    var posting = $.post("delete.html",{media_id:id});
	    posting.done(function(data){     
		});
	} 
     
     $(document).ready(function(){ 
    	 
    	 $("#datepicker1").datepicker(); 
    	 $("#datepicker2").datepicker(); 
    	 var currentpage=parseInt($("#currentPage").val());
    	 $(".pagination li:eq("+currentpage+")").addClass("active");
    	 
	    $(".openEditPage").colorbox({iframe:true, width:"70%", height:"90%", onClosed:function(){ 
	    	location.reload();
	    }});
	    
	    $(".openViewPage").colorbox({iframe:true, width:"70%", height:"90%", onClosed:function(){ } });
    	 
     });
   </script>

</body>
</html>