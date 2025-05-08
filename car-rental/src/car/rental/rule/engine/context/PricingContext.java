package car.rental.rule.engine.context;

import java.math.BigDecimal;

import car.rental.models.Booking;
import car.rental.models.Vehicle;
import car.rental.rule.engine.context.enums.PricingPhase;

public class PricingContext {
    private BigDecimal currentPrice;
    private PricingPhase pricingPhase;
    private Vehicle vehicle;
    private Booking booking;

    public PricingContext(BigDecimal currentPrice, PricingPhase pricingPhase, Vehicle vehicle, Booking booking) {
        this.currentPrice = currentPrice;
        this.pricingPhase = pricingPhase;
        this.vehicle = vehicle;
        this.booking = booking;
        this.currentPrice = BigDecimal.ZERO;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public PricingPhase getPricingPhase() {
        return pricingPhase;
    }

    public void setPricingPhase(PricingPhase pricingPhase) {
        this.pricingPhase = pricingPhase;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
