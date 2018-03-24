package com.gcl

class ParkingSpotReservation {

    static constraints = {
    }

    static belongsTo = [parkingSpot:Parking,car:HouseCar]

    Parking parkingSpot
    HouseCar car
    Date date

}