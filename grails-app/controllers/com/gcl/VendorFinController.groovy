package com.gcl
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.gcl.VendorFin

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class VendorFinController {
	static scaffold=VendorFin
}