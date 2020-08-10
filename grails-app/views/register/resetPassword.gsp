<html>
	<head>
		<<meta name="layout" content="basic">
		<title><g:message messageCode='spring.security.ui.resetPassword.title'/></title>
	</head>
	<body>
		<h1>Reset Password</h1>
		<g:hasErrors bean="${resetPasswordCommand}">
			<ul class="errors" role="alert">
				<g:eachError bean="${resetPasswordCommand}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="resetPassword"  type='resetPassword' focus='password'>
			<fieldset class="form">
				<input type="hidden" name='token' id="token" value="${token}" />
				<h3><g:message default="description" code='spring.security.ui.resetPassword.description'/></h3>
				<div class="form-group ${hasErrors(bean: resetPasswordCommand, field: 'password', 'has-error')} required">
					<label for="password">Password <span  class="required-indicator">*</span></label>
					<input type="password" name="password" id="password" class="form-control" required="" value="" />
					
					</div>
					<div class="form-group ${hasErrors(bean: resetPasswordCommand, field: 'password2', 'has-error')} required">
					<label for="password2">Password (again) <span  class="required-indicator">*</span></label>
					<input type="password" name="password2" id="password2" class="form-control" required="" value="" />
					
					</div>
				</fieldset>
			    <fieldset class="buttons">
					<g:submitButton name="create"  class="btn btn-primary btn-sm active" value="${g.message(default:'Reset', code:'spring.security.ui.resetPassword.submit')}"/>
				</fieldset>
			</g:form>
	</body>
</html>