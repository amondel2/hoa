<html>
	<head>
		<meta name="layout" content="basic"/>
		<asset:stylesheet src='spring-security-ui-auth.css'/>
	</head>
	<body>
		<p/>
		<g:if test='${flash.message}'>
			<div class='login_message'>${flash.message}</div>
		</g:if>
			<h1>Login</h1>
	<form action="/login/authenticate" method="post" name="loginForm" id="loginForm" autocomplete="off">
			<fieldset class="form">
					<div class="form-group required">
						<label for="username">Username<span  class="required-indicator">*</span></label>
						<input type="text" name="username" id="username" class="form-control" required="${username}"/>
					</div>
					<div class="form-group required"><label for="password">Password <span  class="required-indicator">*</span></label>
						<input type="password" name="password" id="password" class="form-control" required=""/>
					</div>
			</fieldset>
			<fieldset class="buttons">
						
							<span class="forgot-link">
								<a href="#" id="forgot-linkhref"><g:message code='spring.security.ui.login.forgotPassword'/></a>
							</span>
							<a href="/register/register" id="register" controller="register">Register as new User</a>
							<input id="loginButton_submit" class="btn btn-primary btn-sm active" type="submit" value="<g:message code='spring.security.ui.login.login'/>" /> 
						
				</fieldset>
			</form>
			<div style="display:none">
				<g:form controller='register' action='showChanallage' useToken="true" name='forgotFrm'>
					<input type="hidden" name="usernameForgot" id="usernameForgot" value=""/>
				</g:form>
			</div>

		<script type="text/javascript">
			$(document).ready(function(){
				$("#forgot-linkhref").on("click",function(e){
					e.preventDefault();
					$("#usernameForgot").val($("#username").val());
					$("#forgotFrm").submit();
				});
			});
		</script>
	</body>
</html>
