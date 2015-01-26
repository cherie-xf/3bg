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
	<form class="form" role="form">
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
													 <input type="checkbox" value="${var2.category_id}">${var2.category_name}
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
		</div>
		</form>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function selectClass(index, classid, classname) {
			parent.$("#classid" + index).val(classid);
			parent.$("#classname" + index).val(classname);
			parent.$("#classnametxt" + index).text(classname);
			//parent.$("#currentPage").val(1);
			parent.jQuery.colorbox.close();
		}
	</script>
</body>
</html>