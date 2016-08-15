package com.gcl
import com.gcl.FeeType;

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

		amount min: new BigDecimal(0)
		paidDate nullable:true,blank:true
		paid validator:{ val, obj -> 
			if( val && ( !obj || obj.paidDate.equals(null) || !obj.paidDate)){
				return ["paiddate.paid.both.required.paid"]
			}
		}
		paidDate validator:{ val, obj ->
			if( val && ( !obj || obj.paid.equals(null) || !obj.paid)){
				return ["paiddate.paid.both.required.paidddate"]
			}
		}
    }
}
