package car.rental.rule.engine;

import java.math.BigDecimal;

import car.rental.rule.engine.context.PricingContext;

public interface PricingRule {
    boolean isApplicable(PricingContext pricingContext);

    BigDecimal apply(PricingContext pricingContext);
}
