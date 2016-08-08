package com.gcl.fin

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class HouseController {
	static scaffold=House
	
	@Transactional
	def save(House houseInstance) {
		def p = params
		if (houseInstance == null) {
			notFound()
			return
		}

		if (houseInstance.hasErrors()) {
			respond houseInstance.errors, view:'create'
			return
		}

		houseInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'messages.label', default: 'Messages'), houseInstance.id])
				redirect action:"show", method:"GET", params:['id':houseInstance.id]
			}
			'*' { respond houseInstance, [status: CREATED] }
		}
	}
}