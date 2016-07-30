package com.gcl
import grails.plugin.springsecurity.SpringSecurityService;
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class HomeController {

	def springSecurityService
	def messagesService
    def index() { 
		def messages
		if(springSecurityService.isLoggedIn()) {
			messages = messagesService.getMessages(params)
			if(!Profile.findByUser(springSecurityService.currentUser)){
				flash.message = "Please Complete Your Profile By clicking on 'Welecome' in the Nav Bar!"
			}
			
		}
		render(view:"index",model:[param:params,messages:messages])		
	}
}