package io.github.c7a7a.cassandraproducts.data;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table("products")
public class Product {
    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED)
    private UUID id;
    @PrimaryKeyColumn(name = "category", type = PrimaryKeyType.PARTITIONED)
    private Category category;
    private String name;
    private String description;
    private BigDecimal price;


    public Product(String name, String description, BigDecimal price, Category category) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
