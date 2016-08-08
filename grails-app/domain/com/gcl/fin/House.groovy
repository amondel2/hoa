package com.gcl.fin
import com.gcl.fin.HouseType

import java.util.Calendar

import org.apache.commons.logging.LogFactory

class House implements Serializable {

	private static final long serialVersionUID = 1
	private static final log = LogFactory.getLog(this)
	
    static constraints = {
    }
	
	static hasMany=[fees:Fee]
	
	HouseType type
	int number
//	Date startPayment
	
	def calculateAmountOwed() {
		//find all the House Months that are not paid
		def sum = 0
		Date now = new GregorianCalendar().getInstance().getTime()
		this.fees?.each {Fee f ->  sum += (f.paid ? 0 : f.amount  ) }
		HouseMonth.withCriteria{
			eq('house',this)
			eq('paid',false)
			months {
				le('startDate',now)
			}
			}?.each{
				sum += it.months.amount
			}
		
			HouseCapital.withCriteria{
				eq('house',this)
				eq('paid',false)}?.each{
				sum += it.cap.amount
			}
		
		
		sum
	}	   
}