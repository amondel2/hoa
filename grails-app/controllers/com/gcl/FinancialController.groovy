package com.gcl
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import java.sql.Timestamp

import com.gcl.House
import com.gcl.DueMonths
import com.gcl.HouseMonth

@Secured(["ROLE_BOARDMEMBER"])
class FinancialController {

	def financialService
	def grailsApplication

	def index() {
		redirect(action:"admin")
	}

	@Secured(["ROLE_BOARDMEMBER"])
	def admin(){
		def cal = new GregorianCalendar().getInstance()
		def currYear = cal.get(cal.YEAR)
		def myCal = new GregorianCalendar().getInstance();
		def firstYear = DueMonths.list(max: 1, offset: 0, sort: "startDate", order: "asc")?.getAt(0)?.startDate
		def isDate = ((firstYear instanceof Timestamp) ||  (firstYear instanceof Date))
		isDate ? myCal.setTime(firstYear) : null
		firstYear = isDate ? myCal.get(myCal.YEAR) : currYear

		def lastYear = DueMonths.list(max: 1, offset: 0, sort: "endDate", order: "desc")?.getAt(0)?.endDate
		isDate = ((lastYear instanceof Timestamp) ||  (lastYear instanceof Date))
		isDate ? myCal.setTime(lastYear) : null
		lastYear = isDate ? myCal.get(myCal.YEAR) :  currYear

		params.year = params.year ? params.int('year') : currYear

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
	
	def deleteDm() {
		def obj
		try{
			def h = House.findById(params.long('hmhn'))
			def hm = HouseMonth.get(h.id,params.long('hmdm')).delete(flush:true)
			def c = grailsApplication.mainContext.getBean('com.gcl.ExtendTagsTagLib')
			def output = c.renderMonthlyBox(year:params.int('year'),month:params.int('month'),hm:h)
			obj = [status:true,amount:h.calculateAmountOwed(),output:output]
		} catch(Exception e) {
				obj = ["status":false,"message":e.getMessage()]
		}
		request.withFormat {
			'*'{ render  obj as JSON }
		}
	}

	def createDuesSingle() {
		def obj
		try{
			def amount = params.float('amount')
			House h = House.findById(params.long('hnid'))
			obj = financialService.createSingDue(amount,params.int('months'),params.int('year'))
			obj['hnid'] = h.id
			obj['hn'] = h.number
		} catch (Exception e) {
			obj = ["status":false,"message":e.getMessage()]
		}
		request.withFormat {
			'*'{ render  obj as JSON }
		}
	}

	def createHMSingle(){
		def obj
		try{
			def h = House.findById(params.long('hnid'))
			def dm = DueMonths.findById(params.long('dmId'))
			def hm = HouseMonth.create(h, dm,true)
			def c = grailsApplication.mainContext.getBean('com.gcl.ExtendTagsTagLib')
			def hmCal = new GregorianCalendar().getInstance()
			hmCal.setTime(dm.startDate)
			def output = c.renderMonthlyBox(year:hmCal.get(hmCal.YEAR),month:hmCal.get(hmCal.MONTH),hm:h)
			obj = [status:true,amount:hm.house.calculateAmountOwed(),output:output,month:hmCal.get(hmCal.MONTH)]
		} catch(Exception e) {
			obj = ["status":false,"message":e.getMessage()]
		}
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
		def obj = financialService.getDueMonth(params.int('months'),params.int('year'))
		def exists=["status":"success","hasDM":obj?true:false,"dmId":obj?.id]
		request.withFormat {
			'*'{ render  exists as JSON }
		}
	}
}