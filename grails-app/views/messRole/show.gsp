
<%@ page import="com.gcl.MessRole" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'messRole.label', default: 'MessRole')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-messRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-messRole" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
				<tbody>
			
				<g:if test="${messRoleInstance?.role}">
				<tr>
         				<td><g:message code="messRole.role.label" default="Role" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="role-label"><g:link controller="role" action="show" id="${messRoleInstance?.role?.id}">${messRoleInstance?.role?.encodeAsHTML()}</g:link></span>
					
				</td></tr>
				</g:if>
			
				<g:if test="${messRoleInstance?.mess}">
				<tr>
         				<td><g:message code="messRole.mess.label" default="Mess" />
         				</td><td>
					
						<span class="property-value" aria-labelledby="mess-label"><g:link controller="messages" action="show" id="${messRoleInstance?.mess?.id}">${messRoleInstance?.mess?.encodeAsHTML()}</g:link></span>
					
				</td></tr>
				</g:if>
			
			</tbody></table>
			<g:form url="[resource:messRoleInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="btn btn-primary btn-sm active" resource="${messRoleInstance}" action="edit" ><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="btn btn-secondary btn-sm active" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
