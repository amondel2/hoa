package com.gcl

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = 'name')
@ToString(includes = 'name', includeNames = true, includePackage = false)
class Vendor implements Serializable {

	private static final long serialVersionUID = 1

	static constraints = {
		name unique: true
		address1 nullable: false, unique: ['address2', 'city', 'state']
		address2 nullable: true, blank: true
		city nullable: false
		state nullable: false
		zip1 min: 10000, max: 99999
		phone nullable: true, blank: true
		email nullable: true, blank: true
		accountNumber nullable: true
	}

	static hasMany = [vendorsFin: VendorFin]

	String name
	String address1
	String address2
	String city
	String state
	Integer zip1
	String phone
	String email
	String accountNumber
	Boolean isActive

	def calculateAmountOwed() {
		//find all the House Months that are not paid
		BigDecimal sum = new BigDecimal(0)
		Date now = new GregorianCalendar().getInstance().getTime()

		VendorFin.withCriteria {
			eq('vendor', this)
			gt('amount', new BigDecimal(0))
			isNull('paidDate')
			le('dueDate', now)
		}?.each { VendorFin vf ->
			sum += vf.amount
		}

		sum.setScale(2, BigDecimal.ROUND_CEILING)
	}

	String getAddress() {

		return this.address1 + ", " + (this.address2 ? this.address2 + ', ' : '') + this.city + " " + this.state + "." + this.zip1

	}
}
