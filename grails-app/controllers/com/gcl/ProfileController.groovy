package com.gcl
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class ProfileController {

	def springSecurityService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	static scaffold=Profile
	
	@Secured(["permitAll"])
    def index(Integer max) {
		if(SpringSecurityUtils.ifAnyGranted("ROLE_BOARDMEMBER")) {
			params.max = Math.min(max ?: 10, 100)
			respond Profile.list(params), model:[profileInstanceCount: Profile.count()]
		} else {
        	redirect(action:"show")
		}
    }

	@Secured(["permitAll"])
    def show(Profile profileInstance) {
		if (!profileInstance) {
			profileInstance = Profile.findByUser(springSecurityService.currentUser)
		}
	
		if(!profileInstance) {
			redirect(action:"create")
		} else {
        	respond profileInstance, model:[user: springSecurityService.currentUser]
		}
    }

	@Secured(["permitAll"])
    def create() {
        respond new Profile(params), model:[user: springSecurityService.currentUser,hl:House.list()]
    }
	
	

    @Transactional
	@Secured(["permitAll"])
    def save(Profile profileInstance) {
        if (profileInstance == null) {
            notFound()
            return
        }

        if (profileInstance.hasErrors()) {
            respond profileInstance.errors, view:'create', model:[user: springSecurityService.currentUser]
            return
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

	@Secured(["permitAll"])
    def edit(Profile profileInstance) {
		if(!profileInstance) {
			profileInstance = Profile.findByUser(springSecurityService.currentUser)
		}
        respond profileInstance, model:[user: springSecurityService.currentUser,hl:House.list()]
    }

    @Transactional
	@Secured(["permitAll"])
    def update(Profile profileInstance) {
        if (profileInstance == null) {
            notFound()
            return
        }

        if (profileInstance.hasErrors()) {
            respond profileInstance.errors, view:'edit', model:[user: springSecurityService.currentUser]
            return
        }

        profileInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Profile.label', default: 'Profile'), profileInstance.id])
                redirect profileInstance
            }
            '*'{ respond profileInstance, [status: OK] }
        }
    }

}
