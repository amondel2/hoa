package com.gcl

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_BOARDMEMBER"])
class DueMonthsController {
	static scaffold=DueMonths
	
	
}