package com.miraijr.karaoke.application.product.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UpdateProductDTO {
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must have 2 characters at least")
    private String name;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive number")
    private Long price;
    @NotNull(message = "Name cannot be null")
    private String groupProduct;

    public UpdateProductDTO() {
    }

    public UpdateProductDTO(@NotNull(message = "Name cannot be null") String name, @NotNull(message = "Price cannot be null") Long price, @NotNull(message = "Name cannot be null") String groupProduct) {
        this.name = name;
        this.price = price;
        this.groupProduct = groupProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(String groupProduct) {
        this.groupProduct = groupProduct;
    }
}
