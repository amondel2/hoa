
<%@ page import="com.gcl.Messages" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'messages.label', default: 'Messages')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-messages" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
				<tbody>
				<g:if test="${messagesInstance?.id}">
				<tr>
         				<td><g:message code="messages.id.label" default="ID" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="id-label">${messagesInstance?.id}</span>
					
				</td></tr>
				</g:if>
				
				<g:if test="${messagesInstance?.expireDate}">
				<tr>
         				<td><g:message code="messages.expireDate.label" default="Expire Date" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="expireDate-label"><g:formatDate date="${messagesInstance?.expireDate}" /></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${messagesInstance?.orderNumber}">
				<tr>
         				<td><g:message code="messages.orderNumber.label" default="Order Number" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="orderNumber-label"><g:fieldValue bean="${messagesInstance}" field="orderNumber"/></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${messagesInstance?.text}">
				<tr>
         				<td><g:message code="messages.text.label" default="Text" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="text-label"><g:fieldValue bean="${messagesInstance}" field="text"/></span>
					
				</td></tr>
				</g:if>
			
			</tbody></table>
			<g:form action = "delete" id="${messagesInstance?.id}" controller='messageEditor' method="DELETE">
				<g:hiddenField name="version" value="${messagesInstance?.version}" />
				<fieldset class="buttons">
					<g:link class="btn btn-primary btn-sm active" id="${messagesInstance.id}" action="edit" ><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="btn btn-secondary btn-sm active" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
