package com.miraijr.karaoke.application.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Query("select p from ProductEntity p where p.code = :code")
    ProductEntity findProductByCode(@Param("code") String code);
}
