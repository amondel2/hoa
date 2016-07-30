package com.gcl

import groovy.transform.EqualsAndHashCode
import grails.rest.*

@EqualsAndHashCode(includes=['id'])
class Messages implements Serializable  {
    
    private static final serialVersionUID = 1L

    static constraints = {
		expireDate validator: { val, obj -> val?.after(new Date()) }
    }
	
	Long id
	Long orderNumber
	String text	
	Date expireDate
}
