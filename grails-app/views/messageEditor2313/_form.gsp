<%@ page import="com.gcl.Messages" %>





<div class="form-group fieldcontain ${hasErrors(bean: messagesInstance, field: 'expireDate', 'has-error')} ">
	<label for="expireDate">
		<g:message code="messages.expireDate.label" default="Expire Date" />
		
	</label>
	<g:datePicker name="expireDate" precision="hour" years="[2016, 2017, 2018, 2019, 2020]"  value="${messagesInstance?.expireDate}" default="${new Date()}"  />

</div>

<div class="form-group fieldcontain ${hasErrors(bean: messagesInstance, field: 'orderNumber', 'has-error')} required">
	<label for="orderNumber">
		<g:message code="messages.orderNumber.label" default="Order Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field class="form-control"  name="orderNumber" type="number" value="${messagesInstance?.orderNumber}" required=""/>

</div>

<div class="form-group fieldcontain ${hasErrors(bean: messagesInstance, field: 'text', 'has-error')} required">
	<label for="text">
		<g:message code="messages.text.label" default="Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="text" required="" value="${messagesInstance?.text}"/>

</div>

