package com.gcl

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.CREATED

@Secured(["ROLE_USER"])
class HouseCarController {

    static scaffold=HouseCar
    def springSecurityService


    def save(HouseCar profileInstance) {
        if (profileInstance == null) {
            notFound()
            return
        }

        if (profileInstance.hasErrors()) {
            respond profileInstance.errors, view:'create', model:[user: springSecurityService.currentUser]
            return
        }

        if(!SpringSecurityUtils.ifAnyGranted("ROLE_BOARDMEMBER")) {

            //prevent other users from modifing profiles they don't have access to
            Profile usprofileInstance = Profile.findByUser(springSecurityService.currentUser)
            profileInstance.home = usprofileInstance?.home
        }

        profileInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'profile.label', default: 'Profile'), profileInstance.id])
                redirect profileInstance
            }
            '*' { respond profileInstance, [status: CREATED] }
        }
    }
}
