package com.gcl

import java.io.Serializable;

class DueMonths implements Serializable {

	private static final long serialVersionUID = 1
	
    static constraints = {
		startDate unique : 'amount', validator:{ val, obj ->
			if( val >= obj.endDate ) {
				return ["default.not.after.messag",'startDate',val,'endDate',obj.endDate]
			}
		}
		endDate unique: 'amount',validator:{ val, obj ->
			if( val < obj.startDate ) {
				return ["default.not.before.messag",'endDate',val,'startDate',obj.startDate]
			}
		}
		amount min: new BigDecimal(0)
	
    }
	
	BigDecimal amount
	Date startDate
	Date endDate
	
}