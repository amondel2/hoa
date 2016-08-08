package com.gcl.fin

import java.io.Serializable

class CapitalContrib implements Serializable {

	private static final long serialVersionUID = 1

    static constraints = {
		dueDate unique:true
	}
	
	BigDecimal amount
	Date dueDate
}
