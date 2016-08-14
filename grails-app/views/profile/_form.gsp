<%@ page import="com.gcl.Profile" %>



<div class="form-group ${hasErrors(bean: profileInstance, field: 'firstName', 'has-error')} required">
	<label for="firstName">
		<g:message code="profile.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" class="form-control" required="" value="${profileInstance?.firstName}"/>

</div>

<div class="form-group ${hasErrors(bean: profileInstance, field: 'lastName', 'has-error')} required">
	<label for="lastName">
		<g:message code="profile.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" class="form-control" required="" value="${profileInstance?.lastName}"/>

</div>

<div class="form-group ${hasErrors(bean: profileInstance, field: 'phoneNumber', 'has-error')} required">
	<label for="phoneNumber">
		<g:message code="profile.phoneNumber.label" default="Phone Number" />
	</label>
	<input type="tel" id="phoneNumber" name="phoneNumber" class="form-control" value="${profileInstance?.phoneNumber}"/>

</div>

<div class="form-group ${hasErrors(bean: profileInstance, field: 'question1', 'has-error')} required">
	<label for="question1">
		<g:message code="profile.question1.label" default="Forgot Password Question 1" />
		<span class="required-indicator">*</span>
	</label>
	<input type="text" id="question1" name="question1" required class="form-control" value="${profileInstance?.question1}"/>

</div>
<div class="form-group ${hasErrors(bean: profileInstance, field: 'answer1', 'has-error')} required">
	<label for="answer1">
		<g:message code="profile.answer1.label" default="Answer For Question 1" />
		<span class="required-indicator">*</span>
	</label>
	<input type="text" id="answer1" name="answer1" required class="form-control" value="${profileInstance?.answer1}"/>
</div>
<div class="form-group ${hasErrors(bean: profileInstance, field: 'question2', 'has-error')} required">
	<label for="question2">
		<g:message code="profile.question2.label" default="Forgot Password Question 2" />
		<span class="required-indicator">*</span>
	</label>
	<input type="text" id="question2" name="question2" required class="form-control" value="${profileInstance?.question2}"/>

</div>
<div class="form-group ${hasErrors(bean: profileInstance, field: 'answer2', 'has-error')} required">
	<label for="answer2">
		<g:message code="profile.answer2.label" default="Answer For Question 2" />
		<span class="required-indicator">*</span>
	</label>
	<input type="text" id="answer2" name="answer2" required class="form-control" value="${profileInstance?.answer2}"/>
</div>

<div class="form-group">
	<label for="House">
		Select a House
	</label>
	<g:select name="home" from="${hl}" value="${profileInstance.homeId}" optionKey="id" />
</div>



<input type="hidden" style="display:none" id="user" name="user.id" required="" value="${user.id}" />