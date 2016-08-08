package com.gcl.fin

import grails.transaction.Transactional

@Transactional
class FinancialService {

    def createDue(sm,nm,sy,amount) {
		def cal = new GregorianCalendar(sy,sm,1)
		int i = 0
		while(i < nm) {
			
			cal.add(cal.MONTH,Math.min(i++, 1)) // this adds nothing the first time :)
			
			def calend = new GregorianCalendar(cal.get(cal.YEAR),cal.get(cal.MONTH),cal.getActualMaximum(cal.DAY_OF_MONTH),23,59,59)
			
			def dm = DueMonths.findOrCreateByStartDateAndEndDateAndAmount (cal.getTime(),calend.getTime(),BigDecimal.valueOf(amount))
			if(!dm.id){
				dm.save(flush:true, failOnError:true)
			}
			House.list().each {
				//we don't know if they paid or not
				if(!HouseMonth.exists(it.id,dm.id)){
					HouseMonth.create it, dm, true
				}
			}
		}
		["status":true]
    }
}
