package com.gcl
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class FeeController {
	static scaffold=true
}