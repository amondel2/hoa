package com.gcl
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_USER","ROLE_ADMIN",'ROLE_BOARDMEMBER'])
class DocumentsController {

    def index() { }
}
