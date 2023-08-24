package com.miraijr.karaoke.application.ordered_product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProductEntity, OrderedProductId> {
    void deleteByOrder_IdAndProduct_Id(Integer orderId, Integer productId);
}
