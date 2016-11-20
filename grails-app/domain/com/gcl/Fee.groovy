package com.gcl
import com.gcl.FeeType;

class Fee {
	
	FeeType feetype
	BigDecimal amount
	House house
	Date assessmentDate
	Date dueDate
	Date paidDate
	String description
	
	static belongsTo=[house:House]
	
    static constraints = {
		amount min: new BigDecimal(0)
		paidDate nullable:true,blank:true
    }
}
