package com.gcl

class ParkingSpotReservation {

    static constraints = {
    }

    static mapping = {
        version false
    }

    static belongsTo = [parkingSpot:Parking,car:HouseCar]

    Parking parkingSpot
    HouseCar car
    Date date

}