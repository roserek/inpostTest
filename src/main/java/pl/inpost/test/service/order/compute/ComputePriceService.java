package pl.inpost.test.service.order.compute;

import pl.inpost.test.model.Product;

import java.math.BigDecimal;

public interface ComputePriceService {
    BigDecimal computePrice(Product product, Integer amount);
}
