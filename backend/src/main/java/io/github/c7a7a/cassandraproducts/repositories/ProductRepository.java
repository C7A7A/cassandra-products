package io.github.c7a7a.cassandraproducts.repositories;

import io.github.c7a7a.cassandraproducts.data.Category;
import io.github.c7a7a.cassandraproducts.data.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends CassandraRepository<Product, UUID> {
    @Query("SELECT * FROM products WHERE category = ?0")
    List<Product> findByCategory(Category category);
}
