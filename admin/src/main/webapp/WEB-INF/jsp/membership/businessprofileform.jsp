<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp"%>

				<div class="row myrow">
					<div class="col-md-12">
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.website" cssClass="col-md-2 control-label">公司网址：</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.website" cssClass="form-control input-sm" placeholder="公司网址" maxlength="255" 
										data-validation="url" data-validation-optional="true"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.category_list" cssClass="col-md-2 control-label">业务范围<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content" id="categoryListDiv">
										<c:forEach items="${membership.businessProfile.category_list}" var="var" varStatus="rowCounter">
											<form:hidden path="businessProfile.category_list[${rowCounter.index}].member_id"/>
											<form:hidden path="businessProfile.category_list[${rowCounter.index}].category_id"/>
											<div name='categorynameDiv' class="col-md-2 item-content">
												<c:out value="${var.category_name}"/>
											</div>
										</c:forEach>
										<a class="openViewPage btn btn-default btn-sm" href="choosecategory.html?member_id=${membership.businessProfile.member_id}">选择业务</a>
										<div class="" id="alertMessageDiv"></div>
								</div>								
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.business_name" cssClass="col-md-2 control-label">公司名称（英文）<font color="red">*</font>：</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.business_name" cssClass="form-control input-sm" placeholder="公司名称（英文）" maxlength="255"
										data-validation="required" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.owner_name" cssClass="col-md-2 control-label">联系人姓名:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.owner_name" cssClass="form-control input-sm" placeholder="联系人姓名" maxlength="45" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.description" cssClass="col-md-2 control-label">公司描述:</form:label>
								<div class="col-md-8 item-content">
									<form:textarea path="businessProfile.description" cssClass="form-control input-sm editbox" placeholder="公司描述" rows="6" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.phone" cssClass="col-md-2 control-label">手机号<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.phone" cssClass="form-control input-sm" placeholder="手机号" data-validation-allowing="int" data-validation="number" maxlength="15" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.email" cssClass="col-md-2 control-label">电子邮箱<font color="red">*</font>:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.email" cssClass="form-control input-sm" placeholder="电子邮箱" maxlength="255"
										data-validation="email" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.address" cssClass="col-md-2 control-label">公司地址:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.address" cssClass="form-control input-sm" placeholder="公司地址" maxlength="255" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.wechat" cssClass="col-md-2 control-label">微信号:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.wechat" cssClass="form-control input-sm" placeholder="微信号" maxlength="45" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.postcode" cssClass="col-md-2 control-label">邮编:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.postcode" cssClass="form-control input-sm" placeholder="邮编" maxlength="45" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.is_person" cssClass="col-md-2 control-label">Business类型:</form:label>
								<div class="col-md-8 item-content">
									<form:label path="businessProfile.is_person" class="radio-inline">
									  <form:radiobutton path="businessProfile.is_person" name="inlineRadioOptions" id="inlineRadio1" value="true"/> 个人
									</form:label>
									<form:label path="businessProfile.is_person" class="radio-inline">
									  <form:radiobutton path="businessProfile.is_person" name="inlineRadioOptions" id="inlineRadio1" value="false"/> 企业
									</form:label>								
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.business_start" cssClass="col-md-2 control-label">Business始于:</form:label>
								<div class="col-md-8 item-content">
									<fmt:formatDate value="${businessProfile.business_start}" var="businessStartString" pattern="MM/dd/YYYY"/>
									<form:input path="businessProfile.business_start" value = "${businessStartString}" cssClass="form-control input-sm" id="datepicker3" placeholder="Business始于" 
										data-validation="date" data-validation-format="MM/dd/YYYY" data-validation-optional="true"/>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.business_number" cssClass="col-md-2 control-label">资格证号/BN:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.business_number" cssClass="form-control input-sm" placeholder="资格证号/BN" maxlength="120" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.tax_number" cssClass="col-md-2 control-label">税号:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.tax_number" cssClass="form-control input-sm" placeholder="税号" maxlength="120" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.wcb" cssClass="col-md-2 control-label">WCB号:</form:label>
								<div class="col-md-8 item-content">
									<form:input path="businessProfile.wcb" cssClass="form-control input-sm" placeholder="WCB号" maxlength="120" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<div class="col-md-2"></div>
								<div class="col-md-8 item-content">
									<form:label path="businessProfile.quick_respond" class="checkbox-inline">
  										<form:checkbox path="businessProfile.quick_respond"/> 2小时快速反馈
									</form:label>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.referal_info" cssClass="col-md-2 control-label">推荐人信息:</form:label>
								<div class="col-md-8 item-content">
									<form:textarea path="businessProfile.referal_info" cssClass="form-control input-sm" placeholder="推荐人信息" rows="6" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.contract_date" cssClass="col-md-2 control-label">签约日期:</form:label>
								<div class="col-md-8 item-content">
									<fmt:formatDate value="${businessProfile.contract_date}" var="contract_dateString" pattern="MM/dd/YYYY"/>
									<form:input path="businessProfile.contract_date" value = "${contract_dateString}" cssClass="form-control input-sm" id="datepicker4" placeholder="签约日期" 
										data-validation-optional="true" data-validation="date" data-validation-format="MM/dd/YYYY" />
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.support_doc" cssClass="col-md-2 control-label">支持文档:</form:label>
								<c:if test="${membership.businessProfile.support_doc_name != null}">
									<div class="col-md-4 item-content">
										<a href="${membership.businessProfile.support_doc}">${membership.businessProfile.support_doc_name}</a>									
									</div>
								</c:if>
								<div class="col-md-4 item-content">
									<input type="file" name="businessProfile.support_doc_file" id="fileBrowser"/> 
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<div class="col-md-2"></div>
								<div class="col-md-8 item-content">
									<form:label path="businessProfile.publication" class="checkbox-inline">
  										<form:checkbox path="businessProfile.publication"/> 发布服务商信息（Busines Number,税号, WCB等不会发布）
									</form:label>
								</div>
							</div>
						</div>
						<div class="row myrow">
							<div class="col-md-12">
								<form:label path="businessProfile.recommend_level_id" cssClass="col-md-2 control-label">推荐级别:</form:label>
								<div class="col-md-8 item-content">
									<form:select path="businessProfile.recommend_level_id" cssClass="form-control input-sm" maxlength="120" >
					                   <form:options items="${applicationScope.recommendLevelMap}"/>
					                 </form:select>
								</div>
							</div>
						</div>
<!-- 						To support inserting image, add below div.						 -->
						<div class="row">
					        <div class="col-sm-12">
					            <div id="insertHtml" style="display:none"></div>
					            <div id="insertValue" style="display:none"></div>
					        </div>		
						</div>
												
					</div>
				</div>

