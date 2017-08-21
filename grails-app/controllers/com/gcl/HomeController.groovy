package com.gcl
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import org.hibernate.FetchMode

@Secured(['permitAll'])
class HomeController {

	def springSecurityService
	def messagesService
    def index() { 
		def messages
		if(springSecurityService.isLoggedIn()) {
			messages = messagesService.getMessages(params)
			if(!Profile.findByUser(springSecurityService.currentUser)){
				flash.message = "Please Complete Your Profile By clicking on 'Welcome' in the Nav Bar!"
			}
		}
		render(view:"index",model:[param:params,messages:messages])		
	}

	@Secured(['ROLE_USER'])
	def contactList() {
		def usersList = Profile.withCriteria {
			or {
				eq('showEmailInfo',true)
				eq('showAddressInfo',true)
				eq('showPhone',true)
			}
			fetchMode "user", FetchMode.JOIN
			fetchMode "home", FetchMode.JOIN
		}
		render(view:"contactList",model:[usersList:usersList])
	}
}