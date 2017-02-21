package com.gcl

import com.gcl.House;
import groovy.sql.Sql
import grails.transaction.Transactional

@Transactional
class FinancialService {

    def dataSource

    def createDue(sm,nm,sy,amount) {
        createDue(sm,nm,sy,amount,null)
    }

    def createDue(sm,nm,sy,amount,type) {
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
                if(!HouseMonth.exists(it.id,dm.id) && (type.equals(null) ||  it.type == type ) ){
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


    def getHOATotalFee() {
        getHOATotalFee(null,null)
    }

    def getHOATotalFee(startDate,endDate) {
        Fee.withCriteria {
            isNull("paidDate")
            if(startDate && endDate) {
                between("dueDate",startDate,endDate)
            } else if (startDate ) {
                ge("dueDate",startDate)
            } else if (endDate) {
                le("dueDate",endDate)
            }
            projections {
                sum("amount")
            }
        }?.getAt(0) ?: 0
    }

    def getHOAOutTotalStandingDues(startDate,endDate) {
        BigDecimal sum = new BigDecimal(0)
        HouseMonth.withCriteria{
            eq('paid',false)
            months {
                if(startDate && endDate) {
                    between("startDate",startDate,endDate)
                } else if (startDate ) {
                    ge("startDate",startDate)
                } else if (endDate) {
                    le("startDate",endDate)
                } else {
                    le('startDate',new GregorianCalendar().getInstance().getTime())
                }
            }
        }?.each{
            sum += it.months.amount
        }
        sum.setScale(2,BigDecimal.ROUND_CEILING)
        sum
    }

    def getHOATotalOwed(startDate,endDate) {
        new BigDecimal(this.getHOATotalFee(startDate,endDate)) + this.getHOAOutTotalStandingDues(startDate,endDate)
    }

    def getHOATotalDebt(startDate,endDate){

        //find all the House Months that are not paid
        BigDecimal sum = new BigDecimal(0)
        VendorFin.withCriteria{
            gt('amount',new BigDecimal(0))
            isNull('paidDate')
            if(startDate && endDate) {
                between("dueDate",startDate,endDate)
            }  else if (startDate ) {
                ge("dueDate",startDate)
            } else if (endDate) {
                le("dueDate",endDate)
            } else {
                le('dueDate',new GregorianCalendar().getInstance().getTime())
            }
        }?.each{ VendorFin vf ->
            sum += vf.amount
        }
        sum.setScale(2,BigDecimal.ROUND_CEILING)
        sum
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

    def getHOATotalFeePaid(startDate,endDate) {
        Fee.withCriteria {
            isNotNull("paidDate")
            if(startDate && endDate) {
                between("paidDate",startDate,endDate)
            } else if (startDate ) {
                ge("paidDate",startDate)
            } else if (endDate) {
                le("paidDate",endDate)
            }
            projections {
                sum("amount")
            }
        }?.getAt(0) ?: 0
    }

    def getHOAOutTotalStandingDuesPaid(startDate,endDate) {
        BigDecimal sum = new BigDecimal(0)
        HouseMonth.withCriteria{
            eq('paid',true)
            months {
                if(startDate && endDate) {
                    between("startDate",startDate,endDate)
                } else if (startDate ) {
                    ge("startDate",startDate)
                } else if (endDate) {
                    le("startDate",endDate)
                } else {
                    le('startDate',new GregorianCalendar().getInstance().getTime())
                }
            }
        }?.each{
            sum += it.months.amount
        }
        sum.setScale(2,BigDecimal.ROUND_CEILING)
        sum
    }

    def getHOATotalPaid(startDate,endDate) {
        new BigDecimal(this.getHOATotalFeePaid(startDate,endDate)) + this.getHOAOutTotalStandingDuesPaid(startDate,endDate)
    }

    def getMoneyInBack(){
        //		def sql = new Sql(dataSource)
        //		def money
        //		sql.eachRow('select * from Bank') { row ->
        //			money = row.amount
        //		}
        //		money.toString()
        Bank.list()?.getAt(0)?.amount ?: 0
    }

    def getHOATotalDebtPaid(startDate,endDate){
        BigDecimal sum = new BigDecimal(0)
        Date now = new GregorianCalendar().getInstance().getTime()

        VendorFin.withCriteria{
            gt('amount',new BigDecimal(0))
            isNotNull('paidDate')
            if(startDate && endDate) {
                between("paidDate",startDate,endDate)
            } else if (startDate ) {
                ge("paidDate",startDate)
            } else if (endDate) {
                le("paidDate",endDate)
            }
        }?.each{ VendorFin vf ->
            sum += vf.amount
        }
        sum.setScale(2,BigDecimal.ROUND_CEILING)
        sum
    }

}
