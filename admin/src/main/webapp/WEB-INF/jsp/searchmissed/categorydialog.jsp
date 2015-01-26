<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>分类对话框</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
<style>
.levelonediv {
	font-size: 14px;
	font-weight: bold;
	padding-bottom: 10px;
	color:#0085C3;
}

.leveltwodiv {
	font-size: 12px;
	font-weight: bold;
}

.levelthreediv {
	font-size: 12px;
	padding-bottom:5px;
}

.levelthreediv ul{
    margin:0;
    padding:5px 0;
}

.levelthreediv li{
    display: inline;
    padding:5px 5px 5px 0;
}
</style>
</head>
<body>
	<div class="container" style="min-height: 100%;">
	    <input type="hidden" id="missedid" value="${searchmissed.id}">
	    <input type="hidden" id="missedkey" value="${searchmissed.keyword}">
		<div class="col-md-12">
			<div class="row myrow">
				<c:forEach items="${displayList}" var="var" varStatus="rowCounter">
					<div class="col-md-4" style="padding-bottom:10px;">
						<div class="row">
							<div class="col-md-12 levelonediv">${var.category_name}</div>
						</div>
						<c:forEach items="${var.childrenList}" var="var1" varStatus="rowCounter1">
							<div class="row">
								<div class="col-md-12">
									<div class="leveltwodiv">${var1.category_name}</div>
									<div class="levelthreediv">
										<ul>
											<c:forEach items="${var1.childrenList}" var="var2" varStatus="rowCounter2">
												<li>
													 <input name="category_id" type="checkbox" value="${var2.category_id}" >${var2.category_name}
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			<div class="row">
			  <button type="button" id="addbutton">添加到指定分类</button>
			  <span id="resultmsg"></span>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#addbutton").click(function(){
				var missedid = $("#missedid").val();
				var missedkey = $("#missedkey").val();
				var category_id = "";
				$.each($("input:checkbox[name=category_id]:checked"),function(){
					category_id+=$(this).val()+",";
				});
				
				//console.log(missedid+" "+missedkey+" "+category_id);
				
				if(category_id.length>1){
				    var posting = $.post("../searchmissed/addkeyword.json",{missedid:missedid,missedkey:missedkey,category_id:category_id});
				    posting.done(function(data){
				    	$("#resultmsg").text(data);
					});
				}else{
					alert("请选择分类");
				}
			    $(this).hide();
			    $("#resultmsg").text(missedkey+"添加至"+category_id+"成功");
			});
		});
		
		function selectClass() {
			parent.jQuery.colorbox.close();
//		    var posting = $.post("/admin/searchmissed/addkeywordtotag.json",{missedid:missedid,missedkey:missedkey,category_id:category_id});
//		    posting.done(function(data){
//		    	console.log(data);
//			});
		}
	</script>
</body>
</html>