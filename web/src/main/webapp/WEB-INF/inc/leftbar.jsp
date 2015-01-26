<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingOne">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" id="collapseHeadOne">
                        <div class="tbsleftbarhead">我的订单</div>
                    </a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                <div class="panel-body">
                    <li><a href="<c:url value="/serviceorder/buyermanage.html"/>">购买的服务</a></li>
                    <li><a href="<c:url value="/serviceorder/suppliermanage.html"/>">卖出的服务</a></li>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingTwo">
                <h3 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo" id="collapseHeadTwo">
                        <div class="tbsleftbarhead">我的产品</div>
                    </a>
                </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
                <div class="panel-body">
                    <li><a href="<c:url value="/serviceproduct/manage.html"/>">服务产品</a></li>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingThree">
                <h4 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree" id="collapseHeadThree">
                        <div class="tbsleftbarhead">个人信息</div>
                    </a>
                </h4>
            </div>
            <div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
                <div class="panel-body">
                    <li><a href="<c:url value="/account/basic.html"/>">基本信息</a></li>
                    <li><a href="<c:url value="/account/contact.html"/>">联系方式</a></li>
                    <li><a href="<c:url value="/account/other.html"/>">其他档案</a></li>
                    <li><a href="<c:url value="/account/password.html"/>">修改密码</a></li>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingFour">
                <h4 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="true" aria-controls="collapseFour" id="collapseHeadFour">
                        <div class="tbsleftbarhead">会员信息</div>
                    </a>
                </h4>
            </div>
            <div id="collapseFour" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFour">
                <div class="panel-body">
                    <li><a href="<c:url value="/membership/manage.html"/>">会员信息</a></li>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingFive">
                <h4 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="true" aria-controls="collapseFive" id="collapseHeadFive">
                        <div class="tbsleftbarhead">Ticket管理</div>
                    </a>
                </h4>
            </div>
            <div id="collapseFive" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFive">
                <div class="panel-body">
                    <li><a href="<c:url value="/ticket/manage.html"/>">Ticket管理</a></li>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingSix">
                <h4 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="true" aria-controls="collapseFive" id="collapseHeadSix">
                        <div class="tbsleftbarhead">Blog管理</div>
                    </a>
                </h4>
            </div>
            <div id="collapseSix" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingSix">
                <div class="panel-body">
                    <li><a href="<c:url value="/blog/manage.html"/>">Blog管理</a></li>
                </div>
            </div>
        </div>
    </div>