package com.gcl

import grails.testing.mixin.integration.Integration
import grails.transaction.*

import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class HomeSpec extends GebSpec {

    def setup() {

    }

    def cleanup() {
    }

    void "Get to Login Page"() {
        when: "The home page is visited"
        to HomePage

        then: "The title is correct"
        at HomePage

        when: "Click to Login"
        navMenu.clickon('Login')

        then:
        at LoginPage
    }


}
