
<%@ page import="com.gcl.Fee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'fee.label', default: 'Fee')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fee" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
				<tbody>
			
				<g:if test="${feeInstance?.amount}">
				<tr>
         				<td><g:message code="fee.amount.label" default="Amount" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${feeInstance}" field="amount"/></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${feeInstance?.paidDate}">
				<tr>
         				<td><g:message code="fee.paidDate.label" default="Paid Date" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="paidDate-label"><g:formatDate date="${feeInstance?.paidDate}" /></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${feeInstance?.assessmentDate}">
				<tr>
         				<td><g:message code="fee.assessmentDate.label" default="Assessment Date" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="assessmentDate-label"><g:formatDate date="${feeInstance?.assessmentDate}" /></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${feeInstance?.description}">
				<tr>
         				<td><g:message code="fee.description.label" default="Description" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${feeInstance}" field="description"/></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${feeInstance?.dueDate}">
				<tr>
         				<td><g:message code="fee.dueDate.label" default="Due Date" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="dueDate-label"><g:formatDate date="${feeInstance?.dueDate}" /></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${feeInstance?.feetype}">
				<tr>
         				<td><g:message code="fee.feetype.label" default="Feetype" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="feetype-label"><g:fieldValue bean="${feeInstance}" field="feetype"/></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${feeInstance?.house}">
				<tr>
         				<td><g:message code="fee.house.label" default="House" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="house-label"><g:link controller="house" action="show" id="${feeInstance?.house?.id}">${feeInstance?.house?.encodeAsHTML()}</g:link></span>
					
				</td></tr>
				</g:if>
			
			</tbody></table>
			<g:form url="[resource:feeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="btn btn-primary btn-sm active" resource="${feeInstance}" action="edit" ><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="btn btn-secondary btn-sm active" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
