<%@ page import="com.gcl.Profile" %>



<div class="form-group ${hasErrors(bean: profileInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="profile.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" class="form-control" required="" value="${profileInstance?.firstName}"/>

</div>

<div class="form-group ${hasErrors(bean: profileInstance, field: 'houseNumber', 'error')} required">
	<label for="houseNumber">
		<g:message code="profile.houseNumber.label" default="House Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="houseNumber" class="form-control" required="" value="${profileInstance?.houseNumber}"/>

</div>

<div class="form-group ${hasErrors(bean: profileInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="profile.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" class="form-control" required="" value="${profileInstance?.lastName}"/>

</div>

<div class="form-group ${hasErrors(bean: profileInstance, field: 'phoneNumber', 'error')} required">
	<label for="phoneNumber">
		<g:message code="profile.phoneNumber.label" default="Phone Number" />
		<span class="required-indicator">*</span>
	</label>
	<input type="tel" id="phoneNumber" name="phoneNumber" required class="form-control" value="${profileInstance?.phoneNumber}"/>

</div>

<input type="hidden" style="display:none" id="user" name="user.id" required="" value="${user.id}" />