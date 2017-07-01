package com.gcl


class Profile implements Serializable {

	private static final long serialVersionUID = 1

	static belongsTo = [ user: User, home:House ]
	Long id
	String firstName
	String lastName
	User user
	House home
	String phoneNumber
	String question1
	String answer1
	String question2
	String answer2

    static constraints = {
		id  unique:true,nullable:true,display:true
		user unique:true
		home  nullable:true
		question1 minSize: 10, maxSize: 300,validator: { val, obj -> 
			if(val.trim().toLowerCase() == obj.question2?.trim()?.toLowerCase())
				return ["default.not.unique.message",'question1','Profile',val]
		}
		question2 minSize: 10, maxSize: 300,validator: { val, obj -> 
			if(val.trim().toLowerCase() == obj.question1?.trim()?.toLowerCase())
				return ["default.not.unique.message",'question2','Profile',val]
		}
		answer1  minSize: 5, maxSize: 300,validator: { val, obj -> 
			if(val.trim().toLowerCase() == obj.answer2?.trim()?.toLowerCase())
				return ["default.not.unique.message",'answer1','Profile',val]
		}
		answer2 minSize: 5, maxSize: 300,validator: { val, obj -> 
			if(val.trim().toLowerCase() == obj.answer1?.trim()?.toLowerCase())
				return ["default.not.unique.message",'answer2','Profile',val]
		}
		phoneNumber nullable:true,blank:true
    }

	static mapping = {
		id column: 'id'
	}

	String toString() {
		return this.firstName + " " + this.lastName 	
	}
	
}
