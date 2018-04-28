package com.gcl
import grails.testing.mixin.integration.Integration
import grails.transaction.*

import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class DocumentsSpec extends GebSpec {
    void "test 2"(){
        when:"Login"
        to LoginPage
        login()
        then:
        at HomePage
        when:
        navMenu.clickon("HOA Documents")
        then:
        at DocumentsPage
        budget.text() == "2018 Budget"
    }
}
