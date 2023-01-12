package pl.inpost.test.service.order.compute;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.inpost.test.model.Product;
import pl.inpost.test.repository.ParametrizationRepository;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AmountDiscountDecoratorTest {
    @Mock
    private ParametrizationRepository parametrizationRepository;
    private ComputePriceService computePriceService;

    private AmountDiscountDecorator amountDiscountDecorator;

    @BeforeEach
    public void init() {
        computePriceService = new BasicComputePriceService();
        amountDiscountDecorator = new AmountDiscountDecorator(parametrizationRepository, computePriceService);
    }

    @Test
    public void computePriceWithDiscountTest() {
        Product product = new Product(UUID.randomUUID(), new BigDecimal("1234.34"));
        Integer amount = 5;

        when(parametrizationRepository.getAmountDiscountThreshold()).thenReturn(4);
        when(parametrizationRepository.getAmountDiscountValue()).thenReturn(20);

        BigDecimal computedPrice = amountDiscountDecorator.computePrice(product, amount);

        BigDecimal expectedPrice = computePriceService.computePrice(product, amount).subtract(BigDecimal.valueOf(100));
        Assertions.assertNotNull(computedPrice);
        Assertions.assertEquals(expectedPrice, computedPrice);
    }

    @Test
    public void computePriceWithoutDiscountTest() {
        Product product = new Product(UUID.randomUUID(), new BigDecimal("1234.34"));
        Integer amount = 5;

        when(parametrizationRepository.getAmountDiscountThreshold()).thenReturn(5);

        BigDecimal computedPrice = amountDiscountDecorator.computePrice(product, amount);

        BigDecimal expectedPrice = computePriceService.computePrice(product, amount);
        Assertions.assertNotNull(computedPrice);
        Assertions.assertEquals(expectedPrice, computedPrice);
    }

}
