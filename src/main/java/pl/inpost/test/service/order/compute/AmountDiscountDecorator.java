package pl.inpost.test.service.order.compute;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.inpost.test.model.Product;
import pl.inpost.test.repository.ParametrizationRepository;

import java.math.BigDecimal;

import static pl.inpost.test.util.TestInpostUtils.multiplyPrice;

@Component
public class AmountDiscountDecorator implements ComputePriceService{
    private final ParametrizationRepository parametrizationRepository;
    private final ComputePriceService computePriceService;

    public AmountDiscountDecorator(ParametrizationRepository parametrizationRepository,
                                   @Qualifier("basicOrderPriceService") ComputePriceService computePriceService) {
        this.parametrizationRepository = parametrizationRepository;
        this.computePriceService = computePriceService;
    }

    @Override
    public BigDecimal computePrice(Product product, Integer amount) {
        BigDecimal price = computePriceService.computePrice(product, amount);
        BigDecimal discount = computeDiscount(amount);
        return price.subtract(discount);
    }

    private BigDecimal computeDiscount(Integer amount)  {
        Integer discountThreshold = parametrizationRepository.getAmountDiscountThreshold();
        if(amount > discountThreshold) {
            Integer discountValue = parametrizationRepository.getAmountDiscountValue();
            return multiplyPrice(new BigDecimal(discountValue), amount);
        }

        return BigDecimal.ZERO;
    }
}
