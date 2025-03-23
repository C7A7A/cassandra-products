package io.github.c7a7a.cassandraproducts.repositories;

import io.github.c7a7a.cassandraproducts.data.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {
    @Query("SELECT * FROM users WHERE username = ?0")
    User findByUsername(String username);
}
