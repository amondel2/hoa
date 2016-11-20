<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="basic">
		<g:set var="entityName" value="${message(code: 'bank.label', default: 'Bank')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-bank" class="content scaffold-create" role="main">
			<div class="message" role="status">Create Not Allowed</div>
		</div>
	</body>
</html>
