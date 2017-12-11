package com.gcl

import com.gcl.FeeType;

class Fee implements Serializable {

	private static final long serialVersionUID = 1

	FeeType feetype
	BigDecimal amount
	House house
	Date assessmentDate
	Date dueDate
	Date paidDate
	String description

	static belongsTo = [house: House]

	static constraints = {
		amount min: new BigDecimal(0)
		paidDate nullable: true, blank: true
	}

	@Override
	String toString() {
		return this.feetype.toString() + " for " + this.amount + " " + this.description
	}

}
