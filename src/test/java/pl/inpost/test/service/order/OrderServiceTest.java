package pl.inpost.test.service.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.inpost.test.exception.ProductNotFoundException;
import pl.inpost.test.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ComputePriceServiceFactory computePriceServiceFactory;

    private OrderService orderService;

    @BeforeEach
    public void init() {
        orderService = new OrderService(productRepository, computePriceServiceFactory);
    }

    @Test
    public void productNotFoundTest() {
        UUID uuid = UUID.randomUUID();
        Integer amount = 5;

        when(productRepository.getProductByUUID(uuid)).thenReturn(Optional.empty());

        ProductNotFoundException exception = Assertions.assertThrows(ProductNotFoundException.class, () -> {
            orderService.calculateOrderPrice(uuid, amount);
        });

        Assertions.assertEquals(String.format("Product with UUID %s not found.", uuid), exception.getMessage());
    }
}
