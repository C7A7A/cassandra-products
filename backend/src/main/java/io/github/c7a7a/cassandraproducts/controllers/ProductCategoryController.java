package io.github.c7a7a.cassandraproducts.controllers;

import io.github.c7a7a.cassandraproducts.services.ProductCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/category")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/{category}")
    public String getProductsByCategory(@PathVariable String category) {
        return productCategoryService.getProductsByCategory(category);
    }
}
