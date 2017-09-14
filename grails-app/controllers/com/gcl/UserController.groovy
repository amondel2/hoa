package com.gcl
import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

import java.nio.file.SecureDirectoryStream

class UserController extends grails.plugin.springsecurity.ui.UserController {

    @Secured(['ROLE_BOARDMEMBER'])
    def listEmail(){
        def test =  User.withCriteria{
            projections { property "email" }
        }
        request.withFormat {
			'*' {
                if(params.format == "json") {
                    render test as JSON
                } else if(params.format == "xml"){
                    render test as XML
                }else {
                    render test.join('<br>')
                }
            }
            json { render test as JSON }
            xml { render test as XML }
        }
    }

    @Secured(['permitAll'])
    def show(){
        def id
        try {
            def p = Profile.findByUser(User.findById(params.id))
            id = p.id
        } catch(Exception e ) {
        }
        redirect(controller:"profile",action:"show",params: [ id: id])
    }

    @Secured(['ROLE_BOARDMEMBER'])
    def listMailingAddress(){
        def test = House.list().collect{ it.toString() }
        request.withFormat {
			'*' { if(params.format == "json") {
                    render test as JSON
                } else if(params.format == "xml"){
                    render test as XML
                }else {
                    render test.join('<br>')
                }
            }
            json { render test as JSON }
            xml { render test as XML }
        }
    }

    @Secured(['ROLE_BOARDMEMBER'])
    def showMissProf(){
        def c = User.createCriteria()
        def matchingActs = c.list {
            not { 'in'('id',Profile.withCriteria{
                        projections{
                            property("user.id")
                        }})}
            projections{
                order "id"
            }
        }

     	respond matchingActs, model:['userInstanceList':matchingActs]
    }

    @Secured(['ROLE_BOARDMEMBER'])
    def sendMail(){
        int statusId = amazonSESTemplateService.sendTemplate(
                'amondel2@gmail.com',
                'TEST E_MAIL',
                [],             // Subject variables, if required
                [
                        foo: 'Some value to use in the template',
                        bar: 'Another value'
                ],
                'test'          // GSP located in '/views/template/emails/_test.gsp'
        )
    }

}