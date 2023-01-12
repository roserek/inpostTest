package pl.inpost.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.inpost.test.model.Product;
import pl.inpost.test.repository.ParametrizationRepository;
import pl.inpost.test.repository.ProductRepository;
import pl.inpost.test.service.order.OrderService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ComputePriceIntegrationTest {

    @MockBean
    private ParametrizationRepository parametrizationRepository;
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private OrderService orderService;

    @Test
    public void testBasic(){
        UUID uuid = UUID.randomUUID();
        Integer amount = 5;

        Product product = new Product(uuid, new BigDecimal("1234.12"));
        when(productRepository.getProductByUUID(uuid)).thenReturn(Optional.of(product));

        when(parametrizationRepository.getPercentageDiscountEnable()).thenReturn(Boolean.FALSE);
        when(parametrizationRepository.getAmountDiscountEnable()).thenReturn(Boolean.FALSE);

        BigDecimal price = orderService.calculateOrderPrice(uuid, amount);

        Assertions.assertNotNull(price);
        Assertions.assertEquals(new BigDecimal("6170.60"), price);
    }

    @Test
    public void testPercentageDiscount(){
        UUID uuid = UUID.randomUUID();
        Integer amount = 5;

        Product product = new Product(uuid, new BigDecimal("1234.12"));
        when(productRepository.getProductByUUID(uuid)).thenReturn(Optional.of(product));

        when(parametrizationRepository.getPercentageDiscountEnable()).thenReturn(Boolean.TRUE);
        when(parametrizationRepository.getPercentageDiscountValue()).thenReturn(10);
        when(parametrizationRepository.getPercentageDiscountThreshold()).thenReturn(3);

        BigDecimal price = orderService.calculateOrderPrice(uuid, amount);

        Assertions.assertNotNull(price);
        Assertions.assertEquals(new BigDecimal("5553.54"), price);
    }

    @Test
    public void testAmountDiscount(){
        UUID uuid = UUID.randomUUID();
        Integer amount = 5;

        Product product = new Product(uuid, new BigDecimal("1234.12"));
        when(productRepository.getProductByUUID(uuid)).thenReturn(Optional.of(product));

        when(parametrizationRepository.getPercentageDiscountEnable()).thenReturn(Boolean.FALSE);
        when(parametrizationRepository.getAmountDiscountEnable()).thenReturn(Boolean.TRUE);
        when(parametrizationRepository.getAmountDiscountValue()).thenReturn(10);
        when(parametrizationRepository.getAmountDiscountThreshold()).thenReturn(3);

        BigDecimal price = orderService.calculateOrderPrice(uuid, amount);

        Assertions.assertNotNull(price);
        Assertions.assertEquals(new BigDecimal("6120.60"), price);
    }

}
