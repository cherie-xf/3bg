<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top tbsheader">
    <div class="col-md-12" style="border-top:5px solid #0766a8;"></div>
    <div class="col-md-12" style="background-color:#eeeeee;text-align:right">
        <div class="container">
           <div class="row">
             <div class="col-md-12">
				<sec:authorize access="isAnonymous()">
		            <ul class="topright">
		              <li><a href="<c:url value="/signup.flow"/>" class="topright">注册</a></li>
		              <li><a href="<c:url value="/login.html"/>" class="topright">登录</a></li>
		            </ul>			
		        </sec:authorize>
				<sec:authorize access="isAuthenticated()">
				   <ul class="topright">
				      <span class="greeting">Hi <sec:authentication property="principal.firstName" /></span>
		              <li style="width:100px;"><a href="<c:url value="/account/basic.html"/>">我的服务宝</a></li>
		              <li><a href="<c:url value="/j_spring_security_logout"/>">退出</a></li>
		            </ul>
				</sec:authorize>	            
             </div>
           </div>
        </div>
    </div>
    <div class="container">
        <div class="col-md-3 tbslogo">
            <a href="<c:url value="/index.html"/>"><img src="/images/logo.png" class="img-responsive" style="width:150px"></a>
        </div>
        <div class="col-md-6 topsearch">
            <div> 
                <div class="input-group tbssearch">
                  <input type="text" class="form-control" placeholder="输入店铺名称、关键字">
                  <span class="input-group-btn">
                    <input class="btn btn-default" type="submit" value="搜 索">
                    </span>
                </div><!-- /input-group -->
            </div>
            <div>
                &nbsp;&nbsp;<a href="#" class="tbssearchword">房地产商</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="tbssearchword">美容美发</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="tbssearchword">大学直通车</a>
            </div>
        </div>
        <div class="col-md-3 tbstele">
            <div class="row">
                <div class="col-md-12 tbs-telelphone-call">服务热线电话</div>
                <div class="col-md-12 tbs-telelphone"><span class="glyphicon glyphicon-phone-alt" aria-hidden="true" style="font-size:23px;left:-10px;color:#ed8f5b"></span>604-998-1889</div>
            </div>
        </div>
    </div>
    <div style="background-color:#414040;">
        <div class="container">
            <div class="navbar-header">
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>        
                <div class="tbshiddenname">三桥服务</div>
            </div>

            <div class="collapse navbar-collapse js-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown dropdown-large">
                        
                        <a href="<c:url value="/index.html"/>" class="tbs-nav-blue tbs-nav-home"><span class="glyphicon glyphicon-home" aria-hidden="true" style="font-size:17px;line-height: 28px;color:#ffffff"></span></a>
                    </li>
                    <c:set var="displayList" value="${applicationScope.globalnav}"/>
                    <c:forEach items="${displayList}" var="var" varStatus="rowCounter">
                    <li class="dropdown dropdown-large">
                        <a href="#" class="dropdown-toggle ${var.cssclass}" data-toggle="dropdown">${var.category_name}</a>
                        <ul class="dropdown-menu dropdown-menu-large row ${var.cssclass}">
                             <c:set var="subList" value="${var.childrenList}"/>
                             <c:forEach items="${subList}" var="var2" varStatus="rowCounter2">
                             <li class="col-sm-2">
                                <ul>
                                    <li class="dropdown-header">${var2.category_name}</li>
                                    <c:set var="endList" value="${var2.childrenList}"/>
                                    <c:forEach items="${endList}" var="var3" varStatus="rowCounter3">
                                    <li><a href="#">${var3.category_name}</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                            </c:forEach>
                        </ul>
                    </li>
				</c:forEach>




                </ul>
            </div>
        </div><!-- /.nav-collapse -->
    </div>
</nav>