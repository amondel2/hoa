
<%@ page import="com.gcl.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<div id="show-profile" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
				<tbody>
				<g:if test="${profileInstance?.firstName}">
					<tr>
         				<td><g:message code="profile.firstName.label" default="First Name" /></td>
         				<td><g:fieldValue bean="${profileInstance}" field="firstName"/></td>
         			</tr>
				</g:if>
			
				
			
				<g:if test="${profileInstance?.lastName}">
					<tr>
         				<td><g:message code="profile.lastName.label" default="Last Name" /></td>
         				<td><g:fieldValue bean="${profileInstance}" field="lastName"/></td>
         			</tr>
				</g:if>
			
			
				<g:if test="${profileInstance?.phoneNumber}">
					<tr>
         				<td><g:message code="profile.phoneNumber.label" default="Phone Number" /></td>
         				<td><g:fieldValue bean="${profileInstance}" field="phoneNumber"/></td>
         			</tr>
				</g:if>
			
			</tbody></table>
			<g:form url="[resource:profileInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="btn btn-primary btn-sm active" action="edit"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="btn btn-secondary btn-sm active" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
