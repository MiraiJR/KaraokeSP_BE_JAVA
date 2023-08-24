package com.miraijr.karaoke.application.ordered_product.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderedProductDTO {
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    @NotNull(message = "ProductId cannot be null")
    @Positive(message = "ProductId must be positive number")
    private Integer productId;

    public OrderedProductDTO() {
    }

    public OrderedProductDTO(@NotNull(message = "Quantity cannot be null") Integer quantity, @NotNull(message = "ProductId cannot be null") Integer productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
