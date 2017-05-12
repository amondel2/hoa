<%@ page import="com.gcl.Fee" %>



<div class="form-group fieldcontain ${hasErrors(bean: feeInstance, field: 'amount', 'has-error')} required">
	<label for="amount">
		<g:message code="fee.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field class="form-control"  name="amount" value="${fieldValue(bean: feeInstance, field: 'amount')}" required=""/>

</div>

<div class="form-group fieldcontain ${hasErrors(bean: feeInstance, field: 'paidDate', 'has-error')} ">
	<label for="paidDate">
		<g:message code="fee.paidDate.label" default="Paid Date" />
		
	</label>
	<g:datePicker name="paidDate" precision="day"  value="${feeInstance?.paidDate}" default="none" noSelection="['': '']" />

</div>

<div class="form-group fieldcontain ${hasErrors(bean: feeInstance, field: 'assessmentDate', 'has-error')} required">
	<label for="assessmentDate">
		<g:message code="fee.assessmentDate.label" default="Assessment Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="assessmentDate" precision="day"  value="${feeInstance?.assessmentDate}"  />

</div>

<div class="form-group fieldcontain ${hasErrors(bean: feeInstance, field: 'description', 'has-error')} required">
	<label for="description">
		<g:message code="fee.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="description" required="" value="${feeInstance?.description}"/>

</div>

<div class="form-group fieldcontain ${hasErrors(bean: feeInstance, field: 'dueDate', 'has-error')} required">
	<label for="dueDate">
		<g:message code="fee.dueDate.label" default="Due Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dueDate" precision="day"  value="${feeInstance?.dueDate}"  />

</div>

<div class="form-group fieldcontain ${hasErrors(bean: feeInstance, field: 'feetype', 'has-error')} required">
	<label for="feetype">
		<g:message code="fee.feetype.label" default="Feetype" />
		<span class="required-indicator">*</span>
	</label>
	<g:select class="form-control" name="feetype" from="${com.gcl.FeeType?.values()}" keys="${com.gcl.FeeType.values()*.name()}" required="" value="${feeInstance?.feetype?.name()}" />

</div>

<div class="form-group fieldcontain ${hasErrors(bean: feeInstance, field: 'house', 'has-error')} required">
	<label for="house">
		<g:message code="fee.house.label" default="House" />
		<span class="required-indicator">*</span>
	</label>
	<g:select class="form-control"  id="house" name="house.id" from="${com.gcl.House.list()}" optionKey="id" required="" value="${feeInstance?.house?.id}" class="many-to-one"/>

</div>

