package car.rental.repos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import car.rental.models.Booking;
import car.rental.models.enums.BookingStatus;

public class BookingRepositoryImpl implements BookingRepository {
    private Map<Integer, Booking> bookings = new HashMap<>();
    private final Map<Integer, List<Booking>> bookingByVehicleId = new HashMap<>();

    @Override
    public int countActiveOrConfirmedBookingsForVehicleInDateRange(String vehicleId, LocalDateTime startDate,
            LocalDateTime endDate) {
        List<Booking> bookings = bookingByVehicleId.get(Integer.parseInt(vehicleId));
        if (bookings == null) {
            return 0;
        }
        return (int) bookings.stream()
                .filter(booking -> booking.getStatus() == BookingStatus.CONFIRMED)
                .filter(booking -> startDate.isBefore(booking.getExpectedDropOffTime())
                        && endDate.isAfter(booking.getExpectedPickUpTime()))
                .count();
    }

    @Override
    public Booking save(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
        bookingByVehicleId.computeIfAbsent(booking.getVehicle().getVehicleId(), k -> new ArrayList<>()).add(booking);
        return booking;
    }

    @Override
    public Booking findBookingById(String bookingId) {
        return bookings.get(Integer.parseInt(bookingId));
    }

    @Override
    public Booking update(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
        bookingByVehicleId.computeIfAbsent(booking.getVehicle().getVehicleId(), k -> new ArrayList<>()).add(booking);
        return booking;
    }

}
