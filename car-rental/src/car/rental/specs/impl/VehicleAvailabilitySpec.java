package car.rental.specs.impl;

import java.time.LocalDateTime;

import car.rental.models.Vehicle;
import car.rental.repos.BookingRepository;
import car.rental.specs.ISpecification;

public class VehicleAvailabilitySpec implements ISpecification<Vehicle> {
    private final BookingRepository bookingRepository;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public VehicleAvailabilitySpec(BookingRepository bookingRepository, LocalDateTime startDate,
            LocalDateTime endDate) {
        this.bookingRepository = bookingRepository;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean isSatisfied(Vehicle item) {
        int count = bookingRepository.countActiveOrConfirmedBookingsForVehicleInDateRange(
                String.valueOf(item.getVehicleId()),
                startDate,
                endDate);
        return count == 0;
    }

}
