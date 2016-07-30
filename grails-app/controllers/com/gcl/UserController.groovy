package com.gcl
import grails.plugin.springsecurity.annotation.Secured

class UserController extends grails.plugin.springsecurity.ui.UserController {

	@Secured(['ROLE_BOARDMEMBER'])
	def listEmail(){
		respond User.withCriteria{
			projections { property "email" }
		}, [formats:['json']]
	}
}