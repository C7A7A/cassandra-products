package io.github.c7a7a.cassandraproducts.services;

import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {
    public String getProductsByCategory(String category) {
        return "GET PRODUCTS BY CATEGORY " + category;
    }
}
