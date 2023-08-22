package com.miraijr.karaoke.application.group_product;

import com.miraijr.karaoke.application.product.ProductEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
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
            fetch = FetchType.EAGER
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

    public void addProduct(ProductEntity newProduct) {
        if(products == null) {
            products = new ArrayList<>();
        }

        products.add(newProduct);
        newProduct.setGroupProduct(this);
    }

    @Override
    public String toString() {
        return "GroupProductEntity{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
