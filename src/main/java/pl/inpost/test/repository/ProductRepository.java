package pl.inpost.test.repository;

import org.springframework.stereotype.Repository;
import pl.inpost.test.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Here instead of static resources in real word we would invoke dao to fetch data from database.
 * We also could implement client to another microservice that stores product information.
 */
@Repository
public class ProductRepository {
    private final static List<Product> AVAILABLE_PRODUCT_LIST = List.of(
            new Product(UUID.randomUUID(), new BigDecimal("5123.13")),
            new Product(UUID.randomUUID(), new BigDecimal("1234.43")),
            new Product(UUID.randomUUID(), new BigDecimal("1233.63")),
            new Product(UUID.randomUUID(), new BigDecimal("2123.73")),
            new Product(UUID.randomUUID(), new BigDecimal("1323.25")),
            new Product(UUID.randomUUID(), new BigDecimal("1823.24")),
            new Product(UUID.randomUUID(), new BigDecimal("1523.22"))
    );

    public List<Product> getAllProducts() {
        return AVAILABLE_PRODUCT_LIST;
    }

    public Optional<Product> getProductByUUID(UUID productUUID) {
        return AVAILABLE_PRODUCT_LIST.stream()
                .filter(product -> productUUID.equals(product.getId()))
                .findFirst();
    }

}
