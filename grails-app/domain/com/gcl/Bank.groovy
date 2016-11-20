package com.gcl

class Bank {
	
	Date date
	BigDecimal amount
	
	def beforeInsert(){
		return false
	}
	
	def beforeDelete(){
		return false
	}
}
