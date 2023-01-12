package pl.inpost.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Product {
    private final UUID id;
    private final BigDecimal price;
}
