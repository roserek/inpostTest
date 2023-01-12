package pl.inpost.test.service.order.compute;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.inpost.test.model.Product;
import pl.inpost.test.repository.ParametrizationRepository;
import pl.inpost.test.util.TestInpostUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static pl.inpost.test.util.TestInpostUtils.computePercentage;

@ExtendWith(MockitoExtension.class)
public class PercentageDiscountDecoratorTest {
    @Mock
    private ParametrizationRepository parametrizationRepository;
    private ComputePriceService computePriceService;

    private PercentageDiscountDecorator percentageDiscountDecorator;

    @BeforeEach
    public void init() {
        computePriceService = new BasicComputePriceService();
        percentageDiscountDecorator = new PercentageDiscountDecorator(parametrizationRepository, computePriceService);
    }

    @Test
    public void computePriceWithDiscountTest() {
        Product product = new Product(UUID.randomUUID(), new BigDecimal("1234.34"));
        Integer amount = 5;

        when(parametrizationRepository.getPercentageDiscountThreshold()).thenReturn(4);
        when(parametrizationRepository.getPercentageDiscountValue()).thenReturn(20);

        BigDecimal computedPrice = percentageDiscountDecorator.computePrice(product, amount);

        Assertions.assertNotNull(computedPrice);
        Assertions.assertEquals(new BigDecimal("4937.36"), computedPrice);
    }

    @Test
    public void computePriceWithoutDiscountTest() {
        Product product = new Product(UUID.randomUUID(), new BigDecimal("1234.34"));
        Integer amount = 5;

        when(parametrizationRepository.getPercentageDiscountThreshold()).thenReturn(5);

        BigDecimal computedPrice = percentageDiscountDecorator.computePrice(product, amount);

        BigDecimal expectedPrice = computePriceService.computePrice(product, amount);
        Assertions.assertNotNull(computedPrice);
        Assertions.assertEquals(expectedPrice, computedPrice);
    }

}
