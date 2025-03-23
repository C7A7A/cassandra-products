package io.github.c7a7a.cassandraproducts.services;

import io.github.c7a7a.cassandraproducts.data.Category;
import io.github.c7a7a.cassandraproducts.data.Product;
import io.github.c7a7a.cassandraproducts.data.ProductDTO;
import io.github.c7a7a.cassandraproducts.repositories.ProductRepository;
import io.github.c7a7a.cassandraproducts.utils.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductCategoryService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getProductsByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                .map(productMapper::mapToProductDTO)
                .collect(Collectors.toList());
    }
}
