package car.rental.requests;

import java.time.LocalDateTime;

import car.rental.models.Location;

public record BookingRequest(String vehicleId, String userId, LocalDateTime expectedPickUpTime,
        LocalDateTime expectedDropOffTime, Location pickUpLocation, Location dropOffLocation) {

}
