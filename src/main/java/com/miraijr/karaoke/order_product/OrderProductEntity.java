package com.miraijr.karaoke.order_product;

import com.miraijr.karaoke.order.OrderEntity;
import com.miraijr.karaoke.product.ProductEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProductEntity {
    @Id
    @OneToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}
    )
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Id
    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(
            nullable = false
    )
    private Integer quantity;
}
