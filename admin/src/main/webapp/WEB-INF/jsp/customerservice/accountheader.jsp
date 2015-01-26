 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>			
			<div class="row myrow">
				<div class="col-md-12">
					<h4>客服账号详情</h4>
				</div>
			</div>
			<div class="row myrow">
				<ul class="nav nav-tabs" role="tablist" id="accountTabList">
					<li role="presentation" id="basicTab"><a href="<c:url value="/customerservice/detail.html?id=${id}"/>">基本信息</a></li>
					<li role="presentation" id="contactTab"><a href="<c:url value="/customerservice/contact.html?id=${id}"/>">联系方式</a></li>
					<li role="presentation" id="userhistoryTab"><a href="<c:url value="/customerservice/userhistory.html?id=${id}"/>">服务日志</a></li>
					<li role="presentation" id="otherTab"><a href="<c:url value="/customerservice/other.html?id=${id}"/>">其他档案</a></li>
				</ul>			
			</div>
