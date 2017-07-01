package com.gcl

import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import static org.springframework.http.HttpStatus.*
import com.gcl.Bank

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class BankController {
	static scaffold=Bank

}