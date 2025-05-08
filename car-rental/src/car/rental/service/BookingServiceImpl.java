package car.rental.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import car.rental.models.Booking;
import car.rental.models.User;
import car.rental.models.Vehicle;
import car.rental.models.enums.BookingStatus;
import car.rental.repos.BookingRepository;
import car.rental.repos.UserRepository;
import car.rental.requests.BookingRequest;
import car.rental.requests.ConfirmPaymentRequest;
import car.rental.rule.engine.PricingRuleEngine;
import car.rental.rule.engine.context.PricingContext;
import car.rental.rule.engine.context.enums.PricingPhase;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final VehicleInventoryService vehicleInventoryService;
    private final UserRepository userRepository;
    private final PricingRuleEngine pricingRuleEngine;

    public BookingServiceImpl(BookingRepository bookingRepository, VehicleInventoryService vehicleInventoryService,
            UserRepository userRepository, PricingRuleEngine pricingRuleEngine) {
        this.bookingRepository = bookingRepository;
        this.vehicleInventoryService = vehicleInventoryService;
        this.userRepository = userRepository;
        this.pricingRuleEngine = pricingRuleEngine;
    }

    public Booking initiateBooking(BookingRequest bookingRequest) {
        String vehicleId = bookingRequest.vehicleId();
        String userId = bookingRequest.userId();
        LocalDateTime expectedPickUpTime = bookingRequest.expectedPickUpTime();
        LocalDateTime expectedDropOffTime = bookingRequest.expectedDropOffTime();
        Vehicle vehicle = vehicleInventoryService.findVehicleById(vehicleId);
        synchronized (vehicle) {
            int count = bookingRepository.countActiveOrConfirmedBookingsForVehicleInDateRange(vehicleId,
                    expectedPickUpTime, expectedDropOffTime);
            if (count > 0) {
                throw new IllegalStateException("Vehicle is already booked for the given date range");
            }
            User user = userRepository.findUserById(userId);
            Booking booking = new Booking(vehicle, user, bookingRequest.pickUpLocation(),
                    bookingRequest.dropOffLocation(), expectedPickUpTime, expectedDropOffTime);
            PricingContext pricingContext = new PricingContext(BigDecimal.ZERO, PricingPhase.ESTIMATE, vehicle,
                    booking);
            bookingRepository.save(booking);
            pricingRuleEngine.applyRules(pricingContext);
            booking.setEstimatedPrice(pricingContext.getCurrentPrice());
            bookingRepository.save(booking);
            return booking;
        }

    }

    @Override
    public Booking confirmPayment(ConfirmPaymentRequest confirmPaymentRequest) {
        Booking booking = bookingRepository.findBookingById(confirmPaymentRequest.bookingId());
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking processVehiclePickup(User user, String bookingId) {
        Booking booking = bookingRepository.findBookingById(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        booking.setActualPickUpTime(LocalDateTime.now());
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking processVehicleReturn(User user, String bookingId) {
        Booking booking = bookingRepository.findBookingById(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        PricingContext pricingContext = new PricingContext(BigDecimal.ZERO, PricingPhase.FINAL, booking.getVehicle(),
                booking);
        pricingRuleEngine.applyRules(pricingContext);
        booking.setActualDropOffTime(LocalDateTime.now());
        booking.setTotalPrice(pricingContext.getCurrentPrice());
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking cancelBooking(User user, String bookingId) {
        Booking booking = bookingRepository.findBookingById(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return booking;
    }

}
