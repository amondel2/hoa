package com.gcl

class Parking {

    static constraints = {
    }

    static hasMany = [parkingSpotReservations:ParkingSpotReservation]
    def spotNumber

}
