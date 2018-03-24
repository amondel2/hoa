package com.gcl

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_USER"])
class ParkingController {

    static scaffold=Parking
}
