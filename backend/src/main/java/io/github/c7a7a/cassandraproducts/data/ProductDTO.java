package io.github.c7a7a.cassandraproducts.data;

import java.math.BigDecimal;

public record ProductDTO(String name, String description, BigDecimal price, Category category) {
}
