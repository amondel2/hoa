package com.gcl

import java.io.Serializable

class CapitalContrib implements Serializable {

	private static final long serialVersionUID = 1

    static constraints = {
		dueDate unique:true
		amount min: new BigDecimal(0)
	}
	
	BigDecimal amount
	Date dueDate
}
