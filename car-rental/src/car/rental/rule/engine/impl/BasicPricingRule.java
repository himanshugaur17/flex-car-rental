package car.rental.rule.engine.impl;

import java.math.BigDecimal;
import java.util.Map;

import car.rental.models.enums.VehicleType;
import car.rental.rule.engine.PricingRule;
import car.rental.rule.engine.context.PricingContext;
import car.rental.rule.engine.context.enums.PricingPhase;

public class BasicPricingRule implements PricingRule {
    private final Map<VehicleType, BigDecimal> pricePerDay;

    public BasicPricingRule(Map<VehicleType, BigDecimal> pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public boolean isApplicable(PricingContext pricingContext) {
        return true;
    }

    @Override
    public BigDecimal apply(PricingContext pricingContext) {
        VehicleType vehicleType = pricingContext.getVehicle().getVehicleType();
        BigDecimal currentPrice = pricingContext.getCurrentPrice();
        int durationInDays = 1;
        if (pricingContext.getPricingPhase() == PricingPhase.ESTIMATE) {
            durationInDays = Math.max(durationInDays,
                    pricingContext.getBooking().getExpectedDropOffTime().getDayOfYear()
                            - pricingContext.getBooking().getExpectedPickUpTime().getDayOfYear());

        } else {
            durationInDays = Math.max(durationInDays,
                    pricingContext.getBooking().getActualDropOffTime().getDayOfYear()
                            - pricingContext.getBooking().getActualPickUpTime().getDayOfYear());
        }

        return currentPrice.add(pricePerDay.get(vehicleType).multiply(BigDecimal.valueOf(durationInDays)));
    }
}
