package com.gcl
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*


@Secured(["ROLE_USER","ROLE_ADMIN",'ROLE_BOARDMEMBER'])
class MessagesController  {
    static scaffold=Messages
}