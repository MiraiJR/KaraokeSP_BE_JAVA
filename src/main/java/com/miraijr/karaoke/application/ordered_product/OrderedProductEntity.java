package com.miraijr.karaoke.application.ordered_product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miraijr.karaoke.application.order.OrderEntity;
import com.miraijr.karaoke.application.product.ProductEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
@IdClass(OrderedProductId.class)
public class OrderedProductEntity {
    @JsonIgnore
    @Id
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}
    )
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(
            nullable = false
    )
    private Integer quantity;

    public OrderedProductEntity() {
    }

    public OrderedProductEntity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderProductEntity{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
