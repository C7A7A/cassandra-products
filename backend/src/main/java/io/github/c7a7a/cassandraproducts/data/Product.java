package io.github.c7a7a.cassandraproducts.data;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table("products")
public class Product {
    @PrimaryKey
    private UUID id;
    // create secondary index on category. Querying by category will be slower, but there is only 1 table to handle CRUD operations
    @Indexed
    @NotBlank(message = "Category cannot be empty")
    private Category category;
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 characters")
    private String name;
    private String description;
    @DecimalMin(value = "0.00", message = "Price must be 0 or positive")
    @Digits(integer = 10, fraction = 2, message = "Price must have up to 2 decimal places")
    private BigDecimal price;


    public Product(String name, Category category, String description, BigDecimal price)  {
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
