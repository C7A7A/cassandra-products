package io.github.c7a7a.cassandraproducts.configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BaseCassandraTest {

    @BeforeAll
    static void beforeAll() {
        CassandraTestContainer.getInstance().start();
    }
}
