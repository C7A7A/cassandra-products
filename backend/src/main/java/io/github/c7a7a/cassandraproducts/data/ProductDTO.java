package io.github.c7a7a.cassandraproducts.data;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductDTO {
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 characters")
    private String name;
    @NotNull(message = "Category cannot be null")
    private Category category;
    private String description;
    @DecimalMin(value = "0.00", message = "Price must be 0 or positive")
    @Digits(integer = 10, fraction = 2, message = "Price must have up to 2 decimal places")
    private BigDecimal price;

    public ProductDTO(String name, Category category, String description, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public ProductDTO() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
}
