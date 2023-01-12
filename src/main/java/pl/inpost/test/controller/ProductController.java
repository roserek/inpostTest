package pl.inpost.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inpost.test.model.Product;
import pl.inpost.test.repository.ProductRepository;
import pl.inpost.test.service.order.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final OrderService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProductList() {
        return ResponseEntity.ok(productRepository.getAllProducts());
    }

    @GetMapping("/{productUUID}/price")
    public ResponseEntity<BigDecimal> calculateProductPrice(@PathVariable UUID productUUID,
                                                          @RequestParam Integer amount) {
        return ResponseEntity.ok(productService.calculateOrderPrice(productUUID, amount));
    }

}
