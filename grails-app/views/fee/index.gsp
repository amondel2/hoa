
<%@ page import="com.gcl.Fee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'fee.label', default: 'Fee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fee" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
			<thead>
					<tr>
					
						<g:sortableColumn property="amount" title="${message(code: 'fee.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="paidDate" title="${message(code: 'fee.paidDate.label', default: 'Paid Date')}" />
						
						<g:sortableColumn property="house.address1" title="${message(code: 'fee.paidDate.label', default: 'House Number')}" />
					
						<g:sortableColumn property="assessmentDate" title="${message(code: 'fee.assessmentDate.label', default: 'Assessment Date')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'fee.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="dueDate" title="${message(code: 'fee.dueDate.label', default: 'Due Date')}" />
					
						<g:sortableColumn property="feetype" title="${message(code: 'fee.feetype.label', default: 'Feetype')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${feeInstanceList}" status="i" var="feeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${feeInstance.id}">${fieldValue(bean: feeInstance, field: "amount")}</g:link></td>
					
						<td><g:formatDate date="${feeInstance.paidDate}" /></td>
						
						<td> ${fieldValue(bean: feeInstance.house, field: "address1")}</td>
					
						<td><g:formatDate date="${feeInstance.assessmentDate}" /></td>
					
						<td>${fieldValue(bean: feeInstance, field: "description")}</td>
					
						<td><g:formatDate date="${feeInstance.dueDate}" /></td>
					
						<td>${fieldValue(bean: feeInstance, field: "feetype")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${feeInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
