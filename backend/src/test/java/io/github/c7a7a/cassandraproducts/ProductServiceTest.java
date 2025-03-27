package io.github.c7a7a.cassandraproducts;

import io.github.c7a7a.cassandraproducts.configuration.BaseCassandraTest;
import io.github.c7a7a.cassandraproducts.data.Category;
import io.github.c7a7a.cassandraproducts.data.Product;
import io.github.c7a7a.cassandraproducts.data.ProductDTO;
import io.github.c7a7a.cassandraproducts.exceptions.ResourceNotFoundException;
import io.github.c7a7a.cassandraproducts.repositories.ProductRepository;
import io.github.c7a7a.cassandraproducts.services.ProductService;
import io.github.c7a7a.cassandraproducts.utils.TestProductEntities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest extends BaseCassandraTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    private final Product product = TestProductEntities.defaultProduct();
    private final ProductDTO productDTO = TestProductEntities.defaultProductDTO();

    @BeforeEach
    void cleanDB() {
        productRepository.deleteAll();
    }

    @Test
    void createProduct_shouldCreateProductInCassandra() {
        Product createdProduct = productService.createProduct(productDTO);
        assertNotNull(createdProduct.getId());

        Optional<Product> savedProduct = productRepository.findById(createdProduct.getId());
        assertTrue(savedProduct.isPresent());
        assertEquals(productDTO.getName(), savedProduct.get().getName());
        assertEquals(productDTO.getCategory(), savedProduct.get().getCategory());
    }

    @Test
    void getProduct_shouldReturnProductFromDB() {
        Product savedProduct = productRepository.save(product);

        Product retrievedProduct = productService.getProduct(savedProduct.getId().toString());

        assertEquals(product.getName(), retrievedProduct.getName());
        assertEquals(product.getCategory(), retrievedProduct.getCategory());
    }

    @Test
    void getProduct_shouldReturnResourceNotFoundException_WhenNotExists() {
        assertThrows(ResourceNotFoundException.class, () ->
                productService.getProduct(UUID.randomUUID().toString())
        );
    }

    @Test
    void getProducts_shouldReturnAllProductsFromDB() {
        Product product2 = TestProductEntities.defaultProduct();
        product2.setName("Product B");
        product2.setCategory(Category.ELECTRONICS);

        productRepository.saveAll(List.of(product, product2));

        List<Product> products = productService.getProducts();

        assertEquals(2, products.size());
        assertTrue(products.stream().anyMatch(p -> p.getName().equals(product.getName())));
        assertTrue(products.stream().anyMatch(p -> p.getCategory() == product.getCategory()));
        assertTrue(products.stream().anyMatch(p -> p.getName().equals(product2.getName())));
        assertTrue(products.stream().anyMatch(p -> p.getCategory() == product2.getCategory()));
    }

    @Test
    void updateProduct_shouldUpdateExistingProduct() {
        Product savedProduct = productRepository.save(product);
        ProductDTO updateDTO = new ProductDTO(
                "Updated name",
                Category.CLOTHES,
                "New Descr",
                new BigDecimal("1999.99")
        );

        productService.updateProduct(savedProduct.getId().toString(), updateDTO);
        Optional<Product> updatedProduct = productRepository.findById(savedProduct.getId());
        assertTrue(updatedProduct.isPresent());

        assertEquals(updateDTO.getName(), updatedProduct.get().getName());
        assertEquals(updateDTO.getPrice(), updatedProduct.get().getPrice());
    }

    @Test
    void updateProduct_shouldThrowResourceNotFoundException_WhenNotExists() {
        assertThrows(ResourceNotFoundException.class, () ->
                productService.updateProduct(UUID.randomUUID().toString(), productDTO)
        );
    }

    @Test
    void deleteProduct_shouldDeleteProductFromDB() {
        Product savedProduct = productRepository.save(product);

        productService.deleteProduct(product.getId().toString());

        assertFalse(productRepository.existsById(savedProduct.getId()));
    }

    @Test
    void deleteProduct_shouldThrowResourceNotFoundException_WhenNotExists() {
        assertThrows(ResourceNotFoundException.class, () ->
            productService.deleteProduct(UUID.randomUUID().toString())
        );
    }
}
