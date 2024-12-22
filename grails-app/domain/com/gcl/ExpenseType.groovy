package com.gcl

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = 'type')
@ToString(includes = 'type', includeNames = true, includePackage = false)
class ExpenseType implements Serializable {

    static mapping = {
        version false
    }

    private static final long serialVersionUID = 1

    String type

    static constraints = { }
}
