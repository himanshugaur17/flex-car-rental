package car.rental.rule.engine;

import java.math.BigDecimal;
import java.util.List;

import car.rental.rule.engine.context.PricingContext;

public class PricingRuleEngine {
    private final List<PricingRule> pricingRules;

    public PricingRuleEngine(List<PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
    }

    public BigDecimal applyRules(PricingContext pricingContext) {
        for (PricingRule pricingRule : pricingRules) {
            if (pricingRule.isApplicable(pricingContext)) {
                pricingContext.setCurrentPrice(pricingRule.apply(pricingContext));
            }
        }
        return pricingContext.getCurrentPrice();
    }
}
