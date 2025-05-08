package car.rental.rule.engine.impl;

import java.math.BigDecimal;

import car.rental.models.enums.VehicleType;
import car.rental.rule.engine.PricingRule;
import car.rental.rule.engine.context.PricingContext;

public class SurChargePricingRule implements PricingRule {
    private final BigDecimal surCharge;

    public SurChargePricingRule(BigDecimal surCharge) {
        this.surCharge = surCharge;
    }

    @Override
    public boolean isApplicable(PricingContext pricingContext) {
        return pricingContext.getVehicle().getVehicleType() == VehicleType.SUV;
    }

    @Override
    public BigDecimal apply(PricingContext pricingContext) {
        return pricingContext.getCurrentPrice().add(surCharge);
    }
}
