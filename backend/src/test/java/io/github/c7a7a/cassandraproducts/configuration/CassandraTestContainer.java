package io.github.c7a7a.cassandraproducts.configuration;

import org.testcontainers.cassandra.CassandraContainer;

public class CassandraTestContainer extends CassandraContainer {
    private static final String IMAGE_VERSION = "cassandra:4.0";

    private static CassandraTestContainer container;

    private CassandraTestContainer() {
        super(IMAGE_VERSION);
    }

    public static CassandraTestContainer getInstance() {
        if (container == null) {
            container = new CassandraTestContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("spring.data.cassandra.contact-points", container.getHost());
        System.setProperty("spring.data.cassandra.port", container.getMappedPort(9042).toString());
    }
}
