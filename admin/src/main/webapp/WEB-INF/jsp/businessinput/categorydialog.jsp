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
	    <input type="hidden" id="member_id" value="${member_id}">
		<div class="col-sm-12">
			<div class="row myrow">
				<c:forEach items="${displayList}" var="var" varStatus="rowCounter">
					<div class="col-sm-4" style="padding-bottom:10px;">
						<div class="row">
							<div class="col-sm-12 levelonediv">${var.category_name}</div>
						</div>
						<c:forEach items="${var.childrenList}" var="var1" varStatus="rowCounter1">
							<div class="row">
								<div class="col-sm-12">
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
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
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
					alert("请选择分类");
				}else if(categoryIdArray.length > 2){
					alert("最多选择两个分类");
				}else if(categoryIdArray.length > 0 && categoryIdArray.length <= 2){
					
					parent.$("#categoryListDiv").empty();
					
					for(i=0; i<categoryIdArray.length; i++){
						var categoryId=categoryIdArray[i];
						var categoryName=categoryNameArray[i];
						
						var appendhtml1="<input id=\"category_list" + i + ".member_id\" name=\"category_list["+ i + "].member_id\" type=\"hidden\" value=\"" + member_id + "\"/>";
						var appendhtml2="<input id=\"category_list" + i + ".category_id\" name=\"category_list["+ i + "].category_id\" type=\"hidden\" value=\"" + categoryId + "\"/>";
						var appendhtml3="<div name='categorynameDiv' class=\"col-md-2 item-content\">" + categoryName + "</div>";					
											
						var appendhtml= appendhtml1 + appendhtml2 + appendhtml3;											
										
						parent.$("#categoryListDiv").append(appendhtml);
					 }
					
					parent.$("#categoryListDiv").append("<a class=\"openEditPage btn btn-default btn-sm\" href=\"choosecategory.html?member_id=" + member_id + "\">选择业务</a>");
					parent.jQuery.colorbox.close();				
					
				}		    

			});
		});
		
	</script>
</body>
</html>