package com.gcl

import java.util.Date;
import grails.databinding.BindingFormat

class VendorFin implements Serializable {

	private static final long serialVersionUID = 1

	static constraints = {
		vendor nullable: false
		paidDate nullable: true
	}

	static belongsTo = [vendor: Vendor]

	Vendor vendor
	BigDecimal amount
	@BindingFormat('MM/dd/yyyy')
	Date dueDate
	@BindingFormat('MM/dd/yyyy')
	Date paidDate
}
