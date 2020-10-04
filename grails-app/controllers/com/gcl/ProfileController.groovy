package com.gcl

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.converters.JSON
import java.util.UUID

@Secured(["ROLE_USER"])
class ProfileController {

    def springSecurityService
    def houseMonthService
    ProfileService profileService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static scaffold=Profile

    @Secured(["permitAll"])
    def index(Integer max) {
        if(SpringSecurityUtils.ifAnyGranted("ROLE_BOARDMEMBER")) {
            params.max = Math.min(max ?: 10, 100)
            respond profileService.list(params), model:[profileCount: profileService.count()]
        } else {
            redirect(action:"show")
        }
    }

    @Secured(["ROLE_BOARDMEMBER","ROLE_USER","ROLE_ADMIN"])
    def show(Long id) {
        Profile profileInstance
        if (!id) {
            profileInstance = Profile.findByUser(springSecurityService.currentUser)
        } else {
            profileInstance =  profileService.get(id)
        }

        if(!profileInstance ) {
            redirect(action:"create")
        } else {
            def getFirstPayment
            def fees
            if(profileInstance.homeId) {
                getFirstPayment = houseMonthService.getFirstPaymentYear(profileInstance.homeId)
                fees = Fee.findAllByHouseAndPaidDateIsNull(profileInstance.home)
            }
            session['encryptKey'] = UUID.randomUUID().toString().replaceAll("-", "")
            SimpleStringHiding sh = SimpleStringHiding.getInstance()
            profileInstance.answer1 =  sh.encrypt(profileInstance.answer1,session['encryptKey']).toString()
            profileInstance.answer2 =  sh.encrypt(profileInstance.answer2,session['encryptKey']).toString()
            def myCal  = new GregorianCalendar().getInstance()
            respond profileInstance, model:[fee:fees,user: springSecurityService.currentUser,getFirstPayment:getFirstPayment,endYear:myCal.get(myCal.YEAR)]
        }
    }

    @Secured(["permitAll"])
    def create() {
        respond new Profile(params), model:[user: springSecurityService.currentUser,hl:House.list()]
    }


    @Secured(['ROLE_BOARDMEMBER'])
    def createFromUser() {
        Profile p = new Profile()
        def rtn = []
        try{
            p.user = User.load(params.uid)
            p.firstName =  p.user.username
            p.lastName = "ChangeME"
            p.question1 = "What is the  number (Please change me)"
            p.answer1 = UUID.randomUUID().toString().replaceAll("-", "");
            p.question2 =  "What is the letters (Please change me)"
            p.answer2 = UUID.randomUUID().toString().replaceAll("-", "");
            if (!p.validate()) {
                thorw new Exception(p.errors.join(" "))
            }
            p.save(flush:true,failOnError:true)
            rtn = [true]
        } catch (Exception e) {
            rtn= [false,e.message]
        }
        withFormat{
           '*' { render rtn as JSON }
        }
    }

    @Secured(["permitAll"])
    def save(Profile profile) {
        if (profile == null) {
            notFound()
            return
        }

        try {
            def email = params.email
            profile.user.email = email
            profileService.save(profile)
        } catch (ValidationException e) {
            respond profile.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'profile.label', default: 'Profile'), profile.id])
                redirect profile
            }
            '*' { respond profile, [status: CREATED] }
        }
    }

    @Secured(["permitAll"])
    def edit(Profile profileInstance) {
        if(SpringSecurityUtils.ifAnyGranted("ROLE_BOARDMEMBER")) {
            if (!profileInstance) {
                profileInstance = Profile.findByUser(springSecurityService.currentUser)
            }
        } else {
            //prevent other users from modifing profiles they don't have access to
            profileInstance = Profile.findByUser(springSecurityService.currentUser)
        }
        respond profileInstance, model:[user: springSecurityService.currentUser,hl:House.list()]
    }



    @Secured(["ROLE_BOARDMEMBER","ROLE_ADMIN","ROLE_USER"])
    def readOnlyHoaPayments(){

        def year
        try{
            year = params.int('year')
            if(!year){
                throw new Exception("null year")
            }
        } catch(Exception e) {
            def cal1 = new GregorianCalendar().getInstance()
            year = cal1.get(cal1.YEAR)
        }
        def rtn = []
        def hms = houseMonthService.getHouseMonthByHouseId(params.hid,year);
        def cal = new GregorianCalendar(year, 0, 1, 0, 0, 1)
        if(hms) {
            def i = 0, z = 1
            HouseMonth hm = hms[0]
            while(i <= 11) {
                if(hm && (hm?.months?.startDate <= cal.getTime())) {
                    rtn[i] = hm.paid
                    hm = hms[z++]
                } else {
                    rtn[i] = -1
                }
                cal.add(cal.DAY_OF_MONTH, cal.getActualMaximum(cal.DAY_OF_MONTH))
                i++
            }
        }
        try {
            rtn[12] = houseMonthService.calculateAmountOwed(House.load(params.hid))
        } catch (Exception e) {
            rtn[12] = null
        }
        withFormat{
           '*' { render rtn as JSON }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Secured(["permitAll"])
    def update(Profile profile) {
        if (profile == null) {
            notFound()
            return
        }
        if(!SpringSecurityUtils.ifAnyGranted("ROLE_BOARDMEMBER")) {
            //make sure this is my profile
            def tmpprofileInstance = Profile.findByUser(springSecurityService.currentUser)
            if(tmpprofileInstance.id != profile.id) {
                redirect action: "edit"
            }
        }
        try {
            def email = params.email
            profile.user.email = email
            profileService.save(profile)
        } catch (ValidationException e) {
            respond profile.errors, view:'edit'
            return
        } catch (Exception e) {
            flash.error = "Not Saved ${e.getMessage()}"
            redirect action: "edit"
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'profile.label', default: 'Profile'), profile.id])
                redirect profile
            }
            '*'{ respond profile, [status: OK] }
        }
    }

}
