
<%@ page import="com.gcl.Bank" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'bank.label', default: 'Bank')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-bank" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
				<tbody>
			
				<g:if test="${bankInstance?.amount}">
				<tr>
         				<td><g:message code="bank.amount.label" default="Amount" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${bankInstance}" field="amount"/></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${bankInstance?.date}">
				<tr>
         				<td><g:message code="bank.date.label" default="Date" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${bankInstance?.date}" type='date' /></span>
					
				</td></tr>
				</g:if>
			
			</tbody></table>
			<g:form action = "edit" id="${bankInstance?.id}" controller='bank' method="GET">
				<g:hiddenField name="version" value="${bankInstance?.version}" />
				<fieldset class="buttons">
					<g:link class="btn btn-primary btn-sm active" id="${bankInstance.id}" action="edit" ><g:message code="default.button.edit.label" default="Edit" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
