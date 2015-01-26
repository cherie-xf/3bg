<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<html>
<head>
<title>用户账户管理</title>
<%@ include file="/WEB-INF/jsp/inc/adminhead.jsp"%>
<link href="/css/treeview.css" rel="stylesheet" />
</head>
<body>

	<%@ include file="/WEB-INF/jsp/inc/adminnav.jsp"%>
	<div class="container">
		<div class="row">
			<div class="row">
				<div class="col-md-12">
					<h4>客服账号详情</h4>
				</div>
			</div>
			<ul class="nav nav-tabs" role="tablist" id="myTab">
				<li role="presentation"><a href="basic.html?id=${id}">基本信息</a></li>
				<li role="presentation"><a href="membership.html?id=${id}">会员账号</a></li>
				<li role="presentation"><a href="contact.html?id=${id}">联系方式</a></li>
				<li role="presentation"><a href="ticket.html?id=${id}">Ticket</a></li>
				<li role="presentation"><a href="order.html?id=${id}">购买订单</a></li>
				<li role="presentation" class="active"><a href="servicelog.html?id=${id}">服务日志</a></li>
				<li role="presentation"><a href="other.html?id=${id}">其他档案</a></li>
			</ul>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="log">
					<form class="form-horizontal " role="form">
						<div class="form-zoom">
							<h4>新增日志：</h4>
							<div class="form-group group-1">
								<!--------------------------------富文本域--------------->
								<div id="divEditor11"></div>
								<textarea id="txtCode" rows="5" cols="50" style="width: 100%"></textarea>

								<!-- ******************************* script ********************************* -->
								<script type="text/javascript">
									//test
									$(function() {
										$('#divEditor11').wangEditor({
											codeTargetId : 'txtCode', //将源码存储到txtCode
											frameHeight : '300px', //默认值为“300px”
											//要显示在编辑器中的html源码（用于编辑）
											initStr : '<p>请输入文字...</p>'
										});
									});
								</script>
								<!--------------------------------富文本域--------------->
							</div>
						</div>
						<div class="form-group" id="queding">
							<button type="button" class="btn btn-default">增加</button>
						</div>
					</form>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">历史日志</h4>
						</div>
						<div class="panel-body">
							<div class="logitem">
								<p class="logtitle">
									<span class="logdate">2014/10/23</span><span class="logeditor">记录人：客服10</span>
								</p>
								<p>Mr. Lahmu's new big work constitutes a controlled
									adventurous technology that awakens the obvious dazzling
									joke/uncompromising composition. This work, at once foulmouthed
									and outlandish, that has as a central theme the algorithmic
									technology-based cybernetic oeuvre of space/time, represented
									here as a casual synthesis of physics and folk art.</p>
							</div>
							<div class="logitem">
								<p class="logtitle">
									<span class="logdate">2014/10/23</span><span class="logeditor">记录人：客服10</span>
								</p>
								<p>Mr. Lahmu's new big work constitutes a controlled
									adventurous technology that awakens the obvious dazzling
									joke/uncompromising composition. This work, at once foulmouthed
									and outlandish, that has as a central theme the algorithmic
									technology-based cybernetic oeuvre of space/time, represented
									here as a casual synthesis of physics and folk art.</p>
							</div>
							<div class="logitem">
								<p class="logtitle">
									<span class="logdate">2014/10/23</span><span class="logeditor">记录人：客服10</span>
								</p>
								<p>Mr. Lahmu's new big work constitutes a controlled
									adventurous technology that awakens the obvious dazzling
									joke/uncompromising composition. This work, at once foulmouthed
									and outlandish, that has as a central theme the algorithmic
									technology-based cybernetic oeuvre of space/time, represented
									here as a casual synthesis of physics and folk art.</p>
							</div>
							<div class="logitem">
								<p class="logtitle">
									<span class="logdate">2014/10/23</span><span class="logeditor">记录人：客服10</span>
								</p>
								<p>Mr. Lahmu's new big work constitutes a controlled
									adventurous technology that awakens the obvious dazzling
									joke/uncompromising composition. This work, at once foulmouthed
									and outlandish, that has as a central theme the algorithmic
									technology-based cybernetic oeuvre of space/time, represented
									here as a casual synthesis of physics and folk art.</p>
							</div>
							<div class="logitem">
								<p class="logtitle">
									<span class="logdate">2014/10/23</span><span class="logeditor">记录人：客服10</span>
								</p>
								<p>Mr. Lahmu's new big work constitutes a controlled
									adventurous technology that awakens the obvious dazzling
									joke/uncompromising composition. This work, at once foulmouthed
									and outlandish, that has as a central theme the algorithmic
									technology-based cybernetic oeuvre of space/time, represented
									here as a casual synthesis of physics and folk art.</p>
							</div>
							<div class="logitem">
								<p class="logtitle">
									<span class="logdate">2014/10/23</span><span class="logeditor">记录人：客服10</span>
								</p>
								<p>Mr. Lahmu's new big work constitutes a controlled
									adventurous technology that awakens the obvious dazzling
									joke/uncompromising composition. This work, at once foulmouthed
									and outlandish, that has as a central theme the algorithmic
									technology-based cybernetic oeuvre of space/time, represented
									here as a casual synthesis of physics and folk art.</p>
							</div>
							<nav class="fr mr">
								<ul class="pagination">
									<li><a href="#">上一页</a></li>
									<li class="active"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">下一页</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/inc/loadjs.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".openEditPage").colorbox({
				iframe : true,
				width : "60%",
				height : "60%",
				onClosed : function() {
					document.forms[0].submit();
				}
			});

			$(".openViewPage").colorbox({
				iframe : true,
				width : "60%",
				height : "60%",
				onClosed : function() {
				}
			});

		});
	</script>
</body>
</html>
