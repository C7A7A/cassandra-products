package io.github.c7a7a.cassandraproducts.controllers;

import io.github.c7a7a.cassandraproducts.data.Product;
import io.github.c7a7a.cassandraproducts.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public String getProducts() {
        return productService.getProducts();
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

}
