package com.gcl.fin.v1

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class DueMonthsController {
	

	static namespace = 'v1'
	def duemonthService = new GroovyClassLoader().loadClass("com.gcl.fin.${this.namespace}.Duemonth".toString()).newInstance()
	
	def test(){
		request.withFormat {
			'*' { render duemonthService.serviceMethod() as JSON }
		}
	}
}