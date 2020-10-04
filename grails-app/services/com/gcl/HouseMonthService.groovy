package com.gcl

import grails.gorm.transactions.Transactional

import java.math.RoundingMode;

@Transactional
class HouseMonthService {

    def getHouseMonthByHouseId(hid){
        getHouseMonthByHouseId(hid,null)
    }

    def calculateAmountOwed(House h) {

        //find all the House Months that are not paid
        BigDecimal sum = new BigDecimal(this.getUnpaidPaidFee(h))
        Date now = new GregorianCalendar().getInstance().getTime()

        HouseMonth.withCriteria{
            eq('house',h)
            eq('paid',false)
            'in'( 'months', DueMonths.withCriteria {
                le('startDate',now)
            })
        }?.each{
            sum += it.months.amount
        }

        sum.setScale(2, RoundingMode.CEILING)
    }

    def getUnpaidPaidFee(House h) {
        Fee.withCriteria {
            eq("house", h)
            isNull("paidDate")
            projections {
                sum("amount")
            }
        }?.getAt(0) ?: 0
    }

    def getHouseMonthByHouseId(hid,year) {
        if(!year) {
            def myCal  = new GregorianCalendar().getInstance()
            year = myCal.get(myCal.YEAR)
        }
        def cal = new GregorianCalendar(year, 0, 1, 0, 0, 0)
        def endcal = new GregorianCalendar(year, 11, 31, 23, 59, 59)

        HouseMonth.withCriteria{
            eq('house',House.findById(hid))
            'in'( 'months', DueMonths.withCriteria {
                between('startDate',cal.getTime(),endcal.getTime())
                order("startDate","ASC")
            })
        }
    }

    def getFirstPaymentYear(hid) {
        (HouseMonth.withCriteria {
            'in'('house', House.withCriteria {
                eq('id', hid)
            })

            'in'('months', DueMonths.withCriteria {
                order "startDate", "ASC"
            })
            maxResults 1
            firstResult 0
        }?.get(0)?.months?.startDate?.getYear() ?: 100) + 1900
    }

    Boolean saveProfile(Profile profileInstance) {
        return profileInstance.save(flush:true)
    }
}
