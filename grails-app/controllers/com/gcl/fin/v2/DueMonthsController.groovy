package com.gcl.fin.v2

import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import com.gcl.fin.v2.Duemonth

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class DueMonthsController {

	static namespace = 'v2'
	def duemonthService = new GroovyClassLoader().loadClass("com.gcl.fin.${this.namespace}.Duemonth".toString()).newInstance()
	
	
	def test(){
		request.withFormat {
			json { render duemonthService.serviceMethod() as JSON }
			'*' { render duemonthService.serviceMethod() as XML }
		}
	}
}