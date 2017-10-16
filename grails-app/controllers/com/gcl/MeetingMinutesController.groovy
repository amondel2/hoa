package com.gcl
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Secured(["ROLE_ADMIN",'ROLE_BOARDMEMBER'])
class MeetingMinutesController {

    static scaffold=MeetingMinutes

    @Secured(["ROLE_USER",'ROLE_BOARDMEMBER'])
    def archive(){
        MeetingMinutes meetmins
        def meetminDate
        def meetminText
        def curr
        def totalEntries = MeetingMinutes.count()
        if(params.newCurr && params.curr) {
            def tmid = MeetingMinutes.findById(params.mid)
            if(params.newCurr > params.curr) {
                meetmins = MeetingMinutes.findAllByMeetDateGreaterThan(tmid.meetDate,[sort : "meetDate"]).first()
            } else {
                meetmins = MeetingMinutes.findAllByMeetDateLessThan(tmid.meetDate,[sort : "meetDate"]).last()
            }
            curr = params.newCurr
        } else {
            meetmins = MeetingMinutes.last(sort : "meetDate")
            curr = totalEntries
        }
        meetminDate = meetmins.meetDate
        meetminText = meetmins.minutes
        render(view:"archive",model:[meetmins:meetminText,meetminDate:meetminDate,mid:meetmins.id,totalEntries:totalEntries,curr:curr])
    }
}