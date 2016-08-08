package com.gcl.fin
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_BOARDMEMBER"])
class FinancialController {

	def financialService
	def grailsApplication
	
	def index() {
		def cal = new GregorianCalendar().getInstance()
		def currYear = cal.get(cal.YEAR)
		def myCal = new GregorianCalendar();
		
		
		def firstYear = DueMonths.list(max: 1, offset: 0, sort: "startDate", order: "desc")?.getAt(0)?.startDate instanceof Date 
		firstYear = firstYear instanceof Date ? myCal.setTime(firstYear) : null
		firstYear =firstYear ? myCal.get(myCal.YEAR) : currYear
		
		def lastYear = DueMonths.list(max: 1, offset: 0, sort: "endDate", order: "asc")?.getAt(0)?.endDate
		lastYear = lastYear instanceof Date ? myCal.setTime(lastYear) : null
		lastYear = lastYear ? myCal.get(myCal.YEAR) :  currYear
		
		params.year = params.year ? params.int('year') : currYear
		
		def hmCal = new GregorianCalendar(params.year,0,1) //find all things for this year
		
		
		
//		def hms = HouseMonth.withCriteria {
//			months{
//				between('startDate',hmCal.getTime(),(hmCal.add(hmCal.MONTH, 12) ? hmCal.getTime() : hmCal.getTime()))
//			}
//		}

		
		render(view:"index",model:[viewYear:params.year,hms:House.list(),lastYear:lastYear,firstYear:firstYear,currMonth:cal.get(cal.MONTH) + 1,currYear:currYear,endYear: cal.get(cal.YEAR)  + 5])
	}

	def createDues(){
		def obj
		try{
			def  sm = params.int('startMonth') - 1 //calendar is 0 based for months
			def nm = params.int('numMonths')
			def sy = params.int('startYear')
			def amount = params.float('amount')
			obj = financialService.createDue(sm,nm,sy,amount)
		} catch(Exception e) {
			obj = ["status":false,"message":e.getMessage()]
		}
		request.withFormat {
			'*'{ render  obj as JSON }
		}
	}
	
	def createHMSingle(){
		def h = House.findById(params.long('hnid'))
		def dm = DueMonths.findById(params.long('dmId'))
		def hm = HouseMonth.create(h, dm,true)
		def c = grailsApplication.mainContext.getBean('com.gcl.ExtendTagsTagLib')
		def hmCal = new GregorianCalendar().getInstance()
		hmCal.setTime(dm.startDate)
		def output = c.renderMonthlyBox(year:hmCal.get(hmCal.YEAR),month:hmCal.get(hmCal.MONTH),hm:h)
		def obj = [status:true,amount:hm.house.calculateAmountOwed(),output:output]
		request.withFormat {
			'*'{ render  obj as JSON }
		}
	}
	
	def changePaid(){
		def isPaid = params.boolean('isPaid')
		def hm = HouseMonth.get(params.long('hmhn'),params.long('hmdm'))
		hm.paid = isPaid
		hm.save(flush:true,failOnError:true)
		def obj = [status:true,amount:hm.house.calculateAmountOwed()]
		request.withFormat {
			'*'{ render  obj as JSON }
		}
		
	}
	
	def findDm(){
		def hmCal = new GregorianCalendar(params.int('year'),params.int('months'),1)
		def hmeCal = new GregorianCalendar(params.int('year'),params.int('months'),1)
		hmeCal.add(hmCal.DAY_OF_MONTH, hmCal.getActualMaximum(hmCal.DAY_OF_MONTH))
		hmeCal.add(hmCal.SECOND, -1)
		def obj = DueMonths.withCriteria {
			between('startDate',hmCal.getTime(),hmeCal.getTime())
		}?.getAt(0)
		def exists=["status":"success","hasDM":obj?true:false,"dmId":obj?.id]
		request.withFormat {
			'*'{ render  exists as JSON }
		}
	}
	
}