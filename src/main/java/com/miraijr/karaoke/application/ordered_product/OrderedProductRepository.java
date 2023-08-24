package com.miraijr.karaoke.application.ordered_product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProductEntity, OrderedProductId> {
}
