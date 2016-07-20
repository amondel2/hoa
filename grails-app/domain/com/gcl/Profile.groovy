package com.gcl

class Profile implements Serializable {

	private static final long serialVersionUID = 1

	static belongsTo = [ user: User ]
	User user
	String firstName
	String lastName
	String phoneNumber
	String houseNumber
	
    static constraints = {
    }
}
