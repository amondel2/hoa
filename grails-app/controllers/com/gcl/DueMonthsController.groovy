package com.gcl

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class DueMonthsController {
	static scaffold=DueMonths
	
	
}