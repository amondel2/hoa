package com.gcl

class MeetingMinutes implements Serializable {

    private static final long serialVersionUID = 1

    static constraints = {
        minutes widget: 'textarea'
    }
    static mapping = {
        minutes type: "text"
        sort "meetDate" : "desc"
    }

    String minutes
    Date meetDate
}
