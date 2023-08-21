package com.miraijr.karaoke.application.group_product;

import com.miraijr.karaoke.application.product.ProductEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "group_products")
public class GroupProductEntity {
    @Id
    private String code;

    @Column(
            nullable = false
    )
    private String name;

    @OneToMany(
            mappedBy = "groupProduct",
            cascade = CascadeType.ALL
    )
    private List<ProductEntity> products;

    public GroupProductEntity() {
    }

    public GroupProductEntity(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "GroupProduct{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
