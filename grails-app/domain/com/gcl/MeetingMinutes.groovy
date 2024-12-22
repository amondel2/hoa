package com.gcl

class MeetingMinutes implements Serializable {

    private static final long serialVersionUID = 1

    static mapping = {
        minutes type:"text"
        sort "meetDate" : "desc"
        version false
    }

    String minutes
    Date meetDate
}
