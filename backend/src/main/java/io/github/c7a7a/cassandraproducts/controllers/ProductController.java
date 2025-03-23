package io.github.c7a7a.cassandraproducts.controllers;

import io.github.c7a7a.cassandraproducts.data.ProductDTO;
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
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
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
    public String updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

}
