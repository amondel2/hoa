package com.gcl

import java.util.Date;
import org.grails.databinding.BindingFormat

class VendorFin {

    static constraints = {
		vendor nullable:false
		paidDate nullable:true
    }
	
	static belongsTo = [vendor:Vendor]
	
	Vendor vendor
	BigDecimal amount
	@BindingFormat('MM/dd/yyyy')
	Date dueDate
	@BindingFormat('MM/dd/yyyy')
	Date paidDate
}
