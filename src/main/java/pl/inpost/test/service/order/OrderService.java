package pl.inpost.test.service.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.inpost.test.exception.ProductNotFoundException;
import pl.inpost.test.model.Product;
import pl.inpost.test.repository.ProductRepository;
import pl.inpost.test.service.order.compute.ComputePriceService;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final ComputePriceServiceFactory computePriceServiceFactory;

    public BigDecimal calculateOrderPrice(UUID productUUID, Integer amount) {
        log.info(String.format("Calculating price for order: product UUID %s, amount %s", productUUID, amount));

        Product product = productRepository.getProductByUUID(productUUID)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with UUID %s not found.", productUUID)));

        ComputePriceService computePriceService = computePriceServiceFactory.getComputePriceService();

        return computePriceService.computePrice(product, amount);
    }

}
