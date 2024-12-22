package com.gcl

class Parking {

    static constraints = {
    }

    static mapping = {
        version false
    }

    static hasMany = [parkingSpotReservations:ParkingSpotReservation]
    String spotNumber

}
