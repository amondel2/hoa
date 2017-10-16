package com.gcl

import grails.plugin.springsecurity.annotation.Secured
import static org.springframework.http.HttpStatus.*
import com.gcl.Bank

@Secured(["ROLE_BOARDMEMBER"])
class BankController {
	static scaffold=Bank

}