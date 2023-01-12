package pl.inpost.test.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * For simplicity, I store parametrization in application properties.
 * Instead of this solution, params can be store in database or no sql cache (e.g. redis)
 * We could than modify behavior in runtime.
 */
@Getter
@Repository
public class ParametrizationRepository {
    @Value("${application.order.discount.amount.enable}")
    private Boolean amountDiscountEnable;
    @Value("${application.order.discount.amount.value}")
    private Integer amountDiscountValue;
    @Value("${application.order.discount.amount.threshold}")
    private Integer amountDiscountThreshold;

    @Value("${application.order.discount.percentage.enable}")
    private Boolean percentageDiscountEnable;
    @Value("${application.order.discount.percentage.value}")
    private Integer percentageDiscountValue;
    @Value("${application.order.discount.percentage.threshold}")
    private Integer percentageDiscountThreshold;
}
