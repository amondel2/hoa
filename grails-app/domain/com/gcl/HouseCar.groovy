package com.gcl

class HouseCar implements Serializable {

    private static final long serialVersionUID = 1


    static constraints = {
        licensePlate unquie:true,nullable: false, blank: false
    }

    static belongsTo = [house:House]
    static hasMany = [parkingSpotReservations:ParkingSpotReservation]

    House house
    String licensePlate
    String make
    String model
    String color
    Boolean isOwnedByHouse

}
