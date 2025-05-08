package car.rental.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import car.rental.models.enums.BookingStatus;

public class Booking {
    private final int bookingId;
    private final Vehicle vehicle;
    private final User user;
    private final Location pickUpLocation;
    private final Location dropOffLocation;
    private final LocalDateTime expectedPickUpTime;
    private final LocalDateTime expectedDropOffTime;
    private LocalDateTime actualPickUpTime;
    private LocalDateTime actualDropOffTime;
    private BigDecimal totalPrice;
    private BigDecimal estimatedPrice;
    private final AtomicInteger bookingIdGenerator = new AtomicInteger(0);

    public void setEstimatedPrice(BigDecimal estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    private BookingStatus status;

    public int getBookingId() {
        return bookingId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public User getUser() {
        return user;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public LocalDateTime getExpectedPickUpTime() {
        return expectedPickUpTime;
    }

    public LocalDateTime getExpectedDropOffTime() {
        return expectedDropOffTime;
    }

    public LocalDateTime getActualPickUpTime() {
        return actualPickUpTime;
    }

    public void setActualPickUpTime(LocalDateTime actualPickUpTime) {
        this.actualPickUpTime = actualPickUpTime;
    }

    public LocalDateTime getActualDropOffTime() {
        return actualDropOffTime;
    }

    public void setActualDropOffTime(LocalDateTime actualDropOffTime) {
        this.actualDropOffTime = actualDropOffTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getEstimatedPrice() {
        return estimatedPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Booking(int bookingId, Vehicle vehicle, User user, Location pickUpLocation, Location dropOffLocation,
            LocalDateTime expectedPickUpTime, LocalDateTime expectedDropOffTime, BigDecimal totalPrice,
            BigDecimal estimatedPrice, BookingStatus status) {
        this.bookingId = bookingId;
        this.vehicle = vehicle;
        this.user = user;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.expectedPickUpTime = expectedPickUpTime;
        this.expectedDropOffTime = expectedDropOffTime;
        this.totalPrice = totalPrice;
        this.estimatedPrice = estimatedPrice;
        this.status = status;
    }

    public Booking(Vehicle vehicle, User user, Location pickUpLocation, Location dropOffLocation,
            LocalDateTime expectedPickUpTime, LocalDateTime expectedDropOffTime) {
        this.bookingId = bookingIdGenerator.incrementAndGet(); // Initialize the final field
        this.vehicle = vehicle;
        this.user = user;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.expectedPickUpTime = expectedPickUpTime;
        this.expectedDropOffTime = expectedDropOffTime;
        this.status = BookingStatus.PENDING; // Set initial status
        this.estimatedPrice = BigDecimal.ZERO; // Initialize price fields
        this.totalPrice = BigDecimal.ZERO;
    }

}
