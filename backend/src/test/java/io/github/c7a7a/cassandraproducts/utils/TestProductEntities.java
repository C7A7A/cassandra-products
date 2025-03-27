package io.github.c7a7a.cassandraproducts.utils;

import io.github.c7a7a.cassandraproducts.data.Category;
import io.github.c7a7a.cassandraproducts.data.Product;
import io.github.c7a7a.cassandraproducts.data.ProductDTO;

import java.math.BigDecimal;

public class TestProductEntities {
    public static Product defaultProduct() {
        return new Product(
                "Test Product",
                Category.CLOTHES,
                "Descr",
                new BigDecimal("42.00")
        );
    }

    public static ProductDTO defaultProductDTO() {
        return new ProductDTO(
                "Test Product",
                Category.CLOTHES,
                "Descr",
                new BigDecimal("42.00")
        );
    }
}
