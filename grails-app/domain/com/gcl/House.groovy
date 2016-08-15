package com.gcl
import java.util.Calendar

import org.apache.commons.logging.LogFactory

class House implements Serializable {
	
	private static final long serialVersionUID = 1
	private static final log = LogFactory.getLog(this)
	
    static constraints = {
		address1 nullable:false, unique: ['address2','city','state']
		address2 nullable:true, blank:true
		city nullable:false
		state nullable:false
		zip1 min:10000,max:99999
		zip2 nullable:true,min:1000,max:9999
		type nullable:false
    }
	
	static hasMany=[profiles:Profile]

	String toString() {
		"${this.address1}, " + (this.address2 ? "${this.address2}, " : "") + "${this.city}, ${this.state} ${this.zip1}" + (this.zip2  ? "-${this.zip2}" : "")

	}
	
	HouseType type
	String address1
	String address2
	String city
	String state
	Integer zip1
	Integer zip2 = null
	
	def getNumber() {
		this.address1?.split(" ")?.getAt(0)
	}
  
}