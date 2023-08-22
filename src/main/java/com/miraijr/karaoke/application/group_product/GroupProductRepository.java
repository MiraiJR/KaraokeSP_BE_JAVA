package com.miraijr.karaoke.application.group_product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupProductRepository extends JpaRepository<GroupProductEntity, String> {
    @Query("select gp from GroupProductEntity gp where gp.code = :code")
    GroupProductEntity findGroupProductByCode(@Param("code") String code);
}
