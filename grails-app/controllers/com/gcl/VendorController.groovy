package com.gcl
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.gcl.Vendor

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class VendorController {
	static scaffold=Vendor	
}