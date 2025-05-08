package car.rental.requests;

import java.time.LocalDateTime;

import car.rental.models.enums.VehicleType;

public record VehicleSearchCriteria(VehicleType vehicleType, String userLocationId, LocalDateTime expectedPickUpTime,
        LocalDateTime expectedDropOffTime) {

}
