package com.gcl

import grails.plugin.springsecurity.ui.RegistrationCode
import grails.transaction.Transactional
import org.springframework.transaction.TransactionStatus

@Transactional
class RegistrationService {

	def springSecurityUiService
	
    RegistrationCode getForgotPassLink(Profile p) {
		springSecurityUiService.save(username: p.user.username, RegistrationCode,'sendForgotPasswordMail', transactionStatus)
    }
}
