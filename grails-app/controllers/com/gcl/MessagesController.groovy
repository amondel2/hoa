package com.gcl
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*


@Secured(["ROLE_USER","ROLE_ADMIN",'ROLE_BOARDMEMBER'])
class MessagesController extends RestfulController {
	def messagesService
   static responseFormats = ['json']
   MessagesController(){
	   super(Messages)
   }
   
   @Override
   def index(Integer max) {
	   params.max = Math.min(max ?: 10, 100)
	   respond messagesService.getMessages(params), [status: OK]
   }
}