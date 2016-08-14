<%@ page import="com.gcl.fin.House" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'house.label', default: 'House')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-house" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
				<tbody>
			
				<g:if test="${houseInstance?.fees}">
				<tr>
         				<td><g:message code="house.fees.label" default="Fees" />
         				</td><td>
					
						<g:each in="${houseInstance.fees}" var="f">
						<span class="property-value" aria-labelledby="fees-label"><g:link controller="fee" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</td></tr>
				</g:if>
			
			
				<g:if test="${houseInstance?.type}">
				<tr>
         				<td><g:message code="house.type.label" default="Type" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${houseInstance}" field="type"/></span>
					
				</td></tr>
				</g:if>
				<g:if test="${houseInstance}">
				<tr>
         				<td><g:message code="house.type.amountOwed" default="AmountOwed" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="type-label">${houseInstance.calculateAmountOwed()}</span>
					
				</td></tr>
				</g:if>
			
			</tbody></table>
			<g:form url="[resource:houseInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="btn btn-primary btn-sm active" resource="${houseInstance}" action="edit" ><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="btn btn-secondary btn-sm active" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
