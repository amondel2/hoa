package com.gcl

import grails.transaction.Transactional

@Transactional
class HouseMonthService {

    def getHouseMonthByHouseId(hid){
        getHouseMonthByHouseId(hid,null)
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
            months {
                between('startDate',cal.getTime(),endcal.getTime())
                order("startDate","ASC")
            }
        }
    }

    def getFirstPaymentYear(hid) {
        (HouseMonth.withCriteria{
                eq('house',House.findById(hid))
                months{
                    order "startDate", "ASC"
                }
                maxResults 1
                firstResult 0
            }?.get(0)?.months.startDate.getYear() ?: 100) + 1900
    }
}
