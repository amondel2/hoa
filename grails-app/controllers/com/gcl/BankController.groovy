package com.gcl

import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import static org.springframework.http.HttpStatus.*
import com.gcl.Bank

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class BankController {
	static scaffold=Bank
	
	def show(Bank bankInstance) {
		if(!bankInstance) {
			bankInstance = Bank.findByAmount(params.id)
		}
		respond bankInstance
	}
	
	def edit(Bank bankInstance) {
		if(!bankInstance) {
			bankInstance = Bank.findByAmount(params.id)
		}
		respond bankInstance
	}
}