package pl.inpost.test.service.order.compute;

import org.springframework.stereotype.Component;
import pl.inpost.test.model.Product;

import java.math.BigDecimal;

import static pl.inpost.test.util.TestInpostUtils.multiplyPrice;

@Component("basicOrderPriceService")
public class BasicComputePriceService implements ComputePriceService {

    @Override
    public BigDecimal computePrice(Product product, Integer amount) {
        return multiplyPrice(product.getPrice(), amount);
    }

}
