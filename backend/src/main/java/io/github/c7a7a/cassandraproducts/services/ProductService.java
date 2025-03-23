package io.github.c7a7a.cassandraproducts.services;

import io.github.c7a7a.cassandraproducts.data.Product;
import io.github.c7a7a.cassandraproducts.data.ProductDTO;
import io.github.c7a7a.cassandraproducts.repositories.ProductRepository;
import io.github.c7a7a.cassandraproducts.utils.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.mapToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductDTO(savedProduct);
    }

    public String getProduct(String id) {
        return "GET PRODUCT " + id;
    }

    public String getProducts() {
        return "GET PRODUCTS";
    }

    public String updateProduct(String id, ProductDTO productDTO) {
        return "UPDATE PRODUCT " + id;
    }

    public String deleteProduct(String id) {
        return "DESTROY PRODUCT " + id;
    }
}
