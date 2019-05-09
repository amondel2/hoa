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
		def meetmins
		def meetdate
		if(springSecurityService.isLoggedIn()) {
			messages = messagesService.getMessages(params)
			if(!Profile.findByUser(springSecurityService.currentUser)){
				flash.message = "Please Complete Your Profile By clicking on 'Welcome' in the Nav Bar!"
			}
			meetmins = MeetingMinutes.last(sort : "meetDate")
			meetdate = meetmins?.meetDate
			meetmins = meetmins?.minutes
		}
		render(view:"index",model:[param:params,messages:messages,meetmins:meetmins,meetminDate:meetdate])
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

	@Secured(['ROLE_USER'])
	def vendorContact() {
		def usersList = Vendor.withCriteria {
			eq('isActive',true)
		}
		render(view:"vendorContact",model:[usersList:usersList])
	}
}