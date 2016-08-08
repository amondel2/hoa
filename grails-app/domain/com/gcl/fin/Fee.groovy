package com.gcl.fin
import com.gcl.fin.FeeType

class Fee {
	
	FeeType feetype
	BigDecimal amount
	House house
	Date assessmentDate
	Date dueDate
	Date paidDate
	Boolean paid = false
	String description
	
	static belongsTo=[house:House]
	
    static constraints = {


		paidDate nullable:true,blank:true
    }
}
