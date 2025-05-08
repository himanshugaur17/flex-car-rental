package car.rental.service;

import car.rental.models.Booking;
import car.rental.models.User;
import car.rental.requests.BookingRequest;
import car.rental.requests.ConfirmPaymentRequest;

public interface BookingService {
    Booking initiateBooking(BookingRequest bookingRequest);

    Booking confirmPayment(ConfirmPaymentRequest confirmPaymentRequest);

    Booking processVehiclePickup(User user, String bookingId);

    Booking processVehicleReturn(User user, String bookingId);

    Booking cancelBooking(User user, String bookingId);
}
