package io.github.c7a7a.cassandraproducts.services;

import io.github.c7a7a.cassandraproducts.data.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public Product createProduct(Product product) {
        return product;
    }

    public String getProduct(String id) {
        return "GET PRODUCT " + id;
    }

    public String getProducts() {
        return "GET PRODUCTS";
    }

    public String updateProduct(String id, Product product) {
        return "UPDATE PRODUCT " + id;
    }

    public String deleteProduct(String id) {
        return "DESTROY PRODUCT " + id;
    }
}
