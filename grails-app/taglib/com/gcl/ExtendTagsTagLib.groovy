package com.gcl

import com.gcl.Profile

class ExtendTagsTagLib {
	static namespace="glc"
    static defaultEncodeAs = [taglib:'html']
	def springSecurityService
	
	def getUserFName = {attrs,body->
		  Profile user = Profile.findByUser(springSecurityService.currentUser)
		  def name =  user ? user.firstName : ""
		  out << body() <<name
	}
}
