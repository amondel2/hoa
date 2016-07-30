
<%@ page import="com.gcl.MessRole" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'messRole.label', default: 'MessRole')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-messRole" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class = "table table-striped">
			<thead>
					<tr>
					
						<th><g:message code="messRole.role.label" default="Role" /></th>
					
						<th><g:message code="messRole.mess.label" default="Mess" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${messRoleInstanceList}" status="i" var="messRoleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${messRoleInstance.id}">${fieldValue(bean: messRoleInstance, field: "role")}</g:link></td>
					
						<td>${fieldValue(bean: messRoleInstance, field: "mess")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${messRoleInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
