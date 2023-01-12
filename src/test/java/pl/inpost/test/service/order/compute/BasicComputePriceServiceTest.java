package pl.inpost.test.service.order.compute;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.inpost.test.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class BasicComputePriceServiceTest {

    private final ComputePriceService computePriceService = new BasicComputePriceService();

    @Test
    public void computePriceTest() {
        Product product = new Product(UUID.randomUUID(), new BigDecimal("1234.34"));
        Integer amount = 5;

        BigDecimal price = computePriceService.computePrice(product, amount);

        Assertions.assertNotNull(price);
        Assertions.assertEquals(new BigDecimal("6171.70"), price);
    }
}
