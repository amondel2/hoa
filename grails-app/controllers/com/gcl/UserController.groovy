package com.gcl
import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured
import com.gcl.fin.House

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
}