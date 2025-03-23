package io.github.c7a7a.cassandraproducts.utils;

import io.github.c7a7a.cassandraproducts.data.Product;
import io.github.c7a7a.cassandraproducts.data.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product mapToProduct(ProductDTO productDTO) {
        return new Product(
                productDTO.name(),
                productDTO.description(),
                productDTO.price(),
                productDTO.category()
        );
    }

    public ProductDTO mapToProductDTO(Product product) {
        return new ProductDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
