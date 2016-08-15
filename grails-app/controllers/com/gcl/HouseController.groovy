package com.gcl

import static org.springframework.http.HttpStatus.*

import com.gcl.House;

import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class HouseController {
	static scaffold=House
	
}