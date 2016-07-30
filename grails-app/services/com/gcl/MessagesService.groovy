package com.gcl

import grails.transaction.Transactional

@Transactional
class MessagesService {

    def getMessages(params) {
		def c = Messages.createCriteria()
		def rtn = c.list(params){
		  ge('expireDate', new Date())
		  order 'expireDate','asc'
		  order 'orderNumber','asc'
		}
    }
}
