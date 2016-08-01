package com.gcl

import grails.plugin.springsecurity.SpringSecurityService;
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegisterCommand;
import grails.plugin.springsecurity.ui.RegistrationCode

import org.hibernate.NonUniqueResultException

import grails.validation.Validateable

import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder

class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {

	def registrationService
	def springSecurityService
	
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
		redirect(url: url)
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

	@Override
	def verifyRegistration() {

		String token = params.t

		RegistrationCode registrationCode = token ? RegistrationCode.findByToken(token) : null
		if (!registrationCode) {
			flash.error = message(code: 'spring.security.ui.register.badCode')
			redirect uri: successHandlerDefaultTargetUrl
			return
		}

		def user = uiRegistrationCodeStrategy.finishRegistration(registrationCode)

		if (!user) {
			flash.error = message(code: 'spring.security.ui.register.badCode')
			redirect uri: successHandlerDefaultTargetUrl
			return
		}

		if (user.hasErrors()) {
			// expected to be handled already by ErrorsStrategy.handleValidationErrors
			return
		}
		def r = registerPostRegisterUrl ?: successHandlerDefaultTargetUrl
		flash.message = message(code: 'spring.security.ui.register.complete')
		springSecurityService.reauthenticate user.username
		redirect uri: "/profile/create" //+ "?autologout=true"
	}
	
	
	def checkChallenge() {
		Profile p
		def qa
		withForm {
			try{
			def pid = params.pid
			 p = Profile.get(pid)
			 qa = [p.answer1,p.answer2]
			} catch (Exception e) {
				flash.message = "Profile Not Completed..Please contanct Site Admin for help"
				redirect(controller: "login", action: "auth")
			}
			if(params.question1.trim().toLowerCase() == qa[0].trim().toLowerCase() && params.question2.trim().toLowerCase() == qa[1].trim().toLowerCase() ) {
				RegistrationCode registrationCode = registrationService.getForgotPassLink(p)
				String url = generateLink('resetPassword', [t: registrationCode.token])
				redirect(url: url)
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
}
