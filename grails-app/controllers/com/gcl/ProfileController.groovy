package com.gcl
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.converters.JSON
import java.util.UUID

@Secured(["ROLE_USER"])
@Transactional(readOnly = true)
class ProfileController {

    def springSecurityService
    def houseMonthService
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
            def getFirstPayment
            def fees
            if(profileInstance.homeId) {
                getFirstPayment = houseMonthService.getFirstPaymentYear(profileInstance.homeId)
                fees = Fee.findAllByHouseAndPaidDateIsNull(profileInstance.home)
            }
            def myCal  = new GregorianCalendar().getInstance()
            respond profileInstance, model:[fee:fees,user: springSecurityService.currentUser,getFirstPayment:getFirstPayment,endYear:myCal.get(myCal.YEAR)]
        }
    }

    @Secured(["permitAll"])
    def create() {
        respond new Profile(params), model:[user: springSecurityService.currentUser,hl:House.list()]
    }

    @Transactional
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
        rtn[12] = House.load(params.hid)?.calculateAmountOwed()
        withFormat{
           '*' { render rtn as JSON }
        }
    }

    @Transactional
    @Secured(["permitAll"])
    def update(Profile profileInstance) {
        if (profileInstance == null) {
            notFound()
            return
        }

        if (profileInstance.hasErrors()) {
            respond profileInstance.errors, view:'edit', model:[user: springSecurityService.currentUser,hl:House.list()]
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
