<%@ page import="com.gcl.MessRole" %>



<div class="form-group fieldcontain ${hasErrors(bean: messRoleInstance, field: 'role', 'has-error')} required">
	<label for="role">
		<g:message code="messRole.role.label" default="Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:select class="form-control"  id="role" name="role.id" from="${com.gcl.Role.list()}" optionKey="id" required="" value="${messRoleInstance?.role?.id}" class="many-to-one"/>

</div>

<div class="form-group fieldcontain ${hasErrors(bean: messRoleInstance, field: 'mess', 'has-error')} required">
	<label for="mess">
		<g:message code="messRole.mess.label" default="Mess" />
		<span class="required-indicator">*</span>
	</label>
	<g:select class="form-control"  id="mess" name="mess.id" from="${com.gcl.Messages.list()}" optionKey="id" required="" value="${messRoleInstance?.mess?.id}" class="many-to-one"/>

</div>

