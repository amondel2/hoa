package com.gcl
import grails.plugin.springsecurity.annotation.Secured
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


@Secured(["ROLE_BOARDMEMBER"])
@Transactional(readOnly = true)
class MessageEditorController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Messages.list(params), model:[messagesInstanceCount: Messages.count()]
    }

    def show(Messages messagesInstance) {
        respond messagesInstance
    }

    def create() {
        respond new Messages(params)
    }

    @Transactional
    def save(Messages messagesInstance) {
        if (messagesInstance == null) {
            notFound()
            return
        }

        if (messagesInstance.hasErrors()) {
            respond messagesInstance.errors, view:'create'
            return
        }

        messagesInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'messages.label', default: 'Messages'), messagesInstance.id])
                redirect action:"show", method:"GET", params:['id':messagesInstance.id]
            }
            '*' { respond messagesInstance, [status: CREATED] }
        }
    }

    def edit(Messages messagesInstance) {
        respond messagesInstance
    }

    @Transactional
    def update(Messages messagesInstance) {
        if (messagesInstance == null) {
            notFound()
            return
        }

        if (messagesInstance.hasErrors()) {
            respond messagesInstance.errors, view:'edit'
            return
        }

        messagesInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Messages.label', default: 'Messages'), messagesInstance.id])
                redirect action:"show", method:"GET", params:['id':messagesInstance.id]
            }
            '*'{ respond messagesInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Messages messagesInstance) {

        if (messagesInstance == null) {
            notFound()
            return
        }

        messagesInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Messages.label', default: 'Messages'), messagesInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'messages.label', default: 'Messages'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
