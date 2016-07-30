
<%@ page import="com.gcl.Messages" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'messages.label', default: 'Messages')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<div id="list-messages" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
			<thead>
					<tr>
						<g:sortableColumn property="id" title="${message(code: 'messages.id.label', default: 'ID')}" />
						<g:sortableColumn property="expireDate" title="${message(code: 'messages.expireDate.label', default: 'Expire Date')}" />
					
						<g:sortableColumn property="orderNumber" title="${message(code: 'messages.orderNumber.label', default: 'Order Number')}" />
					
						<g:sortableColumn property="text" title="${message(code: 'messages.text.label', default: 'Text')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${messagesInstanceList}" status="i" var="messagesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${messagesInstance.id}">${fieldValue(bean: messagesInstance, field: "id")}</g:link></td>
						<td>${fieldValue(bean: messagesInstance, field: "expireDate")}</td>
						<td>${fieldValue(bean: messagesInstance, field: "orderNumber")}</td>
					
						<td>${fieldValue(bean: messagesInstance, field: "text")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${messagesInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
