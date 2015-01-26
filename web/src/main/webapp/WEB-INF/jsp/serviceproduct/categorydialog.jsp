<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglib.jsp"%>
<html>
<head>
<title>分类对话框</title>
<%@ include file="/WEB-INF/inc/simplehead.jsp"%>
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
	    <input type="hidden" id="member_id" value="${member_id}">
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
													 <input name="category_id" type="checkbox" value="${var2.category_id}" alt="${var2.category_name}"/>${var2.category_name}
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
			  <button type="button" id="addbutton">确定</button>
			  <span id="resultmsg"></span>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/inc/loadjs.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#addbutton").click(function(){				
				var member_id = $("#member_id").val();
				var categoryIdArray = [];
				
				$.each($("input:checkbox[name=category_id]:checked"),function(){
					categoryIdArray.push($(this).val());
				});
				
				var categoryNameArray = [];				
				$.each($("input:checkbox[name=category_id]:checked"),function(){
					categoryNameArray.push($(this).prop("alt"));
				});			
				
				if(categoryIdArray.length == 0){
					parent.$("#category_id").val("");
					parent.$("#category_name").val("");
					parent.jQuery.colorbox.close();					
				}else if(categoryIdArray.length > 1){
					alert("最多选择一个分类");
				}else if(categoryIdArray.length == 1){					
					var categoryId=categoryIdArray[0];
					var categoryName=categoryNameArray[0];						
					parent.$("#category_id").val(categoryId);
					parent.$("#category_name").val(categoryName);
					parent.jQuery.colorbox.close();					
				}
			});
		});
		
	</script>
</body>
</html>