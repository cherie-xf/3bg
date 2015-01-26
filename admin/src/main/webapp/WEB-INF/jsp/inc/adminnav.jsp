<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">3QNOW</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <!-- 
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">订单管理 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="pronmng.html">新建订单</a></li>
                <li><a href="reloadproncache.html">订单查询</a></li>
              </ul>
            </li>
             -->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">任务管理 <span class="badge" id="pendingtasks">4</span><b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<c:url value="/ticket/customermng.html"/>">普通任务</a></li>
                <li><a href="<c:url value="/ticket/suspendmng.html"/>">困难任务管理</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Ticket管理 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a class="openEditPage" href="<c:url value="/ticket/create.html"/>">创建Ticket</a></li>
                <li><a href="<c:url value="/ticket/manage.html"/>">Ticket管理</a></li>
              </ul>
            </li>
                        
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">用户管理 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<c:url value="/account/search.html"/>">用户管理</a></li>
                <li><a href="<c:url value="/membership/manage.html"/>">会员查询</a></li>
                <li><a href="<c:url value="/customerservice/manage.html"/>">客服管理</a></li>
                <li><a href="<c:url value="/initbusinessinput/manage.html"/>" target="_new">非会员服务商</a></li>
              </ul>
            </li>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">订单管理 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<c:url value="/serviceorder/manage.html"/>">订单管理</a></li>
              </ul>
            </li>
  
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">服务产品管理 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<c:url value="/serviceproduct/manage.html"/>">服务产品管理</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">内容管理 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<c:url value="/content/manage.html"/>">资讯管理</a></li>
                <li><a href="<c:url value="/blog/manage.html"/>">Blog管理</a></li>
                <li><a href="<c:url value="/media/manage.html"/>">图片管理</a></li>
              </ul>
              
            </li>
            
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">基础数据管理<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<c:url value="/category/browse.html"/>">分类标签管理</a></li>                
                <li><a href="<c:url value="/searchmissed/missedkeymng.html"/>">未命中关键字管理</a></li>
                <li><a href="<c:url value="/system/refreshDictionary.json?reload=true"/>">更新数据字典</a></li>
                <li><a href="<c:url value="/dialog/refreshCategorydialog.html"/>">更新分类列表</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <span class="glyphicon glyphicon-user"><sec:authentication property="principal.lastName" /> <sec:authentication property="principal.firstName" /></span>
                <b class="caret"></b>
              <ul class="dropdown-menu">
                <li><a href="<c:url value="/j_spring_security_logout"/>">退出系统</a></li>               
              </ul>
            </li>
          </ul>
          
	          <div class="input-group globalsearch" style="margin-top:10px;margin-left:10px;">
			      <input type="text" class="form-control input-sm">
			      <span class="input-group-btn">
				       <button class="btn btn-default btn-sm" type="button"><span class="glyphicon glyphicon-search" style="height:18px;"></span></button>
				  </span>
			  </div><!-- /input-group -->	          
        </div><!--/.nav-collapse -->
      </div>
    </div>
    <div class="container" style="height:50px">
    </div>