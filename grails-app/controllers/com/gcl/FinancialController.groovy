package com.gcl
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

import java.sql.Timestamp
import java.text.SimpleDateFormat

import com.gcl.House
import com.gcl.DueMonths
import com.gcl.HouseMonth
import com.gcl.HouseType

@Secured(["ROLE_BOARDMEMBER"])
class FinancialController {

    def financialService
    def houseMonthService


    @Secured(["ROLE_BOARDMEMBER"])
    def index() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date endDate,startDate
        try {
            endDate = sdf.parse(params.todate )
        } catch(Exception e) {
            endDate = null
        }
        try {
            startDate = sdf.parse(params.fromdate )
        } catch(Exception e) {
            startDate = null
        }
        render(view:"index",model:[
                fees:financialService.getHOATotalFee(startDate,endDate),
                totalOwed:financialService.getHOATotalOwed(startDate,endDate),
                hoaOwed:financialService.getHOAOutTotalStandingDues(startDate,endDate),
                debt:financialService.getHOATotalDebt(startDate,endDate),
                feesPaid:financialService.getHOATotalFeePaid(startDate,endDate),
                hoaPaid:financialService.getHOAOutTotalStandingDuesPaid(startDate,endDate),
                totalPaid:financialService.getHOATotalDebtPaid(startDate,endDate),
                moneyInBank:financialService.getMoneyInBack(),
                collected:financialService.getHOATotalPaid(startDate,endDate),endDate:params.todate,startDate:params.fromdate

            ])
    }

    @Transactional
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

        render(view:"admin",model:[viewYear:params.year,hms:House.list(),lastYear:lastYear,firstYear:firstYear,currMonth:cal.get(cal.MONTH) + 1,currYear:currYear,endYear: cal.get(cal.YEAR)  + 5])
    }

    @Transactional
    def createDues(){
        def obj
        try{
            def  sm = params.int('startMonth') - 1 //calendar is 0 based for months
            def nm = params.int('numMonths')
            def sy = params.int('startYear')
            def amount = params.float('amount')
            def type =  params.housetype
            try{
                type = type ? HouseType[type] : null
            } catch (Exception e) {
                type = null
            }
            obj = financialService.createDue(sm,nm,sy,amount,type)
        } catch(Exception e) {
            obj = ["status":false,"message":e.getMessage()]
        }
        request.withFormat {
			'*'{ render  obj as JSON }
        }
    }

    @Transactional
    def deleteDm() {
        def obj
        try{
            def h = House.findById(params.long('hmhn'))
            def hm = HouseMonth.get(h.id,params.long('hmdm')).delete(flush:true)
            def c = grailsApplication.mainContext.getBean('com.gcl.ExtendTagsTagLib')
            def output = c.renderMonthlyBox(year:params.int('year'),month:params.int('month'),hm:h)
            obj = [status:true,amount:houseMonthService.calculateAmountOwed(h),output:output]
        } catch(Exception e) {
            obj = ["status":false,"message":e.getMessage()]
        }
        request.withFormat {
			'*'{ render  obj as JSON }
        }
    }

    @Transactional
    def createDuesSingle() {
        def obj
        try{
            def amount = params.float('amount')
            House h = House.findById(params.long('hnid'))
            obj = financialService.createSingDue(amount,params.int('months'),params.int('year'), h)
            obj['hnid'] = h.id
            obj['hn'] = h.number
            obj.month = params.int('months')
        } catch (Exception e) {
            obj = ["status":false,"message":e.getMessage()]
        }
        request.withFormat {
			'*'{ render  obj as JSON }
        }
    }

    @Transactional
    def createHMSingle(){
        def obj
        try{
            def h = House.findById(params.long('hnid'))
            def dm = DueMonths.findById(params.long('dmId'))
            def hm = HouseMonth.create(h, dm,true)
            def c = grailsApplication.mainContext.getBean('com.gcl.ExtendTagsTagLib')
//            def c = ApplicationHolder.application.mainContext.getBean('com.gcl.ExtendTagsTagLib')
            def hmCal = new GregorianCalendar().getInstance()
            hmCal.setTime(dm.startDate)
            def output = c.renderMonthlyBox(year:hmCal.get(hmCal.YEAR),month:hmCal.get(hmCal.MONTH),hm:h)
            obj = [status:true,amount:houseMonthService.calculateAmountOwed(hm.house),output:output,month:hmCal.get(hmCal.MONTH)]
        } catch(Exception e) {
            obj = ["status":false,"message":e.getMessage()]
        }
        request.withFormat {
			'*'{ render  obj as JSON }
        }
    }

    @Transactional
    def missPayment(){
        def obj = [status:true]
        try {
            HouseMonth hm = HouseMonth.get(params.long('hmhn'), params.long('hmdm'))
            BigDecimal amount = hm.months.amount - new BigDecimal(params.amount);
            if(amount > 0) {
                Fee f = new Fee()
                f.amount = amount
                f.house = hm.house
                f.assessmentDate = hm.months.startDate
                f.dueDate = hm.months.endDate
                f.feetype = FeeType.UnderPaidDues
                f.description = "Under Paid Dues"
                f.save()
            }
        } catch(Exception e) {
            obj.status = false
            obj.message = e.getMessage()
        }
        request.withFormat {
            '*'{ render  obj as JSON }
        }
    }

    @Transactional
    def createLateFee() {
        def obj = [status:true]
        try {
            HouseMonth hm = HouseMonth.get(params.long('hmhn'), params.long('hmdm'))
            Fee f = Fee.findWhere(house: hm.house,feetype: FeeType.Late,dueDate: hm.months.endDate)
            if(!f) {
                f = new Fee(house: hm.house,feetype: FeeType.Late,dueDate: hm.months.endDate)
                Calendar c = Calendar.getInstance()
                f.assessmentDate = c.getTime()
                c.setTime(hm.months.endDate)
                f.description = "Fine for late payment for " + c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " of " + c.get(Calendar.YEAR)
                f.amount = new BigDecimal(params.amount)
                f.save(failOnError: true)
            } else {
                obj.status = false
                obj.message = "Fee Already Exists"
            }
        } catch(Exception e) {
            obj.status = false
            obj.message = e.getMessage()
        }
        request.withFormat {
            '*'{ render  obj as JSON }
        }
    }

    @Transactional
    def changePaid(){
        def isPaid = params.boolean('isPaid')
        def hm = HouseMonth.get(params.long('hmhn'),params.long('hmdm'))
        hm.paid = isPaid
        hm.save(flush:true,failOnError:true)
        if(hm.paid == false) {
            //remove any Fee for under paymnet payment fee
            Fee.where{
                house == hm.house
                feetype == FeeType.UnderPaidDues
                paidDate == null
                assessmentDate == hm.months.startDate
            }?.deleteAll()
        }
        def obj = [status:true,amount:houseMonthService.calculateAmountOwed(hm.house)]
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