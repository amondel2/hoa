package com.gcl

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import org.springframework.security.test.context.support.WithMockUser
import grails.plugin.springsecurity.SpringSecurityUtils


class ProfileControllerSpec extends Specification implements ControllerUnitTest<ProfileController>,DomainUnitTest<Profile> {

     void "test something"() {
        when:"The message action is invoked"

        controller.readOnlyHoaPayments()

        then:"Error is throw no pmid"
        NullPointerException e = thrown()
    }

    @WithMockUser(username = "user1", password = "pwd", roles = "ROLE_USER")
    void "test show"() {
        when:"The message action is invoked"
        SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role ->
            return false
        }
        controller.index()

        then:"Goto to SHOWd"
        response.redirectedUrl == "/profile/show"
    }


    void "test show stuff"() {
        when:"The message action is invoked"
        SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role ->
            return true
        }
        controller.index()

        then:"Goto to SHOWd"
        model.profileCount == 0
    }
}
