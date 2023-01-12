package pl.inpost.test.service.order.compute;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.inpost.test.model.Product;
import pl.inpost.test.repository.ParametrizationRepository;

import java.math.BigDecimal;

import static pl.inpost.test.util.TestInpostUtils.computePercentage;

@Component
public class PercentageDiscountDecorator implements ComputePriceService {
    private final ParametrizationRepository parametrizationRepository;
    private final ComputePriceService computePriceService;

    public PercentageDiscountDecorator(ParametrizationRepository parametrizationRepository,
                                       @Qualifier("basicOrderPriceService") ComputePriceService computePriceService) {
        this.parametrizationRepository = parametrizationRepository;
        this.computePriceService = computePriceService;
    }

    @Override
    public BigDecimal computePrice(Product product, Integer amount) {
        BigDecimal price = computePriceService.computePrice(product, amount);
        BigDecimal discount = computeDiscount(price, amount);
        return price.subtract(discount);
    }

    private BigDecimal computeDiscount(BigDecimal price, Integer amount)  {
        Integer discountThreshold = parametrizationRepository.getPercentageDiscountThreshold();
        if(amount > discountThreshold) {
            Integer discountValue = parametrizationRepository.getPercentageDiscountValue();
            return computePercentage(price, discountValue);
        }

        return BigDecimal.ZERO;
    }
}
