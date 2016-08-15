package com.gcl.fin

import com.gcl.House;

import grails.transaction.Transactional

@Transactional
class FinancialService {

    def createDue(sm,nm,sy,amount) {
		def cal = new GregorianCalendar(sy,sm,1)
		int i = 0
		while(i < nm) {
			
			cal.add(cal.MONTH,Math.min(i++, 1)) // this adds nothing the first time :)
			
			def calend = new GregorianCalendar(cal.get(cal.YEAR),cal.get(cal.MONTH),cal.getActualMaximum(cal.DAY_OF_MONTH),23,59,59)
			def dm = DueMonths.findOrCreateByStartDateAndEndDate (cal.getTime(),calend.getTime())
			dm.amount = BigDecimal.valueOf(amount)
			dm.save(flush:true, failOnError:true)
			House.list().each {
				//we don't know if they paid or not
				if(!HouseMonth.exists(it.id,dm.id)){
					HouseMonth.create it, dm, true
				}
			}
		}
		["status":true]
    }
	
	def createSingDue(amount,monthDM,yearDM) {
		if(!this.getDueMonth(monthDM, yearDM)) {
			def hmCal = new GregorianCalendar(yearDM,monthDM,1)
			def hmeCal = new GregorianCalendar(yearDM,monthDM,1)
			hmeCal.add(hmCal.DAY_OF_MONTH, hmCal.getActualMaximum(hmCal.DAY_OF_MONTH))
			hmeCal.add(hmCal.SECOND, -1)
			DueMonths dm = new DueMonths()
			dm.amount = BigDecimal.valueOf(amount)
			dm.startDate = hmCal.getTime()
			dm.endDate = hmeCal.getTime()
			dm.save(flush:true, failOnError:true)
			["status":true,"dmId":dm.id]
		}
	
		
	}
	
	def getDueMonth(monthDM,yearDM) {
		def hmCal = new GregorianCalendar(yearDM,monthDM,1)
		def hmeCal = new GregorianCalendar(yearDM,monthDM,1)
		hmeCal.add(hmCal.DAY_OF_MONTH, hmCal.getActualMaximum(hmCal.DAY_OF_MONTH))
		hmeCal.add(hmCal.SECOND, -1)
		DueMonths.withCriteria {
			between('startDate',hmCal.getTime(),hmeCal.getTime())
		}?.getAt(0)
	}
}
