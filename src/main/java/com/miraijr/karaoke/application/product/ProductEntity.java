package com.miraijr.karaoke.application.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miraijr.karaoke.application.group_product.GroupProductEntity;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    private String code;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "group_product_id")
    private GroupProductEntity groupProduct;

    @Column(
            nullable = false
    )
    private Long price;

    public ProductEntity() {
    }

    public ProductEntity(String name, String code, Long price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GroupProductEntity getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(GroupProductEntity groupProduct) {
        this.groupProduct = groupProduct;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", groupProduct=" + groupProduct +
                ", price=" + price +
                '}';
    }
}
