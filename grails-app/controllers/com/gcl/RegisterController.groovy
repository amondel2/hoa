package com.gcl

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.plugin.springsecurity.SpringSecurityService;

import org.hibernate.NonUniqueResultException

import grails.validation.Validateable

import org.grails.web.servlet.mvc.SynchronizerTokensHolder
import org.springframework.beans.factory.InitializingBean

class RegisterController implements GrailsConfigurationAware, InitializingBean {

	def registrationService
	def springSecurityService
	String serverURL

	static defaultAction = 'register'

	def register(RegisterCommand registerCommand) {

		if (!request.post) {
			return [registerCommand: new RegisterCommand()]
		}
		withForm {
			if (registerCommand.hasErrors()) {
				redirect(controller: "register", action: "register", params: [ registerCommand : registerCommand])
				return
			}

			User user =  registrationService.createUser(registerCommand)
		}.invalidToken {
			flash.message = "Invalid Form Submission"
			redirect(controller: "login", action: "auth")
		}
		redirect(controller: "register", action: "register")
	}

	def forgotPassword(ForgotPasswordCommand forgotPasswordCommand) {

		if (!request.post) {
			ForgotPasswordCommand fpc = new ForgotPasswordCommand()
			return [forgotPasswordCommand: fpc]
		}
		withForm {
			if (forgotPasswordCommand.hasErrors()) {

				return [forgotPasswordCommand: forgotPasswordCommand]
			}

			def user = findUserByUsername(forgotPasswordCommand.username)
			if (!user) {
				forgotPasswordCommand.errors.rejectValue 'username', 'spring.security.ui.forgotPassword.user.notFound'

				return [forgotPasswordCommand: forgotPasswordCommand]
			}

			if (forgotPasswordExtraValidation && forgotPasswordExtraValidation.size() > 0 && forgotPasswordExtraValidationDomainClassName ) {
				redirect uri: generateLink('securityQuestions', [username: forgotPasswordCommand.username])
			} else {
				if (requireForgotPassEmailValidation) {
					processForgotPasswordEmail(forgotPasswordCommand, user)
				} else {
					redirect uri: processForgotPasswordEmail(forgotPasswordCommand, user)
				}
			}
		}.invalidToken {
			flash.message = "Invalid Form Submission"
			redirect(controller: "login", action: "auth")
		}
	}

	def showChanallage() {
		def p = params
		withForm {			
			if(!params || !params.usernameForgot || !(params.usernameForgot?.trim().size() > 0)) {
				flash.message = "Missing UserName"
				redirect(controller: "login", action: "auth")
			} else {
				//makeSure User Exists
				def userName = params?.usernameForgot?.trim()
				def user
				try {
					user = User.withCriteria(uniqueResult: true){
						eq("username", userName, [ignoreCase: true])
					}
				} catch (NonUniqueResultException e) {
					user = User.withCriteria(uniqueResult: true){
						eq("username", userName)
					}
				} catch (Exception e) {
					flash.message = e.getMessage()
					redirect(controller: "login", action: "auth")
					return true
				}

				if(!user) {
					flash.message = "Missing UserName"
					redirect(controller: "login", action: "auth")
				} else {
					Profile prof = Profile.findByUser(user)
					if(!prof || !prof?.question1 || !prof?.answer1) {
						flash.message = "Profile Not Completed..Please contanct Site Admin for help"
						redirect(controller: "login", action: "auth")
					} else {
						render(view:"showChanallage",model:[profinstance:prof])				
					}
				}
			}
		}.invalidToken {
			flash.message = "Invalid Form Submission"
			redirect(controller: "login", action: "auth")
		}
	}

	def verifyRegistration() {

		String token = params.t


		def user = registrationService.finishRegistration(token)

		if (!user) {
			flash.error = message(code: 'spring.security.ui.register.badCode')
			redirect uri: successHandlerDefaultTargetUrl
			return
		}

		if (user.hasErrors()) {
			// expected to be handled already by ErrorsStrategy.handleValidationErrors
			return
		}
		def r = successHandlerDefaultTargetUrl
		flash.message = message(code: 'spring.security.ui.register.complete')
		springSecurityService.reauthenticate user.username
		redirect uri: "/profile/create" //+ "?autologout=true"
	}

	def resetPassword(ResetPasswordCommand resetPasswordCommand) {
		if (!request.post) {
			return [resetPasswordCommand: new ResetPasswordCommand(),token:params.token]
		}
		if (resetPasswordCommand.hasErrors()) {
			return [resetPasswordCommand: resetPasswordCommand,token: resetPasswordCommand.token ?: params.token]
		}


		if (!registrationService.updatePassword(resetPasswordCommand)) {
			resetPasswordCommand.errors.rejectValue 'password', 'spring.security.ui.forgotPassword.user.notFound'
			return [resetPasswordCommand: resetPasswordCommand, token:resetPasswordCommand.token]
		}

		flash.message = "Your Password Has Been Updated"
		redirect(action: 'auth',controller: 'login')
	}
	
	def checkChallenge() {
		Profile p
		def qa
		withForm {
			try{
			def pid = params.pid
			 p = Profile.get(pid)
			 qa = [p?.answer1,p?.answer2]
			} catch (Exception e) {
				flash.message = "Profile Not Completed..Please contact the Site Admin for help"
				redirect(controller: "login", action: "auth")
			}
			if(params.question1?.trim().toLowerCase() == qa[0]?.trim().toLowerCase() && params.question2?.trim().toLowerCase() == qa[1]?.trim().toLowerCase() ) {

				String token = registrationService.getForgotPassLink(p.user)

				redirect(action: 'resetPassword', params:[token:token])
			} else {
				def tokenurlnya = "/register/showChanallage"
				def tokensHolder = SynchronizerTokensHolder.store(session)
				flash.message = "One of More Answers Not Correct Please Correct Them."
				def model = [:]
				model[SynchronizerTokensHolder.TOKEN_KEY] = tokensHolder.generateToken(tokenurlnya)
				model[SynchronizerTokensHolder.TOKEN_URI]=tokenurlnya
				model['usernameForgot'] = p.user.username
				redirect(controller: "register", action: "showChanallage",  params:model)
			}
			
		}.invalidToken {
			flash.message = "Invalid Form Submission"
			try{
				def pid = params.pid
				p = Profile.get(pid)
				def tokenurlnya = "/register/showChanallage"
				def tokensHolder = SynchronizerTokensHolder.store(session)
				def model = [:]
				model[SynchronizerTokensHolder.TOKEN_KEY] = tokensHolder.generateToken(tokenurlnya)
				model[SynchronizerTokensHolder.TOKEN_URI]=tokenurlnya
				model['usernameForgot'] = p.user.username
				redirect(controller: "register", action: "showChanallage",  params:model)
			} catch(Exception e) {
				redirect(controller: "login", action: "auth")
			}
		}
	}

	protected static int passwordMaxLength
	protected String validationUserLookUpProperty
	protected static int passwordMinLength
	protected static String passwordValidationRegex
	protected String successHandlerDefaultTargetUrl

	void afterPropertiesSet() {
		RegisterCommand.User = User
		RegisterCommand.usernamePropertyName = "username"

		validationUserLookUpProperty = 'user'
		successHandlerDefaultTargetUrl = '/'
		passwordMaxLength =  64
		passwordMinLength =  8
		passwordValidationRegex = '^.*(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$'
	}

	static boolean checkPasswordMinLength(String password, command) {
		password && password.length() >= passwordMinLength
	}

	static boolean checkPasswordMaxLength(String password, command) {
		password && password.length() <= passwordMaxLength
	}

	static boolean checkPasswordRegex(String password, command) {
		password && password.matches(passwordValidationRegex)
	}

	static final passwordValidator = { value, command ->
		if (command.hasProperty('username') && command.username && command.username.equals(value)) {
			return 'command.password.error.username'
		}

		if (!checkPasswordMinLength(value, command) || !checkPasswordMaxLength(value, command)) {
			return ['command.password.error.length', passwordMinLength, passwordMaxLength]
		}
		if (!checkPasswordRegex(value, command)) {
			return 'command.password.error.strength'
		}
		return true
	}

	static final password2Validator = { value, command ->
		if (command.password != command.password2) {
			return 'command.password2.error.mismatch'
7		}
	}

	@Override
	void setConfiguration(Config co) {
		serverURL = co.getProperty('grails.serverURL', String)
	}
}
