package car.rental.repos;

import java.time.LocalDateTime;

import car.rental.models.Booking;

public interface BookingRepository {
    int countActiveOrConfirmedBookingsForVehicleInDateRange(String vehicleId, LocalDateTime startDate,
            LocalDateTime endDate);

    Booking save(Booking booking);

    Booking findBookingById(String bookingId);

    Booking update(Booking booking);

}
