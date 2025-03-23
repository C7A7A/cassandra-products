package io.github.c7a7a.cassandraproducts.services;

import io.github.c7a7a.cassandraproducts.data.Product;
import io.github.c7a7a.cassandraproducts.data.ProductDTO;
import io.github.c7a7a.cassandraproducts.exceptions.ResourceNotFoundException;
import io.github.c7a7a.cassandraproducts.repositories.ProductRepository;
import io.github.c7a7a.cassandraproducts.utils.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = productMapper.mapToProduct(productDTO);
        return productRepository.save(product);
    }

    public Product getProduct(String id) {
        UUID productId = UUID.fromString(id);
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ResourceNotFoundException("Product with id: " + id + " not found");
        }
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(String id, ProductDTO productDTO) {
        UUID productId = UUID.fromString(id);
        Product updatedProduct = productMapper.mapToProduct(productDTO);
        updatedProduct.setId(productId);

        if (productRepository.existsById(productId)) {
            return productRepository.save(updatedProduct);
        }

        throw new ResourceNotFoundException("Product with id: " + id + " not found");
    }

    public String deleteProduct(String id) {
        UUID productId = UUID.fromString(id);

        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return id;
        }

        throw new ResourceNotFoundException("Product with id: " + id + " not found");
    }
}
