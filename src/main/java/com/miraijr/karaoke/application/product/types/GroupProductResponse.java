package com.miraijr.karaoke.application.product.types;

import com.miraijr.karaoke.application.group_product.GroupProductEntity;
import com.miraijr.karaoke.application.product.ProductEntity;

import java.util.List;

public class GroupProductResponse {
    private GroupProductEntity groupProductEntity;
    private List<ProductEntity> products;

    public GroupProductResponse() {
    }

    public GroupProductResponse(GroupProductEntity groupProductEntity, List<ProductEntity> products) {
        this.groupProductEntity = groupProductEntity;
        this.products = products;
    }

    public GroupProductEntity getGroupProductEntity() {
        return groupProductEntity;
    }

    public void setGroupProductEntity(GroupProductEntity groupProductEntity) {
        this.groupProductEntity = groupProductEntity;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
