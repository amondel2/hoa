package com.gcl

import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegisterCommand;
import grails.plugin.springsecurity.ui.RegistrationCode

class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
	
	@Override
	def register(RegisterCommand registerCommand) {

		if (!request.post) {
			return [registerCommand: new RegisterCommand()]
		}

		if (registerCommand.hasErrors()) {
			return [registerCommand: registerCommand]
		}

		def user = uiRegistrationCodeStrategy.createUser(registerCommand)
		String salt = saltSource instanceof NullSaltSource ? null : registerCommand.username
		RegistrationCode registrationCode = uiRegistrationCodeStrategy.register(user, registerCommand.password, salt)

		if (registrationCode == null || registrationCode.hasErrors()) {
			// null means problem creating the user
			flash.error = message(code: 'spring.security.ui.register.miscError')
			return [registerCommand: registerCommand]
		}
		
		String url = generateLink('verifyRegistration', [t: registrationCode.token])
		
				def body = registerEmailBody
				if (body.contains('$')) {
					body = evaluate(body, [user: user, url: url])
				}

		//sendVerifyRegistrationMail registrationCode, user, registerCommand.email
		redirect(url: url)
		//[emailSent: true, registerCommand: registerCommand,registrationCode:registrationCode,url:url]
	}

}