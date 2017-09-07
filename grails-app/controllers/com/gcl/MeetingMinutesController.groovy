package com.gcl
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*


@Secured(["ROLE_ADMIN",'ROLE_BOARDMEMBER'])
class MeetingMinutesController {

    static scaffold=MeetingMinutes
}
