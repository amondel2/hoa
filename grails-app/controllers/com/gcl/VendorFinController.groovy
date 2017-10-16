package com.gcl


import grails.plugin.springsecurity.annotation.Secured


@Secured(["ROLE_BOARDMEMBER"])
class VendorFinController {
	static scaffold=VendorFin
}