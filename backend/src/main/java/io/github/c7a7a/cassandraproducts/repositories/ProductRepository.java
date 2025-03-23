package io.github.c7a7a.cassandraproducts.repositories;

import io.github.c7a7a.cassandraproducts.data.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends CassandraRepository<Product, UUID> {
}
