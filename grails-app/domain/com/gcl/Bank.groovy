package com.gcl
import java.io.Serializable;

class Bank implements Serializable {

	private static final long serialVersionUID = 1
	
	Date date
	BigDecimal amount
	String name
	
	def beforeInsert(){
		return false
	}
	
	def beforeDelete(){
		return false
	}

	@Override
	String toString() {
		return this.name
	}
}
