
<%@ page import="com.gcl.Bank" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'bank.label', default: 'Bank')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-bank" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
			<thead>
					<tr>
					
						<g:sortableColumn property="amount" title="${message(code: 'bank.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'bank.date.label', default: 'Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${bankInstanceList}" status="i" var="bankInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${bankInstance.id}">${fieldValue(bean: bankInstance, field: "amount")}</g:link></td>
					
						<td><g:formatDate date="${bankInstance.date}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${bankInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
